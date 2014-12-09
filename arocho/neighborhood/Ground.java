// Grass.java
// Barrett Koster 2014 for CS-355

package arocho.neighborhood;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;

import flat.Dot;

public class Ground
{
   
   Point[] corner;
   float[] up = {  0,  1,  0, 0 }; // y normal
   float[] color;

   
   public Ground(double x, double z, float color[])
   {
      corner = new Point[8];
      this.color = color;
      
      corner[0] = new Point(  x, 0,  z );
      corner[1] = new Point(  x, 0, -z );
      corner[2] = new Point( -x, 0, -z );
      corner[3] = new Point( -x, 0,  z );
   }
   
   public void drawMe( GL2 gl )
   {
      gl.glPushMatrix();
      //gl.glTranslated(-0.5, -0.5, -0.5 ); // center the cube on the origin
            
      gl.glMaterialfv(GL.GL_FRONT, GL2.GL_AMBIENT_AND_DIFFUSE, color, 0);
      
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
