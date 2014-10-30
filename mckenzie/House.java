package mckenzie;

import javax.media.opengl.GL2;

public class House 
{
	Cube cube1;
	Roof roof1;
	
	public House(double anchorX, double anchorZ, float cColor[], float rColor[])
	{
		cube1 = new Cube(anchorX, anchorZ, cColor);
		roof1 = new Roof(anchorX, anchorZ, rColor);
	}
	
	public void drawMe(GL2 gl)
	{
		cube1.drawMe(gl);
		roof1.drawMe(gl);
	}
	
	public void report()
	{
		cube1.report();
	}
}
