// Tree.java
//Arocho <3

package arocho.tree;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;

import com.jogamp.opengl.util.gl2.GLUT;

import flat.Dot;

public class Tree
{
	GLUT glut;
	float angle=0;
	float cylHeight;

	float beigeColor[] = { 1.0f, 0.9f, 0.8f, 1.0f};
	float brickColor[] = { 0.79f, 0.25f, 0.32f, 1.0f};

	public Tree(double x, double y, double z)
	{	
		angle =35;
		cylHeight = 2;
		glut = new GLUT();
	}

	public void drawMe( GL2 gl )
	{
		gl.glPushMatrix();
		// This has shininess added, doesn't work great yet ...
		gl.glMaterialf(GL.GL_FRONT, GL2.GL_SHININESS, 100f );

		//Trunk
		gl.glMaterialfv(GL.GL_FRONT, GL2.GL_AMBIENT_AND_DIFFUSE, beigeColor, 0);
		glut.glutSolidCylinder(0.02, cylHeight, 6, 6);
		gl.glPopMatrix();
	}

	public void branch(GL2 gl, float cylHeight) throws InterruptedException{
		//Each branch will be 2/3rd the size of previous
		cylHeight *= 0.66;
		if (cylHeight > 0.2){		
			//To the right
			gl.glPushMatrix();
			gl.glRotated(angle, 0, 1, 0);
			gl.glMaterialfv(GL.GL_FRONT, GL2.GL_AMBIENT_AND_DIFFUSE, beigeColor, 0);
			glut.glutSolidCylinder(0.02, cylHeight, 6, 6);
			gl.glTranslated(0, 0, cylHeight);
			branch(gl, cylHeight);
			if(cylHeight > 0.2 && cylHeight < 0.3){
				this.blooms(gl);}
			gl.glPopMatrix();

			//To the left
			gl.glPushMatrix();
			gl.glRotated(-angle, 0, 1, 0);
			gl.glMaterialfv(GL.GL_FRONT, GL2.GL_AMBIENT_AND_DIFFUSE, beigeColor, 0);
			glut.glutSolidCylinder(0.02, cylHeight, 6, 6);
			gl.glTranslated(0, 0, cylHeight);
			branch(gl, cylHeight);
			if(cylHeight > 0.2 && cylHeight < 0.3){
				this.blooms(gl);}
			gl.glPopMatrix();

			//To the front
			gl.glPushMatrix();
			gl.glRotated(angle, 0, 0, 1);
			gl.glMaterialfv(GL.GL_FRONT, GL2.GL_AMBIENT_AND_DIFFUSE, beigeColor, 0);
			glut.glutSolidCylinder(0.02, cylHeight, 6, 6);
			gl.glTranslated(0, 0, cylHeight);
			branch(gl, cylHeight);
			if(cylHeight > 0.2 && cylHeight < 0.3){
				this.blooms(gl);}
			gl.glPopMatrix();

			//To the back
			gl.glPushMatrix();
			gl.glRotated(-angle, 0, 0, 1);
			gl.glMaterialfv(GL.GL_FRONT, GL2.GL_AMBIENT_AND_DIFFUSE, beigeColor, 0);
			glut.glutSolidCylinder(0.02, cylHeight, 6, 6);
			gl.glTranslated(0, 0, cylHeight);
			branch(gl, cylHeight);
			if(cylHeight > 0.2 && cylHeight < 0.3){
				this.blooms(gl);}
			gl.glPopMatrix();
		}
	}

	public void blooms( GL2 gl )
	{
		gl.glMaterialfv(GL.GL_FRONT, GL2.GL_AMBIENT_AND_DIFFUSE, brickColor, 0);
		glut.glutSolidCube(0.07f);
	}

	public void groundDisturbance( GL2 gl, float xc, float yc, float zc ) 
	{
		double r = 0.3f; // radius.
		

		for( float a = 0;  a <= 360; a+=10 ) {
			float ang = (float) Math.toRadians( a );
			float x = xc + (float) (r*Math.cos( ang ));
			float y = yc + 0f; 
			float z = zc + (float) (r*Math.sin( ang ));
			
			gl.glPushMatrix();
			gl.glRotated(30, 0, 1, 0);
			gl.glTranslated(x, -2.9, z);
			glut.glutSolidCube(0.2f);
			gl.glPopMatrix();
		}
	}
}
