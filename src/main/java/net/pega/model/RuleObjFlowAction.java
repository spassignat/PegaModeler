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

public class RuleObjFlowAction extends RuleObj {
	String pxLinkedRefTo;
	String pyImplementationType;
	String pyTaskName;
	String pyApplicationName;
	String pyUseCaseName;
	String pyWorkTypeName;
	String pxLinkedClassTo;
	String pxObjClass;
	String pyLinkedClassName;
	String pyTaskID;
	String pyFlowKey;
	String pyLabel;
	String pyFlowType;

	public String getPxLinkedRefTo() {
		return pxLinkedRefTo;
	}

	public void setPxLinkedRefTo(String pxLinkedRefTo) {
		this.pxLinkedRefTo = pxLinkedRefTo;
	}

	public String getPyImplementationType() {
		return pyImplementationType;
	}

	public void setPyImplementationType(String pyImplementationType) {
		this.pyImplementationType = pyImplementationType;
	}

	public String getPyTaskName() {
		return pyTaskName;
	}

	public void setPyTaskName(String pyTaskName) {
		this.pyTaskName = pyTaskName;
	}

	public String getPyApplicationName() {
		return pyApplicationName;
	}

	public void setPyApplicationName(String pyApplicationName) {
		this.pyApplicationName = pyApplicationName;
	}

	public String getPyUseCaseName() {
		return pyUseCaseName;
	}

	public void setPyUseCaseName(String pyUseCaseName) {
		this.pyUseCaseName = pyUseCaseName;
	}

	public String getPyWorkTypeName() {
		return pyWorkTypeName;
	}

	public void setPyWorkTypeName(String pyWorkTypeName) {
		this.pyWorkTypeName = pyWorkTypeName;
	}

	public String getPxLinkedClassTo() {
		return pxLinkedClassTo;
	}

	public void setPxLinkedClassTo(String pxLinkedClassTo) {
		this.pxLinkedClassTo = pxLinkedClassTo;
	}

	@Override
	public String getPxObjClass() {
		return pxObjClass;
	}

	@Override
	public void setPxObjClass(String pxObjClass) {
		this.pxObjClass = pxObjClass;
	}

	public String getPyLinkedClassName() {
		return pyLinkedClassName;
	}

	public void setPyLinkedClassName(String pyLinkedClassName) {
		this.pyLinkedClassName = pyLinkedClassName;
	}

	public String getPyTaskID() {
		return pyTaskID;
	}

	public void setPyTaskID(String pyTaskID) {
		this.pyTaskID = pyTaskID;
	}

	public String getPyFlowKey() {
		return pyFlowKey;
	}

	public void setPyFlowKey(String pyFlowKey) {
		this.pyFlowKey = pyFlowKey;
	}

	@Override
	public String getPyLabel() {
		return pyLabel;
	}

	@Override
	public void setPyLabel(String pyLabel) {
		this.pyLabel = pyLabel;
	}

	public String getPyFlowType() {
		return pyFlowType;
	}

	public void setPyFlowType(String pyFlowType) {
		this.pyFlowType = pyFlowType;
	}
}
