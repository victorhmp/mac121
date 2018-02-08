/******************************************************************************
 *
 * MAC0121 - Algoritmos e Estruturas de Dados I
 * Aluno: Victor Hugo Miranda Pinto
 * Numero USP: 10297720
 * Tarefa: Número de ocorrências em lista ordenada
 *
 * DECLARO QUE SOU O ÚNICO AUTOR E RESPONSÁVEL POR ESTE PROGRAMA.  TODAS AS 
 * PARTES DO PROGRAMA, EXCETO AS QUE SÃO BASEADAS EM MATERIAL FORNECIDO  
 * PELO PROFESSOR OU COPIADAS DO LIVRO OU DAS BIBLIOTECAS DE SEDGEWICK & WAYNE, 
 * FORAM DESENVOLVIDAS POR MIM.  DECLARO TAMBÉM QUE SOU RESPONSÁVEL POR TODAS 
 * AS CÓPIAS DESTE PROGRAMA E QUE NÃO DISTRIBUÍ NEM FACILITEI A DISTRIBUIÇÃO
 * DE CÓPIAS DESTA PROGRAMA.
 *
 ******************************************************************************/

public class CountOccurrences  {
	
	// binary search with jumps of length a
	// occorences of the same number will be in the range [p+1 ... q]
	public static int bSearchCount(int[] arr, int n, int k){
		int p = -1, q = -1;
		for(int a = n; a >= 1; a/=2){
			while (p+a < n && arr[p+a] < k) p += a;
			while (q+a < n && arr[q+a] <= k) q += a;
		}

		return Math.abs(p - q);
	}

	// function that reads the original array of values
	public static int[] readInp(String filename){
		In in = new In(filename);
		int[] inp = in.readAllInts();

		return inp;
	}

	public static void main(String[] args) {
		int[] inp = readInp(args[0]);

		// while that are queryes to be checked run binary search and count occurences
		while(!StdIn.isEmpty()){
			int q = StdIn.readInt();
			StdOut.println(bSearchCount(inp, inp.length, q));
		}
	}
}