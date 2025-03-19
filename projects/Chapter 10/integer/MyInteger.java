public class MyInteger {
    private int value;

    public MyInteger(int value) {
        this.value = value;
    }

    public int getvalue() {
        return value;
    }

    public boolean iseven() {
        return iseven(this.value);
    }

    public boolean isodd() {
        return isodd(this.value);
    }

    public boolean isprime() {
        return isprime(this.value);
    }

    public boolean equals(int value) {
        return this.value == value;
    }

    public boolean equals(MyInteger myinteger) {
        return this.value == myinteger.getvalue();
    }

    public static boolean iseven(int value) {
        return value % 2 == 0;
    }

    public static boolean isodd(int value) {
        return value % 2 != 0;
    }

    public static boolean isprime(int value) {
        if (value <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(value); i++) {
            if (value % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean iseven(MyInteger myinteger) {
        return iseven(myinteger.getvalue());
    }

    public static boolean isodd(MyInteger myinteger) {
        return isodd(myinteger.getvalue());
    }

    public static boolean isprime(MyInteger myinteger) {
        return isprime(myinteger.getvalue());
    }

    public static int parseint(char[] chars) {
        return Integer.parseInt(new String(chars));
    }

    public static int parseint(String s) {
        return Integer.parseInt(s);
    }
}