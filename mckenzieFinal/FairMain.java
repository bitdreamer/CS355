//FairMain.java
//Main file for the FairGrounds scene
//Final project CS355 Fall 2014
//McKenzie Bradley

package mckenzieFinal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;

import threed.Bird;
import mckenzie.House;

import com.jogamp.opengl.util.Animator;
import com.jogamp.opengl.util.gl2.GLUT;

public class FairMain extends JFrame implements GLEventListener, ActionListener
{
	private GLU glu = new GLU();
	Animator goThingy;

	GLCanvas glcanvas;
	GLUT glut;

	Spherey cups[];
	Person people[];
	int pplCount= 40;

	float red[] =   {1.0f,0.0f,0.0f,1.0f};
	float green[] = { 0.0f, 1.0f, 0.3f, 1.0f };
	float blue[] = { 0.0f, 0.3f, 1.0f, 1.0f};
	float black[] = {0.0f,0.0f,0.0f,1.0f};
	float white[] = {1.0f,1.0f,1.0f,1.0f};
	float orange[] = {0.9f,0.3f,0.0f,1.0f};

	BP cameraAngleX; // tilt the direction of gaze down (or minus is up)
	BP cameraAngleY; // pan left or right on camera
	BP cameraAngleZ;

	BP cameraX; // position of the camera
	BP cameraY;
	BP cameraZ;

	BP wholeModelAngleX; //tumbles the whole model toward us
	BP wholeModelAngleY; // spins the whole model around a vertical axis

	BP zoom; // controls the cameral angle width

	Control buttons;


	double ferRot=0.0;

	float c1start1 = 0;
	float c1start2 = -2;
	float c2start1 = -2;
	float c2start2 = 0;
	float c3start1 = 0;
	float c3start2 = 2;
	float c4start1 = 2;
	float c4start2 = 0;

	Seat seat1 = new Seat();

	javax.swing.Timer clicky;
	long lastTime;

	public static void main(String[] args) 
	{
		new FairMain();
	}

