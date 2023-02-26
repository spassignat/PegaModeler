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
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.project.Project;
import net.pega.intellij.BaseModule;
import net.pega.intellij.modeler.CaseTypeService;
import net.pega.intellij.modeler.Rule;
import net.pega.model.RuleApplication;
import net.pega.model.RuleObjCaseType;
import net.pega.model.RuleSetVersion;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class CaseTypeModule extends BaseModule implements CaseTypeService {
	List<RuleObjCaseType> allRuleSets = new ArrayList<>();

	public CaseTypeModule(@NotNull Project project) {
		super(project);
	}

	public void analyse(PrintStream out, Project project, Rule rule) {
		try (CloseableHttpResponse response = execute("casetypes")) {
			final ObjectMapper objectMapper = new ObjectMapper();
			final InputStream content = response.getEntity().getContent();
			final List<RuleObjCaseType> ruleApplications = objectMapper.readValue(content, new TypeReference<List<RuleObjCaseType>>() {
			});
			for (RuleObjCaseType ruleApplication : ruleApplications) {
				ruleListener.onCaseTypeLoaded(ruleApplication);
			}
			log(response.getStatusLine().toString());
		} catch (Exception e) {
			log(e.toString());
		}
	}

	@Override
	public void loadCaseTypes(@NotNull ProgressIndicator indicator, RuleApplication ruleApp, RuleSetVersion ruleSetVersion) {
		try (CloseableHttpResponse response = execute("casetype", ruleSetVersion.getPyRuleSet(), ruleSetVersion.getPyRuleSetVersionID())) {
			final InputStream content = response.getEntity().getContent();
			final List<RuleObjCaseType> ruleObjCaseTypes = objectMapper.readValue(content, new TypeReference<List<RuleObjCaseType>>() {
			});
			ruleListener.clearCaseTypes();
			for (RuleObjCaseType rle : ruleObjCaseTypes) {
				indicator.setText("add " + rle.getPyLabel());
				allRuleSets.add(rle);
				ruleListener.onCaseTypeLoaded(rle);
			}
			//			log(response.getStatusLine().toString());
		} catch (Exception e) {
			e.printStackTrace();
			log(e.toString());
		}
		indicator.stop();
	}
}
