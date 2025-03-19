
public class TestMyInteger {
    public static void main(String[] args) {
        MyInteger myint1 = new MyInteger(7);
        MyInteger myint2 = new MyInteger(10);

        System.out.println("Testing non-static methods:");
        System.out.println("myint1 value: " + myint1.getvalue());
        System.out.println("Is myint1 even? " + myint1.iseven());
        System.out.println("Is myint1 odd? " + myint1.isodd());
        System.out.println("Is myint1 prime? " + myint1.isprime());
        System.out.println("Does myint1 equal 7? " + myint1.equals(7));
        System.out.println("Does myint1 equal myint2? " + myint1.equals(myint2));

        System.out.println("\nTesting static methods with int parameter:");
        System.out.println("Is 10 even? " + MyInteger.iseven(10));
        System.out.println("Is 10 odd? " + MyInteger.isodd(10));
        System.out.println("Is 10 prime? " + MyInteger.isprime(10));

        System.out.println("\nTesting static methods with MyInteger parameter:");
        System.out.println("Is myint2 even? " + MyInteger.iseven(myint2));
        System.out.println("Is myint2 odd? " + MyInteger.isodd(myint2));
        System.out.println("Is myint2 prime? " + MyInteger.isprime(myint2));

        System.out.println("\nTesting static parsing methods:");
        char[] chars = {'1', '2', '3'};
        String str = "456";
        System.out.println("Parsed int from char array: " + MyInteger.parseint(chars));
        System.out.println("Parsed int from string: " + MyInteger.parseint(str));
    }
}