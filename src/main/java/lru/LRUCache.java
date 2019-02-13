package lru;

/**
 * LRU缓存接口
 * @author yuh
 * @date 2019-02-13 09:21
 **/
public interface LRUCache<K, V> {

    V get(K key);

    void add(K key, V val);

    void print();
}
