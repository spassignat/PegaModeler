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
package net.pega.intellij.modeler.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.pom.Navigatable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * Action class to demonstrate how to interact with the IntelliJ Platform.
 * The only action this class performs is to provide the user with a popup dialog as feedback.
 * Typically this class is instantiated by the IntelliJ Platform framework based on declarations
 * in the plugin.xml file. But when added at runtime this class is instantiated by an action group.
 */
public class PopupDialogAction extends AnAction {
	/**
	 * This default constructor is used by the IntelliJ Platform framework to instantiate this class based on plugin.xml
	 * declarations. Only needed in {@link PopupDialogAction} class because a second constructor is overridden.
	 *
	 * @see AnAction#AnAction()
	 */
	public PopupDialogAction() {
		super();
	}

	/**
	 * This constructor is used to support dynamically added menu actions.
	 * It sets the text, description to be displayed for the menu item.
	 * Otherwise, the default AnAction constructor is used by the IntelliJ Platform.
	 *
	 * @param text        The text to be displayed as a menu item.
	 * @param description The description of the menu item.
	 * @param icon        The icon to be used with the menu item.
	 */
	public PopupDialogAction(@Nullable String text, @Nullable String description, @Nullable Icon icon) {
		super(text, description, icon);
	}

	/**
	 * Gives the user feedback when the dynamic action menu is chosen.
	 * Pops a simple message dialog. See the psi_demo plugin for an
	 * example of how to use {@link AnActionEvent} to access data.
	 *
	 * @param event Event received when the associated menu item is chosen.
	 */
	@Override
	public void actionPerformed(@NotNull AnActionEvent event) {
		// Using the event, create and show a dialog
		Project currentProject = event.getProject();
		StringBuilder dlgMsg = new StringBuilder(event.getPresentation().getText() + " Selected!");
		String dlgTitle = event.getPresentation().getDescription();
		// If an element is selected in the editor, add info about it.
		Navigatable nav = event.getData(CommonDataKeys.NAVIGATABLE);
		if (nav != null) {
			dlgMsg.append(String.format("\nSelected Element: %s", nav));
		}
		Messages.showMessageDialog(currentProject, dlgMsg.toString(), dlgTitle, Messages.getInformationIcon());
	}

	/**
	 * Determines whether this menu item is available for the current context.
	 * Requires a project to be open.
	 *
	 * @param e Event received when the associated group-id menu is chosen.
	 */
	@Override
	public void update(AnActionEvent e) {
		// Set the availability based on whether a project is open
		Project project = e.getProject();
		e.getPresentation().setEnabledAndVisible(project != null);
	}
}
