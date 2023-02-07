package net.pega.intellij.modeler.config;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.project.Project;
import com.intellij.util.xmlb.annotations.Property;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.*;

@State(name = "PegaProjectSettings", storages = @Storage("pegamodeler.xml"))
public class PegaProjectSettings implements PersistentStateComponent<PegaProjectSettings.PegaConfigState> {

	public PegaConfigState state;
	static Map<String, PegaProjectSettings> managers = new HashMap<>();

	public static PegaProjectSettings getInstance(Project myProject) {
		if (!managers.containsKey(myProject.getName())) {
			managers.put(myProject.getName(), new PegaProjectSettings());
		}
		return managers.get(myProject.getName());
	}

	public PegaProjectSettings() {
		state = new PegaConfigState();
	}

	public PegaConfigState getState() {
		return state;
	}

	public void loadState(PegaConfigState state) {
		this.state.login=state.login;
		this.state.url=state.url;
		this.state.baseClassName=state.baseClassName;
		this.state.pwd=state.pwd;
	}

	public static class PegaConfigState {
		@Property
		public String baseClassName;
		@Property
		public String url;
		@Property
		public String login;
		@Property
		public String pwd;
		private List<ChangeListener> listeners = new ArrayList<>();

		public void addChangeListener(ChangeListener changeListener) {
			listeners.add(changeListener);
		}

		@Override
		public String toString() {
			return "PegaConfigState{" + "baseClassName='" + baseClassName + '\'' + ", url='" + url + '\'' + ", login='" + login + '\'' + ", pwd='" + pwd + '\'' + '}';
		}

		public PegaConfigState(String baseClassName, String url, String login, String pwd) {
			this.baseClassName = baseClassName;
			this.url = url;
			this.login = login;
			this.pwd = pwd;
		}

		public PegaConfigState() {
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;
			PegaConfigState that = (PegaConfigState) o;
			return Objects.equals(baseClassName, that.baseClassName) && Objects.equals(url, that.url) && Objects.equals(login, that.login) && Objects.equals(pwd, that.pwd);
		}

		@Override
		public int hashCode() {
			return Objects.hash(baseClassName, url, login, pwd);
		}

		public void fireChangeEvent() {
			final ChangeEvent e = new ChangeEvent(this);
			for (int i = 0; i < listeners.size(); i++) {
				ChangeListener changeListener = listeners.get(i);
				changeListener.stateChanged(e);
			}
		}
	}
}

