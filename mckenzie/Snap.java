// Snap.java
// 2014 for CS-355 by Barrett Koster
// This class remembers a state of the scene by way of 
// recording the values of all of the BPs.

package mckenzie;

public class Snap
{
   double[] par; // the list of values of all the BPs for one
                 // moment in time.
   int len = 30; // hopefully bigger than the number of BPs on
                 // the control panel.
   
   // constructor
   public Snap()
   {
      par = new double[len];
   }
   
   // copy constructor
   public Snap ( Snap s )
   {
      par = new double[len];
      for ( int i=0; i<len; i++ )
      {
         par[i] = s.par[i];
      }
   }
   
   // interpolate
   // When frac=0, set to a; when frac is 1 set to b.
   // interpolate smoothly between those
   public void interpolate( Snap a, Snap b, double frac )
   {      
      for ( int i=0; i<len; i++ )
      {
         par[i] = a.par[i]*(1-frac) + b.par[i]*frac;
      }
   }
}
