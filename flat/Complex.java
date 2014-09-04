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

    // time.  return Complex which is this one times a (double)
    public Complex times( double a )
    {
        return new Complex( real*a, imag*a );
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