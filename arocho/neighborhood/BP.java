// ButtonPanel.java
// 2014 CS-355 

// This class is the control for ONE number, q, embeded in this class.
// It has buttons to increase and decrease and stop (and reset) q.
// There is the expectation that some other class will repeatedly
// call step() to apply changes to q.  Any other class wishing to
// use the value of this BP must come get it here.


package arocho.neighborhood;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class BP extends JPanel implements ActionListener
{
   String name; // name of quantity that this ButtonPanel controls
   double q; // the quantity
   double dq; // current rate of change
   double qorig; // initial value of q, return to this on 'reset'
   double ddq; // size of change to dq when you press up or down buttons
   
   JButton upButton;
   JButton stopButton;
   JButton resetButton;
   JButton downButton;
   
   
   // constructor, name of quantity, default value, rate change increment (2nd derivative).
   // It builds the panel with buttons to increase the ddq (second dif of the quantity).
   public BP( String name1, double q1, double ddq1 )
   {
      name = name1;
      qorig = q1;
      q = q1;
      ddq = ddq1;
      
      setBackground( new Color(200,180, 220 ) );
      
      setPreferredSize(new Dimension( 400, 34 ) );
      setLayout( new FlowLayout() );
      
      add( new JLabel(name) );
      upButton = makeButton("+");
      stopButton = makeButton("stop");
      resetButton = makeButton("reset"); 
      downButton = makeButton("-");
   }
   
   // construct the button, add it to the window, attach action listener and return it.
   public JButton makeButton(String s)
   {
      JButton b = new JButton(s);
      add(b);
      b.addActionListener(this);
      
      return b;
   }

   public void reset()
   {
      q = qorig;
      dq = 0;
   }
   public void step()
   {
      q = q + dq;
   }
   
   public void actionPerformed( ActionEvent e )
   {
      if      ( e.getSource()==upButton    ) { dq += ddq; }
      else if ( e.getSource()==stopButton  ) { dq = 0; }
      else if ( e.getSource()==resetButton ) { q = qorig; dq = 0; }
      else if ( e.getSource()==downButton  ) { dq -= ddq; }
      //System.out.println("bp reports "+name+" = "+q);
   }
}
