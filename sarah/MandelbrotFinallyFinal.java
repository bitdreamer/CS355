// Mandelbrot!
package sarah; 
import java.awt.Color;
import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;
 
public class MandelbrotFinallyFinal extends JFrame implements GLEventListener
{
	private GLU glu = new GLU();
    private final int numiterations = 570;
    private final double image = 130;
   
    private double xvalue;
    private double yvalue;
    private double cenX;
    private double cenY;
    private double tmp;
    public static void main(String[] args)
	{
		new MandelbrotFinallyFinal();
		
	}
 
    public MandelbrotFinallyFinal() 
    {
    	setTitle("MandelbrotFinal");
		
		 GLProfile profile = GLProfile.get(GLProfile.GL2);
		 GLCapabilities capabilities = new GLCapabilities(profile);
		 GLCanvas glcanvas = new GLCanvas(capabilities);

		 glcanvas.addGLEventListener(this);

		 glcanvas.setSize(500, 500);
		 getContentPane().add(glcanvas);
		 setSize(getContentPane().getPreferredSize());

		 setVisible(true);
    }
    
    public void display(GLAutoDrawable glDrawable)
    {
    	final GL2 gl = glDrawable.getGL().getGL2();
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
		drawMandelbrot(gl);
		gl.glFlush();
    }
    public void drawMandelbrot(GL2 gl)
    {
    	for (int y = 0; y < 700; y++) // initial y value
    	{
            for (int x = 0; x < 700; x++) // initial x value
            {
                xvalue = yvalue = 0; // They start at zero
                // Change in x and y
                cenX = (x - 400) / image; 
                cenY = (y - 300) / image;
                int iter = 35;
                while (xvalue * xvalue + yvalue * yvalue < 4 && iter > 0) 
                {
                    tmp = xvalue * xvalue - yvalue * yvalue + cenX;
                    yvalue = 2.0 * xvalue * yvalue + cenY;
                    xvalue = tmp;
                    iter--;
                    
                    if(iter == numiterations)
                	{
                		gl.glBegin(GL.GL_POINTS);
            			gl.glColor3f(0.0f, 0.0f, 0.0f);
            			gl.glVertex2f((float)cenX,(float)cenY);
            			gl.glEnd();
                	}
                    else
        			{
        				gl.glBegin(GL.GL_POINTS);
        				float red = (iter*12)/255;
        				float green = (iter*17)/255;
        				float blue = (iter*18)/255;
        				gl.glColor3f(red, green, blue);
        				gl.glVertex2f((float)cenX,(float)cenY);
        				gl.glEnd();
        			}
                }
            }
        }
    	
    	    	
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