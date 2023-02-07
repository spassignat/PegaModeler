// Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package net.pega.intellij.modeler.view;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import net.pega.intellij.modeler.config.PegaProjectSettings;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PegaWindow {
	private JPanel myToolWindowContent;
	private JButton connectButton;
	private JButton generateDataModelButton;
	private JLabel urlLabel;

	public PegaWindow(ToolWindow toolWindow, @NotNull Project project) {
		final PegaProjectSettings instance = PegaProjectSettings.getInstance(project);
		final PegaProjectSettings.PegaConfigState state = instance.getState();
		state.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent e) {
				urlLabel.setText(state.url);
			}
		});
		//		hideToolWindowButton.addActionListener(e -> toolWindow.hide(null));
//		myToolWindowContent=new JPanel(new FlowLayout());
//		myToolWindowContent.add(connectButton=new JButton("Connect"));
//		myToolWindowContent.add(generateDataModelButton=new JButton("Generate"));
		}

	public JPanel getContent() {
		return myToolWindowContent;
	}
}
