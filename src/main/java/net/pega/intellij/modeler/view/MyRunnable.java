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
package net.pega.intellij.modeler.view;

import com.intellij.openapi.application.WriteAction;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import net.pega.intellij.modeler.PegaClient;
import net.pega.intellij.modeler.PegaPlugin;
import net.pega.intellij.modeler.config.PegaConfigState;
import net.pega.intellij.modeler.config.PegaProjectSettings;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Objects;
import java.util.Optional;
import java.util.ServiceLoader;

public class MyRunnable implements Runnable, MessageCallback {
	final MessageCallback responsesField;
	private final String fileName;
	private final PegaClient pegaClient;
	private final @NotNull Project project;

	public MyRunnable(MessageCallback responsesField, String pegaClientName, @NotNull Project project, String fileName) {
		final ServiceLoader<PegaClient> load = ServiceLoader.load(PegaClient.class, PegaClient.class.getClassLoader());
		final Optional<ServiceLoader.Provider<PegaClient>> first = load.stream().filter(elt -> Objects.equals(elt.get().getAnalysis(), pegaClientName)).findFirst();
		final PegaProjectSettings service = project.getService(PegaProjectSettings.class);
		final PegaConfigState state = service.getState();
		this.pegaClient = first.get().get();
		this.pegaClient.init(state, this);
		this.project = project;
		this.fileName = fileName;
		this.responsesField = responsesField;
	}

	@Override
	public void clear() {
		responsesField.clear();
	}

	public void log(String msg) {
		responsesField.log(msg);
	}

	@Override
	public void run() {
		responsesField.clear();
		Logger.getInstance(PegaPlugin.class).info("Generate " + fileName);
		try {
			WriteAction.run(() -> {
				final PegaProjectSettings service = project.getService(PegaProjectSettings.class);
				final PegaConfigState state = service.getState();
				pegaClient.init(state, this);
				final VirtualFile root = project.getWorkspaceFile().getParent().getParent();
				VirtualFile pegaFolder = root.findChild("pega");
				if (pegaFolder == null) {
					pegaFolder = root.createChildDirectory(this, "pega");
				}
				final VirtualFile dmFile = pegaFolder.findOrCreateChildData(this, fileName);
				final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
				final PrintStream printStream = new PrintStream(byteArrayOutputStream);
				pegaClient.analyse(printStream, null);
				printStream.flush();
				VfsUtil.saveText(dmFile, byteArrayOutputStream.toString());
			});
		} catch (Exception e) {
			responsesField.log(e.toString());
			Logger.getInstance(PegaPlugin.class).error(e);
		}
	}
}
