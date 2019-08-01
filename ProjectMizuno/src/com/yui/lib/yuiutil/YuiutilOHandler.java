package com.yui.lib.yuiutil;
import javax.swing.*;
import java.applet.*;
import java.io.*;
import java.net.*;


public class YuiutilOHandler{

    private JFrame jFrame  = null;

    public YuiutilOHandler()  {

    }

    public YuiutilOHandler(JFrame applet) {
        setJFrame(applet);
    }

    private JFrame setJFrame(JFrame jFrame)  {
        // das un parametro que sea referencia a un jframe y recibes una referencia a jframe ( siempre es asi de confuso)
        this.jFrame = jFrame;
        return this.jFrame;
    }
    public InputStream openInputStream(String argstr_name,String argstr_baseurl, String argstr_src, String argstr_srcfile, String argstr_srcurl) {
        System.out.println("++YuiutilIOHandler:==================================================");
        System.out.println("++YuiutilIOHandler:openInputStream|argstr_name=[" + argstr_name + "]");

        InputStream retis = openInputStreamAction(argstr_name,
                argstr_baseurl,
                argstr_src, argstr_srcfile, argstr_srcurl);

        System.out.println("++YutilIOHandler:==================================================");
        return retis;
    }

    public
    InputStream openInputStream(com.yui.lib.yuiutil.YuiutilOHandlerName arg_ioname) {
        // por ahora sale rojito porque aun no hice la yuiutilOHandlerName
        System.out.println("++YuiutilIOHandler:==================================================");
        System.out.println("++YutilIOHandler:openInputStream|arg_ioname=[" + arg_ioname + "]");

        InputStream retis = arg_ioname.is = openInputStreamAction(arg_ioname.name,
                arg_ioname.baseurl,
                arg_ioname.src, arg_ioname.srcfile, arg_ioname.srcurl);

        System.out.println("++YuiutilIOHandler:==================================================");
        return retis;
    }

    public
    InputStream openInputStreamAction(String argstr_name,String argstr_baseurl, String argstr_src, String argstr_srcfile, String argstr_srcurl){
        // abre el inpustream de un archivo o url dependiendo en los valores que le ponga el usuario y la forma de correrlo
        URL baseURL = null;
        URL openURL = null;
        InputStream is = null;
        System.out.println("++YuiutilIOHandler:openInputStream|argstr_name=[" + argstr_name + ",applet=[" + applet + "]");
        System.out.println("++YuiutilIOHandler:openInputStream|argstr_baseurl=[" + argstr_baseurl + "]" +
                ",argstr_src=[" + argstr_src + "]" +
                ",argstr_srcfile=[" + argstr_srcfile + "]" +
                ",argstr_srcurl=[" + argstr_srcurl + "]");


        if (argstr_baseurl != null) {
            // se trabaja con el url ahora
            try  {
                baseURL = new URL(argstr_baseurl);
            }
            catch (MalformedURLException e_malbaseurl)  {
                System.out.println("++YuiutilIOHandler:openInputStream|Malformed base URL argstr_baseurl=[" + argstr_baseurl + "],exception=[" + e_malbaseurl + "]");
                return null;
            }
        }
        else {
            if (applet != null) {
                baseURL = applet.getDocumentBase();
                // urgente encontrar metodo para obtener url de un jframe
            }
        }
        System.out.println("++YuiutilIOHandler:openInputStream|baseURL=[" + baseURL + "]");


        if (applet != null)   {
            if (argstr_src != null)  {
                try  {
                    openURL = new URL(baseURL, argstr_src);
                    try      {
                        is = openURL.openConnection().getInputStream();
                    }
                    catch (IOException e_erropenstream)                    {
                        System.out.println("++YutilIOHandler:openInputStream|open input stream error argstr_src=[" + argstr_src + "],exception=[" + e_erropenstream + "]");
                        return null;
                    }
                }
                catch (MalformedURLException e_malopenurl)   {
                    System.out.println("++YuiutilIOHandler:openInputStream|Malformed src URL argstr_src=[" + argstr_src + "],exception=[" + e_malopenurl + "]");
                    return null;
                }
                System.out.println("++YuiutilIOHandler:openInputStream|openURL=[" + openURL + "]");
            }
            else

            if (argstr_srcfile != null)    {
                try   {
                    is = new FileInputStream(argstr_srcfile);
                }
                catch (IOException e_erropenstream)   {
                    System.out.println("++YuiutilIOHandler:openInputStream|open input stream error argstr_srcfile[" + argstr_srcfile + "],exception=[" + e_erropenstream + "]");
                    return null;
                }
            }
            else

            if (argstr_srcurl != null)  {
                try
                {
                    openURL = new URL(baseURL, argstr_srcurl);
                    try     {
                        is = openURL.openConnection().getInputStream();
                    }
                    catch (IOException e_erropenstream)   {
                        System.out.println("++YuiutilIOHandler:openInputStream|open input stream error argstr_srcurl=[" + argstr_srcurl + "],exception=[" + e_erropenstream + "]");
                        return null;
                    }
                }
                catch (MalformedURLException e_malopenurl)   {
                    System.out.println("++YuiutilIOHandler:openInputStream|Malformed src URL argstr_srcurl=[" + argstr_srcurl + "],exception=[" + e_malopenurl + "]");
                    return null;
                }
                System.out.println("++YuiutilIOHandler:openInputStream|openURL=[" + openURL + "]");
            }
            else    {
                System.out.println("++YuiutilIOHandler:ERROR - neither src nor srcfile nor srcurl property set");
                return null;
            }

        }
        else  {
//para abrir como una applicacion
            if (argstr_src != null)   {
                try   {
                    is = new FileInputStream(argstr_src);
                }
                catch (IOException e_erropenstream)     {
                    System.out.println("++YuiutilIOHandler:openInputStream|open input stream error argstr_src[" + argstr_src + "],exception=[" + e_erropenstream + "]");
                    return null;
                }
            }
            else

            if (argstr_srcfile != null)  {
                try    {
                    is = new FileInputStream(argstr_srcfile);
                }
                catch (IOException e_erropenstream) {
                    System.out.println("++YuiutilIOHandler:openInputStream|open input stream error argstr_srcfile[" + argstr_srcfile + "],exception=[" + e_erropenstream + "]");
                    return null;
                }
            }
            else

            if (argstr_srcurl != null){
                try   {
                    openURL = new URL(baseURL, argstr_srcurl);
                    try      {
                        is = openURL.openConnection().getInputStream();
                    }
                    catch (IOException e_erropenstream)  {
                        System.out.println("++YuiutilIOHandler:openInputStream|open input stream error argstr_srcurl=[" + argstr_srcurl + "],exception=[" + e_erropenstream + "]");
                        return null;
                    }
                }
                catch (MalformedURLException e_malopenurl)  {
                    System.out.println("++YuiutilIOHandler:openInputStream|Malformed src URL argstr_srcurl=[" + argstr_srcurl + "],exception=[" + e_malopenurl + "]");
                    return null;
                }
                System.out.println("++YuiutilIOHandler:openInputStream|openURL=[" + openURL + "]");
            }
            else   {
                System.out.println("++YuiutilIOHandler:ERROR - neither src nor srcfile nor srcurl property set");
                return null;
            }

        }

        return is;
    }

