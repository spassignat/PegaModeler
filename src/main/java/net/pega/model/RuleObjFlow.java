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
import java.util.Map;

public class RuleObjFlow extends RuleObj {
	String pxCommitDateTime;
	String pxCreateDateTime;
	String pxCreateSystemID;
	String[] pxDataReferences;
	String pxHostId;
	String pxInsId;
	String pxInsName;
	String pxInstanceLockedBy;
	String pxInstanceLockedCreateDateTime;
	String pxInstanceLockedKey;
	String[] pxNamedPageReferences;
	String pxOriginalCreateDateTime;
	String pxOriginalCreateOpName;
	String pxOriginalCreateOperator;
	String pxOriginalCreateSystemID;
	String pxSaveDateTime;
	String pxUpdateDateTime;
	String pxUpdateOpName;
	String pxUpdateOperator;
	String pxUpdateSystemID;
	boolean pyAlphaSortLocalActions;
	String pyAssociatedImage;
	boolean pyBaseRule;
	CallParam pyCallParams;
	boolean pyCanCreateWorkObject;
	String pyCanCreateWorkObjectOld;
	boolean pyCanStartInteractively;
	String pyChannelName;
	String pyCircumstanceDate;
	String pyCircumstanceProp;
	String pyClassName;
	String pyCircumstanceDateProp;
	String pyCircumstanceVal;
	String pyShowJavaWindowName;
	String pyMethodStatus;
	String pxInstanceLockedRuleSetVersion;
	String pxInstanceLockedDateTime;
	String pyJavaGenerateVersion;
	String pyForecasterAvailable;
	String pyTaskStatusXml;
	String pyWorkPartiesRule;
	String pyConfirmChoice;
	String pzImplVersion;
	String pyRuleFormStatus;
	String pyOriginalRFB;
	EmbedReferenceRule[] pxRuleReferences;

	public EmbedReferenceRule[] getPxRuleReferences() {
		return pxRuleReferences;
	}

	public void setPxRuleReferences(EmbedReferenceRule[] pxRuleReferences) {
		this.pxRuleReferences = pxRuleReferences;
	}

	public String getPyCircumstanceDateProp() {
		return pyCircumstanceDateProp;
	}

	public void setPyCircumstanceDateProp(String pyCircumstanceDateProp) {
		this.pyCircumstanceDateProp = pyCircumstanceDateProp;
	}

	public String getPyCircumstanceVal() {
		return pyCircumstanceVal;
	}

	public void setPyCircumstanceVal(String pyCircumstanceVal) {
		this.pyCircumstanceVal = pyCircumstanceVal;
	}

	public String getPyShowJavaWindowName() {
		return pyShowJavaWindowName;
	}

	public void setPyShowJavaWindowName(String pyShowJavaWindowName) {
		this.pyShowJavaWindowName = pyShowJavaWindowName;
	}

	public String getPyMethodStatus() {
		return pyMethodStatus;
	}

	public void setPyMethodStatus(String pyMethodStatus) {
		this.pyMethodStatus = pyMethodStatus;
	}

	public String getPxInstanceLockedRuleSetVersion() {
		return pxInstanceLockedRuleSetVersion;
	}

	public void setPxInstanceLockedRuleSetVersion(String pxInstanceLockedRuleSetVersion) {
		this.pxInstanceLockedRuleSetVersion = pxInstanceLockedRuleSetVersion;
	}

	public String getPxInstanceLockedDateTime() {
		return pxInstanceLockedDateTime;
	}

	public void setPxInstanceLockedDateTime(String pxInstanceLockedDateTime) {
		this.pxInstanceLockedDateTime = pxInstanceLockedDateTime;
	}

	public String getPyJavaGenerateVersion() {
		return pyJavaGenerateVersion;
	}

	public void setPyJavaGenerateVersion(String pyJavaGenerateVersion) {
		this.pyJavaGenerateVersion = pyJavaGenerateVersion;
	}

	public String getPyForecasterAvailable() {
		return pyForecasterAvailable;
	}

