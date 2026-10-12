package com.hot100.support;

import com.hot100.common.ListNode;
import com.hot100.common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/** Small builders used only by tests. */
public final class TestNodes {

    private TestNodes() {
    }

    public static ListNode list(int... values) {
        ListNode dummy = new ListNode();
        ListNode tail = dummy;
        for (int value : values) {
            tail.next = new ListNode(value);
            tail = tail.next;
        }
        return dummy.next;
    }

    public static int[] toArray(ListNode head) {
        int[] buffer = new int[16];
        int size = 0;
        for (ListNode node = head; node != null; node = node.next) {
            if (size == buffer.length) {
                buffer = Arrays.copyOf(buffer, buffer.length * 2);
            }
            buffer[size++] = node.val;
        }
        return Arrays.copyOf(buffer, size);
    }

    /** Builds a binary tree from level-order values; null means a missing child. */
    public static TreeNode tree(Integer... values) {
        if (values.length == 0 || values[0] == null) {
            return null;
        }

        TreeNode root = new TreeNode(values[0]);
        Deque<TreeNode> parents = new ArrayDeque<>();
        parents.add(root);
        int index = 1;

        while (!parents.isEmpty() && index < values.length) {
            TreeNode parent = parents.remove();

            Integer leftValue = values[index++];
            if (leftValue != null) {
                parent.left = new TreeNode(leftValue);
                parents.add(parent.left);
            }

            if (index < values.length) {
                Integer rightValue = values[index++];
                if (rightValue != null) {
                    parent.right = new TreeNode(rightValue);
                    parents.add(parent.right);
                }
            }
        }

        return root;
    }

    /** Converts a tree to level order and removes unneeded trailing nulls. */
    public static List<Integer> toLevelOrder(TreeNode root) {
        if (root == null) {
            return List.of();
        }

        List<Integer> values = new ArrayList<>();
        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();
            if (node == null) {
                values.add(null);
                continue;
            }
            values.add(node.val);
            queue.add(node.left);
            queue.add(node.right);
        }

        int last = values.size() - 1;
        while (last >= 0 && values.get(last) == null) {
            last--;
        }
        return new ArrayList<>(values.subList(0, last + 1));
    }
}

