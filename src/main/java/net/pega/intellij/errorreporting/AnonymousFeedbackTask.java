
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
package net.pega.intellij.errorreporting;

import com.intellij.openapi.diagnostic.SubmittedReportInfo;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.Task.Backgroundable;
import com.intellij.openapi.project.Project;
import com.intellij.util.Consumer;
import net.pega.intellij.modeler.config.PegaConfigState;
import net.pega.intellij.modeler.config.PegaProjectSettings;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;

/**
 * Encapsulates the sending of feedback into a background task that is run by {@link GitHubErrorReporter}
 */
public class AnonymousFeedbackTask extends Backgroundable {
	private final String githubToken;
	private final Consumer<SubmittedReportInfo> myCallback;
	private final LinkedHashMap<String, String> myParams;

	AnonymousFeedbackTask(@Nullable Project project, @NotNull String title, LinkedHashMap<String, String> params, final Consumer<SubmittedReportInfo> callback) {
		super(project, title, true);
		final PegaProjectSettings service = project.getService(PegaProjectSettings.class);
		final PegaConfigState state = service.getState();
		githubToken = state.githubToken;
		myParams = params;
		myCallback = callback;
	}

	public boolean perform() {
		final SubmittedReportInfo t = AnonymousFeedback.sendFeedback(myParams, githubToken);
		myCallback.consume(t);
		return t.getStatus() == SubmittedReportInfo.SubmissionStatus.FAILED;
	}

	@Override
	public void run(@NotNull ProgressIndicator indicator) {
		indicator.setIndeterminate(true);
		myCallback.consume(AnonymousFeedback.sendFeedback(myParams, githubToken));
	}
}
