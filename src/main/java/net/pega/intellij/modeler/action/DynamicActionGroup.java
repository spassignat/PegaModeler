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

import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import icons.SdkIcons;
import org.jetbrains.annotations.NotNull;

/**
 * Demonstrates adding an action group to a menu statically in plugin.xml, and then creating a menu item within
 * the group at runtime. See plugin.xml for the declaration of {@link DynamicActionGroup}, and note the group
 * declaration does not contain an action. {@link DynamicActionGroup} is based on {@link ActionGroup} because menu
 * children are determined on rules other than just positional constraints.
 *
 * @see ActionGroup
 */
public class DynamicActionGroup extends ActionGroup {
	/**
	 * Returns an array of menu actions for the group.
	 *
	 * @param e Event received when the associated group-id menu is chosen.
	 * @return AnAction[] An instance of {@link AnAction}, in this case containing a single instance of the
	 * {@link PopupDialogAction} class.
	 */
	@Override
	public AnAction @NotNull [] getChildren(AnActionEvent e) {
		return new AnAction[]{new PopupDialogAction("Action Added at Runtime", "Dynamic Action Demo", SdkIcons.Sdk_default_icon)};
	}
}
