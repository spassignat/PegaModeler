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
package net.pega.intellij.generator.datamodel;

import com.intellij.ui.JBColor;
import net.pega.intellij.modeler.PegaBundle;
import net.pega.intellij.modeler.config.PegaProjectSettings;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.List;

public class DataModelForm {
	private final DefaultListModel<String> model;
	private JButton addButton;
	private JTextField baseClassField;
	private JTextField caseTypeColorField;
	private JLabel classLabel;
	private JTextField classNameField;
	private JPanel contentPane;
	private JTextField entityColorField;
	private JTextPane helpDataModel;
	private JList<String> list1;
	private JTextField maxClass;
	private JCheckBox onTopCheckBox;
	private JTextField pageColorField;

	public DataModelForm() {
		model = new DefaultListModel<>();
		classNameField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					model.addElement(classNameField.getText());
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
		addButton.addActionListener(e -> model.addElement(classNameField.getText().trim()));
		maxClass.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ke) {
				String value = maxClass.getText();
				try {
					Integer.parseInt(value);
					maxClass.setForeground(JBColor.BLACK);
				} catch (NumberFormatException e) {
					maxClass.setForeground(JBColor.RED);
				}
			}
		});
		helpDataModel.setText(PegaBundle.getHelp("data-model"));
	}

	public void apply(PegaProjectSettings settings) {
		final DataModelState state = settings.getState().dataModelState;
		try {
			state.maxClass = Integer.parseInt(maxClass.getText());
		} catch (NumberFormatException e) {
		}
		state.baseClassName = baseClassField.getText();
		final Object[] objects = model.toArray();
		state.highestClasses = new String[objects.length];
		System.arraycopy(objects, 0, state.highestClasses, 0, objects.length);
		state.abstractOnTop = onTopCheckBox.isSelected();
		state.pageColor = pageColorField.getText();
		state.entityColor = entityColorField.getText();
		state.caseTypeColor = caseTypeColorField.getText();
	}

	public DataModelState currentState() {
		final Object[] objects = model.toArray();
		Arrays.sort(objects);
		String[] cls = new String[objects.length];
		System.arraycopy(objects, 0, cls, 0, cls.length);
		final String text = maxClass.getText();
		int i = 2;
		try {
			i = Integer.parseInt(text);
		} catch (NumberFormatException e) {
		}
		return new DataModelState(onTopCheckBox.isSelected(), baseClassField.getText(), caseTypeColorField.getText(), entityColorField.getText(), cls, i, pageColorField.getText());
	}

	public boolean isModified(DataModelState state) {
		return !currentState().equals(state);
	}

	public void reset(DataModelState state) {
		maxClass.setText("" + state.maxClass);
		onTopCheckBox.setSelected(state.abstractOnTop);
		baseClassField.setText(state.baseClassName);
		model.removeAllElements();
		if (state.highestClasses != null) {
			for (String highestClass : state.highestClasses) {
				model.addElement(highestClass);
			}
		} else {
			model.addElement("Work-");
			model.addElement("Data-");
		}
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
	}
}
