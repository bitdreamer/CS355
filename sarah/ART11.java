// Art2.java
// Barrett Koster for CS-212 class 2013

package sarah;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;
 

public class ART11 extends JFrame implements GLEventListener
{
	private GLU glu = new GLU();
	
	int choices;
  	float x1=0.0f;
  	float y1=2.0f;
  	float x2=-2.0f;
  	float y2=2.0f;
  	float x3=2.0f;
  	float y3=2.0f;
    
    float x=0.0f;
    float y=0.0f;
    float dx;
    float dy;
    
   public static void main( String[] args )
   {
      System.out.println("hi there.");
      new ART11();
   }
   
   public ART11()
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
   public void display(GLAutoDrawable gLDrawable) 
   {
        final GL2 gl = gLDrawable.getGL().getGL2(); // make the gl so we can draw
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();
        // gl.glTranslatef(-1.5f, 0.0f, -6.0f);
        
     
	    	artsy(gl);
	    	   gl.glFlush();
	   
	}
   
   private void artsy(GL2 gl2)
   {
	   final GL2 gl = gl2.getGL().getGL2(); // make the gl so we can draw
       gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
       gl.glLoadIdentity();
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
	    	gl.glBegin(GL2.GL_TRIANGLES );
	        gl.glColor4fv( green, 0 );
	        gl.glVertex2f( x, y );
	        gl.glVertex2f( x, y );
	        gl.glVertex2f( x, y );
	        gl.glEnd();
	   }
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
  


   
