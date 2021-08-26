package chesslayer.pieces;

import chesslayer.*;
import boardlayer.*;

public class Knight extends ChessPiece {
    
    public Knight(){
        super();
    }

    public Knight(Board board, Color color) {
        super(board, color);
    }

    public void canMove(boolean[][] mat, Position p){
        if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }
        if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }
    }

    @Override
    public String toString() {
        return "N";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position p = new Position(0, 0);

        // UP/RIGHT
        p.setValues(position.getRow() - 2, position.getColumn() + 1);
        canMove(mat, p);

        // UP/LEFT
        p.setValues(position.getRow() - 2, position.getColumn() - 1);
        canMove(mat, p);

        // RIGHT/UP
        p.setValues(position.getRow() - 1, position.getColumn() + 2);
        canMove(mat, p);

        // RIGHT/DOWN
        p.setValues(position.getRow() + 1, position.getColumn() + 2);
        canMove(mat, p);

        // DOWN/RIGHT
        p.setValues(position.getRow() + 2, position.getColumn() + 1);
        canMove(mat, p);

        // DOWN/LEFT
        p.setValues(position.getRow() + 2, position.getColumn() - 1);
        canMove(mat, p);

        // LEFT/DOWN
        p.setValues(position.getRow() + 1, position.getColumn() - 2);
        canMove(mat, p);

        // LEFT/UP
        p.setValues(position.getRow() - 1, position.getColumn() - 2);
        canMove(mat, p);

        return mat;
    }

}
