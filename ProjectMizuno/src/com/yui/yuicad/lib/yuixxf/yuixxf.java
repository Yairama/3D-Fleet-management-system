package com.yui.yuicad.lib.yuixxf;

import java.util.*;

import com.yui.lib.yuiutil.*;


/**
 * The Yxxf class represents a single instance of a Ycad drawing.
 * <p>
 * The Yxxf drawing object is a hierarchical data structure based
 * on AutoDesk's DXF format.
 * The Yxxf drawing object contains references to the various DXF
 * sections and flags describing it's state.
 * A familiarity with the DXF format is required to fully understand and
 * use the Yxxf object.
 * See the Ycad User's Guide for information on the DXF format.
 * <p>The code:<pre>
 * Yxxf D = new Yxxf();</pre>
 * will create an empty drawing object, D, that has the state
 * ready=false, complete=false, written=false.
 * <p>Only the DXF sections
 * needed for the viewing the drawing are implemented and they are:
 * <TABLE BORDER="1" WIDTH="704">
 *     <TR>
 *         <TH WIDTH="299">
 *             <P>Section Name / Description
 *         </TH>
 *         <TH WIDTH="169">
 *             <P>Object Type
 *         </TH>
 *         <TH WIDTH="214">
 *             <P>Accessed as (D is type Yxxf)
 *         </TH>
 *     </TR>
 *     <TR>
 *         <TD WIDTH="299"><FONT FACE="Courier New, Courier">HEADER<BR>
 *             </FONT>A collection of individual fields such as current line type, current color, linetype scale.</TD>
 *         <TD WIDTH="169"><FONT FACE="Courier New, Courier">YxxfSecHeader</FONT></TD>
 *         <TD WIDTH="214"><FONT FACE="Courier New, Courier">D.secHEADER</FONT></TD>
 *     </TR>
 *     <TR>
 *         <TD WIDTH="299"><FONT FACE="Courier New, Courier">TABLES<BR>
 *             </FONT>A collection of tables including: layer table, line type table, text style (fonta), Vport table.</TD>
 *         <TD WIDTH="169"><FONT FACE="Courier New, Courier">YxxfSecTables</FONT></TD>
 *         <TD WIDTH="214"><FONT FACE="Courier New, Courier">D.secTABLES</FONT></TD>
 *     </TR>
 *     <TR>
 *         <TD WIDTH="299"><FONT FACE="Courier New, Courier">BLOCKS<BR>
 *             </FONT>A named sequence of drawing entities.</TD>
 *         <TD WIDTH="169"><FONT FACE="Courier New, Courier">YxxfSecBlocks</FONT></TD>
 *         <TD WIDTH="214"><FONT FACE="Courier New, Courier">D.secBLOCKS</FONT></TD>
 *     </TR>
 *     <TR>
 *         <TD WIDTH="299"><FONT FACE="Courier New, Courier">ENTITIES<BR>
 *             </FONT>A sequence of drawing entities such as line, circle, polyline, insert block. There are two types of entities,
 *             Paper Space and Model Space.</TD>
 *         <TD WIDTH="169"><FONT FACE="Courier New, Courier">YxxfSecEntities</FONT></TD>
 *         <TD WIDTH="214"><FONT FACE="Courier New, Courier">D.secENTITIES</FONT></TD>
 *     </TR>
 * </TABLE>
 * <p>Thus:<pre>
 * YxxfGfxPointW extpnt = D.secHeader.extmin;</pre>
 * will access a field within the HEADER section and<pre>
 * YxxfTblLayer layer = D.secTables.findLayer_add("FLOORPLAN");</pre>
 * <p>will call a method in the TABLES section.
 * <p>See the individual section class descriptions for field and method summaries.
 *
 * @author Ed Karlo - Y Systems, LLC
 */
public class Yxxf extends Observable{
    /**
     * Paper space root layer name (char array)
     */
    public static final char[]  CHR_LAYERNAME__PAPER_SPACE_ROOT_LAYER
            = { '*','*','P','A','P','E','R','_','S','P','A','C','E','_','R','O','O','T','_','L','A','Y','E','R' } ;
    /**
     * Paper space root layer name (String)
     */
    public static final String  STR_LAYERNAME__PAPER_SPACE_ROOT_LAYER = "**PAPER_SPACE_ROOT_LAYER";

    /**
     * Paper space root layer name (char array)
     */
    public static final char[]  CHR_LAYERNAME__MODEL_SPACE_ROOT_LAYER
            = { '*','*','M','O','D','E','L','_','S','P','A','C','E','_','R','O','O','T','_','L','A','Y','E','R' } ;
    /**
     * Paper space root layer name (String)
     */
    public static final String  STR_LAYERNAME__MODEL_SPACE_ROOT_LAYER = "**MODEL_SPACE_ROOT_LAYER";