    public
    OutputStream openOutputStream(String argstr_name,String argstr_baseurl, String argstr_dst, String argstr_dstfile, String argstr_dsturl) {
        System.out.println("++YuiutilIOHandler:==================================================");
        System.out.println("++YuiutilIOHandler:openOutputStream|argstr_name=[" + argstr_name + "]");

        OutputStream retos = openOutputStreamAction(argstr_name, argstr_baseurl,argstr_dst, argstr_dstfile, argstr_dsturl);
        System.out.println("++YuiutilIOHandler:==================================================");
        return retos;
    }
    //==========================================================================


     //==========================================================================
     /**
     * @param arg_ioname A YutilIOHandlerName.
     * @return An output stream.
     */
    public OutputStream openOutputStream(com.yui.lib.yuiutil.YuiutilOHandlerName arg_ioname){
        System.out.println("++YuiutilIOHandler:==================================================");
        System.out.println("++YuiutilIOHandler:openInputStream|arg_ioname=[" + arg_ioname + "]");

        OutputStream retos = arg_ioname.os = openOutputStreamAction(arg_ioname.name,arg_ioname.baseurl,arg_ioname.dst, arg_ioname.dstfile, arg_ioname.dsturl);

        System.out.println("++YuiutilIOHandler:==================================================");
        return retos;
    }

