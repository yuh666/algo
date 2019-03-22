package simhash;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.recognition.impl.StopRecognition;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.nlpcn.commons.lang.util.MurmurHash;

import java.io.*;
import java.util.*;

/**
 * ##########初始化过程##########
 *
 *                     分词（去掉停止词）               逐词hash
 * 如果可以,别下完这场雨      --->         可以 雨       --->   可以(00001110101010) 雨(0001010101010)
 *
 * 每个词hash分散到64位上并累加(1 -> +1 , 0 -> -1)        降维得到最终hash(由多个维度降低为两个维度 即0,1 >0 -> 1 <0 -> -1)
 *        --->      [-1,1,2,......,-1]                 --->    011010101010100101
 *
 * bloomFilter过滤(保证相同的能在O(1)搞定)                hash拆成4段（每16位一段） 并反向索引
 *      --->          bloomFilter.insert(hash)        ---> {10101010101010 ->[hash1,..hashn],...}
 *
 * ############查找过程###########
 *
 *  hash(与上面一致) -> bloomFilter.mightContain(hash) || hash拆成四段去取的每段对应的列表 遍历计算Hamming距离 < 10 认为是相似
 *
 */
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
        initStopWrods(stopWordPath);
        int lineNumber = getLineNumber(absolutePath);
        System.out.println("文件共有" + lineNumber + "行");
        simhashReverseMap = new HashMap<>(lineNumber);
        bloomFilter = BloomFilter.create(Funnels.longFunnel(), lineNumber);
        int count = initBloomFilterAndReverseMap(absolutePath);
        System.out.println("构造不重复simhash数目:" + count + "个,耗时:" + (System.currentTimeMillis() - l) + "ms");
    }

    public boolean mayContains(String doc) {
        long simhash = simhash(doc);
        if (bloomFilter.mightContain(simhash)) {
            return true;
        }
        for (int i = 0; i < 4; i++) {
            long quaterHash = simhash >> i * 16 & 0xffff;
            Set<Long> set = simhashReverseMap.get(quaterHash);
            if (set == null) {
                continue;
            }
            for (Long hash : set) {
                if (hammingDistance(hash, simhash) < 10) {
                    return true;
                }
            }
        }
        return false;
    }

    private int hammingDistance(long hash1, long hash2) {
        return Long.bitCount(hash1 ^ hash2);
    }

    private int initBloomFilterAndReverseMap(String docPath) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(docPath));
        String line;
        int count = 0;
        while ((line = bufferedReader.readLine()) != null) {
            long simhash = simhash(line);
            if (bloomFilter.mightContain(simhash)) {
                continue;
            }
            count++;
            bloomFilter.put(simhash);
            for (int i = 0; i < 4; i++) {
                long quaterHash = simhash >> i * 16 & 0xffff;
                Set<Long> set = simhashReverseMap.get(quaterHash);
                if (set == null) {
                    set = new HashSet<>();
                    simhashReverseMap.put(quaterHash, set);
                }
                set.add(simhash);
            }
        }
        bufferedReader.close();
        return count;
    }

    private void initStopWrods(String path) throws IOException {
        s = new StopRecognition();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        String sw;
        while ((sw = bufferedReader.readLine()) != null) {
            s.insertStopWords(sw);
        }
    }

    private int getLineNumber(String path) throws IOException {
        LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(path));
        lineNumberReader.skip(Long.MAX_VALUE);
        lineNumberReader.close();
        return lineNumberReader.getLineNumber();
    }


    private long simhash(String doc) {
        Arrays.fill(bits, 0);
        Result result = ToAnalysis.parse(doc).recognition(s);
        List<Term> terms = result.getTerms();
        System.out.println(terms);
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


    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.init("/Users/yuh/develop/daojia_projects/algo/src/main/java/simhash/rain.txt",
                "/Users/yuh/develop/daojia_projects/algo/src/main/java/simhash/stopwords.dic");
        String str = "属于90后的玩游戏黄金年纪已经慢慢逝去，想必各大90玩家们都开始踏进工作和社会上的浮躁，甚至有的已经成家立业了，" +
                "如今在快节奏娱乐方式和年纪和当年的自己格格不入，碎片化的时间对游戏的热爱痴迷程度往往不如当年，" +
                "作为一名游戏玩家希望把自己在当年所玩过的游戏仅存的一些记忆在这里分享给大家，可能每个人所玩过的游戏不一样，" +
                "我只是把我自己在当年所玩过的在这里做分我知道享，希望不被历史所埋没这些启蒙经典单机游戏。";


        System.out.println(main.mayContains(str));
    }

}
