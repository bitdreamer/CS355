// TwoTemplate.java
// Barrett Koster 2014
// This file is a template for doing 2D drawings in OpenGL.  

package sarah;

import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;
 
public class THREEcopyTwoTemplate extends JFrame implements GLEventListener
{
	float choices;
	float x1=512;
	float y1=109;
	float x2=146;
	float y2=654;
	float x3=876;
	float y3=654;
    
	float x=200;
	float y=700;
	float dx;
	float dy;
    
   private GLU glu = new GLU(); // just has some function we like
   
    public static void main(String[] args) 
    {
       new THREEcopyTwoTemplate();
    }
    
    public THREEcopyTwoTemplate()
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
        artthis(gl);
        
        gl.glFlush();
   }
   public void artthis(GL2 gl)
   {
	   float red[] =   {1.0f,0.0f,0.0f,1.0f}; // color red
       float green[] = { 0.0f, 1.0f, 0.3f, 1.0f }; // green ish
       float blue[] =   {0.0f,0.0f,1.0f,1.0f}; // color red
	   for(int i=0; i < 50000; i++)
	   {
		   choices = (int) (Math.random() * 3) + 1;
		   System.out.println(choices);
		   if(choices==1)
	    	{
	    		dx=(x-x1);
	    		dy=(y-y1);
	    		//g.drawLine(x,y,x,y);
	    		
	    	}
	    	else if(choices==2)
	    	{
	    		dx=(x-x2);
	    		dy=(y-y2);
	    		//g.drawLine(x,y,x,y);
	    		
	    	}
	    	else
	    	{
	    		dx=(x-x3);
	    		dy=(y-y3);
	    	//	g.drawLine(x,y,x,y);
	    	}
	    	
	    	x=x-dx/2;
	    	y=y-dy/2;
	    	//g.drawOval(x,y,2,2);
	    	   gl.glBegin(GL2.GL_LINES );
	           gl.glColor4fv( green, 0 );
	           gl.glVertex2f( x, y );
	           gl.glVertex2f( x+1, y+1 );
	           gl.glEnd();
	  
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