/*
 * Copyright (c) 2014-2023 Stephane Passignat - Exygen
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

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;
import net.pega.intellij.modeler.view.MessageCallback;
import net.pega.intellij.modeler.view.MyRunnable;
import org.jetbrains.annotations.NotNull;

/**
 * Provides functionality to throw a runtime exception when the action is invoked. It is used to test the error reporting
 * functions. Don't forget to register the action in plugin.xml to make it work.
 */
public class DataModelAction extends AnAction {
	public void actionPerformed(@NotNull AnActionEvent e) {
		final Project project = e.getProject();
		ToolWindowManager toolWindowManager = ToolWindowManager.getInstance(project);
		ToolWindow toolWindow = toolWindowManager.getToolWindow("Pega");
		if (toolWindow != null) {
			final MessageCallback component = (MessageCallback) toolWindow.getComponent().getComponent(0);
			toolWindow.show();
			new MyRunnable(component, "DataModel", project, "data-model.puml").run();
		}
	}
}
