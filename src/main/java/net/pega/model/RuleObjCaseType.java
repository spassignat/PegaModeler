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
package net.pega.model;

import java.util.List;

public class RuleObjCaseType extends RuleObj {
	List<AlternateStage> pyAlternateStages;
	List<RuleObjProcess> pyCaseOptionalProcesses;
	List<CaseAction> pyCaseWideLocalActions;
	String pyMethodStatus;
	String pyPurpose;
	List<EmbedStage> pyStages;
	String pyStreamName;
	public RuleObjCaseType() {
	}

	public List<AlternateStage> getPyAlternateStages() {
		return pyAlternateStages;
	}

	public void setPyAlternateStages(List<AlternateStage> pyAlternateStages) {
		this.pyAlternateStages = pyAlternateStages;
	}

	public List<RuleObjProcess> getPyCaseOptionalProcesses() {
		return pyCaseOptionalProcesses;
	}

	public void setPyCaseOptionalProcesses(List<RuleObjProcess> pyCaseOptionalProcesses) {
		this.pyCaseOptionalProcesses = pyCaseOptionalProcesses;
	}

	public List<CaseAction> getPyCaseWideLocalActions() {
		return pyCaseWideLocalActions;
	}

	public void setPyCaseWideLocalActions(List<CaseAction> pyCaseWideLocalActions) {
		this.pyCaseWideLocalActions = pyCaseWideLocalActions;
	}

	public String getPyMethodStatus() {
		return pyMethodStatus;
	}

	public void setPyMethodStatus(String pyMethodStatus) {
		this.pyMethodStatus = pyMethodStatus;
	}

	public String getPyPurpose() {
		return pyPurpose;
	}

	public void setPyPurpose(String pyPurpose) {
		this.pyPurpose = pyPurpose;
	}

	public List<EmbedStage> getPyStages() {
		return pyStages;
	}

	public void setPyStages(List<EmbedStage> pyEmbedStages) {
		this.pyStages = pyEmbedStages;
	}

	public String getPyStreamName() {
		return pyStreamName;
	}

	public void setPyStreamName(String pyStreamName) {
		this.pyStreamName = pyStreamName;
	}
}
