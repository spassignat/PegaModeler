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
		if (highestClasses != null ) {
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
