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

import net.pega.intellij.modeler.PegaBundle;

import javax.swing.*;

public class ConnectForm {
	private JPanel contentPane;
	private JTextPane helpConnection;
	private JTextField loginField;
	private JLabel loginLabel;
	private JPasswordField passwordField;
	private JLabel passwordLabel;
	private JTextField urlField;
	private JLabel urlLabel;

	public ConnectForm() {
		helpConnection.setText(PegaBundle.getHelp("connect"));
	}

	public void apply(ConnectState state) {
		state.login = loginField.getText();
		state.pwd = new String(passwordField.getPassword());
		state.url = urlField.getText();
	}

	public ConnectState currentState() {
		return new ConnectState(loginField.getText(), new String(passwordField.getPassword()), urlField.getText());
	}

	public boolean isModified(ConnectState state) {
		final ConnectState connectState = currentState();
		return !connectState.equals(state);
	}

	public void reset(ConnectState state) {
		passwordField.setText(state.pwd);
		loginField.setText(state.login);
		urlField.setText(state.url);
	}
}
