// ThreeTemplate.java
// Barrett Koster 2014
// This file is a template for doing 3D drawings in OpenGL.  

package threed;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;
 
public class ThreeTemplate extends JFrame implements GLEventListener
{
   private GLU glu = new GLU(); // just has some function we like
   
   Cube cube1;
   
    public static void main(String[] args) 
    {
       new ThreeTemplate();
    }
    
    public ThreeTemplate()
   {
      setTitle("ThreeTemplate");
      GLProfile profile = GLProfile.get(GLProfile.GL2);
      GLCapabilities capabilities = new GLCapabilities(profile);
 
      // The canvas is the widget that's drawn in the JFrame
      GLCanvas glcanvas = new GLCanvas(capabilities);
      glcanvas.addGLEventListener(this);
      glcanvas.setSize( 300, 300 );
      getContentPane().add( glcanvas);
      setSize( getContentPane().getPreferredSize() );
      cube1 = new Cube();
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
        //setLighting(gl);
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        //gl.glLoadIdentity();
       // gl.glTranslatef(-1.5f, 0.0f, -6.0f);
        
        float red[] =   {1.0f,0.0f,0.0f,1.0f}; // color red
        float green[] = { 0.0f, 1.0f, 0.3f, 1.0f }; // green ish

        gl.glBegin(GL2.GL_TRIANGLES);     
       

        gl.glColor4fv(red, 0);
        gl.glVertex3f( 0.0f, 1.0f, 0.0f  );
        gl.glVertex3f( -1.0f, -1.0f, 0.0f   );
        gl.glVertex3f( 1.0f, -1.0f, 0.0f  );
        gl.glEnd();   
        
        gl.glBegin(GL2.GL_LINES );
        gl.glColor4fv( green, 0 );
        gl.glVertex3f( 0.0f, 0.0f, 0.0f  );
        gl.glVertex3f( 2.0f, 2.0f, 0.0f  );
        gl.glEnd();
        
        cube1.drawMe(gl);

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
        gl.glFrustum( -2, 2, -2 ,2, 4, 20 );
        gl.glTranslatef(0.0f, 0.0f, -6.0f);
    
        
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glRotated( 40.0, 1.0, 0.0, 0.0 );
        

   }
   
   public void setLighting( GL2 gl )
   {

       float pos[] = { 5.0f, 5.0f, 10.0f, 0.0f };

       gl.glEnable(GL2.GL_LIGHTING); // lighting system on
       gl.glEnable(GL2.GL_LIGHT0);   // light0 on
       gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, pos, 0); // light0 position
       //gl.glEnable(GL.GL_CULL_FACE);

      float[] lambi = { 0.3f, 0.3f, 0.5f,  1.0f };
      float[] lidif = { 1f, 1f, 1f, 1.0f };
      // float[] lspec = { 1f, 1f, 1f, 1.0f };
      // float[] lipos = { 1.0f, 4.0f, 2.0f, 0.0f };
      gl.glLightfv( gl.GL_LIGHT0, gl.GL_AMBIENT , lambi, 0 );
      gl.glLightfv( gl.GL_LIGHT0, gl.GL_DIFFUSE , lidif, 0 );
      //gl.glLightfv( gl.GL_LIGHT0, gl.GL_SPECULAR, lspec, 0 );
      // gl.glLightfv( gl.GL_LIGHT0, gl.GL_POSITION, lipos, 0 );

      gl.glEnable(GL.GL_DEPTH_TEST);

   }



   public void dispose(GLAutoDrawable arg0) 
   {
      System.out.println("dispose() called");
   }

}