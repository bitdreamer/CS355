// BedSpread.java
// Barrett Koster 2014 for CS-355

package threed;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;

import flat.Dot;

public class BedSpread
{
   int gridSize = 100;
   
   float forest[] = { 0.1f, 0.5f, 0.6f, 1.0f }; // forest green
   float frog[]    = { 0.4f, 0.7f, 0.3f, 1.0f }; // frog green
   
   Point[] corner;
   float[] up = {  0,  1,  0, 0 }; // y normal

   Point[][] bed;
   V3[][] normals;

   
   public BedSpread()
   {
      bed = new Point[gridSize+1][gridSize+1];
      normals = new V3[gridSize+1][gridSize+1];
      
      for ( int i=0; i<gridSize+1; i++ )
      {
         for ( int j=0; j<gridSize+1; j++ )
         {
            double z = 0; // should be function
            double x = -5 + 0.1*i;
            double y = -5 + 0.1*j;
            
            z = 0.4 * Math.sin( 4 * x ) + 0.4 * Math.sin( 4 * y );
            
            bed[i][j] = new Point( x, y, z );
            
            double nx = - 1.6 * Math.cos( 4*x);
            double ny = - 1.6 * Math.cos( 4*y);
            double nz = 1;
            V3 normy = new V3( nx, ny ,nz );
            normy.normalize();
            
            normals[i][j] = normy;
         
         }
      }
   }
   
   public void drawMe( GL2 gl )
   {
      //float blue[] = { 0.0f, 0.3f, 1.0f, 1.0f }; // green ish blue
      //gl.glColor4fv(blue, 0);      

      gl.glPushMatrix();
      //gl.glTranslated(-0.5, -0.5, -0.5 ); // center the cube on the origin
            
      gl.glMaterialfv(GL.GL_FRONT, GL2.GL_AMBIENT_AND_DIFFUSE, forest, 0);
      gl.glMaterialf( GL.GL_FRONT, GL2.GL_SHININESS, 500.0f );
      
      for ( int i=0; i<gridSize; i++ )
      {
         for ( int j=0; j<gridSize; j++ )
         {
            //gl.glNormal3fv( up, 0 );
            gl.glBegin( GL2.GL_POLYGON);
            
            gl.glNormal3fv( normals[i][j].getv() );
            gl.glVertex3fv( bed[i][j].getv() );
                        
            gl.glNormal3fv( normals[i+1][j].getv() );
            gl.glVertex3fv( bed[i+1][j].getv() );
            
            gl.glNormal3fv( normals[i][j+1].getv() );
            gl.glVertex3fv( bed[i][j+1].getv() );
            gl.glEnd();
          
            gl.glBegin( GL2.GL_POLYGON);
            
            gl.glNormal3fv( normals[i+1][j].getv() );
            gl.glVertex3fv( bed[i+1][j].getv() );
            
            gl.glNormal3fv( normals[i+1][j+1].getv() );
            gl.glVertex3fv( bed[i+1][j+1].getv() );
            
            gl.glNormal3fv( normals[i][j+1].getv() );
            gl.glVertex3fv( bed[i][j+1].getv() );
            gl.glEnd();

         }
      }
      
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
