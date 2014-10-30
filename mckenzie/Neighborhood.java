// Neightborhood.java
// McKenzie Bradley
// Adapted from Twirly4.java

package mckenzie;

import java.awt.*;
import java.awt.event.*; 

import javax.swing.*;
import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;

import com.jogamp.opengl.util.*;

public class Neighborhood  extends JFrame implements GLEventListener
{
	private GLU glu = new GLU(); // just has some function we like
	Animator goThingy;
	final Neighborhood thisthis; // for use in contexts where "this" doesn't work
	double yrot = 0;

	GLCanvas glcanvas;

	float red[] =   {1.0f,0.0f,0.0f,1.0f}; // color red
	float green[] = { 0.0f, 1.0f, 0.3f, 1.0f }; // green ish
	float blue[] = { 0.0f, 0.3f, 1.0f, 1.0f};
	float gray[] = {0.5f,0.5f,0.5f,1,0f}; //gray?

	BP cameraAngleX; // tilt the direction of gaze down (or minus is up)
	BP cameraAngleY; // pan left or right on camera
	BP cameraAngleZ;

	BP cameraX; // position of the camera
	BP cameraY;
	BP cameraZ;

	BP wholeModelAngleX; //tumbles the whole model toward us
	BP wholeModelAngleY; // spins the whole model around a vertical axis

	BP zoom; // controls the cameral angle width

	House houses[];

	ControlStuff4 buttons;
	// javax.swing.Timer timey;


	public static void main(String[] args) 
	{
		new Neighborhood();
	}

