// Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package net.pega.intellij.modeler.config.ui;

import com.intellij.openapi.options.ConfigurableUi;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.Project;
import net.pega.intellij.modeler.config.PegaProjectSettings;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class PegaProjectSettingsForm implements ConfigurableUi<PegaProjectSettings> {
	private JPanel configPane;
	private JTextField baseClassField;
	private JPasswordField passwordField;
	private JTextField loginField;
	private JTextArea urlField;
	private JLabel loginLabel;
	private JLabel classLabel;
	private JLabel passwordLabel;
	private JLabel urlLabel;

	public PegaProjectSettingsForm(Project project) {

	/*	configPane = new JPanel(new GridLayout(2, 4));
		configPane.add(urlLabel = new JLabel("URL"));
		configPane.add(urlField = new JTextArea());
		configPane.add(loginLabel = new JLabel("Login"));
		configPane.add(loginField = new JTextField());
		configPane.add(passwordLabel = new JLabel("Password"));
		configPane.add(passwordField = new JPasswordField());
		configPane.add(classLabel = new JLabel("Class Name"));
		configPane.add(baseClassField = new JTextField());*/
	}


	@Override
	public void apply(@NotNull PegaProjectSettings settings) throws ConfigurationException {
		settings.state.baseClassName = baseClassField.getText();
		settings.state.login = loginField.getText();
		settings.state.pwd = passwordField.getText();
		settings.state.url = urlField.getText();
		settings.state.fireChangeEvent();
	}



	@Override
	public boolean isModified(@NotNull PegaProjectSettings settings) {
		final PegaProjectSettings.PegaConfigState
				conf =
				new PegaProjectSettings.PegaConfigState(baseClassField.getText(), urlField.getText(), loginField.getText(), new String(passwordField.getPassword()));
		return !settings.state.equals(conf);
	}

	@Override
	public void reset(@NotNull PegaProjectSettings settings) {
		passwordField.setText(settings.state.pwd);
		loginField.setText(settings.state.login);
		urlField.setText(settings.state.url);
		baseClassField.setText(settings.state.baseClassName);
	}

	@Override
	public @NotNull JComponent getComponent() {
		return configPane;
	}
}
