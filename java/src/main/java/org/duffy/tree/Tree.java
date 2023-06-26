package org.duffy.tree;

public class Tree {

    class Node {
        int left;
        int right;

        int depth;
        int order;

        public Node(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    Node[] nodes = new Node[10001];
    int[] counts = new int[10001];
    public void pr2250(int n, Node[] nodes, int[] cnt) {
        this.nodes = nodes;
        this.counts = cnt;
        int[] left = new int[10001];
        int[] right = new int[10001];

        int root = 0;
        for (int i = 1; i < n + 1; i++) {
            if (cnt[i] == 0)
                root = i;
        }

        inorder(root, 1);

        int maxDepth = 0;
        for (int i = 1; i < n + 1; i++) {
            int depth = nodes[i].depth;
            int order = nodes[i].order;

            if (left[depth] == 0)
                left[depth] = order;
            else
                left[depth] = Math.min(left[depth], order);

            right[depth] = Math.max(right[depth], order);
            maxDepth = Math.max(maxDepth, depth);
        }

        int ret = 0;
        int retDepth = 0;
        for (int i = 1; i < maxDepth + 1; i++) {
            if (ret < right[i] - left[i] + 1) {
                ret = right[i] - left[i] + 1;
                retDepth = i;
            }
        }

        System.out.println(retDepth + " " + ret);
    }

    int order = 0;
    public void inorder(int node, int depth) {
        if (node == -1) return;
        inorder(nodes[node].left, depth+1);
        nodes[node].order = ++order;
        nodes[node].depth = depth;
        inorder(nodes[node].right, depth+1);
    }
}
