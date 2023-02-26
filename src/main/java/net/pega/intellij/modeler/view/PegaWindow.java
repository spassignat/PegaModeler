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
package net.pega.intellij.modeler.view;

import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.util.messages.MessageBus;
import net.pega.intellij.modeler.*;
import net.pega.intellij.modeler.config.PegaConfigState;
import net.pega.intellij.modeler.config.PegaProjectSettings;
import net.pega.model.RuleApplication;
import net.pega.model.RuleObjCaseType;
import net.pega.model.RuleSetVersion;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;

import static net.pega.intellij.modeler.PegaPlugin.RULE_LISTENER_TOPIC;

public class PegaWindow {
	public JTextArea area;
	DefaultComboBoxModel<RuleApplication> appModel = new DefaultComboBoxModel<RuleApplication>();
	Project project;
	DefaultComboBoxModel<RuleSetVersion> ruleSetVersionModel = new DefaultComboBoxModel<RuleSetVersion>();
	private JComboBox<RuleApplication> appComboBox;
	private JButton clearButton;
	private JButton connectButton;
	private JButton generateDataModelButton;
	private JButton generateProcess;
	private JButton loadAppsButton;
	private JButton loadRuleSetButton;
	private JPanel myToolWindowContent;
	private JComboBox<RuleSetVersion> ruleSetsComboBox;
	private JTree ruleTree;
	private DefaultTreeModel ruleTreeModel;
	private JLabel urlLabel;
	private JButton validApp;
	private JButton validRS;

