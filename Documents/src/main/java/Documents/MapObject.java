package Documents;



public abstract class MapObject {
    /**
     * Private variable declaration for the MapObject abstract class
     *      xPos for holding the x position
     *      yPos for holding the y position
     *      charType for ??
     */
    private int xPos;
    private int yPos;
    private int charType;

    MapObject(){

    }
    MapObject(int x, int y){
        xPos = x;
        yPos = y;
    }

    /**
     * Getters for the positions of objects?
     *
     *
     * Getter for xPos
     */
    public int getX() {
        return xPos;
    }

    /**
     * Getter for yPos
     */
    public int getY() {
        return yPos;
    }

    /**
     * Getter for charType
     */
    public int getCharType() {
        return charType;
    }

    /**
     * Setters for the positions of objects?
     *
     *
     * Setter for xPos
     */
    public void setX(int xPos) {
        this.xPos = xPos;
    }

    /**
     * Setter for yPos
     */
    public void setY(int yPos) {
        this.yPos = yPos;
    }

    /**
     *  toString provides a string statement of all the attributes of the object.
     *  Can be used for error checking?
     */
    @Override
    public String toString() {
        return "MapObject{" + "xPos=" + xPos + ", yPos=" + yPos + ", charType=" + charType + '}';
    }


}