	public void setPyForecasterAvailable(String pyForecasterAvailable) {
		this.pyForecasterAvailable = pyForecasterAvailable;
	}

	public String getPyTaskStatusXml() {
		return pyTaskStatusXml;
	}

	public void setPyTaskStatusXml(String pyTaskStatusXml) {
		this.pyTaskStatusXml = pyTaskStatusXml;
	}

	public String getPyWorkPartiesRule() {
		return pyWorkPartiesRule;
	}

	public void setPyWorkPartiesRule(String pyWorkPartiesRule) {
		this.pyWorkPartiesRule = pyWorkPartiesRule;
	}

	public String getPyConfirmChoice() {
		return pyConfirmChoice;
	}

	public void setPyConfirmChoice(String pyConfirmChoice) {
		this.pyConfirmChoice = pyConfirmChoice;
	}

	public String getPzImplVersion() {
		return pzImplVersion;
	}

	public void setPzImplVersion(String pzImplVersion) {
		this.pzImplVersion = pzImplVersion;
	}

	public String getPyRuleFormStatus() {
		return pyRuleFormStatus;
	}

	public void setPyRuleFormStatus(String pyRuleFormStatus) {
		this.pyRuleFormStatus = pyRuleFormStatus;
	}

	public String getPyOriginalRFB() {
		return pyOriginalRFB;
	}

	public void setPyOriginalRFB(String pyOriginalRFB) {
		this.pyOriginalRFB = pyOriginalRFB;
	}

	public String getPxInstanceLockedRuleSet() {
		return pxInstanceLockedRuleSet;
	}

	public void setPxInstanceLockedRuleSet(String pxInstanceLockedRuleSet) {
		this.pxInstanceLockedRuleSet = pxInstanceLockedRuleSet;
	}

	public boolean isPyPurpose() {
		return pyPurpose;
	}

	public void setPyPurpose(boolean pyPurpose) {
		this.pyPurpose = pyPurpose;
	}

	public boolean isPyDraftModeON() {
		return pyDraftModeON;
	}

	public void setPyDraftModeON(boolean pyDraftModeON) {
		this.pyDraftModeON = pyDraftModeON;
	}

	String pxInstanceLockedRuleSet;
	int pyCoveredByCount;
	boolean pyPurpose;
	boolean pyDeferErrors;
	boolean pyDraftModeON;
	boolean pyDoHarvesting;
	ExpressionBuilder pyExpressionGadget;
	String pyFlowName;
	RuleObjFlow pyFlowPage;
	EmbedRuleObjFlowFromTasks[] pyFromTasks;
	String pyInterface;
	String pyJavaClassName;
	String pyJavaStream;
	String pyLabelOld;
	Map<String,?> pyToolBarSettings;

	public Map<String, ?> getPyToolBarSettings() {
		return pyToolBarSettings;
	}

	public String getPyScreenFlowHarness() {
		return pyScreenFlowHarness;
	}

	public void setPyScreenFlowHarness(String pyScreenFlowHarness) {
		this.pyScreenFlowHarness = pyScreenFlowHarness;
	}

	public void setPyToolBarSettings(Map<String, ?> pyToolBarSettings) {
		this.pyToolBarSettings = pyToolBarSettings;
	}

