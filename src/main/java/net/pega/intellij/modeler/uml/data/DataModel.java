package net.pega.intellij.modeler.uml.data;

import net.pega.intellij.modeler.uml.PegaClient;

import java.io.PrintStream;

public class DataModel extends PegaClient {
	static String snakeToCamel(String str) {
		return str.replaceAll("-", "_");
	}

	public void analyse(PrintStream out) {
		UmlGenerator generator = new UmlGenerator(this);
		MetadataLoader loader = new MetadataLoader(this);
		final MClass mClass = new MClass(state.dataModelState.baseClassName);
		loader.analyseClass(mClass);
		out.println("@startuml");
		generator.generateHeader(out, state);
		generator.generateDataModel(out, loader.getClasses());
		out.println("@enduml");
	}

	@Override
	public String getAnalysis() {
		return "DataModel";
	}
}
