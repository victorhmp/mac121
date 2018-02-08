/******************************************************************************
 *
 * MAC0121 - Algoritmos e Estruturas de Dados I
 * Aluno: Victor Hugo Miranda Pinto
 * Numero USP: 10297720
 * Tarefa: Variante do Web Exercise 1.5.39 (Elastic collisions)
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

public class ColoredBallOO {
	private Vector position;
	private Vector velocity;
	private final double radius;
	private final Color col;

	public ColoredBallOO(Vector p, Vector v, double r, Color c){
		position = p;
		velocity = v;
		radius = r;
		col = c;
	}
	// method to get ball's position
	public Vector pos(){
		return position;
	}
	// method to get ball's velocity
	public Vector vel(){
		return velocity;
	}
	// method to become ball's radius
	public double radius(){
		return radius;
	}
	// method to set the ball's velocity
	public void setVel(Vector v){
		velocity = v;
		return;
	}
	// method to update ball's position after a dt interval of time
	public void updatePosition(double dt){
		position = position.plus(velocity.scale(dt));
		return;
	}
	// checks for collisions with the walls
	public void treatWalls(double size, double dt){
		Vector q = position.plus(velocity.scale(dt));
		// handles the x-axys
		if(q.cartesian(0)<0 || q.cartesian(0)>size){
			double[] auxX = {-velocity.cartesian(0), velocity.cartesian(1)};
			Vector aux = new Vector(auxX);
			velocity = aux;
		}
		// handles the y-axys
		if(q.cartesian(1) < 0 || q.cartesian(1) > size){
			double[] auxY = {velocity.cartesian(0), -velocity.cartesian(1)};
			Vector aux = new Vector(auxY);
			velocity = aux;
		}

		return;
	}
	// makes the ball move acording to it's position
	public void move(double size, double dt){
		// check for collision
		treatWalls(size, dt);
		// move
		updatePosition(dt);

		return;
	}
	// draws the ball on StdDraw
	public void draw(){
		StdDraw.setPenColor(col);
		StdDraw.filledCircle(position.cartesian(0), position.cartesian(1), radius);
	}

}