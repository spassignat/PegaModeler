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
 * PARTICULAR PURPOSE AND NON INFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package net.pega.model;

import java.util.List;

public class AlternateStage extends Rule {
	String pyChannelMenuLabel;
	String pyChildCaseStatus;
	boolean pyCleanupProcess;
	String[] pyExposedStatusList;
	boolean pyIsTerminalStage;
	CaseAction[] pyLocalActions;
	List<RuleObjProcess> pyOptionalProcesses;
	List<RuleObjProcess> pyProcesses;
	String[] pyRequiredCategories;
	String pyStageID;
	String pyStageName;
	String pyStageTransition;
	String pyStageWorkStatus;

	public String getPyChannelMenuLabel() {
		return pyChannelMenuLabel;
	}

	public void setPyChannelMenuLabel(String pyChannelMenuLabel) {
		this.pyChannelMenuLabel = pyChannelMenuLabel;
	}

	public String getPyChildCaseStatus() {
		return pyChildCaseStatus;
	}

	public void setPyChildCaseStatus(String pyChildCaseStatus) {
		this.pyChildCaseStatus = pyChildCaseStatus;
	}

	public String[] getPyExposedStatusList() {
		return pyExposedStatusList;
	}

	public void setPyExposedStatusList(String[] pyExposedStatusList) {
		this.pyExposedStatusList = pyExposedStatusList;
	}

	public CaseAction[] getPyLocalActions() {
		return pyLocalActions;
	}

	public void setPyLocalActions(CaseAction[] pyLocalActions) {
		this.pyLocalActions = pyLocalActions;
	}

	public List<RuleObjProcess> getPyOptionalProcesses() {
		return pyOptionalProcesses;
	}

	public void setPyOptionalProcesses(List<RuleObjProcess> pyOptionalProcesses) {
		this.pyOptionalProcesses = pyOptionalProcesses;
	}

	public List<RuleObjProcess> getPyProcesses() {
		return pyProcesses;
	}

	public void setPyProcesses(List<RuleObjProcess> pyProcesses) {
		this.pyProcesses = pyProcesses;
	}

	public String[] getPyRequiredCategories() {
		return pyRequiredCategories;
	}

	public void setPyRequiredCategories(String[] pyRequiredCategories) {
		this.pyRequiredCategories = pyRequiredCategories;
	}

	public String getPyStageID() {
		return pyStageID;
	}

	public void setPyStageID(String pyStageID) {
		this.pyStageID = pyStageID;
	}

	public String getPyStageName() {
		return pyStageName;
	}

	public void setPyStageName(String pyStageName) {
		this.pyStageName = pyStageName;
	}

	public String getPyStageTransition() {
		return pyStageTransition;
	}

	public void setPyStageTransition(String pyStageTransition) {
		this.pyStageTransition = pyStageTransition;
	}

	public String getPyStageWorkStatus() {
		return pyStageWorkStatus;
	}

	public void setPyStageWorkStatus(String pyStageWorkStatus) {
		this.pyStageWorkStatus = pyStageWorkStatus;
	}

	public boolean isPyCleanupProcess() {
		return pyCleanupProcess;
	}

	public void setPyCleanupProcess(boolean pyCleanupProcess) {
		this.pyCleanupProcess = pyCleanupProcess;
	}

	public boolean isPyIsTerminalStage() {
		return pyIsTerminalStage;
	}

	public void setPyIsTerminalStage(boolean pyIsTerminalStage) {
		this.pyIsTerminalStage = pyIsTerminalStage;
	}
}
