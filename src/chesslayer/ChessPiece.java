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

    public ChessPosition getChessPiece() {
        return ChessPosition.fromPosition(position);
    }

    public ChessPosition getChessPosition() {
        return ChessPosition.fromPosition(position);
    }

    // Testa se a cor da peça em "position" é diferente da cor da minha peça.
    protected boolean isThereOpponentPiece(Position position) {
        ChessPiece p = (ChessPiece) getBoard().piece(position);
        return p != null && p.getColor() != color;
    }
}
