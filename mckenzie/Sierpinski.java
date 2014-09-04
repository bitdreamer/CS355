//Sierpinski.java
//McKenzie Bradley for CS355 Fall 2014

package mckenzie;

import javax.media.opengl.GL2;

public class Sierpinski 
{
	int level = 4;

	public Sierpinski()
	{

	}

	public void drawMe(Dot a, Dot b, Dot c, GL2 gl, int level )
	{
		//make sure to stop if level <=0
		if(level<=0)
		{
			return;
		}
		else
		{

			gl.glBegin( GL2.GL_LINE_LOOP );
			float w[] = a.getf();
			float y[] = b.getf();
			float z[] = c.getf();
			float red[] = {1.0f,0.0f,0.0f,1.0f};
			gl.glColor4fv(red, 0);
			gl.glVertex2f(w[0],w[1]);
			gl.glVertex2f(y[0],y[1]);
			gl.glVertex2f(z[0],z[1]);
			gl.glEnd();

			System.out.println("hi " + level);
			Dot d = new Dot();
			d.x[0] = (a.x[0]+b.x[0])/2; d.x[1] = (a.x[1]+b.x[1])/2;
			Dot e = new Dot();
			e.x[0] = (a.x[0]+c.x[0])/2; e.x[1] = (a.x[1]+c.x[1])/2;
			Dot f = new Dot();
			f.x[0] = (c.x[0]+b.x[0])/2; f.x[1] = (c.x[1]+b.x[1])/2;
			
			//corner 0 = a, corner 1 = b, corner 2 = c
			//make 3 triangles in each triangle
			drawMe(a,d,e,gl,level-1); 
	      	drawMe(d,b,f,gl,level-1); 
	      	drawMe(e,f,c,gl,level-1); 
		}
	}
}
