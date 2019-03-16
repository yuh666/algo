package simhash;

import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.nlpcn.commons.lang.util.MurmurHash;

import java.io.*;
import java.util.*;

public class Main {

    //single thread avoid GC
    private static final int[] bits = new int[64];
    //simhash list
    private static Set<Long> simhashSet;
    //bloom filter

    public static void init(String absolutePath) throws IOException {
        long l = System.currentTimeMillis();
        FileReader fileReader = new FileReader(absolutePath);
        LineNumberReader lineNumberReader = new LineNumberReader(fileReader);
        lineNumberReader.skip(Long.MAX_VALUE);
        System.out.println("文件共有" + lineNumberReader.getLineNumber() + "行");
        simhashSet = new HashSet<>(lineNumberReader.getLineNumber());
        lineNumberReader.close();
        fileReader.close();
        lineNumberReader = new LineNumberReader(new FileReader(absolutePath));
        String line;
        while ((line = lineNumberReader.readLine()) != null) {
            simhashSet.add(simhash(line));
        }
        lineNumberReader.close();
        fileReader.close();
        System.out.println("构造不重复simhash数目:" + simhashSet.size() + "个,耗时:" + (System.currentTimeMillis() - l) + "ms");
    }


    public static long simhash(String doc) {
        Arrays.fill(bits, 0);
        Result result = ToAnalysis.parse(doc);
        List<Term> terms = result.getTerms();
        for (Term term : terms) {
            String realName = term.getRealName();
            long hash = MurmurHash.hash64(realName);
            for (int i = 63; i >= 0; i--) {
                if ((hash >> i & 1) == 1) {
                    bits[63 - i]++;
                } else {
                    bits[63 - i]--;
                }
            }
        }
        long hash = 0;
        long one = 1;
        for (int i = 0; i < 64; i++) {
            if (bits[i] > 0) {
                hash |= one;
            }
            one <<= 1;
        }
        Arrays.fill(bits, 0);
        return hash;
    }


    public static boolean mayContains(String doc) {
        long simhash = simhash(doc);
        if (simhashSet.contains(simhash)) {
            return true;
        }
        for (Long hash : simhashSet) {
            //hanming distance
            if (Long.bitCount(simhash ^ hash) < 5) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        init("C:\\Users\\yuh\\IdeaProjects\\algo\\src\\main\\java\\simhash\\log.txt");
    }

}
