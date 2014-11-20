// WeedMain.java
// Barrett Koster 2014
// This is the driver file for playing with fractals.  

package sarah;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;
 
public class MainOne extends JFrame implements GLEventListener
{
   private GLU glu = new GLU(); // just has some function we like
   OneTwo CallMethod;
   
    public static void main(String[] args) 
    {
       new MainOne();
    }
    
    
   // constructor
   public MainOne()
   {
      setTitle("Random Lines");
      GLProfile profile = GLProfile.get(GLProfile.GL2);
      GLCapabilities capabilities = new GLCapabilities(profile);
 
      // The canvas is the widget that's drawn in the JFrame
      GLCanvas glcanvas = new GLCanvas(capabilities);
      glcanvas.addGLEventListener(this);
      glcanvas.setSize( 300, 300 );
      getContentPane().add( glcanvas);
      setSize( getContentPane().getPreferredSize() );
      
      CallMethod = new OneTwo();
      
      setVisible( true );
    }
    
   
   public void display(GLAutoDrawable gLDrawable) 
   {
        final GL2 gl = gLDrawable.getGL().getGL2(); // make the gl so we can draw
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();

        
        float red[] =   {1.0f,0.0f,0.0f,1.0f}; // color red
        
        gl.glBegin(GL2.GL_LINES );
        gl.glColor4fv( red, 0 );
        gl.glVertex2f( 0.0f, 0.0f );
        gl.glVertex2f( 2.0f, 2.0f );
        gl.glEnd();
        
        CallMethod.drawMe(gl);

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

        if (height <= 0)
        {
            height = 1;
        }

        final float h = (float) width / (float) height;

        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();

        // glu.gluPerspective(45.0f, h, 1.0, 20.0);
        glu.gluOrtho2D( -4, 4, -4, 4 );
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
   }


   public void dispose(GLAutoDrawable arg0) 
   {
      System.out.println("dispose() called");
   }

}