package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import boardlayer.BoardException;
import chesslayer.*;
import chesslayer.pieces.*;


public class Program {
    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);
        ChessMatch chessMatch = new ChessMatch();

        while(true) {
            try{
                UI.clearScreen();
                UI.printMatch(chessMatch);
                System.out.println();
                System.out.print("Source: ");
                ChessPosition source = UI.readChessPosition(sc);

                boolean[][] possibleMoves = chessMatch.possibleMoves(source);
                UI.clearScreen();
                UI.printBoard(chessMatch.getPieces(), possibleMoves);

                System.out.println();
                System.out.print("Target: ");
                ChessPosition target = UI.readChessPosition(sc);

                ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
            }
            catch(ChessException e){
                System.out.println("CE: " + e.getMessage());
                sc.nextLine();
                // Para o programa esperar o user apertar "Enter" para prosseguir.
            }
            catch(InputMismatchException e){
                System.out.println("IME: " + e.getMessage());
                sc.nextLine();
            }
            catch(BoardException e){
                System.out.println("BE: " + e.getMessage());
                sc.nextLine();
            }
        }
               
    //sc.close();
    }
}
