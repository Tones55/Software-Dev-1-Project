import java.util.*;

public class GamePiece {

    private char symbol;
    private char backupSymbol;

    public GamePiece(){

    }

    public GamePiece(char symbol , char backupSymbol) {

        this.symbol = symbol;
        this.backupSymbol = backupSymbol;
    }

    public void createNew(char symbol , char backupSymbol){

        System.out.println("Creating a new game piece...");
        this.symbol = symbol;
        this.backupSymbol = backupSymbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public char getBackupSymbol() {
        return backupSymbol;
    }

    public void setBackupSymbol(char backupSymbol) {
        this.backupSymbol = backupSymbol;
    }
}
