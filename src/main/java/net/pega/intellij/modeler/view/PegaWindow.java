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
package net.pega.intellij.modeler.view;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import net.pega.intellij.modeler.config.PegaConfigState;
import net.pega.intellij.modeler.config.PegaProjectSettings;
import net.pega.intellij.modeler.uml.PegaClient;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ServiceLoader;

public class PegaWindow {
	private JTextArea area;
	private JButton clearButton;
	private JButton connectButton;
	private JButton generateDataModelButton;
	private JPanel myToolWindowContent;
	private JLabel urlLabel;

	public PegaWindow(ToolWindow toolWindow, @NotNull Project project) {
		final PegaProjectSettings instance = PegaProjectSettings.getInstance(project);
		final PegaConfigState state = instance.getState();
		final ServiceLoader<PegaClient> load = ServiceLoader.load(PegaClient.class, PegaClient.class.getClassLoader());
		for (PegaClient pegaClient : load) {
			pegaClient.init(state);
			if ("DataModel".equals(pegaClient.getAnalysis())) {
				generateDataModelButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent evt) {
						ApplicationManager.getApplication().invokeAndWait(new MyRunnable(area, pegaClient, project, "datamodel.puml"));
					}
				});
			} else if ("Connect".equals(pegaClient.getAnalysis())) {
				connectButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent evt) {
						ApplicationManager.getApplication().invokeAndWait(new MyRunnable(area, pegaClient, project, "connect.puml"));
					}
				});
			}
			clearButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					area.setText("");
				}
			});
		}
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

	private void updateView(PegaConfigState state) {
		urlLabel.setText(state.connectState.url);
	}
}
