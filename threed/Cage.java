// Cube.java
// Barrett Koster 2014 for CS-355

package threed;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;

import flat.Dot;

public class Cage
{
   
   float red[] =   {1.0f,0.0f,0.0f,1.0f}; // color red
   float green[] = { 0.0f, 1.0f, 0.3f, 1.0f }; // green ish
   float blue[] = { 0.0f, 0.3f, 1.0f, 1.0f};
   float white[] = { 1.0f, 1.0f, 1.0f, 1.0f};

   Point[] corner;
   float[] na = {  0,  0,  1, 0 };
   float[] nb = {  1,  0,  0, 0 };
   float[] ne = { -1,  0,  0, 0 };
   float[] nc = {  0,  1,  0, 0 };
   float[] nd = {  0, -1,  0, 0 };
   float[] nf = {  0,  0, -1, 0 };


   
   public Cage()
   {
      corner = new Point[8];
      
      corner[0] = new Point( 0, 0, 0 );
      corner[1] = new Point( 0, 0, 1 );
      corner[2] = new Point( 0, 1, 0 );
      corner[3] = new Point( 0, 1, 1 );
      corner[4] = new Point( 1, 0, 0 );
      corner[5] = new Point( 1, 0, 1 );
      corner[6] = new Point( 1, 1, 0 );
      corner[7] = new Point( 1, 1, 1 );
   }
   
   public void drawMe( GL2 gl )
   {
      //float blue[] = { 0.0f, 0.3f, 1.0f, 1.0f }; // green ish blue
      //gl.glColor4fv(blue, 0);      

      gl.glPushMatrix();
      gl.glScaled( 8.0, 8.0, 8.0 );
      
      gl.glTranslated(-0.5, -0.5, -0.5 ); // center the cube on the origin
      
      // This has shininess added, doesn't work great yet ...
      gl.glMaterialf(GL.GL_FRONT_AND_BACK, GL2.GL_SHININESS, 100f );
      
      gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL2.GL_AMBIENT_AND_DIFFUSE, blue, 0);
      gl.glMaterialfv( GL.GL_FRONT_AND_BACK, GL2.GL_SPECULAR, white, 0 );
      
      face( gl, 1,5,7,3,na );
      face( gl, 4,6,7,5,nb );
      face( gl, 7,6,2,3,nc );
     
      face( gl, 1,0,4,5,nd );
      face( gl, 1,3,2,0,ne );
      face( gl, 0,2,6,4,nf );
      gl.glPopMatrix();
   }
   
   public void face( GL2 gl, int a, int b, int c, int d, float[] n )
   {
      gl.glNormal3fv( n, 0 );
      gl.glBegin( GL2.GL_LINES);
      gl.glVertex3fv( corner[a].getv() );
      gl.glVertex3fv( corner[b].getv() );
      gl.glVertex3fv( corner[c].getv() );
      gl.glVertex3fv( corner[d].getv() );
      gl.glEnd();
   }
}
