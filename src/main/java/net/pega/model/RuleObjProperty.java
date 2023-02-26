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

import net.pega.intellij.modeler.Rule;

public class RuleObjProperty extends Rule {
	private boolean list;
	private boolean multiple;
	private String pyPropertyName;
	private boolean page;
	private boolean reference;
	private String pyPropertyMode;

	public String getPyPropertyName() {
		return pyPropertyName;
	}

	public void setPyPropertyName(String pyPropertyName) {
		this.pyPropertyName = pyPropertyName;
	}

	public String getPyPropertyMode() {
		return pyPropertyMode;
	}

	public void setPyPropertyMode(String pyPropertyMode) {
		this.pyPropertyMode = pyPropertyMode;
	}

	public boolean isList() {
		return list;
	}

	public void setList(boolean list) {
		this.list = list;
	}

	public boolean isMultiple() {
		return multiple;
	}

	public void setMultiple(boolean multiple) {
		this.multiple = multiple;
	}

	public boolean isPage() {
		return page;
	}

	public void setPage(boolean page) {
		this.page = page;
	}

	public boolean isReference() {
		return reference;
	}

	public void setReference(boolean reference) {
		this.reference = reference;
	}
}
