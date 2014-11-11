// Sphere.java
// Barrett Koster 2014 for CS-355

package threed;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;

import flat.Dot;

public class Sphere
{
   
  // float red[] =   {1.0f,0.0f,0.0f,1.0f}; // color red
  // float green[] = { 0.0f, 1.0f, 0.3f, 1.0f }; // green ish
  // float blue[] = { 0.0f, 0.3f, 1.0f, 1.0f};
  // float white[] = { 1.0f, 1.0f, 1.0f, 1.0f};

   Point[] corner;
   float[] na = {  0,  0,  1, 0 };
   float[] nb = {  1,  0,  0, 0 };
   float[] ne = { -1,  0,  0, 0 };
   float[] nc = {  0,  1,  0, 0 };
   float[] nd = {  0, -1,  0, 0 };
   float[] nf = {  0,  0, -1, 0 };

   Point[][] water;
   V3[][] up;

   int longitudeSize = 12;
   int lattitudeSize = 8;
   
   public Sphere()
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
      
      water = new Point[longitudeSize+1][lattitudeSize+1];
      up = new V3[longitudeSize+1][lattitudeSize+1];
      double r = 1; // radius of sphere
      
      for (int i=0; i<=longitudeSize; i++ )
      {
         for ( int j=0; j<=lattitudeSize; j++ )
         {
            double pi = 3.1415926;
            double alpha = j * pi / lattitudeSize ;
            double beta = i * pi * 2 / longitudeSize ;
            
            double x = r * Math.cos(beta) * Math.sin(alpha);
            double y = - r * Math.cos( alpha );
            double z = r * Math.sin(beta) * Math.sin(alpha);
            
            water[i][j] = new Point(x,y,z);
            up[i][j] = new V3( water[i][j].w[0], water[i][j].w[1], 
                             water[i][j].w[2] );
            up[i][j].normalize();
         }
      }
      
      
   }
   
   public void drawMe( GL2 gl )
   {
      //float blue[] = { 0.0f, 0.3f, 1.0f, 1.0f }; // green ish blue
      //gl.glColor4fv(blue, 0);    
      
      gl.glMaterialf( GL.GL_FRONT, GL2.GL_SHININESS, 500.0f );

      
      for (int i=0; i<longitudeSize; i++ )
      {
         for ( int j=0; j<lattitudeSize; j++ )
         {
            gl.glBegin( GL2.GL_POLYGON );

            // color
            double  red = ((i*317)%255)/255.0;
            double green = ((i*287)%255)/255.0;
            double blue = ((i*13242)%255)/255.0;
            float[] col = new float[4];
            col[0] = (float)red;
            col[1] = (float)green;
            col[2] = (float)blue;
            col[3] = 1;
            gl.glMaterialfv(GL.GL_FRONT, GL2.GL_AMBIENT_AND_DIFFUSE, col, 0);
           
            gl.glNormal3fv( up   [i][j].getv() ); 
            gl.glVertex3fv( water[i][j].getv() );
            
            gl.glNormal3fv( up   [i+1][j].getv() ); 
            gl.glVertex3fv( water[i+1][j].getv() );
            
            gl.glNormal3fv( up   [i+1][j+1].getv() ); 
            gl.glVertex3fv( water[i+1][j+1].getv() );
            
            gl.glNormal3fv( up   [i][j+1].getv() ); 
            gl.glVertex3fv( water[i][j+1].getv() );
            
            gl.glEnd();
          
         
         }
      }

      


   }
   /*
   public void face( GL2 gl, int a, int b, int c, int d, float[] n )
   {
      gl.glNormal3fv( n, 0 );
      gl.glBegin( GL2.GL_POLYGON);
      gl.glVertex3fv( corner[a].getv() );
      gl.glVertex3fv( corner[b].getv() );
      gl.glVertex3fv( corner[c].getv() );
      gl.glVertex3fv( corner[d].getv() );
      gl.glEnd();
   }*/
}
