/******************************************************************************
 *
 * MAC0121 - Algoritmos e Estruturas de Dados I
 * Aluno: Victor Hugo Miranda Pinto
 * Numero USP: 10297720
 * Tarefa: Web Exercise 2.5.23 (Median given two sorted arrays)
 *
 * DECLARO QUE SOU O ÚNICO AUTOR E RESPONSÁVEL POR ESTE PROGRAMA.  TODAS AS 
 * PARTES DO PROGRAMA, EXCETO AS QUE SÃO BASEADAS EM MATERIAL FORNECIDO  
 * PELO PROFESSOR OU COPIADAS DO LIVRO OU DAS BIBLIOTECAS DE SEDGEWICK & WAYNE, 
 * FORAM DESENVOLVIDAS POR MIM.  DECLARO TAMBÉM QUE SOU RESPONSÁVEL POR TODAS 
 * AS CÓPIAS DESTE PROGRAMA E QUE NÃO DISTRIBUÍ NEM FACILITEI A DISTRIBUIÇÃO
 * DE CÓPIAS DESTA PROGRAMA.
 *
 ******************************************************************************/

class Median2Sorted {

	public static Comparable select(Comparable[] a, Comparable[] b, int k){
		int hiA = a.length;
		int hiB = b.length;

	    int lowA = 0, lowB = 0;
	    int mid_step = 0;

	    // we know that when (lowA + lowB) = k, we already have an "imaginary array" of size k-1
	    // so we stop and just look at which element from A or B should be put in position k
	    // and that is the answer
	    while (lowA + lowB < k) {
	    	// here our step is basically the same as in a binary search
	    	// we begin with a base step and half if each time, giving us the median of each sub-array each time
	    	// garrantees our O(log(a.length + b.length)) efficiency
	        mid_step = ((k + 1) - lowA - lowB) / 2;
	        int stepA = lowA + mid_step;
	        int stepB = lowB + mid_step;
	        if (hiA > stepA - 1 && (hiB <= stepB - 1 || a[stepA - 1].compareTo(b[stepB - 1]) < 0)) {
	        	// if median from array A[lowA...hiA] is greater then median from array B[lowB...hiB]
	        	// we know the answer is not in A[0...stepA)
	            lowA = stepA;
	        }
	        // else, we know it is not in B[0...stepB)
	        else
	            lowB = stepB;
	    }

	    // (lowA + lowB) = k
	    // returns the min(a[lowA], b[lowB])
	    if (hiA > lowA && (hiB <= lowB || a[lowA].compareTo(b[lowB]) < 0)) {
	        return a[lowA];
	    }
	    else 
	        return b[lowB];
	}

	// simple test client
	public static void main(String[] args) {

		int k = Integer.parseInt(args[0]);

		String[] a = StdIn.readLine().split("\\s");
		String[] b = StdIn.readLine().split("\\s");
		MyString[] aa = new MyString[a.length];
		MyString[] bb = new MyString[b.length];
		for (int i = 0; i < a.length; i++)
		    aa[i] = new MyString(a[i]);
		for (int i = 0; i < b.length; i++)
		    bb[i] = new MyString(b[i]);

		MyString s = (MyString) select(aa, bb, k);

		StdOut.println(s.val());
	}
}