package net.pega.intellij.modeler.uml.connect;

import net.pega.intellij.modeler.PegaBundle;

import javax.swing.*;

public class ConnectForm {
	private JPanel contentPane;
	private JTextPane helpConnection;
	private JTextField loginField;
	private JPasswordField passwordField;
	private JTextField urlField;
	private JLabel urlLabel;
	private JLabel passwordLabel;
	private JLabel loginLabel;

	public ConnectForm() {
		helpConnection.setText(PegaBundle.getHelp("connect"));
	}

	public void apply(ConnectState connectState) {
		final ConnectState state = connectState;
		state.login = loginField.getText();
		state.pwd = passwordField.getText();
		state.url = urlField.getText();
	}

	public ConnectState currentState() {
		final ConnectState state = new ConnectState(loginField.getText(), passwordField.getText(), urlField.getText());
		return state;
	}

	public boolean isModified(ConnectState state) {
		final ConnectState connectState = currentState();
		return !connectState.equals(state);
	}

	public void reset(ConnectState connectState) {
		final ConnectState state = connectState;
		passwordField.setText(state.pwd);
		loginField.setText(state.login);
		urlField.setText(state.url);
	}
}
