// Mousing.java
// Barrett Koster 2014
// This is a template for how to read the mouse  

package arocho;

import java.awt.event.*;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;
 
public class Mousing extends JFrame implements GLEventListener
   , MouseListener, MouseMotionListener
{
   private GLU glu = new GLU(); // just has some function we like
   GLCanvas glcanvas;
   
   int wid=300, hit=300;  // these remember reshape call values
   // Note: getWidth and getHeight give values not consistent with 
   // glcanvas coordinates.
   
   int mouseDownX=150, mouseDownY=150;
   int mouseX=250, mouseY=350;
   
   double xmin=-2, xmax=2, ymin=-2, ymax=2;
   Fly theFly = new Fly(0,0);
   
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
      // GLCanvas local def converted to global
      glcanvas = new GLCanvas(capabilities);
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
      
        theFly.drawMe( gl );
        
        //Draw a line, no a box
        //gl.glEnable(GL2.GL_COLOR_LOGIC_OP);
        //gl.glLogicOp(GL2.GL_XOR);
        gl.glColor3f( 1.0f, 1.0f, 1.0f );

        //Just the outline 
        //gl.glBegin( GL2.GL_LINE_LOOP );
        
        //Filled-in
        gl.glBegin(GL2.GL_POLYGON);
        float mdX = (float)(xScreen2Model(mouseDownX));
        float mX = (float)(xScreen2Model(mouseX));
        float mdY = (float)(yScreen2Model(mouseDownY));
        float mY = (float)(yScreen2Model(mouseY));
        
        //snap line
        //gl.glVertex2f(mdX, mdY);
        //gl.glVertex2f(mX, mY);
        
        //snap box
        gl.glVertex2f(mdX, mdY);
        gl.glVertex2f(mX, mdY);
        gl.glVertex2f(mX, mY);
        gl.glVertex2f(mdX, mY);
        
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
      wid = width; hit = height;
      
      //System.out.println("reshape() called: x = "+x+", y = "+y+", width = "+width+", height = "+height);
        final GL2 gl = gLDrawable.getGL().getGL2();

        if (height <= 0) // avoid a divide by zero error!
        {
            height = 1;
        }

        final float h = (float) width / (float) height; // aspect ration

        //gl.glViewport(0, 0, width, height);
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();

        // glu.gluPerspective(45.0f, h, 1.0, 20.0);
        glu.gluOrtho2D( xmin, xmax, ymin, ymax );
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
   
   
   // mouse methods
   public void mouseReleased( MouseEvent m ) {}
   public void mouseEntered( MouseEvent m ) {}
   public void mousePressed( MouseEvent m ) {
	   mouseDownX = m.getX();
	   mouseDownY = m.getY();
   }
   public void mouseExited( MouseEvent m ) {}
   public void mouseClicked( MouseEvent m )
   {
	   //mouseDownX = m.getX();
	   //mouseDownY = m.getY();
      
      //System.out.println("mouse clicked at "+m.getX()+" "+m.getY());
      //System.out.println("model coords: "+xScreen2Model(m.getX())+" "
      //                   +yScreen2Model( m.getY())
      //                  );
      
      theFly = new Fly( xScreen2Model(mouseDownX), yScreen2Model(mouseDownY) );
      // repaint();
      //gl.gluglutPostRedisplay();
      glcanvas.display(); // this forces a redraw
   }
   public void mouseMoved( MouseEvent m ) {}
   public void mouseDragged( MouseEvent m )
   {
      //System.out.println("mouse dragged to "+m.getX()+" "+m.getY());
      
     mouseX = m.getX(); mouseY = m.getY();
          glcanvas.display();
   }
   
   
   // These convert pixels to model coords so we can draw.  
   // They are not quite right ... missing and offset or something.
   public double xScreen2Model( int xScreen )
   {
      double xModel = 0;
      
      xModel = (1.0*xScreen)/wid * (xmax-xmin ) + xmin ;
      
      return xModel;    
   }

   public double yScreen2Model( int yScreen )
   {
      double yModel = 0;
      
      yModel = ymax - (1.0*yScreen)/hit * (ymax-ymin ) ;
      
      return yModel;    
   }

   
   
}