import java.io.*;
import java.util.*;

/**
 * evaluates Postfix expressions and translates from infix to postfix
 *
 * @author Ben Andrews, Marie Viita, Kellyn Hartzler
 */
public class PIP {
    /**
     *Solves a postfix expression and returns the solution.
     *
     * @param input The postfix expression being input to be solved
     * @return A solution to the input postfix expression
     */
    static String evaluatePostfix(String input) {
        Stack<String> stack = new Stack<String>();
        for(int i = 0; i< input.length(); i++) {
            if(!((input.substring(i,i+1)).equals(" "))) {
                //If the next item is a number push to stack until an operator is found
                if (isNum(input, i)) {
                    stack.push(input.substring(i, i + 1));
                } else {
                    //When an operator is found, apply it to the two numbers in the stack using doMath and put the new single number into the stack
                    String second = stack.pop();
                    String first = stack.pop();
                    stack.push(doMath(first, second, input.substring(i, i + 1)));
                }
            }
        }
        //Final number in the stack SHOULD be the answer
        String answer = stack.pop();
        //If there are more numbers in the stack, it the expression is incorrect
        if(!(stack.empty())) {
            throw new IllegalArgumentException("invalid postfix expression");
        }
        return answer;
    }

    /**
     * Tests if a character at index of str is an operator.
     *
     * @param str The string being searched in.
     * @param index The single character substring to be tested.
     * @return If the character in question is a non-operator.
     */
    public static boolean isNum(String str, int index) {
        String ch = str.substring(index, index+1);
        //All operators being tested for
        if((ch.equals("+")) || (ch.equals("-")) || (ch.equals("*")) || (ch.equals(")")) ||  (ch.equals("(")) || (ch.equals("/"))){
            return false;
        }
        return true;
    }

    /**
     * Applies a specified operation to two different numbers.
     *
     * @param first The first number in the expression.
     * @param op The operator in between the two numbers.
     * @param second The number at the end.
     * @return the result of the operation.
     */
    public static String doMath(String first, String second, String op) {

        //first + second
        if (op.substring(0,1).equals("+")){
            int f = Integer.parseInt(first);
            int s = Integer.parseInt(second);

            return Integer.toString(f+s);
        }
        //first - second
        if (op.substring(0,1).equals("-")){
            int f = Integer.parseInt(first);
            int s = Integer.parseInt(second);

            return Integer.toString(f-s);
        }
        //first * second
        if (op.substring(0,1).equals("*")){
            int f = Integer.parseInt(first);
            int s = Integer.parseInt(second);

            return Integer.toString(f*s);
        }
        //first / second
        if (op.substring(0,1).equals("/")){
            int f = Integer.parseInt(first);
            int s = Integer.parseInt(second);

            return Integer.toString(f/s);
        }
        //If it's invalid then throw an exception
        else{
          throw new IllegalArgumentException("invalid operator");
        }
    }

    /**
     * Takes an equation in infix and returns it in postfix
     *
     * @param input the infix equation to be translated
     * @return the postfix equation
     */
    static String infixToPostfix(String input) {

        Stack<String> stack = new Stack<String>();

        String output = "";

        //loops through each character of the input and adds to stack or output
        for(int i = 0; i< input.length(); i++) {
            //if character is a space, it is skipped
            if(!((input.substring(i,i+1)).equals(" "))) {

                String cur = input.substring(i, i+1);

                // tests if it's a number, if it is, adds to output
                if(isNum(input, i)) {

                    output += cur;

                    // else it must be an operator, finds precedence then appropriately adds to output or stack
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

    /**
     * Checks precedence between two operators
     *
     * @param first The first operator to be compared.
     * @param second The second operator to be compared.
     * @return If the first operator has precedence over the second
     */
    public static boolean checkPrecedence(String first, String second) {
        int f = 0;
        int s = 0;
        // assigns precedence value to first and second operator
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
