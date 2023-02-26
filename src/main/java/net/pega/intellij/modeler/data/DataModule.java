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
package net.pega.intellij.modeler.data;

import com.intellij.openapi.project.Project;
import net.pega.intellij.modeler.Generator;
import net.pega.intellij.modeler.Loader;
import net.pega.intellij.modeler.PegaClient;
import net.pega.intellij.modeler.Rule;
import net.pega.model.RuleObjClass;

import java.io.PrintStream;

public class DataModule extends PegaClient {
	public DataModule(Project project) {
		super(project);
	}

	public void analyse(PrintStream out, Project project, Rule rule) {
		Generator generator = new UmlGenerator(this);
		Loader loader = new DataLoader(this);
		final RuleObjClass ruleObjClass = new RuleObjClass(configuration.dataModelState.baseClassName);
		loader.analyse(ruleObjClass);
		out.println("@startuml");
		generator.generateHeader(out, configuration);
		generator.generateDiagram(out, loader.getMetadatas());
		out.println("@enduml");
	}

	@Override
	public String getAnalysis() {
		return "DataModel";
	}
}
