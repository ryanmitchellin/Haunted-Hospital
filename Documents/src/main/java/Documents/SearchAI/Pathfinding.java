package Documents.SearchAI;

import Documents.main.GamePanel;
import java.util.ArrayList;

public class Pathfinding {
    GamePanel gp;
    Node[][] node;
    ArrayList<Node> openList = new ArrayList<Node>();
    public ArrayList<Node> pathList = new ArrayList<Node>();
    Node startNode, goalNode, currentNode;
    boolean goalReached = false;
    int step = 0;

    public Pathfinding(GamePanel gp) {
        this.gp = gp;
    }
    public void instantiateNodes() {
        node = new Node[gp.maxWCol][gp.maxWRow];

        int column = 0;
        int row = 0;

        while(column < gp.maxWCol && row < gp.maxWRow) {
            node[column][row] = new Node(column, row);

            column++;
            if (column == gp.maxWCol) {
                column = 0;
                row++;
            }
        }
    }
    public void resetNodes() {
        int column = 0;
        int row = 0;

        while (column < gp.maxWCol && row < gp.maxWRow) {

            // Reset open, checked and solid state
            node[column][row].open = false;
            node[column][row].checked = false;
            node[column][row].solid = false;

            column++;
            if (column == gp.maxWCol) {
                column = 0;
                row++;
            }
        }

        // Reset settings
        openList.clear();
        pathList.clear();
        goalReached = false;
        step = 0;
    }
    public void setNode(int startCol, int startRow, int goalCol, int goalRow, Entity entity) {
        resetNodes();

        // Set Start and Goal node
        startNode = node[startCol][startRow];
        currentNode = startNode;
        goalNode = node[goalCol][goalRow];
        openList.add(currentNode);

        int column = 0;
        int row = 0;

        while (column < gp.maxWCol && row < gp.maxWRow) {

            // Set Solid NOde
            // Check tiles
            int tileNum = gp.tileM.mapTileNum[gp.currentMap][column][row];
            if (gp.tileMapNum.tile[tileNum].collision == true) {
                node[column][row].solid = true;
            }

            // Set Cost
            getCost(node[column][row]);

            column++;
            if (column == gp.maxWCol) {
                column = 0;
                row++;
            }
        }
    }

    public void getCost(Node node) {

        //G cost
        int xDist = Math.abs(node.column - startNode.column);
        int yDist = Math.abs(node.row - startNode.row);
        node.gCost = xDist = yDist;
        // H Cost
        xDist = Math.abs(node.column - goalNode.column);
        yDist = Math.abs(node.row - goalNode.row);
        node.hCost = xDist + yDist;
        // F cost
        node.fCost = node.gCost + node.hCost;
    }

    public boo


}