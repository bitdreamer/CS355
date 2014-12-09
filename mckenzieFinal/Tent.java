//Tent.java
//Combines frame (tent poles) and roof to make a fairground tent
//McKenzie Bradley CS355 Fall 2014

package mckenzieFinal;

import javax.media.opengl.GL2;

public class Tent 
{
	Frame frame1;
	Roof roof1;
	
	public Tent(double anchorX, double anchorZ, float cColor[], float rColor[])
	{
		frame1 = new Frame(anchorX, anchorZ, cColor);
		roof1 = new Roof(anchorX, anchorZ, rColor);
	}
	
	public void drawMe(GL2 gl)
	{
		frame1.drawMe(gl);
		roof1.drawMe(gl);
	}
}
