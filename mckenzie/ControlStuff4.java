// ControlStuff4.java
// This class is a separate Frame to put controls in.  
// It accomodates controls of type BP.  This class has
// the addControl method that lets you add such a control
// of a given name.

package mckenzie;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class ControlStuff4 extends JFrame implements ActionListener
{
   BP[] theBP;
   int theBPcount = 0;
   Snap[] path; // sequence of snapshots of scene position
   int pathCount = 0; // number of Snaps on the path
   double pathPointer = 0; // where we are along the path
   Snap snappy; // just a dummy snap we can use to follow path
   boolean doingPath = false;
   
   JButton snapButton;
   JButton goPathButton;
   
   javax.swing.Timer timey;
   
   //Twirly4 theTwirly;
   
   public ControlStuff4(  )
   {
     // theTwirly = tw;
      setLocation( 600, 100 );
      setSize( new Dimension(500,500) );
      setLayout( new FlowLayout() );
      
      theBP = new BP[20];
      path = new Snap[100];
      snappy = new Snap();
      
      timey = new javax.swing.Timer( 100, this );
      timey.start();
      
      snapButton = makeButton("snapshot");
      goPathButton = makeButton("do path");
      
      setVisible(true);
   }
   
   // construct the button, add it to the window, attach action listener and return it.
   public JButton makeButton(String s)
   {
      JButton b = new JButton(s);
      add(b);
      b.addActionListener(this);
      
      return b;
   }


   public BP addControl( String name, double qorig, double ddq )
   {
      BP bp = new BP( name, qorig, ddq );
      add(bp);
      theBP[theBPcount++] = bp;
      return bp;
   }
   
   public Snap takeSnapShot()
   {
      Snap dog = new Snap();
      
      for (int i=0; i<theBPcount; i++ )
      {
         dog.par[i] = theBP[i].q;
      }
      
      return dog;
   }
   public void restoreSnapShot( Snap dog)
   {   
      for (int i=0; i<theBPcount; i++ )
      {
         theBP[i].q = dog.par[i];
      }
   }

   public void actionPerformed( ActionEvent e )
   {
      if ( e.getSource()==timey )
      {
         if ( doingPath ) // set BPs to pathPointer along the path, increment pathPointer
         {
            pathPointer += 0.02; if ( pathPointer >= pathCount ) { pathPointer -= pathCount; }
            int ppint = (int) pathPointer; // just the integer part
            int ppnext = (ppint+1)%pathCount;
            double frac = pathPointer - ppint; // just the fractional part
            snappy.interpolate( path[ppint], path[ppnext], frac );
            restoreSnapShot( snappy );
         }
         else // not doing path, just make everyone step
         {
            for (int i=0; i<theBPcount; i++ )
            {
               theBP[i].step();
            }
         }
      }
      else if ( e.getSource()==snapButton   ) { path[pathCount++] = takeSnapShot();  }
      else if ( e.getSource()==goPathButton ) { doingPath = !doingPath; }
   }
         
}
