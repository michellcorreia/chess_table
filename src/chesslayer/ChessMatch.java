package chesslayer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardlayer.*;
import chesslayer.pieces.King;
import chesslayer.pieces.Rook;

public class ChessMatch {

    private int turn;
    private Color currentPlayer;
    private Board board;
    private boolean check;
    private boolean checkMate;

    private List<Piece> piecesOnTheBoard = new ArrayList<>();
    private List<Piece> capturedPieces = new ArrayList<>();

    // Configura toda a lógica inicial da partida.
    public ChessMatch() {
        board = new Board(8, 8);
        turn = 1;
        currentPlayer = Color.WHITE;
        check = false;
        initialSetup();
    }

    public int getTurn() {
        return turn;
    }

    public Color getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean getCheck() {
        return check;
    }

    public boolean getCheckMate() {
        return checkMate;
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

    // Checa os movimentos possíveis da peça selecionada. (retorna uma matriz com as
    // posições possíveis)
    public boolean[][] possibleMoves(ChessPosition sourceposition) {
        Position position = sourceposition.toPosition();
        validateSourcePosition(position);
        return board.piece(position).possibleMoves();
    }

    // Valida a existencia da peça, faz o movimento e retorna a peça capturada (se
    // existir)
    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();

        validateSourcePosition(source);
        validateTargetPosition(source, target);
        Piece capturedPiece = makeMove(source, target);
       
        if(testCheck(currentPlayer)) {
            undoMove(source, target, capturedPiece);
            throw new ChessException("You can't put yourself in check.");
        }

        check = testCheck(opponent(currentPlayer)) ? true : false;

        if(testCheckMate(opponent(currentPlayer))) {
            checkMate = true;
        }
        else {
            nextTurn();
        }
        
        return (ChessPiece) capturedPiece;
    }

    // Realiza o movimento da peça, captura e retorna a peça capturada (se existir)
    private Piece makeMove(Position source, Position target) {
        Piece p = board.removePiece(source);
        Piece capturedPiece = board.removePiece(target);
        board.placePiece(p, target);

        if (capturedPiece != null) {
            piecesOnTheBoard.remove(capturedPiece);
            capturedPieces.add(capturedPiece);
        }

        return capturedPiece;
    }

    // Responsável por desfazer o movimento (oposto do método makeMove).
    private void undoMove(Position source, Position target, Piece capturedPiece) {
        Piece p = board.removePiece(target);
        board.placePiece(p, source);

        if (capturedPiece != null) {
            board.placePiece(capturedPiece, target);
            capturedPieces.remove(capturedPiece);
            piecesOnTheBoard.add(capturedPiece);
        }
    }

    // Valida se realmente existe uma peça na posição de origem.
    private void validateSourcePosition(Position sourcePosition) {
        if (!board.thereIsAPiece(sourcePosition)) {
            throw new ChessException("There is no piece on source position.");
        }
        if (currentPlayer != ((ChessPiece) board.piece(sourcePosition)).getColor()) {
            throw new ChessException("The chosen piece doesn't belong to you.");
        }

        if (!board.piece(sourcePosition).isThereAnyPossibleMove()) {
            throw new ChessException("There are no possible moves for the chosen piece.");
        }
    }

    // Valida se a posição de destino é uma posição que a peça de origem pode se
    // mover.
    private void validateTargetPosition(Position source, Position target) {
        if (!board.piece(source).possibleMove(target)) {
            throw new ChessException("The chosen piece can´t move to target position.");
        }
    }

    // Incrementa a contagem de turnos, e faz com que apenas peças uma cor possam
    // ser utilizadas em cada turno.
    private void nextTurn() {
        turn++;
        // Se o jogador atual é o branco, então agora será o preto.. caso contrário,
        // será o branco.
        currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    // Retorna a cor do oponente.
    private Color opponent(Color color) {
        return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    // Procura na lista a peça do rei da cor informada.
    private ChessPiece king(Color color) {
        List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
        for (Piece p : list) {
            if (p instanceof King) {
                return (ChessPiece)p;
            }
        }
        throw new IllegalStateException("There is no " + color + " king on the board.");
    }


    private boolean testCheck(Color color) {
        Position kingPosition = king(color).getChessPosition().toPosition();
        List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == opponent(color)).collect(Collectors.toList());
        for(Piece x : opponentPieces) {
            boolean[][] mat = x.possibleMoves();
            if(mat[kingPosition.getRow()][kingPosition.getColumn()]) {
                return true;
            }
        }
        return false;
    }

    private boolean testCheckMate(Color color) {
        if(!testCheck(color)) {
            return false;
        }
        List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
        for(Piece x : list) {
            boolean[][] mat = x.possibleMoves();
            for(int i = 0; i < board.getRows(); i++) {
                for(int j = 0; j < board.getColumns(); j++) {
                    if(mat[i][j]) {
                        Position source = ((ChessPiece)x).getChessPosition().toPosition();
                        Position target = new Position(i, j);
                        Piece capturedPiece = makeMove(source, target);

                        boolean testCheck = testCheck(color);
                        undoMove(source, target, capturedPiece);
                        if(!testCheck) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    // Método para possibilitar a inserção de peças usando as posições do tabuleiro,
    // e não mais da matriz (extingue o uso direto de placePiece)
    public void placeNewPiece(char column, int row, ChessPiece piece) {
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
        piecesOnTheBoard.add(piece);
    }

    // Organiza as peças em suas respectivas posições ao inicio de uma partida.
    private void initialSetup() {
        placeNewPiece('h', 7, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new King(board, Color.WHITE));

        placeNewPiece('b', 8, new Rook(board, Color.BLACK));
        placeNewPiece('a', 8, new King(board, Color.BLACK));
    }

}