	String pyScreenFlowHarness;
	boolean pyLaunchOnReentry;
	EmbedRuleObjFlowFromTasks[] pyLocalActions;
	String pyModelerDefaultClickMode;
	boolean pyModelerGuideModeON;
	boolean pyModifiedWithPM;
	String pyModifiers;
	boolean pyNextAssignment;
	boolean pyNextAssignmentWorkBasket;
	String pyOverlayView;
	String pyPageOrientation;
	EmbedPagesAndClasses[] pyPagesAndClasses;
	List<EmbedMethodParam> pyParameters;
	boolean pyPlaceGlobalLocalsAtTop;
	boolean pyPostVisio2000Saved;
	RuleObjFlow pyPreviousValues;
	EmbedRulePrivilegeSecurity[] pyPrivilegeList;
	String pyProcessModel;
	int pyRegenStartingFlows;
	String pyRelativePath;
	String pyRuleAvailable;
	String pyRuleFormStatusTime;
	String pyRuleParamsStreamName;
	String pyRuleSet;
	String pyRuleSetVersion;
	String pySLAType;
	List<ShapeNode> pyShapeNodes;
	String pyShowGrid;
	boolean pySkipNewHarness;
	String pySkipOrAllowType;
	boolean pySortDateCircumWithinRSMajor;
	String pyStartActivity;
	String pyStartingHarnessOld;
	String pyStartingModelOld;
	String[] pyStencils;
	String pyStreamName;
	String pySubCategory;
	boolean pySystemFlow;
	String pyUsage;
	List<RuleObjFlowAction> pyUseCaseLinks;
	String pyValueChanged;
	int pyVisioDocVersion;
	String pyVisioJpeg;
	String pyVisioRFB;
	String pyWhenToSkip;
	List<EmbededRuleWhen> pyWhensList;
	String pyWorkClass;
	String pyXMLSignature;
	boolean pzCaseTypeExistsInCoverClass;
	int pzChecksum;
	boolean pzHideCaseCreationSettings;
	int pzIndexCount;
	String pzOriginalInstanceKey;
	boolean pzRuleParametersShowSkills;
	int pzRuleSetVersionMajor;
	int pzRuleSetVersionMinor;
	int pzRuleSetVersionPatch;

	public String getPxCommitDateTime() {
		return pxCommitDateTime;
	}

	public void setPxCommitDateTime(String pxCommitDateTime) {
		this.pxCommitDateTime = pxCommitDateTime;
	}

	public String getPxCreateDateTime() {
		return pxCreateDateTime;
	}

	public void setPxCreateDateTime(String pxCreateDateTime) {
		this.pxCreateDateTime = pxCreateDateTime;
	}

	public String getPxCreateSystemID() {
		return pxCreateSystemID;
	}

	public void setPxCreateSystemID(String pxCreateSystemID) {
		this.pxCreateSystemID = pxCreateSystemID;
	}

	public String[] getPxDataReferences() {
		return pxDataReferences;
	}

	public void setPxDataReferences(String[] pxDataReferences) {
		this.pxDataReferences = pxDataReferences;
	}

	public String getPxHostId() {
		return pxHostId;
	}

	public void setPxHostId(String pxHostId) {
		this.pxHostId = pxHostId;
	}

	public String getPxInsId() {
		return pxInsId;
	}

	public void setPxInsId(String pxInsId) {
		this.pxInsId = pxInsId;
	}

	public String getPxInsName() {
		return pxInsName;
	}

	public void setPxInsName(String pxInsName) {
		this.pxInsName = pxInsName;
	}

	public String getPxInstanceLockedBy() {
		return pxInstanceLockedBy;
	}

	public void setPxInstanceLockedBy(String pxInstanceLockedBy) {
		this.pxInstanceLockedBy = pxInstanceLockedBy;
	}

	public String getPxInstanceLockedCreateDateTime() {
		return pxInstanceLockedCreateDateTime;
	}

	public void setPxInstanceLockedCreateDateTime(String pxInstanceLockedCreateDateTime) {
		this.pxInstanceLockedCreateDateTime = pxInstanceLockedCreateDateTime;
	}

	public String getPxInstanceLockedKey() {
		return pxInstanceLockedKey;
	}

	public void setPxInstanceLockedKey(String pxInstanceLockedKey) {
		this.pxInstanceLockedKey = pxInstanceLockedKey;
	}

	public String[] getPxNamedPageReferences() {
		return pxNamedPageReferences;
	}

	public void setPxNamedPageReferences(String[] pxNamedPageReferences) {
		this.pxNamedPageReferences = pxNamedPageReferences;
	}

	public String getPxOriginalCreateDateTime() {
		return pxOriginalCreateDateTime;
	}

