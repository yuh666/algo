package ac;

import java.util.*;

/**
 * @author yuh
 * @date 2019-03-28 09:54
 **/
public class ACTrie {


    private class Result {
        String after;
        Set<String> sensitiveWords;

        public Result(String after, Set<String> sensitiveWords) {
            this.after = after;
            this.sensitiveWords = sensitiveWords;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Result{");
            sb.append("after='").append(after).append('\'');
            sb.append(", sensitiveWords=").append(sensitiveWords);
            sb.append('}');
            return sb.toString();
        }
    }

    private class ACNode {
        char data;
        Map<Character, ACNode> nodes = new TreeMap<>();
        ACNode fail;
        boolean isEnd;
        int length;

        public ACNode(char data) {
            this.data = data;
        }

        ACNode get(char data) {
            return nodes.get(data);
        }


        ACNode put(char data, ACNode node) {
            return nodes.put(data, node);
        }

    }

    private ACNode root;

    public ACTrie() {
        root = new ACNode('\0');
        root.isEnd = false;
    }

    public void add(String word) {
        ACNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            ACNode node = curr.get(c);
            if (node == null) {
                node = new ACNode(c);
                curr.put(c, node);
            }
            if (i == word.length() - 1) {
                node.isEnd = true;
                node.length = word.length();
            }
            curr = node;
        }
    }


    public void init() {
        LinkedList<ACNode> list = new LinkedList<>();
        root.fail = null;
        list.add(root);
        while (!list.isEmpty()) {
            ACNode acNode = list.removeFirst();
            Map<Character, ACNode> nodes = acNode.nodes;
            for (Map.Entry<Character, ACNode> entry : nodes.entrySet()) {
                ACNode node = entry.getValue();
                char key = entry.getKey();
                if (acNode == root) {
                    node.fail = root;
                } else {
                    ACNode fail = acNode.fail;
                    while (fail != null) {
                        if (fail.get(key) != null) {
                            fail = fail.get(key);
                            break;
                        }
                        fail = fail.fail;
                    }
                    node.fail = fail == null ? root : fail;
                }
                list.add(node);
            }
        }
    }

    public Result matchAll(String word, boolean all) {
        Set<String> list = new HashSet<>();
        ArrayList<Integer> index = new ArrayList<>();
        Set<Integer> inUse = new HashSet<>();
        ArrayList<Integer> stop = new ArrayList<>();
        _matchAll(word, list, index, stop, all,inUse);
        char[] chars = word.toCharArray();
        for (Integer i : stop) {
            chars[i] = '*';
        }
        for (Integer i : inUse) {
            chars[i] = '*';
        }
        return new Result(new String(chars), list);
    }

    private void _matchAll(String word, Set<String> words, List<Integer> indexes, List<Integer> stop, boolean all,Set<Integer> inUseIndex) {
        ACNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            while (curr.get(c) == null && curr != root) {
                curr = curr.fail;
            }
            curr = curr.get(c);
            if (curr == null) {
                curr = root;
            } else {
                indexes.add(i);
            }
            ACNode temp = curr;
            while (temp != root) {
                if (temp.isEnd) {
                    addSWord(indexes, word, words, temp.length,inUseIndex);
                    if (!all) {
                        break;
                    }
                }
                temp = temp.fail;
            }
        }
    }

    public void addStopWord(String stopWord){

    }

    private void addSWord(List<Integer> indexes, String word, Set<String> words, int length, Set<Integer> inUseIndex) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = indexes.size() - length; i < indexes.size(); i++) {
            Integer index = indexes.get(i);
            inUseIndex.add(index);
            stringBuilder.append(word.charAt(index));
        }
        words.add(stringBuilder.toString());
    }


    public static void main(String[] args) {
        ACTrie acTrie = new ACTrie();
        acTrie.add("abc");
        acTrie.add("abd");
        acTrie.add("bd");
        acTrie.init();
        Result abcbd = acTrie.matchAll("facfabdsdf", false);
        System.out.println(abcbd);
    }

}
