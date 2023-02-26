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
package net.pega.intellij.modeler.process;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.pega.intellij.modeler.Context;
import net.pega.intellij.modeler.Loader;
import net.pega.model.Phase;
import net.pega.model.RuleObjCaseType;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

class CaseTypeLoader implements Loader<RuleObjCaseType> {
	private final Map<String, RuleObjCaseType> classMap = new HashMap<>();
	Context context;

	public CaseTypeLoader(Context context) {
		this.context = context;
	}

	@Override
	public void analyse(RuleObjCaseType mClass) {
		if (Arrays.stream(context.getConfiguration().dataModelState.highestClasses).noneMatch(ac -> Objects.equals(ac, mClass.getPyClassName()))) {
			final RuleObjCaseType mClass1 = getCaseType(mClass.getPyClassName());
			if (mClass1 != null) {
				loadDetails(mClass1);
				//				analysePhases(mClass1);
			}
		}
	}

	public Collection<RuleObjCaseType> getMetadatas() {
		return new ArrayList<>(classMap.values());
	}

	private void analysePhases(RuleObjCaseType mClass) {
		final HttpGet get = context.createRequest("/phases/" + mClass.getPyClassName());
		context.log("analyseproperties: " + mClass.getPyClassName());
		final InputStream content;
		try (CloseableHttpResponse response = context.execute(get)) {
			context.log(response.getStatusLine().toString());
			context.log(System.getProperty("line.separator"));
			content = response.getEntity().getContent();
			final InputStreamReader reader = new InputStreamReader(content);
			JsonArray pxResults = JsonParser.parseReader(reader).getAsJsonArray();
			for (int i = 0; i < pxResults.size(); i++) {
				final JsonElement jsonElement = pxResults.get(i);
				context.log("    - " + jsonElement);
				registerPhase((JsonObject) jsonElement);
			}
		} catch (Exception e) {
			context.log(e.toString());
		}
	}

	private RuleObjCaseType getCaseType(String pyPageClass) {
		if (Arrays.stream(context.getConfiguration().dataModelState.highestClasses).noneMatch(ac -> Objects.equals(ac,
																												   pyPageClass)) && classMap.size() < context.getConfiguration().dataModelState.maxClass) {
			if (!classMap.containsKey(pyPageClass)) {
				final RuleObjCaseType value = new RuleObjCaseType();
				value.setPyLabel(pyPageClass);
				classMap.put(pyPageClass, value);
			}
			return classMap.get(pyPageClass);
		}
		return null;
	}

	private void loadDetails(RuleObjCaseType mClass) {
		final HttpGet get = context.createRequest("/casetype/" + mClass.getPyClassName());
		context.log("analyseCaseType: " + mClass.getPyClassName());
		//		System.out.println(context.getState());
		final InputStream content;
		try (CloseableHttpResponse response = context.execute(get)) {
			context.log(response.getStatusLine().toString());
			context.log(System.getProperty("line.separator"));
			content = response.getEntity().getContent();
			final InputStreamReader reader = new InputStreamReader(content);
			JsonArray pxResults = JsonParser.parseReader(reader).getAsJsonArray();
			for (int i = 0; i < pxResults.size(); i++) {
				final JsonElement jsonElement = pxResults.get(i);
				context.log("  - " + jsonElement);
			}
		} catch (Exception e) {
			context.log(e.toString());
			throw new RuntimeException(e);
		}
	}

	private void registerPhase(JsonObject jsonElement) {
		final Phase pyProperty = new Phase();
		/*
		the name of the property
		 */
		final String pyPropertyName = jsonElement.get("pyPropertyName").getAsString();
		/*
		return Page or String
		 */
		String pyPropertyMode = jsonElement.get("pyPropertyMode").getAsString();
		/*
		containing class
		 */
		String pyClassName = jsonElement.get("pyClassName").getAsString();
		String pyStringType = jsonElement.get("pyStringType").getAsString();
		boolean pyIsReference = jsonElement.get("pyIsReference").getAsBoolean();
		/**
		 * return "" or the class of the field
		 */
		String pyPageClass = jsonElement.get("pyPageClass").getAsString();
	}
}
