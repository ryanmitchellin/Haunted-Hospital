package Documents.SearchAI;

public class Node {
    Node parent; // would this be Node head?
    public int column;
    public int row;
    int gCost;
    int hCost;
    int fCost;
    boolean solid;
    boolean open;
    boolean checked;

    public Node(int column, int row) {
        this.column = column;
        this.row = row;
    }
}