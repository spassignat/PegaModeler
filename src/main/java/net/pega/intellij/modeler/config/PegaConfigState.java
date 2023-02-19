package net.pega.intellij.modeler.config;

import com.intellij.util.xmlb.annotations.Transient;
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
	public String toString() {
		return "PegaConfigState{" + "githubToken='" + githubToken + '\'' + ", githubUser='" + githubUser + '\'' + '}';
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
}
