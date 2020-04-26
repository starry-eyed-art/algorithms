package com.starry.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Description 给定一个二叉树，返回它的中序 遍历。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,null,2,3]
 * 1
 * \
 * 2
 * /
 * 3
 * <p>
 * 输出: [1,3,2]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-inorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Auther: https://github.com/starry-eyed-art
 * @Date: 2020/4/26 9:43 上午
 */
public class NO94 {

    public List<Integer> inorderTraversal2(TreeNode root) {
        ArrayList<Integer> resList = new ArrayList<>();
        //inorderTraversal(root, resList)
        cycle(root, resList);
        return resList;
    }

    // 非递归实现
    private void cycle(TreeNode root, ArrayList<Integer> resList) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode peek = stack.peek();
            if (peek.left != null) {
                stack.add(peek.left);
            } else if (peek.left == null && peek.right == null) {
                resList.add(stack.pop().val);
                if (!stack.isEmpty()) {
                    stack.peek().left = null;
                }
            } else {
                TreeNode right = peek.right;
                TreeNode bottom = stack.pop();
                resList.add(bottom.val);
                if (right != null) {
                    stack.push(right);
                }
            }
        }
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> resList = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            TreeNode pop = stack.pop();
            resList.add(pop.val);

            // 可以直接写为 cur = pop.right，这样写是为了方便理解
            if (pop.right != null) {
                cur = pop.right;
            } else {
                cur = null;
            }
        }

        return resList;
    }

    public void main(String[] args) {
        TreeNode treeNode = new TreeNode(100);
        TreeNode treeNode2 = new TreeNode(80);
        TreeNode treeNode3 = new TreeNode(120);
        treeNode.left = treeNode2;
        treeNode.right = treeNode3;
        System.out.println(inorderTraversal(treeNode));
    }

    // 递归方式实现
    private void inorderTraversal(TreeNode node, ArrayList<Integer> resList) {
        if (node == null) {
            return;
        }
        inorderTraversal(node.left, resList);
        resList.add(node.val);
        inorderTraversal(node.right, resList);
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}

















