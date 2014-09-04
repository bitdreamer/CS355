//SierpinskiMain.java
//McKenzie Bradley for CS355 Fall 2014

package mckenzie;

import javax.media.opengl.*;
import javax.media.opengl.awt.*;
import javax.media.opengl.glu.*;
import javax.swing.*;

public class SierpinskiMain extends JFrame implements GLEventListener
{
	private GLU glu = new GLU(); // just has some function we like
	Sierpinski s1;

	public static void main(String[] args) 
	{
		new SierpinskiMain();
	}

	// constructor
	public SierpinskiMain()
	{
		setTitle("Sierpinski");
		GLProfile profile = GLProfile.get(GLProfile.GL2);
		GLCapabilities capabilities = new GLCapabilities(profile);

		// The canvas is the widget that's drawn in the JFrame
		GLCanvas glcanvas = new GLCanvas(capabilities);
		glcanvas.addGLEventListener(this);
		glcanvas.setSize( 300, 300 );
		getContentPane().add( glcanvas);
		setSize( getContentPane().getPreferredSize() );

		s1 = new Sierpinski();

		setVisible( true );
	}

	// display().  Note that this fuction and most (all?) of the rest are 
	// handlers for the GLEventlistener interface.
	// display() is the main on that gets called to draw your stuff.  
	// The argument glDrawable is the context for drawing; the windows system
	// hands you that, the place you are going to draw on.
	public void display(GLAutoDrawable gLDrawable) 
	{
		final GL2 gl = gLDrawable.getGL().getGL2(); // make the gl so we can draw
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();

		
		
 
		Dot a = new Dot();
		a.x[0] = -150; a.x[1] = 100;
		Dot b = new Dot();
		b.x[0] = 150; b.x[1] = 100;
		Dot c = new Dot();
		c.x[0] = 0; c.x[1] = -125;
		
		s1.drawMe(a,b,c,gl,4);

		gl.glFlush();
	}

	public void displayChanged(GLAutoDrawable gLDrawable, boolean modeChanged, boolean deviceChanged) 
	{
		System.out.println("displayChanged called");
	}

	public void init(GLAutoDrawable gLDrawable) 
	{
		System.out.println("init() called");
		GL2 gl = gLDrawable.getGL().getGL2();
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		gl.glShadeModel(GL2.GL_FLAT);
	}

	public void reshape(GLAutoDrawable gLDrawable, int x, int y, int width, int height) 
	{
		System.out.println("reshape() called: x = "+x+", y = "+y+", width = "+width+", height = "+height);
		final GL2 gl = gLDrawable.getGL().getGL2();

		if (height <= 0) // avoid a divide by zero error!
		{
			height = 1;
		}

		gl.glViewport(0, 0, width, height);
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();

		glu.gluOrtho2D( -200, 200, -200, 200 );
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();
	}


	public void dispose(GLAutoDrawable arg0) 
	{
		System.out.println("dispose() called");
	}
}
