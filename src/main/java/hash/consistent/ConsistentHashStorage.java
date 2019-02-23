package hash.consistent;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author yuh
 * @date -- :
 **/
public class ConsistentHashStorage<K, V> {

    private SortedMap<Integer, VirtualNode<V>> circle = new TreeMap<Integer, VirtualNode<V>>();

    /**
     * 添加一个节点
     *
     * @param newServer 新的物理服务器
     */
    public void addNode(StorageServer<V> newServer) {
        System.out.println("开始添加节点[" + newServer.getIp() + "]");
        for (int i = 0; i < 10; i++) {
            System.out.println("开始添加[" + newServer.getIp() + "#VN" + (i + 1) + "]");
            int hash = hash(newServer.getIp() + "#VN" + (i + 1));
            System.out.println("此虚拟节点hash为" + hash);
            VirtualNode<V> newVirtualNode = new VirtualNode<V>(hash, newServer, newServer.getIp() + "#VN" + (i + 1));
            if (!circle.isEmpty()) {
                VirtualNode<V> virtualNode = null;
                SortedMap<Integer, VirtualNode<V>> tailMap = circle.tailMap(hash);
                if (tailMap.isEmpty()) {
                    virtualNode = circle.get(circle.firstKey());
                } else {
                    virtualNode = circle.get(tailMap.firstKey());
                }
                if (virtualNode == null) {
                    System.out.println("第一次添加StorageServer,无需转移数据");
                } else if (virtualNode.getIp().equals(newServer.getIp())) {
                    System.out.println("同StorageServer,无需转移数据");
                } else {
                    //转移数据
                    virtualNode.transferPartIOfDataToOther(newVirtualNode);
                }
            }
            circle.put(hash, newVirtualNode);
            System.out.println("circle剩余虚拟节点数目" + circle.size());
        }
    }

    public void removeNode(StorageServer<V> newServer) {
        if (circle.isEmpty()) {
            System.out.println("环上为空,无法移除");
            return;
        }
        System.out.println("开始删除节点[" + newServer.getIp() + "]");
        for (int i = 0; i < 10; i++) {
            System.out.println("开始删除[" + newServer.getIp() + "#VN" + (i + 1) + "]");
            int hash = hash(newServer.getIp() + "#VN" + (i + 1));
            System.out.println("此虚拟节点hash为" + hash);
            VirtualNode<V> node = circle.get(hash);
            SortedMap<Integer, VirtualNode<V>> tailMap = circle.tailMap(hash);
            VirtualNode<V> virtualNode = null;
            if (tailMap.isEmpty()) {
                virtualNode = circle.get(circle.firstKey());
            } else {
                virtualNode = circle.get(tailMap.firstKey());
            }
            //copy
            node.transferPartIOfDataToOther(virtualNode);
            circle.remove(hash);
            System.out.println("circle剩余虚拟节点数目" + circle.size());
        }
    }

    public V put(K key, V v) {
        String s = key.toString();
        System.out.println("插入节点" + s);
        int hash = hash(s);
        System.out.println(s + "的hash为" + hash);
        SortedMap<Integer, VirtualNode<V>> tailMap = circle.tailMap(hash);
        VirtualNode<V> virtualNode = null;
        if (!tailMap.isEmpty()) {
            virtualNode = tailMap.get(tailMap.firstKey());
        } else {
            virtualNode = circle.get(circle.firstKey());
        }
        System.out.println(s + "被路由到节点" + virtualNode.getName());
        return virtualNode.put(hash,v);
    }

    public V get(K key) {
        String s = key.toString();
        System.out.println("获取节点" + s);
        int hash = hash(s);
        System.out.println(s + "的hash为" + hash);
        SortedMap<Integer, VirtualNode<V>> tailMap = circle.tailMap(hash);
        VirtualNode<V> virtualNode = null;
        if (!tailMap.isEmpty()) {
            virtualNode = tailMap.get(tailMap.firstKey());
        } else {
            virtualNode = circle.get(circle.firstKey());
        }
        System.out.println(s + "被路由到节点" + virtualNode.getName());
        return virtualNode.get(hash);
    }

    public V remove(K key) {
        String s = key.toString();
        System.out.println("删除节点" + s);
        int hash = hash(s);
        System.out.println(s + "的hash为" + hash);
        SortedMap<Integer, VirtualNode<V>> tailMap = circle.tailMap(hash);
        VirtualNode<V> virtualNode = null;
        if (!tailMap.isEmpty()) {
            virtualNode = tailMap.get(tailMap.firstKey());
        } else {
            virtualNode = circle.get(circle.firstKey());
        }
        System.out.println(s + "被路由到节点" + virtualNode.getName());
        return virtualNode.remove(hash);
    }

    private int hash(String str) {
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < str.length(); i++) {
            hash = (hash ^ str.charAt(i)) * p;
        }
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;

        // 如果算出来的值为负数则取其绝对值
        if (hash < 0) {
            hash = Math.abs(hash);
        }
        return hash;
    }


    public static void main(String[] args) {
        ConsistentHashStorage<String, String> storage = new ConsistentHashStorage<String, String>();
        StorageServer<String> server1 = new StorageServer<String>("192.168.0.1");
        StorageServer<String> server2 = new StorageServer<String>("192.168.0.2");
        storage.addNode(server1);
//        storage.removeNode(server1);


        for (int i = 0; i < 10000; i++) {
            storage.put(i+"_key",i+"_val");
        }

//        for (int i = 0; i < 10000; i++) {
//            System.err.println(storage.get(i+"_key"));
//        }

        storage.addNode(server2);


    }


}
