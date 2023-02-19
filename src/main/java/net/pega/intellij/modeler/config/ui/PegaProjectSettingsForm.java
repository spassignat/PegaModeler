// Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package net.pega.intellij.modeler.config.ui;

import com.intellij.openapi.options.ConfigurableUi;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.Project;
import net.pega.intellij.modeler.PegaBundle;
import net.pega.intellij.modeler.config.PegaConfigState;
import net.pega.intellij.modeler.config.PegaProjectSettings;
import net.pega.intellij.modeler.uml.connect.ConnectForm;
import net.pega.intellij.modeler.uml.data.DataModelForm;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class PegaProjectSettingsForm implements ConfigurableUi<PegaProjectSettings> {
	private JPanel configPane;
	private ConnectForm connectForm;
	private DataModelForm dataModelForm;
	private JTextField gitHubUserField;
	private JTextArea githubToken;
	private JTextPane helpAbout;

	public PegaProjectSettingsForm(Project project) {
		helpAbout.setText(PegaBundle.getHelp("about"));
	}

	@Override
	public void apply(@NotNull PegaProjectSettings settings) throws ConfigurationException {
		final PegaConfigState state = settings.getState();
		connectForm.apply(settings.getState().connectState);
		dataModelForm.apply(settings);
		state.githubUser = gitHubUserField.getText();
		state.githubToken = githubToken.getText();
		settings.fireChangeEvent();
	}

	public PegaConfigState currentState() {
		final PegaConfigState conf = new PegaConfigState(gitHubUserField.getText(), githubToken.getText());
		return conf;
	}

	@Override
	public @NotNull JComponent getComponent() {
		return configPane;
	}

	@Override
	public boolean isModified(@NotNull PegaProjectSettings settings) {
		final PegaConfigState state = settings.getState();
		boolean b1 = connectForm.isModified(state.connectState);
		boolean b2 = dataModelForm.isModified(state.dataModelState);
		final PegaConfigState conf = new PegaConfigState(gitHubUserField.getText(), githubToken.getText());
		final boolean b3 = !state.equals(conf);
		return b3 || b1 || b2;
	}

	public void reset(@NotNull PegaProjectSettings settings) {
		final PegaConfigState state = settings.getState();
		dataModelForm.reset(state.dataModelState);
		connectForm.reset(state.connectState);
		gitHubUserField.setText(state.githubUser);
		githubToken.setText(state.githubToken);
	}
}
