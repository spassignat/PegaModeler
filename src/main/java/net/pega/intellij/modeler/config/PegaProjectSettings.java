package net.pega.intellij.modeler.config;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.project.Project;
import com.intellij.util.xmlb.annotations.Transient;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@State(name = "PegaProjectSettings", storages = @Storage(value = "pega.xml"))
public class PegaProjectSettings implements PersistentStateComponent<PegaProjectSettings.PegaConfigState> {
	@Transient
	private List<ChangeListener> listeners = new ArrayList<>();
	private PegaConfigState state = new PegaConfigState();

	public PegaProjectSettings() {
	}

	public static PegaProjectSettings getInstance(@NotNull Project project) {
		return project.getService(PegaProjectSettings.class);
	}

	public void addChangeListener(ChangeListener changeListener) {
		listeners.add(changeListener);
	}

	public void fireChangeEvent() {
		final ChangeEvent e = new ChangeEvent(this);
		for (int i = 0; i < listeners.size(); i++) {
			ChangeListener changeListener = listeners.get(i);
			changeListener.stateChanged(e);
		}
	}

	public PegaConfigState getState() {
		return state;
	}

	public void loadState(PegaConfigState state) {
		this.state.login = state.login;
		this.state.url = state.url;
		this.state.baseClassName = state.baseClassName;
		this.state.pwd = state.pwd;
		this.state.caseTypeColor = state.caseTypeColor;
		this.state.highestClasses = state.highestClasses;
		this.state.pageColor = state.pageColor;
		this.state.entityColor = state.entityColor;
	}

	public static class PegaConfigState {
		@NonNls
		public boolean abstractOnTop;
		@NonNls
		public String baseClassName;
		@NonNls
		public String caseTypeColor;
		@NonNls
		public String entityColor;
		public String[] highestClasses;
		@NonNls
		public String login;
		public int maxClass;
		@NonNls
		public String pageColor;
		@NonNls
		public String pwd;
		@NonNls
		public String url;



		public PegaConfigState() {
		}

		public PegaConfigState(boolean abstractOnTop,
							   String baseClassName,
							   String caseTypeColor,
							   String entityColor,
							   String[] highestClasses,
							   String login,
							   int maxClass,
							   String pageColor,
							   String pwd,
							   String url) {
			this.abstractOnTop = abstractOnTop;
			this.baseClassName = baseClassName;
			this.caseTypeColor = caseTypeColor;
			this.entityColor = entityColor;
			this.highestClasses = highestClasses;
			this.login = login;
			this.maxClass = maxClass;
			this.pageColor = pageColor;
			this.pwd = pwd;
			this.url = url;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;
			PegaConfigState that = (PegaConfigState) o;
			return abstractOnTop == that.abstractOnTop && maxClass == that.maxClass && Objects.equals(baseClassName, that.baseClassName) && Objects.equals(caseTypeColor,
																																						   that.caseTypeColor) && Objects.equals(
					entityColor,
					that.entityColor) && Arrays.equals(highestClasses, that.highestClasses) && Objects.equals(login, that.login) && Objects.equals(pageColor, that.pageColor) && Objects.equals(pwd,
																																																that.pwd) && Objects.equals(
					url,
					that.url);
		}

		@Override
		public int hashCode() {
			int result = Objects.hash(abstractOnTop, baseClassName, caseTypeColor, entityColor, login, maxClass, pageColor, pwd, url);
			result = 31 * result + Arrays.hashCode(highestClasses);
			return result;
		}
	}
}

