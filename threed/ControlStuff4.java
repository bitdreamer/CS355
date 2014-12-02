// ControlStuff4.java
// This class is a separate Frame to put controls in.  
// It accomodates controls of type BP.  This class has
// the addControl method that lets you add such a control
// of a given name.

package threed;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import javax.swing.*;

//import Chippy.Piece;
import java.io.*;

public class ControlStuff4 extends JFrame implements ActionListener
{
   boolean bug = true;
   BP[] theBP;
   int theBPcount = 0;
   Snap[] path; // sequence of snapshots of scene position
   int pathCount = 0; // number of Snaps on the path
   double pathPointer = 0; // where we are along the path
   Snap snappy; // just a dummy snap we can use to follow path
   boolean doingPath = false;
   
   JButton storeButton;
   JButton loadButton;
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
      storeButton = makeButton("store path");
      loadButton = makeButton("load path");
      
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
      Snap.parCount = theBPcount;
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
      else if ( e.getSource()==storeButton ) { storePath(); }
      else if ( e.getSource()==loadButton ) { loadPath(); }
   }
   
   public void storePath()
   {
      JFileChooser fc = new JFileChooser();
      
      int result = fc.showSaveDialog(this);
      
      if (result == JFileChooser.APPROVE_OPTION)
      {
        File file = fc.getSelectedFile();
        try
        {
           FileWriter fw = new FileWriter ( file );
          // ObjectOutputStream oos = new ObjectOutputStream ( fos );
           
           for ( int i=0; i<pathCount; i++ )
           {
              Snap sn = path[i];
              String s = sn.report();
              fw.write( s );
              fw.write("\n");
           }
           
           fw.flush();
           fw.close();
        }
        catch ( Exception e )
        {
           System.out.println( e.toString() );
        }
      }

   }
   
   public void loadPath()
   {
      if (bug) { System.out.println("ControlStuff4.loadPath ..."); }
      JFileChooser fc = new JFileChooser();
      try
      {
         int result = fc.showOpenDialog(this); // this is where the user picks the file
      
         if (result == JFileChooser.APPROVE_OPTION)
         {
            File file = fc.getSelectedFile(); // ok, so what was that file?
            FileReader fr = new FileReader(file);
            BufferedReader bfr = new BufferedReader( fr );
            
            if ( bfr != null )
            {
                String line;
                boolean done=false;
                while (!done)
                {
                    line = null;
                    try{ line = bfr.readLine(); }
                    catch (EOFException e) { done = true; } // doesn't work
                    catch (IOException e) 
                    { System.out.println("Cmd.cmd: read error="+e); done = true; }
                    
                    // detect end of file (this one works)
                    if ( line ==null ) { done = true; }
                    
                    if ( !done )
                    {
                        path[theBPcount++] = new Snap( line ); 
                    }
                }
            }
         }
      }
      catch (Exception e ) { System.out.println( e.toString() );}

   }
         
}