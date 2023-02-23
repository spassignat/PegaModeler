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
package net.pega.intellij.modeler.uml.data;

import net.pega.intellij.modeler.config.PegaConfigState;
import net.pega.intellij.modeler.uml.Context;
import net.pega.intellij.modeler.uml.data.model.MClass;
import net.pega.intellij.modeler.uml.data.model.MProperty;

import java.io.PrintStream;
import java.util.Collection;

class UmlGenerator {
	Context context;

	public UmlGenerator(Context context) {
		this.context = context;
	}

	void generateDataModel(PrintStream out, Collection<MClass> dataModel) {
		for (MClass value : dataModel) {
			out.println((value.isAbs() ? "abstract " : "") + "class " + DataModel.snakeToCamel(value.getName()) + getTemplate(value) + " {");
			for (MProperty mProperty : value.getProperties().values()) {
				if (!mProperty.isPage()) {
					out.println(DataModel.snakeToCamel(mProperty.getName()) + ":" + DataModel.snakeToCamel(mProperty.getType()));
				}
			}
			out.println("}");
		}
		for (MClass value : dataModel) {
			if (value.getParent() != null) {
				final String s1 = DataModel.snakeToCamel(value.getParent());
				final String s2 = DataModel.snakeToCamel(value.getName());
				if (context.getState().dataModelState.abstractOnTop) {
					out.println(s1 + " <|-- " + s2);
				} else {
					out.println(s2 + " --|> " + s1);
				}
			}
			for (MProperty mProperty : value.getProperties().values()) {
				if (mProperty.isPage()) {
					out.println(DataModel.snakeToCamel(value.getName()) + (mProperty.isReference() ? " -" : " *") + "->" + (mProperty.isList() ? " \"*\" " : " \"1\" ") + DataModel.snakeToCamel(mProperty.getType()) + " : " + DataModel.snakeToCamel(
							mProperty.getName()));
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
		switch (aclass.getClassType()) {
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
