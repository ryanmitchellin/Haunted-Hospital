package Documents;


public class EnemyGhost extends DynamicCharacter {


    public EnemyGhost(int x, int y, double movementSpeed) {
        super(x, y, movementSpeed, new Room());
    }

    @Override
    public void move(int dx, int dy, Board board) {
        // Implement enemy ghost movement using AI path finding
        
        MapObject temp = super.getPrevTile();

        int tempX = getX();
        int tempY = getY();
        setX((int) (getX()+ dx * movementSpeed));
        setY((int) (getY()+ dy * movementSpeed));
        if(checkCollision((board.board[getX()][getY()]))){
            tilePrev = new Room();
        }
        else{
            tilePrev = board.board[getX()][getY()];
        }
        this.tilePrev = board.board[getX()][getY()];
        board.board[getX()][getY()]=this;
        
        board.board[tempX][tempY]=temp;

    }

    public void calculateMove(Board board){

        double distance = Math.abs(getX()-board.mainCharacter.getX())+Math.abs(getY()-board.mainCharacter.getX());
        if(distance>Math.abs(getX()+1-board.mainCharacter.getX())+Math.abs(getY()-board.mainCharacter.getX())){
            if(!(board.board[getX()+1][getY()] instanceof Wall)){
                move(1, 0, board);
                
            }
        }
        else if(distance>Math.abs(getX()-1-board.mainCharacter.getX())+Math.abs(getY()-board.mainCharacter.getX())){
            if(!(board.board[getX()+1][getY()] instanceof Wall)){
                move(-1, 0, board);
                
            }
        }
        else if(distance>Math.abs(getX()-board.mainCharacter.getX())+Math.abs(getY()+1-board.mainCharacter.getX())){
            if(!(board.board[getX()][getY()+1] instanceof Wall)){
                move(0, 1, board);
                
            }
        }
        else if(distance>Math.abs(getX()-board.mainCharacter.getX())+Math.abs(getY()-1-board.mainCharacter.getX())){
            if(!(board.board[getX()][getY()-1] instanceof Wall)){
                move(0, -1, board);
                
            }
        }
    }
    @Override
    public boolean checkCollision(MapObject other) {
        // Implement collision detection for enemy ghosts
        if (other instanceof MainCharacter) {
            return true;
        }
        return false;
    }

}