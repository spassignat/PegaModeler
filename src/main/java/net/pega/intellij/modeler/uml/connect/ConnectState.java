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
package net.pega.intellij.modeler.uml.connect;

import org.jetbrains.annotations.NonNls;

import java.util.Objects;

public class ConnectState {
	@NonNls
	public String login;
	@NonNls
	public String pwd;
	@NonNls
	public String url;

	public ConnectState() {
	}

	public ConnectState(String login, String pwd, String url) {
		this.login = login;
		this.pwd = pwd;
		this.url = url;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ConnectState that = (ConnectState) o;
		return Objects.equals(login, that.login) && Objects.equals(pwd, that.pwd) && Objects.equals(url, that.url);
	}

	@Override
	public int hashCode() {
		return Objects.hash(login, pwd, url);
	}

	public void loadState(ConnectState state) {
		this.login = state.login;
		this.url = state.url;
		this.pwd = state.pwd;
	}

	@Override
	public String toString() {
		return "ConnectState{" + "login='" + login + '\'' + ", pwd='" + pwd + '\'' + ", url='" + url + '\'' + '}';
	}
}
