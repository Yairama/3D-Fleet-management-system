package com.yui.lib.yuiutil;

public class YuiutilCharBuffer {

    private char value;

    public  YuiutilCharBuffer()  {
    }

    public YuiutilCharBuffer(char value)   {
        this.value = value;
    }

    public char setValue(char value)  {
        return this.value = value;
    }

    public char getValue() {
        return value;
    }

    public int hashCode()  {
        return (int)value;
        //no entendi este metodo ninja :(
    }

    public boolean equals(Object obj)   {
        if ((obj != null) && (obj instanceof YuiutilCharBuffer)) {

            return value == ((YuiutilCharBuffer)obj).getValue();
        }
        return false;
    }

    public String toString() {
        char buf[] = { value };
        return String.valueOf(buf);
        //te devuelve el string que pusiste
    }
}
