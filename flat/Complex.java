// Complex.java
// 2004 Barrett Koster

package flat;

public class Complex
{
    double real;
    double imag;

    public Complex()
    {
        real = 0; imag = 0;
    }

    public Complex( double r, double i )
    {
        real = r; imag = i;
    }

    public Complex( Complex a )
    {
        real = a.real; imag = a.imag;
    }

    // this one uses a lot of garbage collection
    public Complex plus( Complex a )
    {
        Complex z = new Complex();

        z.real = real + a.real;
        z.imag = imag + a.imag;

        return z;
    }
    
    // add a to THIS number
    public void add( Complex a )
    {
       real += a.real;
       imag += a.imag;
    }

    public Complex minus( Complex a )
    {
        Complex z = new Complex();

        z.real = real - a.real;
        z.imag = imag - a.imag;

        return z;
    }

    public Complex times( Complex a )
    {
        Complex z = new Complex();

        z.real = real*a.real - imag*a.imag;
        z.imag = real*a.imag + imag*a.real;

        return z;

    }
    
    // multiply 'a' into THIS number
    public void multiply( Complex a )
    {
       double rsaved = real;
       double arsaved = a.real;
       real = real*a.real - imag*a.imag;
       imag = rsaved*a.imag + imag*arsaved;
       
    }

    // time.  return Complex which is this one times a (double)
    public Complex times( double a )
    {
        return new Complex( real*a, imag*a );
    }

    public void squareMe()
    {
       double temp = real*real - imag*imag;
       imag = 2 * real * imag;
       real = temp;
    }


    public double magsq()
    {
        return real*real + imag*imag;
    }

    public double mag()
    {
        return Math.sqrt( magsq() );
    }

    public void report() {
        System.out.println("real= "+real+" imag="+imag);
    }

}