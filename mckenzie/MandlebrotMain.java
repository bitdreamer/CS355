package mckenzie;

//z = z^2 + c

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;

public class MandlebrotMain extends JFrame implements GLEventListener, MouseListener, MouseMotionListener
{
	private GLU glu = new GLU();
	GLProfile profile = GLProfile.get(GLProfile.GL2);
	GLCapabilities capabilities = new GLCapabilities(profile);
	
	// The canvas is the widget that's drawn in the JFrame
	GLCanvas glcanvas = new GLCanvas(capabilities);
	
	Mandlebrot m1;
	Dot c = new Dot();
	int dwell = 100;

	double xmin=-2, xmax=2, ymin=-2, ymax=2;
	int wid=500, hit=500;

	int mouseDownX=0, mouseDownY=0; // where you click the mouse
	int mouseX=0, mouseY=0;  // where you drag the mouse
	float white[] = { 1.0f, 1.0f, 1.0f, 1.0f};

	boolean change = false;

	public static void main(String[] args) 
	{
		new MandlebrotMain();
	}

	public MandlebrotMain()
	{

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Mandlebrot");

		glcanvas.addGLEventListener(this);
		
		glcanvas.setSize( wid, hit );
		getContentPane().add( glcanvas);
		setSize( getContentPane().getPreferredSize() );
		glcanvas.addMouseListener(this);
		glcanvas.addMouseMotionListener(this);

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
		
		//System.out.println("Change is " + change);
		if(change=true)
		{
			//mouseDownX=0;mouseX=0;mouseDownY=0;mouseY=0;
			change=false;
		}
		
		
		
		
		glu.gluOrtho2D( (double)xmin, (double)xmax, (double)ymin, (double)ymax);
		//glu.gluOrtho2D( xScreen2Model(mouseDownX), xScreen2Model(mouseX), yScreen2Model(mouseDownY), yScreen2Model(mouseY) );
		
		m1.drawMe(dwell, gl,(float)xmin,(float)ymin,(float)xmax,(float)ymax, wid, hit);

		gl.glColor3f( 1.0f, 1.0f, 1.0f );
		


		gl.glBegin( GL2.GL_LINE_LOOP);
		float mdX =  (float)(xScreen2Model(mouseDownX));
		float mX =  (float)(xScreen2Model(mouseX));
		float mdY =  (float)(yScreen2Model(mouseDownY));
		float mY =  (float)(yScreen2Model(mouseY));

		// snap box
		gl.glVertex2f( mdX, mdY );
		gl.glVertex2f( mX, mdY );
		gl.glVertex2f( mX, mY );
		gl.glVertex2f( mdX, mY );
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
		/*oWid=width;
		oHit=width;*/
		System.out.println("reshape() called: x = "+x+", y = "+y+", width = "+width+", height = "+height);
		final GL2 gl = gLDrawable.getGL().getGL2();

		if (height <= 0) // avoid a divide by zero error!
		{
			height = 1;
		}

		//final float h = (float) width / (float) height; // aspect ration

		gl.glViewport(0, 0, 500, 500);
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();

		
		//glu.gluOrtho2D( -2, 2, -2, 2 );
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();
	}
	

	public void dispose(GLAutoDrawable arg0) 
	{
		System.out.println("dispose() called");
	}

	public void mouseClicked(MouseEvent e) {
		System.out.println("mouse clicked");
		glcanvas.display();
	}

	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}

	public void mousePressed(MouseEvent e) 
	{
		System.out.println("mouse pressed at x=" + xScreen2Model(e.getX()) + " & y=" + yScreen2Model(e.getY()));
		//System.out.println("mouse pressed at x=" + e.getX() + " & y=" + e.getY());
		mouseDownX = e.getX();
		mouseDownY = e.getY();  

	}

	public void mouseReleased(MouseEvent e) 
	{
		System.out.println("mouseReleased");
		/*float txmax = (float)(xScreen2Model(mouseX));
        float txmin = (float)(xScreen2Model(mouseDownX));
        float tymax = (float)(yScreen2Model(mouseDownY));
        float tymin = (float)(yScreen2Model(mouseY));
        
        xmax = txmax;
        xmin = txmin;
        ymax = tymax;
        ymin = tymin;
        */
		
		xmax = (float)(xScreen2Model(mouseX));
        xmin = (float)(xScreen2Model(mouseDownX));
        ymax = (float)(yScreen2Model(mouseDownY));
        ymin = (float)(yScreen2Model(mouseY));
        
        change = true;
		glcanvas.display();
		
	}

	public void mouseDragged(MouseEvent e) 
	{
		System.out.println("mouse dragged");
		mouseX = e.getX(); mouseY = e.getY();
		glcanvas.display();
	}

	public void mouseMoved(MouseEvent e) {}

	public double xScreen2Model( int xScreen )
	{
		double xModel = 0;

		xModel = (1.0*xScreen)/wid * (xmax-xmin ) + xmin ;

		return xModel;    
	}

	public double yScreen2Model( int yScreen )
	{
		double yModel = 0;

		yModel = ymax - (1.0*yScreen)/hit * (ymax-ymin ) ;

		return yModel;    
	}

}
