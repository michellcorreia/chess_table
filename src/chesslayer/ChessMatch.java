package chesslayer;

import boardlayer.*;
import chesslayer.pieces.Rook;

public class ChessMatch {

    private Board board;

    // Define as dimensões do tabuleiro, e chama o initialSetup para organizar as
    // peças em suas posições iniciais.
    public ChessMatch() {
        board = new Board(8, 8);
        initialSetup();
    }

    // Transfere toda a matriz pieces[][](Tipo Piece) para mat[][](Tipo ChessPieces)
    // fazendo Downcasting.
    public ChessPiece[][] getPieces() {
        ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {
                mat[i][j] = (ChessPiece) board.piece(i, j);
            }
        }
        return mat;
    }

    // Valida a existencia da peça, faz o movimento e retorna a peça capturada (se existir)
    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();
        
        validateSourcePosition(source);
        Piece capturedPiece = makeMove(source, target);
        return (ChessPiece)capturedPiece;
    }

    // Realiza o movimento da peça, captura e retorna a peça capturada (se existir)
    private Piece makeMove(Position source, Position target) {
        Piece p = board.removePiece(source);
        Piece capturedPiece = board.removePiece(target);
        board.placePiece(p, target);
        return capturedPiece;
    }

    // Valida se realmente existe uma peça na posição de origem.
    private void validateSourcePosition(Position sourcePosition) {
        if(!board.thereIsAPiece(sourcePosition)) {
            throw new ChessException("There is no piece on source position.");
        }
    }

    // Método para possibilitar a inserção de peças usando as posições do tabuleiro,
    // e não mais da matriz (extingue o uso direto de placePiece)
    public void placeNewPiece(char column, int row, ChessPiece piece) {
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
