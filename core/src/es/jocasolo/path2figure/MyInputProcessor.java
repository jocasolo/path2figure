package es.jocasolo.path2figure;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;

public class MyInputProcessor implements InputProcessor {
	
	private List<Vector2> draggedPoints = new ArrayList<Vector2>();
	private List<Vector2> screenPoints = new ArrayList<Vector2>();
	private BoxBodyBuilder builder;
	private Camera camera;
	
	public MyInputProcessor(World world, Camera camera) {
		this.builder = new BoxBodyBuilder(world);
		this.camera = camera;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		draggedPoints.clear();
		screenPoints.clear();
		
		final Vector3 tmp = new Vector3();
		camera.unproject(tmp.set(screenX, screenY, 0));
		draggedPoints.add(new Vector2(tmp.x, tmp.y));
		screenPoints.add(new Vector2(screenX, Gdx.graphics.getHeight()-screenY));
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		System.out.println(draggedPoints.size());
		// Validate figure
		if(!draggedPoints.isEmpty()) {
			List<Vector2> figureVertices = FigureUtils.validateFigure(draggedPoints);
			if(figureVertices != null){
				final Vector2 data = FigureUtils.squareData(figureVertices);
				builder.createDynamicSquare(0.5f, 0.5f, 0.5f, data.x, data.y, figureVertices.get(0).x+data.x/2, figureVertices.get(0).y-data.y/2, false);
				draggedPoints.clear();
			}
		}
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		if(draggedPoints.size()>0){
			final Vector3 tmp = new Vector3();
			camera.unproject(tmp.set(screenX, screenY, 0));
			
			final Vector2 lastPoint = draggedPoints.get(draggedPoints.size()-1);
			float deltaX = lastPoint.x - tmp.x;
			float deltaY = lastPoint.y - tmp.y;
			if(deltaX < 0) deltaX *= -1;
			if(deltaY < 0) deltaY *= -1;
			if(deltaX > 0.1f || deltaY > 0.1f){
				draggedPoints.add(new Vector2(tmp.x, tmp.y));
				screenPoints.add(new Vector2(screenX, Gdx.graphics.getHeight()-screenY));
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

	public List<Vector2> getDraggedPoints() {
		return draggedPoints;
	}

	public void setDraggedPoints(List<Vector2> draggedPoints) {
		this.draggedPoints = draggedPoints;
	}

	public List<Vector2> getScreenPoints() {
		return screenPoints;
	}

	public void setScreenPoints(List<Vector2> screenPoints) {
		this.screenPoints = screenPoints;
	}
	
}
