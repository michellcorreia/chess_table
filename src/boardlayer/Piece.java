package boardlayer;

public class Piece {

    protected Position position;
    private Board board;

    public Piece() {
    }

    // A posição de uma peça recém-criada é null.(não foi colocada no tabuleiro ainda.)
    public Piece(Board board) {
        this.board = board;
        position = null;
    }

    // Somente subclasses de Piece, e classes do mesmo pacote poderão acessar o tabuleiro da peça
    protected Board getBoard() {
        return board;
    }

    // Não há setBoard pois as peças não poderão ser transferidas de tabuleiro.
}