	public void setPxOriginalCreateDateTime(String pxOriginalCreateDateTime) {
		this.pxOriginalCreateDateTime = pxOriginalCreateDateTime;
	}

	public String getPxOriginalCreateOpName() {
		return pxOriginalCreateOpName;
	}

	public void setPxOriginalCreateOpName(String pxOriginalCreateOpName) {
		this.pxOriginalCreateOpName = pxOriginalCreateOpName;
	}

	public String getPxOriginalCreateOperator() {
		return pxOriginalCreateOperator;
	}

	public void setPxOriginalCreateOperator(String pxOriginalCreateOperator) {
		this.pxOriginalCreateOperator = pxOriginalCreateOperator;
	}

	public String getPxOriginalCreateSystemID() {
		return pxOriginalCreateSystemID;
	}

	public void setPxOriginalCreateSystemID(String pxOriginalCreateSystemID) {
		this.pxOriginalCreateSystemID = pxOriginalCreateSystemID;
	}

	public String getPxSaveDateTime() {
		return pxSaveDateTime;
	}

	public void setPxSaveDateTime(String pxSaveDateTime) {
		this.pxSaveDateTime = pxSaveDateTime;
	}

	public String getPxUpdateDateTime() {
		return pxUpdateDateTime;
	}

	public void setPxUpdateDateTime(String pxUpdateDateTime) {
		this.pxUpdateDateTime = pxUpdateDateTime;
	}

	public String getPxUpdateOpName() {
		return pxUpdateOpName;
	}

	public void setPxUpdateOpName(String pxUpdateOpName) {
		this.pxUpdateOpName = pxUpdateOpName;
	}

	public String getPxUpdateOperator() {
		return pxUpdateOperator;
	}

	public void setPxUpdateOperator(String pxUpdateOperator) {
		this.pxUpdateOperator = pxUpdateOperator;
	}

	public String getPxUpdateSystemID() {
		return pxUpdateSystemID;
	}

	public void setPxUpdateSystemID(String pxUpdateSystemID) {
		this.pxUpdateSystemID = pxUpdateSystemID;
	}

	public String getPyAssociatedImage() {
		return pyAssociatedImage;
	}

	public void setPyAssociatedImage(String pyAssociatedImage) {
		this.pyAssociatedImage = pyAssociatedImage;
	}

	public CallParam getPyCallParams() {
		return pyCallParams;
	}

	public void setPyCallParams(CallParam pyCallParams) {
		this.pyCallParams = pyCallParams;
	}

	public String getPyCanCreateWorkObjectOld() {
		return pyCanCreateWorkObjectOld;
	}

	public void setPyCanCreateWorkObjectOld(String pyCanCreateWorkObjectOld) {
		this.pyCanCreateWorkObjectOld = pyCanCreateWorkObjectOld;
	}

	public String getPyChannelName() {
		return pyChannelName;
	}

	public void setPyChannelName(String pyChannelName) {
		this.pyChannelName = pyChannelName;
	}

	public String getPyCircumstanceDate() {
		return pyCircumstanceDate;
	}

	public void setPyCircumstanceDate(String pyCircumstanceDate) {
		this.pyCircumstanceDate = pyCircumstanceDate;
	}

	public String getPyCircumstanceProp() {
		return pyCircumstanceProp;
	}

	public void setPyCircumstanceProp(String pyCircumstanceProp) {
		this.pyCircumstanceProp = pyCircumstanceProp;
	}

	@Override
	public String getPyClassName() {
		return pyClassName;
	}

	@Override
	public void setPyClassName(String pyClassName) {
		this.pyClassName = pyClassName;
	}

	public int getPyCoveredByCount() {
		return pyCoveredByCount;
	}

	public void setPyCoveredByCount(int pyCoveredByCount) {
		this.pyCoveredByCount = pyCoveredByCount;
	}

	public ExpressionBuilder getPyExpressionGadget() {
		return pyExpressionGadget;
	}

	public void setPyExpressionGadget(ExpressionBuilder pyExpressionGadget) {
		this.pyExpressionGadget = pyExpressionGadget;
	}

