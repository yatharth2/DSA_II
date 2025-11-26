import java.util.ArrayList;

class Node{ 
    int data;
    Node left, right;

    public Node (int item) {
        data = item;
        left = right = null;
    }
}

public class InOrderBT {

    static void inOrder (Node node, ArrayList<integer> res){
        if (Node == null){
            return;
        }

        inOrder(node.left, res);

        res.add(node.data);

        inOrder(node.right, res);
    }

    public static void main(String [] args){
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        ArrayList<integer> res = new ArrayList<>;
        InOrder (root, res);

        for (int node : res )
            System.out.println(node + " ");
    }
}