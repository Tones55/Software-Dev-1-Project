import java.util.Scanner;

public class ConnectFour {

    private static final Scanner input = new Scanner(System.in);
    private static Player player1;
    private static Player player2;
    private static GameBoard board;

    public static void main(String[] args) {

        //Create Players 1 & 2
        //Create the GamePieces for Players 1 & 2
        String[] inputs = createPlayer(1);
        player1 = new Player(inputs[0] , 1 , inputs[1].charAt(0) , inputs[2].charAt(0));
        inputs = createPlayer(2);
        player2 = new Player(inputs[0] , 2 , inputs[1].charAt(0) , inputs[2].charAt(0));

        board = createGameBoard();
        playGame();

    }

    private static String[] createPlayer(int player) {
        //Take input for a new Player and their GamePieces
        String playerName;
        char[] symbols;

        System.out.print("Player " + player + " enter your name: ");
        playerName = input.nextLine();

        symbols = createPiece();

        System.out.println("Welcome " + playerName + "!\n");

        return new String[]{playerName, String.valueOf(symbols[0]), String.valueOf(symbols[1])};
    }

    private static char[] createPiece(){

        char[] chars = new char[2];
        System.out.print("Enter a single symbol character to represent your game piece: ");
        chars[0] = input.nextLine().charAt(0);

        System.out.print("Enter another single symbol character as a back up: ");
        chars[1] = input.nextLine().charAt(0);

        return chars;
    }

    private static GameBoard createGameBoard(){
        //create a game board

        System.out.print("How big would you like the board to be. It will be a square give the length of 1 side: ");
        int xy = input.nextInt();
        return new GameBoard(xy);
    }

    private static boolean swapSymbols(int playerTurn){

        //Use the backup symbol if there is a conflict
        if (player1.getPiece().getSymbol() == player2.getPiece().getSymbol()) {
            if (playerTurn == 1) {
                char temp = player2.getPiece().getSymbol();
                player2.getPiece().setSymbol(player2.getPiece().getBackupSymbol());
                player2.getPiece().setSymbol(temp);
            }else{
                char temp = player1.getPiece().getSymbol();
                player1.getPiece().setSymbol(player1.getPiece().getBackupSymbol());
                player1.getPiece().setSymbol(temp);
            }
            return true;
        }
        return false;
    }

    private static void playGame(){

        System.out.println("Current Standings: \n" +
                player1.getName() + ": " + player1.getWins() + "\n" +
                player2.getName() + ": " + player2.getWins());

        //Select the player to start & define how the game works
        int playerTurn = (int) (Math.random() * 2 + 1);
        Player currentPlayer;
        if(playerTurn == 1) {
            System.out.println(player1.getName() + " will go first.");
            currentPlayer = player1;
        }
        else {
            System.out.println(player2.getName() + " will go first.");
            currentPlayer = player2;
        }
        System.out.println("You will pick a column to drop your game piece into, it will then go to the bottom most available slot.\n");

        boolean swap = swapSymbols(playerTurn);

        //Start the game
        int count = 0; //counts how many slots were used on the board so the game can be ended
        while(count < board.getxDim() * board.getyDim() & !board.checkForWin(currentPlayer)){

            //let the deserving player take their turn
            if(playerTurn == 1) {
                currentPlayer = player1;
                takeTurn(currentPlayer);
                playerTurn = 2;
            } else {
                currentPlayer = player2;
                takeTurn(currentPlayer);
                playerTurn = 1;
            }
            count++;
        }
        //swap back the symbols if they were swapped earlier
        if(swap) {
            if(!(count % 2 == 0))
                if(playerTurn == 1)
                    playerTurn = 2;
                else
                    playerTurn = 1;
            swapSymbols(playerTurn);
        }
        board.printBoard();
        if(count >= board.getyDim() * board.getxDim())
            System.out.println("Due to a lack of board space remaining...");
        endGame(currentPlayer);
    }

    private static void takeTurn(Player player){
        //Take the steps for a player to take their turn

        System.out.println("You may now take your turn " + player.getName());
        board.printBoard();

        boolean available;
        do {
            System.out.print("Pick an available column: ");
            int choice = input.nextInt()-1;
            available = board.useGamePiece(player, choice);
            System.out.println();
        }while(!available);
    }

    public static void endGame(Player winner){

        //Display a message to the winner
        System.out.println("Congratulations to " + winner.getName() + "! YOU WIN!");
        winner.setWins(winner.getWins()+1);

        do {
            System.out.println("1) Would you like to play again?\n" +
                    "2) Change a players board pieces?\n" +
                    "3) Switch a player?\n" +
                    "4) Change the board size?\n" +
                    "5) Quit");

            int choice = input.nextInt();
            if (choice == 2) {
                char[] pieces;
                String trash = input.nextLine();
                System.out.print("Which player? 1 or 2: ");
                int swap = input.nextInt();
                System.out.println("Continuing to board piece creation...");
                trash = input.nextLine();
                pieces = createPiece();
                if (swap == 1)
                    player1.getPiece().createNew(pieces[0], pieces[1]);
                else
                    player2.getPiece().createNew(pieces[0], pieces[1]);

            } else if (choice == 3) {
                System.out.println("Which player do you want to switch out? 1 or 2");
                int swap = input.nextInt();
                System.out.println("Continuing to Player creation...");
                String trash = input.nextLine();
                String[] newVals = createPlayer(swap);
                if (swap == 1)
                    player1 = new Player(newVals[0], 1, newVals[1].charAt(0), newVals[2].charAt(0));
                else
                    player2 = new Player(newVals[0], 2, newVals[1].charAt(0), newVals[2].charAt(0));

            } else if (choice == 4) {
                System.out.println();
                board = createGameBoard();

            } else if (choice == 5){
                System.exit(0);
            } else {
                System.out.println("Starting new game...");
                board.fillSlots();
                playGame();
            }
        }while(true);
    }
}
