package chesslayer;

import boardlayer.*;

public abstract class ChessPiece extends Piece {

    private Color color;
    private int moveCount;

    public ChessPiece() {
        super();
    }

    public ChessPiece(Board board, Color color) {
        super(board);
        this.color = color;
        moveCount = 0;
    }

    public Color getColor() {
        return color;
    }

    public int getMoveCount() {
        return moveCount;
    }

    public void increaseMoveCount() {
        moveCount++;
    }

    public void decreaseMoveCount() {
        moveCount--;
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
