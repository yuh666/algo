package simhash;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.Funnels;
import com.google.common.hash.PrimitiveSink;
import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.recognition.Recognition;
import org.ansj.recognition.impl.StopRecognition;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.nlpcn.commons.lang.util.MurmurHash;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

public class Main {

    //single thread, avoid GC
    private final int[] bits = new int[64];
    //simhash reverse index
    private Map<Long, Set<Long>> simhashReverseMap;
    //bloomFilter
    private BloomFilter<Long> bloomFilter;
    //stopWord
    private StopRecognition s;

    public void init(String absolutePath, String stopWordPath) throws IOException {
        long l = System.currentTimeMillis();
        s = new StopRecognition();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(stopWordPath));
        String sw;
        while ((sw = bufferedReader.readLine()) != null) {
            s.insertStopWords(sw);
        }
        FileReader fileReader = new FileReader(absolutePath);
        LineNumberReader lineNumberReader = new LineNumberReader(fileReader);
        lineNumberReader.skip(Long.MAX_VALUE);
        System.out.println("文件共有" + lineNumberReader.getLineNumber() + "行");
        simhashReverseMap = new HashMap<>(lineNumberReader.getLineNumber());
        bloomFilter = BloomFilter.create(Funnels.longFunnel(), lineNumberReader.getLineNumber());
        lineNumberReader.close();
        fileReader.close();
        lineNumberReader = new LineNumberReader(new FileReader(absolutePath));
        String line;
        int count = 0;
        while ((line = lineNumberReader.readLine()) != null) {
            long simhash = simhash(line);
            if (bloomFilter.mightContain(simhash)) {
                continue;
            }
            count++;
            bloomFilter.put(simhash);
            for (int i = 0; i < 8; i++) {
                long quaterHash = simhash >> i * 8 & 0xff;
                Set<Long> set = simhashReverseMap.get(quaterHash);
                if (set == null) {
                    set = new HashSet<>();
                    simhashReverseMap.put(quaterHash, set);
                }
                set.add(simhash);
            }
        }
        lineNumberReader.close();
        fileReader.close();
        System.out.println("构造不重复simhash数目:" + count + "个,耗时:" + (System.currentTimeMillis() - l) + "ms");
    }


    public long simhash(String doc) {
        Arrays.fill(bits, 0);
        Result result = ToAnalysis.parse(doc).recognition(s);
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


    public boolean mayContains(String doc) {
        long simhash = simhash(doc);
        if (bloomFilter.mightContain(simhash)) {
            return true;
        }
        for (int i = 0; i < 8; i++) {
            long quaterHash = simhash >> i * 8 & 0xff;
            Set<Long> set = simhashReverseMap.get(quaterHash);
            if (set == null) {
                continue;
            }
            for (Long hash : set) {
                if (Long.bitCount(simhash ^ hash) < 10) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.init("C:\\Users\\yuh\\IdeaProjects\\algo\\src\\main\\java\\simhash\\log.txt",
                "C:\\Users\\yuh\\IdeaProjects\\algo\\src\\main\\java\\simhash\\stopwords.dic");
//        for (int i = 0; i < 100000; i++) {
//            long l = System.nanoTime();
//            boolean b = main.mayContains("我们都有一个家名字叫美国,");
//            //compare between interprete and JIT
//            if (i < 10 || i > 99990) {
//                System.out.println(b+","+(System.nanoTime() - l));
//            }
//        }
    }

}
