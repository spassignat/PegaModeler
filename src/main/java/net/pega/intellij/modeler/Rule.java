/*
 * Copyright (c) 2023 Stephane Passignat - Exygen
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
package net.pega.intellij.modeler;

public abstract class Rule {
	private String pxObjClass;
	private boolean pyBaseRule;
	private String pyCategory;
	private String pyClassName;
	private String pyLabel;
	private String pyRuleName;
	private String pyRuleSet;
	private String pyRuleSetVersion;
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

	public String getPyRuleName() {
		return pyRuleName;
	}

	public void setPyRuleName(String pyRuleName) {
		this.pyRuleName = pyRuleName;
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
