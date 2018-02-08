/******************************************************************************
 *
 * MAC0121 - Algoritmos e Estruturas de Dados I
 * Aluno: Victor Hugo Miranda Pinto
 * Numero USP: 10297720
 * Tarefa: Web Exercise 4.2.1 (Union of intervals)
 *
 * DECLARO QUE SOU O ÚNICO AUTOR E RESPONSÁVEL POR ESTE PROGRAMA.  TODAS AS 
 * PARTES DO PROGRAMA, EXCETO AS QUE SÃO BASEADAS EM MATERIAL FORNECIDO  
 * PELO PROFESSOR OU COPIADAS DO LIVRO OU DAS BIBLIOTECAS DE SEDGEWICK & WAYNE, 
 * FORAM DESENVOLVIDAS POR MIM.  DECLARO TAMBÉM QUE SOU RESPONSÁVEL POR TODAS 
 * AS CÓPIAS DESTE PROGRAMA E QUE NÃO DISTRIBUÍ NEM FACILITEI A DISTRIBUIÇÃO
 * DE CÓPIAS DESTA PROGRAMA.
 *
 ******************************************************************************/

public class UnionOfIntervals  {

	// function to compare intervals by their min
    public static int compare(Interval1D a, Interval1D b) {
        if      (a.min() < b.min()) return -1;
        else if (a.min() > b.min()) return +1;
        else if (a.max() < b.max()) return -1;
        else if (a.max() > b.max()) return +1;
        else                    return  0;
    }

    // merge sort implementation
	private static Interval1D[] aux;
	public static void merge(Interval1D[] a, int lo, int mid, int hi){
		int i = lo, j=mid, N = hi-lo;
		for(int k =0;k<N;k++){
			if(i==mid) aux[k] = a[j++];
			else if (j==hi) aux[k] = a[i++];
			else if (compare(a[j], a[i]) < 0) aux[k] = a[j++];
			else aux[k] = a[i++];
		}

		for(int k=0;k<N;k++){
			a[lo+k] = aux[k];
		}
	}

	public static void sort(Interval1D[] a, int size){
		aux = new Interval1D[size];
		sort(a, 0, size);
	}

	public static void sort(Interval1D[] a, int lo, int hi){
		int N = hi-lo;
		if(N <= 1) return;
		int mid = lo + N/2;
		sort(a, lo, mid);
		sort(a, mid, hi);
		merge(a, lo, mid, hi);
	}

	// up to here

	public static void main(String[] args) {
		// input intervals (up to 11234 intervals)
		Interval1D[] interval = new Interval1D[1123456];
		int size=0;

		while(!StdIn.isEmpty()){
			double min = StdIn.readDouble();
			double max = StdIn.readDouble();

			interval[size++] = new Interval1D(min, max);
		}
		// array where the final answer is stored
		Interval1D[] interval_final = new Interval1D[size+1];

		Interval1D[] interval_original = new Interval1D[size];
		for(int i=0;i<size;i++)
			interval_original[i] = interval[i];
		
		// sort by min
		sort(interval, size);

		// aux variable to keep track of the number of intervals in the answer
		int aux=0; 

		for(int i=0;i<size;i++){
			Interval1D inter = interval[i];
			for(int j=i+1;j<size;j++){
				if(inter.intersects(interval[j])){
					inter = new Interval1D(inter.min(), Math.max( inter.max(), interval[j].max() ));
					if(j==size-1) interval_final[aux++] = inter;
				}
				else{
					interval_final[aux++] = inter;
					i = j-1;
					break;
				}
			}
		}

		// get union of intervals length
		double total_length = 0;
		for(int k=0;k<aux;k++){
			total_length += interval_final[k].length();
		}

		StdOut.println( "Total length: " + total_length  + " [" + (aux) + " components]");

		// only for "verboso"
		if(args.length > 0){
			for(int k=0;k<aux;k++)
				StdOut.println(interval_final[k]);
			double c = 0;
			for(int i=0;i<size;i++){
				// vertical lines
				StdDraw.setPenRadius(0.002);
				StdDraw.line( interval_original[i].min(), 0.0,  interval_original[i].min(), 1.0);
				StdDraw.line( interval_original[i].max(), 0.0,  interval_original[i].max(), 1.0);

				//horizontal lines
				StdDraw.setPenRadius(0.005);
				StdDraw.line( interval_original[i].min(), (0.1+c),  interval_original[i].max(), (0.1+c));

				c+=0.02;
			}


			for(int k=0;k<aux;k++){
				StdDraw.setPenRadius(0.005);
				StdDraw.setPenColor(StdDraw.RED);

				StdDraw.line( interval_final[k].min(), (0.1+c), interval_final[k].max(), (0.1+c) );
				c+=0.02;
			}
		}
	}
}