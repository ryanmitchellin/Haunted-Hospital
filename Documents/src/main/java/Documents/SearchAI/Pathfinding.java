package Documents.SearchAI;

import Documents.main.GamePanel;
import java.util.ArrayList;

public class Pathfinding {
    GamePanel gp;
    Node[][] node;
    ArrayList<Node> openList = new ArrayList<>();
    public ArrayList<Node> pathList = new ArrayList<>();
    Node startNode, goalNode, currentNode;
    boolean goalReached = false;
    int step = 0;

    public Pathfinding(GamePanel gp) {
        this.gp = gp;
        instantiateNodes(); //potential error #1
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
    public void setNode(int startCol, int startRow, int goalCol, int goalRow) {
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
            int tileNum = gp.tileFactory.tileMapNum[column][row];
            if (gp.tileFactory.getTile(tileNum).collision == true) {
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
        node.gCost = xDist + yDist;
        // H Cost
        xDist = Math.abs(node.column - goalNode.column);
        yDist = Math.abs(node.row - goalNode.row);
        node.hCost = xDist + yDist;
        // F cost
        node.fCost = node.gCost + node.hCost;
    }

    public boolean search() {
        while (goalReached == false && step < 500) {
            int column = currentNode.column;
            int row = currentNode.row;

            // Check the current node
            currentNode.checked = true;
            openList.remove(currentNode);

            //Open the Up node
            if (row - 1 >= 0) {
                openNode(node[column][row-1]);
            }
            //Open the left node
            if (column - 1 >= 0) {
                openNode(node[column-1][row]);
            }
            //Open the down node
            if (row + 1 >= 0) {
                openNode(node[column][row+1]);
            }
            //Open the right node
            if (column + 1 >= 0) {
                openNode(node[column+1][row]);
            }

            // Finding best node
            int bestNodeIndex = 0;
            int bestNodefCost = 999;

            for(int i = 0; i < openList.size(); i++) {

                // Check if this node's F cost is better
                if (openList.get(i).fCost < bestNodefCost) {
                    bestNodeIndex = i;
                    bestNodefCost = openList.get(i).fCost;
                }
                // IF F cost is equalm check the G cost
                else if (openList.get(i).fCost == bestNodefCost) {
                    if (openList.get(i).gCost < openList.get(bestNodeIndex).gCost) {
                        bestNodeIndex = i;
                    }
                }
            }

            // IF there is no node in openList, end loop
            if (openList.size() == 0) {
                break;
            }
            // After the loop, openList[bestNodeIndex] is the next step
            currentNode = openList.get(bestNodeIndex);

            if (currentNode == goalNode) {
                goalReached = true;
                trackPath();
            }
            step++;
        }
        return goalReached;
    }

    public void openNode(Node node) {

        if (node.open == false && node.checked == false && node.solid == false) {

            node.open = true;
            node.parent = currentNode;
            openList.add(node);
        }
    }

    public void trackPath() {
        Node current = goalNode;
        while (current != startNode) {
            pathList.add(0, current);
            current = current.parent;
        }
    }
}