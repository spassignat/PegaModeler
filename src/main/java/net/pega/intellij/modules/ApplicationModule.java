/*
 * Copyright (c) 2023-2023 Stephane Passignat - Exygen
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is furnished
 * to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE AND NON INFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package net.pega.intellij.modules;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intellij.openapi.project.Project;
import net.pega.intellij.modeler.ApplicationService;
import net.pega.intellij.BaseModule;
import net.pega.intellij.modeler.Rule;
import net.pega.intellij.modeler.RuleListener;
import net.pega.model.RuleApplication;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static net.pega.intellij.modeler.PegaPlugin.RULE_LISTENER_TOPIC;

public class ApplicationModule extends BaseModule implements ApplicationService {
	private List<RuleApplication> allApplications = new ArrayList<RuleApplication>();
	private RuleApplication ruleApplication;

	public ApplicationModule(@NotNull Project project) {
		super(project);
	}

	public void analyse(PrintStream out, Project project, Rule rule) {
		try (CloseableHttpResponse response = execute("applications")) {
			final ObjectMapper objectMapper = new ObjectMapper();
			final InputStream content = response.getEntity().getContent();
			final List<RuleApplication> ruleApplications = objectMapper.readValue(content, new TypeReference<List<RuleApplication>>() {
			});
			ruleListener.clearApplications();
			for (RuleApplication ruleApplication : ruleApplications) {
				ruleListener.onApplicationLoaded(ruleApplication);
			}
			//			log(response.getStatusLine().toString());
		} catch (Exception e) {
			//			log(e.toString());
		}
	}

	@Override
	public RuleApplication getRuleApplication() {
		return ruleApplication;
	}

	@Override
	public void loadApplications() {
		allApplications.clear();
		try (CloseableHttpResponse response = execute("applications")) {
			final InputStream content = response.getEntity().getContent();
			final List<RuleApplication> ruleApplications = objectMapper.readValue(content, new TypeReference<List<RuleApplication>>() {
			});
			ruleListener.clearApplications();
			for (int i = 0; i < ruleApplications.size(); i++) {
				RuleApplication ruleApplication = ruleApplications.get(i);
				for (int j = i+1; j < ruleApplications.size(); j++) {
					RuleApplication application = ruleApplications.get(j);
					if (ruleApplication.getPyLabel().equals(application.getPyLabel())) {
						ruleApplications.remove(j);
						j--;
					}
				}
			}
			for (RuleApplication ruleApplication : ruleApplications) {
				allApplications.add(ruleApplication);
				ruleListener.onApplicationLoaded(ruleApplication);
			}
			//			log(response.getStatusLine().toString());
		} catch (Exception e) {
			e.printStackTrace();
			//			log(e.toString());
		}
	}

	public void setRuleApplication(Project project, RuleApplication ruleApplication) {
		this.ruleApplication = ruleApplication;
		final RuleListener ruleListener = project.getMessageBus().syncPublisher(RULE_LISTENER_TOPIC);
		ruleListener.onApplicationChanged(ruleApplication);
	}
}
