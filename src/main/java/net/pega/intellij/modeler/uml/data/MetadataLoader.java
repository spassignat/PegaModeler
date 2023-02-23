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
package net.pega.intellij.modeler.uml.data;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.pega.intellij.modeler.uml.Context;
import net.pega.intellij.modeler.uml.data.model.MClass;
import net.pega.intellij.modeler.uml.data.model.MProperty;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

class MetadataLoader {
	private final Map<String, MClass> classMap = new HashMap<>();
	Context context;

	public MetadataLoader(Context context) {
		this.context = context;
	}

	void analyseClass(MClass mClass) {
		if (Arrays.stream(context.getState().dataModelState.highestClasses).noneMatch(ac -> Objects.equals(ac, mClass.getName()))) {
			final MClass mClass1 = getMClass(mClass.getName());
			if (mClass1 != null) {
				analyseInheritance(mClass1);
				analyseProperties(mClass1);
				mClass1.setAnalyzed(true);
				boolean pendingClasses = true;
				int mxc = 100;
				while (pendingClasses && mxc-- > 0) {
					getClasses().stream().filter(c -> !c.isAnalyzed()).forEach(this::analyseClass);
					pendingClasses = getClasses().stream().anyMatch(c -> !c.isAnalyzed());
				}
			}
		}
	}

	Collection<MClass> getClasses() {
		return new ArrayList<>(classMap.values());
	}

	private void analyseInheritance(MClass mClass) {
		final HttpGet get = context.createRequest("/class/" + mClass.getName());
		context.log("analyseClass: " + mClass.getName());
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
				final JsonObject jsonElement1 = (JsonObject) jsonElement;
				final String pyClassType = jsonElement1.get("pyClassType").getAsString();
				final String pyClassName = jsonElement1.get("pyDerivesFrom").getAsString();
				final boolean pyClassInheritance = jsonElement1.get("pyClassInheritance").getAsBoolean();
				mClass.setAbs("Abstract".equals(pyClassType));
				mClass.setInheritance(pyClassInheritance);
				MClass declaringClass = getMClass(pyClassName);
				if (declaringClass != null) {
					mClass.setParent(declaringClass.getName());
				}
				context.log("  - " + jsonElement);
			}
		} catch (Exception e) {
			context.log(e.toString());
			throw new RuntimeException(e);
		}
	}

	private void analyseProperties(MClass mClass) {
		final HttpGet get = context.createRequest("/properties/" + mClass.getName());
		context.log("analyseproperties: " + mClass.getName());
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
				registerProperty((JsonObject) jsonElement);
			}
		} catch (Exception e) {
			context.log(e.toString());
		}
	}

	private MClass getMClass(String pyPageClass) {
		if (Arrays.stream(context.getState().dataModelState.highestClasses).noneMatch(ac -> Objects.equals(ac, pyPageClass)) && classMap.size() < context.getState().dataModelState.maxClass) {
			if (!classMap.containsKey(pyPageClass)) {
				classMap.put(pyPageClass, new MClass(pyPageClass));
			}
			return classMap.get(pyPageClass);
		}
		return null;
	}

	private void registerProperty(JsonObject jsonElement) {
		final MProperty pyProperty = new MProperty();
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
		MClass declaringClass = getMClass(pyClassName);
		if (declaringClass != null) {
			pyProperty.setPage(pyPropertyMode.startsWith("Page"));
			pyProperty.setReference(pyIsReference);
			pyProperty.setList(pyPropertyMode.equals("PageList"));
			pyProperty.setName(pyPropertyName);
			if (pyProperty.isPage()) {
				MClass fieldType = getMClass(pyPageClass);
				pyProperty.setType(fieldType.getName());
			} else {
				pyProperty.setType(pyPropertyMode);
			}
			declaringClass.getProperties().put(pyPropertyName, pyProperty);
		}
	}
}
