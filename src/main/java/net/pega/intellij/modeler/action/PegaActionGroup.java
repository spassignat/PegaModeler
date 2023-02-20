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
package net.pega.intellij.modeler.action;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.editor.Editor;
import icons.SdkIcons;

/**
 * Creates an action group to contain menu actions. See plugin.xml declarations.
 */
public class PegaActionGroup extends DefaultActionGroup {
	/**
	 * Given {@link PegaActionGroup} is derived from {@link com.intellij.openapi.actionSystem.ActionGroup},
	 * in this context {@code update()} determines whether the action group itself should be enabled or disabled.
	 * Requires an editor to be active in order to enable the group functionality.
	 *
	 * @param event Event received when the associated group-id menu is chosen.
	 * @see com.intellij.openapi.actionSystem.AnAction#update(AnActionEvent)
	 */
	@Override
	public void update(AnActionEvent event) {
		// Enable/disable depending on whether user is editing
		Editor editor = event.getData(CommonDataKeys.EDITOR);
		event.getPresentation().setEnabled(editor != null);
		// Take this opportunity to set an icon for the group.
		event.getPresentation().setIcon(SdkIcons.PegaIcon);
	}
}
