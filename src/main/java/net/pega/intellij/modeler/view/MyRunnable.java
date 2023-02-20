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
package net.pega.intellij.modeler.view;

import com.intellij.openapi.application.WriteAction;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import net.pega.intellij.modeler.PegaPlugin;
import net.pega.intellij.modeler.config.PegaConfigState;
import net.pega.intellij.modeler.config.PegaProjectSettings;
import net.pega.intellij.modeler.uml.PegaClient;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class MyRunnable implements Runnable, MessageCallback {
	final JTextArea responsesField;
	private final String fileName;
	private final PegaClient pegaClient;
	private final @NotNull Project project;

	public MyRunnable(JTextArea responsesField, PegaClient pegaClient, @NotNull Project project, String fileName) {
		this.pegaClient = pegaClient;
		this.project = project;
		this.fileName = fileName;
		this.responsesField = responsesField;
	}

	public void log(String msg) {
		responsesField.append(msg);
		//		System.out.println(msg);
	}

	@Override
	public void run() {
		responsesField.setText("");
		Logger.getInstance(PegaPlugin.class).info("Generate " + fileName);
		try {
			WriteAction.run(() -> {
				final PegaProjectSettings instance = PegaProjectSettings.getInstance(project);
				final PegaConfigState state = instance.getState();
				pegaClient.init(state, this);
				final VirtualFile root = project.getWorkspaceFile().getParent().getParent();
				VirtualFile pegaFolder = root.findChild("pega");
				if (pegaFolder == null) {
					pegaFolder = root.createChildDirectory(this, "pega");
				}
				final VirtualFile dmFile = pegaFolder.findOrCreateChildData(this, fileName);
				final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
				final PrintStream printStream = new PrintStream(byteArrayOutputStream);
				pegaClient.analyse(printStream);
				printStream.flush();
				VfsUtil.saveText(dmFile, byteArrayOutputStream.toString());
			});
		} catch (Exception e) {
			responsesField.setText(e.toString());
			Logger.getInstance(PegaPlugin.class).error(e);
		}
	}
}
