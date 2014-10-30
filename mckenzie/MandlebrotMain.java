package mckenzie;

//z = z^2 + c

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;

public class MandlebrotMain extends JFrame implements GLEventListener
{
	private GLU glu = new GLU();
	Mandlebrot m1;
	Dot c = new Dot();
	int dwell = 500;

	public static void main(String[] args) 
	{
		new MandlebrotMain();
	}

	public MandlebrotMain()
	{
		setTitle("Mandlebrot");
		GLProfile profile = GLProfile.get(GLProfile.GL2);
		GLCapabilities capabilities = new GLCapabilities(profile);

		// The canvas is the widget that's drawn in the JFrame
		GLCanvas glcanvas = new GLCanvas(capabilities);
		glcanvas.addGLEventListener(this);
		glcanvas.setSize( 500, 500 );
		getContentPane().add( glcanvas);
		setSize( getContentPane().getPreferredSize() );

		m1 = new Mandlebrot();

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
		// gl.glTranslatef(-1.5f, 0.0f, -6.0f);


		m1.drawMe(c, dwell, gl);
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

		final float h = (float) width / (float) height; // aspect ration

		gl.glViewport(0, 0, width, height);
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();

		// glu.gluPerspective(45.0f, h, 1.0, 20.0);
		glu.gluOrtho2D( -2, 2, -2, 2 );
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();
	}


	public void dispose(GLAutoDrawable arg0) 
	{
		System.out.println("dispose() called");
	}

}
