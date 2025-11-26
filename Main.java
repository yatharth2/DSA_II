class Node{
    int key; 
    Node left, right;

    public Node(int item){
        key = item;
        left = right = null;
    }
}

public class Main {
    public static void main(String[]args) {
        Node n1 = new Node (2);
        Node n2 = new Node (3);
        Node n3 = new Node (4);
        Node n4 = new Node (5);
        
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
    }
}