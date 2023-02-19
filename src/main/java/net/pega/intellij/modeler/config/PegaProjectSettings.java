package net.pega.intellij.modeler.config;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.project.Project;
import com.intellij.util.xmlb.annotations.Transient;
import org.jetbrains.annotations.NotNull;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;
import java.util.List;

@State(name = "PegaProjectSettings", storages = @Storage(value = "pega.xml"))
public class PegaProjectSettings implements PersistentStateComponent<PegaConfigState> {
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
		this.state.connectState.loadState(state.connectState);
		this.state.dataModelState.loadState(state.dataModelState);
		this.state.githubUser = state.githubUser;
		this.state.githubToken = state.githubToken;
	}
}

