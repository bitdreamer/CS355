// Cube.java
// Cube w/ anchor point

package mckenzieFinal;

import javax.media.opengl.*;

public class Cube
{
	Point1[] corner;
	float[] na = {  0,  0,  1, 0 };
	float[] nb = {  1,  0,  0, 0 };
	float[] ne = { -1,  0,  0, 0 };
	float[] nc = {  0,  1,  0, 0 };
	float[] nd = {  0, -1,  0, 0 };
	float[] nf = {  0,  0, -1, 0 };

	float[] color;
	
	double anX;
	double anZ;

	public Cube(double aX, double aZ, float[] cColor)
	{
		anX=aX;
		anZ=aZ;
		color=cColor;
		
		corner = new Point1[8];

		corner[0] = new Point1( aX, 0, 0+aZ );
		corner[1] = new Point1( aX, 0, 1+aZ );
		corner[2] = new Point1( aX, 1, 0+aZ );
		corner[3] = new Point1( aX, 1, 1+aZ );
		corner[4] = new Point1( aX+1, 0, 0+aZ );
		corner[5] = new Point1( aX+1, 0, 1+aZ );
		corner[6] = new Point1( aX+1, 1, 0+aZ );
		corner[7] = new Point1( aX+1, 1, 1+aZ );
	}

	public void drawMe( GL2 gl )
	{
		//gl.glColor4fv(blue, 0);    
		gl.glMaterialfv(GL.GL_FRONT, GL2.GL_AMBIENT_AND_DIFFUSE, color, 0);

		gl.glPushMatrix();
		gl.glTranslated(-0.5, -0.5, -0.5 ); // center the cube on the origin

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
		gl.glVertex3fv( corner[a].getv() );
		gl.glVertex3fv( corner[b].getv() );
		gl.glVertex3fv( corner[c].getv() );
		gl.glVertex3fv( corner[d].getv() );
		gl.glEnd();
	}
	
	public void report()
	{
		System.out.println(anX+" "+anZ);
	}
}
