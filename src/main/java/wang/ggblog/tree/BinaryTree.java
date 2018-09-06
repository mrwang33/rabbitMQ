package wang.ggblog.tree;

import java.util.HashSet;
import java.util.Optional;
import java.util.Random;

/**
 * @author wang1
 * @date 2018/9/3
 */
public class BinaryTree {
    public static void main(String[] args) {
        Node<Integer> integerNode = new Node<Integer>(5);
        HashSet<Integer> integers = new HashSet<Integer>();
        for (int i = 1; i < 50000; i++) {
            integers.add(i);
            integerNode.addData(i, integerNode);
        }
        System.out.println(integers.size());
        long l = System.currentTimeMillis();
        Node nodeData = integerNode.getNodeData(49999, integerNode);
        System.out.println("get data success: " + nodeData.getData());
        long end = System.currentTimeMillis();
        long l1 = end - l;
        System.out.println("查找数据成功！共耗时: " + l1);

        // set 寻找值
        long l2 = System.currentTimeMillis();
        Optional<Integer> first = integers.stream().filter(s -> s.equals(49999)).findFirst();
        System.out.println(first.orElse(null));
        long l3 = System.currentTimeMillis();
        long end1 = l3 - l2;
        System.out.println("set 查找數據成功！共耗時: " + end1);
    }
}
