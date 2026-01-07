import java.util.Scanner;

public class SearchFunc {

    static int[] a = {1,2,3,4,5,6,7,8,9,10};
    static int n = a.length;

    // this function prints in the middle of the recursion flow
    // just following the same pattern everywhere (2*i+1) and (2*i+2)
    static void visit1(int i) {
        if (i >= n) return; // outside array means stop

        visit1(2*i + 1); // going to the first possible next position
        System.out.print(a[i] + " "); // printing the value in the middle
        visit1(2*i + 2); // going to the second possible next position
    }

    // this function performs the search
    // same pattern as o/p-1 but this time i check for the key
    static int search(int i, int key) {
        if (i >= n) return -1; // no more valid index here

        if (a[i] == key) return i; // found the key so return the index instantly

        int check1 = search(2*i + 1, key); // trying next spot 1
        if (check1 != -1) return check1; // if found i stop here

        return search(2*i + 2, key); // if not found i try next spot 2
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("enter number to search: "); 
        int key = sc.nextInt();

        int result = search(0, key); // starting the search from index 0

        if(result != -1) {
            System.out.println("found at index: " + result);
        } else {
            System.out.println("not found");
        }
    }
}
