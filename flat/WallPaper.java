// WallPaper.java
// Barrett Koster and the CS-355 class 2014
// from Barry Martin's WallPaper program

package flat;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;


public class WallPaper
{
   long a = 2473; // constants to run the formula
   long b = 92739;
   long c = 31237;
   
   float redpart = 1.0f;
   float greenpart = 1.0f;
   float bluepart = 1.0f;
   
   Dot traveler; // this is the dot we draw the moves all over
   
   public WallPaper()
   {
      traveler = new Dot();
   }
   
   public void drawMe( GL2 gl )
   {
      
      gl.glBegin( GL2.GL_POINTS );
      for ( long i=0; i<10000000; i++ )
      {
         nextDot();
         float[] w = traveler.getf();
         if ( i%100 == 0  ) { advanceColor(); }
         gl.glColor4f(redpart,greenpart,bluepart,1.0f);
         gl.glVertex2f( w[0], w[1] );
      }
      gl.glEnd();
   }
   
   // advanced the dot to the next location (according to the 
   // formula
   // x = y - sing(x) * sqrt( absval(b*x - c) )
   // y = a-x 
   public void nextDot()
   {
      //traveler.x[0] += 0.01*(Math.random()-0.5);
      //traveler.x[1] += 0.01*(Math.random()-0.5);
      
      double x = traveler.x[0];
      traveler.x[0] = traveler.x[1] - sign(x) * Math.sqrt( Math.abs(b*x-c) );
      traveler.x[1] = a - x;
   }
   
   public int sign( double x )
   {
      if ( x>=0 ) { return 1; }
      else { return -1; }
   }
   
   public void advanceColor()
   {
      redpart += 0.001; if ( redpart > 1.0 ) { redpart -= 1.0; }
      greenpart += 0.00154; if ( greenpart > 1.0 ) { greenpart -= 1.0; }
      bluepart += 0.00231; if ( bluepart > 1.0 ) { bluepart -= 1.0; }
   }
}
