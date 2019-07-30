package com.yui.lib.yuiutil;

import java.io.*;
import java.util.*;
import java.awt.*;

/** Rutinas para manejar argumentos de línea de comandos (aplicaciones)
    Extiende de java.util.Properties*/

public class YuiUtilProperties extends Properties{
    /**Constructor#0 sin properties*/
    public YuiUtilProperties(){super(new Properties());}

    /** Constructor#1 properties default desde un String y un boolean
     * args = Coleccion de properties
     * scanout = Analiza los argumentos flag*/
    public YuiUtilProperties(String args, boolean scanout){
        super(new Properties());
        setPropertiesDefaults(args,scanout);
    }

    /**Constructor properties default desde un array String*/
    public YuiUtilProperties(String argv[]){
        super(new Properties());
        setPropertiesDefaults(argv);
    }

    /**Constructor properties default desde un InputStream*/
    public YuiUtilProperties(InputStream in){
        super(new Properties());
        setPropertiesDefaults(in);
    }

    /**Constructor properties default desde un objeto YuiUtilProperties*/
    public YuiUtilProperties(YuiUtilProperties argprops){
        super(new Properties());
        setPropertiesDefaults(argprops);
    }
    /** Establece las propiedades desde Constructor#1*/
    public void setPropertiesDefaults(String args, boolean scanout){
        if(args==null){return;}
        if(scanout){

        }
    }
}