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
        if((ch.equals("+")) || (ch.equals("-")) || (ch.equals("*")) || (ch.equals(")")) ||  (ch.equals("(")) || (ch.equals("/"))){
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

                String cur = input.substring(i, i+1);

                if(isNum(input, i)) {

                    output += cur;

                } else if (cur.equals(")")) {
                    boolean end = false;
                    while (!end) {
                        if (stack.peek().equals("(")) {
                            stack.pop();
                            end = true;
                        } else {
                            output += stack.pop();
                        }
                    }

                } else if (stack.isEmpty()) {

                    stack.push(cur);

                } else if (cur.equals("(")) {

                    stack.push(cur);

                } else {

                    if (checkPrecedence(cur, stack.peek())) {

                        stack.push(cur);

                    } else {

                        while (!stack.isEmpty() && !checkPrecedence(cur, stack.peek())) {

                            output += stack.pop();

                        }

                        stack.push(cur);

                    }

                }

            }
        }
        while (!(stack.isEmpty())){
            output += stack.pop();
        }
        return output;
    }

    public static boolean checkPrecedence(String first, String second) {
        int f = 0;
        int s = 0;
    if (first.equals("*") || first.equals("/")) {
            f = 2;
        } else  if (first.equals("+") || first.equals("-")) {
            f = 1;
        }
    if (second.equals("*") || second.equals("/")) {
            s = 2;
        } else  if (second.equals("+") || second.equals("-")) {
            s = 1;
        }
        return (f > s);
    }
    //true is higher, false is lower


}
