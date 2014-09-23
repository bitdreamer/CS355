// Twirly.java
// Barrett Koster 2014
// This file is a template for doing 3D drawings in OpenGL.  
// This has attached files 

package threed;

import java.awt.*;
import java.awt.event.*; 
import javax.swing.*;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;
//import javax.swing.JFrame;
import com.jogamp.opengl.util.*;
 
public class Twirly2  extends JFrame implements GLEventListener // ActionListener,
   // , MouseListener
{
   private GLU glu = new GLU(); // just has some function we like
   Animator goThingy;
   final Twirly2 thisthis; // for use in contexts where "this" doesn't work
   //Frame theFrame;
   double yrot = 0;
   
   GLCanvas glcanvas;
   
   float red[] =   {1.0f,0.0f,0.0f,1.0f}; // color red
   float green[] = { 0.0f, 1.0f, 0.3f, 1.0f }; // green ish
   float blue[] = { 0.0f, 0.3f, 1.0f, 1.0f};
   
   ControlStuff2 buttons;
  // javax.swing.Timer timey;
   
   Cube cube1;
   
    public static void main(String[] args) 
    {
       new Twirly2();
    }
    
    public Twirly2()
   {      
      GLProfile profile = GLProfile.get(GLProfile.GL2);
      GLCapabilities capabilities = new GLCapabilities(profile);
      glcanvas = new GLCanvas(capabilities);
      glcanvas.addGLEventListener(this);
      
      //theFrame = new Frame("Twirly");
      setSize( 500, 500 );
      //theFrame.setSize( new Dimension(500,500) );
      //theFrame.add( glcanvas ); 
      add(glcanvas);

      goThingy = new Animator( glcanvas );
      goThingy.start();

      cube1 = new Cube();
      
      buttons = new ControlStuff2( this );
      thisthis = this;
      
      //theFrame.
      addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent e) {
         //ani.stop();
         buttons.dispose();
        
         System.exit(0);
         }
         });
      
       //theFrame.
       setVisible( true );  
    }
    
   // display().  Note that this fuction and most (all?) of the rest are 
    // handlers for the GLEventlistener interface.
    // display() is the main on that gets called to draw your stuff.  
    // The argument glDrawable is the context for drawing; the windows system
    // hands you that, the place you are going to draw on.
   public void display(GLAutoDrawable gLDrawable) 
   {
     // update();
        final GL2 gl = gLDrawable.getGL().getGL2(); // make the gl so we can draw
        
        yrot += 0.1;
        
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glRotated( yrot, 0.0, 1.0, 0.0 );  
        gl.glRotated(buttons.anglex, 1.0, 0.0, 0.0 );   
        
        setLighting(gl);
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        //gl.glLoadIdentity();
       // gl.glTranslatef(-1.5f, 0.0f, -6.0f);
           
           
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
        gl.glRotated( 20.0, 0.0, 1.0, 0.0 );  
        gl.glRotated(buttons.anglex, 1.0, 0.0, 0.0 );

   }
   
   public void setLighting( GL2 gl )
   {

       float pos[] = { 5.0f, 5.0f, 10.0f, 0.0f };

       gl.glEnable(GL2.GL_LIGHTING); // lighting system on
       gl.glEnable(GL2.GL_LIGHT0);   // light0 on
       gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, pos, 0); // light0 position
       //gl.glEnable(GL.GL_CULL_FACE);

      float[] lambi = { 0.3f, 0.3f, 0.5f,  1.0f };
      float[] lidif = { 0.1f, 0.1f, 0.1f, 1.0f };
      // float[] lspec = { 1f, 1f, 1f, 1.0f };
      // float[] lipos = { 1.0f, 4.0f, 2.0f, 0.0f };
      gl.glLightfv( gl.GL_LIGHT0, gl.GL_AMBIENT , lambi, 0 );
      gl.glLightfv( gl.GL_LIGHT0, gl.GL_DIFFUSE , lidif, 0 );
      //gl.glLightfv( gl.GL_LIGHT0, gl.GL_SPECULAR, lspec, 0 );
      // gl.glLightfv( gl.GL_LIGHT0, gl.GL_POSITION, lipos, 0 );
      
      gl.glMaterialfv(GL.GL_FRONT, GL2.GL_AMBIENT_AND_DIFFUSE, blue, 0);


      gl.glEnable(GL.GL_DEPTH_TEST);

   }
   
   /*
   public void actionPerformed( ActionEvent e )
   {
      if ( e.getSource()==timey ) { System.out.println("hey"); }
      glcanvas.display();
   
   }
   
   public void mouseEntered( MouseEvent m ) {}
   public void mouseExited( MouseEvent m ) {}
   public void mousePressed( MouseEvent m ) {}
   public void mouseReleased( MouseEvent m ) {}
   public void mouseClicked( MouseEvent m ) { glcanvas.display(); glcanvas.swapBuffers(); }
   */
   
   public void dispose(GLAutoDrawable arg0) 
   {
      System.out.println("dispose() called");
   }

}