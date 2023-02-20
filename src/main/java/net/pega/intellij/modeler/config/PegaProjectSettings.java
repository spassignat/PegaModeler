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
	private final List<ChangeListener> listeners = new ArrayList<>();
	private final PegaConfigState state = new PegaConfigState();

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

