// Spherey.java
// McKenzie Bradley 2014 for CS-355
//Modified sphere - only draws half the panels to make a teacup

package mckenzieFinal;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;

import flat.Dot;

public class Spherey
{

	Point2[][] water;
	V3[][] up;

	int longitudeSize = 12;
	int lattitudeSize = 10;
	
	float[] col;
	int sX;
	int sZ;

	public Spherey(float[] color, int sox, int soz)
	{
		col = color;
		sX=sox;
		sZ=soz;

		water = new Point2[longitudeSize+1][lattitudeSize+1];
		up = new V3[longitudeSize+1][lattitudeSize+1];
		double r = 0.5; // radius of sphere

		for (int i=0; i<=longitudeSize; i++ )
		{
			for ( int j=0; j<=lattitudeSize; j++ )
			{
				double pi = 3.1415926;
				double alpha = j * pi / lattitudeSize ;
				double beta = i * pi * 2 / longitudeSize ;

				double x = (r * Math.cos(beta) * Math.sin(alpha));
				double y = (- r * Math.cos( alpha ));
				double z = (r * Math.sin(beta) * Math.sin(alpha));
				
				water[i][j] = new Point2(x,y,z);
				up[i][j] = new V3( water[i][j].w[0], water[i][j].w[1], water[i][j].w[2] );
				up[i][j].normalize();
			}
		}


	}

	public void drawMe( GL2 gl )
	{  
		gl.glMaterialf( GL.GL_FRONT, GL2.GL_SHININESS, 500.0f );

		for (int i=0; i<longitudeSize-(longitudeSize/2); i++ )
		{
			for ( int j=0; j<lattitudeSize; j++ )
			{
				gl.glBegin( GL2.GL_POLYGON );

				gl.glMaterialfv(GL.GL_FRONT, GL2.GL_AMBIENT_AND_DIFFUSE, col, 0);

				gl.glNormal3fv( up   [i][j].getv() ); 
				gl.glVertex3fv( water[i][j].getv() );

				gl.glNormal3fv( up   [i+1][j].getv() ); 
				gl.glVertex3fv( water[i+1][j].getv() );

				gl.glNormal3fv( up   [i+1][j+1].getv() ); 
				gl.glVertex3fv( water[i+1][j+1].getv() );

				gl.glNormal3fv( up   [i][j+1].getv() ); 
				gl.glVertex3fv( water[i][j+1].getv() );

				gl.glEnd();


			}
		}
	}
}