	public String getPyFlowName() {
		return pyFlowName;
	}

	public void setPyFlowName(String pyFlowName) {
		this.pyFlowName = pyFlowName;
	}

	public RuleObjFlow getPyFlowPage() {
		return pyFlowPage;
	}

	public void setPyFlowPage(RuleObjFlow pyFlowPage) {
		this.pyFlowPage = pyFlowPage;
	}

	public EmbedRuleObjFlowFromTasks[] getPyFromTasks() {
		return pyFromTasks;
	}

	public void setPyFromTasks(EmbedRuleObjFlowFromTasks[] pyFromTasks) {
		this.pyFromTasks = pyFromTasks;
	}

	public String getPyInterface() {
		return pyInterface;
	}

	public void setPyInterface(String pyInterface) {
		this.pyInterface = pyInterface;
	}

	public String getPyJavaClassName() {
		return pyJavaClassName;
	}

	public void setPyJavaClassName(String pyJavaClassName) {
		this.pyJavaClassName = pyJavaClassName;
	}

	public String getPyJavaStream() {
		return pyJavaStream;
	}

	public void setPyJavaStream(String pyJavaStream) {
		this.pyJavaStream = pyJavaStream;
	}

	public String getPyLabelOld() {
		return pyLabelOld;
	}

	public void setPyLabelOld(String pyLabelOld) {
		this.pyLabelOld = pyLabelOld;
	}

	public EmbedRuleObjFlowFromTasks[] getPyLocalActions() {
		return pyLocalActions;
	}

	public void setPyLocalActions(EmbedRuleObjFlowFromTasks[] pyLocalActions) {
		this.pyLocalActions = pyLocalActions;
	}

	public String getPyModelerDefaultClickMode() {
		return pyModelerDefaultClickMode;
	}

	public void setPyModelerDefaultClickMode(String pyModelerDefaultClickMode) {
		this.pyModelerDefaultClickMode = pyModelerDefaultClickMode;
	}

	public String getPyModifiers() {
		return pyModifiers;
	}

	public void setPyModifiers(String pyModifiers) {
		this.pyModifiers = pyModifiers;
	}

	public String getPyOverlayView() {
		return pyOverlayView;
	}

	public void setPyOverlayView(String pyOverlayView) {
		this.pyOverlayView = pyOverlayView;
	}

	public String getPyPageOrientation() {
		return pyPageOrientation;
	}

	public void setPyPageOrientation(String pyPageOrientation) {
		this.pyPageOrientation = pyPageOrientation;
	}

	public EmbedPagesAndClasses[] getPyPagesAndClasses() {
		return pyPagesAndClasses;
	}

	public void setPyPagesAndClasses(EmbedPagesAndClasses[] pyPagesAndClasses) {
		this.pyPagesAndClasses = pyPagesAndClasses;
	}

	public List<EmbedMethodParam> getPyParameters() {
		return pyParameters;
	}

	public void setPyParameters(List<EmbedMethodParam> pyParameters) {
		this.pyParameters = pyParameters;
	}

	public RuleObjFlow getPyPreviousValues() {
		return pyPreviousValues;
	}

	public void setPyPreviousValues(RuleObjFlow pyPreviousValues) {
		this.pyPreviousValues = pyPreviousValues;
	}

	public EmbedRulePrivilegeSecurity[] getPyPrivilegeList() {
		return pyPrivilegeList;
	}

	public void setPyPrivilegeList(EmbedRulePrivilegeSecurity[] pyPrivilegeList) {
		this.pyPrivilegeList = pyPrivilegeList;
	}

	public String getPyProcessModel() {
		return pyProcessModel;
	}

	public void setPyProcessModel(String pyProcessModel) {
		this.pyProcessModel = pyProcessModel;
	}

	public int getPyRegenStartingFlows() {
		return pyRegenStartingFlows;
	}

	public void setPyRegenStartingFlows(int pyRegenStartingFlows) {
		this.pyRegenStartingFlows = pyRegenStartingFlows;
	}

