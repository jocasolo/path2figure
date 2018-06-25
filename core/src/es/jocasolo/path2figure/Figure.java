package es.jocasolo.path2figure;

import java.util.List;

import com.badlogic.gdx.math.Vector2;

public class Figure {
	
	public static enum Type {
		CIRCLE, SQUARE;
	}
	
	private Vector2 position;
	private List<Vector2> vertices;
	private Type type;
	private float width;
	private float height;
	
	public Vector2 getPosition() {
		return position;
	}
	public void setPosition(Vector2 position) {
		this.position = position;
	}
	public List<Vector2> getVertices() {
		return vertices;
	}
	public void setVertices(List<Vector2> vertices) {
		this.vertices = vertices;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public float getWidth() {
		return width;
	}
	public void setWidth(float width) {
		this.width = width;
	}
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	public float getRadius() {
		return width/2;
	}
	public void setRadius(float radius) {
		this.width = radius*2;
	}
	@Override
	public String toString() {
		return type + ":" + position + ":" + width + ":" + height;
	}
}
