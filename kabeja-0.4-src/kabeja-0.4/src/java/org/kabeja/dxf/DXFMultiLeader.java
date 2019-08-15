/** \file
 * DXFMultiLeader - entity named MULTILEADER that contain lines and text
 *
 * Type: Java code 
 * \authors Eugene Korol
 * \date 18.12.2018
 */


/*
   Copyright 2005 Simon Mieth

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
package org.kabeja.dxf;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.kabeja.dxf.helpers.Point;


/**
 * @author <a href="mailto:simon.mieth@gmx.de>Simon Mieth</a>
 *
 */
public class DXFMultiLeader extends DXFEntity {
    protected String styleName = "";
    protected double arrowHeadSize = 0.0;
    protected double textGap;
    protected double scaleFactor;
    protected double textHeight = 0; // font size
	protected String text = "";
    protected List coordinates = new ArrayList();
    protected int pathType = 0;
    protected int creationType = 0;
	protected int attachmentText = 0;
    protected Point horizontalDirection = new Point();
    protected Point lastOffsetText = new Point();
    protected Point lastOffsetInsertion = new Point();
	protected Point textLocation = new Point();
    protected boolean arrowEnabled = false;
	protected boolean frameEnabled = false;
	protected boolean frameEnabledFirst = true;	
    protected String textID = "";

    /**
     * @return Returns the textID.
     */
    public String getTextID() {
        return textID;
    }
	
	/**
     * @param text
     *            The text to set.
     */
    public void setText(String text) {
        if(this.text.isEmpty()) this.text = text; // set only first text string
        //this.textDoc = DXFTextParser.parseDXFText(this);
    }
	
	public void setAttachmentText(int attachment) {
        if(attachmentText == 0) attachmentText = attachment;
    }
	
	public int getAttachmentText() {
        return attachmentText;
    }
	
	/**
     * @return Returns the text.
     */
    public String getText() {
        return text;
    }

    /**
     * @param textID
     *            The textID to set.
     */
    public void setTextID(String textID) {
        this.textID = textID;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.kabeja.dxf.DXFEntity#getBounds()
     */
    public Bounds getBounds() {
        Bounds bounds = new Bounds();
        bounds.setValid(false);

        return bounds;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.kabeja.dxf.DXFEntity#getType()
     */
    public String getType() {
        return DXFConstants.ENTITY_TYPE_MULTILEADER;
    }

    /**
     * @return Returns the arrowHeadSize.
     */
    public double getArrowHeadSize() {
        return arrowHeadSize;
    }

    /**
     * @param arrowHeadSize
     *            The arrowHeadSize to set.
     */
    public void setArrowHeadSize(double arrowHeadSize) {
        this.arrowHeadSize = arrowHeadSize;
    }

    /**
     * @return Returns the creationType.
     */
    public int getCreationType() {
        return creationType;
    }

    /**
     * @param creationType
     *            The creationType to set.
     */
    public void setCreationType(int creationType) {
        this.creationType = creationType;
    }

    /**
     * @return Returns the horizontalDirection.
     */
    public Point getHorizontalDirection() {
        return horizontalDirection;
    }

    /**
     * @param horizontalDirection
     *            The horizontalDirection to set.
     */
    public void setHorizontalDirection(Point horizontalDirection) {
        this.horizontalDirection = horizontalDirection;
    }

    /**
     * @return Returns the lastOffsetInsertion.
     */
    public Point getLastOffsetInsertion() {
        return lastOffsetInsertion;
    }

    /**
     * @param lastOffsetInsertion
     *            The lastOffsetInsertion to set.
     */
    public void setLastOffsetInsertion(Point lastOffsetInsertion) {
        this.lastOffsetInsertion = lastOffsetInsertion;
    }

    /**
     * @return Returns the lastOffsetText.
     */
    public Point getLastOffsetText() {
        return lastOffsetText;
    }
	
	public Point getTextLocation() {
        return textLocation;
    }

    /**
     * @param lastOffsetText
     *            The lastOffsetText to set.
     */
    public void setLastOffsetText(Point lastOffsetText) {
        this.lastOffsetText = lastOffsetText;
    }

    /**
     * @return Returns the pathType.
     */
    public int getPathType() {
        return pathType;
    }

    /**
     * @param pathType
     *            The pathType to set.
     */
    public void setPathType(int pathType) {
        this.pathType = pathType;
    }

    /**
     * @return Returns the scaleFactor.
     */
    public double getScaleFactor() {
        return scaleFactor;
    }

    /**
     * @param scaleFactor
     *            The scaleFactor to set.
     */
    public void setScaleFactor(double scaleFactor) {
        this.scaleFactor = scaleFactor;
    }

    /**
     * @return Returns the styleName.
     */
    public String getStyleNameID() {
        return styleName;
    }

    /**
     * @param styleName
     *            The styleName to set.
     */
    public void setStyleNameID(String styleName) {
        this.styleName = styleName;
    }

    /**
     * @return Returns the textGap.
     */
    public double getTextGap() {
        return textGap;
    }

    /**
     * @param textGap
     *            The textGap to set.
     */
    public void setTextGap(double textGap) {
        this.textGap = textGap;
    }

    /**
     * @return Returns the textHeight.
     */
    public double getTextHeight() {
        return textHeight;
    }

    /**
     * @param textHeight
     *            The textHeight to set.
     */
    public void setTextHeight(double textHeight) {
        if(this.textHeight == 0) this.textHeight = textHeight;
    }
	
    public void addCoordinate(Point vertex) {
        coordinates.add(vertex);
    }

    public int getCoordinateCount() {
        return this.coordinates.size();
    }

    public Point getCoordinateAt(int index) {
        return (Point) this.coordinates.get(index);
    }

    public Iterator getCoordinateIterator() {
        return this.coordinates.iterator();
    }

    /**
     * @return Returns the arrowEnabled.
     */
    public boolean isArrowEnabled() {
        return arrowEnabled;
    }
	
	/**
     * @return if frame text enable
     */
    public boolean isFrameEnabled() {
        return frameEnabled;
    }
	
	public void setFrameEnabled(boolean frameEnabled) {
        if(!frameEnabledFirst) this.frameEnabled = frameEnabled;
		frameEnabledFirst = false;
    }

    /**
     * @param arrowEnabled
     *            The arrowEnabled to set.
     */
    public void setArrowEnabled(boolean arrowEnabled) {
        this.arrowEnabled = arrowEnabled;
    }

    public boolean isSplinePath() {
        return this.pathType == 1;
    }

    public double getLength() {
        // TODO Auto-generated method stub
        return 0;
    }
}
