package wang.ggblog.tree;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * @author wang1
 * @date 2018/9/3
 */
public class Node<T extends Comparable<T>> {
    // 左节点
    private Node leftNode;
    // 右节点
    private Node rightNode;
    // 存储的数据
    private T data;

    public Node() {
    }

    public Node(T data) {
        this.data = data;
    }

    /**
     * 添加数据
     *
     * @return -1 异常 1正常
     */
    public int addData(T toAdd, Node rootNode) {
        int addStatus = -1;
        T data = (T) rootNode.getData();
        // 等待添加的变量大于这个节点的值
        if (toAdd.compareTo(data) > 0) {
            // 所以放在右节点
            //先检查右节点是否为空
            if (rootNode.rightNode == null) {
                rootNode.rightNode = new Node(toAdd);
            } else {

                addData(toAdd, rootNode.rightNode);
            }
        } else if (toAdd.equals(data)) {
            return -1;
        } else {
            if (rootNode.leftNode == null) {
                rootNode.leftNode = new Node(toAdd);
            } else {
                addData(toAdd, rootNode.leftNode);
            }

        }

        return 1;
    }

    public T getData() {
        return data;
    }

    public Node getNodeData(T t, Node root) {
        long l = System.currentTimeMillis();
        T data = (T) root.getData();
        if (data.equals(t)) {
            l = System.currentTimeMillis();
            System.out.println("success:"+data);
            return root;
        } else {
            if (t.compareTo(data) > 0) {
                if (root.rightNode == null) {
                    return null;
                } else {
                    return getNodeData(t, root.rightNode);
                }
            } else {
                if (root.leftNode == null) {
                    return null;
                } else {
                    return getNodeData(t, root.leftNode);
                }
            }
        }
    }

}