    /**
     * Paper space block name (char array)
     */
    public static final char[]  CHR_BLOCKNAME__PAPER_SPACE
            = { '*','P','A','P','E','R','_','S','P','A','C','E' } ;
    /**
     * Paper space block name (String)
     */
    public static final String  STR_BLOCKNAME__PAPER_SPACE = "*PAPER_SPACE";

    /**
     * Paper space block name (char array)
     */
    public static final char[]  CHR_BLOCKNAME__MODEL_SPACE
            = { '*','M','O','D','E','L','_','S','P','A','C','E' } ;
    /**
     * Paper space block name (String)
     */
    public static final String  STR_BLOCKNAME__MODEL_SPACE = "*MODEL_SPACE";


    //==========================================================================
    // Drawing

    /**
     * Drawing type unknown (not currently used)
     */
    public final static int     TYPE_UNKNOWN    = 0;

    /**
     * Drawing type MAIN (not currently used)
     */
    public final static int     TYPE_MAIN       = 1;

    /**
     * Drawing type FONT (not currently used)
     */
    public final static int     TYPE_FONT       = 2;

    /**
     * A drawing type (not currently used)
     */
    public
    int                         type            = TYPE_UNKNOWN;

    /**
     * Header Section
     * A collection of individual fields such as current line type,
     * current color, linetype scale.
     */
    public
    YxxfSecHeader               secHeader       = null;

    /**
     * Tables Section
     * A collection of tables including: layer table,
     * line type table, text style(font), ViewPort.
     */
    public
    YxxfSecTables               secTables       = null;

    /**
     * Blocks Section
     * A named sequence of drawing entities. Blocks are either Paper
     * Space or Model Space blocks.
     */
    public
    YxxfSecBlocks               secBlocks       = null;

    /**
     * Entities Section
     * A sequence of drawing entities such as line, circle, polyline,
     * insert block. There are two types of entities, Paper Space and Model
     * Space.
     */
    public
    YxxfSecEntities             secEntities     = null;
    //==========================================================================


    //==========================================================================
    // Get and put drawing items

    /**
     * Drawing source
     */
    public
    YutilIOHandlerName          ioname          = null;

    /**
     * I/O Handler
     */
    public
    YutilIOHandler              iohandler       = null;

    /**
     * Ready flag
     */
    private
    boolean                     ready           = false;

    /**
     * Complete flag
     */
    private
    boolean                     complete        = false;

    /**
     * Written flag
     */
    private
    boolean                     written         = false;
    //==========================================================================




    //==========================================================================
    /**
     * Constructor
     * Sets up entity
     */
    public
    Yxxf()
    {
        setDrawingEmpty();
    }


    /**
     * Set to initial empty state.
     */
    public
    void setDrawingEmpty()
    {
        type            = TYPE_UNKNOWN;

        secHeader       = new YxxfSecHeader();
        secTables       = new YxxfSecTables();
        secBlocks       = new YxxfSecBlocks();
        secEntities     = new YxxfSecEntities();

        ready           = false;
        complete        = false;
        written         = false;

        setupESpace();
    }
    //==========================================================================




    //==========================================================================
    // Ready wait/set/get

    /**
     * Wait until drawing is ready.
     */
    public synchronized
    void waitDrawingReady()
    {
        if (ready)
            return;

        while (true)
        {
            try { wait(); }
            catch(InterruptedException e) { System.out.println(e); }

            if (ready)
                return;
        }
    }


    /**
     * Set the value of the ready flag.
     * @param ready Is the drawing ready
     * @return true if ready
     */
    public synchronized
    boolean setDrawingReady(boolean ready)
    {
        this.ready = ready;
        notifyAll();
        return this.ready;
    }


    /**
     * Return value of ready flag.
     * @return true if ready
     */
    public // synchronized
    boolean getDrawingReady()
    {
        return ready;
    }
    //==========================================================================




    //==========================================================================
    // Complete wait/set/get

    /**
     * Wait until drawing is complete.
     */
    public synchronized
    void waitDrawingComplete()
    {
        if (complete)
            return;

        while (true)
        {
            try { wait(); }
            catch(InterruptedException e) { System.out.println(e); }

            if (complete)
                return;
        }
    }


    /**
     * Set the value of the complete flag and tell listeners it's changed.
     * @param complete The value of the complete flag
     * @return true if complete
     */
    public synchronized
    boolean setDrawingComplete(boolean complete)
    {
        this.complete = complete;

        // Mark lists as complete
        secEntities.insPSpace.block.setBlockComplete(this.complete);
        secEntities.insMSpace.block.setBlockComplete(this.complete);

        // Notify it's good to go
        secEntities.insPSpace.block.drawNotify();
        secEntities.insMSpace.block.drawNotify();

        notifyAll();

        return this.complete;
    }


