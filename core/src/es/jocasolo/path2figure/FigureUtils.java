package es.jocasolo.path2figure;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector2;

public class FigureUtils {
	
	public static Vector2 squareData(List<Vector2> vertices){
		float width = vertices.get(0).x - vertices.get(1).x;
		float height = vertices.get(0).y - vertices.get(1).y;
		if(width < 0) width *= -1;
		if(height < 0) height *= -1;
		System.out.println(vertices);
		System.out.println(width);
		System.out.println(height);
		return new Vector2(width/2, height/2);
	}
	
	public static List<Vector2> validateFigure(List<Vector2> points){
		
		String moves = "";
		boolean isValid = true;
		List<Vector2> figureVertices = new ArrayList<Vector2>();
		
		float maxX = points.get(0).x;
		float maxY = points.get(0).y;
		float minX = points.get(0).x;
		float minY = points.get(0).y;

		if(points.size() > 2) {
			for(int i=2; i<points.size(); i++){
				
				// Determine the directions that the mouse has taken
				Vector2 p0 = points.get(i-2);
				Vector2 p1 = points.get(i-1);
				Vector2 p2 = points.get(i);
				if(p2.x > p1.x && p2.x > p0.x && p2.x - p0.x > 0.1f){ // Move to right
					if(moves.equals("") || (!moves.equals("") && !moves.substring(moves.length()-1).equals("R")))
						moves += "R";
				} else if (p0.x > p2.x && p1.x > p2.x && p0.x - p2.x > 0.1f){ // Move to left
					if(moves.equals("") || (!moves.equals("") && !moves.substring(moves.length()-1).equals("L")))
						moves += "L";
				} else if(p2.y > p1.y && p2.y > p0.y && p2.y - p0.y > 0.1f){ // Move to up
					if(moves.equals("") || (!moves.equals("") && !moves.substring(moves.length()-1).equals("U")))
						moves += "U";
				} else if(p0.y > p2.y && p1.y > p2.y && p0.y - p2.y > 0.1f){ // Move to down
					if(moves.equals("") || (!moves.equals("") && !moves.substring(moves.length()-1).equals("D")))
						moves += "D";
				}
				
				// Figure vertices
				if(p1.x < minX) minX = p1.x;
				if(p1.x > maxX) maxX = p1.x;
				if(p1.y < minY) minY = p1.y;
				if(p1.y > maxY) maxY = p1.y;
			}
		}
		System.out.println(moves);
		// Validate figure
		if(moves.length() == 4 ){
			if(moves.replaceAll("R", "").length() != 3)
				isValid = false;
			if(moves.replaceAll("L", "").length() != 3)
				isValid = false;
			if(moves.replaceAll("U", "").length() != 3)
				isValid = false;
			if(moves.replaceAll("D", "").length() != 3)
				isValid = false;
		} else {
			isValid = false;
		}
		
		// Return figure vertices
		if(isValid){
			figureVertices.add(new Vector2(minX, maxY));
			figureVertices.add(new Vector2(maxX, minY));
			System.out.println(figureVertices);
			return figureVertices;
		}
		
		return null;
	}
	
}
