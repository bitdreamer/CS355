// Twirly4.java
// Barrett Koster 2014
// This file is a template for doing 3D drawings in OpenGL.  
// This also does animation.

package threed;

import java.awt.*;
import java.awt.event.*; 
import javax.swing.*;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;
import com.jogamp.opengl.util.*;
 
public class Twirly4  extends JFrame implements GLEventListener // ActionListener,
   // , MouseListener
{
   private GLU glu = new GLU(); // just has some function we like
   Animator goThingy;
   final Twirly4 thisthis; // for use in contexts where "this" doesn't work
   double yrot = 0;
   
   double aspectRatio = 1;
   
   GLCanvas glcanvas;
   
   float red[] =   {1.0f,0.0f,0.0f,1.0f}; // color red
   float green[] = { 0.0f, 1.0f, 0.3f, 1.0f }; // green ish
   float blue[] = { 0.0f, 0.3f, 1.0f, 1.0f};
   
   BP cameraAngleX; // tilt the direction of gaze down (or minus is up)
   BP cameraAngleY; // pan left or right on camera
   BP cameraAngleZ;
   
   BP cameraX; // position of the camera
   BP cameraY;
   BP cameraZ;
   
   BP wholeModelAngleX; //tumbles the whole model toward us
   BP wholeModelAngleY; // spins the whole model around a vertical axis
   
   BP bigCubeAngleX; // flips big center cube over toward you
   BP bigCubeAngleY; // rotate around verticle axis
   
   BP cubieAngleX; // flips the little corner cubies 
   BP cubieAngleY;
   
   BP setOfCubiesAngleZ;
   
   BP zoom; // controls the cameral angle width
   
   
   ControlStuff4 buttons;
  // javax.swing.Timer timey;
   
   Cube cube1;
   
    public static void main(String[] args) 
    {
       new Twirly4();
    }
    
    public Twirly4()
   {      
      GLProfile profile = GLProfile.get(GLProfile.GL2);
      GLCapabilities capabilities = new GLCapabilities(profile);
      glcanvas = new GLCanvas(capabilities);
      glcanvas.addGLEventListener(this);
      
      setSize( 500, 500 );
      add(glcanvas);

      goThingy = new Animator( glcanvas );
      goThingy.start();

      cube1 = new Cube();
      
      buttons = new ControlStuff4( ) ;
      cameraAngleX = buttons.addControl("cam look down",20,1);
      cameraAngleY = buttons.addControl("cam pan right",-12,1);
      cameraAngleZ = buttons.addControl("cam tilt right",0,1);
      cameraX = buttons.addControl("cam x",1,0.01);
      cameraY = buttons.addControl("cam y",2, 0.01);
      cameraZ = buttons.addControl("cam z",6, 0.01);
      
      wholeModelAngleX = buttons.addControl("whole rot x", 0, 1 );
      wholeModelAngleY = buttons.addControl("whole rot y", 0, 1 );
      
      bigCubeAngleX = buttons.addControl("big cube rot x", 0, 1);
      bigCubeAngleY = buttons.addControl("big cube rot y", 0, 1);
      
      cubieAngleX = buttons.addControl("cubies rot x",0,1);
      cubieAngleY = buttons.addControl("cubies rot y",0,1);
      
      setOfCubiesAngleZ = buttons.addControl("cloud z",0,1);
      
      zoom = buttons.addControl("zoom", 2, 0.01 );
      
      thisthis = this;
      
      addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent e) {
         goThingy.stop();
         buttons.dispose();
        
         System.exit(0);
         }
         });

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
        
        // this section controls the camera
        // Note that the rotations (pan) are listed first so that they happen last.
        // This keeps the center of rotation at the camera.
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        // glu.gluPerspective(45.0f, h, 1.0, 20.0);
        gl.glFrustum( -zoom.q*aspectRatio, zoom.q*aspectRatio, -zoom.q ,zoom.q, 4, 20 );

        gl.glRotated(cameraAngleZ.q,0,0,1); // tilt the frame
        gl.glRotated(cameraAngleX.q, 1, 0, 0 ); // look down or up
        gl.glRotated(cameraAngleY.q, 0, 1, 0 );  // pan left or right
        gl.glTranslated(-cameraX.q, // this is moving the camera
                        -cameraY.q, // to position, use negatives
                        -cameraZ.q
                       ); // because it is reaally
        // accomplished by pushing the scene in the opposite direction

        // this section controls the position of the big cube
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glRotated(wholeModelAngleX.q, 1.0, 0.0, 0.0 );   
        gl.glRotated(wholeModelAngleY.q, 0.0, 1.0, 0.0 );  
        
        setLighting(gl);
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        //gl.glLoadIdentity();
       // gl.glTranslatef(-1.5f, 0.0f, -6.0f);
           
        gl.glPushMatrix();
        gl.glRotated( bigCubeAngleX.q, 1, 0, 0 );   
        gl.glRotated( bigCubeAngleY.q, 0, 1, 0 );   
        cube1.drawMe(gl); // big center cube
        gl.glPopMatrix();
       // gl.glSc
        
        for ( int i=-1; i<2; i += 2 )
        {
           for ( int j=-1; j<2; j += 2 )
           {
              for ( int k=-1; k<2; k += 2 )
              {
             
                 //push
                 gl.glPushMatrix();
                 
                 // rotates the set of cubies 
                 gl.glRotated( setOfCubiesAngleZ.q, 0, 0, 1 );
                 
                 gl.glTranslated( i, j, k );
                 gl.glRotated( -setOfCubiesAngleZ.q, 0, 0, 1 );
                 gl.glRotated( cubieAngleY.q, 0, 1, 0 ); // twirls cubies
                 gl.glRotated( cubieAngleX.q, 1, 0, 0 );
                 
                 gl.glScaled(  .2, .2, .2 );
                 
                 cube1.drawMe(gl);
                 // pop
                 gl.glPopMatrix();
              }
           }
           
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
        gl.glClearColor(1.0f, 0.0f, 0.0f, 0.0f);
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

        aspectRatio = (float) width / (float) height; // aspect ratio

        gl.glViewport(0, 0, width, height);

   }
   
   
   // sets the lights and the material properties.
   public void setLighting( GL2 gl )
   {

       float pos[] = { 5.0f, 5.0f, 10.0f, 0.0f };

       gl.glEnable(GL2.GL_LIGHTING); // lighting system on
       gl.glEnable(GL2.GL_LIGHT0);   // light0 on
       gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, pos, 0); // light0 position
       //gl.glEnable(GL.GL_CULL_FACE);

      float[] lambi = { 0.3f, 0.3f, 0.5f,  1.0f };
      float[] lidif = { 0.2f, 0.2f, 0.2f, 1.0f };
      float[] lspec = { 1f, 1f, 1f, 1.0f };
      // float[] lipos = { 1.0f, 4.0f, 2.0f, 0.0f };
      gl.glLightfv( gl.GL_LIGHT0, gl.GL_AMBIENT , lambi, 0 );
      gl.glLightfv( gl.GL_LIGHT0, gl.GL_DIFFUSE , lidif, 0 );
      gl.glLightfv( gl.GL_LIGHT0, gl.GL_SPECULAR, lspec, 0 );
      // gl.glLightfv( gl.GL_LIGHT0, gl.GL_POSITION, lipos, 0 );
      
      gl.glMaterialfv(GL.GL_FRONT, GL2.GL_AMBIENT_AND_DIFFUSE, blue, 0);


      gl.glEnable(GL.GL_DEPTH_TEST);

   }
   

   
   public void dispose(GLAutoDrawable arg0) 
   {
      System.out.println("dispose() called");
   }

}