	public String getPyRelativePath() {
		return pyRelativePath;
	}

	public void setPyRelativePath(String pyRelativePath) {
		this.pyRelativePath = pyRelativePath;
	}

	public String getPyRuleAvailable() {
		return pyRuleAvailable;
	}

	public void setPyRuleAvailable(String pyRuleAvailable) {
		this.pyRuleAvailable = pyRuleAvailable;
	}

	public String getPyRuleFormStatusTime() {
		return pyRuleFormStatusTime;
	}

	public void setPyRuleFormStatusTime(String pyRuleFormStatusTime) {
		this.pyRuleFormStatusTime = pyRuleFormStatusTime;
	}

	public String getPyRuleParamsStreamName() {
		return pyRuleParamsStreamName;
	}

	public void setPyRuleParamsStreamName(String pyRuleParamsStreamName) {
		this.pyRuleParamsStreamName = pyRuleParamsStreamName;
	}

	@Override
	public String getPyRuleSet() {
		return pyRuleSet;
	}

	@Override
	public void setPyRuleSet(String pyRuleSet) {
		this.pyRuleSet = pyRuleSet;
	}

	@Override
	public String getPyRuleSetVersion() {
		return pyRuleSetVersion;
	}

	@Override
	public void setPyRuleSetVersion(String pyRuleSetVersion) {
		this.pyRuleSetVersion = pyRuleSetVersion;
	}

	public String getPySLAType() {
		return pySLAType;
	}

	public void setPySLAType(String pySLAType) {
		this.pySLAType = pySLAType;
	}

	public List<ShapeNode> getPyShapeNodes() {
		return pyShapeNodes;
	}

	public void setPyShapeNodes(List<ShapeNode> pyShapeNodes) {
		this.pyShapeNodes = pyShapeNodes;
	}

	public String getPyShowGrid() {
		return pyShowGrid;
	}

	public void setPyShowGrid(String pyShowGrid) {
		this.pyShowGrid = pyShowGrid;
	}

	public String getPySkipOrAllowType() {
		return pySkipOrAllowType;
	}

	public void setPySkipOrAllowType(String pySkipOrAllowType) {
		this.pySkipOrAllowType = pySkipOrAllowType;
	}

	public String getPyStartActivity() {
		return pyStartActivity;
	}

	public void setPyStartActivity(String pyStartActivity) {
		this.pyStartActivity = pyStartActivity;
	}

	public String getPyStartingHarnessOld() {
		return pyStartingHarnessOld;
	}

	public void setPyStartingHarnessOld(String pyStartingHarnessOld) {
		this.pyStartingHarnessOld = pyStartingHarnessOld;
	}

	public String getPyStartingModelOld() {
		return pyStartingModelOld;
	}

	public void setPyStartingModelOld(String pyStartingModelOld) {
		this.pyStartingModelOld = pyStartingModelOld;
	}

	public String[] getPyStencils() {
		return pyStencils;
	}

	public void setPyStencils(String[] pyStencils) {
		this.pyStencils = pyStencils;
	}

	public String getPyStreamName() {
		return pyStreamName;
	}

	public void setPyStreamName(String pyStreamName) {
		this.pyStreamName = pyStreamName;
	}

	public String getPySubCategory() {
		return pySubCategory;
	}

	public void setPySubCategory(String pySubCategory) {
		this.pySubCategory = pySubCategory;
	}

	public String getPyUsage() {
		return pyUsage;
	}

	public void setPyUsage(String pyUsage) {
		this.pyUsage = pyUsage;
	}

	public List<RuleObjFlowAction> getPyUseCaseLinks() {
		return pyUseCaseLinks;
	}

	public void setPyUseCaseLinks(List<RuleObjFlowAction> pyUseCaseLinks) {
		this.pyUseCaseLinks = pyUseCaseLinks;
	}

	public String getPyValueChanged() {
		return pyValueChanged;
	}

	public void setPyValueChanged(String pyValueChanged) {
		this.pyValueChanged = pyValueChanged;
	}

