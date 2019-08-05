package FirstPack;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;

import javax.swing.JFrame;

public class Triangle implements GLEventListener {

    @Override
    public void display(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();
        gl.glBegin(GL2.GL_LINES);

        //drawing the base
        gl.glBegin(GL2.GL_LINES);
        gl.glVertex3f(-0.50f, -0.50f, 0);
        gl.glVertex3f(0.50f, -0.50f, 0);
        gl.glEnd();

        //drawing the right edge
        gl.glBegin(GL2.GL_LINES);
        gl.glVertex3f(0f, 0.50f, 0);
        gl.glVertex3f(-0.50f, -0.50f, 0);
        gl.glEnd();

        //drawing the lft edge
        gl.glBegin(GL2.GL_LINES);
        gl.glVertex3f(0f, 0.50f, 0);
        gl.glVertex3f(0.50f, -0.50f, 0);
        gl.glEnd();
        gl.glFlush();
    }

    @Override
    public void dispose(GLAutoDrawable arg0) {
        //method body
    }

    @Override
    public void init(GLAutoDrawable arg0) {
        // method body
    }

    @Override
    public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
        // method body
    }
}