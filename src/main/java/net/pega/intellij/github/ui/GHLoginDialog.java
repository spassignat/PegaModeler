package net.pega.intellij.github.ui;

import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.components.JBTextField;
import com.intellij.ui.components.panels.HorizontalLayout;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

public class GHLoginDialog extends DialogWrapper {
	private static final int DIALOG_WIDTH = 400;

	private final JPanel myPanel;
	private final JLabel myLoginLabel;
	private final JBTextField myLoginField;
	private final JLabel myPasswordLabel;
	private final JPasswordField myPasswordField;

	public GHLoginDialog(@Nullable Component parent, @NotNull String title) {
		super(parent, false);

		setTitle(title);
		setResizable(false);
		setModal(true);

		myLoginLabel = new JLabel("Login:");
		myLoginField = new JBTextField();
		myPasswordLabel = new JLabel("Password:");
		myPasswordField = new JPasswordField();

		myPanel = new JPanel(new HorizontalLayout(5));
		myPanel.add(myLoginLabel);
		myPanel.add(myLoginField);
		myPanel.add(myPasswordLabel);
		myPanel.add(myPasswordField);

		init();
	}

	@Nullable
	@Override
	protected JComponent createCenterPanel() {
		return myPanel;
	}

	@NotNull
	public String getLogin() {
		return myLoginField.getText();
	}

	@NotNull
	public String getPassword() {
		return new String(myPasswordField.getPassword());
	}

	@Override
	protected void init() {
		super.init();
		getWindow().setSize(new Dimension(DIALOG_WIDTH, getWindow().getHeight()));
	}
}