    public OutputStream openOutputStreamAction( String argstr_name,String argstr_baseurl, String argstr_dst, String argstr_dstfile, String argstr_dsturl)    {
        URL baseURL = null;
        URL openURL = null;
        OutputStream os = null;


        System.out.println("++YuiutilIOHandler:openOutputStream|argstr_name=[" + argstr_name + ",applet=[" + applet + "]");
        System.out.println("++YuiutilIOHandler:openOutputStream|argstr_baseurl=[" + argstr_baseurl + "]" +",argstr_dst=[" + argstr_dst + "]" +",argstr_dstfile=[" + argstr_dstfile + "]" + ",argstr_dsturl=[" + argstr_dsturl + "]");

        if (argstr_baseurl != null)  {
            //se desarrolla la url base a usar
            try {
                baseURL = new URL(argstr_baseurl);
            }
            catch (MalformedURLException e_malbaseurl) {
                System.out.println("++YuiutilIOHandler:openOutputStream|Malformed base URL argstr_baseurl=[" + argstr_baseurl + "],exception=[" + e_malbaseurl + "]");
                return null;
            }
        }
        else {
            if (applet != null) {
                baseURL = applet.getDocumentBase();
            }
        }
        System.out.println("++YuiutilIOHandler:openOutputStream|baseURL=[" + baseURL + "]");

        if (applet != null)  {

            if (argstr_dst != null)  {
                try {
                    openURL = new URL(baseURL, argstr_dst);
                    try {
                        os = openURL.openConnection().getOutputStream();
                    }
                    catch (IOException e_erropenstream)   {
                        System.out.println("++YuiutilIOHandler:openOutputStream|open output stream error argstr_dst=[" + argstr_dst + "],exception=[" + e_erropenstream + "]");
                        return null;
                    }
                }
                catch (MalformedURLException e_malopenurl)    {
                    System.out.println("++YuiutilIOHandler:openOutputStream|Malformed dst URL argstr_dst=[" + argstr_dst + "],exception=[" + e_malopenurl + "]");
                    return null;
                }
                System.out.println("++YuiutilIOHandler:openOutputStream|openURL=[" + openURL + "]");
            }
            else

            if (argstr_dstfile != null) {
                try   {
                    os = new FileOutputStream(argstr_dstfile);
                }
                catch (IOException e_erropenstream)   {
                    System.out.println("++YuiutilIOHandler:openOutputStream|open output stream error argstr_dstfile[" + argstr_dstfile + "],exception=[" + e_erropenstream + "]");
                    return null;
                }
            }
            else
            if (argstr_dsturl != null)   {
                try  {
                    openURL = new URL(baseURL, argstr_dsturl);
                    try  {
                        os = openURL.openConnection().getOutputStream();
                    }
                    catch (IOException e_erropenstream)  {
                        System.out.println("++YuiutilIOHandler:openOutputStream|open output stream error argstr_dsturl=[" + argstr_dsturl + "],exception=[" + e_erropenstream + "]");
                        return null;
                    }
                }
                catch (MalformedURLException e_malopenurl)   {
                    System.out.println("++YuiutilIOHandler:openOutputStream|Malformed dst URL argstr_dsturl=[" + argstr_dsturl + "],exception=[" + e_malopenurl + "]");
                    return null;
                }
                System.out.println("++YuiutilIOHandler:openOutputStream|openURL=[" + openURL + "]");
            }
            else  {
                System.out.println("++YutilIOHandler:ERROR - neither dst nor dstfile nor dsturl property set");
                return null;
            }

        }
        else {
            if (argstr_dst != null)   {
                try   {
                    os = new FileOutputStream(argstr_dst);
                }
                catch (IOException e_erropenstream)  {
                    System.out.println("++YuiutilIOHandler:openOutputStream|open input stream error argstr_dst[" + argstr_dst + "],exception=[" + e_erropenstream + "]");
                    return null;
                }
            }
            else

            if (argstr_dstfile != null){
                try   {
                    os = new FileOutputStream(argstr_dstfile);
                }
                catch (IOException e_erropenstream)   {
                    System.out.println("++YutilIOHandler:openOutputStream|open input stream error argstr_dstfile[" + argstr_dstfile + "],exception=[" + e_erropenstream + "]");
                    return null;
                }
            }
            else

            if (argstr_dsturl != null)  {
                try  {
                    openURL = new URL(baseURL, argstr_dsturl);
                    try     {
                        os = openURL.openConnection().getOutputStream();
                    }
                    catch (IOException e_erropenstream)  {
                        System.out.println("++YutilIOHandler:openOutputStream|open output stream error argstr_dsturl=[" + argstr_dsturl + "],exception=[" + e_erropenstream + "]");
                        return null;
                    }
                }
                catch (MalformedURLException e_malopenurl)  {
                    System.out.println("++YutilIOHandler:openOutputStream|Malformed dst URL argstr_dsturl=[" + argstr_dsturl + "],exception=[" + e_malopenurl + "]");
                    return null;
                }
                System.out.println("++YutilIOHandler:openOutputStream|openURL=[" + openURL + "]");
            }
            else {
                System.out.println("++YutilIOHandler:ERROR - neither dst nor dstfile nor dsturl property set");
                return null;
            }

        }

        return os;
    }
    //==========================================================================


}

