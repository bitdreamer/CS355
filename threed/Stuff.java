// Stuff.java

package threed;

public class Stuff
{
   public static void main( String[] args )
   {
      new Stuff();
   }
   
   //constructor
   public Stuff()
   {
      SubStuff ss = new SubStuff();
      Frog f = new Frog();
      
   }
   
   public class SubStuff
   {
      int x;
      int y;
      
   }
   
   public class Frog
   {
      double a;
      double b;
      double c;
   }
}
