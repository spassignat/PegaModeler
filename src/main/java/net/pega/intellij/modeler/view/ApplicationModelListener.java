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

import net.pega.intellij.modeler.RuleListener;
import net.pega.model.RuleApplication;
import net.pega.model.RuleObjCaseType;
import net.pega.model.RuleSetVersion;

public class ApplicationModelListener implements RuleListener {
	RuleListener delegate;

	@Override
	public void clearApplications() {
		if (delegate != null) {
			delegate.clearApplications();
		}
	}

	@Override
	public void clearCaseTypes() {
		if (delegate != null) {
			delegate.clearCaseTypes();
		}
	}

	@Override
	public void clearRuleSetVersion() {
		if (delegate != null) {
			delegate.clearRuleSetVersion();
		}
	}

	@Override
	public void log(String message) {
		if (delegate != null) {
			delegate.log(message);
		}
	}

	@Override
	public void logEnter(String message) {
		if (delegate != null) {
			delegate.logEnter(message);
		}
	}

	@Override
	public void logExit(String message) {
		if (delegate != null) {
			delegate.logExit(message);
		}
	}

	@Override
	public void onApplicationChanged(RuleApplication ruleApplication) {
		if (delegate != null) {
			delegate.onApplicationChanged(ruleApplication);
		}
	}

	@Override
	public void onApplicationLoaded(RuleApplication app) {
		if (delegate != null) {
			delegate.onApplicationLoaded(app);
		}
	}

	@Override
	public void onCaseTypeLoaded(RuleObjCaseType rle) {
		if (delegate != null) {
			delegate.onCaseTypeLoaded(rle);
		}
	}

	@Override
	public void onRulesetVersionLoaded(RuleSetVersion ruleSetVersion) {
		if (delegate != null) {
			delegate.onRulesetVersionLoaded(ruleSetVersion);
		}
	}

	public void setDelegate(RuleListener delegate) {
		if (delegate != null) {
			this.delegate = delegate;
		}
	}
}