	public PegaWindow(ToolWindow toolWindow, @NotNull Project project) {
		this.project = project;
		final PegaProjectSettings service = project.getService(PegaProjectSettings.class);
		final PegaConfigState state = service.getState();
		final Application application = ApplicationManager.getApplication();
		service.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				updateView(state);
			}
		});
		setupAppCombo();
		setupButtons(project, application);
		setupRulesetCombo();
		setupTree();
		updateView(state);
		setupMessageBus(project);
	}

	public JPanel getContent() {
		return myToolWindowContent;
	}

	private JPopupMenu createPopup() {
		JPopupMenu popupMenu = new JPopupMenu();
		final TreePath[] selectionPaths = ruleTree.getSelectionPaths();
		final TreePath selectionPath = selectionPaths[selectionPaths.length - 1];
		final DefaultMutableTreeNode lastPathComponent = (DefaultMutableTreeNode) selectionPath.getLastPathComponent();
		final Object userObject = lastPathComponent.getUserObject();
		if (userObject instanceof RuleObjCaseType) {
			JMenuItem menuItem1 = new JMenuItem("DataModel");
			popupMenu.add(menuItem1);
			menuItem1.addActionListener(e -> {
				ToolWindowManager toolWindowManager = ToolWindowManager.getInstance(project);
				ToolWindow toolWindow = toolWindowManager.getToolWindow("Pega");
				if (toolWindow != null) {
					final MessageCallback component = (MessageCallback) toolWindow.getComponent().getComponent(0);
					toolWindow.show();
					final MyRunnable dataModel = new MyRunnable(component, "DataModel", project, "data-model.puml");
					ApplicationManager.getApplication().invokeLater(dataModel);
				}
			});
			JMenuItem menuItem2 = new JMenuItem("Process");
			menuItem2.addActionListener(e -> {
				ToolWindowManager toolWindowManager = ToolWindowManager.getInstance(project);
				ToolWindow toolWindow = toolWindowManager.getToolWindow("Pega");
				if (toolWindow != null) {
					final MessageCallback component = (MessageCallback) toolWindow.getComponent().getComponent(0);
					toolWindow.show();
					final MyRunnable dataModel = new MyRunnable(component, "Process", project, "data-model.puml");
					ApplicationManager.getApplication().invokeLater(dataModel);
				}
			});
			popupMenu.add(menuItem2);
		}
		return popupMenu;
	}

	private void createUIComponents() {
		myToolWindowContent = new MyJPanel() {
			@Override
			public void clear() {
				area.setText("");
			}

			@Override
			public void log(String message) {
				area.append(message);
			}
		};
		// TODO: place custom component creation code here
	}

	private DefaultMutableTreeNode findNodeByObject(Object obj, DefaultMutableTreeNode node) {
		if (node.getUserObject() == obj) {
			return node;
		}
		final Enumeration<TreeNode> children = node.children();
		while (children.hasMoreElements()) {
			TreeNode treeNode = children.nextElement();
			final DefaultMutableTreeNode nodeByObject = findNodeByObject(obj, (DefaultMutableTreeNode) treeNode);
			if (nodeByObject != null) {
				return nodeByObject;
			}
		}
		return null;
	}

	private void setupAppCombo() {
		appComboBox.setModel(appModel);
		appComboBox.setRenderer(new DefaultListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
				final String pyClassName;
				if (value != null) {
					pyClassName = ((RuleApplication) value).getPyLabel();
				} else {
					pyClassName = "null";
				}
				return super.getListCellRendererComponent(list, pyClassName, index, isSelected, cellHasFocus);
			}
		});
	}

	private void setupButtons(@NotNull Project project, Application application) {
		generateDataModelButton.addActionListener(evt -> {
			final MyRunnable dataModel = new MyRunnable((MessageCallback) myToolWindowContent, "DataModel", project, "data-model.puml");
			application.invokeAndWait(dataModel);
		});
		connectButton.addActionListener(evt -> {
			final MyRunnable connect = new MyRunnable((MessageCallback) myToolWindowContent, "Connect", project, "connect.txt");
			application.invokeAndWait(connect);
		});
		generateProcess.addActionListener(evt -> {
			final MyRunnable connect = new MyRunnable((MessageCallback) myToolWindowContent, "Process", project, "process.puml");
			application.invokeAndWait(connect);
		});
		clearButton.addActionListener(e -> ((MyJPanel) myToolWindowContent).clear());
		loadAppsButton.addActionListener(evt -> {
			application.invokeLater(() -> {
				project.getService(ApplicationService.class).loadApplications();
			});
			//			final MyRunnable connect = new MyRunnable((MessageCallback) myToolWindowContent, "Application", project, "applications.puml");
			//			application.invokeAndWait(connect);
		});
		loadRuleSetButton.addActionListener(evt -> {
			final Task.Backgroundable backgroundable = new Task.Backgroundable(project, "Load RuleSets") {
				@Override
				public void run(@NotNull ProgressIndicator indicator) {
					try {
						final RuleApplication selectedApplication = (RuleApplication) appComboBox.getSelectedItem();
						final RuleSetVersionService service = project.getService(RuleSetVersionService.class);
						service.loadRuleSets(indicator, selectedApplication);
						project.getService(RuleSetVersionService.class).loadRuleSets(indicator, selectedApplication);
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				}
			};
			//			final MyRunnable connect = new MyRunnable((MessageCallback) myToolWindowContent, "Application", project, "applications.puml");
			//			application.invokeAndWait(connect);
		});
		validApp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				final Task.Backgroundable backgroundable = new Task.Backgroundable(project, "Load RuleSets") {
					@Override
					public void run(@NotNull ProgressIndicator indicator) {
						try {
							final RuleApplication selectedApplication = (RuleApplication) appComboBox.getSelectedItem();
							final DefaultMutableTreeNode root = (DefaultMutableTreeNode) ruleTreeModel.getRoot();
							final DefaultMutableTreeNode foundApp = findNodeByObject(selectedApplication, root);
							if (foundApp == null) {
								root.add(new DefaultMutableTreeNode(selectedApplication));
								final RuleSetVersionService service = project.getService(RuleSetVersionService.class);
								service.loadRuleSets(indicator, selectedApplication);
								ruleTree.updateUI();
							}
						} catch (Exception ex) {
							throw new RuntimeException(ex);
						}
					}
				};
				ProgressManager.getInstance().run(backgroundable);
			}
		});
		validRS.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				final Task.Backgroundable backgroundable = new Task.Backgroundable(project, "Load RuleSets") {
					@Override
					public void run(@NotNull ProgressIndicator indicator) {
						try {
							final RuleSetVersion ruleSetVersion = (RuleSetVersion) ruleSetsComboBox.getSelectedItem();
							final DefaultMutableTreeNode root = (DefaultMutableTreeNode) ruleTreeModel.getRoot();
							final DefaultMutableTreeNode foundNode = findNodeByObject(ruleSetVersion, root);
							if (foundNode == null) {
								final RuleApplication selectedApplication = (RuleApplication) appComboBox.getSelectedItem();
								final DefaultMutableTreeNode foundAppNode = findNodeByObject(selectedApplication, root);
								if (foundAppNode != null) {
									foundAppNode.add(new DefaultMutableTreeNode(ruleSetVersion));
								}
								CaseTypeService caseTypeService = project.getService(CaseTypeService.class);
								caseTypeService.loadCaseTypes(indicator, selectedApplication, ruleSetVersion);
								ruleTree.updateUI();
							}
						} catch (Exception ex) {
							ex.printStackTrace();
							throw new RuntimeException(ex);
						}
					}
				};
				ProgressManager.getInstance().run(backgroundable);
			}
		});
	}

	private void setupMessageBus(@NotNull Project project) {
		final MessageBus messageBus = project.getMessageBus();
		messageBus.connect().subscribe(RULE_LISTENER_TOPIC, new RuleListener() {
			@Override
			public void clearApplications() {
				appModel.removeAllElements();
			}

			@Override
			public void clearCaseTypes() {
			}

			@Override
			public void clearRuleSetVersion() {
				ruleSetVersionModel.removeAllElements();
			}

			@Override
			public void log(String message) {
				area.append(message + "\n");
			}

			@Override
			public void logEnter(String message) {
				area.append(message + "\n");
			}

			@Override
			public void logExit(String message) {
				area.append(message + "\n");
			}

			@Override
			public void onApplicationChanged(RuleApplication ruleApplication) {
			}

			@Override
			public void onApplicationLoaded(RuleApplication app) {
				appModel.addElement(app);
			}

			@Override
			public void onCaseTypeLoaded(RuleObjCaseType rle) {
				final RuleSetVersion ruleSetVersion = (RuleSetVersion) ruleSetsComboBox.getSelectedItem();
				final DefaultMutableTreeNode root = (DefaultMutableTreeNode) ruleTreeModel.getRoot();
				final DefaultMutableTreeNode foundNode = findNodeByObject(ruleSetVersion, root);
				if (foundNode != null) {
					foundNode.add(new DefaultMutableTreeNode(rle));
				}
			}

			@Override
			public void onRulesetVersionLoaded(RuleSetVersion ruleSetVersion) {
				ruleSetVersionModel.addElement(ruleSetVersion);
			}
		});
	}

	private void setupRulesetCombo() {
		ruleSetsComboBox.setModel(ruleSetVersionModel);
		ruleSetsComboBox.setRenderer(new DefaultListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
				final String pyClassName;
				if (value != null) {
					pyClassName = ((RuleSetVersion) value).getPyLabel();
				} else {
					pyClassName = "null";
				}
				return super.getListCellRendererComponent(list, pyClassName, index, isSelected, cellHasFocus);
			}
		});
	}

	@NotNull
	private void setupTree() {
		ruleTreeModel = new DefaultTreeModel(new DefaultMutableTreeNode("Pega"));
		ruleTree.setModel(ruleTreeModel);
		ruleTree.setCellRenderer(new DefaultTreeCellRenderer() {
			@Override
			public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
				if (value instanceof DefaultMutableTreeNode) {
					final DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) value;
					final Object userObject = treeNode.getUserObject();
					if (userObject instanceof Rule) {
						return super.getTreeCellRendererComponent(tree, ((Rule) userObject).getPyLabel(), sel, expanded, leaf, row, hasFocus);
					}
				}
				return super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
			}
		});
		ruleTree.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				showPopupMenu(e);
			}

			public void mouseReleased(MouseEvent e) {
				showPopupMenu(e);
			}

			private void showPopupMenu(MouseEvent e) {
				if (e.isPopupTrigger()) {
					createPopup().show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
	}

	private void updateView(PegaConfigState state) {
		urlLabel.setText(state.connectState.url);
	}
}
