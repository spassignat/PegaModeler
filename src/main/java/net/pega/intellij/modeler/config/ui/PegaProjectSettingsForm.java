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
package net.pega.intellij.modeler.config.ui;

import com.intellij.openapi.options.ConfigurableUi;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.Project;
import net.pega.intellij.modeler.PegaBundle;
import net.pega.intellij.modeler.config.PegaConfigState;
import net.pega.intellij.modeler.config.PegaProjectSettings;
import net.pega.intellij.modeler.connect.ConnectForm;
import net.pega.intellij.modeler.data.DataModelForm;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class PegaProjectSettingsForm implements ConfigurableUi<PegaProjectSettings> {
	private JPanel configPane;
	private ConnectForm connectForm;
	private DataModelForm dataModelForm;
	private JTextField gitHubUserField;
	private JTextField githubToken;
	private JTextPane helpAbout;

	public PegaProjectSettingsForm(Project project) {
		helpAbout.setText(PegaBundle.getHelp("about"));
	}

	@Override
	public void apply(@NotNull PegaProjectSettings settings) throws ConfigurationException {
		try {
			final PegaConfigState state = settings.getState();
			connectForm.apply(settings.getState().connectState);
			dataModelForm.apply(settings);
			state.githubUser = gitHubUserField.getText();
			state.githubToken = githubToken.getText();
			settings.fireChangeEvent();
		} catch (Exception e) {
			throw new ConfigurationException(PegaBundle.message("apply.fail", e.getMessage()));
		}
	}

	public PegaConfigState currentState() {
		return new PegaConfigState(gitHubUserField.getText(), githubToken.getText());
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
