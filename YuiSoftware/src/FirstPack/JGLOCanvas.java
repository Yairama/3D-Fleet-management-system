package FirstPack;

import javax.media.opengl.awt.GLCanvas;

import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.swing.*;

public class JGLOCanvas implements GLEventListener {



    @Override
    public void init(GLAutoDrawable glAutoDrawable) {

    }

    @Override
    public void dispose(GLAutoDrawable glAutoDrawable) {

    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {

    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {

    }
    //protected JGLOCanvas b = new JGLOCanvas();
    final GLProfile profile;
    protected GLCapabilities capabilities;
    protected GLCanvas glcanvas;
    protected JPanel glJpanel=new JPanel();
    public JGLOCanvas() {
        //getting the capabilities object of GL2 profile
        profile = GLProfile.get(GLProfile.GL2);
        capabilities = new GLCapabilities(profile);
        glcanvas = new GLCanvas(capabilities);

        //glcanvas.addGLEventListener(b);
        glcanvas.setSize(400, 400);
        glJpanel.add(glcanvas);
        Triangle kk = new Triangle();
        glcanvas.addGLEventListener(kk);

    }
}
