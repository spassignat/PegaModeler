package net.pega.intellij.modeler.uml.data;

import net.pega.intellij.modeler.config.PegaConfigState;
import net.pega.intellij.modeler.uml.Context;

import java.io.PrintStream;
import java.util.Collection;

class UmlGenerator {
	Context context = null;

	public UmlGenerator(Context context) {
		this.context = context;
	}

	void generateDataModel(PrintStream out, Collection<MClass> dataModel) {
		for (MClass value : dataModel) {
			out.println((value.abs?"abstract ":"")+"class " + DataModel.snakeToCamel(value.name) + getTemplate(value) + " {");
			for (MProperty mProperty : value.properties.values()) {
				if (!mProperty.page) {
					out.println(DataModel.snakeToCamel(mProperty.name) + ":" + DataModel.snakeToCamel(mProperty.type));
				}
			}
			out.println("}");
		}
		for (MClass value : dataModel) {
			if (value.parent != null) {
				final String s1 = DataModel.snakeToCamel(value.parent);
				final String s2 = DataModel.snakeToCamel(value.name);
				if (context.getState().dataModelState.abstractOnTop) {
					out.println(s1 + " <|-- " + s2);
				} else {
					out.println(s2 + " --|> " + s1);
				}
			}
			for (MProperty mProperty : value.properties.values()) {
				if (mProperty.page) {
					out.println(DataModel.snakeToCamel(value.name) + (mProperty.reference ? " -" : " *") + "->" + (mProperty.list ? " \"*\" " : " \"1\" ") + DataModel.snakeToCamel(mProperty.type) + " : " + DataModel.snakeToCamel(
							mProperty.name));
				}
			}
		}
	}

	void generateHeader(PrintStream out, PegaConfigState state) {
		out.println("skinparam class {");
		out.println("BackgroundColor<<Work>> " + state.dataModelState.caseTypeColor);
		out.println("BackgroundColor<<Entity>> " + state.dataModelState.entityColor);
		out.println("BackgroundColor<<Page>> " + state.dataModelState.pageColor);
		out.println("}");
	}

	private String getTemplate(MClass aclass) {
		switch (aclass.classType) {
			case CASE:
				return "<<Work>>";
			case ENTITY:
				return "<<Entity>>";
			case PAGE:
				return "<<Page>>";
		}
		return "";
	}
}
