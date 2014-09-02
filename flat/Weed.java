// Weed.java
// Barrett Koster 2014

// This is just a fractal thing, to be drawin in JOGL

package flat;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;


public class Weed
{
   public Weed()
   {
   }
   
   public void drawMe( GL2 gl )
   {
      float blue[] = { 0.0f, 0.3f, 1.0f, 1.0f }; // green ish 
      gl.glColor4fv(blue, 0);      

      Dot d = new Dot();
      branch( d, 4, gl );
   }
   
   // draw a weed (bunch of lines connects like trunk-branch-twig ).
   // Do recursively to level levels.  Note: N level branches are N long
   // max.  Directions of branches are random.
   // This method makes branchFactor branches, then calls itself with level-1
   // at the end of each branch.  Branches are random in length up to 
   // level.
   public void branch( Dot d, int level, GL2 gl )
   {
      int branchFactor = 6;
      if ( level>0 )
      {    
         
         float[] w = d.getf();
         
         Dot b; // new Dot
         
         for ( int i=0; i<branchFactor; i++ )
         {
            gl.glBegin(GL2.GL_LINES);     
            b = new Dot( w[0]+level*(Math.random()-0.5), w[1]+level*(Math.random()-0.5) );
            float[] bw = b.getf();
            gl.glVertex2f( w[0], w[1]  ); // given Dot
            gl.glVertex2f( bw[0], bw[1]  ); // new Dot displaced randomly
            gl.glEnd();
            
            branch( b, level-1, gl );
         }
   
   
      }
   }
}
