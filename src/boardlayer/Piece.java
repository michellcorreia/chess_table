package boardlayer;

public abstract class Piece {

    protected Position position;
    private Board board;

    public Piece() {
    }

    // A posição de uma peça recém-criada é null.(não foi colocada no tabuleiro
    // ainda.)
    public Piece(Board board) {
        this.board = board;
        position = null;
    }

    // Somente subclasses de Piece, e classes do mesmo pacote poderão acessar o
    // tabuleiro da peça
    protected Board getBoard() {
        return board;
    }
    // Não há setBoard pois as peças não poderão ser transferidas de tabuleiro.

    // Método abstrato que retorna uma matriz booleana
    public abstract boolean[][] possibleMoves();

    // Método concreto que puxa o método abstrato para checar uma posição na matriz
    public boolean possibleMove(Position position) {
        return possibleMoves()[position.getRow()][position.getColumn()];
    }

    // Checa dentro da matriz booleana se existe pelo menos um movimento possível
    // para a peça (um campo true)
    public boolean isThereAnyPossibleMove() {
        boolean[][] mat = possibleMoves();
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                if (mat[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }
}
