// Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
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
	private JButton sendErrorButton;

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
			});	sendErrorButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					throw new RuntimeException();
				}
			});
		}
		instance.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				updateView(state);
			}
		});
		//		hideToolWindowButton.addActionListener(e -> toolWindow.hide(null));
		//		myToolWindowContent=new JPanel(new FlowLayout());
		//		myToolWindowContent.add(connectButton=new JButton("Connect"));
		//		myToolWindowContent.add(generateDataModelButton=new JButton("Generate"));
		updateView(state);
	}

	public JPanel getContent() {
		return myToolWindowContent;
	}

	private void updateView(PegaConfigState state) {
		urlLabel.setText(state.connectState.url);
	}
}
