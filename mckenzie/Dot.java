// Dot.java
// Barrett Koster  2014

// This is a 2D point.

package mckenzie;

public class Dot
{
   double[] x;
   
   public Dot()
   {
      x = new double[2];
      x[0] = x[1] = 0.0f;
      
      
   }
   public Dot( double a, double b)
   {
      x = new double[2];
      x[0] = a;
      x[1] = b;
      
   }
   
   public float[] getf()
   {
      float [] w = new float[2];
      
      w[0] = (float)(x[0]);
      w[1] = (float)(x[1]);
      
      return w;
   }
   
  
}
