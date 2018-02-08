/******************************************************************************
 *
 * MAC0121 - Algoritmos e Estruturas de Dados I
 * Aluno: Victor Hugo Miranda Pinto
 * Numero USP: 10297720
 * Tarefa: Word Loops
 *
 * Baseado em: DepthFirstSearch.java
 * Dependências: Graph.java | ST.java
 *
 * DECLARO QUE SOU O ÚNICO AUTOR E RESPONSÁVEL POR ESTE PROGRAMA.  TODAS AS
 * PARTES DO PROGRAMA, EXCETO AS QUE SÃO BASEADAS EM MATERIAL FORNECIDO
 * PELO PROFESSOR OU COPIADAS DO LIVRO OU DAS BIBLIOTECAS DE SEDGEWICK & WAYNE,
 * FORAM DESENVOLVIDAS POR MIM.  DECLARO TAMBÉM QUE SOU RESPONSÁVEL POR TODAS
 * AS CÓPIAS DESTE PROGRAMA E QUE NÃO DISTRIBUÍ NEM FACILITEI A DISTRIBUIÇÃO
 * DE CÓPIAS DESTA PROGRAMA.
 *
 ******************************************************************************/


public class DepthFirstSearchNew {

    // Essa nova classe é uma adaptação da classe original criada por
    // Robert Sedgewick e Kevin Wayne, para concretizar a solução do EP.

    private ST<String, Boolean> marked;     // marked[v] = existe um caminho entre s e v?
    private String init;                   // palavra que inicia (e termina) o word loop
    private String loop;                    // word loop final
    private int loop_size;                 // variável auxiliar para saber o tamanho do loop
    private boolean found_loop;            // booleana para saber quando para a DFS

    // Construtor do objeto que realiza uma busca partindo de s
    public DepthFirstSearchNew(Graph G, String s) {
        marked = new ST<String, Boolean>();
        for (String v : G.vertices()) 
            marked.put(v, false);
        validateVertex(s);
        init = s;
        loop = "";
        loop_size = 1;
        found_loop = false;
        dfs(G, s);
    }

    // DFS a partir do vértice v
    private void dfs(Graph G, String v) {
	    marked.put(v, true);

        for (String w : G.adjacentTo(v)) {
            
            // checar se o vértice já foi visitado
            // se não foi, aumento o tamanho do loop em 1
            // e concateno a palavra do vértice no loop
            if (!marked.get(w)) {
                loop = this.loop + " " + w;
                loop_size++;
                dfs(G, w);
            }

            // nesse caso, o vértice já foi visitado,
            // assim, posso ter encontrado um word loop,
            // então considero a restrição de que um word loop deve ter
            // pelo menos 3 palavras distintas
            if(w.equals(init) && loop_size >= 3){
                    loop = this.loop + " " + this.init;
                    found_loop = true;
            }

            // se encontrei o word loop, termino a DFS
            if(this.found_loop){
                return;
            }
        }

        // se o vértice analisado no momento não possui mais vizinhos,
        // ou seja, cheguei a um "dead-end", retiro esse vértice do loop
        String a = " " + v;
        this.loop = this.loop.replace(a, "");
        loop_size--;
    }

    // função simples que apenas retorna o word loop encontrado
    public String getLoop(){
        return this.loop;
    }

    public boolean marked(String v) {
        validateVertex(v);
        return marked.get(v);
    }

    private void validateVertex(String v) {
	if (!marked.contains(v))
	    throw new IllegalArgumentException("vertex not in graph");
    }
}