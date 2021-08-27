# Tabuleiro de Xadrez (finalizado)
Projeto de partida de xadrez feito no curso de Java -  100% java

O programa objetiva simular uma partida de xadrez, obedecendo todas as regras do jogo. No estágio atual, o programa está apenas sendo estruturado em camadas para posteriormente ir recebendo as instruções lógicas. O tabuleiro de xadrez é criado e manuseado através de uma matriz bidimensional, que é definida como 8x8. Toda a inteligência à respeito do jogo será colocada dentro de uma interação com matriz.

O programa possui duas camadas:


### "boardlayer"

É a parte estrutural do programa, definindo os métodos e atributos mais genéricos dos objetos. Essas classes não serão acessados pelo programa principal, elas servirão de suporte (ou superclasses) para as classes dentro da camada lógica. 

### "chesslayer"

É a camada lógica, possui os métodos e atributos mais específicos dos objetos. Nela está toda a inteligência acerca do funcionamento de um jogo de xadrez, como a definição e criação do  tabuleiro com suas dimensões, a lógica responsável por colocar as peças em suas posições iniciais dentro do tabuleiro e os tipos de peças que compõem o jogo de xadrez. (mais implementações ainda serão feitas)