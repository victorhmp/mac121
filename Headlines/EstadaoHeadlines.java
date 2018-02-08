/******************************************************************************
 *
 * MAC0121 - Algoritmos e Estruturas de Dados I
 * Aluno: Victor Hugo Miranda Pinto
 * Numero USP: 10297720
 * Tarefa: Manchetes
 * 
 * Baseado em StockQuote.java
 *
 * DECLARO QUE SOU O ÚNICO AUTOR E RESPONSÁVEL POR ESTE PROGRAMA.  TODAS AS 
 * PARTES DO PROGRAMA, EXCETO AS QUE SÃO BASEADAS EM MATERIAL FORNECIDO  
 * PELO PROFESSOR OU COPIADAS DO LIVRO OU DAS BIBLIOTECAS DE SEDGEWICK & WAYNE, 
 * FORAM DESENVOLVIDAS POR MIM.  DECLARO TAMBÉM QUE SOU RESPONSÁVEL POR TODAS 
 * AS CÓPIAS DESTE PROGRAMA E QUE NÃO DISTRIBUÍ NEM FACILITEI A DISTRIBUIÇÃO
 * DE CÓPIAS DESTA PROGRAMA.
 *
 ******************************************************************************/


import org.apache.commons.lang3.StringEscapeUtils;

public class EstadaoHeadlines{
	
	// gets estadao.com.br HTML
	private static String readHTML() {
        In page = new In("http://www.estadao.com.br");
        String html = page.readAll();
        if (html.contains("<title></title>")) return null;
        else return html;
    }

    // global variable to keep track of where my function is checking
    public static int next_p = 0;

    // gets each headline under <h3...> </h3>
    public static String manchete(String html, int idx){
        // this three lines cover the possible variations of <h3...></h3>
    	int p = html.indexOf("<h3", idx);
    	int from = html.indexOf(">",p);
    	int to = html.indexOf("</h3>", from);

    	next_p = to;

    	String man = "";

    	if(p != -1 && from != -1 && to !=- 1) 
    		man = html.substring(from+1, to);

    	// returns clean headline
    	return StringEscapeUtils.unescapeHtml4(man);
    }


    public static void main(String[] args){
    	String html = readHTML();

        // loop that prints out headlines
    	while(next_p < html.length()){
    		String man = manchete(html, next_p);
    		if(man.length()<=2) break;
    		StdOut.println(man);
    	}

        // done
    }
}