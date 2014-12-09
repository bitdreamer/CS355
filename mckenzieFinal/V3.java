// V3.java
// Barrett Koster 2005

/*
 * This class is for traditional 3-space vectors.
*/

package mckenzieFinal;

import java.nio.FloatBuffer;

public class V3
{
    protected float [] w; // values of the vector
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
    
    // constructor, takes 3 floats
    public V3( double x, double y, double z )
    {
       w = new float[3];
       w[0] = (float)x; w[1] = (float) y; w[2] = (float)z; 
    }
    
    public V3 ( V3 copyThis )
    {
       w = new float[3];
       w[0] = copyThis.w[0];
       w[1] = copyThis.w[1];
       w[2] = copyThis.w[2];
    }
    
    
    // minus.  returns c = this-b
    public V3 minus( V3 b )
    {
        V3 c = new V3( w ); // copy values from this V3 to c
        for ( int i=0; i<3; i++ ) { c.w[i] -= b.w[i]; } // subtract b values
        return c;
    }
    
    // add.  adds b into THIS vector
    public void add( V3 b )
    {      
        for ( int i=0; i<3; i++ ) { w[i] += b.w[i]; } // subtract b values
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
    
    public void replace(V3 r)
    {
    	w=r.w;
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
    
    public void adjust(V3 g)
    {
    	for ( int i=0; i<3; i++ ) 
    	{ 
    		/*if(g.w[i]>1)
    		{
    			if(w[i]>1)
    			{
    				w[i]+=g.w[i];
    			}
    			else
    			{
    				w[i]=(float)-0.7*w[i];
    			}
    		}
    		else
    		{
    			if(w[i]<1)
    			{
    				w[i]+=g.w[i];
    			}
    			else
    			{
    				w[i]=(float)-0.7*w[i];
    			}
    		}*/
    		
    		
    		//w[i] += ((float)0.9*g.w[i]-(float)0.1*w[i]); 
    		w[i] +=g.w[i]/w[i];
    	}
    }
    
    

    public FloatBuffer getv()
    {
       FloatBuffer v;
       
       v = FloatBuffer.wrap( w );

       return v;
    }
    

}


