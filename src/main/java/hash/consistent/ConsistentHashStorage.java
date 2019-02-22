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
            Integer key = circle.tailMap(hash).firstKey();
            VirtualNode<V> virtualNode = null;
            if (key == null) {
                virtualNode = circle.get(circle.firstKey());
            } else {
                virtualNode = circle.get(key);
            }
            VirtualNode<V> newVirtualNode = new VirtualNode<V>(hash, newServer, newServer.getIp() + "#VN" + (i + 1));
            if (virtualNode == null) {
                System.out.println("第一次添加StorageServer,无需转移数据");
            } else if (virtualNode.getIp().equals(newServer.getIp())) {
                System.out.println("同StorageServer,无需转移数据");
            } else {
                //转移数据
                virtualNode.transferPartIOfDataToOther(newVirtualNode);
            }
            circle.put(hash, newVirtualNode);
        }
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
}
