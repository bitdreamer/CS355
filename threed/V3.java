// V3.java
// Barrett Koster 2005

/*
 * This class is for traditional 3-space vectors.
*/

package threed;

import java.nio.FloatBuffer;

public class V3
{
    protected float [] w;
    public float[] floats() { return w; }
    public FloatBuffer floatBuffer() { return FloatBuffer.wrap(w,0,3);}
    
    // V3. constructor.  if no argument, init to 0
    public V3 ()
    {
        w = new float[3];
        for ( int i=0; i<3; i++ ) { w[i] = 0; }  
    }
    
    // V3. constructor.  takes as argument and array of 3 floats
    public V3 ( float [] h )
    {
        w = new float[3];
        for ( int i=0; i<3; i++ ) { w[i] = h[i]; }  
    }
    
    // minus.  returns c = this-b
    public V3 minus( V3 b )
    {
        V3 c = new V3( w ); // copy values from this V3 to c
        for ( int i=0; i<3; i++ ) { c.w[i] -= b.w[i]; } // subtract b values
        return c;
    }
    
    // cross.  returns new V3 which is ( this X z ).
    // This is just a 3-space cross.  
    public V3 cross( V3 z )
    {
        V3 c = new V3();

        c.w[0] =   w[1] * z.w[2] - w[2] * z.w[1];
        c.w[1] = - w[0] * z.w[2] + w[2] * z.w[0];
        c.w[2] =   w[0] * z.w[1] - w[1] * z.w[0];

        return c;
    }
    
       // normalize.  scale vector so its length is one.
    // does nothing if length is 0.
    public double normalize()
    {
        double m = mag();
        if ( m > 0 ) { mult( 1/m ); } 
        return m;
    }
    // magsq.  return the square of the magnitude.
    public double magsq()
    {
        double r = 0.0;
        int i;
        for ( i=0; i<3; i++ ) { r += w[i] * w[i]; }
        return r;
    }

    // mag. return the magnitude.
    public double mag()
    {
        return Math.sqrt( magsq() );
    }
    
    // mult. multiplies the constant into this V3
    public void mult( double k )
    {
        int i;
        for ( i=0; i<3; i++ ) { w[i] *= k; }
    }
    
    

    public FloatBuffer getv()
    {
       FloatBuffer v;
       
       v = FloatBuffer.wrap( w );

       return v;
    }
    

}