	public int getPyVisioDocVersion() {
		return pyVisioDocVersion;
	}

	public void setPyVisioDocVersion(int pyVisioDocVersion) {
		this.pyVisioDocVersion = pyVisioDocVersion;
	}

	public String getPyVisioJpeg() {
		return pyVisioJpeg;
	}

	public void setPyVisioJpeg(String pyVisioJpeg) {
		this.pyVisioJpeg = pyVisioJpeg;
	}

	public String getPyVisioRFB() {
		return pyVisioRFB;
	}

	public void setPyVisioRFB(String pyVisioRFB) {
		this.pyVisioRFB = pyVisioRFB;
	}

	public String getPyWhenToSkip() {
		return pyWhenToSkip;
	}

	public void setPyWhenToSkip(String pyWhenToSkip) {
		this.pyWhenToSkip = pyWhenToSkip;
	}

	public List<EmbededRuleWhen> getPyWhensList() {
		return pyWhensList;
	}

	public void setPyWhensList(List<EmbededRuleWhen> pyWhensList) {
		this.pyWhensList = pyWhensList;
	}

	public String getPyWorkClass() {
		return pyWorkClass;
	}

	public void setPyWorkClass(String pyWorkClass) {
		this.pyWorkClass = pyWorkClass;
	}

	public String getPyXMLSignature() {
		return pyXMLSignature;
	}

	public void setPyXMLSignature(String pyXMLSignature) {
		this.pyXMLSignature = pyXMLSignature;
	}

	public int getPzChecksum() {
		return pzChecksum;
	}

	public void setPzChecksum(int pzChecksum) {
		this.pzChecksum = pzChecksum;
	}

	public int getPzIndexCount() {
		return pzIndexCount;
	}

	public void setPzIndexCount(int pzIndexCount) {
		this.pzIndexCount = pzIndexCount;
	}

	public String getPzOriginalInstanceKey() {
		return pzOriginalInstanceKey;
	}

	public void setPzOriginalInstanceKey(String pzOriginalInstanceKey) {
		this.pzOriginalInstanceKey = pzOriginalInstanceKey;
	}

	public int getPzRuleSetVersionMajor() {
		return pzRuleSetVersionMajor;
	}

	public void setPzRuleSetVersionMajor(int pzRuleSetVersionMajor) {
		this.pzRuleSetVersionMajor = pzRuleSetVersionMajor;
	}

	public int getPzRuleSetVersionMinor() {
		return pzRuleSetVersionMinor;
	}

	public void setPzRuleSetVersionMinor(int pzRuleSetVersionMinor) {
		this.pzRuleSetVersionMinor = pzRuleSetVersionMinor;
	}

	public int getPzRuleSetVersionPatch() {
		return pzRuleSetVersionPatch;
	}

	public void setPzRuleSetVersionPatch(int pzRuleSetVersionPatch) {
		this.pzRuleSetVersionPatch = pzRuleSetVersionPatch;
	}

	public boolean isPyAlphaSortLocalActions() {
		return pyAlphaSortLocalActions;
	}

	public void setPyAlphaSortLocalActions(boolean pyAlphaSortLocalActions) {
		this.pyAlphaSortLocalActions = pyAlphaSortLocalActions;
	}

	@Override
	public boolean isPyBaseRule() {
		return pyBaseRule;
	}

	@Override
	public void setPyBaseRule(boolean pyBaseRule) {
		this.pyBaseRule = pyBaseRule;
	}

	public boolean isPyCanCreateWorkObject() {
		return pyCanCreateWorkObject;
	}

	public void setPyCanCreateWorkObject(boolean pyCanCreateWorkObject) {
		this.pyCanCreateWorkObject = pyCanCreateWorkObject;
	}

	public boolean isPyCanStartInteractively() {
		return pyCanStartInteractively;
	}

	public void setPyCanStartInteractively(boolean pyCanStartInteractively) {
		this.pyCanStartInteractively = pyCanStartInteractively;
	}

