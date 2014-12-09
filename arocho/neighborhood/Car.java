// House.java
// Barrett Koster 2014 for CS-355

package arocho.neighborhood;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;


import flat.Dot;

public class Car
{
	GLUT glut;
	//3D point arrays
	Point[] main;

	//normal vectors
	float[] na = {  0,  0,  1, 0 };
	float[] nb = {  1,  0,  0, 0 };
	float[] ne = { -1,  0,  0, 0 };
	float[] nc = {  0,  1,  0, 0 };
	float[] nd = {  0, -1,  0, 0 };
	float[] nf = {  0,  0, -1, 0 };

	float blueColor[] = { 0.0f, 0.0f, 0.8f, 1.0f};

	public Car(double x, double y, double z)
	{
		main = new Point[8];

		main[0] = new Point( x,   y+.1,   z );
		main[1] = new Point( x,   y+.1,   z+.5 );
		main[2] = new Point( x,   y+.5,   z );
		main[3] = new Point( x,   y+.5,   z+.5 );
		main[4] = new Point( x+1, y+.1,   z );
		main[5] = new Point( x+1, y+.1,   z+.5 );
		main[6] = new Point( x+1, y+.5,   z );
		main[7] = new Point( x+1, y+.5,   z+.5 );
	}

	public void drawMe( GL2 gl)
	{		
		gl.glPushMatrix();
		//gl.glTranslated(-0.5, -3, -0.5 ); // center the cube on the origin

		// This has shininess added, doesn't work great yet ...
		gl.glMaterialf(GL.GL_FRONT, GL2.GL_SHININESS, 100f );
		
		//Walls
		gl.glMaterialfv(GL.GL_FRONT, GL2.GL_AMBIENT_AND_DIFFUSE, blueColor, 0);
		face( gl, 1,5,7,3,na );
		face( gl, 4,6,7,5,nb );
		face( gl, 7,6,2,3,nc );
		face( gl, 1,0,4,5,nd );
		face( gl, 1,3,2,0,ne );
		face( gl, 0,2,6,4,nf );
		gl.glPopMatrix();
	}

	public void face( GL2 gl, int a, int b, int c, int d, float[] n )
	{
		gl.glNormal3fv( n, 0 );
		gl.glBegin( GL2.GL_POLYGON);
		gl.glVertex3fv( main[a].getv() );
		gl.glVertex3fv( main[b].getv() );
		gl.glVertex3fv( main[c].getv() );
		gl.glVertex3fv( main[d].getv() );
		gl.glEnd();
	}
}
