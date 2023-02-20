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
