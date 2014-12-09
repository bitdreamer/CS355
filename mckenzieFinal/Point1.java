// Point1.java
//3d points

package mckenzieFinal;

import java.nio.FloatBuffer;


public class Point1
{
   double[] x;
   
   public Point1()
   {
      x = new double[3];
      x[0] = x[1] = x[2] = 0.0f;
   }
   public Point1( double a, double b, double c)
   {
      x = new double[3];
      x[0] = a;
      x[1] = b;
      x[2] = c;
   }
   
   public float[] getf()
   {
      float [] w = new float[3];
      
      w[0] = (float)(x[0]);
      w[1] = (float)(x[1]);
      w[2] = (float)(x[2]);
      
      return w;
   }
   public FloatBuffer getv()
   {
      FloatBuffer v;
      
      v = FloatBuffer.wrap( getf() );

      return v;
   }
   
  
}
