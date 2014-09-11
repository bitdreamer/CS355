package arocho;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseListener;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;

public class Sierpinski extends JFrame implements GLEventListener, MouseListener{
	
	//Class variables
	int depth = 0;
	private GLU glu = new GLU();
	
	//Select three points for the main triangle
	Dot d1 = new Dot(0.0f,2.0f);
	Dot d2 = new Dot (-2.0f,-2.0f);
	Dot d3 = new Dot(2.0f,-2.0f);
	
	//Dat main method doe
	public static void main(String[] args){
		new Sierpinski();
	}
	
	/**
	 * Constructor
	 */
	public Sierpinski(){
		setTitle("Triangleception");
		
		GLProfile profile = GLProfile.get(GLProfile.GL2);
		GLCapabilities capabilities = new GLCapabilities(profile);
		GLCanvas glcanvas = new GLCanvas(capabilities);
		
		glcanvas.addGLEventListener(this);
		glcanvas.addMouseListener(this);
		
		glcanvas.setSize(300, 300);
		getContentPane().add(glcanvas);
		setSize(getContentPane().getPreferredSize());
		
		setVisible(true);
	}
	
	/**
	 * Setter for depth
	 * @param depth - determines levels of Gasket
	 * @return int value for depth
	 */
	public int setDepth(int depth){
		this.depth = depth;
		return depth;
	}
	
	/**
	 * display contains all that there is to be drawn.
	 * The argument glDrawable is the context for drawing;
	 * the windows system hands you the place you will draw on
	 */
	public void display(GLAutoDrawable glDrawable) {	
		final GL2 gl = glDrawable.getGL().getGL2();
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
		
		float blue[] = {0.0f,0.0f,1.0f,1.0f};
		
		displayGasket(gl, 3, d1, d2, d3);
	}
	
	/**
	 * Recursively draws a gasket
	 * @param gl - enables drawing
	 * @param depth - sets depth of gasket
	 * @param d1 - Starting coordinates of the triangle (3 dots)
	 * @param d2
	 * @param d3
	 */
	public void displayGasket(GL2 gl, int depth, 
			Dot d1, Dot d2, Dot d3){
		//Base case
		if(depth == 0){
			gl.glColor3f( 1.0f, 1.0f, 1.0f );
			gl.glBegin(GL2.GL_LINE_LOOP);
				gl.glVertex2f(d1.getf()[0], d1.getf()[1]);
				gl.glVertex2f(d2.getf()[0], d2.getf()[1]);
				gl.glVertex2f(d3.getf()[0], d3.getf()[1]);
			gl.glEnd();
			gl.glFlush();
		}
		else {
			//This is where the magic happens
			Dot d12 = midpoint(d1,d2);
			Dot d23 = midpoint(d2,d3);
			Dot d31 = midpoint(d3,d1);
			
			displayGasket(gl, depth - 1, d1, d12, d31);
	        displayGasket(gl, depth - 1, d12, d2, d23);
	        displayGasket(gl, depth - 1, d31, d23, d3);
		}
	}
	
	/**
	 * Calculates the center between two given Dots
	 * @param d1 - Dots 1 and 2
	 * @param d2
	 * @return - midpoint
	 */
	public Dot midpoint(Dot d1, Dot d2){
		return new Dot((d1.x[0] + d2.x[0]) / 2, (d1.x[1] + d2.x[1]) / 2);
	}
	
	@Override
	public void init(GLAutoDrawable glDrawable) {
		System.out.println("init() called");
        GL2 gl = glDrawable.getGL().getGL2();
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glShadeModel(GL2.GL_FLAT);
	}

	@Override
	public void reshape(GLAutoDrawable glDrawable, int x, int y, int width,
			int height) {
		System.out.println("reshape() called: x = "+x+", y = "+y+", width = "+width+", height = "+height);
        final GL2 gl = glDrawable.getGL().getGL2();
        
        //Avoid a divide by zero error!
        if (height <= 0)
        {
            height = 1;
        }

        //Aspect ratio
        final float h = (float) width / (float) height;
        
        //Making the Viewport as big as the window. There can be multiple 
        //viewports in a single window
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();

        //Size of the world
        glu.gluOrtho2D( -4, 4, -4, 4 );
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
	}

	/**
	 * Draw a gasket every time the mouse is clicked
	 * @param m
	 */
	public void mouseClicked(MouseEvent m) {
		d1 = new Dot(m.getX(), m.getY());
		d2 = new Dot (m.getX() - 2.0, m.getY() - 4.0);
		d3 = new Dot(m.getX() + 2.0, m.getY() - 4.0);

	}
//Must convert to viewport coordinates to be able to create more coordinates.
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void dispose(GLAutoDrawable arg0) {}
	
}