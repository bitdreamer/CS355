// WallPaperMain.java
// Barrett Koster 2014
// Barry Martin's cool dot pattern.  

package sarah;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;
 
public class WallpaperMain extends JFrame implements GLEventListener
{
   private GLU glu = new GLU(); // just has some function we like
   Wallpaper wp1;
   
    public static void main(String[] args) 
    {
       new WallpaperMain();
    }
    
    // constructor
    public WallpaperMain()
   {
      setTitle("TwoTemplate");
      GLProfile profile = GLProfile.get(GLProfile.GL2);
      GLCapabilities capabilities = new GLCapabilities(profile);
 
      // The canvas is the widget that's drawn in the JFrame
      GLCanvas glcanvas = new GLCanvas(capabilities);
      glcanvas.addGLEventListener(this);
      glcanvas.setSize( 300, 300 );
      getContentPane().add( glcanvas);
      setSize( getContentPane().getPreferredSize() );
      
      wp1 = new Wallpaper();
      
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
/*
        gl.glBegin(GL2.GL_TRIANGLES);     
       

        gl.glColor4fv(red, 0);
        gl.glVertex2f( 0.0f, 1.0f  );
        gl.glVertex2f( -1.0f, -1.0f  );
        gl.glVertex2f( 1.0f, -1.0f );
        gl.glEnd();   
*/        
        wp1.drawMe(gl);

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
        glu.gluOrtho2D( -200000, 200000, -200000, 200000 );
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
   }


   public void dispose(GLAutoDrawable arg0) 
   {
      System.out.println("dispose() called");
   }

}