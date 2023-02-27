import java.util.ArrayList;

public class GameBoard {

    private char[][] slots;
    private int xDim;
    private int yDim;

    public GameBoard(int xDim){

        this.xDim = xDim;
        this.yDim = xDim;
        slots = new char[xDim][yDim];
        fillSlots();
    }

    public void fillSlots(){
        for(int i=0; i<xDim; i++){
            for(int j=0; j<yDim; j++){
                slots[i][j] = 160;
            }
        }
    }

    public void printBoard(){

        System.out.println();
        for(int i=yDim-1; i>-1; i--){
            for(int j=0; j<xDim; j++){
                System.out.print("|| " + slots[j][i] + " ");
            }
            System.out.println("||");
            for(int j=0; j<xDim*5+2; j++){
                System.out.print("=");
            }
            System.out.println();
        }
        int c = 0;
        int column = 1;
        for(int i=0; i<xDim*5; i++){
            if(c == 3){
                System.out.print(column);
                column++;
                c = -2;
            }else{
                System.out.print(" ");
            }
            c++;
            if(column == 10)
                column = 1;
        }
        System.out.println();
    }

    public boolean checkForWin(Player player) {

        //check the rows and columns for 4 in a row
        for (int i = 0; i < xDim; i++) {
            int inRowX = 0;
            int inRowY = 0;
            for (int j = 0; j < yDim; j++) {
                if (slots[i][j] == player.getPiece().getSymbol()) {
                    inRowX++;
                    if (inRowX == 4)
                        return true;
                } else {
                    inRowX = 0;
                }
                if (slots[j][i] == player.getPiece().getSymbol()) {
                    inRowY++;
                    if (inRowY == 4)
                        return true;
                } else {
                    inRowY = 0;
                }
            }
        }
        //check direct diagonals for a win
        for(int i = 0; i < xDim - 3; i++){
            if(checkDirectAgonal(i , player))
                return true;
            if(checkIndirectAgonal(i , player))
                return true;
        }
        return false;
    }

    private boolean checkDirectAgonal(int i , Player player){

        for(int j = 0; j < yDim - 3; j++){
            if(slots[i][j] == player.getPiece().getSymbol() && slots[i][j] == slots[i+1][j+1] &&
                    slots[i][j] == slots[i+2][j+2] && slots[i][j] == slots[i+3][j+3]){
                return true;
            }
        }
        return false;
    }

    private boolean checkIndirectAgonal(int i , Player player){

        for(int j = 3; j < yDim; j++){
            if(slots[i][j] == player.getPiece().getSymbol() && slots[i][j] == slots[i+1][j-1] &&
                    slots[i][j] == slots[i+2][j-2] && slots[i][j] == slots[i+3][j-3]){
                return true;
            }
        }
        return false;
    }

    public boolean useGamePiece(Player player , int x){

        if(x < xDim && x > -1){
            int availableIndex = yDim;
            for(int i=0; i<yDim; i++) {
                if(slots[x][i] == 160) {
                    if(availableIndex > i)
                        availableIndex = i;
                }
            }
            if(availableIndex < yDim) {
                slots[x][availableIndex] = player.getPiece().getSymbol();
                return true;
            }
        }
        return false;
    }

    public int getxDim() {
        return xDim;
    }

    public int getyDim() {
        return yDim;
    }
}
