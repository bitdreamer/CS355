// Cube.java
// Barrett Koster 2014 for CS-355

package threed;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;

import java.util.*;

import flat.Dot;

public class Bird
{
	protected static LinkedList <Bird> murder;
	protected static int murderCount = 0;
	protected static Bird leaderBird;

	float red[] =   {1.0f,0.0f,0.0f,1.0f}; // color red
	float green[] = { 0.0f, 1.0f, 0.3f, 1.0f }; // green ish
	float blue[] = { 0.0f, 0.3f, 1.0f, 1.0f};
	float white[] = { 1.0f, 1.0f, 1.0f, 1.0f};
	float yellow[] = { 1.0f, 1.0f, 0.4f };

	Point[] corner;

	float[] norm = {  0,  1,  0, 0 };
	V3 position;
	V3 velocity; // units per second

	boolean launched = false;

	public Bird()
	{
		murder.add(this);
		murderCount++;

		corner = new Point[3];

		corner[0] = new Point( 1, 0, 0 );
		corner[1] = new Point( -1, 0, -0.3 );
		corner[2] = new Point( -1, 0, 0.3 );

		velocity = new V3( 0.1, 0.1, 0.0 );
		position = new V3(Math.random()-0.5, 
				Math.random()-0.5,
				Math.random()-0.5 );

	}

	public void launcher(boolean l)
	{
		launched = l;
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

		//velocity.w[1] = 0; // hack to make it fly flat for debugging

		// make velocity point toward home moreso if we
		// are far away from home (0,0,0).
		if ( position.magsq() > 10 )
		{
			change = new V3( position);
			change.mult( -0.02 );
			//change.mult(-0.5);
			velocity.add(change);
		}

		// match velocity of nearby birds (1/dist^2)
		if(launched==true)
		{
			Iterator <Bird> it = murder.iterator();
			while ( it.hasNext() )
			{
				double a = setA();	//Integer between 0 and 1
				//1 = adopt others velocity
				//0 = keep own velocity
				//Vmine = a * Vyours + (1-a) * Vmine
				Bird b = it.next();

				V3 distv = b.position.minus( position );
				double dist2 = distv.magsq();
				if ( dist2 <1) { dist2 = 1;}
				a = (a/dist2)*5;

				V3 vel = new V3( b.velocity );
				/*V3 velo = velocity;
         vel.mult( 1/dist2 * 0.01);
         //velocity.add( vel );
         velocity.adjust(vel);*/

				//Makes all the birds fly together
				V3 vela = vel; //yours
				vela.mult(a); //*a
				V3 velocitya = velocity; //mine
				velocitya.mult(1-a); //*1-a
				vela.add(velocitya); //+
				velocity.replace(vela); //=

			}
		}


		// move in direction of velocity by deltat
		V3  dx = new V3( velocity );
		dx.mult( deltat );
		position.add( dx );



	}
	
	public double setA()
	{
		return 0.5/murderCount;
	}

	public void drawMe( GL2 gl )
	{
		//float blue[] = { 0.0f, 0.3f, 1.0f, 1.0f }; // green ish blue
		//gl.glColor4fv(blue, 0);      

		gl.glPushMatrix();
		gl.glTranslatef( position.w[0],position.w[1],position.w[2] ); 

		double angle = Math.atan2( -velocity.w[2], velocity.w[0] );

		gl.glRotated( angle*57.2957795 , 0, 1, 0 );

		// This has shininess added, doesn't work great yet ...
		gl.glMaterialf(GL.GL_FRONT, GL2.GL_SHININESS, 100f );

		gl.glMaterialfv(GL.GL_FRONT, GL2.GL_AMBIENT_AND_DIFFUSE, yellow, 0);
		gl.glMaterialfv( GL.GL_FRONT, GL2.GL_SPECULAR, white, 0 );

		face( gl, 0, 1, 2,norm );

		gl.glPopMatrix();
	}

	public void face( GL2 gl, int a, int b, int c, float[] n )
	{
		gl.glNormal3fv( n, 0 );
		gl.glBegin( GL2.GL_POLYGON);
		gl.glVertex3fv( corner[a].getv() );
		gl.glVertex3fv( corner[b].getv() );
		gl.glVertex3fv( corner[c].getv() );
		gl.glEnd();
	}
}
