package mckenzie;

import java.util.Iterator;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import com.jogamp.opengl.util.gl2.GLUT;


public class Truck 
{
	float red[] =   {1.0f,0.0f,0.0f,1.0f}; // color red
	float green[] = { 0.0f, 1.0f, 0.3f, 1.0f }; // green ish
	float blue[] = { 0.0f, 0.3f, 1.0f, 1.0f};
	float white[] = { 1.0f, 1.0f, 1.0f, 1.0f};
	float yellow[] = { 1.0f, 1.0f, 0.4f };

	Point[] corner;
	Point[] hcorner;
	float[] na = {  0,  0,  1, 0 };
	float[] nb = {  1,  0,  0, 0 };
	float[] ne = { -1,  0,  0, 0 };
	float[] nc = {  0,  1,  0, 0 };
	float[] nd = {  0, -1,  0, 0 };
	float[] nf = {  0,  0, -1, 0 };

	float[] norm = {  0,  1,  0, 0 };
	V3 position;
	V3 velocity; // units per second
	
	double wheelRot=0.0;
	
	float[] cColor;
	public Truck(float[] col)
	{
		cColor = col;
		corner = new Point[8];

		corner[0] = new Point( 0, -0.5, 0 );
		corner[1] = new Point( 0, -0.5, 0.5 );
		corner[2] = new Point( 0, 0.05, 0 );
		corner[3] = new Point( 0, 0.05, 0.5 );
		corner[4] = new Point( 0.75, -0.5, 0 );
		corner[5] = new Point( 0.75, -0.5, 0.5 );
		corner[6] = new Point( 0.75, 0.05, 0 );
		corner[7] = new Point( 0.75, 0.05, 0.5 );
		
		
		hcorner = new Point[8];

		hcorner[0] = new Point( 0.75, -0.5, 0 );
		hcorner[1] = new Point( 0.75, -0.5, 0.5 );
		hcorner[2] = new Point( 0.75, -0.25, 0 );
		hcorner[3] = new Point( 0.75, -0.25, 0.5 );
		hcorner[4] = new Point( 1, -0.5, 0 );
		hcorner[5] = new Point( 1, -0.5, 0.5 );
		hcorner[6] = new Point( 1, -0.25, 0 );
		hcorner[7] = new Point( 1, -0.25, 0.5 );

		velocity = new V3( 0.1, 0.1, 0.0 );
		position = new V3(2, 0, 7.5 );

	}
	
	public void adjust(double changer, double scoot)
	{
		if(changer>0)
		{
			changer=0.1;
		}
		if(changer==0)
		{
			changer=0;
		}
		if(changer<0)
		{
			changer=-0.1;
		}
		if(scoot>5)
		{
			scoot=5;
		}
		if(scoot<-5)
		{
			scoot=-5;
		}
		// change the velocity by a random amount
		/*V3 change = new V3( changer, 
				0,
				0
				);
		change.mult( 0.2 );
		velocity.add( change );*/

		velocity.w[0]=(float) (changer);
		velocity.w[1] = 0;
		velocity.w[2]=(float)(scoot*changer);
	}


	// move the bird this much time 
	public void move( double deltat)
	{

		/*
		if(position.w[0]>12 || position.w[0]<-3)
		{
			velocity.w[0] = position.w[0]*(-1);
		}
		if(position.w[2]>12 || position.w[2]<-3)
		{
			velocity.w[2] = position.w[2]*(-1);
		}*/

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
		
		gl.glMaterialfv(GL.GL_FRONT, GL2.GL_AMBIENT_AND_DIFFUSE, blue, 0);
		//gl.glRotatef( (float)30.0, 0, (float) wheelRot,0 );
		Wheel wheel1 = new Wheel(0.5,1,blue);
		wheel1.drawMe(gl);
		Wheel wheel2 = new Wheel(1.25,1,blue);
		wheel2.drawMe(gl);
		Wheel wheel3 = new Wheel(0.5,0.4,blue);
		wheel3.drawMe(gl);
		Wheel wheel4 = new Wheel(1.25,0.4,blue);
		wheel4.drawMe(gl);

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
