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

public class ShapeNode extends Rule {
	String pyCheckedOutOrLocked;
	String pyDescription;
	String pyHelpContentID;
	String pyMoreInformationContentID;
	String pyShapeID;
	String pyShapeIcon;
	String pyShapeImpl;
	String pyShapeName;
	String pyShapeType;
	String pyStepPlaceHolder;
	int pzConversationIndex;

	public String getPyCheckedOutOrLocked() {
		return pyCheckedOutOrLocked;
	}

	public void setPyCheckedOutOrLocked(String pyCheckedOutOrLocked) {
		this.pyCheckedOutOrLocked = pyCheckedOutOrLocked;
	}

	public String getPyDescription() {
		return pyDescription;
	}

	public void setPyDescription(String pyDescription) {
		this.pyDescription = pyDescription;
	}

	public String getPyHelpContentID() {
		return pyHelpContentID;
	}

	public void setPyHelpContentID(String pyHelpContentID) {
		this.pyHelpContentID = pyHelpContentID;
	}

	public String getPyMoreInformationContentID() {
		return pyMoreInformationContentID;
	}

	public void setPyMoreInformationContentID(String pyMoreInformationContentID) {
		this.pyMoreInformationContentID = pyMoreInformationContentID;
	}

	public String getPyShapeID() {
		return pyShapeID;
	}

	public void setPyShapeID(String pyShapeID) {
		this.pyShapeID = pyShapeID;
	}

	public String getPyShapeIcon() {
		return pyShapeIcon;
	}

	public void setPyShapeIcon(String pyShapeIcon) {
		this.pyShapeIcon = pyShapeIcon;
	}

	public String getPyShapeImpl() {
		return pyShapeImpl;
	}

	public void setPyShapeImpl(String pyShapeImpl) {
		this.pyShapeImpl = pyShapeImpl;
	}

	public String getPyShapeName() {
		return pyShapeName;
	}

	public void setPyShapeName(String pyShapeName) {
		this.pyShapeName = pyShapeName;
	}

	public String getPyShapeType() {
		return pyShapeType;
	}

	public void setPyShapeType(String pyShapeType) {
		this.pyShapeType = pyShapeType;
	}

	public String getPyStepPlaceHolder() {
		return pyStepPlaceHolder;
	}

	public void setPyStepPlaceHolder(String pyStepPlaceHolder) {
		this.pyStepPlaceHolder = pyStepPlaceHolder;
	}

	public int getPzConversationIndex() {
		return pzConversationIndex;
	}

	public void setPzConversationIndex(int pzConversationIndex) {
		this.pzConversationIndex = pzConversationIndex;
	}
}
