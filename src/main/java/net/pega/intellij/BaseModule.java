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
package net.pega.intellij;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intellij.openapi.project.Project;
import net.pega.intellij.modeler.RuleListener;
import net.pega.intellij.modeler.config.PegaProjectSettings;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import static net.pega.intellij.modeler.PegaPlugin.RULE_LISTENER_TOPIC;

public class BaseModule {
	public final ObjectMapper objectMapper = new ObjectMapper();
	protected final Project project;
	protected final RuleListener ruleListener;
	private PegaProjectSettings settings;

	public BaseModule(@NotNull Project project) {
		this.project = project;
		ruleListener = project.getMessageBus().syncPublisher(RULE_LISTENER_TOPIC);
		settings = project.getService(PegaProjectSettings.class);
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	public CloseableHttpResponse execute(String... context) throws IOException {
		final HttpGet get = settings.createRequest(context);
		log("Execute " + get);
		final CloseableHttpClient httpClient = settings.createHttpClient();
		final CloseableHttpResponse execute = httpClient.execute(get);
		log(" response= " + execute);
		return execute;
	}

	public PegaProjectSettings getSettings() {
		return settings;
	}

	public void log(String message) {
		ruleListener.log(message);
	}

	void logEnter(String message) {
		ruleListener.logEnter(message);
	}

	void logExit(String message) {
		ruleListener.logEnter(message);
	}
}
