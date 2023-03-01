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
 * PARTICULAR PURPOSE AND NON INFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package net.pega.model;

public class ExpressionBuilder extends Rule {
	boolean pyShowCustomPages;
	boolean pyShowLocalVariables;
	boolean pyShowParameters;
	boolean pyEditable;

	public boolean isPyShowCustomPages() {
		return pyShowCustomPages;
	}

	public void setPyShowCustomPages(boolean pyShowCustomPages) {
		this.pyShowCustomPages = pyShowCustomPages;
	}

	public boolean isPyShowLocalVariables() {
		return pyShowLocalVariables;
	}

	public void setPyShowLocalVariables(boolean pyShowLocalVariables) {
		this.pyShowLocalVariables = pyShowLocalVariables;
	}

	public boolean isPyShowParameters() {
		return pyShowParameters;
	}

	public void setPyShowParameters(boolean pyShowParameters) {
		this.pyShowParameters = pyShowParameters;
	}

	public boolean isPyEditable() {
		return pyEditable;
	}

	public void setPyEditable(boolean pyEditable) {
		this.pyEditable = pyEditable;
	}

	public boolean isPyIsLaunchAsOverlay() {
		return pyIsLaunchAsOverlay;
	}

	public void setPyIsLaunchAsOverlay(boolean pyIsLaunchAsOverlay) {
		this.pyIsLaunchAsOverlay = pyIsLaunchAsOverlay;
	}

	boolean pyIsLaunchAsOverlay;
}
