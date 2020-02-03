/**
 * Battleship grid to map shots taken by the user, is inherited by board
 * Homework 12.
 *
 *     @author Luke Batchelder
 *     @author Jessica Diehl
 */

public class grid {
    static int boardSize=10;
    static HUD readOut;
    //0 meains nothing is there and no shots have been taken 1 is a miss 2 is a ship is in the space but it has not
    //been hit 3 a hit
    protected int[][] gridValues=new int[boardSize][boardSize];
    /**
     * sets up the grid each value set to a 0 used by subclasses of grid
     *
     * @return			None
     *
     */
    grid() {
        for (int i = 0; i < boardSize; i++) {
            for (int m = 0; m < boardSize; m++) {
                gridValues[i][m] = 0;
            }
        }
    }
    /**
     * Denotes a battleship hit at the given coordinates
     * in the Space map, the player's attempted shots
     *
     * @param: sets up a version of the grid that h
     * @return None
     *
     */
    grid(HUD ourReadout) {
        readOut=ourReadout;
        for (int i = 0; i < boardSize; i++) {
            for (int m = 0; m < boardSize; m++) {
                gridValues[i][m] = 0;
            }
        }
    }
    /**
     *record the effect of a shot launched by the player and if it conected
     * @param: x x coordinate
     * @param: y y coordinate
     * @return result 0 for a miss 1 for a hit
     *
     */
    public void recordShotResults(int x, int y, int result){
    //0 means a miss 1 means a hit 2 means you have already fired there
        if (result==0) {
            gridValues[y][x]=3;
            readOut.setSpaceMiss(x,y);
            System.out.println("you missed!");
        } else {
            if(result==1){
                gridValues[y][x]=2;
            readOut.setSpaceHit(x,y);
            System.out.println("hit!");
            }
        }
    }
    /**
     * prints the grid
     *
     */
    public void printGrid(){
        for(int i=0; i<boardSize; i++){
            for(int m=0; m<boardSize; m++) {
                if (gridValues[i][m] == 0|| gridValues[i][m]==3) {
                    System.out.print("#");
                } else {
                    if (gridValues[i][m] == 1) {
                        System.out.print("O");
                    } else {
                        if (gridValues[i][m] == 2) {
                            System.out.print("X");
                        }
                    }
                }
            }
            System.out.println("");
        }
    }
}
