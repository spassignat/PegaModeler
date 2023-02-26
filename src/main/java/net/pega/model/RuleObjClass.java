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

import java.util.HashMap;
import java.util.Map;

public class RuleObjClass extends Rule {
	private final ClassType classType;
	private final Map<String, RuleObjProperty> properties = new HashMap<>();
	private boolean abs;
	private boolean inheritance;
	private String parent;
	private boolean analyzed = false;

	public RuleObjClass(String pyPageClass) {
		setPyClassName(pyPageClass);
		classType = getPyClassName().indexOf("-Work-") > 0 ? ClassType.CASE : ClassType.PAGE;
	}

	public ClassType getClassType() {
		return classType;
	}


	public String getParent() {
		return parent;
	}

	public Map<String, RuleObjProperty> getProperties() {
		return properties;
	}

	public boolean isAbs() {
		return abs;
	}

	public boolean isAnalyzed() {
		return analyzed;
	}

	public boolean isInheritance() {
		return inheritance;
	}

	public void setAbs(boolean abs) {
		this.abs = abs;
	}

	public void setAnalyzed(boolean analyzed) {
		this.analyzed = analyzed;
	}

	public void setInheritance(boolean inheritance) {
		this.inheritance = inheritance;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}
}
