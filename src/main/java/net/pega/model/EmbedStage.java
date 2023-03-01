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
package net.pega.model;

import com.intellij.openapi.diagnostic.Attachment;

import java.util.List;

public class EmbedStage extends Rule {
	List<Attachment> pyAttachments;
	List<Chanel> pyChannels;
	List<RuleObjFlow> pyProcesses;
	boolean pyIsInitializationStage;
	private String pyStageName;

	public String getPyStageName() {
		return pyStageName;
	}

	public boolean isPyIsInitializationStage() {
		return pyIsInitializationStage;
	}

	public void setPyIsInitializationStage(boolean pyIsInitializationStage) {
		this.pyIsInitializationStage = pyIsInitializationStage;
	}

	public boolean isPyIsTerminalStage() {
		return pyIsTerminalStage;
	}

	public void setPyIsTerminalStage(boolean pyIsTerminalStage) {
		this.pyIsTerminalStage = pyIsTerminalStage;
	}

	boolean pyIsTerminalStage;

	public List<Attachment> getPyAttachments() {
		return pyAttachments;
	}

	public void setPyAttachments(List<Attachment> pyAttachments) {
		this.pyAttachments = pyAttachments;
	}

	public List<Chanel> getPyChannels() {
		return pyChannels;
	}

	public void setPyChannels(List<Chanel> pyChannels) {
		this.pyChannels = pyChannels;
	}

	public List<RuleObjFlow> getPyProcesses() {
		return pyProcesses;
	}

	public void setPyProcesses(List<RuleObjFlow> pyProcesses) {
		this.pyProcesses = pyProcesses;
	}

	public void setPyStageName(String pyStageName) {
		this.pyStageName = pyStageName;
	}
}
