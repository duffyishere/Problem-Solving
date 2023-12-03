package org.duffy.leet_code;

import java.util.Stack;

public class MinStack {
    private Stack<Node> stack;

    public MinStack() {
        this.stack = new Stack<>();
    }

    public void push(int val) {
        Node node;
        if (stack.isEmpty()) {
            node = new Node(val, val);
        } else {
            node = new Node(val, stack.peek().minValue);
        }
        stack.push(node);
    }

    public int top() {
        return stack.peek().value;
    }

    public int pop() {
        return stack.pop().value;
    }

    public int getMin() {
        return stack.peek().minValue;
    }

    class Node {
        int value;
        int minValue;

        public Node(int value, int prevMin) {
            this.value = value;
            minValue = Math.min(prevMin, value);
        }
    }
}
