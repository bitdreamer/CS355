package sarah;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;

public class Mandelbrot extends JFrame implements GLEventListener
{
	private GLU glu = new GLU();
	private static final int loop=16;
	float xmin = -2;
	float xmax = 2;
	float ymin = -2;
	float ymax = 2;
	
	
	public static void main(String[] args)
	{
		new Mandelbrot();
		
	}
	
	public Mandelbrot()
	{
		setTitle("Mandelbrot");
		GLProfile profile = GLProfile.get(GLProfile.GL2);
	    GLCapabilities capabilities = new GLCapabilities(profile);
	 
	    GLCanvas glcanvas = new GLCanvas(capabilities);
	    glcanvas.addGLEventListener(this);
	    glcanvas.setSize( 300, 300 );
	    getContentPane().add( glcanvas);
	    setSize( getContentPane().getPreferredSize() );
	    setVisible( true );
	}
	
	public void display(GLAutoDrawable glDrawable)
	{
		final GL2 gl = glDrawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();
        float red[] =   {1.0f,0.0f,0.0f,1.0f};
        float green[] = { 0.0f, 1.0f, 0.3f, 1.0f };
        float blue[] =   {0.0f,0.0f,1.0f,1.0f}; // color red

		Color fractalColor = Color.red;
		int argb = fractalColor.getRGB();
		
		for(int i=0;i<300;i++)
		{
			for(int j=0;j<300;j++)
			{
				double a = xmin+i*(xmax-xmin)/300;
				double b = ymin+j*(ymax-ymin)/300;
				if(!equations(a,b))
					gl.glBegin(GL2.GL_POINTS );
			    gl.glColor4fv(red, 0);
			    gl.glVertex2f( i, j  );
			    //gl.glVertex2f( i, j  );
			   // gl.glVertex2f( i, j );
			    gl.glEnd();
				
			}
		}
		 gl.glFlush();
	}
public boolean equations(double one, double two) 
{
	double x = 0.0;
	double y = 0.0;
	int iterations = 0;
	do {
	  double xnew = x * x - y * y + one;
	  double ynew = 2 * x * y + two;
	  x = xnew;
	  y = ynew;
	  iterations++;
	  if (iterations == loop)
	  {
	    return false;
	  }
	} while (x <= 2 && y <= 2);
	return true;
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
