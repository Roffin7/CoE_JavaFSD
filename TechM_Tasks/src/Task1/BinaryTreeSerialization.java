package Task1;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Arrays;

class TreeNode {
    int val;
    TreeNode left, right;
    
    TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}

public class BinaryTreeSerialization {
    private static final String NULL_MARKER = "#";
    private static final String DELIMITER = ",";

    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializeHelper(root, sb);
        return sb.toString();
    }

    private void serializeHelper(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append(NULL_MARKER).append(DELIMITER);
            return;
        }
        sb.append(node.val).append(DELIMITER);
        serializeHelper(node.left, sb);
        serializeHelper(node.right, sb);
    }

    public TreeNode deserialize(String data) {
        Queue<String> nodes = new LinkedList<>(Arrays.asList(data.split(DELIMITER)));
        return deserializeHelper(nodes);
    }

    private TreeNode deserializeHelper(Queue<String> nodes) {
        if (nodes.isEmpty()) return null;
        
        String val = nodes.poll();
        if (val.equals(NULL_MARKER)) return null;
        
        TreeNode node = new TreeNode(Integer.parseInt(val));
        node.left = deserializeHelper(nodes);
        node.right = deserializeHelper(nodes);
        return node;
    }

    public void preorder(TreeNode root) {
        if (root == null) {
            System.out.print("# ");
            return;
        }
        System.out.print(root.val + " ");
        preorder(root.left);
        preorder(root.right);
    }

    public static void main(String[] args) {
        BinaryTreeSerialization tree = new BinaryTreeSerialization();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        String serializedTree = tree.serialize(root);
        System.out.println("Serialized: " + serializedTree);

        TreeNode deserializedRoot = tree.deserialize(serializedTree);
        System.out.print("Preorder of Deserialized Tree: ");
        tree.preorder(deserializedRoot);
    }
}
