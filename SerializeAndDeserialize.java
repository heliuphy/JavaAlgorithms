import java.util.LinkedList;
import java.util.Queue;

public class SerializeAndDeserialize {
    /**
     * This method will be invoked first, you should design your own algorithm to
     * serialize a binary tree which denote by a root node to a string which can be
     * easily deserialized by your own "deserialize" method later.
     */
    public String serialize(TreeNode root) {
        // write your code here
        if (root == null) {
            return "{}";
        }

        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> q = new LinkedList<>();

        q.offer(root);
        sb.append("{");
        sb.append(Integer.toString(root.val));

        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            if (node.left != null) {
                sb.append(",");
                sb.append(Integer.toString(node.left.val));
                q.offer(node.left);
            } else if (node.left == null) {
                sb.append(",#");
            }

            if (node.right != null) {
                sb.append(",");
                sb.append(Integer.toString(node.right.val));
                q.offer(node.right);
            } else if (node.right == null) {
                sb.append(",#");
            }

            if (q.isEmpty()) {
                sb.append("}");
            }
        }

        // for (int i = sb.length() - 2; i >= 0; i--) {
        //     if (sb.charAt(i) == '#') {
        //         sb.delete(i - 1, i + 1);
        //     }
        //     else {
        //         break;
        //     }
        // }
        int flag = sb.length() - 2;
        while(flag >= 0) {
            if (sb.charAt(flag) == '#') {
                flag -= 2;
            }
            else {
                break;
            }
        }
        sb.delete(flag + 1, sb.length() - 1);

        

        return sb.toString();
    }

    /**
     * This method will be invoked second, the argument data is what exactly you
     * serialized at method "serialize", that means the data is not given by system,
     * it's given by your own serialize method. So the format of data is designed by
     * yourself, and deserialize it here as you serialize it in "serialize" method.
     */
    public TreeNode deserialize(String data) {
        // write your code here
        if (data.equals("{}")) {
            return null;
        }

        String[] vals = data.substring(1, data.length() - 1).split(",");

        Queue<TreeNode> q = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(vals[0]));
        q.offer(root);

        boolean isLeftChild = true;

        for (int i = 1; i < vals.length; i++) {
            if (!vals[i].equals("#")) {
                TreeNode newNode = new TreeNode(Integer.parseInt(vals[i]));
                q.offer(newNode);
                if (isLeftChild) {
                    q.peek().left = newNode;
                } else {
                    q.peek().right = newNode;
                    q.poll();
                }
                
            }
            else {
                if (!isLeftChild) {
                    q.poll();
                }
            }
            isLeftChild = !isLeftChild;
            
        }

        return root;

    }

    public static void main(String[] args) {
        SerializeAndDeserialize s = new SerializeAndDeserialize();
        String input = "{3,9,20,#,#,15,7}";
        TreeNode t = s.deserialize(input);
        String output = s.serialize(t);

        System.out.println(output);
    }
}


class TreeNode {
    public int val;
    public TreeNode left, right;

    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}