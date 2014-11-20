package sarah;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;

public class MandelFinTwo extends JFrame implements GLEventListener
{
	private GLU glu = new GLU();
	float xmin = -3f;
	float ymin = -1.5f;
	float w = 5;
	float h = 3;
	
	float wid = 1500;
	float hit = 1500;
	
	float xmax = xmin + w;
	float ymax = ymin + h;
	
	final int MAX = 35;
	
	public static void main(String[] args)
	{
		new MandelFinTwo();
		
	}

	public MandelFinTwo()
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
		float deltax = (xmax - xmin)/wid;
		float deltay = (ymax - ymin)/hit;
		
		float y = ymin;
		for(int i = 0; i < (int)hit; i++)
		{
			float x = xmin;
			
		
		for(int j = 0; j < (int)wid; j++)
		{
			float a = x;
			float b = y;
			int n = 0;
			
			while(n < MAX)
			{
				float aa = a*a;
				float bb = b*b;
				float twoab = 2*a*b;
				
				a = aa - bb + x;
				b = twoab + y;
				
				if(aa + bb > 16)
				{
					break;
					
				}
				n++;
			}
			
			if(n == MAX)
			{
				gl.glBegin(GL.GL_POINTS);
				gl.glColor3f(0.0f, 0.0f, 0.0f);
				gl.glVertex2f(x,y);
				gl.glEnd();
			}
			else
			{
				
				gl.glBegin(GL.GL_POINTS);
				float red = (n*13)/255;
				float green = (n*16)/255;
				float blue = (n*19)/255;
				gl.glColor3f(red, green, blue);
				gl.glVertex2f(x,y);
				gl.glEnd();
			}
			x += deltax;
			
		}
		y += deltay;
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



























