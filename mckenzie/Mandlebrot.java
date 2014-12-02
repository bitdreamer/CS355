package mckenzie;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

public class Mandlebrot 
{
	int hopMax = 500;	//How many hops from center going to make
	//More = more clear image, less blobish
	
	/*
	//Start edge
	float xmin = -2;
	float ymin = -2;
	*/
	
	//Width and height of area that will be colored
	//float w = 4;
	//float h = 4;
	
	
	//End edge
	//float xmax = xmin + w;
	//float ymax = ymin + h;

	public Mandlebrot()
	{

	}

	public void drawMe(int dwell, GL2 gl, float xmin, float ymin, float xmax, float ymax, int w, int h)
	{
		
		
		/*float xmax = xmin + w;
		float ymax = ymin + h;*/
		
		//go through all points
		//if diverge, black, else, white


		//deltas - change of x and y each iteration
		//increasing width and height increases precision
		int width = w;
		int height = h;
		float deltax = (xmax - xmin) / width;
		float deltay = (ymax - ymin) / height;
		

		
		//Choose a y point, ymin
		float y = ymin;
		for (int i = 0; i < height; i++)
		{
			//Go through all x points at that y, starting at xmin
			float x = xmin;
			for (int j = 0; j < width; j++)
			{
				//Perform calculations to determine if point x,y diverges
				//z=z^2+c
				
				//New x,y
				float a = x;
				float b = y;
				int n = 0; //number of hops for each point

				//Performs calculation until diverges or reach end of hops we are checking
				while(n < hopMax)
				{
					//a^2 and b^2 make z^2
					float aSquare = a * a;
					float bSquare = b * b;

					
					//iteration
					//based off complex math method of adding complex numbers
					float twoab =  2*a*b;
					a = (aSquare - bSquare) + x;
					b = twoab + y;
					
					
					
					if(aSquare + bSquare > 10)
					{
						break;
					}
					
					n++;
					

				}

				//Black - doesn't diverge within maximum hop limit
				if (n == hopMax)
				{
					gl.glBegin (GL.GL_POINTS);
					//gl.glColor3f(1.0f,1.0f,1.0f);
					gl.glColor3f (0.0f, 0.0f, 0.0f);
					gl.glVertex2f(x,y);
					gl.glEnd ();
				}
				//White - diverges within maximum hop limit
				else
				{
					gl.glBegin (GL.GL_POINTS);
					//gl.glColor3f (0.0f, 0.0f, 0.0f);
					//gl.glColor3f(1.0f,1.0f,1.0f);
					double[] color=color(n);
					gl.glColor3f((float)color[0],(float)color[1],(float)color[2]);
					gl.glVertex2f(x,y);
					gl.glEnd ();
				}
				x += deltax;
			}
			y += deltay;
		}
	}

	/*public void drawMe(Dot c, int dwell)
	{
	    float px = 0;
	    float py = 0;

        int i; int j;
        for ( i=0; i<500; i++ )
        {
		   //Complex cx = xmin + (px/w)*(xmax-xmin);;
           for ( j=0; j<500; j++ )
           {
		      //Complex cy = ymax - (py/h)*(ymax-ymin);;

			  //color(cx,dwell);

           }
        }
	}*/

	public double[] color(int dwell)
	{
		double r = ((dwell*3)%255)/255.0;
		double g = ((dwell*11)%255)/255.0;
		double b = ((dwell*23)%255)/255.0;
		double[] col = {r,g,b};
		//col={r,g,b};
		return col;
	}
}
