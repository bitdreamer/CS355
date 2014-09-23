// ControlStuff.java

package threed;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ControlStuff extends JFrame implements ActionListener
{
   JButton rotatexButton;
   double anglex = 20;
   Twirly theTwirly;
   
   public ControlStuff( Twirly tw )
   {
      theTwirly = tw;
      setLocation( 600, 100 );
      setSize( new Dimension(300,300) );
      setLayout( new FlowLayout() );
      
      rotatexButton = new JButton("rotate x");
      add(rotatexButton);
      rotatexButton.addActionListener(this);
      
      setVisible(true);
   }

   public void actionPerformed( ActionEvent e )
   {
      if ( e.getSource()==rotatexButton ) { anglex += 5;  }
      //theTwirly.glcanvas.display();
      //theTwirly.repaint();
      //theTwirly.glcanvas.displayChanged();
   }
         
}
