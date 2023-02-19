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

	@Override
	public String toString() {
		return "ConnectState{" + "login='" + login + '\'' + ", pwd='" + pwd + '\'' + ", url='" + url + '\'' + '}';
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

	public ConnectState(String login, String pwd, String url) {
		this.login = login;
		this.pwd = pwd;
		this.url = url;
	}

	public void loadState(ConnectState state) {
		this.login = state.login;
		this.url = state.url;
		this.pwd = state.pwd;
	}
}
