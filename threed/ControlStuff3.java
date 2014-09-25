// ControlStuff3.java

package threed;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ControlStuff3 extends JFrame implements ActionListener
{
   JButton rotatexButton;
   JButton rotateyButton;
   double anglex = 20;
   double angley = 0;
   
   double centerx = 0;
   JButton rotatecxButton;
   JButton outeryButton;
   double outery = 0;
   
   JButton goButton;
   boolean going = false;
   
   javax.swing.Timer timey;
   
   Twirly3 theTwirly;
   
   public ControlStuff3( Twirly3 tw )
   {
      theTwirly = tw;
      setLocation( 600, 100 );
      setSize( new Dimension(300,300) );
      setLayout( new FlowLayout() );
      
      rotatexButton = makeButton("rotate x");
      rotateyButton = makeButton("rotate y");
      rotatecxButton = makeButton("rotate center x"); 
      outeryButton = makeButton("rotate out cubies in Y");
      goButton = makeButton("go");
      
      timey = new javax.swing.Timer( 100, this );
      
      
      setVisible(true);
   }
   
   public JButton makeButton(String s)
   {
      JButton b = new JButton(s);
      add(b);
      b.addActionListener(this);
      
      return b;
   }

   public void actionPerformed( ActionEvent e )
   {
      if      ( e.getSource()==rotatexButton ) { anglex += 6;  }
      else if ( e.getSource()==rotateyButton ) { angley += 7;  }
      else if ( e.getSource()==rotatecxButton ) { centerx += 4;  }
      else if ( e.getSource()==outeryButton ) { outery += 5;  }
      else if ( e.getSource()==goButton )
      {
         going = !going;
         if (going) { timey.start(); } else { timey.stop(); }
      }
      else if ( e.getSource()==timey )
      {
         //anglex += 1.5;
         //angley += 2;
         //centerx += 2.5;
         outery += 3;
      }
   }
         
}
