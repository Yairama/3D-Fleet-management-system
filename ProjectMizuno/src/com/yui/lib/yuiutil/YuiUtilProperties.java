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

    /**Constructor#2 properties default desde un array String*/
        public YuiUtilProperties(String argv[]){
        super(new Properties());
        setPropertiesDefaults(argv);
    }

    /**Constructor#3 properties default desde un InputStream*/
        public YuiUtilProperties(InputStream in){
        super(new Properties());
        setPropertiesDefaults(in);
    }

    /**Constructor#4 properties default desde un objeto YuiUtilProperties*/
        public YuiUtilProperties(YuiUtilProperties argprops){
        super(new Properties());
        setPropertiesDefaults(argprops);
    }
    /** Establece las propiedades desde Constructor#1*/
        public void setPropertiesDefaults(String args, boolean scanout){
        if(args==null){return;}
        if(scanout) {
            StringBuffer sb = new StringBuffer(args.length() + 32);
            try {
                defaults.load(new StringReader(YuiUtilProperties.scanOutProperties(args, sb).toString()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            try {
                defaults.load(new StringReader(args));
            }catch (IOException e){e.printStackTrace();}
        }

        try {
            defaults.load(new StringReader(args));
        }catch (IOException e){e.printStackTrace();}

        }

    /** Establece las propiedades desde Constructor#2*/
    public void setPropertiesDefaults(String argv[]){
        if (argv == null)
            return;

        for (int i = 0; i < argv.length; i++)
        {
            try {
                defaults.load(new StringReader(argv[i]));
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /** Establece las propiedades desde Constructor#3*/
    public void setPropertiesDefaults(InputStream in){
        if (in == null)
            return;
        try {
            defaults.load(in);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Establece las propiedades desde Constructor#4*/
    public void setPropertiesDefaults(YuiUtilProperties argprops){
        if (argprops == null)
            return;

        // Copy all key/val pairs
        for (Enumeration ep = argprops.propertyNames(); ep.hasMoreElements(); )
        {
            String key = (String)ep.nextElement();
            String val = key + "=" + argprops.getProperty(key);
            setPropertiesDefaults(val, false);
        }
    }

    /**Establecer propiedades Constructor#1*/
    public void setProperties(String args, boolean scanout){
        if (args == null)
            return;
        if (scanout)
        {
            StringBuffer sb = new StringBuffer(args.length() + 32);
            try {
                load(new StringReader(YuiUtilProperties.scanOutProperties(args, sb).toString()));
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
        {
            try {
                load(new StringReader(args));
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**Establecer propiedades Constructor#2*/
    public void setProperties(String argv[]){
        if (argv == null)
            return;

        for (int i = 0; i < argv.length; i++)
        {
            try {
                load(new StringReader(argv[i]));
            }
            catch (IOException excp) {
                excp.printStackTrace();
            }
        }
    }

    /**Establecer propiedades Constructor#3*/
    public void setProperties(InputStream in){
        if (in == null)
            return;

        try {
            load(in);
        }
        catch (IOException excp) {
            excp.printStackTrace();
        }
    }

    /**Establecer propiedades Constructor#3*/
    public void setProperties(YuiUtilProperties argprops){
        if (argprops == null)
            return;

        // Copy all key/val pairs
        for (Enumeration ep = argprops.propertyNames(); ep.hasMoreElements(); )
        {
            String key = (String)ep.nextElement();
            String val = key + "=" + argprops.getProperty(key);
            setProperties(val, false);
        }
    }

    /**Obtener propiedades como entero*/
    public int getProperty_int(String key){
        if (key == null)
            return 0;

        String val = getProperty(key);

        if (val == null)
            return 0;

        int ret_int = 0;
        try
        {
            Integer workint = new Integer(val);
            ret_int = workint.intValue();
        }
        catch(NumberFormatException e)
        {
            ret_int = 0;
        }

        return ret_int;
    }

    /**Obtener propiedades como double*/
    public double getProperty_double(String key)
    {
        if (key == null)
            return 0.0;

        String val = getProperty(key);

        if (val == null)
            return 0.0;

        double ret_double = 0.0;
        try
        {
            Double workdouble = new Double(val);
            ret_double = workdouble.doubleValue();
        }
        catch(NumberFormatException e)
        {
            ret_double = 0.0;
        }

        return ret_double;
    }

    /**Obtener propiedades como double*/
    public boolean getProperty_boolean(String key)
    {
        if (key == null)
            return false;

        String val = getProperty(key);

        if (val == null)
            return false;

        boolean ret_boolean = false;
        if (val.equalsIgnoreCase("ON") ||
                val.equalsIgnoreCase("YES") ||
                val.equalsIgnoreCase("TRUE"))
        {
            ret_boolean = true;
        }
        else
        if (val.equalsIgnoreCase("OFF") ||
                val.equalsIgnoreCase("NO") ||
                val.equalsIgnoreCase("FALSE"))
        {
            ret_boolean = false;
        }

        return ret_boolean;
    }
    /**Obtener propiedades como String*/
    public String getProperty_String(String key)
    {
        if (key == null)
            return null;

        String val = getProperty(key);

        return val;
    }

    /**Obtener propiedades como Color*/
    public Color getProperty_Color(String key)
    {
        if (key == null)
            return Color.black;

        String val = getProperty(key);

        if (val == null)
            return Color.black;

        Color ret_Color = convertStringToColor(val);

        return ret_Color;
    }

    /**Convertir String a color*/
    public static Color convertStringToColor(String val)
    {
        Color ret_Color;

        try
        {
            String workstr1;
            int slen = val.length();
            if (val.startsWith("0x") || val.startsWith("0X"))
            {
                workstr1 = val.substring(2);
                slen -= 2;
            }
            else
            if (val.startsWith("#"))
            {
                workstr1 = val.substring(1);
                slen -= 1;
            }
            else
            {   // decimal integer
                return new Color(Integer.parseInt(val));
            }

            // process hex string
            if (slen <= 6)
            {   // no alpha
                int ival = Integer.parseInt(workstr1, 16);
                ret_Color = new Color(ival);
            }
            else
            {   // has alpha of some sort
                String workstr2;
                if (slen == 8)
                {
                    workstr2 = workstr1;
                }
                else
                if (slen == 7)
                {
                    workstr2 = "0" + workstr1;
                }
                else
                {
                    workstr2 = workstr1.substring(slen - 8); // get rightmost 8
                }

                // System.out.println("Color,val=[" + val + "],key=[" + key + "],slen=" + slen + "]");
                // System.out.println("      workstr1=[" + workstr1 + "],workstr2=[" + workstr2 + "]");
                int a = Integer.parseInt(workstr2.substring(0, 2), 16); // a
                int r = Integer.parseInt(workstr2.substring(2, 4), 16); // r
                int g = Integer.parseInt(workstr2.substring(4, 6), 16); // g
                int b = Integer.parseInt(workstr2.substring(6, 8), 16); // b
                // System.out.println("      ret_Color1=[" + r + ":" + g + ":" + b +":" + a + "]");
                // int ival = Integer.parseInt(workstr2, 16);
                // ret_Color = new Color(ival, true);
                // System.out.println("      ival=" + ival);
                try {
                    ret_Color = new Color(r, g, b, a);
                }
                catch (NoSuchMethodError excp1) {
                    System.out.println("YutilProperties:convertStringToColor|excp1=[" + excp1 + "]");
                    ret_Color = new Color(r, g, b);
                }
                // System.out.println("      ret_Color1=[" + ret_Color + "]");
            }
        }
        catch(NumberFormatException e)
        {
            ret_Color = Color.black;
            // System.out.println("Color,ret_Color3=[" + ret_Color + "]");
        }

        return ret_Color;
    }

    /**Convertir Color a String*/
    public static String convertColorToString(Color val)
    {
        String ret_String;

        int a;
        try {
            a = val.getAlpha();
        }
        catch (NoSuchMethodError excp) {
            System.out.println("YutilProperties:convertColorToString:excp=[" + excp + "]");
            a = 0xFF;
        }
        int r = val.getRed(), g = val.getGreen(), b = val.getBlue();
        String as, rs, gs, bs;

        rs = Integer.toHexString(r); if (rs.length() == 1) rs = "0" + rs;
        gs = Integer.toHexString(g); if (gs.length() == 1) gs = "0" + gs;
        bs = Integer.toHexString(b); if (bs.length() == 1) bs = "0" + bs;

        if (a == 0xFF)
        {
            ret_String = "#" + rs + gs + bs;
        }
        else
        {
            as = Integer.toHexString(a); if (as.length() == 1) as = "0" + as;
            ret_String = "#" + as + rs + gs + bs;
        }

        return ret_String;
    }

    /**Argumento de análisis Cadena en pares separados de nueva línea delimitados Nombre de propiedad / valor.*/
    public static StringBuffer scanOutProperties(String args, StringBuffer sb)
    {
        int si; // source Index

        int len = args.length();
        char c;

        int nambeg, namlen;
        int valbeg, vallen;

        // set output sb empty
        sb.setLength(0);

        // scan entire args for nam/val pairs
        si = 0;

        mainscanloop: // outermost scan loop
        for (;;)
        {
            if (si >= len)
                break mainscanloop; // totally done

            namvalscanloop: // scan single nam/val pair
            for (;;)
            {
                // ====== begin scan on one pair
                nambeg = -1;
                namlen = 0;

                valbeg = -1;
                vallen = 0;


                // ====== scan past white space before nam
                for (;;)
                {
                    if (si >= len)
                        break namvalscanloop; // done with this pair

                    c = args.charAt(si);

                    if (c == ' ' || c == '\t' || c == '\n' || c == '\r')
                    {
                        si++;
                        continue;
                    }
                    break;
                }


                // ====== Start of nam
                // scan len of nam, up to '='
                nambeg = si;
                for (;;)
                {
                    if (si >= len)
                        break namvalscanloop; // done with this pair

                    c = args.charAt(si);

                    if (c == '\n')
                    {
                        si++;
                        break namvalscanloop; // done with this pair
                    }

                    if (c == '=') // Found delimiter - go on to scan val
                    {
                        si++;
                        break;
                    }

                    namlen++;

                    si++;
                }


                // ====== Start of val
                // scan len of val
                // handle " and ' bounded values
                if (si >= len)
                    break namvalscanloop; // done with this pair

                c = args.charAt(si);

                // === scan to matching " or '
                if (c == '\"' || c == '\'')
                {
                    char matchc = c;
                    si++;
                    if (si >= len)
                        break namvalscanloop; // done with this pair

                    c = args.charAt(si);

                    valbeg = si;
                    for (;;)
                    {
                        if (c == '\n')
                        {
                            si++;
                            break namvalscanloop; // done with this pair
                        }

                        if (c == '\\') // Check for escaped " or '
                        {
                            if (si + 1 < len)
                            {
                                if (args.charAt(si + 1) == '\"' || args.charAt(si + 1) == '\'')
                                {
                                    vallen += 2;
                                    si += 2;
                                    if (si >= len)
                                        break namvalscanloop; // done with this pair
                                    c = args.charAt(si);
                                    continue;
                                }
                            }
                        }

                        if (c == matchc)
                        {
                            si++;
                            break namvalscanloop; // done with this pair
                        }

                        vallen++;

                        si++;
                        if (si >= len)
                            break namvalscanloop; // done with this pair
                        c = args.charAt(si);
                    }
                }
                else

                // === scan normal value - c is valid upon first entry
                {
                    valbeg = si;
                    for (;;)
                    {
                        if (c == '\n')
                        {
                            si++;
                            break namvalscanloop; // done with this pair
                        }

                        if (c == ' ')
                        {
                            si++;
                            break namvalscanloop; // done with this pair
                        }

                        vallen++;

                        si++;
                        if (si >= len)
                            break namvalscanloop; // done with this pair
                        c = args.charAt(si);
                    }
                }

            } // end of namvalscanloop

            // append anything accumulated in output sb and go for another pair
            YuiUtilProperties.scanOutPropertiesNamValAppend(args, nambeg, namlen, valbeg, vallen, sb);

        } // end of for ever

        return sb;
    }

    /**Agregar un par de nombre / valor a un StringBuffer*/
    private static void scanOutPropertiesNamValAppend(String args,
                                       int nambeg, int namlen,
                                       int valbeg, int vallen,
                                       StringBuffer sb)
    {
        int si; // source Index

        int len = args.length();

        if (nambeg < 0 || nambeg >= len || (nambeg + namlen - 1) >= len)
            return;
        if (valbeg < 0 || valbeg >= len || (valbeg + vallen - 1) >= len)
            return;

        // append nam
        for (si = nambeg; si < (nambeg + namlen); si++)
        {
            sb.append(args.charAt(si));
        }

        // append deliminator
        sb.append('=');

        // append val
        for (si = valbeg; si < (valbeg + vallen); si++)
        {
            sb.append(args.charAt(si));
        }

        // append terminator
        sb.append('\n');
    }
}

