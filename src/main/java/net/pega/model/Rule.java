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
package net.pega.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intellij.openapi.project.Project;
import net.pega.intellij.modules.ApplicationModule;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Comparator;
import java.util.List;

public class Rule {
	List<Rule> pyRuleVersionsList;
	private String pxObjClass;
	private boolean pyBaseRule;
	private String pyCategory;
	private String pyClassName;
	private String pyLabel;
	private String pyRuleSet;
	private String pyRuleSetVersion;
	private String pyValue;
	private String pzInsKey;


	public String getPxObjClass() {
		return pxObjClass;
	}

	public void setPxObjClass(String pxObjClass) {
		this.pxObjClass = pxObjClass;
	}

	public String getPyCategory() {
		return pyCategory;
	}

	public void setPyCategory(String pyCategory) {
		this.pyCategory = pyCategory;
	}

	public String getPyClassName() {
		return pyClassName;
	}

	public void setPyClassName(String pyClassName) {
		this.pyClassName = pyClassName;
	}

	public String getPyLabel() {
		return pyLabel;
	}

	public void setPyLabel(String pyLabel) {
		this.pyLabel = pyLabel;
	}


	public String getPyRuleSet() {
		return pyRuleSet;
	}

	public void setPyRuleSet(String pyRuleSet) {
		this.pyRuleSet = pyRuleSet;
	}

	public String getPyRuleSetVersion() {
		return pyRuleSetVersion;
	}

	public void setPyRuleSetVersion(String pyRuleSetVersion) {
		this.pyRuleSetVersion = pyRuleSetVersion;
	}

	public List<Rule> getPyRuleVersionsList() {
		return pyRuleVersionsList;
	}

	public void setPyRuleVersionsList(List<Rule> pyRuleVersionsList) {
		this.pyRuleVersionsList = pyRuleVersionsList;
	}

	public String getPyValue() {
		return pyValue;
	}

	public void setPyValue(String pyValue) {
		this.pyValue = pyValue;
	}

	public String getPzInsKey() {
		return pzInsKey;
	}

	public void setPzInsKey(String pzInsKey) {
		this.pzInsKey = pzInsKey;
	}

	public boolean isPyBaseRule() {
		return pyBaseRule;
	}

	public void setPyBaseRule(boolean pyBaseRule) {
		this.pyBaseRule = pyBaseRule;
	}
}
