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
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package net.pega.intellij.modeler.uml.data;

import org.jetbrains.annotations.NonNls;

import java.util.Arrays;
import java.util.Objects;

public class DataModelState {
	@NonNls
	public boolean abstractOnTop;
	@NonNls
	public String baseClassName;
	@NonNls
	public String caseTypeColor;
	@NonNls
	public String entityColor;
	public String[] highestClasses;
	public int maxClass;
	@NonNls
	public String pageColor;

	public DataModelState() {
	}

	public DataModelState(boolean abstractOnTop, String baseClassName, String caseTypeColor, String entityColor, String[] highestClasses, int maxClass, String pageColor) {
		this.abstractOnTop = abstractOnTop;
		this.baseClassName = baseClassName;
		this.caseTypeColor = caseTypeColor;
		this.entityColor = entityColor;
		this.highestClasses = highestClasses;
		this.maxClass = maxClass;
		this.pageColor = pageColor;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		DataModelState that = (DataModelState) o;
		final boolean b = abstractOnTop == that.abstractOnTop;
		final boolean b1 = maxClass == that.maxClass;
		final boolean equals = Objects.equals(baseClassName, that.baseClassName);
		final boolean equals1 = Objects.equals(caseTypeColor, that.caseTypeColor);
		final boolean equals2 = Objects.equals(entityColor, that.entityColor);
		if (highestClasses != null) {
			Arrays.sort(highestClasses);
		}
		if (that.highestClasses != null) {
			Arrays.sort(that.highestClasses);
		}
		final boolean equals3 = Arrays.equals(highestClasses, that.highestClasses);
		final boolean equals4 = Objects.equals(pageColor, that.pageColor);
		return b && b1 && equals && equals1 && equals2 && equals3 && equals4;
	}

	@Override
	public int hashCode() {
		int result = Objects.hash(abstractOnTop, baseClassName, caseTypeColor, entityColor, maxClass, pageColor);
		result = 31 * result + Arrays.hashCode(highestClasses);
		return result;
	}

	public void loadState(DataModelState state) {
		this.baseClassName = state.baseClassName;
		this.highestClasses = state.highestClasses;
		this.abstractOnTop = state.abstractOnTop;
		this.caseTypeColor = state.caseTypeColor;
		this.maxClass = state.maxClass;
		this.entityColor = state.entityColor;
		this.pageColor = state.pageColor;
	}

	@Override
	public String toString() {
		return "DataModelState{" + "abstractOnTop=" + abstractOnTop + ", baseClassName='" + baseClassName + '\'' + ", caseTypeColor='" + caseTypeColor + '\'' + ", entityColor='" + entityColor + '\'' + ", highestClasses=" + Arrays.toString(
				highestClasses) + ", maxClass=" + maxClass + ", pageColor='" + pageColor + '\'' + '}';
	}
}
