package game;

public class Projectile extends GameObject{
	GameObject from;
	Obstacle to;
	float attackSpeed = 6f;
	float distance = 0f;
	float attackPower = 8f;
	
	Projectile(Sketch s, GameObject a, Obstacle b) {
		sketch = s;
		from = a;
		to =b;
		radius = 3f;
		x = from.x;
		y = from.y;
		color=sketch.color(255,0,0,255);
		sketch.world.contents.add(this);
	}
	
	public boolean update() {
		
		distance = Sketch.dist(x, y, to.x, to.y);
		dx = attackSpeed*(to.x-x)/distance;
		dy = attackSpeed*(to.y-y)/distance;
		x += dx;
		y += dy;
		distance = Sketch.dist(x, y, to.x, to.y);
		if(distance>to.radius)	return true;
		else {
			Obstacle tmp = to;
			tmp.obstacleLife -= attackPower;
			return false;
		}
	}
}
