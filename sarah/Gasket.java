// Sierpinski Gasket Program 2014
// NOTE: I don't think this changes levels. I didn't do the recursive way
// because I'm not sure I was doing it right but I will try to look more into
// it to see how I can do this recursively. The program also take a few seconds 
// to show up because there are so many dots. I tried putting the part of the code
// where I make the dots outside of the loop so it doesn't take a while to load
// but then the dots don't show up if you put it outside of the loop.

package sarah;


import java.awt.Color;
import java.awt.Graphics;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;

public class Gasket  extends JFrame implements GLEventListener
{
	int choices;			// The variable choices determines a number from 1
							// to 3 which is how many sides a triangle has and 
							// calculates the length of that specific line
  	
	float x1=0.0f;			// These are the end points of the triangle which I
							// will use to draw a line to make a triangle
    float y1=2.0f;
    float x2=-2.0f;
    float y2=-2.0f;
    float x3=2.0f;
    float y3=-2.0f;
    
    float x=0.0f;			// These will be the center coordinates of the screen 
    						// from where the new triangles will be drawn
    float y=0.0f;
    float dx;				// the dx and dy are the midpoints of a specific triangle side
    float dy;
    private GLU glu = new GLU();
    
   public static void main( String[] args )
   {
      System.out.println("Sierpinski Gasket Program");
      new Gasket();
   }
   public Gasket()
   {
      setTitle("Sierpinski Gasket");
      GLProfile profile = GLProfile.get(GLProfile.GL2);
      GLCapabilities capabilities = new GLCapabilities(profile);
      GLCanvas glcanvas = new GLCanvas(capabilities);
      glcanvas.addGLEventListener(this);
      glcanvas.setSize( 500, 500 );
      getContentPane().add( glcanvas);
      setSize( getContentPane().getPreferredSize() );
      setVisible( true );
    }
 
   
   public void display(GLAutoDrawable gLDrawable) 
   {
	   final GL2 gl = gLDrawable.getGL().getGL2(); // This statement lets us draw
       gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT); // Clears the screen
       gl.glLoadIdentity();
      
       float red[] =   {1.0f,0.0f,0.0f,1.0f}; // Red color
       float green[] = { 0.0f, 1.0f, 0.3f, 1.0f }; // Green color
       float blue[] =   {0.0f,0.0f,1.0f,1.0f}; // Blue Color
       
       // Drawing the big/main triangle
       gl.glBegin(GL2.GL_LINES);
       gl.glColor4fv(red, 0);
       gl.glVertex2f(x1, y1);
       gl.glVertex2f(x2, y2);
       gl.glEnd();
       gl.glBegin(GL2.GL_LINES);
       gl.glColor4fv(red, 0);
       gl.glVertex2f(x1, y1);
       gl.glVertex2f(x3, y3);
       gl.glEnd();
       gl.glBegin(GL2.GL_LINES);
       gl.glColor4fv(red, 0);
       gl.glVertex2f(x2, y2);
       gl.glVertex2f(x3, y3);
       gl.glEnd();
    
	   artthis(gl); // Here I'm calling the artthis method which finds the midpoints 
	   // of each side of the triangle so I can draw a new line from the midpoint
	   // of one side to the midpoint of another midpoint on another side
       gl.glFlush();
 
   }

   public void artthis(GL2 gl)
   {
	   float red[] =   {1.0f,0.0f,0.0f,1.0f}; // Red Color
       float green[] = { 0.0f, 1.0f, 0.3f, 1.0f }; // Green Color
       float blue[] =   {0.0f,0.0f,1.0f,1.0f}; // Blue Color
	
       for(int i=0; i < 30000; i++) // Loop that make points that shape into mini triangles
	   {
		   choices = (int) (Math.random() * 3) + 1; // Randomly choosing a number from 1 to 3
		   // since there are three sides to a triangle

		   // Below, depending on which number is randomly generated, I am finding the length of
		   // each of the three sides, set a color, and draw dots. I did it this way because I really
		   // liked the color theme it created. It makes the triangle very colorful this way.
		   if(choices==1)
	    	{
	    		dx=(x-x1);
	    		dy=(y-y1);	
	    		gl.glColor4fv(green, 0); 
	    		gl.glBegin(GL2.GL_POINTS); // Need this to be able to make dots 
		    	gl.glVertex2f(x,y); // The points that will be drawn 
		    	gl.glEnd();
	    	}
	    	else if(choices==2)
	    	{
	    		dx=(x-x2);
	    		dy=(y-y2);
	    		gl.glColor4fv(red, 0); 
	    		gl.glBegin(GL2.GL_POINTS); // Need this to be able to make dots 
		    	gl.glVertex2f(x,y); // The points that will be drawn 
		    	gl.glEnd();
	    	}
	    	else
	    	{
	    		dx=(x-x3);
	    		dy=(y-y3);
	    		gl.glColor4fv(blue, 0);
	    		gl.glBegin(GL2.GL_POINTS); // Need this to be able to make dots 
		    	gl.glVertex2f(x,y); // The points that will be drawn 
		    	gl.glEnd();
	    	}
	    	
	    	x=x-dx/2; // Here I'm finding the midpoint of a line
	    	y=y-dy/2;
	    
	    gl.glFlush();
	    	
	  
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
   
