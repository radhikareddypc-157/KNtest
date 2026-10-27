public class GreatestOfThree {
    public static int findGreatest(int a, int b, int c) {
        if (a >= b && a >= c) {
            return a;
        } else if (b >= a && b >= c) {
            return b;
        } else {
            return c;
        }
    }

    public static void main(String[] args) {
        int a = 10, b = 25, c = 15;
        int greatest = findGreatest(a, b, c);
        System.out.println("Greatest is: " + greatest);
    }
}