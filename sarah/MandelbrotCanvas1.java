package sarah;

import java.awt.*;
import java.lang.Object.*;

public class MandelbrotCanvas1 extends Canvas{

    boolean draw = false;
    ComplexNumber[][] cnSet = new ComplexNumber[600][600];
    int depth = 255;
    double lx = -1.5;
    double ly = -1.25;
    double ux = 0.5;
    double uy = 1.25;
    int size = 600;
    
    
    double xStep = (ux - lx)/size;
    double yStep = (uy - ly)/size;
    
    
    MandelbrotCanvas1(){
        super();
        for( int i = 0; i < size; i ++){
            for (int j = 0; j < size; j++){
                cnSet[i][j] = new ComplexNumber(lx + i*xStep, uy - j*yStep);
            }
        }
    
    }
    
    //initialize cnSet
    
    public void paint(Graphics g){
        if (draw){
            
            //iterate each point
            ComplexNumber cn = new ComplexNumber();
            for (int i = 0; i < size; i++){
                for (int j = 0; j < size; j++){
                    iterate(cn, cnSet[i][j], 0, g);
                    g.fillRect(i ,j ,1,1);
                    
                }
            }
               
        }
            
    }
    
    //z is temp, c is number in arrray
    public void iterate(ComplexNumber z, ComplexNumber c, int iterations, Graphics g){
        if (iterations == depth){
            g.setColor(Color.BLACK);
        } else {
            
            if (z.getMagnitude() > 2 ){
                Color cl = new Color(180, 0 + iterations*2, 0);
                g.setColor(cl);
            } else {
                z = c;
                z.multiply(c);
                z.add(c);
               
                iterate(z, c, iterations + 1, g);
            }
        }
    }

                
    public void draw(){
        draw = true;
    }
    

        
        
        
}

        

