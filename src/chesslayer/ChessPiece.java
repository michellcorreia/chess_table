package chesslayer;

import boardlayer.*;

public abstract class ChessPiece extends Piece {

    private Color color;

    public ChessPiece() {
        super();
    }

    public ChessPiece(Board board, Color color) {
        super(board);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
