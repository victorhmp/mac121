/******************************************************************************
 *
 * MAC0121 - Algoritmos e Estruturas de Dados I
 * Aluno: Victor Hugo Miranda Pinto
 * Numero USP: 10297720
 * Tarefa: Verificador ortográfico
 *
 * DECLARO QUE SOU O ÚNICO AUTOR E RESPONSÁVEL POR ESTE PROGRAMA.  TODAS AS 
 * PARTES DO PROGRAMA, EXCETO AS QUE SÃO BASEADAS EM MATERIAL FORNECIDO  
 * PELO PROFESSOR OU COPIADAS DO LIVRO OU DAS BIBLIOTECAS DE SEDGEWICK & WAYNE, 
 * FORAM DESENVOLVIDAS POR MIM.  DECLARO TAMBÉM QUE SOU RESPONSÁVEL POR TODAS 
 * AS CÓPIAS DESTE PROGRAMA E QUE NÃO DISTRIBUÍ NEM FACILITEI A DISTRIBUIÇÃO
 * DE CÓPIAS DESTA PROGRAMA.
 *
 ******************************************************************************/

public class Ortho {

	// simple function to read a dictionary
	public static String[] readDic(String name){
		In in = new In(name);
		String[] dic = in.readAllStrings();

		return dic;
	}

	public static int buscaB(String key, String[] a){
		return buscaB(key, a, 0, a.length);
	}
	// binary search
	public static int buscaB(String x, String[] dic, int lo, int hi){
		if(hi <= lo) return -1;
		int mid = lo + (hi - lo)/2;
		int cmp = dic[mid].compareTo(x);

		if(cmp > 0) return buscaB(x, dic, lo, mid);
		if(cmp < 0) return buscaB(x, dic, mid+1, hi);

		else return mid;
	}

	public static void main(String[] args) {
		String[] dic = readDic(args[0]);
		String text = StdIn.readAll();

		// does the spliting striping everything that is not a letter
		String[] words = text.split("\\P{L}+");

		int n = words.length;

		//if(args[1]=="") {
			for(int i=0;i<n;i++){
				// checks for word as it appears
				int result1 = buscaB(words[i], dic);

				// rechecks it, in lower case
				int result2 = buscaB(words[i].toLowerCase(), dic);

				if(result1<0 && result2<0) StdOut.println(words[i]);
			}
		//}
		/*
		else {

			int j=0;
			String[] found = new String[n];

			for(int i=0;i<n;i++){
				// checks for word as it appears
				int result1 = buscaB(words[i], dic);

				// rechecks it, in lower case
				int result2 = buscaB(words[i].toLowerCase(), dic);

				if(result1<0 && result2<0) found[j++] = words[i];
			}

			j = 0;
			int k=0;
			int aux=0;

			for(int i=0;i<text.length();i++){
				if(!Character.isWhitespace(text.charAt(i))) aux++;
				if(Character.isWhitespace(text.charAt(i)) && aux>0){
					k++;
					aux=0;
				}
				if(words[k]==found[j]){
					StdOut.print(" *"+words[k]+"* ");
					j++;
					i++;
				}
				else if(words[k]!=found[j]) StdOut.print(text.charAt(i));
			}
		}
		*/
	}
}