	public boolean isPyDeferErrors() {
		return pyDeferErrors;
	}

	public void setPyDeferErrors(boolean pyDeferErrors) {
		this.pyDeferErrors = pyDeferErrors;
	}

	public boolean isPyDoHarvesting() {
		return pyDoHarvesting;
	}

	public void setPyDoHarvesting(boolean pyDoHarvesting) {
		this.pyDoHarvesting = pyDoHarvesting;
	}

	public boolean isPyLaunchOnReentry() {
		return pyLaunchOnReentry;
	}

	public void setPyLaunchOnReentry(boolean pyLaunchOnReentry) {
		this.pyLaunchOnReentry = pyLaunchOnReentry;
	}

	public boolean isPyModelerGuideModeON() {
		return pyModelerGuideModeON;
	}

	public void setPyModelerGuideModeON(boolean pyModelerGuideModeON) {
		this.pyModelerGuideModeON = pyModelerGuideModeON;
	}

	public boolean isPyModifiedWithPM() {
		return pyModifiedWithPM;
	}

	public void setPyModifiedWithPM(boolean pyModifiedWithPM) {
		this.pyModifiedWithPM = pyModifiedWithPM;
	}

	public boolean isPyNextAssignment() {
		return pyNextAssignment;
	}

	public void setPyNextAssignment(boolean pyNextAssignment) {
		this.pyNextAssignment = pyNextAssignment;
	}

	public boolean isPyNextAssignmentWorkBasket() {
		return pyNextAssignmentWorkBasket;
	}

	public void setPyNextAssignmentWorkBasket(boolean pyNextAssignmentWorkBasket) {
		this.pyNextAssignmentWorkBasket = pyNextAssignmentWorkBasket;
	}

	public boolean isPyPlaceGlobalLocalsAtTop() {
		return pyPlaceGlobalLocalsAtTop;
	}

	public void setPyPlaceGlobalLocalsAtTop(boolean pyPlaceGlobalLocalsAtTop) {
		this.pyPlaceGlobalLocalsAtTop = pyPlaceGlobalLocalsAtTop;
	}

	public boolean isPyPostVisio2000Saved() {
		return pyPostVisio2000Saved;
	}

	public void setPyPostVisio2000Saved(boolean pyPostVisio2000Saved) {
		this.pyPostVisio2000Saved = pyPostVisio2000Saved;
	}

	public boolean isPySkipNewHarness() {
		return pySkipNewHarness;
	}

	public void setPySkipNewHarness(boolean pySkipNewHarness) {
		this.pySkipNewHarness = pySkipNewHarness;
	}

	public boolean isPySortDateCircumWithinRSMajor() {
		return pySortDateCircumWithinRSMajor;
	}

	public void setPySortDateCircumWithinRSMajor(boolean pySortDateCircumWithinRSMajor) {
		this.pySortDateCircumWithinRSMajor = pySortDateCircumWithinRSMajor;
	}

	public boolean isPySystemFlow() {
		return pySystemFlow;
	}

	public void setPySystemFlow(boolean pySystemFlow) {
		this.pySystemFlow = pySystemFlow;
	}

	public boolean isPzCaseTypeExistsInCoverClass() {
		return pzCaseTypeExistsInCoverClass;
	}

	public void setPzCaseTypeExistsInCoverClass(boolean pzCaseTypeExistsInCoverClass) {
		this.pzCaseTypeExistsInCoverClass = pzCaseTypeExistsInCoverClass;
	}

	public boolean isPzHideCaseCreationSettings() {
		return pzHideCaseCreationSettings;
	}

	public void setPzHideCaseCreationSettings(boolean pzHideCaseCreationSettings) {
		this.pzHideCaseCreationSettings = pzHideCaseCreationSettings;
	}

	public boolean isPzRuleParametersShowSkills() {
		return pzRuleParametersShowSkills;
	}

	public void setPzRuleParametersShowSkills(boolean pzRuleParametersShowSkills) {
		this.pzRuleParametersShowSkills = pzRuleParametersShowSkills;
	}
}
