// Cube.java
// Barrett Koster 2014 for CS-355

package threed;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;

import flat.Dot;

public class Bird
{
   
   float red[] =   {1.0f,0.0f,0.0f,1.0f}; // color red
   float green[] = { 0.0f, 1.0f, 0.3f, 1.0f }; // green ish
   float blue[] = { 0.0f, 0.3f, 1.0f, 1.0f};
   float white[] = { 1.0f, 1.0f, 1.0f, 1.0f};

   Point[] corner;
   
   float[] norm = {  0,  1,  0, 0 };
   V3 position;
   V3 velocity; // units per second

   public Bird()
   {
      corner = new Point[3];
      
      corner[0] = new Point( 0, 0, 0 );
      corner[1] = new Point( -1, 0, -1 );
      corner[2] = new Point( -1, 0, 1 );
      
      velocity = new V3( 0.1, 0.1, 0.0 );
      position = new V3( );

   }
   
   // move the bird this much time 
   public void move( double deltat )
   {
      // change the velocity by a random amount
      V3 change = new V3( Math.random()-0.5, 
                          Math.random()-0.5,
                          Math.random()-0.5
                        );
      change.mult( 0.2 );
      velocity.add( change );
      
      // make velocity point toward home moreso if we
      // are far away from home (0,0,0).
      if ( position.magsq() > 5 )
      {
         change = new V3( position);
         change.mult( -0.02 );
         velocity.add(change);
      }
      
      
      // move in direction of velocity by deltat
      V3  dx = new V3( velocity );
      dx.mult( deltat );
      position.add( dx );
   }
   
   public void drawMe( GL2 gl )
   {
      //float blue[] = { 0.0f, 0.3f, 1.0f, 1.0f }; // green ish blue
      //gl.glColor4fv(blue, 0);      

      gl.glPushMatrix();
      gl.glTranslatef( position.w[0],position.w[1],position.w[2] ); 

      
      // This has shininess added, doesn't work great yet ...
      gl.glMaterialf(GL.GL_FRONT, GL2.GL_SHININESS, 100f );
      
      gl.glMaterialfv(GL.GL_FRONT, GL2.GL_AMBIENT_AND_DIFFUSE, blue, 0);
      gl.glMaterialfv( GL.GL_FRONT, GL2.GL_SPECULAR, white, 0 );
      
      face( gl, 0, 1, 2,norm );

      gl.glPopMatrix();
   }
   
   public void face( GL2 gl, int a, int b, int c, float[] n )
   {
      gl.glNormal3fv( n, 0 );
      gl.glBegin( GL2.GL_POLYGON);
      gl.glVertex3fv( corner[a].getv() );
      gl.glVertex3fv( corner[b].getv() );
      gl.glVertex3fv( corner[c].getv() );
      gl.glEnd();
   }
}
