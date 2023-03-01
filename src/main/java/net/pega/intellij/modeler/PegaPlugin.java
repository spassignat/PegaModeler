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
package net.pega.intellij.modeler;

import com.intellij.openapi.application.WriteAction;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.messages.Topic;

import java.io.IOException;
import java.io.OutputStream;

public final class PegaPlugin {
	public static final Topic<RuleListener> RULE_LISTENER_TOPIC = new Topic<>(RuleListener.class, Topic.BroadcastDirection.TO_CHILDREN);

	public static void saveToFile(Project project, Object resquestor, OutputStream os, String... paths) throws IOException {
		WriteAction.run(() -> {
			VirtualFile currentFile = project.getWorkspaceFile().getParent().getParent();
			for (int i = 0; i < paths.length; i++) {
				String path = paths[i];
				VirtualFile pegaFolder = currentFile.findChild(path);
				if (pegaFolder == null || !pegaFolder.exists()) {
					if (i == paths.length - 1) {
						currentFile = currentFile.findOrCreateChildData(resquestor, path);
					} else {
						currentFile = currentFile.createChildDirectory(resquestor, path);
					}
				}else{
					currentFile=pegaFolder;
				}
			}
			VfsUtil.saveText(currentFile, os.toString());
		});
	}

	public static String snakeToCamel(String str) {
		return str.replaceAll("-", "_");
	}
}
