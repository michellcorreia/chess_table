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

    // Método para possibilitar a inserção de peças usando as posições do tabuleiro, e não mais da matriz (extingue o uso direto de placePiece)
    private void placeNewPiece(char column, int row, ChessPiece piece) {
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
    }

    // Organiza as peças em suas respectivas posições ao inicio de uma partida.
    private void initialSetup() {
        placeNewPiece('a', 1, new Rook(board, Color.WHITE));
        placeNewPiece('h', 1, new Rook(board, Color.WHITE));

        placeNewPiece('a', 8, new Rook(board, Color.BLACK));
        placeNewPiece('h', 8, new Rook(board, Color.BLACK));
    }

}
