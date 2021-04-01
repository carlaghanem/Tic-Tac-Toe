
import java.util.ArrayList;
import java.util.Scanner;

public class TicTacToeBruteForce {
    private static String validCols="abc";
    private static String validLetters="xo";
    public static void main(String[] args) {
        TicTacToe TTT=new TicTacToe();
        String str;//user's input
        int[][] board;
        char humanLetter='-',AILetter='-'; //letters x or o for every player
        int rowAI,colAI_Int,rowInd;
        String AIMove=""; //AI's move updated after every turn
        char colAI;
        char humanCol='-';int humanRow=0;int humanCol_int=0;
        ArrayList<Character> humanL=new ArrayList<>();//to catch the symbol of human
        boolean play=true,valid=true;
        Scanner scan=new Scanner(System.in);

        //Introduction to the game
        System.out.println("Welcome to TicTacToe!"+"\nTo play this game, you will have to enter your move in the format of: letter digit X/O (eg: a1 X). "+
                "\nNote that you won't be able to change the input after you enter it.");

        //The loop of the game running until someone wins or there's a draw
        do {
            System.out.println("Enter your move: letter digit X/O"+"\n(\"new\" for a new game and \"quit\" to quit the app)");
            str=scan.nextLine();
            str=str.toLowerCase();
            while(!isValid(str)) {
                System.out.println("Please try again.");
                str=scan.nextLine().toLowerCase();
            }

            //End the game if user enters quit
            if(str.equals("quit")) {System.out.println("Game ended. Thank you for playing!");
                break; }
            else
                //begin a new game and restore the board if the player enters new
                if(str.equals("new")) {
                    TTT.clearBoard();
                    System.out.println("New game has started");
                }
                else
                    //print the current board if user enters dump
                    if(str.equalsIgnoreCase("dump")) { //print the board
                        board=TTT.getBoard();
                        printBoard(board,humanL.get(0),AILetter);
                    }

                    //Analyze the move
                    else {
                        humanCol=str.charAt(0);
                        humanRow=Integer.parseInt(str.charAt(1)+"");
                        humanLetter=str.charAt(3);

                        //fixing the letter of the human
                        humanL.add(humanLetter);
                        //notify the user in case they change their input letter
                        if(humanLetter!=humanL.get(0)) {
                            System.out.println("Your letter is "+humanL.get(0));
                        }
                        //assigning the letter of the AI (the other option than human's letter)
                        if(humanL.get(0)=='x') AILetter='o';
                        else AILetter='x';
                        //realizing what the human's move is in order to play the move
                        //by assigning indexes to the input row and column
                        switch(humanCol) {
                            case 'a': humanCol_int=0;break;
                            case 'b': humanCol_int=1;break;
                            case 'c':humanCol_int=2;
                        }
                        switch(humanRow) {
                            case 1: humanRow=2;break;
                            case 2: humanRow=1;break;
                            case 3: humanRow=0;
                        }
                        //playing the human's move
                        play=TTT.playMove(TTT.HUMAN, humanRow, humanCol_int);

                        //if there is no draw and no one wins, we can generate a move for the computer
                        if(!TTT.boardIsFull()&&!TTT.isAWin(TTT.COMPUTER)&&!TTT.isAWin(TTT.HUMAN)&&play) {
                            do {
                                //generate a random column and row for the computer
                                //then assign the appropriate naming for both for printing the move
                                rowAI=(int)(Math.random()*3);
                                colAI_Int=(int)(Math.random()*3);
                                switch(colAI_Int) {
                                    case 0: colAI='a';break;
                                    case 1: colAI='b';break;
                                    case 2:colAI='c';break;
                                    default: colAI='-';
                                }
                                switch(rowAI) {
                                    case 0: rowInd=3;break;
                                    case 1: rowInd=2;break;
                                    case 2: rowInd=1;break;
                                    default: rowInd=0;
                                }
                                //play the computer's move
                                valid=TTT.playMove(TTT.COMPUTER, rowAI, colAI_Int);

                            }while(!valid); //abort one move

                            //print the move of the computer
                            AIMove+=""+colAI+rowInd+" "+AILetter+" ";
                            System.out.println("AI Move: "+AIMove);
                            //re-initialize the String for the move
                            AIMove="";
                            board=TTT.getBoard();
                            //print board after each move
                            printBoard(board, humanL.get(0), AILetter);}

                    }

        }while(!TTT.boardIsFull()&&!TTT.isAWin(TTT.COMPUTER)&&!TTT.isAWin(TTT.HUMAN));

        //Printing the results of the game to the screen
        if(TTT.boardIsFull()) System.out.println("Draw!");
        else if(TTT.isAWin(TTT.COMPUTER)) System.out.println("Computer Won! Better Luck Next Game.");
        else if(TTT.isAWin(TTT.HUMAN)) System.out.println("You won! Good job.");
        scan.close();
    }

    //method for checking the input validity of the user
    private static boolean isValid(String str) {
        boolean valid=true;
        if(str.equals("quit")||str.equals("new")||str.equals("dump"))
            return true;
            //checking the length so that there are no extra or less characters
        else if(str.length()!=4)
            return false;
        else {
            //checking the content of this input
            char col=str.charAt(0);
            int row=Integer.parseInt(str.charAt(1)+"");
            char letter=str.toLowerCase().charAt(3);
            if(validCols.indexOf(col)==-1) {
                System.out.println("Column not available. (abc)");
                return false;
            }
            if(row<1||row>3) {
                System.out.println("Row not available. (123)");
                return false;
            }
            if(validLetters.indexOf(letter)==-1) {
                System.out.println("Invalid letter");
                return false;
            }if(str.charAt(2)!=' ') {
                System.out.println("Invalid input");
                return false;
            }

        }
        return valid;
    }

    //method for printing the board
    private static void printBoard(int[][] board,char humanLetter,char AILetter) {
        for(int i=0;i<board.length;i++) {
            for(int j=0;j<board[0].length;j++) {
                if(board[i][j]==0) System.out.print(humanLetter+" ");
                else if(board[i][j]==1) System.out.print(AILetter+" ");
                else if(board[i][j]==2) System.out.print("- ");
            }
            System.out.println();
        }
    }




















}
