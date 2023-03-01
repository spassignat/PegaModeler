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
package net.pega.intellij.generator.datamodel;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.pega.intellij.BaseModule;
import net.pega.intellij.generator.Loader;
import net.pega.model.RuleObjClass;
import net.pega.model.RuleObjProperty;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

class DataLoader implements Loader<RuleObjClass> {
	private final Map<String, RuleObjClass> classMap = new HashMap<>();
	BaseModule context;
	private DataModelState dataModelState;

	public DataLoader(BaseModule context) {
		this.context = context;
		dataModelState = context.getSettings().getState().dataModelState;
	}

	public Collection<RuleObjClass> load(RuleObjClass ruleObjClass) throws IOException {
		if (Arrays.stream(dataModelState.highestClasses).noneMatch(ac -> Objects.equals(ac, ruleObjClass.getPyClassName()))) {
			final RuleObjClass ruleObjClass1 = getMClass(ruleObjClass.getPyClassName());
			if (ruleObjClass1 != null) {
				analyseInheritance(ruleObjClass1);
				analyseProperties(ruleObjClass1);
				ruleObjClass1.setAnalyzed(true);
				boolean pendingClasses = true;
				int mxc = 100;
				while (pendingClasses && mxc-- > 0) {
					final List<RuleObjClass> values = new ArrayList<>(classMap.values());
					for (int i = 0; i < values.size(); i++) {
						RuleObjClass objClass = values.get(i);
						if (!objClass.isAnalyzed()) {
							load(objClass);
						}
					}
					pendingClasses = classMap.values().stream().anyMatch(c -> !c.isAnalyzed());
				}
			}
		}
		return new ArrayList<>(classMap.values());
	}

	private void analyseInheritance(RuleObjClass ruleObjClass) {
		context.log("analyseClass: " + ruleObjClass.getPyClassName());
		//		System.out.println(context.getState());
		final InputStream content;
		try (CloseableHttpResponse response = context.execute("class", ruleObjClass.getPyRuleName(), ruleObjClass.getPyRuleSetVersion(), ruleObjClass.getPyClassName())) {
			context.log(response.getStatusLine().toString());
			context.log(System.getProperty("line.separator"));
			content = response.getEntity().getContent();
			final InputStreamReader reader = new InputStreamReader(content);
			JsonArray pxResults = JsonParser.parseReader(reader).getAsJsonArray();
			for (int i = 0; i < pxResults.size(); i++) {
				final JsonElement jsonElement = pxResults.get(i);
				final JsonObject jsonElement1 = (JsonObject) jsonElement;
				final String pyClassType = jsonElement1.get("pyClassType").getAsString();
				final String pyClassName = jsonElement1.get("pyDerivesFrom").getAsString();
				final boolean pyClassInheritance = jsonElement1.get("pyClassInheritance").getAsBoolean();
				ruleObjClass.setAbs("Abstract".equals(pyClassType));
				ruleObjClass.setInheritance(pyClassInheritance);
				RuleObjClass declaringClass = getMClass(pyClassName);
				if (declaringClass != null) {
					ruleObjClass.setParent(declaringClass.getPyClassName());
				}
				context.log("  - " + jsonElement);
			}
		} catch (Exception e) {
			context.log(e.toString());
			throw new RuntimeException(e);
		}
	}

	private void analyseProperties(RuleObjClass ruleObjClass) throws IOException {
		context.log("analyseproperties: " + ruleObjClass.getPyClassName());
		final InputStream content;
		try (CloseableHttpResponse response = context.execute("properties", ruleObjClass.getPyClassName())) {
			context.log(response.getStatusLine().toString());
			context.log(System.getProperty("line.separator"));
			content = response.getEntity().getContent();
			final InputStreamReader reader = new InputStreamReader(content);
			JsonArray pxResults = JsonParser.parseReader(reader).getAsJsonArray();
			for (int i = 0; i < pxResults.size(); i++) {
				final JsonElement jsonElement = pxResults.get(i);
				context.log("    - " + jsonElement);
				registerProperty((JsonObject) jsonElement);
			}
		} catch (Exception e) {
			context.log(e.toString());
		}
	}

	private RuleObjClass getMClass(String pyPageClass) {
		if (Arrays.stream(context.getSettings().getState().dataModelState.highestClasses).noneMatch(ac -> Objects.equals(ac,
																														 pyPageClass)) && classMap.size() < context.getSettings().getState().dataModelState.maxClass) {
			if (!classMap.containsKey(pyPageClass)) {
				classMap.put(pyPageClass, new RuleObjClass(pyPageClass));
			}
			return classMap.get(pyPageClass);
		}
		return null;
	}

	private void registerProperty(JsonObject jsonElement) {
		final RuleObjProperty pyRuleObjProperty = new RuleObjProperty();
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
		RuleObjClass declaringClass = getMClass(pyClassName);
		if (declaringClass != null) {
			pyRuleObjProperty.setPage(pyPropertyMode.startsWith("Page"));
			pyRuleObjProperty.setReference(pyIsReference);
			pyRuleObjProperty.setList(pyPropertyMode.equals("PageList"));
			pyRuleObjProperty.setPyPropertyName(pyPropertyName);
			if (pyRuleObjProperty.isPage()) {
				RuleObjClass fieldType = getMClass(pyPageClass);
				pyRuleObjProperty.setPyPropertyMode(fieldType.getPyClassName());
			} else {
				pyRuleObjProperty.setPyPropertyMode(pyPropertyMode);
			}
			declaringClass.getProperties().put(pyPropertyName, pyRuleObjProperty);
		}
	}
}
