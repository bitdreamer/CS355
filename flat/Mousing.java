// Mousing.java
// Barrett Koster 2014
// This is a template for how to read the mouse  

package flat;

import java.awt.event.*;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;
 
public class Mousing extends JFrame implements GLEventListener
   , MouseListener, MouseMotionListener
{
   private GLU glu = new GLU(); // just has some function we like
   
    public static void main(String[] args) 
    {
       new Mousing();
    }
    
    public Mousing()
   {
      setTitle("TwoTemplate");
      GLProfile profile = GLProfile.get(GLProfile.GL2);
      GLCapabilities capabilities = new GLCapabilities(profile);
 
      // The canvas is the widget that's drawn in the JFrame
      GLCanvas glcanvas = new GLCanvas(capabilities);
      glcanvas.addGLEventListener(this); // catches events needing redraw, reshape ...
      glcanvas.addMouseListener(this); // mouse clicks get sent to mouseClicked() ...
      glcanvas.addMouseMotionListener(this); // get move overs and drags
      glcanvas.setSize( 300, 300 );
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

        gl.glBegin(GL2.GL_TRIANGLES);     
       

        gl.glColor4fv(red, 0);
        gl.glVertex2f( 0.0f, 1.0f  );
        gl.glVertex2f( -1.0f, -1.0f  );
        gl.glVertex2f( 1.0f, -1.0f );
        gl.glEnd();   
        
        gl.glBegin(GL2.GL_LINES );
        gl.glColor4fv( green, 0 );
        gl.glVertex2f( 0.0f, 0.0f );
        gl.glVertex2f( 2.0f, 2.0f );
        gl.glEnd();

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

   // whatever
   public void dispose(GLAutoDrawable arg0) 
   {
      System.out.println("dispose() called");
   }
   
   // Mouse events
   public void glutMouseFunc( MouseEvent m )
   {
      System.out.println("glutMouseFunc called");
   }
   
   public void mouseReleased( MouseEvent m ) {}
   public void mouseEntered( MouseEvent m ) {}
   public void mousePressed( MouseEvent m ) {}
   public void mouseExited( MouseEvent m ) {}
   public void mouseClicked( MouseEvent m )
   {
      System.out.println("mouse clicked at "+m.getX()+" "+m.getY());
   }
   public void mouseMoved( MouseEvent m ) {}
   public void mouseDragged( MouseEvent m )
   {
      System.out.println("mouse dragged to "+m.getX()+" "+m.getY());
          
   }

}