package chesslayer.pieces;

import chesslayer.*;
import boardlayer.*;

public class Rook extends ChessPiece {
    
    public Rook(){
        super();
    }

    public Rook(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "R";
    }
}
