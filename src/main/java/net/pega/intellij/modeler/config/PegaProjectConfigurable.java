// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package net.pega.intellij.modeler.config;

import com.intellij.openapi.options.ConfigurableBase;
import com.intellij.openapi.project.Project;
import net.pega.intellij.modeler.PegaBundle;
import net.pega.intellij.modeler.config.ui.PegaProjectSettingsForm;
import org.jetbrains.annotations.NotNull;

public class PegaProjectConfigurable extends ConfigurableBase<PegaProjectSettingsForm, PegaProjectSettings> {
	public static final String ID = "net.pega.intellij.modeler.config.PegaProjectConfigurable";
	private final Project project;

	public PegaProjectConfigurable(Project project) {
		super(ID, PegaBundle.message("pega.plugin.display.name"), /* helpTopic= */ null);
		this.project = project;
	}

	@Override
	protected PegaProjectSettingsForm createUi() {
		return new PegaProjectSettingsForm(project);
	}

	@Override
	protected @NotNull PegaProjectSettings getSettings() {
		return PegaProjectSettings.getInstance(project);
	}
}
