import java.util.Scanner;

public class Player {

    private String name;
    private int wins;
    private final GamePiece piece;
    private int playerNum;

    public Player(String name , int playerNum , char symbol , char backupSymbol) {

        this.name = name;
        wins = 0;
        piece = new GamePiece(symbol , backupSymbol);
        this.playerNum = playerNum;
    }

    public String getName() {
        return name;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public GamePiece getPiece() {
        return piece;
    }
}
