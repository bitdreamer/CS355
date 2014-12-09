// Point2.java
// Points for spherey

package mckenzieFinal;

import java.nio.FloatBuffer;

//import javax.media.opengl.*;
//import javax.media.opengl.awt.GLCanvas;
//import javax.media.opengl.glu.GLU;

public class Point2
{
   double[] w;
   
   public Point2()
   {
      w = new double[3];
      w[0] = w[1] = w[2] = 0.0f;
   }
   
   public Point2( Point2 z )
   {
      w = new double[3];
      w[0] = z.w[0];
      w[1] = z.w[1];
      w[2] = z.w[2];
     
   }
   
   public Point2( double a, double b, double c)
   {
      w = new double[3];
      w[0] = a;
      w[1] = b;
      w[2] = c;
   }
   
   public float[] getf()
   {
      float [] z = new float[4];
      
      z[0] = (float)(w[0]);
      z[1] = (float)(w[1]);
      z[2] = (float)(w[2]);
      z[3] = 1.0f;
      
      return z;
   }
   public FloatBuffer getv()
   {
      FloatBuffer v;
      
      v = FloatBuffer.wrap( getf() );

      return v;
   }
   
   // add v onto this point
   public void add( V3 v )
   {
      for ( int i=0; i<3; i++ ) { w[i] += v.w[i]; }
   }
}
