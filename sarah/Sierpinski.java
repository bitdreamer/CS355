package sarah;


import java.awt.*;

public class Sierpinski extends Frame {
    protected static Graphics this_graphics;
    protected static Dimension this_size;
    static int area_size = 300;
    protected Button dotest;
    protected Button doquit;
    
    // drawGasket
    //
    // Draws a Sierpinski gasket using multiple calls to drawTriangle.
    // The gasket consists of <depth> sizes of triangles, bounded by
    // the points represented by <a>, <b>, and <c>. Each point is
    // an array of two real numbers, the x-coordinate followed by the
    // y-coordinate. Both coordinates will be between 0 and 1.
    public static void drawGasket(int depth, double[] a, double[] b, double[] c) {
        // Insert your code here.
    }
    
    // Sierpinski
    //
    // Initializes the dialog box
    public Sierpinski() {
        super("Sierpinski gasket");
        
        this.resize(area_size, area_size);
    }
    
    // drawTriangle
    //
    // Draws a triangle between the points <x>, <y>, and <z>.
    public static void drawTriangle(double[] x, double[] y, double[] z) {
        int[][] coord = new int[2][4];
        int i;
        
        coord[0][0] = (int) (this_size.width * x[0]);
        coord[0][1] = (int) (this_size.width * y[0]);
        coord[0][2] = (int) (this_size.width * z[0]);
        coord[0][3] = (int) (this_size.width * x[0]);
        
        coord[1][0] = (int) (this_size.height * x[1]);
        coord[1][1] = (int) (this_size.height * y[1]);
        coord[1][2] = (int) (this_size.height * z[1]);
        coord[1][3] = (int) (this_size.height * x[1]);
        
        this_graphics.drawPolygon(coord[0], coord[1], 4);
    }
    
    // action
    //
    // Handle button clicks. (This code isn't effective right now.)
    public boolean action(Event e, Object arg) {
        String input;
        int num;
        int is_prime;
        
        this.dispose();
        return true;
    }
    
    // gotFocus
    //
    // This does nothing.
    public boolean gotFocus(Event e, Object arg) {
        return true;
    }
    
    // paint
    //
    // Set up for the call to draw the gasket
    public void paint(Graphics g) {
        double[] x = new double[2];
        double[] y = new double[2];
        double[] z = new double[2];
        
        x[0] = 0.0; x[1] = 0.0;
        y[0] = 1.0; y[1] = 0.0;
        z[0] = 0.5; z[1] = 1.0;
        this_graphics = g;
        this_size = this.size();
        drawTriangle(x, y, z);
        drawGasket(7, x, y, z);
    }
    
    // main
    //
    // Create the dialog box
    public static void main(String[] args) {
        Sierpinski f;
        
        // create the dialog box
        f = new Sierpinski();
        f.resize(300, 300);
        f.setResizable(false);
        f.show();
    }
}
