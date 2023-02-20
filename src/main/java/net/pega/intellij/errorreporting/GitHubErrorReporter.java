/*
 * Copyright (c) 2018-2023 Stephane Passignat - Exygen
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
package net.pega.intellij.errorreporting;

import com.intellij.diagnostic.DiagnosticBundle;
import com.intellij.diagnostic.IdeaReportingEvent;
import com.intellij.diagnostic.LogMessage;
import com.intellij.ide.DataManager;
import com.intellij.ide.plugins.IdeaPluginDescriptor;
import com.intellij.idea.IdeaLogger;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationGroup;
import com.intellij.notification.NotificationGroupManager;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.application.ApplicationInfo;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.ApplicationNamesInfo;
import com.intellij.openapi.application.ex.ApplicationInfoEx;
import com.intellij.openapi.diagnostic.Attachment;
import com.intellij.openapi.diagnostic.ErrorReportSubmitter;
import com.intellij.openapi.diagnostic.IdeaLoggingEvent;
import com.intellij.openapi.diagnostic.SubmittedReportInfo;
import com.intellij.openapi.project.Project;
import com.intellij.util.Consumer;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.LinkedHashMap;

/**
 * Provides error reporting functionality for the plugin. When a user experiences an exception thrown by the plugin,
 * this is used to create an issue on a dedicated GitHub repository. This class takes care of collecting information about
 * the error and starts the creation of a GitHub issue as background task.
 */
public class GitHubErrorReporter extends ErrorReportSubmitter {
	@SuppressWarnings("BooleanMethodNameMustStartWithQuestion")
	private static boolean doSubmit(final IdeaLoggingEvent event, final Component parentComponent, final Consumer<SubmittedReportInfo> callback, final GitHubErrorBean bean, final String description) {
		final DataContext dataContext = DataManager.getInstance().getDataContext(parentComponent);
		bean.setDescription(description);
		bean.setMessage(event.getMessage());
		if (event instanceof IdeaReportingEvent) {
			final IdeaPluginDescriptor plugin = ((IdeaReportingEvent) event).getPlugin();
			if (plugin != null) {
				bean.setPluginName(plugin.getName());
				bean.setPluginVersion(plugin.getVersion());
			}
		}
		Object data = event.getData();
		if (data instanceof LogMessage) {
			bean.setAttachments(ContainerUtil.filter(((LogMessage) data).getAllAttachments(), Attachment::isIncluded));
		}
		LinkedHashMap<String, String>
				reportValues =
				IdeaInformationProxy.getKeyValuePairs(bean, ApplicationManager.getApplication(), (ApplicationInfoEx) ApplicationInfo.getInstance(), ApplicationNamesInfo.getInstance());
		final Project project = CommonDataKeys.PROJECT.getData(dataContext);
		final CallbackWithNotification notifyingCallback = new CallbackWithNotification(callback, project);
		AnonymousFeedbackTask task = new AnonymousFeedbackTask(project, ErrorReportBundle.message("report.error.progress.dialog.text"), reportValues, notifyingCallback);
		return task.perform();
	}

	@NotNull
	@Override
	public String getReportActionText() {
		return ErrorReportBundle.message("report.error.to.plugin.vendor");
	}

	@Override
	public boolean submit(IdeaLoggingEvent @NotNull [] events, @Nullable String additionalInfo, @NotNull Component parentComponent, @NotNull Consumer<? super SubmittedReportInfo> consumer) {
		GitHubErrorBean errorBean = new GitHubErrorBean(events[0].getThrowable(), IdeaLogger.ourLastActionId);
		return doSubmit(events[0], parentComponent, (Consumer<SubmittedReportInfo>) consumer, errorBean, additionalInfo);
	}

	/**
	 * Provides functionality to show a error report message to the user that gives a click-able link to the created issue.
	 */
	static class CallbackWithNotification implements Consumer<SubmittedReportInfo> {
		private final Consumer<SubmittedReportInfo> myOriginalConsumer;
		private final Project myProject;

		CallbackWithNotification(Consumer<SubmittedReportInfo> originalConsumer, Project project) {
			this.myOriginalConsumer = originalConsumer;
			this.myProject = project;
		}

		@Override
		public void consume(SubmittedReportInfo reportInfo) {
			myOriginalConsumer.consume(reportInfo);
			final NotificationGroup group = NotificationGroupManager.getInstance().getNotificationGroup("Error Report");
			final String errorReport = DiagnosticBundle.message("error.report.title");
			final Notification notification = group.createNotification(errorReport, reportInfo.getLinkText(), NotificationType.ERROR);
			notification.setImportant(false).notify(myProject);
		}
	}
}
