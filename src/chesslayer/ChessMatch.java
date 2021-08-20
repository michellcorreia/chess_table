package chesslayer;

import boardlayer.*;
import chesslayer.pieces.Rook;


public class ChessMatch {

    private Board board;

    // Define as dimensões do tabuleiro, e chama o initialSetup para organizar as peças em suas posições iniciais.
    public ChessMatch() {
        board = new Board(8, 8);
        initialSetup();
    }
    
    // Transfere toda a matriz pieces[][](Tipo Piece) para mat[][](Tipo ChessPieces) fazendo Downcasting.
    public ChessPiece[][] getPieces() {
        ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {
                mat[i][j] = (ChessPiece) board.piece(i, j);
            }
        }
        return mat;
    }

    // Organiza as peças em suas respectivas posições ao inicio de uma partida.
    private void initialSetup() {
        board.placePiece(new Rook(board, Color.WHITE), new Position(0, 0));
        board.placePiece(new Rook(board, Color.WHITE), new Position(0, 7));

        board.placePiece(new Rook(board, Color.BLACK), new Position(7, 0));
        board.placePiece(new Rook(board, Color.BLACK), new Position(7, 7));
    }

}
