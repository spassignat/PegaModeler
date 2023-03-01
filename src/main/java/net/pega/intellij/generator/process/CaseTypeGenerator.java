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
package net.pega.intellij.generator.process;

import net.pega.intellij.BaseModule;
import net.pega.intellij.generator.Generator;
import net.pega.intellij.modeler.config.PegaConfigState;
import net.pega.model.EmbedStage;
import net.pega.model.RuleObjCaseType;
import net.pega.model.RuleObjFlow;

import java.io.PrintStream;
import java.util.Collection;

class CaseTypeGenerator implements Generator<RuleObjCaseType> {
	BaseModule context;

	public CaseTypeGenerator(BaseModule context) {
		this.context = context;
	}

	public void generateDiagram(PrintStream out, Collection<RuleObjCaseType> dataModel) {
		for (RuleObjCaseType ruleObjCaseType : dataModel) {
			if (ruleObjCaseType.getPyStages() != null) {
				for (EmbedStage pyStage : ruleObjCaseType.getPyStages()) {
					if (pyStage.isPyIsInitializationStage()) {
						out.printf("|#lightgreen|%s|\n", pyStage.getPyStageName());
						out.printf("start\n");
					} else if (pyStage.isPyIsTerminalStage()) {
						out.printf("|#pink|%s|\n", pyStage.getPyStageName());
					} else{
						out.printf("|#white|%s|\n", pyStage.getPyStageName());
					}
					for (RuleObjFlow pyProcess : pyStage.getPyProcesses()) {
						out.printf(":%s;\n", pyProcess.getPyLabel());
					}
				}
			}
		}
	}

	public void generateHeader(PrintStream out, PegaConfigState state) {
		out.println("skinparam class {");
		out.println("BackgroundColor<<Work>> " + state.dataModelState.caseTypeColor);
		out.println("}");
	}
}
