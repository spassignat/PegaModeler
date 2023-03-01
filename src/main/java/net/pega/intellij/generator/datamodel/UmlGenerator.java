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
package net.pega.intellij.generator.datamodel;

import net.pega.intellij.BaseModule;
import net.pega.intellij.generator.Generator;
import net.pega.intellij.modeler.PegaPlugin;
import net.pega.intellij.modeler.config.PegaConfigState;
import net.pega.intellij.modeler.Context;
import net.pega.model.RuleObjClass;
import net.pega.model.RuleObjProperty;

import java.io.PrintStream;
import java.util.Collection;

class UmlGenerator implements Generator<RuleObjClass> {
	BaseModule context;

	public UmlGenerator(BaseModule context) {
		this.context = context;
	}

	public void generateHeader(PrintStream out, PegaConfigState state) {
		out.println("skinparam class {");
		out.println("BackgroundColor<<Work>> " + state.dataModelState.caseTypeColor);
		out.println("BackgroundColor<<Entity>> " + state.dataModelState.entityColor);
		out.println("BackgroundColor<<Page>> " + state.dataModelState.pageColor);
		out.println("}");
	}

	public void generateDiagram(PrintStream out, Collection<RuleObjClass> dataModel) {
		for (RuleObjClass value : dataModel) {
			out.println((value.isAbs() ? "abstract " : "") + "class " + PegaPlugin.snakeToCamel(value.getPyClassName()) + getTemplate(value) + " {");
			for (RuleObjProperty ruleObjProperty : value.getProperties().values()) {
				if (!ruleObjProperty.isPage()) {
					out.println(PegaPlugin.snakeToCamel(ruleObjProperty.getPyPropertyName()) + ":" + PegaPlugin.snakeToCamel(ruleObjProperty.getPyPropertyMode()));
				}
			}
			out.println("}");
		}
		for (RuleObjClass value : dataModel) {
			if (value.getParent() != null) {
				final String s1 = PegaPlugin.snakeToCamel(value.getParent());
				final String s2 = PegaPlugin.snakeToCamel(value.getPyClassName());
				if (context.getSettings().getState().dataModelState.abstractOnTop) {
					out.println(s1 + " <|-- " + s2);
				} else {
					out.println(s2 + " --|> " + s1);
				}
			}
			for (RuleObjProperty ruleObjProperty : value.getProperties().values()) {
				if (ruleObjProperty.isPage()) {
					out.println(PegaPlugin.snakeToCamel(value.getPyClassName()) + (ruleObjProperty.isReference() ? " -" : " *") + "->" + (ruleObjProperty.isList() ? " \"*\" " : " \"1\" ") + PegaPlugin.snakeToCamel(
							ruleObjProperty.getPyPropertyMode()) + " : " + PegaPlugin.snakeToCamel(ruleObjProperty.getPyPropertyName()));
				}
			}
		}
	}

	private String getTemplate(RuleObjClass aclass) {
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
