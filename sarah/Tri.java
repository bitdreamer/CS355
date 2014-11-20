package sarah;

import java.awt.Point;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;

import flat.TwoTemplate;

public class Tri extends JFrame implements GLEventListener
{
	   private GLU glu = new GLU(); // just has some function we like
	   
	    public static void main(String[] args) 
	    {
	       new Tri();
	    }
	public Tri()
{

	      setTitle("TwoTemplate");
	      GLProfile profile = GLProfile.get(GLProfile.GL2);
	      GLCapabilities capabilities = new GLCapabilities(profile);
	 
	      // The canvas is the widget that's drawn in the JFrame
	      GLCanvas glcanvas = new GLCanvas(capabilities);
	      glcanvas.addGLEventListener(this);
	      glcanvas.setSize( 300, 300 );
	      getContentPane().add( glcanvas);
	      setSize( getContentPane().getPreferredSize() );
	      setVisible( true );
	
}    
	
	
public void drawMe(GLAutoDrawable gLDrawable)
{
	final GL2 gl = gLDrawable.getGL().getGL2(); // make the gl so we can draw
    gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
    gl.glLoadIdentity();


    float red[] =   {1.0f,0.0f,0.0f,1.0f}; // color red
    float green[] = { 0.0f, 1.0f, 0.3f, 1.0f }; // green ish

    gl.glBegin(GL2.GL_TRIANGLES);     
   

    gl.glColor4fv(red, 0);
	gl.glVertex2f(0.0f, 2.0f);
	gl.glVertex2f(-0.2f, -2.0f);
	gl.glVertex2f(2.0f, -2.0f);
	gl.glEnd();
	
	
}
@Override
public void display(GLAutoDrawable arg0) {
	// TODO Auto-generated method stub
	
}
@Override
public void dispose(GLAutoDrawable arg0) {
	// TODO Auto-generated method stub
	
}
@Override
public void init(GLAutoDrawable arg0) {
	// TODO Auto-generated method stub
	
}
@Override
public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
	// TODO Auto-generated method stub
	
}	





}