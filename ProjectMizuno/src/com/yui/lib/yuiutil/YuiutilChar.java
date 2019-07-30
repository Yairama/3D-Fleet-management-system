package com.yui.lib.yuiutil;
// en esta clase se sobreescriben metodos
public class YuiutilChar{

    private char value;

    public YuiutilChar()  {
    }

    public YuiutilChar(char value)    {
        this.value = value;
    }

    public
    char setValue(char value)    {
        return this.value = value;
    }

    public char getValue()    {
        return value;
    }

    public int hashCode()  {
        return (int)value;
    }

    public boolean equals(Object obj)    {
        if ((obj != null) && (obj instanceof YuiutilChar))   {
            return value == ((YuiutilChar)obj).getValue();
        }
        return false;
    }

    public
    String toString()    {
        char buf[] = { value };
        return String.valueOf(buf);
    }
}

