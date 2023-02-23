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
package net.pega.intellij.modeler.view;

import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import net.pega.intellij.modeler.config.PegaConfigState;
import net.pega.intellij.modeler.config.PegaProjectSettings;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PegaWindow {
	public JTextArea area;
	private JButton clearButton;
	private JButton connectButton;
	private JButton generateDataModelButton;
	private JPanel myToolWindowContent;
	private JLabel urlLabel;

	public PegaWindow(ToolWindow toolWindow, @NotNull Project project) {
		final PegaProjectSettings instance = PegaProjectSettings.getInstance(project);
		final PegaConfigState state = instance.getState();
		final Application application = ApplicationManager.getApplication();
		generateDataModelButton.addActionListener(evt -> {
			final MyRunnable dataModel = new MyRunnable((MessageCallback) myToolWindowContent, "DataModel", project, "data-model.puml");
			application.invokeAndWait(dataModel);
		});
		connectButton.addActionListener(evt -> {
			final MyRunnable connect = new MyRunnable((MessageCallback) myToolWindowContent, "Connect", project, "connect.txt");
			application.invokeAndWait(connect);
		});
		clearButton.addActionListener(e -> ((MyJPanel) myToolWindowContent).clear());
		instance.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				updateView(state);
			}
		});
		updateView(state);
	}

	public JPanel getContent() {
		return myToolWindowContent;
	}

	private void createUIComponents() {
		myToolWindowContent = new MyJPanel() {
			@Override
			public void clear() {
				area.setText("");
			}

			@Override
			public void log(String message) {
				area.append(message);
			}
		};
		// TODO: place custom component creation code here
	}

	private void updateView(PegaConfigState state) {
		urlLabel.setText(state.connectState.url);
	}
}
