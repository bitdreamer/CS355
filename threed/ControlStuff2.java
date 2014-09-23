// ControlStuff.java

package threed;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ControlStuff2 extends JFrame implements ActionListener
{
   JButton rotatexButton;
   JButton rotateyButton;
   double anglex = 20;
   double angley = 0;
   
   double centerx = 0;
   JButton rotatecxButton;
   
   Twirly2 theTwirly;
   
   public ControlStuff2( Twirly2 tw )
   {
      theTwirly = tw;
      setLocation( 600, 100 );
      setSize( new Dimension(300,300) );
      setLayout( new FlowLayout() );
      
      rotatexButton = new JButton("rotate x");
      add(rotatexButton);
      rotatexButton.addActionListener(this);
      
      rotateyButton = new JButton("rotate y");
      add(rotateyButton);
      rotateyButton.addActionListener(this);
      
      rotatecxButton = new JButton("rotate center x");
      add(rotatecxButton);
      rotatecxButton.addActionListener(this);
      
      setVisible(true);
   }

   public void actionPerformed( ActionEvent e )
   {
      if      ( e.getSource()==rotatexButton ) { anglex += 5;  }
      else if ( e.getSource()==rotateyButton ) { angley += 5;  }
      else if ( e.getSource()==rotatecxButton ) { centerx += 5;  }
      //theTwirly.glcanvas.display();
      //theTwirly.repaint();
      //theTwirly.glcanvas.displayChanged();
   }
         
}
