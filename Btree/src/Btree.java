
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

class Node {

    public boolean isnewLeaf;
    ArrayList<Integer> Entries = new ArrayList<Integer>();
    ArrayList<Node> Children = new ArrayList<Node>();

    public Node(boolean isnewLeaf) {
        this.isnewLeaf = isnewLeaf;
    }

    public Node(int x) {
        Entries.add(x);
    }
}

public class Btree {

    public Node root;
    public static int t = 3;
    public static int tmax = 2 * t - 1;
    public static int tmin = t - 1;
    public static int h = 0;

    Btree() {
        Node x = new Node(true);
        root = x;
        h++;
    }

    public void insert(int key) {
        Node r = root;
        if (r.Entries.size() == 2 * t - 1) {
            Node s = new Node(false);
            root = s;
            s.Children.add(0, r);
            h++;
            splitChild(s, 0);
            insertNonfull(s, key);
        } else {
            insertNonfull(r, key);
        }
    }

    public void splitChild(Node x, int i) {
        Node z = new Node(true);
        Node y = x.Children.get(i);
        z.isnewLeaf = y.isnewLeaf = true;
        for (int j = 0; j < t - 1; j++) {
            z.Entries.add(j, y.Entries.get(j + t));
        }
        if (!y.isnewLeaf) {
            for (int j = 0; j < t; j++) {
                z.Children.add(j, y.Children.get(j + t));
            }
        }
        for (int j = x.Entries.size() + 1; j > i + 1; j--) {
            x.Children.add(j + 1, x.Children.get(j));
        }

        x.Children.add(i + 1, z);

        x.Entries.add(y.Entries.get(t - 1));

        for (int j = y.Entries.size() - 1; j >= t - 1; j--) {
            y.Entries.remove(j);
        }

    }

    public void insertNonfull(Node x, int k) {
        int i = x.Entries.size() - 1;
        if (x.isnewLeaf) {
            x.Entries.add(k);
            Collections.sort(x.Entries);
        } else {
            while (i >= 0 && k < x.Entries.get(i)) {
                i--;
            }
            i++;
            if (x.Children.get(i).Entries.size() == 2 * t - 1) {
                splitChild(x, i);
                if (k > x.Entries.get(i)) {
                    i++;
                }
            }
            insertNonfull(x.Children.get(i), k);
        }
    }

    public static void main(String[] args) {
        Btree btree = new Btree();

        System.out.println("t = " + t);
        System.out.println("Max. value in node = " + tmax);
        System.out.println("Min. value in node = " + tmin);
        System.out.println("\n");

        for (int i = 1; i <= 10; i++) {
            btree.insert(i);
            System.out.println("Inserting " + i);
        }

        int root_entries = tmax - btree.root.Entries.size();
        System.out.println("Number of Entries: " + btree.root.Entries.size());
        System.out.println("Number of entries that can be inserted = " + root_entries);
        System.out.println("Entries in root: ");
        for (int i = 0; i < btree.root.Entries.size(); i++) {
            System.out.println(btree.root.Entries.get(i));
        }
        System.out.println();

        System.out.println("Children size: " + btree.root.Children.size());

        int c1_entries = tmax - btree.root.Children.get(0).Entries.size();
        System.out.println("1st Child size: " + btree.root.Children.get(0).Entries.size());
        System.out.println("Number of entries that can be inserted = " + c1_entries);
        System.out.println("Entries in child: ");
        for (int i = 0; i < btree.root.Children.get(0).Entries.size(); i++) {
            System.out.println(btree.root.Children.get(0).Entries.get(i));
        }
        System.out.println();

        int c2_entries = tmax - btree.root.Children.get(1).Entries.size();
        System.out.println("2nd Child size: " + btree.root.Children.get(1).Entries.size());
        System.out.println("Number of entries that can be inserted = " + c2_entries);
        System.out.println("Entries in child:");
        for (int i = 0; i < btree.root.Children.get(1).Entries.size(); i++) {
            System.out.println(btree.root.Children.get(1).Entries.get(i));
        }

        System.out.println();

        int c3_entries = tmax - btree.root.Children.get(2).Entries.size();
        System.out.println("3rd Child size: " + btree.root.Children.get(2).Entries.size());
        System.out.println("Number of entries that can be inserted = " + c3_entries);
        System.out.println("Entries in child: ");
        for (int i = 0; i < btree.root.Children.get(2).Entries.size(); i++) {
            System.out.println(btree.root.Children.get(2).Entries.get(i));
        }

        System.out.println();
        System.out.println("Total height: " + h + "\n");

        System.out.println("5|");
        System.out.println("4|\t\t\tx");
        System.out.println("3|\t\t\tx");
        System.out.println("2|\tx\tx\tx");
        System.out.println("1|\tx\tx\tx");

        for (int j = 0; j < 30; j++) {
            System.out.print("-");
        }
        System.out.println("");

        int ar[] = {1, 2, 3, 4, 5};
        for (int k = 0; k < 3; k++) {
            System.out.print("\t");
            System.out.print(+ar[k]);
        }
        System.out.println("");
        System.out.println("X-axis = No. of children");
        System.out.println("Y-axis = No. of elements in each child node");
    }

}
