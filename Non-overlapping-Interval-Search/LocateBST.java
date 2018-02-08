/******************************************************************************
 *
 * MAC0121 - Algoritmos e Estruturas de Dados I
 * Aluno: Victor Hugo Miranda Pinto
 * Numero USP: 10297720
 * Tarefa: Non-overlapping interval search
 *
 * Baseado em: BST.java e Queue.java
 *
 * DECLARO QUE SOU O ÚNICO AUTOR E RESPONSÁVEL POR ESTE PROGRAMA.  TODAS AS 
 * PARTES DO PROGRAMA, EXCETO AS QUE SÃO BASEADAS EM MATERIAL FORNECIDO  
 * PELO PROFESSOR OU COPIADAS DO LIVRO OU DAS BIBLIOTECAS DE SEDGEWICK & WAYNE, 
 * FORAM DESENVOLVIDAS POR MIM.  DECLARO TAMBÉM QUE SOU RESPONSÁVEL POR TODAS 
 * AS CÓPIAS DESTE PROGRAMA E QUE NÃO DISTRIBUÍ NEM FACILITEI A DISTRIBUIÇÃO
 * DE CÓPIAS DESTA PROGRAMA.
 *
 ******************************************************************************/

public class LocateBST {
    public static void main(String[] args) {

        // first, read all intervals from command line argument
        // and store them in a Binary Search Tree
        BST<Integer, Integer> st = new BST<Integer, Integer>();
        In in = new In(args[0]);
        int[] interval_arr = in.readAllInts();
        for(int i=0;i<interval_arr.length-1;i++){
            st.put(interval_arr[i], interval_arr[i+1]);
            i++;
        }


        // storing values that will be useful
        // max and min (value and key)
        int min_key = st.min();
        int min_val = st.get(min_key);
        int max_key = st.max();
        int max_val = st.get(max_key);

        // read an integer from Std input and check where it fits
        while(!StdIn.isEmpty()){
            int search = StdIn.readInt();

            // check if "search" is 'inside' the tree
            if(search >= min_key && search <= max_val){
                int left = st.floor(search);
                int right = st.get(left);

                // check if search is in between two intervals
                if(right < search){
                    int next_left = st.select(st.rank(left)+1);
                    int next_right = st.get(next_left);

                    StdOut.println(search + ": between [" + left + ", " + right + "] & [" + next_left + ", " + next_right + "]");
                }
                // if not, just print the single interval
                else
                    StdOut.println(search + ": " + "[" + left + ", " + right + "]");
            }

            // check boundaries to know if search is on the right or the left
            else if(search < min_key){
                StdOut.println(search + ": left of " + "[" + min_key + ", " + min_val + "]");
            }
            else{
                StdOut.println(search + ": right of " + "[" + max_key + ", " + max_val + "]");
            }
        }
        // all input processed
        // done.
    }
}