package hash.consistent;

import java.util.Map;
import java.util.SortedMap;

/**
 * @author yuh
 * @date 2019-02-22 17:41
 **/
public class VirtualNode<V> {

    private String name;
    private int hash;
    private StorageServer<V> storageServer;


    public VirtualNode(int hash, StorageServer<V> storageServer, String name) {
        this.hash = hash;
        this.storageServer = storageServer;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getIp() {
        return storageServer.getIp();
    }

    public void transferPartIOfDataToOther(VirtualNode<V> other) {
        SortedMap<Integer, V> tailMap = storageServer.tailMap(other.hash);
        if (tailMap.isEmpty()) {
            return;
        }
        for (Map.Entry<Integer, V> entry : storageServer.entrySet()) {
            if (entry.getKey() > other.hash) {
                break;
            }
            System.out.println(name + "->" + other.name + "传输数据:" + entry.toString());
            other.storageServer.put(entry.getKey(), entry.getValue());
        }
        for (Map.Entry<Integer, V> entry : other.storageServer.entrySet()) {
            storageServer.remove(entry.getKey());
            System.out.println(name + "移除数据" + entry.toString());
        }
    }

    public V put(int key, V val) {
        return storageServer.put(key, val);
    }

    public V get(int key) {
        return storageServer.get(key);
    }

    public V remove(int key) {
        return storageServer.remove(key);
    }


}
