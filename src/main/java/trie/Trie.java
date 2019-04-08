package trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Trie {

    class Node {
        char data;
        boolean isEnd;
        Map<Character, Node> nodes;

        public Node(char data) {
            this.data = data;
            isEnd = false;
            nodes = new HashMap<>();
        }

        public Node get(char data) {
            return nodes.get(data);
        }

        public void put(char data, Node node) {
            nodes.put(data, node);
        }
    }

    private Node root = new Node('\0');

    public void add(String word) {
        Node curr = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            Node node = curr.get(c);
            if (node == null) {
                node = new Node(c);
                curr.put(c, node);
            }
            if (i == word.length() - 1) {
                node.isEnd = true;
            }
            curr = node;
        }
    }


    public boolean startWith(String prefix) {
        Node curr = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            Node node = curr.get(c);
            if (node == null) {
                return false;
            }
            curr = node;
        }
        return true;
    }



    public List<String> allStartWith(String prefix) {
        Node curr = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            Node node = curr.get(c);
            if (node == null) {
                return new ArrayList<>();
            }
            curr = node;
        }
        ArrayList<String> list = new ArrayList<>();
        collect(list,curr,prefix);
        return list;
    }

    private void collect(ArrayList<String> list, Node curr, String prefix) {
        if(curr.isEnd){
            list.add(prefix);
        }
        Map<Character, Node> nodes = curr.nodes;
        if(nodes == null || nodes.isEmpty()){
            return;
        }
        for (Map.Entry<Character, Node> characterNodeEntry : nodes.entrySet()) {
            collect(list,characterNodeEntry.getValue(),prefix+characterNodeEntry.getValue().data);
        }
    }


    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.add("abc");
        trie.add("abd");
        trie.add("abs");
        trie.add("acs");
        System.out.println(trie.allStartWith("ac`"));
    }












}
