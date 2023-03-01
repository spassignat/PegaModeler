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
package net.pega.model;

public class EmbedMethodParam extends Rule {
	String pyParametersParamDesc;
	String pyParametersParamInOut;
	String pyParametersParamIntelliBaseClass;
	String pyParametersParamIntelliRule;
	String pyParametersParamIntelliValidateAs;
	String pyParametersParamName;
	String pyParametersParamReq;
	String pyParametersParamSize;
	String pyParametersParamType;

	public String getPyParametersParamDesc() {
		return pyParametersParamDesc;
	}

	public void setPyParametersParamDesc(String pyParametersParamDesc) {
		this.pyParametersParamDesc = pyParametersParamDesc;
	}

	public String getPyParametersParamInOut() {
		return pyParametersParamInOut;
	}

	public void setPyParametersParamInOut(String pyParametersParamInOut) {
		this.pyParametersParamInOut = pyParametersParamInOut;
	}

	public String getPyParametersParamIntelliBaseClass() {
		return pyParametersParamIntelliBaseClass;
	}

	public void setPyParametersParamIntelliBaseClass(String pyParametersParamIntelliBaseClass) {
		this.pyParametersParamIntelliBaseClass = pyParametersParamIntelliBaseClass;
	}

	public String getPyParametersParamIntelliRule() {
		return pyParametersParamIntelliRule;
	}

	public void setPyParametersParamIntelliRule(String pyParametersParamIntelliRule) {
		this.pyParametersParamIntelliRule = pyParametersParamIntelliRule;
	}

	public String getPyParametersParamIntelliValidateAs() {
		return pyParametersParamIntelliValidateAs;
	}

	public void setPyParametersParamIntelliValidateAs(String pyParametersParamIntelliValidateAs) {
		this.pyParametersParamIntelliValidateAs = pyParametersParamIntelliValidateAs;
	}

	public String getPyParametersParamName() {
		return pyParametersParamName;
	}

	public void setPyParametersParamName(String pyParametersParamName) {
		this.pyParametersParamName = pyParametersParamName;
	}

	public String getPyParametersParamReq() {
		return pyParametersParamReq;
	}

	public void setPyParametersParamReq(String pyParametersParamReq) {
		this.pyParametersParamReq = pyParametersParamReq;
	}

	public String getPyParametersParamSize() {
		return pyParametersParamSize;
	}

	public void setPyParametersParamSize(String pyParametersParamSize) {
		this.pyParametersParamSize = pyParametersParamSize;
	}

	public String getPyParametersParamType() {
		return pyParametersParamType;
	}

	public void setPyParametersParamType(String pyParametersParamType) {
		this.pyParametersParamType = pyParametersParamType;
	}
}
