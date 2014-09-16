// Fly.java
// Barrett Koster 2014

// This is a dot to draw on the screen.

package arocho;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;


public class Fly
{
   
   Dot myDot;
   
   public Fly( double x1, double y1)
   {
      myDot = new Dot(x1,y1);
   }
   
   public void drawMe( GL2 gl )
   {
      float blue[] = { 0.0f, 0.3f, 1.0f, 1.0f }; // green ish 
      gl.glColor4fv(blue, 0);      

      float[] w = myDot.getf();  

      gl.glBegin(GL2.GL_POLYGON);     
      gl.glVertex2f( w[0], w[1]  ); // given Dot
      gl.glVertex2f( (float)(w[0]+0.2), w[1]  ); // given Dot
      gl.glVertex2f( w[0], (float)(w[1]+0.2)  ); // given Dot
     gl.glEnd();
     gl.glBegin(GL2.GL_POLYGON);     
     gl.glVertex2f( w[0], w[1]  ); // given Dot
     gl.glVertex2f( (float)(w[0]-0.2), w[1]  ); // given Dot
     gl.glVertex2f( w[0], (float)(w[1]-0.2)  ); // given Dot
    gl.glEnd();
            
   }

}
