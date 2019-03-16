package simhash;

import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.nlpcn.commons.lang.util.MurmurHash;

import java.util.List;


public class SimHasher {

    public long simhash(String doc) {
        Result result = ToAnalysis.parse(doc);
        List<Term> terms = result.getTerms();
        int[] bits = new int[64];
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
        return hash;
    }

    public int hammingDistance(long simHash1, long simHash2) {
        return Long.bitCount(simHash1 ^ simHash2);
    }

    public static void main(String[] args) {
        SimHasher simHasher = new SimHasher();
        long simhash1 = simHasher.simhash("我有一头小毛驴我从来也不骑,有一天我心血来潮骑它去赶集,我手里拿着小皮鞭我心里直欢喜");
        long simhash2 = simHasher.simhash("我有一只小毛驴我从来也不骑,有一天我心血来潮牵它去赶集,我手里拿着小皮鞭我心里直突突");
        int distance = simHasher.hammingDistance(simhash1, simhash2);
        System.out.println("相似度:" + (double)(64 - distance) / 64);
    }


}
