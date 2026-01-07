public class RecursiveFunc {

    static int[] a = {1,2,3,4,5,6,7,8,9,10};
    static int N = a.length;

    // print in the middle
    static void visit1(int i) {
        if (i >= N) return;
        visit1(2*i + 1);
        System.out.print(a[i]);
        visit1(2*i + 2);
    }

    // print first
    static void visit2(int i) {
        if (i >= N) return;
        System.out.print(a[i]);
        visit2(2*i + 1);
        visit2(2*i + 2);
    }

    // print last
    static void visit3(int i) {
        if (i >= N) return;
        visit3(2*i + 1);
        visit3(2*i + 2);
        System.out.print(a[i]);
    }

    public static void main(String[] args) {
        System.out.print("visit1: ");
        visit1(0);

        System.out.print("visit2: ");
        visit2(0);

        System.out.print("visit3: ");
        visit3(0);
    }
}
