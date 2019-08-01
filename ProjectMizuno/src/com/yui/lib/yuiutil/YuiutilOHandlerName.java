package com.yui.lib.yuiutil;
import java.io.*;

public class YuiutilOHandlerName {

    public String              name                     = null;

    public String              baseurl                  = null;

    public int                 gettype                  = 0;

    public String              src                      = null;

    public String              srcfile                  = null;

    public String              srcurl                   = null;

    public int                 puttype                  = 0;

    public String              dst                      = null;

    public String              dstfile                  = null;

    public String              dsturl                   = null;

    public InputStream         is                       = null;

    //input asociado

    public OutputStream        os                       = null;

    public  YuiutilOHandlerName()  {
    }

    public  YuiutilOHandlerName(String argstr_name) {
        name  = argstr_name;
    }

    public  String toString()  {
        return "YuiutilIOHandlerName=[" + name + "|" + baseurl + "|" + src + "|" + srcfile + "|" + srcurl + "|" + is + "|" + os + "]";
    }
}
