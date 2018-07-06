package project;

import java.awt.Graphics;
import java.awt.Point;
import java.util.Vector;

public class Reta {
	Point p1;
	Point p2;
	
	public Reta(Point inicio, Point fim){
		p1 = inicio;
		p2 = fim;
	}
	
	public Vector draw(){
		int x, y, erro, deltaX, deltaY;
		Vector pontos = new Vector();
		erro = 0;
		x = p1.x;
		y = p1.y;
		deltaX = p2.x - p1.x;
		deltaY = p2.y - p1.y;
 
		if((Math.abs(deltaY)>=Math.abs(deltaX) && p1.y>p2.y)
			||(Math.abs(deltaY)<Math.abs(deltaX) && deltaY<0)){
 
			x = p2.x;
			y = p2.y;
			deltaX = p1.x-p2.x;
			deltaY = p1.y-p2.y;
		}
		//p1.draw(g);
		pontos.add(p1);
		if(deltaX>=0){
			if(Math.abs(deltaX)>=Math.abs(deltaY)){
				for(int i=1;i<Math.abs(deltaX);i++){
					if(erro<0){
						x++;
						//new Ponto(x,y).draw(g);
						pontos.add(new Point(x,y));
						erro += deltaY;
					}else{
						x++;
						y++;
						//new Ponto(x,y).draw(g);
						pontos.add(new Point(x,y));
						erro += deltaY - deltaX;
					}
				}
			}else{
				for(int i=1;i<Math.abs(deltaY);i++){
					if(erro<0){
						x++;
						y++;
						//new Ponto(x,y).draw(g);
						pontos.add(new Point(x,y));
						erro += deltaY - deltaX;						
					}else{
						y++;
						//new Ponto(x,y).draw(g);
						pontos.add(new Point(x,y));
						erro -= deltaX;
					}
				}
			}
		}else{ // deltaX<0
			if(Math.abs(deltaX)>=Math.abs(deltaY)){
				for(int i=1;i<Math.abs(deltaX);i++){
					if(erro<0){
						x--;
						//new Ponto(x,y).draw(g);
						pontos.add(new Point(x,y));
						erro += deltaY;
					}else{
						x--;
						y++;
						//new Ponto(x,y).draw(g);
						pontos.add(new Point(x,y));
						erro += deltaY + deltaX;
					}
				}
			}else{
				for(int i=1;i<Math.abs(deltaY);i++){
					if(erro<0){
						x--;
						y++;
						//new Ponto(x,y).draw(g);
						pontos.add(new Point(x,y));
						erro += deltaY + deltaX;						
					}else{
						y++;
						//new Ponto(x,y).draw(g);
						pontos.add(new Point(x,y));
						erro += deltaX;
					}
				}
			}
		}
		//p2.draw(g);
		pontos.add(p2);
		
		if(p2.y<p1.y){
			int total = pontos.size();
			Vector temp = new Vector();
			for(int i = total-1; i >=0; i--){
				temp.add(pontos.elementAt(i));
			}
			pontos = temp;
		}
		
		return pontos;
	}
}
