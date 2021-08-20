package application;

import chesslayer.ChessPiece;

public class UI {

    // Exibição do tabuleiro e suas peças/espaços.
    public static void printBoard(ChessPiece[][] pieces) {
        for (int i = 0; i < pieces.length; i++) {
            System.out.print((pieces.length - i) + " ");
            for (int j = 0; j < pieces.length; j++) {
                printPiece(pieces[i][j]);
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }

    // Exibição de uma unica peça.
    private static void printPiece(ChessPiece piece) {
        if (piece == null) {
            System.out.print("-");
        }
        else {
            System.out.print(piece);
        }
        System.out.print(" ");
    }
}