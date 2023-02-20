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
