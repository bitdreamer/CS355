// Point.java
// Barrett Koster  2014

// This is a 3D point.

package threed;

import java.nio.FloatBuffer;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;


public class Point
{
   double[] x;
   
   public Point()
   {
      x = new double[3];
      x[0] = x[1] = x[2] = 0.0f;
   }
   
   public Point( Point z )
   {
      x = new double[3];
      x[0] = z.x[0];
      x[1] = z.x[1];
      x[2] = z.x[2];
     
   }
   
   public Point( double a, double b, double c)
   {
      x = new double[3];
      x[0] = a;
      x[1] = b;
      x[2] = c;
   }
   
   public float[] getf()
   {
      float [] w = new float[4];
      
      w[0] = (float)(x[0]);
      w[1] = (float)(x[1]);
      w[2] = (float)(x[2]);
      w[3] = 1.0f;
      
      return w;
   }
   public FloatBuffer getv()
   {
      FloatBuffer v;
      
      v = FloatBuffer.wrap( getf() );

      return v;
   }
   
  
}
