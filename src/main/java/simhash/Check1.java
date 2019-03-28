package simhash;


import java.util.*;
// welcome to mail to longcheng@58.com

public class Check1 {

    private TreeMap<Integer, BitSet> map = new TreeMap<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    });

    // you must rewrite this function
    public void init(Vector<String> docList) {
        for (String s : docList) {
            int count = 0;
            BitSet bitSet = new BitSet();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (bitSet.get(c)) {
                    continue;
                }
                bitSet.set(c);
                count++;
            }
            map.put(count, bitSet);
        }

    }


    public int check(char[] info, int infoLen) {
        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < infoLen; i++) {
            set.add(info[i]);
        }
        int v = (int) (set.size() / 0.6D) + 1;
        SortedMap<Integer, BitSet> tailMap = map.tailMap(v);
        if (tailMap.isEmpty()) {
            return 0;
        }
        int i = 0;
        for (Map.Entry<Integer, BitSet> entry : tailMap.entrySet()) {
            Integer len = entry.getKey();
            BitSet value = entry.getValue();
            for (Character character : set) {
                if (value.get(character)) {
                    i++;
                    if((double)i / len >= 0.6D){
                        return 1;
                    }
                }
            }
        }
        return 0;
    }


    public static void main(String[] args) {
        String a = "abcdefghigklmn";
        String f = "abcdefghigklmn1234253456456540nbclkahfasdbfaksbflasdhf;osdhfo;jhdoihfbksn slncs7567";
        String b = "abcdefgh";
        Vector<String> vector = new Vector<>();
        vector.add(a);
        vector.add(f);
        Check1 check1 = new Check1();
        check1.init(vector);
        System.out.println(check1.check(b.toCharArray(),b.length()));
    }

}

