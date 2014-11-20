// TwoTemplate.java
// Barrett Koster 2014
// This file is a template for doing 2D drawings in OpenGL.  

package sarah;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;
 
public class CopyOfTwoTemplate extends JFrame implements GLEventListener
{
   private GLU glu = new GLU(); // just has some function we like
   
    public static void main(String[] args) 
    {
       new CopyOfTwoTemplate();
    }
    
    public CopyOfTwoTemplate()
   {
      setTitle("TwoTemplate");
      GLProfile profile = GLProfile.get(GLProfile.GL2);
      GLCapabilities capabilities = new GLCapabilities(profile);
 
      // The canvas is the widget that's drawn in the JFrame
      GLCanvas glcanvas = new GLCanvas(capabilities);
      glcanvas.addGLEventListener(this);
      glcanvas.setSize( 500, 500 );
      getContentPane().add( glcanvas);
      setSize( getContentPane().getPreferredSize() );
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
        
        float red[] =   {1.0f,0.0f,0.0f,1.0f}; // color red
        float green[] = { 0.0f, 1.0f, 0.3f, 1.0f }; // green ish
        float blue[] =   {0.0f,0.0f,1.0f,1.0f}; // color red

        float zero=0.0f;
        float one=2.0f;
        float neg=-2.0f;
        /*******/
        float ax=0.0f;
        float ay=2.0f;
        float bx=-2.0f;
        float by=-2.0f;
        float cx=2.0f;
        float cy=-2.0f;
        /*******/
        float dx=(ax+bx)/2f;
        float dy=(ay+by)/2f;
        float ex=(bx+cx)/2f;
        float ey=(by+cy)/2f;
        float fx=(ax+cx)/2f;
        float fy=(ay+cy)/2f;
        /*******/
        float onex=(ax+dx)/2;
        float oney=(ay+dy)/2;
        float twox=(dx+fx)/2;
        float twoy=(dy+fy)/2;;
        float threex=(ax+fx)/2;;
        float threey=(ay+fy)/2;;
        float fourx=(ex+fx)/2;;
        float foury=(ey+fy)/2;;
        float fivex=(ex+cx)/2;;
        float fivey=(ey+cy)/2;;
        float sixx=(fx+cx)/2;;
        float sixy=(fy+cy)/2;;
        float sevenx=(dx+bx)/2;;
        float seveny=(dy+by)/2;;
        float eightx=(bx+ex)/2;;
        float eighty=(by+ey)/2;;
        float ninex=(dx+ex)/2;;
        float niney=(dy+ey)/2;;
        
        
        
        gl.glBegin(GL2.GL_TRIANGLES );
        
        gl.glColor4fv(blue, 0);
	    gl.glVertex2f( ax, ay  );
	    gl.glVertex2f( by, by  );
	    gl.glVertex2f( cx, cy );
	    gl.glEnd();
	    
	    gl.glBegin(GL2.GL_TRIANGLES );
	    gl.glColor4fv(red, 0);
	    gl.glVertex2f( dx, dy  );
	    gl.glVertex2f( ex, ey  );
	    gl.glVertex2f( fx, fy );
	    gl.glEnd();
	    
	    gl.glBegin(GL2.GL_TRIANGLES );
	    gl.glColor4fv(green, 0);
	    gl.glVertex2f( onex, oney  );
	    gl.glVertex2f( twox, twoy  );
	    gl.glVertex2f( threex, threey );
	    gl.glEnd();
	    
	    gl.glBegin(GL2.GL_TRIANGLES );
	    gl.glColor4fv(green, 0);
	    gl.glVertex2f( fourx, foury  );
	    gl.glVertex2f( fivex, fivey  );
	    gl.glVertex2f( sixx, sixy );
	    gl.glEnd();
	    
	    gl.glBegin(GL2.GL_TRIANGLES );
	    gl.glColor4fv(green, 0);
	    gl.glVertex2f( sevenx, seveny  );
	    gl.glVertex2f( eightx, eighty  );
	    gl.glVertex2f( ninex, niney );
	    gl.glEnd();
	  
	    
	    
        //for(int i=0; i < 2; i+=0.1);
       // {
        /*
        		gl.glColor4fv(blue, 0);
		        gl.glVertex2f( 0.0f, 2.0f  );
		        gl.glVertex2f( -2.0f, -2.0f  );
		        gl.glVertex2f( 2.0f, -2.0f );
		        gl.glEnd();
		*/
		     	
        //}
        /*
        gl.glBegin(GL2.GL_TRIANGLES );
        gl.glColor4fv( red, 0 );
        gl.glVertex2f( 0.0f, -2.0f  );
        gl.glVertex2f( -1.0f, 0.0f  );
        gl.glVertex2f( 1.0f, 0.0f );
        gl.glEnd();
        
        gl.glBegin(GL2.GL_TRIANGLES);     
        gl.glColor4fv(green, 0);
        gl.glVertex2f( 0.0f/2, -2.0f/2  );
        gl.glVertex2f( -1.0f/2, 0.0f/2  );
        gl.glVertex2f( 1.0f/2, 0.0f/2 );
        gl.glEnd();
        
        gl.glBegin(GL2.GL_LINES );
        gl.glColor4fv( green, 0 );
        gl.glVertex2f( 0.0f, 0.0f );
        gl.glVertex2f( 2.0f, 2.0f );
        gl.glEnd();
	*/
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