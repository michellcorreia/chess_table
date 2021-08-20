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
}
