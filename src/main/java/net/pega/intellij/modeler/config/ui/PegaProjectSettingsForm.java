// Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package net.pega.intellij.modeler.config.ui;

import com.intellij.openapi.options.ConfigurableUi;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.Project;
import net.pega.intellij.modeler.config.PegaProjectSettings;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.List;

public class PegaProjectSettingsForm implements ConfigurableUi<PegaProjectSettings> {
	private final DefaultListModel<String> model;
	private JButton addButton;
	private JTextField baseClassField;
	private JTextField caseTypeColorField;
	private JLabel classLabel;
	private JPanel configPane;
	private JTextField entityColorField;
	private JList<String> list1;
	private JTextField loginField;
	private JLabel loginLabel;
	private JCheckBox onTopCheckBox;
	private JTextField pageColorField;
	private JPasswordField passwordField;
	private JLabel passwordLabel;
	private JTextField textField1;
	private JTextArea urlField;
	private JLabel urlLabel;

	public PegaProjectSettingsForm(Project project) {
		model = new DefaultListModel<String>();
		textField1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					model.addElement(textField1.getText());
				}
			}
		});
		list1.setModel(model);
		list1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_DELETE) {
					final List<String> selectedValuesList = list1.getSelectedValuesList();
					for (Object o : selectedValuesList) {
						model.removeElement(o);
					}
				}
			}
		});
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.addElement(textField1.getText().trim());
			}
		});
	}

	@Override
	public void apply(@NotNull PegaProjectSettings settings) throws ConfigurationException {
		final PegaProjectSettings.PegaConfigState state = settings.getState();
		state.baseClassName = baseClassField.getText();
		state.login = loginField.getText();
		state.pwd = passwordField.getText();
		state.url = urlField.getText();
		state.pageColor = pageColorField.getText();
		state.entityColor = entityColorField.getText();
		state.caseTypeColor = caseTypeColorField.getText();
		final Object[] objects = model.toArray();
		state.highestClasses = new String[objects.length];
		System.arraycopy(objects, 0, state.highestClasses, 0, objects.length);
		settings.fireChangeEvent();
	}

	@Override
	public @NotNull JComponent getComponent() {
		return configPane;
	}

	@Override
	public boolean isModified(@NotNull PegaProjectSettings settings) {
		final Object[] objects = model.toArray();
		Arrays.sort(objects);
		String[] cls = new String[objects.length];
		System.arraycopy(objects, 0, cls, 0, cls.length);
		final PegaProjectSettings.PegaConfigState
				conf =
				new PegaProjectSettings.PegaConfigState(onTopCheckBox.isSelected(),
														baseClassField.getText(),
														urlField.getText(),
														loginField.getText(),
														cls,
														new String(passwordField.getPassword()),
														caseTypeColorField.getText(),
														pageColorField.getText(),
														entityColorField.getText());
		return !settings.getState().equals(conf);
	}

	@Override
	public void reset(@NotNull PegaProjectSettings settings) {
		final PegaProjectSettings.PegaConfigState state = settings.getState();
		passwordField.setText(state.pwd);
		loginField.setText(state.login);
		urlField.setText(state.url);
		baseClassField.setText(state.baseClassName);
		if (state.caseTypeColor != null && "".equals(state.caseTypeColor.trim())) {
			caseTypeColorField.setText(state.caseTypeColor);
		} else {
			caseTypeColorField.setText("#2FE5C2");
		}
		if (state.pageColor != null && "".equals(state.pageColor.trim())) {
			pageColorField.setText(state.pageColor);
		} else {
			pageColorField.setText("#2FA1E5");
		}
		if (state.entityColor != null && "".equals(state.entityColor.trim())) {
			entityColorField.setText(state.entityColor);
		} else {
			entityColorField.setText("#F85475");
		}
		model.removeAllElements();
		if (state.highestClasses != null) {
			for (String highestClass : state.highestClasses) {
				model.addElement(highestClass);
			}
		} else {
			model.addElement("Work-");
			model.addElement("Data-");
		}
	}
}
