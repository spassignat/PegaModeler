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
package net.pega.intellij.generator.process;

import com.intellij.openapi.project.Project;
import net.pega.intellij.BaseModule;
import net.pega.intellij.generator.Loader;
import net.pega.model.RuleObjCaseType;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class CaseTypeLoader extends BaseModule implements Loader<RuleObjCaseType> {
	private final Map<String, RuleObjCaseType> classMap = new HashMap<>();

	public CaseTypeLoader(@NotNull Project project) {
		super(project);
	}

	@Override
	public Collection<RuleObjCaseType> load(RuleObjCaseType mClass) {
		if (!classMap.containsKey(mClass.getPyClassName())) {
			classMap.put(mClass.getPyClassName(), mClass);
		}
		if (Arrays.stream(getSettings().getState().dataModelState.highestClasses).noneMatch(ac -> Objects.equals(ac, mClass.getPyClassName()))) {
			final RuleObjCaseType mClass1 = getCaseType(mClass.getPyClassName());
			if (mClass1 != null) {
				loadDetails(mClass1);
			}
		}
		return new ArrayList<>(classMap.values());
	}

	private RuleObjCaseType getCaseType(String pyPageClass) {
		if (Arrays.stream(getSettings().getState().dataModelState.highestClasses).noneMatch(ac -> Objects.equals(ac,
																												 pyPageClass)) && classMap.size() < getSettings().getState().dataModelState.maxClass) {
			if (!classMap.containsKey(pyPageClass)) {
				final RuleObjCaseType value = new RuleObjCaseType();
				value.setPyClassName(pyPageClass);
				classMap.put(pyPageClass, value);
			}
			return classMap.get(pyPageClass);
		}
		return null;
	}

	private void loadDetails(RuleObjCaseType mClass) {
		log("analyseCaseType: " + mClass.getPyClassName());
		final InputStream content;
		try (CloseableHttpResponse response = execute("phases", mClass.getPyRuleSet(), mClass.getPyRuleSetVersion(), mClass.getPyClassName())) {
			log(response.getStatusLine().toString());
			log(System.getProperty("line.separator"));
			content = response.getEntity().getContent();
			final InputStreamReader reader = new InputStreamReader(content);
			final RuleObjCaseType ruleObjCaseType = objectMapper.readValue(reader, RuleObjCaseType.class);
			classMap.put(mClass.getPyClassName(), ruleObjCaseType);
			super.ruleListener.onCaseTypeLoaded(ruleObjCaseType);
		} catch (Exception e) {
			log(e.toString());
			throw new RuntimeException(e);
		}
	}
}
