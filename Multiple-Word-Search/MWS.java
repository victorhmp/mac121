/******************************************************************************
 *
 * MAC0121 - Algoritmos e Estruturas de Dados I
 * Aluno: Victor Hugo Miranda Pinto
 * Numero USP: 10297720
 * Tarefa: Multiple word search
 *
 * Dependências: Queue.java / BST.java
 *
 * DECLARO QUE SOU O ÚNICO AUTOR E RESPONSÁVEL POR ESTE PROGRAMA.  TODAS AS 
 * PARTES DO PROGRAMA, EXCETO AS QUE SÃO BASEADAS EM MATERIAL FORNECIDO  
 * PELO PROFESSOR OU COPIADAS DO LIVRO OU DAS BIBLIOTECAS DE SEDGEWICK & WAYNE, 
 * FORAM DESENVOLVIDAS POR MIM.  DECLARO TAMBÉM QUE SOU RESPONSÁVEL POR TODAS 
 * AS CÓPIAS DESTE PROGRAMA E QUE NÃO DISTRIBUÍ NEM FACILITEI A DISTRIBUIÇÃO
 * DE CÓPIAS DESTA PROGRAMA.
 *
 ******************************************************************************/

public class MWS {
	public static void main(String[] args) {
        // faz leitura da lista com as k palavras
        // e as coloca em uma árvore de busca binária (BST)
        // Key: palavra / Value: número de ocorrências
        BST<String, Integer> words = new BST<String, Integer>();
		In wordList = new In(args[1]);
		String[] str = wordList.readAllStrings();
        for(int i=0;i<str.length;i++)
            words.put(str[i], 0);

        // faz a leitura do texto onde as palavras serão buscadas
        // salva as palavras do texto em um vetor
        In text = new In(args[0]);
        String textStr = text.readAll();
        String[] textArray = textStr.split("\\P{L}+");

        // variáveis auxiliares
        int hits = words.size();
        int fim = 0;
        int ini = 0;
        int counter = 0;
        int printed = 0;

        // valor alto para efeito de comparação
        // fator limitante de k: k < 11234567
        int resp = 11234567;

        // loop que faz a busca pelo menor intervalo
        // condição de parada: quando tiver um intervalo
        // em que alguma palavra não aparece
        // para cada extremo esquerdo de um intervalo,
        // busca o extremo direito tal que esse seja a posição
        // da última palavra encontrada (quando hits = words.size())
        while(hits >= words.size()){
        	hits = 0;
            counter++;
        	
            // percorro o texto a partir do extremo esquerdo que estou
            // analizando (ini) até encontrar k palavras diferentes
            for(int curr = ini; curr < textArray.length; curr++){
                String cur_word = textArray[curr];
                if(words.contains(cur_word)){
                    if(words.get(cur_word) == 0) hits++;
                    words.put(cur_word, words.get(cur_word)+1);
                }
                if(hits == words.size()){
                    fim = curr;
                    break;
                }
            }

            // minha resposta será o menor intervalo que encontrar
            // contendo todas as palavras
            resp = Math.min(resp, (fim-ini+2));
            ini++;

            // "reset" na BST para contar novamente o número de ocorrências
            for(int i=0;i<str.length;i++)
                words.put(str[i], 0);
        }

        // checa se foram dadas palavras que não estão no texto
        // e imprime o conjunto dessas palavras
        if(!(counter > 1) && words.size() > 1){
            StdOut.printf("Set of words not in the text: { ");
            for(String s : words.keys()){
                if(words.get(s)==0){
                    printed++;
                    if(printed == (words.size()-hits)){
                        StdOut.printf("%s }\n", s);
                        break;
                    }
                    else StdOut.printf("%s, ", s);
                }
            }
        }

        else
            StdOut.println("Number of words in interval: " + (resp));
	}
}