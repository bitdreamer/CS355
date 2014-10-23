// Grass.java
// Barrett Koster 2014 for CS-355

package threed;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;

import flat.Dot;

public class Grass
{
   
   float forest[] = { 0.1f, 0.5f, 0.1f, 1.0f }; // green ish

   Point[] corner;
   float[] up = {  0,  1,  0, 0 }; // y normal


   
   public Grass()
   {
      corner = new Point[8];
      
      corner[0] = new Point(  10, 0,  10 );
      corner[1] = new Point(  10, 0, -10 );
      corner[2] = new Point( -10, 0, -10 );
      corner[3] = new Point( -10, 0,  10 );
   }
   
   public void drawMe( GL2 gl )
   {
      //float blue[] = { 0.0f, 0.3f, 1.0f, 1.0f }; // green ish blue
      //gl.glColor4fv(blue, 0);      

      gl.glPushMatrix();
      //gl.glTranslated(-0.5, -0.5, -0.5 ); // center the cube on the origin
            
      gl.glMaterialfv(GL.GL_FRONT, GL2.GL_AMBIENT_AND_DIFFUSE, forest, 0);
      
      face( gl, 0, 1, 2, 3 , up );
      
       gl.glPopMatrix();
   }
   
   public void face( GL2 gl, int a, int b, int c, int d, float[] n )
   {
      gl.glNormal3fv( n, 0 );
      gl.glBegin( GL2.GL_POLYGON);
      gl.glVertex3fv( corner[a].getv() );
      gl.glVertex3fv( corner[b].getv() );
      gl.glVertex3fv( corner[c].getv() );
      gl.glVertex3fv( corner[d].getv() );
      gl.glEnd();
   }
}
