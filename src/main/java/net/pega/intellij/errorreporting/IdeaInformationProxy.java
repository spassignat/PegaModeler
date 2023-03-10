/*
 * Copyright (c) 2017-2023 Stephane Passignat - Exygen
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
package net.pega.intellij.errorreporting;

import com.intellij.openapi.application.ApplicationNamesInfo;
import com.intellij.openapi.application.ex.ApplicationInfoEx;
import com.intellij.openapi.diagnostic.Attachment;
import com.intellij.openapi.util.SystemInfo;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.LinkedHashMap;

/**
 * Collects information about the running IDEA and the error
 */
class IdeaInformationProxy {
	static LinkedHashMap<String, String> getKeyValuePairs(GitHubErrorBean error, ApplicationInfoEx appInfo, ApplicationNamesInfo namesInfo) {
		LinkedHashMap<String, String> params = new LinkedHashMap<>(21);
		params.put("error.description", error.getDescription());
		params.put("Plugin Name", error.getPluginName());
		params.put("Plugin Version", error.getPluginVersion());
		params.put("OS Name", SystemInfo.OS_NAME);
		params.put("Java version", SystemInfo.JAVA_VERSION);
		params.put("Java vm vendor", SystemInfo.JAVA_VENDOR);
		params.put("App Name", namesInfo.getProductName());
		params.put("App Full Name", namesInfo.getFullProductName());
		params.put("App Version name", appInfo.getVersionName());
		params.put("Is EAP", Boolean.toString(appInfo.isEAP()));
		params.put("App Build", appInfo.getBuild().asString());
		params.put("App Version", appInfo.getFullVersion());
		params.put("Last Action", error.getLastAction());
		params.put("error.message", error.getMessage());
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		error.getThrowable().printStackTrace(pw);
		params.put("error.stacktrace", sw.toString());
		params.put("error.hash", error.getExceptionHash());
		for (Attachment attachment : error.getAttachments()) {
			params.put("attachment.name", attachment.getName());
			params.put("attachment.value", attachment.getEncodedBytes());
		}
		return params;
	}
}
