/******************************************************************************
 *
 * MAC0121 - Algoritmos e Estruturas de Dados I
 * Aluno: Victor Hugo Miranda Pinto
 * Numero USP: 10297720
 * Tarefa: Exercício 1.3.9 de Algs4
 *
 * DECLARO QUE SOU O ÚNICO AUTOR E RESPONSÁVEL POR ESTE PROGRAMA.  TODAS AS 
 * PARTES DO PROGRAMA, EXCETO AS QUE SÃO BASEADAS EM MATERIAL FORNECIDO  
 * PELO PROFESSOR OU COPIADAS DO LIVRO OU DAS BIBLIOTECAS DE SEDGEWICK & WAYNE, 
 * FORAM DESENVOLVIDAS POR MIM.  DECLARO TAMBÉM QUE SOU RESPONSÁVEL POR TODAS 
 * AS CÓPIAS DESTE PROGRAMA E QUE NÃO DISTRIBUÍ NEM FACILITEI A DISTRIBUIÇÃO
 * DE CÓPIAS DESTA PROGRAMA.
 *
 ******************************************************************************/

public class LParens {
	public static void main(String[] args) {

		// Criação de objetos do tipo Stack<Item> dependem da
		// classe Stack, implementada por Sedgewick & Wayne.
		// São estruturadas por listas ligadas.
		Stack<String> ops = new Stack<String>();
		Stack<String> vals = new Stack<String>();

		// Resolução semelhante ao algoritmo de Dijkstra para
		// calcular expressões infixas.

		while(!StdIn.isEmpty()){
			String s = StdIn.readString();
			if(s.equals("(")) {}
			else if(s.equals("+") || s.equals("*") || s.equals("-") || s.equals("/")){
				ops.push(s);
			}
			else if(s.equals(")")){
				String operation = ops.pop();
				String v = vals.pop();

				v = "( " + vals.pop() + " " + operation + " " + v + " )";

				vals.push(v);
			}
			else vals.push(s);
		}

		// Minha expressão final estará no topo da pilha de valores
		StdOut.println(vals.pop());
	}
}
