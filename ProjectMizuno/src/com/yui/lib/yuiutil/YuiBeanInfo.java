package com.yui.lib.yuiutil;
import java.beans.*;


public class YuiBeanInfo extends java.beans.SimpleBeanInfo{
    private final Class beanClass = YuiBeanInfo.class;
    public YuiBeanInfo() {
    }

    public BeanInfo[] getAdditionalBeanInfo()    {
        // se crea un beaninfo para la superclass de este bean
        try        {
            BeanInfo[] bi = new BeanInfo[1];
            bi[0] = Introspector.getBeanInfo(beanClass.getSuperclass());
            return bi;
        }
        catch (IntrospectionException e)        {
            throw new Error(e.toString());
        }
    }

    public BeanDescriptor getBeanDescriptor() {
        //se obtiene el beandescriptor para este bean
        BeanDescriptor bd = new BeanDescriptor(beanClass);
        return bd;
    }


    public java.awt.Image getIcon(int nIconKind){
        //obtiene la imagen que se usara para representar este bean
        java.awt.Image img = null;
        return img;
    }


}