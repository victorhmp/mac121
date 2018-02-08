/******************************************************************************
 *
 * MAC0121 - Algoritmos e Estruturas de Dados I
 * Aluno: Victor Hugo Miranda Pinto
 * Numero USP: 10297720
 * Tarefa: Variante do Creative Ex. 3.1.33 (Color study)
 * 
 * Baseado em ColorStudy.java
 *
 * DECLARO QUE SOU O ÚNICO AUTOR E RESPONSÁVEL POR ESTE PROGRAMA.  TODAS AS 
 * PARTES DO PROGRAMA, EXCETO AS QUE SÃO BASEADAS EM MATERIAL FORNECIDO  
 * PELO PROFESSOR OU COPIADAS DO LIVRO OU DAS BIBLIOTECAS DE SEDGEWICK & WAYNE, 
 * FORAM DESENVOLVIDAS POR MIM.  DECLARO TAMBÉM QUE SOU RESPONSÁVEL POR TODAS 
 * AS CÓPIAS DESTE PROGRAMA E QUE NÃO DISTRIBUÍ NEM FACILITEI A DISTRIBUIÇÃO
 * DE CÓPIAS DESTA PROGRAMA.
 *
 ******************************************************************************/

import java.awt.Color;

public class ColorStudyH {  
    public static void main(String[] args) {
        StdDraw.setXscale(-1, 16);
        StdDraw.setYscale(-1, 16);

        float h1 = Float.parseFloat(args[0]);
        float h2 = Float.parseFloat(args[1]);

        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++){
                int gray = i*16 + (15-j);
                
                // generates colors
                float t = (float)Math.sqrt(i*i + j*j)/(float)(Math.sqrt(15*15 + 15*15));
                float h = (1-t)*h1 + t*h2;
                
                // main squares
                Color c1 = Color.getHSBColor(h, .95f, .95f);
                StdDraw.setPenColor(c1);
                StdDraw.filledSquare(i, j, 0.5);

                // gray squares
                Color c2 = new Color(gray, gray, gray);
                StdDraw.setPenColor(c2);
                StdDraw.filledSquare(i, j, 0.25);
            }
        }
        StdDraw.save("study" + "-" + h1 + "-" + h2 + ".png");
    }
}