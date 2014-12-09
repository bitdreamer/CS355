// Suburb.java

package arocho.neighborhood;

import java.awt.*;
import java.awt.event.*; 

import javax.swing.*;
import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;

import com.jogamp.opengl.util.*;
import com.jogamp.opengl.util.gl2.GLUT;

public class Suburb  extends JFrame implements GLEventListener
{
	private GLU glu = new GLU(); // just has some function we like
	Animator goThingy;
	final Suburb thisthis; // for use in contexts where "this" doesn't work
	double yrot = 0;

	double aspectRatio = 1;

	GLCanvas glcanvas;

	BP cameraAngleX; // tilt the direction of gaze down (or minus is up)
	BP cameraAngleY; // pan left or right on camera
	BP cameraAngleZ;

	BP cameraX; // position of the camera
	BP cameraY;
	BP cameraZ;

	BP wholeModelAngleX; //tumbles the whole model toward us
	BP wholeModelAngleY; // spins the whole model around a vertical axis

	BP zoom; // controls the cameral angle width
	BP drive;
	ControlStuff4 buttons;

	House home;
	Ground grass;
	Ground road;
	Car car;

	public static void main(String[] args) 
	{
		new Suburb();
	}

	public Suburb()
	{      
		GLProfile profile = GLProfile.get(GLProfile.GL2);
		GLCapabilities capabilities = new GLCapabilities(profile);
		glcanvas = new GLCanvas(capabilities);
		glcanvas.addGLEventListener(this);

		setSize( 500, 500 );
		add(glcanvas);

		goThingy = new Animator( glcanvas );
		goThingy.start();

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
		drive = buttons.addControl("drive", 0, 0.01);
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
		gl.glFrustum( -zoom.q*aspectRatio, zoom.q*aspectRatio, -zoom.q ,zoom.q, 4, 20 );

		gl.glRotated(cameraAngleZ.q,0,0,1); // tilt the frame
		gl.glRotated(cameraAngleX.q, 1, 0, 0 ); // look down or up
		gl.glRotated(cameraAngleY.q, 0, 1, 0 );  // pan left or right
		gl.glTranslated(-cameraX.q, // this is moving the camera
				-cameraY.q, // to position, use negatives
				-cameraZ.q
				); // because it is really
		// accomplished by pushing the scene in the opposite direction

		// this section controls the position of the big cube
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();
		gl.glRotated(wholeModelAngleX.q, 1.0, 0.0, 0.0 );   
		gl.glRotated(wholeModelAngleY.q, 0.0, 1.0, 0.0 );  

		setLighting(gl);
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

		//Not the best way to make houses. Must refactor.
		//Casa #1
		gl.glPushMatrix();
		gl.glTranslatef( -3, -2.9f, -2f);
		gl.glRotated( 45, 0, 1, 0 );
		home = new House(0, 0, 0);
		home.drawMe(gl);
		gl.glPopMatrix();

		//Casa #2
		gl.glPushMatrix();
		gl.glTranslatef( -2, -2.9f, 1);
		gl.glRotated( -45, 0, 1, 0 );
		home = new House(0, 0, 0);
		home.drawMe(gl);
		gl.glPopMatrix();

		//Casa #3
		gl.glPushMatrix();
		gl.glTranslatef( 1, -2.9f, -2f);
		gl.glRotated( 50, 0, 1, 0 );
		home = new House(0, 0, 0);
		home.drawMe(gl);
		gl.glPopMatrix();

		//Casa #4
		gl.glPushMatrix();
		gl.glTranslatef( 1, -2.9f, 2);
		gl.glRotated( -50, 0, 1, 0 );
		home = new House(0, 0, 0);
		home.drawMe(gl);
		gl.glPopMatrix();

		//Casa #5
		gl.glPushMatrix();
		gl.glTranslatef( 4, -2.9f, -2);
		home = new House(0, 0, 0);
		home.drawMe(gl);
		gl.glPopMatrix();

		//Casa #6
		gl.glPushMatrix();
		gl.glTranslatef( 4, -2.9f, 1);
		home = new House(0, 0, 0);
		home.drawMe(gl);
		gl.glPopMatrix();

		//Ground drawing setup
		float forest[] = { 0.1f, 0.5f, 0.1f, 1.0f };
		float gravel[] = { 0.7f, 0.7f, 0.7f, 1.0f };
		grass = new Ground( 6, 6, forest);
		road = new Ground(3, 1, gravel);

		//Draws grass
		gl.glPushMatrix();
		gl.glTranslatef( 0, -3, 0 );
		grass.drawMe(gl);
		gl.glPopMatrix();


		//Draws road
		gl.glPushMatrix();
		gl.glTranslatef( 3, -2.9f, 0 );
		road.drawMe(gl);
		gl.glPopMatrix();

		//Draws cul de sac
		drawCircle(gl, 0, -2.9f, 0);
		
		car = new Car(drive.q, -2.9, 0);
		gl.glPushMatrix();
			car.drawMe(gl);
		gl.glPopMatrix();

		gl.glFlush();
	}

	public void drawCircle( GL gl, float xc, float yc, float zc ) 
	{
		double r = 2.0f; // radius.

		((GL2) gl).glBegin( GL.GL_TRIANGLE_FAN ); {
			((GL2) gl).glVertex3f( xc, yc, zc ); // center.
			for( float a = 0;  a <= 360; a+=10 ) {
				float ang = (float) Math.toRadians( a );
				float x = xc + (float) (r*Math.cos( ang ));
				float y = yc + 0f; 
				float z = zc + (float) (r*Math.sin( ang ));
				((GL2) gl).glVertex3f( x, y, z );
			}
		} ((GL2) gl).glEnd();

	}


	public void displayChanged(GLAutoDrawable gLDrawable, boolean modeChanged, boolean deviceChanged) 
	{
		System.out.println("displayChanged called");
	}

	public void init(GLAutoDrawable gLDrawable) 
	{
		System.out.println("init() called");
		GL2 gl = gLDrawable.getGL().getGL2();
		gl.glClearColor(0.5f, 0.8f, 1.0f, 0.0f);
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

		aspectRatio = (float) width / (float) height; // aspect ratio

		gl.glViewport(0, 0, width, height);

	}

	// sets the lights and the material properties.
	public void setLighting( GL2 gl )
	{

		float pos[] = { 5.0f, 5.0f, 10.0f, 0.0f };

		gl.glEnable(GL2.GL_LIGHTING); // lighting system on
		gl.glEnable(GL2.GL_LIGHT0);   // light0 on
		gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, pos, 0); // light0 position

		float[] lambi = { 0.3f, 0.3f, 0.5f,  1.0f };
		float[] lidif = { 0.2f, 0.2f, 0.2f, 1.0f };
		float[] lspec = { 1f, 1f, 1f, 1.0f };

		gl.glLightfv( gl.GL_LIGHT0, gl.GL_AMBIENT , lambi, 0 );
		gl.glLightfv( gl.GL_LIGHT0, gl.GL_DIFFUSE , lidif, 0 );
		gl.glLightfv( gl.GL_LIGHT0, gl.GL_SPECULAR, lspec, 0 );
		gl.glEnable(GL.GL_DEPTH_TEST);

	}



	public void dispose(GLAutoDrawable arg0) 
	{
		System.out.println("dispose() called");
	}

}