package arocho;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;

public class Mandelbrot extends JFrame implements GLEventListener{
	
	//Class Variables
	private GLU glu = new GLU();
	//coordinates
	float xmin = -3f;
	float ymin = -1.5f;
	float w = 5;	//width of plane
	float h = 3;	//height of plane
	
	float wid = 1500;
	float hit = 1500;
	
	//x has to go from its minimum value to its maximum value
	float xmax = xmin + w;
	//y has to go from its minimum value to its maximum value
	float ymax = ymin + h;
	
	final int MAX = 35;
	
	public static void main(String[] args){
		new Mandelbrot();
	}
	
	/**
	 * Constructor
	 */
	public Mandelbrot(){
		setTitle("Mandel Bro");
		
		GLProfile profile = GLProfile.get(GLProfile.GL2);
		GLCapabilities capabilities = new GLCapabilities(profile);
		GLCanvas glcanvas = new GLCanvas(capabilities);
		
		glcanvas.addGLEventListener(this);
		
		glcanvas.setSize(500, 500);
		getContentPane().add(glcanvas);
		setSize(getContentPane().getPreferredSize());
		
		setVisible(true);
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
		drawMandelbrot(gl);//add the method call that actually draws the Mandelbrot in this spot.
		gl.glFlush();
	}
	
	public void drawMandelbrot(GL2 gl){
		
		//Calculate the deltas of x and y for each iteration
		float deltax = (xmax - xmin) / wid;
		float deltay = (ymax - ymin) / hit;
		
		//Start y at its minimum value
		float y = ymin;
		for (int i = 0; i < (int)hit; i++){
			//Start x at its minimum value
			float x = xmin;
			for (int j = 0; j < (int)wid; j++){
				//Do some complex math and make place-holders
				float a = x;
				float b = y;
				int n = 0;
				
				//Does the input tend to infinity?
				while(n < MAX){
					//Square 'em!
					float aa = a * a;
					float bb = b * b;
					float twoab = 2 * a * b;
					
					a = aa - bb + x;
					b = twoab + y;
					
					if(aa + bb > 16){
						break;
					}
					n++;
				}
				
					if (n == MAX){
						gl.glBegin (GL.GL_POINTS);
							gl.glColor3f (0.0f, 0.0f, 0.0f);
							gl.glVertex2f(x,y);
						gl.glEnd ();
					}
					else{
						gl.glBegin (GL.GL_POINTS);
							//funky colors
							float red = (n*13)/255;
							float green = (n*16)/255;
							float blue = (n*19)/255;
							gl.glColor3f (red, green, blue);
							gl.glVertex2f(x,y);
							gl.glEnd ();
				}
				x += deltax;
			}
			y += deltay;
		}
	}
	
	@Override
	public void init(GLAutoDrawable glDrawable) {
		System.out.println("init() called");
        GL2 gl = glDrawable.getGL().getGL2();
        gl.glClearColor(255f, 255f, 255f, 255.0f);
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
        glu.gluOrtho2D( -2, 2, -2, 2);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
	}
	
		// From Mousing.java 
		//These convert pixels to model coords so we can draw.  
		// They are not quite right ... missing an offset or something.
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

	@Override
	public void dispose(GLAutoDrawable arg0) {}
}