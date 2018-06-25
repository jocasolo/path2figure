package es.jocasolo.path2figure;

import java.util.List;

import com.badlogic.gdx.math.Vector2;

public class FigureUtils {
	
	public static Figure validateFigure(List<Vector2> points){
		String moves = "";
		boolean isValid = true;
		Figure.Type figureType = null;
		
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
		
		// Validate figure
		if(moves.length() == 4 ){
			figureType = Figure.Type.SQUARE;
			
			if(moves.replaceAll("R", "").length() != 3)
				isValid = false;
			if(moves.replaceAll("L", "").length() != 3)
				isValid = false;
			if(moves.replaceAll("U", "").length() != 3)
				isValid = false;
			if(moves.replaceAll("D", "").length() != 3)
				isValid = false;
			
		} else if(moves.length() == 5) {
			figureType = Figure.Type.CIRCLE;
			
			if(moves.charAt(0) == moves.charAt(4)){
				String midCharacters = moves.substring(1, 3);
				char firstCharacter = moves.charAt(0);
				if(midCharacters.indexOf(firstCharacter) != -1)
					isValid = false;
				if(moves.charAt(1) == moves.charAt(2) || moves.charAt(1) == moves.charAt(3) || moves.charAt(2) == moves.charAt(3))
					isValid = false;
			}
			
		}else {
			isValid = false;
		}
		
		// Return figure vertices
		Figure figure = null;
		if(isValid)
			figure = generateFigure(new Vector2(minX, maxY), new Vector2(maxX, minY), figureType);
		
		return figure;
	}
	
	private static Figure generateFigure(Vector2 v1, Vector2 v2, Figure.Type type){
		Figure figure = new Figure();
		figure.setType(type);
		figure.setWidth(Math.abs(v1.x - v2.x));
		figure.setHeight(Math.abs(v1.y - v2.y));
		figure.setPosition(new Vector2(v1.x + figure.getWidth()/2, v1.y - figure.getHeight()/2));
		//System.out.println(figure);
		return figure;
	}
	
}