	public FairMain()
	{
		setTitle("Fairgrounds");
		GLProfile profile = GLProfile.get(GLProfile.GL2);
		GLCapabilities capabilities = new GLCapabilities(profile);

		glcanvas = new GLCanvas(capabilities);
		glcanvas.addGLEventListener(this);

		setSize( 500, 500 );
		add( glcanvas);

		goThingy = new Animator( glcanvas );
		goThingy.start();

		people = new Person[pplCount];
		for ( int i=0; i<pplCount; i++ )
		{
			double d1 = Math.random();
			float f1 = (float) d1;

			double d2 = Math.random();
			float f2 = (float) d2;

			double d3 = Math.random();
			float f3 = (float) d3;

			float cColor[] = {f1,f2,f3,1.0f};
			people[i] = new Person(cColor);
		}


		cups = new Spherey[5];

		for(int x=0;x<5;x++)
		{
			cups[x] = new Spherey(orange,x+6,x+3);
		}


		buttons = new Control( ) ;
		cameraAngleX = buttons.addControl("cam look down",20,1);
		cameraAngleY = buttons.addControl("cam pan right",85,1);
		cameraAngleZ = buttons.addControl("cam tilt right",0,1);
		cameraX = buttons.addControl("cam x",-5,0.01);
		cameraY = buttons.addControl("cam y",5, 0.01);
		cameraZ = buttons.addControl("cam z",6, 0.01);
		wholeModelAngleX = buttons.addControl("whole rot x", 0, 1 );
		wholeModelAngleY = buttons.addControl("whole rot y", 0, 1 );
		zoom = buttons.addControl("zoom", 2, 0.01 );

		clicky = new javax.swing.Timer( 100, this );
		clicky.start();


		Date date = new Date(); // now
		lastTime = date.getTime(); // set lastTime to now


		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				goThingy.stop();
				buttons.dispose();

				System.exit(0);
			}
		});

		setVisible( true );

	}

	public void display(GLAutoDrawable gLDrawable) 
	{

		final GL2 gl = gLDrawable.getGL().getGL2();
		setLighting(gl);
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);


		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glFrustum( -zoom.q, zoom.q, -zoom.q ,zoom.q, 4, 20 );
		gl.glRotated(cameraAngleZ.q,0,0,1);
		gl.glRotated(cameraAngleX.q, 1, 0, 0 );
		gl.glRotated(cameraAngleY.q, 0, 1, 0 );
		gl.glTranslated(-cameraX.q, -cameraY.q, -cameraZ.q);

		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();
		gl.glRotated(wholeModelAngleX.q, 1.0, 0.0, 0.0 );   
		gl.glRotated(wholeModelAngleY.q, 0.0, 1.0, 0.0 );  



		gl.glMaterialfv(GL.GL_FRONT, GL2.GL_AMBIENT_AND_DIFFUSE, green, 0);
		gl.glBegin( GL2.GL_POLYGON);
		gl.glVertex3d( -50,-0.5,50 );
		gl.glVertex3d(50,-0.5,50);
		gl.glVertex3d(50,-0.5,-50);;
		gl.glVertex3d(-50,-0.5,-50);
		gl.glEnd();

		//People
		for ( int i=0; i<pplCount-3; i++ )
		{
			people[i].drawMe(gl);
		}



		//Tents
		//HAHA THIS IS HORRIBLE
		Tent h1 = new Tent(2,0,blue,white);
		h1.drawMe(gl);
		Tent h2 = new Tent(5,1,blue,red);
		h2.drawMe(gl);
		Tent h3 = new Tent(8,2,black,red);
		h3.drawMe(gl);
		Tent h4 = new Tent(10,3,red,black);
		h4.drawMe(gl);
		Tent h5 = new Tent(9,4,white,orange);
		h5.drawMe(gl);
		Tent h6 = new Tent(13,5,red,blue);
		h6.drawMe(gl);
		Tent h7 = new Tent(8,6,blue,red);
		h7.drawMe(gl);
		Tent h8 = new Tent(11,7,orange,blue);
		h8.drawMe(gl);
		Tent h9 = new Tent(9,8,black,blue);
		h9.drawMe(gl);
		Tent h10 = new Tent(12,9,white,blue);
		h10.drawMe(gl);
		Tent h11 = new Tent(7,10,red,blue);
		h11.drawMe(gl);
		Tent h1b = new Tent(0,-2,blue,white);
		h1b.drawMe(gl);
		Tent h2b = new Tent(1,11,blue,red);
		h2b.drawMe(gl);
		Tent h3b = new Tent(-3,2,black,red);
		h3b.drawMe(gl);
		Tent h4b = new Tent(3,12,blue,black);
		h4b.drawMe(gl);
		Tent h5b = new Tent(1,4,red,orange);
		h5b.drawMe(gl);
		Tent h6b = new Tent(4,13,blue,orange);
		h6b.drawMe(gl);
		Tent h7b = new Tent(0,6,white,white);
		h7b.drawMe(gl);
		Tent h8b = new Tent(6,12,white,blue);
		h8b.drawMe(gl);
		Tent h9b = new Tent(-1,8,black,blue);
		h9b.drawMe(gl);
		Tent h10b = new Tent(9,12,orange,red);
		h10b.drawMe(gl);
		Tent h11b = new Tent(3,10,white,red);
		h11b.drawMe(gl);


		glut = new GLUT();

		//Teapot
		gl.glMaterialfv(GL.GL_FRONT, GL2.GL_AMBIENT_AND_DIFFUSE, red, 0);
		gl.glTranslatef(5,(float) 0.25,5);
		gl.glRotatef((float) 90.0,0.0f,-1.5f,0.0f);
		glut.glutSolidTeapot(1);
		//Teapot fence
		gl.glMaterialfv(GL.GL_FRONT, GL2.GL_AMBIENT_AND_DIFFUSE, white, 0);
		gl.glTranslatef(0,-1,0);
		gl.glRotatef((float)90.0,1.0f,0.0f,0.0f);
		glut.glutWireTorus((float)1,(float)3.5,3,10);
		//Teapot cups
		gl.glMaterialfv(GL.GL_FRONT, GL2.GL_AMBIENT_AND_DIFFUSE, orange, 0);
		gl.glTranslatef(0,0,(float)-0.75);
		gl.glTranslatef(c1start1,c1start2,0);
		cups[0].drawMe(gl);
		gl.glTranslatef(c1start1*(-1),c1start2*(-1),0);
		gl.glTranslatef(c2start1,c2start2,0);
		cups[1].drawMe(gl);
		gl.glTranslatef(c2start1*(-1),c2start2*(-1),0);
		gl.glTranslatef(c3start1,c3start2,0);
		cups[2].drawMe(gl);
		gl.glTranslatef(c3start1*(-1),c3start2*(-1),0);
		gl.glTranslatef(c4start1,c4start2,0);
		cups[3].drawMe(gl);
		gl.glTranslatef(c4start1*(-1),c4start2*(-1),0);
		gl.glTranslatef(0,0,(float)0.75);


		//Ferris wheel frame
		gl.glMaterialfv(GL.GL_FRONT, GL2.GL_AMBIENT_AND_DIFFUSE, black, 0);
		gl.glRotatef((float)90.0,0.0f,1.0f,0.0f);
		gl.glTranslatef(4, (float)0.75,-7);
		glut.glutWireTorus((float)0.5,2,2,20);
		gl.glTranslatef(0,0,-2);
		glut.glutWireTorus((float)0.5,2,2,20);

		//Ferris wheel seats
		gl.glTranslatef(0,0,1);
		for ( int i=-1; i<2; i += 2 )
		{
			for ( int j=-1; j<2; j += 2 )
			{
				for ( int k=-1; k<2; k += 2 )
				{

					gl.glPushMatrix();

					gl.glRotated( ferRot, 0, 0, 1 );

					gl.glTranslated( i, j, 0 );
					gl.glRotated( -ferRot, 0, 0, 1 );

					gl.glScaled(  .75, .75, .75 );

					seat1.drawMe(gl);

					gl.glPopMatrix();
				}
			}

		}




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
		gl.glClearColor(0.52f, 0.81f, 0.9f, 0.0f);
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
		gl.glFrustum( -2, 2, -2 ,2, 4, 20 );
		gl.glTranslatef(0.0f, 0.0f, -6.0f);


		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();
		gl.glRotated( 20.0, 0.0, 1.0, 0.0 );  
		gl.glRotated( 40.0, 1.0, 0.0, 0.0 );

	}

	public void setLighting( GL2 gl )
	{

		float pos[] = { 5.0f, 5.0f, 10.0f, 0.0f };

		gl.glEnable(GL2.GL_LIGHTING); // lighting system on
		gl.glEnable(GL2.GL_LIGHT0);   // light0 on
		gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, pos, 0); // light0 position

		float[] lambi = { 0.3f, 0.3f, 0.5f,  1.0f };
		float[] lidif = { 0.1f, 0.1f, 0.1f, 1.0f };
		gl.glLightfv( gl.GL_LIGHT0, gl.GL_AMBIENT , lambi, 0 );
		gl.glLightfv( gl.GL_LIGHT0, gl.GL_DIFFUSE , lidif, 0 );

		gl.glMaterialfv(GL.GL_FRONT, GL2.GL_AMBIENT_AND_DIFFUSE, blue, 0);


		gl.glEnable(GL.GL_DEPTH_TEST);
	}

	public void dispose(GLAutoDrawable arg0) 
	{
		System.out.println("ispose() called");
	}
	public void actionPerformed( ActionEvent e )
	{
		Date date = new Date();
		long current = date.getTime();

		// time since last update in seconds
		double deltat = (current - lastTime)/1000.0; 

		//bird1.move(deltat);
		for ( int i=0; i<pplCount; i++ )
		{
			people[i].move(deltat);
		}


		lastTime = current;

		//ferris movement
		ferRot++;




		//movement patterns for the cups...this is bad, could be OO
		//cup 1
		if(c1start1<=2 && c1start1>=0 && c1start2>=-2 && c1start2<0)
		{
			c1start1+=0.1;
			c1start2+=0.1;
		}
		else if(c1start1<=2 && c1start1>0 && c1start2>=0 && c1start2<=2)
		{
			c1start1-=0.1;
			c1start2+=0.1;	
		}
		else if(c1start1<0 && c1start1>=-2 && c1start2>=0 && c1start2<=2)
		{
			c1start1-=0.1;
			c1start2-=0.1;	
		}
		else if(c1start1<=0 && c1start1>=-2 && c1start2>=-2 && c1start2<0)
		{
			c1start1+=0.1;
			c1start2-=0.1;	
		}

		if(c1start1>2)
		{
			c1start1-=0.1;
		}
		if(c1start1<-2)
		{
			c1start1+=0.1;

		}
		if(c1start2>2)
		{
			c1start2-=0.1;
		}
		if(c1start2<-2)
		{
			c1start2+=0.1;
		}


		//cup 2
		if(c2start1<=2 && c2start1>=0 && c2start2>=-2 && c2start2<0)
		{
			c2start1+=0.1;
			c2start2+=0.1;
		}
		else if(c2start1<=2 && c2start1>0 && c2start2>=0 && c2start2<=2)
		{
			c2start1-=0.1;
			c2start2+=0.1;	
		}
		else if(c2start1<0 && c2start1>=-2 && c2start2>=0 && c2start2<=2)
		{
			c2start1-=0.1;
			c2start2-=0.1;	
		}
		else if(c2start1<=0 && c2start1>=-2 && c2start2>=-2 && c2start2<0)
		{
			c2start1+=0.1;
			c2start2-=0.1;	
		}

		if(c2start1>2)
		{
			c2start1-=0.1;
		}
		if(c2start1<-2)
		{
			c2start1+=0.1;

		}
		if(c2start2>2)
		{
			c2start2-=0.1;
		}
		if(c2start2<-2)
		{
			c2start2+=0.1;
		}


		//cup 3
		if(c3start1<=2 && c3start1>=0 && c3start2>=-2 && c3start2<0)
		{
			c3start1+=0.1;
			c3start2+=0.1;
		}
		else if(c3start1<=2 && c3start1>0 && c3start2>=0 && c3start2<=2)
		{
			c3start1-=0.1;
			c3start2+=0.1;	
		}
		else if(c3start1<=0 && c3start1>=-2 && c3start2>=0 && c3start2<=2)
		{
			c3start1-=0.1;
			c3start2-=0.1;	
		}
		else if(c3start1<0 && c3start1>=-2 && c3start2>=-2 && c3start2<0)
		{
			c3start1+=0.1;
			c3start2-=0.1;	
		}

		if(c3start1>2)
		{
			c3start1-=0.1;
		}
		if(c3start1<-2)
		{
			c3start1+=0.1;

		}
		if(c3start2>2)
		{
			c3start2-=0.1;
		}
		if(c3start2<-2)
		{
			c3start2+=0.1;
		}


		//cup 4
		if(c4start1<=2 && c4start1>=0 && c4start2>=-2 && c4start2<0)
		{
			c4start1+=0.1;
			c4start2+=0.1;
		}
		else if(c4start1<=2 && c4start1>0 && c4start2>=0 && c4start2<=2)
		{
			c4start1-=0.1;
			c4start2+=0.1;	
		}
		else if(c4start1<0 && c4start1>=-2 && c4start2>=0 && c4start2<=2)
		{
			c4start1-=0.1;
			c4start2-=0.1;	
		}
		else if(c4start1<=0 && c4start1>=-2 && c4start2>=-2 && c4start2<0)
		{
			c4start1+=0.1;
			c4start2-=0.1;	
		}

		if(c4start1>2)
		{
			c4start1-=0.1;
		}
		if(c4start1<-2)
		{
			c4start1+=0.1;

		}
		if(c4start2>2)
		{
			c4start2-=0.1;
		}
		if(c4start2<-2)
		{
			c4start2+=0.1;
		}
	}

}