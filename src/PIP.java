import java.io.*;
import java.util.*;
public class PIP {

    static String evaluatePostfix(String input) {

        Stack<String> stack = new Stack<String>();

        for(int i = 0; i< input.length(); i++) {
            if(!((input.substring(i,i+1)).equals(" "))) {
                if (isNum(input, i)) {
                    stack.push(input.substring(i, i + 1));
                } else {
                    String second = stack.pop();
                    String first = stack.pop();
                    stack.push(doMath(first, second, input.substring(i, i + 1)));
                }
            }
        }
        String answer = stack.pop();
        if(!(stack.empty())) {
            throw new IllegalArgumentException("invalid postfix expression");
        }
        return answer;
    }

    public static boolean isNum(String str, int index) {
        String ch = str.substring(index, index+1);
        if((ch.equals("+")) || (ch.equals("-")) || (ch.equals("*")) || (ch.equals("/"))){
            return false;
        }
        return true;
    }

    public static String doMath(String first, String second, String op) {
        if (op.substring(0,1).equals("+")){
            int f = Integer.parseInt(first);
            int s = Integer.parseInt(second);

            return Integer.toString(f+s);
        }
        if (op.substring(0,1).equals("-")){
            int f = Integer.parseInt(first);
            int s = Integer.parseInt(second);

            return Integer.toString(f-s);
        }
        if (op.substring(0,1).equals("*")){
            int f = Integer.parseInt(first);
            int s = Integer.parseInt(second);

            return Integer.toString(f*s);
        }
        if (op.substring(0,1).equals("/")){
            int f = Integer.parseInt(first);
            int s = Integer.parseInt(second);

            return Integer.toString(f/s);
        }
        else{
          throw new IllegalArgumentException("invalid operator");
        }
    }

    static String infixToPostfix(String input) {

        Stack<String> stack = new Stack<String>();

        String output = "";

        for(int i = 0; i< input.length(); i++) {
            if(!((input.substring(i,i+1)).equals(" "))) {

                String curr = input.substring(i, i + 1);

                if (isNum(input, i)) {

                    output += curr;

                }
            else {



                }


            }
        }
    }

    public static boolean checkPrecedence(String first, String second) {
        if (first.equals("(")) {
            return true;
        }
        if (second.equals("(")) {
            return false;
        }
        if (first.equals("+") || first.equals("-")) {
            if (second.equals("*") || second.equals("/")) {
                return false;
            }
        }

        if (first.equals("*") || first.equals("/")) {
            if (second.equals("+") || second.equals("-")) {
                return true;
            }
        }

        return false;
    }
    //true is higher, false is lower


}
