package mckenzie;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

public class Roof 
{
	Point[] corner;
	float[] na = {  0,  0,  1, 0 };
	float[] nb = {  1,  0,  0, 0 };
	float[] ne = { -1,  0,  0, 0 };
	float[] nc = {  0,  1,  0, 0 };
	float[] nd = {  0, -1,  0, 0 };
	float[] nf = {  0,  0, -1, 0 };

	float[] color;

	public Roof(double aX, double aZ, float[] rColor)
	{
		color = rColor;
		
		corner = new Point[8];

		corner[0] = new Point( aX, 1, 0+aZ );
		corner[1] = new Point( aX, 1, 1+aZ );
		corner[2] = new Point( aX, 0.5+1, 0.5+aZ );
		corner[4] = new Point( aX+1, 1, 0+aZ );
		corner[5] = new Point( aX+1, 1, 1+aZ );
		corner[6] = new Point( aX+1, 0.5+1, 0.5+aZ );
	}

	public void drawMe( GL2 gl )
	{   
		float red[] =   {1.0f,0.0f,0.0f,1.0f}; // color red
	   float green[] = { 0.0f, 1.0f, 0.3f, 1.0f }; // green ish
	   float blue[] = { 0.0f, 0.3f, 1.0f, 1.0f};
		//gl.glColor4fv(blue, 0);  
		
		gl.glMaterialfv(GL.GL_FRONT, GL2.GL_AMBIENT_AND_DIFFUSE, color, 0);

		gl.glPushMatrix();
		gl.glTranslated(-0.5, -0.5, -0.5 ); // center the cube on the origin

		faceS( gl, 1,5,6,2,na );
		faceT( gl, 4,6,5,nb );
		faceS( gl, 1,0,4,5,nd );
		faceT( gl, 1,2,0,ne );
		faceS( gl, 0,2,6,4,nf );
		gl.glPopMatrix();
	}

	public void faceS( GL2 gl, int a, int b, int c, int d, float[] n )
	{
		gl.glNormal3fv( n, 0 );
		gl.glBegin( GL2.GL_POLYGON);
		gl.glVertex3fv( corner[a].getv() );
		gl.glVertex3fv( corner[b].getv() );
		gl.glVertex3fv( corner[c].getv() );
		gl.glVertex3fv( corner[d].getv() );
		gl.glEnd();
	}
	
	public void faceT( GL2 gl, int a, int c, int d, float[] n )
	{
		gl.glNormal3fv( n, 0 );
		gl.glBegin( GL2.GL_POLYGON);
		gl.glVertex3fv( corner[a].getv() );
		gl.glVertex3fv( corner[c].getv() );
		gl.glVertex3fv( corner[d].getv() );
		gl.glEnd();
	}
}
