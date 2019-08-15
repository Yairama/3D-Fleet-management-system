/** \file
 * DXFAttrib - entity name ATTRIB that contain text
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
package org.kabeja.parser.entities;

import org.kabeja.dxf.DXFAttrib; // DXFAttrib entity
import org.kabeja.dxf.DXFConstants;
import org.kabeja.parser.DXFValue;
import org.kabeja.dxf.DXFEntity;


/**
 * @author <a href="mailto:simon.mieth@gmx.de">Simon Mieth</a>
 *
 */
public class DXFAttribHandler extends AbstractEntityHandler {
	public static final String ENTITY_NAME = "ATTRIB"; // entity name
    public static final int ATTRIB_VERTICAL_ALIGN = 74;
    public static final int ATTRIB_TEXT_LENGTH = 73;
	public static final int TEXT_HEIGHT = 40; // font size
	private DXFAttrib text;

    public DXFAttribHandler() {
        super();
    }

    /* (non-Javadoc)
     * @see de.miethxml.kabeja.parser.entities.DXFEntityHandler#parseGroup(int, de.miethxml.kabeja.parser.DXFValue)
     */
    public void parseGroup(int groupCode, DXFValue value) {
        switch (groupCode) {
		case GROUPCODE_TEXT: // parsing text from ATTRIB
			text.setText(value.getValue());
			break;
			
		case GROUPCODE_START_X:
            text.setX(value.getDoubleValue());

            break;

        case GROUPCODE_START_Y:
            text.setY(value.getDoubleValue());

            break;

        case GROUPCODE_START_Z:
            text.setZ(value.getDoubleValue());

            break;
		case TEXT_HEIGHT: // parsing font size
			text.setHeight(value.getDoubleValue());
			
			break;
        case ATTRIB_TEXT_LENGTH:

            //ignore not used by
            break;

        case ATTRIB_VERTICAL_ALIGN:
            text.setValign(value.getIntegerValue());

            break;

        default:
            super.parseCommonProperty(groupCode, value, text);
        }
    }

    public void startDXFEntity() {
        text = new DXFAttrib();
		text.setDXFDocument(this.doc);
    }
	
	/*
     * (non-Javadoc)
     *
     * @see org.dxf2svg.parser.entities.EntityHandler#endParsing()
     */
    public void endDXFEntity() {
    }

    /*
     * (non-Javadoc)
     *
     * @see org.dxf2svg.parser.entities.EntityHandler#getEntity()
     */
    public DXFEntity getDXFEntity() {
        return text;
    }

    /* (non-Javadoc)
     * @see de.miethxml.kabeja.parser.entities.DXFEntityHandler#getDXFEntityName()
     */
    public String getDXFEntityName() {
        return ENTITY_NAME;
    }
	/*
     * (non-Javadoc)
     *
     * @see org.dxf2svg.parser.entities.EntityHandler#isFollowSequence()
     */
    public boolean isFollowSequence() {
        return false;
    }
}
