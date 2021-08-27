package chesslayer.pieces;

import boardlayer.*;
import chesslayer.*;

public class King extends ChessPiece {

    // A peça King precisa ter acesso à partida para poder fazer a verificação dos
    // movimentos especiais.
    private ChessMatch chessMatch;

    public King() {
        super();
    }

    public King(Board board, Color color, ChessMatch chessMatch) {
        super(board, color);
        this.chessMatch = chessMatch;
    }

    @Override
    public String toString() {
        return "K";
    }

    public void canMove(boolean[][] mat, Position p) {
        if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }
    }

    // Método para testar se o movimento especial "Castling" pode ser feito.
    private boolean testRookCastling(Position position) {
        ChessPiece p = (ChessPiece)getBoard().piece(position);
        return p != null && p instanceof Rook && p.getMoveCount() == 0 && p.getColor() == getColor(); 
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position p = new Position(0, 0);

        // above
        p.setValues(position.getRow() - 1, position.getColumn());
        canMove(mat, p);

        // below
        p.setValues(position.getRow() + 1, position.getColumn());
        canMove(mat, p);

        // left
        p.setValues((position.getRow()), position.getColumn() - 1);
        canMove(mat, p);

        // right
        p.setValues((position.getRow()), position.getColumn() + 1);
        canMove(mat, p);

        // NE
        p.setValues((position.getRow() - 1), position.getColumn() + 1);
        canMove(mat, p);

        // SE
        p.setValues((position.getRow() + 1), position.getColumn() + 1);
        canMove(mat, p);

        // NW
        p.setValues((position.getRow() - 1), position.getColumn() - 1);
        canMove(mat, p);

        // SW
        p.setValues((position.getRow() + 1), position.getColumn() - 1);
        canMove(mat, p);

        // Verificação do movimento especial "Castling"
        if(getMoveCount() == 0 && !chessMatch.getCheck()) {
            // Movimento para a torre da direita
            Position t1 = new Position(position.getRow(), position.getColumn() + 3);
            if(testRookCastling(t1)) {
                Position p1 = new Position(position.getRow(), position.getColumn() + 1);
                Position p2 = new Position(position.getRow(), position.getColumn() + 2);
                if(getBoard().piece(p1) == null && getBoard().piece(p2) == null) {
                    mat[position.getRow()][position.getColumn() + 2] = true;
                }
            }

            // Movimento para a torre da esquerda
            Position t2 = new Position(position.getRow(), position.getColumn() - 4);
            if(testRookCastling(t2)) {
                Position p1 = new Position(position.getRow(), position.getColumn() - 1);
                Position p2 = new Position(position.getRow(), position.getColumn() - 2);
                Position p3 = new Position(position.getRow(), position.getColumn() - 3);
                if(getBoard().piece(p1) == null && getBoard().piece(p2) == null && getBoard().piece(p3) == null) {
                    mat[position.getRow()][position.getColumn() - 2] = true;
                }
            }     
        }

        return mat;
    }
}
