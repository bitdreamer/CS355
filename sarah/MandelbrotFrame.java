package sarah;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class MandelbrotFrame extends JFrame implements ActionListener {

    MandelbrotCanvas1 rtc;
    JButton startButton;
    Container c;

    MandelbrotFrame(){
        super("Dragon");
        setVisible(true);
        addComponents();
        setSize(800,500);
    }

    public void addComponents(){
        c = getContentPane();
        c.setLayout(null);
    
        rtc = new MandelbrotCanvas1();
        rtc.setBackground(Color.GRAY);
        startButton = new JButton("Dragon!!!");
        startButton.addActionListener(this);
    
        c.add(rtc);
        c.add(startButton);
        rtc.setBounds(0,0,600,600);
        startButton.setBounds(650,50,100,50);
    }

    public void actionPerformed(ActionEvent e){
        if (e.getSource() == startButton){
            rtc.draw();
            rtc.repaint();
        }
    }
}
