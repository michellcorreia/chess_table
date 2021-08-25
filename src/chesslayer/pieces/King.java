package chesslayer.pieces;

import boardlayer.*;
import chesslayer.*;

public class King extends ChessPiece {
    
    public King(){
        super();
    }

    public King(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "K";
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
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position p = new Position(0, 0);

        //above
        p.setValues(position.getRow() - 1, position.getColumn());
        canMove(mat, p);

        //below
        p.setValues(position.getRow() + 1, position.getColumn());
        canMove(mat, p);

        //left
        p.setValues((position.getRow()), position.getColumn() - 1);
        canMove(mat, p);

        //right
        p.setValues((position.getRow()), position.getColumn() + 1);
        canMove(mat, p);

        //NE
        p.setValues((position.getRow() - 1), position.getColumn() + 1);
        canMove(mat, p);

        //SE
        p.setValues((position.getRow() + 1), position.getColumn() + 1);
        canMove(mat, p);

        //NW
        p.setValues((position.getRow() - 1), position.getColumn() - 1);
        canMove(mat, p);

        //SW
        p.setValues((position.getRow() + 1), position.getColumn() - 1);
        canMove(mat, p);


        return mat;
    }
}
