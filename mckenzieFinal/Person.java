//Person.java
//Makes a person, all head colors are same, receives shirt color
//McKenzie Bradley CS355 Fall 2014

package mckenzieFinal;


import javax.media.opengl.GL;
import javax.media.opengl.GL2;



public class Person 
{
	float red[] =   {1.0f,0.0f,0.0f,1.0f}; // color red
	float green[] = { 0.0f, 1.0f, 0.3f, 1.0f }; // green ish
	float blue[] = { 0.0f, 0.3f, 1.0f, 1.0f};
	float white[] = { 1.0f, 1.0f, 1.0f, 1.0f};
	float yellow[] = { 1.0f, 1.0f, 0.4f };

	Point1[] corner;
	Point1[] hcorner;
	float[] na = {  0,  0,  1, 0 };
	float[] nb = {  1,  0,  0, 0 };
	float[] ne = { -1,  0,  0, 0 };
	float[] nc = {  0,  1,  0, 0 };
	float[] nd = {  0, -1,  0, 0 };
	float[] nf = {  0,  0, -1, 0 };

	float[] norm = {  0,  1,  0, 0 };
	V3 position;
	V3 velocity; // units per second
	
	float[] cColor;
	public Person(float[] col)
	{
		cColor = col;
		corner = new Point1[8];

		corner[0] = new Point1( 0, -0.5, 0 );
		corner[1] = new Point1( 0, -0.5, 0.25 );
		corner[2] = new Point1( 0, 0.05, 0 );
		corner[3] = new Point1( 0, 0.05, 0.25 );
		corner[4] = new Point1( 0.25, -0.5, 0 );
		corner[5] = new Point1( 0.25, -0.5, 0.25 );
		corner[6] = new Point1( 0.25, 0.05, 0 );
		corner[7] = new Point1( 0.25, 0.05, 0.25 );
		
		
		hcorner = new Point1[8];

		hcorner[0] = new Point1( 0, 0.05, 0 );
		hcorner[1] = new Point1( 0, 0.05, 0.25 );
		hcorner[2] = new Point1( 0, 0.25, 0 );
		hcorner[3] = new Point1( 0, 0.25, 0.25 );
		hcorner[4] = new Point1( 0.25, 0.05, 0 );
		hcorner[5] = new Point1( 0.25, 0.05, 0.25 );
		hcorner[6] = new Point1( 0.25, 0.25, 0 );
		hcorner[7] = new Point1( 0.25, 0.25, 0.25 );

		velocity = new V3( 0.1, 0.1, 0.0 );
		position = new V3(-1, 0, 5 );

	}


	// move the bird this much time 
	public void move( double deltat )
	{
		// change the velocity by a random amount
		V3 change = new V3( Math.random()-0.5, 
				Math.random()-0.5,
				Math.random()-0.5
				);
		change.mult( 0.2 );
		velocity.add( change );

		velocity.w[1] = 0;
		
		if(position.w[0]>12 || position.w[0]<-3)
		{
			velocity.w[0] = position.w[0]*(-1);
		}
		if(position.w[2]>12 || position.w[2]<-3)
		{
			velocity.w[2] = position.w[2]*(-1);
		}

		// move in direction of velocity by deltat
		V3  dx = new V3( velocity );
		dx.mult( deltat );
		position.add( dx );
	}

	public void drawMe(GL2 gl)
	{
		gl.glPushMatrix();
		gl.glTranslatef( position.w[0],position.w[1],position.w[2] ); 

		double angle = Math.atan2( -velocity.w[2], velocity.w[0] );

		gl.glRotated( angle*57.2957795 , 0, 1, 0 );

		// This has shininess added, doesn't work great yet ...
		gl.glMaterialf(GL.GL_FRONT, GL2.GL_SHININESS, 100f );

		gl.glMaterialfv(GL.GL_FRONT, GL2.GL_AMBIENT_AND_DIFFUSE, cColor, 0);
		gl.glMaterialfv( GL.GL_FRONT, GL2.GL_SPECULAR, white, 0 );

		//face( gl, 0, 1, 2,norm );

		face( gl, 1,5,7,3,na );
		face( gl, 4,6,7,5,nb );
		face( gl, 7,6,2,3,nc );
		face( gl, 1,0,4,5,nd );
		face( gl, 1,3,2,0,ne );
		face( gl, 0,2,6,4,nf );
		
		gl.glMaterialfv(GL.GL_FRONT, GL2.GL_AMBIENT_AND_DIFFUSE, yellow, 0);
		gl.glMaterialfv( GL.GL_FRONT, GL2.GL_SPECULAR, white, 0 );

		//face( gl, 0, 1, 2,norm );

		faceh( gl, 1,5,7,3,na );
		faceh( gl, 4,6,7,5,nb );
		faceh( gl, 7,6,2,3,nc );
		faceh( gl, 1,0,4,5,nd );
		faceh( gl, 1,3,2,0,ne );
		faceh( gl, 0,2,6,4,nf );

		gl.glPopMatrix();
	}

	public void face( GL2 gl, int a, int b, int c, int d,float[] n )
	{
		gl.glNormal3fv( n, 0 );
		gl.glBegin( GL2.GL_POLYGON);
		gl.glVertex3fv( corner[a].getv() );
		gl.glVertex3fv( corner[b].getv() );
		gl.glVertex3fv( corner[c].getv() );
		gl.glVertex3fv(corner[d].getv());
		gl.glEnd();
	}
	
	public void faceh( GL2 gl, int a, int b, int c, int d,float[] n )
	{
		gl.glNormal3fv( n, 0 );
		gl.glBegin( GL2.GL_POLYGON);
		gl.glVertex3fv( hcorner[a].getv() );
		gl.glVertex3fv( hcorner[b].getv() );
		gl.glVertex3fv( hcorner[c].getv() );
		gl.glVertex3fv(hcorner[d].getv());
		gl.glEnd();
	}
}
