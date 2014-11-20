// Art2.java
// Barrett Koster for CS-212 class 2013

package sarah;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;

public class ART10 extends JFrame
{
	int choices;
  	int x1=512;
    int y1=109;
    int x2=146;
    int y2=654;
    int x3=876;
    int y3=654;
    
    int x=200;
    int y=700;
    int dx;
    int dy;
    
   public static void main( String[] args )
   {
      System.out.println("hi there.");
      new ART10();
   }
   
   public ART10()
   {
	  setDefaultCloseOperation( EXIT_ON_CLOSE );

      setSize( 1000,700);
      setVisible( true);
   }
   
   public void paint( Graphics g ) 
   {
      super.paint(g);
      
        g.drawLine(x1, y1, x2, y2);
	    g.drawLine(x1, y1, x3, y3);
	    g.drawLine(x2, y2, x3, y3);
	    
	    g.drawLine(x, y, x, y);
	    
	    artthis(g);
 
   }

   public void artthis(Graphics g)
   {
	   for(int i=0; i < 50000; i++)
	   {
		   choices = (int) (Math.random() * 3) + 1;
		   System.out.println(choices);
		   if(choices==1)
	    	{
	    		dx=(x-x1);
	    		dy=(y-y1);
	    		//g.drawLine(x,y,x,y);
	    		
	    	}
	    	else if(choices==2)
	    	{
	    		dx=(x-x2);
	    		dy=(y-y2);
	    		//g.drawLine(x,y,x,y);
	    		
	    	}
	    	else
	    	{
	    		dx=(x-x3);
	    		dy=(y-y3);
	    	//	g.drawLine(x,y,x,y);
	    	}
	    	
	    	x=x-dx/2;
	    	y=y-dy/2;
	    	g.drawOval(x,y,2,2);
	    	
	  
	    }
	   }
   }
   
