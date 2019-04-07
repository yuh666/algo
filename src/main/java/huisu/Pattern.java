package huisu;

public class Pattern {

    private boolean success = false;
    private String pattern;

    public Pattern(String pattern) {
        this.pattern = pattern;
    }


    public boolean macth(String str) {
        return _macth(str, 0, 0);
    }

    public boolean _macth(String str, int pj, int sj) {
        if (pj == pattern.length() && sj == str.length()) {
            return true;
        }
        char pc = pattern.charAt(pj);
        char sc = str.charAt(sj);
        if (pc == '*') {
            for (int i = 0; i <= str.length() - sj; i++) {
                if (_macth(str, pj + 1, sj + i)) {
                    return true;
                }
            }
        } else if (pc == '?') {
            if (_macth(str, pj + 1, sj)) {
                return true;
            }
            if (_macth(str, pj + 1, sj + 1)) {
                return true;
            }
        } else {
            if (pc == sc) {
                return _macth(str, pj + 1, sj + 1);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Pattern pattern = new Pattern("ab?c");
        System.out.println(pattern.macth("abxc"));
    }

}
