package boardlayer;

public class Board {

    private int rows;
    private int columns;
    private Piece[][] pieces;

    public Board(int rows, int columns) {
        if (rows < 1 || columns < 1) {
            throw new BoardException("Error creating board: there must be at least 1 row and 1 column.");
        }
        this.rows = rows;
        this.columns = columns;
        pieces = new Piece[rows][columns];
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public Piece piece(int row, int column) {
        if (!positionExists(new Position(row, column))) {
            throw new BoardException("Position not on the board.");
        }
        return pieces[row][column];
    }

    // sobrecarga
    public Piece piece(Position position) {
        if (!positionExists(position)) {
            throw new BoardException("Position not on the board.");
        }
        return pieces[position.getRow()][position.getColumn()];
    }

    // Insere peças no tabuleiro, na posição passada como argumento.
    public void placePiece(Piece piece, Position position) {
        if (thereIsAPiece(position)) {
            throw new BoardException("There's already a piece on position " + position + ".");
        }
        pieces[position.getRow()][position.getColumn()] = piece;
        piece.position = position;
    }

    // Remove peças do tabuleiro, na posição passada como argumento.
    public Piece removePiece(Position position) {
        if (!positionExists(position)) {
            throw new BoardException("Position not on the board");
        }
        if (!thereIsAPiece(position)) {
            return null;
        }
        // O método remove a peça da posição, guarda essa peça em uma variável auxiliar
        // (com position null) e retorna essa variável com a peça que foi apagada.
        Piece aux = piece(position);
        aux.position = null;
        pieces[position.getRow()][position.getColumn()] = null;
        return aux;
    }

    // Método de apoio (usado abaixo).
    private boolean positionExists(int row, int column) {
        return row >= 0 && row < rows && column >= 0 && column < columns;
    }

    // Checa se a posição existe detro do board.
    public boolean positionExists(Position position) {
        return positionExists(position.getRow(), position.getColumn());
    }

    // Checa se existe uma peça na posição.
    public boolean thereIsAPiece(Position position) {
        if (!positionExists(position)) {
            throw new BoardException("Position not on the board.");
        }
        return piece(position) != null;
    }
}
