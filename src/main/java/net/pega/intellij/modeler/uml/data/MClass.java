package net.pega.intellij.modeler.uml.data;

import java.util.HashMap;
import java.util.Map;

class MClass {
	final MClassType classType;
	final String name;
	public boolean abs;
	public boolean inheritance;
	public String parent;
	Map<String, MProperty> properties = new HashMap<>();
	boolean analyzed = false;

	public MClass(String pyPageClass) {
		name = pyPageClass;
		classType = name.indexOf("-Work-") > 0 ? MClassType.CASE : MClassType.PAGE;
	}
}

enum MClassType {
	CASE,
	PAGE,
	ENTITY
}
