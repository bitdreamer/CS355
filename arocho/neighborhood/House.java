// House.java
// Barrett Koster 2014 for CS-355

package arocho.neighborhood;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;

import flat.Dot;

public class House
{
	//3D point arrays
	Point[] main;
	Point[] garage;
	Point[] roof;

	//normal vectors
	float[] na = {  0,  0,  1, 0 };
	float[] nb = {  1,  0,  0, 0 };
	float[] ne = { -1,  0,  0, 0 };
	float[] nc = {  0,  1,  0, 0 };
	float[] nd = {  0, -1,  0, 0 };
	float[] nf = {  0,  0, -1, 0 };

	float beigeColor[] = { 1.0f, 0.9f, 0.8f, 1.0f};
	float brickColor[] = { 0.79f, 0.25f, 0.32f, 1.0f};

	public House(double x, double y, double z)
	{
		main = new Point[8];
		roof = new Point[7];

		main[0] = new Point( x,   y,   z );
		main[1] = new Point( x,   y,   z+1 );
		main[2] = new Point( x,   y+1, z );
		main[3] = new Point( x,   y+1, z+1 );
		main[4] = new Point( x+1, y,   z );
		main[5] = new Point( x+1, y,   z+1 );
		main[6] = new Point( x+1, y+1, z );
		main[7] = new Point( x+1, y+1, z+1 );


		roof[0] = new Point( x,   y+1,   z );
		roof[1] = new Point( x,   y+1,   z+1 );
		roof[2] = new Point( x,   y+1.5, z+0.5 );
		roof[4] = new Point( x+1, y+1,   z );
		roof[5] = new Point( x+1, y+1,   z+1 );
		roof[6] = new Point( x+1, y+1.5, z+0.5 );
	}

	public void drawMe( GL2 gl )
	{
		gl.glPushMatrix();
		//gl.glTranslated(-0.5, -3, -0.5 ); // center the cube on the origin

		// This has shininess added, doesn't work great yet ...
		gl.glMaterialf(GL.GL_FRONT, GL2.GL_SHININESS, 100f );
		
		//Walls
		gl.glMaterialfv(GL.GL_FRONT, GL2.GL_AMBIENT_AND_DIFFUSE, beigeColor, 0);
		wallFace( gl, 1,5,7,3,na );
		wallFace( gl, 4,6,7,5,nb );
		wallFace( gl, 7,6,2,3,nc );
		wallFace( gl, 1,0,4,5,nd );
		wallFace( gl, 1,3,2,0,ne );
		wallFace( gl, 0,2,6,4,nf );

		gl.glPopMatrix();

		//Vaulted Ceiling
		gl.glMaterialfv(GL.GL_FRONT, GL2.GL_AMBIENT_AND_DIFFUSE, brickColor, 0);
		gl.glPushMatrix();
		roofFace1( gl, 1,5,6,2,na );
		roofFace2( gl, 4,6,5,nb );
		roofFace1( gl, 1,0,4,5,nd );
		roofFace2( gl, 1,2,0,ne );
		roofFace1( gl, 0,2,6,4,nf );
		gl.glPopMatrix();
	}

	public void wallFace( GL2 gl, int a, int b, int c, int d, float[] n )
	{
		gl.glNormal3fv( n, 0 );
		gl.glBegin( GL2.GL_POLYGON);
		gl.glVertex3fv( main[a].getv() );
		gl.glVertex3fv( main[b].getv() );
		gl.glVertex3fv( main[c].getv() );
		gl.glVertex3fv( main[d].getv() );
		gl.glEnd();
	}

	public void roofFace1( GL2 gl, int a, int b, int c, int d, float[] n )
	{
		gl.glNormal3fv( n, 0 );
		gl.glBegin( GL2.GL_POLYGON);
		gl.glVertex3fv( roof[a].getv() );
		gl.glVertex3fv( roof[b].getv() );
		gl.glVertex3fv( roof[c].getv() );
		gl.glVertex3fv( roof[d].getv() );
		gl.glEnd();
	}

	public void roofFace2( GL2 gl, int a, int c, int d, float[] n )
	{
		gl.glNormal3fv( n, 0 );
		gl.glBegin( GL2.GL_POLYGON);
		gl.glVertex3fv( roof[a].getv() );
		gl.glVertex3fv( roof[c].getv() );
		gl.glVertex3fv( roof[d].getv() );
		gl.glEnd();
	}
}