    /**
     * Get the value of complete flag
     * @return true if complete
     */
    public // synchronized
    boolean getDrawingComplete()
    {
        return complete;
    }
    //==========================================================================




    //==========================================================================
    // Written wait/set/get

    /**
     * Wait until drawing is written.
     */
    public synchronized
    void waitDrawingWritten()
    {
        if (written)
            return;

        while (true)
        {
            try { wait(); }
            catch(InterruptedException e) { System.out.println(e); }

            if (written)
                return;
        }
    }


    /**
     * Set the value of the written flag and notify listeners that
     * it has changed.
     * @param written The value of the written flag
     * @return true if written
     */
    public synchronized
    boolean setDrawingWritten(boolean written)
    {
        this.written = written;
        notifyAll();
        return this.written;
    }


    /**
     * Get the value of the written flag.
     * @return true if written
     */
    public // synchronized
    boolean getDrawingWritten()
    {
        return written;
    }
    //==========================================================================




    //==========================================================================
    /**
     * Initial insert where the drawing
     * Set up inserts for paper space and model space.
     * <UL>
     *   <LI>Check if layer "0" exists and create if necessary.
     *   <LI>Check if layers "**PAPER_SPACE_ROOT_LAYER" and
     *       "**MODEL_SPACE_ROOT_LAYER" exist and create if necessary.
     *   <LI>Check if blocks "*PAPER_SPACE" and "*MODEL_SPACE" exist and
     *       create if necessary.
     *   <LI>Create PSpace and MSpace inserts.
     * </UL>
     */
    public
    void setupESpace()
    {
        // Create default line types CONTINUOUS and BYLAYER
        YxxfTblLtype def_ltype_continuous =
                secTables.findLtype_add(YxxfTblLtypeKey.STR_LTYPENAME__CONTINUOUS);
        secTables.findLtype_add(YxxfTblLtypeKey.STR_LTYPENAME__BYLAYER);


        // Create default layer 0
        secTables.findLayer_add(YxxfTblLayerKey.STR_LAYERNAME__0);


        //
        // PSpace
        //

        // Create the root layer for PSpace.
        YxxfTblLayer
                layPSpaceRoot                       = secTables.findLayer_add(STR_LAYERNAME__PAPER_SPACE_ROOT_LAYER);
        layPSpaceRoot.ltype                 = def_ltype_continuous;
        layPSpaceRoot.aci                   = YxxfGfxPalette.ACI_WHITE;

        // Set up block for PSpace
        YxxfEntBlock
                blkPSpace                           = secBlocks.findBlock(STR_BLOCKNAME__PAPER_SPACE);
        if (blkPSpace == null)
        {
            blkPSpace                       = secBlocks.findBlock_add(STR_BLOCKNAME__PAPER_SPACE);
            blkPSpace.hdr_layer             = layPSpaceRoot;
            blkPSpace.calc(this);
        }

        // Create the insert for PSpace
        if (secEntities.insPSpace == null)
            secEntities.insPSpace           = new YxxfEntInsert(blkPSpace);
        secEntities.insPSpace.hdr_layer     = layPSpaceRoot;
        secEntities.insPSpace.hdr_aci       = YxxfGfxPalette.ACI_WHITE;
        secEntities.insPSpace.calc(this);


        //
        // MSpace
        //

        // Create the root layer for MSpace
        YxxfTblLayer
                layMSpaceRoot                       = secTables.findLayer_add(STR_LAYERNAME__MODEL_SPACE_ROOT_LAYER);
        layMSpaceRoot.ltype                 = def_ltype_continuous;
        layMSpaceRoot.aci                   = YxxfGfxPalette.ACI_WHITE;

        // Set up block for MSpace
        YxxfEntBlock
                blkMSpace                           = secBlocks.findBlock(STR_BLOCKNAME__MODEL_SPACE);
        if (blkMSpace == null)
        {
            blkMSpace                       = secBlocks.findBlock_add(STR_BLOCKNAME__MODEL_SPACE);
            blkMSpace.hdr_layer             = layMSpaceRoot;
            blkMSpace.calc(this);
        }

        // Create the insert for MSpace
        if (secEntities.insMSpace == null)
            secEntities.insMSpace           = new YxxfEntInsert(blkMSpace);
        secEntities.insMSpace.hdr_layer     = layMSpaceRoot;
        secEntities.insMSpace.hdr_aci       = YxxfGfxPalette.ACI_WHITE;
        secEntities.insMSpace.calc(this);
    }
    //==========================================================================


    //==========================================================================
    /**
     * Notify listeners that view has changed.
     * @param vhevt A View handler event.
     */
    public
    void notifyViewHandler(YxxfDrwViewHandlerEvent vhevt)
    {
        setChanged();
        notifyObservers(vhevt);
    }
    //==========================================================================
}

