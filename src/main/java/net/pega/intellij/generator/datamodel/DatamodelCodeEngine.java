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

import com.intellij.openapi.project.Project;
import net.pega.intellij.BaseModule;
import net.pega.intellij.generator.Generator;
import net.pega.intellij.generator.CodeEngine;
import net.pega.model.RuleObjClass;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Collection;

public class DatamodelCodeEngine extends BaseModule implements CodeEngine<RuleObjClass> {
	public DatamodelCodeEngine(@NotNull Project project) {
		super(project);
	}

	@Override
	public void analyse(PrintStream out, RuleObjClass rule) throws IOException {
		Generator<RuleObjClass> generator = new UmlGenerator(this);
		DataLoader loader = new DataLoader(this);
		final RuleObjClass ruleObjClass = new RuleObjClass(getSettings().getState().dataModelState.baseClassName);
		final Collection<RuleObjClass> load = loader.load(ruleObjClass);
		out.println("@startuml");
		generator.generateHeader(out, getSettings().getState());
		generator.generateDiagram(out, load);
		out.println("@enduml");
	}
}
