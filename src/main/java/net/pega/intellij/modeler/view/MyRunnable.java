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
	private final PegaClient pegaClient;
	private final @NotNull Project project;
	JTextArea responsesField;
	private String fileName;

	public MyRunnable(JTextArea responsesField, PegaClient pegaClient, @NotNull Project project, String fileName) {
		this.pegaClient = pegaClient;
		this.project = project;
		this.fileName = fileName;
		this.responsesField = responsesField;
	}

	@Override
	public void run() {
		responsesField.setText("");
		Logger.getInstance(PegaPlugin.class).info("Generate " + fileName);
		try {
			WriteAction.run(() -> {
				final PegaProjectSettings instance = PegaProjectSettings.getInstance(project);
				final PegaConfigState state = instance.getState();
				pegaClient.init(state,this);
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
				final byte[] bytes = byteArrayOutputStream.toByteArray();
				VfsUtil.saveText(dmFile, new String(bytes));
			});
		} catch (Exception e) {
			responsesField.setText(e.toString());
			Logger.getInstance(PegaPlugin.class).error(e);
		}
	}

	public void log(String msg) {
		responsesField.append(msg);
//		System.out.println(msg);
	}
}
