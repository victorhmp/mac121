/******************************************************************************
 *
 * MAC0121 - Algoritmos e Estruturas de Dados I
 * Aluno: Victor Hugo Miranda Pinto
 * Numero USP: 10297720
 * Tarefa: Word Loops
 *
 * Baseado em: WordLadder.java
 * Dependências: Graph.java | DepthFirstSearchNew.java
 *
 * DECLARO QUE SOU O ÚNICO AUTOR E RESPONSÁVEL POR ESTE PROGRAMA.  TODAS AS
 * PARTES DO PROGRAMA, EXCETO AS QUE SÃO BASEADAS EM MATERIAL FORNECIDO
 * PELO PROFESSOR OU COPIADAS DO LIVRO OU DAS BIBLIOTECAS DE SEDGEWICK & WAYNE,
 * FORAM DESENVOLVIDAS POR MIM.  DECLARO TAMBÉM QUE SOU RESPONSÁVEL POR TODAS
 * AS CÓPIAS DESTE PROGRAMA E QUE NÃO DISTRIBUÍ NEM FACILITEI A DISTRIBUIÇÃO
 * DE CÓPIAS DESTA PROGRAMA.
 *
 ******************************************************************************/

public class WordLoop {

    // função auxiliar para determinar se duas palavras são
    // conectadas, ou seja, diferem por uma letra apenas
    public static boolean isConected(String a, String b) {
        int differ = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) differ++;
        }
        return (differ == 1);
    }

    public static void main(String[] args) {

        In valid_words = new In(args[0]);
        String[] words = valid_words.readAllStrings();
        String[] query = new String[100000];

        // cria o grafo com as palavras dadas pelo arquivo
        // de nome dado por args[0] nos vértices e arestas
        // ligando palavras que diferem por uma letra
        Graph G = new Graph();
        for (int i = 0; i < words.length; i++)
            for (int j = i+1; j < words.length; j++)
                if (isConected(words[i], words[j]))
                    G.addEdge(words[i], words[j]);

        // lê da entrada padrão as palavras que vamos buscar,
        // onde começamos nossos possíveis word loops
        int n = 0;
        while (!StdIn.isEmpty()) {
            query[n] = StdIn.readString();
            if(G.hasVertex(query[n])){
                DepthFirstSearchNew search = new DepthFirstSearchNew(G, query[n]);
                if(!search.getLoop().equals(""))
                    StdOut.println(query[n] + ": " + query[n] + search.getLoop());
                else StdOut.println(query[n] + ": no word loop");
            }
            else StdOut.println(query[n] + ": not in graph");

            n++;
        }
    }
}
