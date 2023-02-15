package net.pega.intellij.modeler.uml.data;

class MProperty {
	public boolean list;
	public boolean reference;
	String name;
	String type;
	boolean multiple;
	boolean page;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isMultiple() {
		return multiple;
	}

	public void setMultiple(boolean multiple) {
		this.multiple = multiple;
	}
}
