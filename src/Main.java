public class Main {
    public static void main(String[] args) {
        System.out.println("2 + (((3/4) -5)/6)");
        String f = PIP.infixToPostfix("2 + (((3/4) -5)/6)");
       System.out.println(PIP.infixToPostfix("2 + (((3/4) -5)/6)"));
       System.out.println(PIP.evaluatePostfix(f));
    }
}