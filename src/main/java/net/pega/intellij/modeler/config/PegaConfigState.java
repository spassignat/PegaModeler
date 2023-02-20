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

import net.pega.intellij.modeler.uml.connect.ConnectState;
import net.pega.intellij.modeler.uml.data.DataModelState;

import java.util.Objects;

public class PegaConfigState {
	public ConnectState connectState = new ConnectState();
	public DataModelState dataModelState = new DataModelState();
	public String githubToken;
	public String githubUser;

	public PegaConfigState() {
	}

	public PegaConfigState(String githubUser, String githubToken) {
		this.githubUser = githubUser;
		this.githubToken = githubToken;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		PegaConfigState that = (PegaConfigState) o;
		return Objects.equals(githubToken, that.githubToken) && Objects.equals(githubUser, that.githubUser);
	}

	@Override
	public int hashCode() {
		return Objects.hash(connectState, dataModelState, githubToken, githubUser);
	}

	@Override
	public String toString() {
		return "PegaConfigState{" + "githubToken='" + githubToken + '\'' + ", githubUser='" + githubUser + '\'' + '}';
	}
}
