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
package org.kabeja.parser;

import java.util.Hashtable;
import java.util.Iterator;

import org.kabeja.dxf.DXFDocument;
import org.kabeja.dxf.DXFEntity;
import org.kabeja.dxf.DXFInsert;
import org.kabeja.parser.entities.DXFEntityHandler;


/**
 * @author <a href="mailto:simon.mieth@gmx.de>Simon Mieth</a>
 *
 *
 */
public class DXFEntitiesSectionHandler extends AbstractSectionHandler
    implements DXFSectionHandler, HandlerManager {
    private static String SECTION_KEY = "ENTITIES";
    private static String ENTITY_ENDKEY = "SEQEND";
    public static final int ENTITY_START = 0;
    protected Hashtable handlers = new Hashtable();
    protected DXFEntityHandler handler = null;
    protected boolean parseEntity = false;
    private DXFEntity firstEntity = null;

    public DXFEntitiesSectionHandler() {
    }

    /*
     * (non-Javadoc)
     *
     * @see org.dxf2svg.parser.SectionHandler#getSectionKey()
     */
    public String getSectionKey() {
        return SECTION_KEY;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.dxf2svg.parser.SectionHandler#parseGroup(int, java.lang.String)
     */
    public void parseGroup(int groupCode, DXFValue value) {
        if (groupCode == ENTITY_START) {
            if (parseEntity) {
                if (handler.isFollowSequence()) {
                    //there is a sequence like polyline
                    handler.parseGroup(groupCode, value);

                    return;
                } else{ //if (value.getValue() == ENTITY_ENDKEY){
                	
                    endEntity();//检测到当前ENTITY的一段结束并与doc建立连接关系
                }
            }
            if (handlers.containsKey(value.getValue())) {
                //get handler for the new entity
                handler = (DXFEntityHandler) handlers.get(value.getValue());
                handler.setDXFDocument(this.doc);
                parseEntity = true;
                //if (firstEntity == null){
                //	firstEntity = handler.getDXFEntity();
                    handler.startDXFEntity(); //new entity, example: insert = new DXFInsert();
               // }else {
               //     DXFEntity entity = handler.getDXFEntity();
               //     handler.startDXFEntity(); //new entity, example: insert = new DXFInsert();
               //     entity.nextDxfEntity = handler.getDXFEntity(); //link with last entity
				//}
                
            } else {
                //no handler found
                parseEntity = false;
            }
        } else if (parseEntity) {
            handler.parseGroup(groupCode, value);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see org.dxf2svg.parser.SectionHandler#setDXFDocument(org.dxf2svg.xml.DXFDocument)
     */
    public void setDXFDocument(DXFDocument doc) {
        this.doc = doc;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.dxf2svg.parser.SectionHandler#endParsing()
     */
    public void endSection() {
        endEntity();
    }

    /*
     * (non-Javadoc)
     *
     * @see org.dxf2svg.parser.SectionHandler#startParsing()
     */
    public void startSection() {
        parseEntity = false;
    }

    protected void endEntity() {
        if (parseEntity) {

            DXFEntity entity = handler.getDXFEntity();
            doc.addDXFEntity(entity);//将此entity加入本doc中对应的layer中
            handler.endDXFEntity();
            //firstEntity = null;
        }
    }

    public void addDXFEntityHandler(DXFEntityHandler handler) {
        handler.setDXFDocument(doc);
        handlers.put(handler.getDXFEntityName(), handler);
    }

    public void addHandler(Handler handler) {
        addDXFEntityHandler((DXFEntityHandler) handler);
    }

    /* (non-Javadoc)
         * @see de.miethxml.kabeja.parser.Handler#releaseDXFDocument()
         */
    public void releaseDXFDocument() {
        this.doc = null;

        Iterator i = handlers.values().iterator();

        while (i.hasNext()) {
            Handler handler = (Handler) i.next();
            handler.releaseDXFDocument();
        }
    }
}
