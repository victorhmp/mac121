/******************************************************************************
 *
 * MAC0121 - Algoritmos e Estruturas de Dados I
 * Aluno: Victor Hugo Miranda Pinto
 * Numero USP: 10297720
 * Tarefa: Caching ótimo
 *
 * Dependências: IndexMaxPQ.java / BST.java / Queue.java
 *
 * DECLARO QUE SOU O ÚNICO AUTOR E RESPONSÁVEL POR ESTE PROGRAMA.  TODAS AS 
 * PARTES DO PROGRAMA, EXCETO AS QUE SÃO BASEADAS EM MATERIAL FORNECIDO  
 * PELO PROFESSOR OU COPIADAS DO LIVRO OU DAS BIBLIOTECAS DE SEDGEWICK & WAYNE, 
 * FORAM DESENVOLVIDAS POR MIM.  DECLARO TAMBÉM QUE SOU RESPONSÁVEL POR TODAS 
 * AS CÓPIAS DESTE PROGRAMA E QUE NÃO DISTRIBUÍ NEM FACILITEI A DISTRIBUIÇÃO
 * DE CÓPIAS DESTA PROGRAMA.
 *
 ******************************************************************************/

/***************************************************************
Sendo:
	N: número total de d_i (a sequência seria d_1, ..., d_N)
	m: tamanho do cache
	n: número de d_i distintos na sequência

	Sabemos que obtivemos a complexidade desejada pois,
vamos acessar a BST exatamente N vezes, primeiro para preencher
o cache com sua configuração inicial e depois, partindo de j+1
terminamos de iterar pelas queries. Cada busca por um elemento
na BST me garante custo O(log n), da mesma maneira que a operação
insert() no meu cache tem custo O(log m). Assim, atingindo a com-
plexidade desejada O(N(log n + log m)).
***************************************************************/

public class Clairvoyant {

	public static void main(String[] args){
		int cache_size = Integer.parseInt(args[0]);
		int cache_miss = 0;
		int count = 0;

		// vetor com os querys de entrada
		String[] que = StdIn.readAllStrings();

		// BST auxiliar que guarda a primeira ocorrência
		// de cada String no vetor de querys
		BST<String, Integer> query = new BST<String, Integer>();

		// String auxiliar para guardar a sequência de
		// ocorrências de um certo String em que
		String s = "";

		// Preenche a BST
		for(int i=0;i<que.length;i++){
			if(!query.contains(que[i])){
				query.put(que[i], count);
				s = s + que[i] + " ";
				count++;
			}
		}

		// vetor booleano auxiliar para preencher o cache
		// sem elementos repetidos
		boolean[] ocorr = new boolean[query.size()+1];

		// split da String s, apenas para auxiliar a imprimir os elementos
		String[] s_aux = s.split("\\s+");

		// vetor de Queues, onde cada Queue guarda as ocorrências de um String diferente
		// de que[]
		Queue<Integer>[] ind = (Queue<Integer>[]) new Queue[query.size()+1];
		for(int i=0;i<query.size()+1;i++){
			ind[i] = new Queue<Integer>();
		}

		// Representa o cache
		IndexMaxPQ<Integer> cache = new IndexMaxPQ<Integer>(que.length + query.size()+1);

		// Preenche as Queues
		for(int i=0;i<que.length;i++){
			int aux = query.get(que[i]);
			ind[aux].enqueue(i);
		}

		// Ajuste para colocar na Queue de cada elemento
		// um valor maior do que o número de query
		// ou seja, um valor para o qual ele nunca mais
		// aparece
		int helper = que.length;
		for(int i=0;i<ind.length;i++){
			ind[i].enqueue(helper++);
		}

		count = 0;
		int j=0;

		// Preenche o Cache em sua configuração inicial
		for(j=0;j<que.length;j++){
			int aux = query.get(que[j]);
			if(!ocorr[aux]){
				ind[aux].dequeue();
				cache.insert(ind[aux].peek(), ind[aux].peek());
				count++;
				if(args.length > 1) StdOut.println(que[j] + ": +" + que[j]);
			}
			else{
				if(args.length > 1) StdOut.println(que[j] + ": in cache");
			}
			ocorr[aux] = true;
			if(count == cache_size) break;
		}

		// sei que o número de cache_miss até agora
		// deve ser n, pois só foram adicionados elementos
		// nunca, retirados.
		cache_miss = cache_size;

		// Faço o resto da varredura, sabendo que o meu Cache está cheio
		for(int k = j+1; k < que.length; k++){
			int aux = query.get(que[k]);

			// Cache hit!
			if(cache.contains(ind[aux].peek())){
				if(ind[aux].size() == 2){
					cache.delete(ind[aux].peek());
					ind[aux].dequeue();
					cache.insert(ind[aux].peek(), ind[aux].peek());
				}
				else if(ind[aux].size()==1) {}
				else{
					cache.delete(ind[aux].peek());
					ind[aux].dequeue();
					cache.insert(ind[aux].peek(), ind[aux].peek());
				}
				if(args.length > 1) StdOut.println(que[k] + ": in cache");
			}

			// Cache miss!
			else{
				cache_miss++;
				int insertion_pos = cache.delMax();
				if(insertion_pos >= que.length){
					if(args.length > 1) StdOut.println(que[k] + ": -" + s_aux[insertion_pos-que.length] + "/+" + que[k]);
				}
			    else{
			    	if(args.length > 1) StdOut.println(que[k] + ": -" + que[insertion_pos] + "/+" + que[k]);
			    }
				if(ind[aux].size() == 1){
					cache.insert(ind[aux].peek(), ind[aux].peek());
				}
				else{
					int b = ind[aux].dequeue();
					cache.insert(ind[aux].peek(), ind[aux].peek());
				}
			}
		}

		StdOut.println("Number of cache misses: " + cache_miss);

	}
}
