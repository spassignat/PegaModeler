package net.pega.intellij.modeler.uml.data;

import net.pega.intellij.modeler.PegaBundle;
import net.pega.intellij.modeler.config.PegaProjectSettings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	private JPanel contentPane;
	private JTextField entityColorField;
	private JTextPane helpDataModel;
	private JList<String> list1;
	private JTextField maxClass;
	private JCheckBox onTopCheckBox;
	private JTextField pageColorField;
	private JTextField textField1;

	public DataModelForm() {
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
		maxClass.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ke) {
				String value = maxClass.getText();
				try {
					Integer.parseInt(value);
					maxClass.setForeground(Color.black);
				} catch (NumberFormatException e) {
					maxClass.setForeground(Color.red);
				}
			}
		});
		helpDataModel.setText(PegaBundle.getHelp("datamodel"));
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
		 int i=2;
		try {
			i = Integer.parseInt(text);
		} catch (NumberFormatException e) {
		}
		return new DataModelState(onTopCheckBox.isSelected(), baseClassField.getText(), caseTypeColorField.getText(), entityColorField.getText(), cls, i, pageColorField.getText());
	}

	public boolean isModified(DataModelState state) {
		return !currentState().equals(state);
	}

	public void reset(DataModelState dataModelState) {
		final DataModelState state = dataModelState;
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
