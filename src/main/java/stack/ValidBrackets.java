package stack;

public class ValidBrackets {

    public static void main(String[] args) {
        System.out.println(isValidBrackets("((((()))))"));
    }

    public static boolean isValidBrackets(String brackets){
        Stack<Character> stack = new ArrayStack<Character>(brackets.length());
        char[] chars = brackets.toCharArray();
        for (char aChar : chars) {
            if (aChar == '('){
                stack.push('(');
            }else{
                Character pop = stack.pop();
                if(pop == null || pop != '('){
                    return false;
                }
            }
        }
        return stack.size() == 0;
    }
}