	public Neighborhood()
	{      
		GLProfile profile = GLProfile.get(GLProfile.GL2);
		GLCapabilities capabilities = new GLCapabilities(profile);
		glcanvas = new GLCanvas(capabilities);
		glcanvas.addGLEventListener(this);

		setSize( 500, 500 );
		add(glcanvas);

		goThingy = new Animator( glcanvas );
		goThingy.start();

		double anchorX = 1;
		double anchorZ = 0.5;


		houses = new House[30];
		int x=0;

		for(int m = 0; m<3; m++)
		{
			for(int k = 0; k<2; k++)
			{
				for(int i = 0; i<5; i++)
				{
					double d1 = Math.random();
					float f1 = (float) d1;

					double d2 = Math.random();
					float f2 = (float) d2;

					double d3 = Math.random();
					float f3 = (float) d3;

					float cColor[] = {f1,f2,f3,1.0f};

					double dr1 = Math.random();
					float fr1 = (float) dr1;

					double dr2 = Math.random();
					float fr2 = (float) dr2;

					double dr3 = Math.random();
					float fr3 = (float) dr3;

					float rColor[] = {fr1,fr2,fr3,1.0f};

					houses[x] = new House(anchorX, anchorZ, cColor, rColor);
					//System.out.println(x);
					x++;


					anchorX+=1.5;
				}
				anchorZ+=2;
				anchorX = 1;
			}
			anchorZ+=2;
		}

		for(int j = 0; j<30; j++)
		{
			//houses[j].drawMe(gl);
			houses[j].report();
		}


		buttons = new ControlStuff4( ) ;
		cameraAngleX = buttons.addControl("cam look down",20,1);
		cameraAngleY = buttons.addControl("cam pan right",-12,1);
		cameraAngleZ = buttons.addControl("cam tilt right",0,1);
		cameraX = buttons.addControl("cam x",1,0.01);
		cameraY = buttons.addControl("cam y",2, 0.01);
		cameraZ = buttons.addControl("cam z",6, 0.01);

		wholeModelAngleX = buttons.addControl("whole rot x", 0, 1 );
		wholeModelAngleY = buttons.addControl("whole rot y", 0, 1 );


		zoom = buttons.addControl("zoom", 2, 0.01 );

		thisthis = this;

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				goThingy.stop();
				buttons.dispose();

				System.exit(0);
			}
		});

		setVisible( true );  
	}

	// display().  Note that this fuction and most (all?) of the rest are 
	// handlers for the GLEventlistener interface.
	// display() is the main on that gets called to draw your stuff.  
	// The argument glDrawable is the context for drawing; the windows system
	// hands you that, the place you are going to draw on.
	public void display(GLAutoDrawable gLDrawable) 
	{
		// update();
		final GL2 gl = gLDrawable.getGL().getGL2(); // make the gl so we can draw

		// this section controls the camera
		// Note that the rotations (pan) are listed first so that they happen last.
		// This keeps the center of rotation at the camera.
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();
		// glu.gluPerspective(45.0f, h, 1.0, 20.0);
		gl.glFrustum( -zoom.q, zoom.q, -zoom.q ,zoom.q, 4, 20 );

		gl.glRotated(cameraAngleZ.q,0,0,1); // tilt the frame
		gl.glRotated(cameraAngleX.q, 1, 0, 0 ); // look down or up
		gl.glRotated(cameraAngleY.q, 0, 1, 0 );  // pan left or right
		gl.glTranslated(-cameraX.q, // this is moving the camera
				-cameraY.q, // to position, use negatives
				-cameraZ.q
				); // because it is reaally
		// accomplished by pushing the scene in the opposite direction


		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();
		gl.glRotated(wholeModelAngleX.q, 1.0, 0.0, 0.0 );   
		gl.glRotated(wholeModelAngleY.q, 0.0, 1.0, 0.0 );  

		setLighting(gl);
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);







		for(int j = 0; j<30; j++)
		{
			houses[j].drawMe(gl);
			//houses[j].report();
		}

		gl.glMaterialfv(GL.GL_FRONT, GL2.GL_AMBIENT_AND_DIFFUSE, green, 0);
		gl.glBegin( GL2.GL_POLYGON);
		gl.glVertex3d( 0,-0.5,15.5 );
		gl.glVertex3d(10,-0.5,15.5);
		gl.glVertex3d(10,-0.5,-0.5);;
		gl.glVertex3d(0,-0.5,-0.5);
		gl.glEnd();

		gl.glMaterialfv(GL.GL_FRONT, GL2.GL_AMBIENT_AND_DIFFUSE, gray, 0);
		double xs = 0.5; //start street point, house beginning
		double xt = 2;	//start street point, change to move whole thing?
		double xu = 7.5; //street length
		double xv = 1; //street width
		for(int k=0; k<3; k++)
		{
			gl.glBegin( GL2.GL_POLYGON);
			gl.glVertex3d( xs,-0.499,xt );
			gl.glVertex3d(xu,-0.499,xt);
			gl.glVertex3d(xu,-0.499,xv);
			gl.glVertex3d(xs,-0.499,xv);
			gl.glEnd();
			xt+=6;
			xv+=6;
		}
		
		gl.glBegin( GL2.GL_POLYGON);
		gl.glVertex3d( 7.5,-0.499,-0.5 );
		gl.glVertex3d(8.5,-0.499,-0.5);
		gl.glVertex3d(8.5,-0.499,15.5);
		gl.glVertex3d(7.5,-0.499,15.5);
		gl.glEnd();

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

	}


	// sets the lights and the material properties.
	public void setLighting( GL2 gl )
	{

		float pos[] = { 5.0f, 5.0f, 10.0f, 0.0f };

		gl.glEnable(GL2.GL_LIGHTING); // lighting system on
		gl.glEnable(GL2.GL_LIGHT0);   // light0 on
		gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, pos, 0); // light0 position
		//gl.glEnable(GL.GL_CULL_FACE);

		float[] lambi = { 0.3f, 0.3f, 0.5f,  1.0f };
		float[] lidif = { 0.2f, 0.2f, 0.2f, 1.0f };
		float[] lspec = { 1f, 1f, 1f, 1.0f };
		// float[] lipos = { 1.0f, 4.0f, 2.0f, 0.0f };
		gl.glLightfv( gl.GL_LIGHT0, gl.GL_AMBIENT , lambi, 0 );
		gl.glLightfv( gl.GL_LIGHT0, gl.GL_DIFFUSE , lidif, 0 );
		gl.glLightfv( gl.GL_LIGHT0, gl.GL_SPECULAR, lspec, 0 );
		// gl.glLightfv( gl.GL_LIGHT0, gl.GL_POSITION, lipos, 0 );

		gl.glMaterialfv(GL.GL_FRONT, GL2.GL_AMBIENT_AND_DIFFUSE, blue, 0);


		gl.glEnable(GL.GL_DEPTH_TEST);

	}



	public void dispose(GLAutoDrawable arg0) 
	{
		System.out.println("dispose() called");
	}

}