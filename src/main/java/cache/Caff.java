package cache;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;

import java.util.concurrent.TimeUnit;

/**
 * @author yuh
 * @date 2019-04-25 17:22
 **/
public class Caff {

    public static void main(String[] args) {
        LoadingCache<String,String> cache = Caffeine.newBuilder().expireAfterWrite(10,TimeUnit.SECONDS).build(k -> null);
        cache.get("a");
    }
}
