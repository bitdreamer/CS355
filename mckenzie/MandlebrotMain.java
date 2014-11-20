package mckenzie;

//z = z^2 + c

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;

public class MandlebrotMain extends JFrame implements GLEventListener, MouseListener, MouseMotionListener
{
	private GLU glu = new GLU();
	Mandlebrot m1;
	Dot c = new Dot();
	int dwell = 100;
	GLProfile profile = GLProfile.get(GLProfile.GL2);
	GLCapabilities capabilities = new GLCapabilities(profile);

	// The canvas is the widget that's drawn in the JFrame
	GLCanvas glcanvas = new GLCanvas(capabilities);

	double xmin=-2, xmax=2, ymin=-2, ymax=2;
	int wid=500, hit=500;

	int mouseDownX=0, mouseDownY=0; // where you click the mouse
	int mouseX=500, mouseY=500;  // where you drag the mouse
	float white[] = { 1.0f, 1.0f, 1.0f, 1.0f};

	public static void main(String[] args) 
	{
		new MandlebrotMain();
	}

	public MandlebrotMain()
	{


		setTitle("Mandlebrot");

		glcanvas.addGLEventListener(this);
		glcanvas.addMouseListener(this);
		glcanvas.addMouseMotionListener(this);
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
		
		//glcanvas.reshape(mouseDownX, mouseDownY, mouseX, mouseY);
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
		//gl.glViewport(mouseDownX, mouseDownY, mouseX, mouseY);
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();

		// glu.gluPerspective(45.0f, h, 1.0, 20.0);
		//glu.gluOrtho2D( -2, 2, -2, 2 );
		//glu.gluOrtho2D( xScreen2Model(mouseDownX), yScreen2Model(mouseDownY),xScreen2Model(mouseX), yScreen2Model(mouseY) );
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

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {
		System.out.println("mouse pressed");
		mouseDownX = e.getX();
		mouseDownY = e.getY();  

	}

	public void mouseReleased(MouseEvent e) {
		//System.out.println("aojdfa");
		glcanvas.display();
		//glcanvas.zoom();
	}

	public void mouseDragged(MouseEvent e) {
		System.out.println("mouse dragged");
		mouseX = e.getX(); mouseY = e.getY();
		glcanvas.display();


	}

	public void mouseMoved(MouseEvent e) {


	}

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
