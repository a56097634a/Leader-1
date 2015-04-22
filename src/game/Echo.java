package game;
import java.util.ArrayList;
public class Echo extends GameObject{
	static int burstLength = 150;
	static float foodRadius = 10;
	int ttl=0;
	
	ArrayList<Echo> newEcho;
	Echo(Sketch s, float ix, float iy){
		sketch=s;
		x=ix;
		y=iy;
		ttl=burstLength;
		radius = Sketch.map(ttl, burstLength, 0, foodRadius, 50 * foodRadius);
		color = sketch.color(60, 99, 99);
		//newEcho = new ArrayList<Echo>();
	}
	
	public boolean update(){
		radius = Sketch.map(ttl, burstLength, 0, foodRadius, 50 * foodRadius);
		for(int i = 0; i < sketch.world.contents.size(); i++){
			GameObject other = sketch.world.contents.get(i);
			float dist = Sketch.dist(other.x, other.y, x, y);
			if(other != this && other instanceof Food &&  dist <= radius + 1 && dist >= radius - 1){
				//trigger a echo at that point
				Burst rb = new Burst(sketch, other.x, other.y, other.color, foodRadius);
				
				sketch.world.contents.add(rb);
			}
		}
		
//		for(int i = 0; i < newEcho.size(); i++){
//			sketch.world.contents.add(newEcho.get(i));
//		}
		
		return --ttl > 0;
	}
	
	public void draw(WorldView camera){
		float alpha = Sketch.map(ttl, burstLength, 0, 255, 0);
	    sketch.noFill();
	    sketch.stroke(color, alpha);
	    sketch.strokeWeight(4);
	    sketch.ellipse(camera.screenX(x), camera.screenY(y),
				camera.scale * radius * 2, camera.scale * radius * 2);
	}
}
