package zxc.rpc.zxcrpcremote.start;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class BSTTest {

    public static void main(String[] args) {
        // 创建有序数组并构建初始平衡BST
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(3);
        integers.add(2);
        integers.add(4);
        integers.add(5);
        integers.add(6);
        integers.add(7);
        integers.sort(Integer::compareTo); // 排序后为 [1, 2, 3, 4, 5, 6, 7]

        TreeNode root = sortedArrayToBST(integers, 0, integers.size() - 1);

        // 插入新节点（例如插入值8）
        root = insert(root, 8);

        // 验证插入后的中序遍历结果
        System.out.print("插入后中序遍历结果: ");
        inOrder(root); // 输出: 1 2 3 4 5 6 7 8


    }

    // 递归构建平衡BST（初始树）
    private static TreeNode sortedArrayToBST(List<Integer> list, int start, int end) {
        if (start > end) return null;
        int mid = start + (end - start) / 2;
        TreeNode node = new TreeNode(list.get(mid));
        node.left = sortedArrayToBST(list, start, mid - 1);
        node.right = sortedArrayToBST(list, mid + 1, end);
        return node;
    }

    // 递归插入新节点（不保证平衡）
    private static TreeNode insert(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val); // 找到插入位置，创建新节点
        }
        if (val < root.val) {
            root.left = insert(root.left, val); // 递归左子树插入
        } else if (val > root.val) {
            root.right = insert(root.right, val); // 递归右子树插入
        }
        // 如果 val == root.val，可根据需求选择不插入或处理重复值
        return root; // 返回更新后的节点
    }

    // 中序遍历验证树结构
    private static void inOrder(TreeNode node) {
        if (node == null) return;
        inOrder(node.left);
        System.out.print(node.val + " ");
        inOrder(node.right);
    }


    static class TreeNode {
        int val;
        int height; // 树高度
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
            this.height = 1; // 初始高度 = 1
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 获取节点高度
     * @param node
     * @return
     */
    private int getHeight(TreeNode node) {
        return (node == null) ? 0 : node.height;
    }

    /**
     * 计算节点的平衡因子（左子树高-右子树高）
     * @param node
     * @return
     */
    private int getBalanceFactory(TreeNode node) {
        return (node ==  null) ? 0 : getHeight(node.left) - getHeight(node.right);
    }

    /**
     * 更新节点高度
     * @param node
     */
    private void  updateHeight(TreeNode node) {
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    }

    /**
     * 右旋，处理左左失衡
     * @param y
     * @return
     */
    private TreeNode rightRotate(TreeNode y)  {
        TreeNode x = y.left;
        TreeNode T3 = x.right;
        // 旋转
        x.right = y;
        y.left = T3;

        // 更新高度（先更新子节点y，再更新父节点x）
        updateHeight(y);
        updateHeight(x);
        return x;
    }

    private TreeNode leftRotate(TreeNode x) {
        TreeNode y = x.right;
        TreeNode T2 = x.left;
        // 旋转
        y.left = x;
        x.right = T2;
        updateHeight(x);
        updateHeight(y);
        return y;
    }

}
