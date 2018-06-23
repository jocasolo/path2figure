package es.jocasolo.path2figure;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class MyStage extends Stage implements Screen, Disposable {
	
	private Box2DDebugRenderer debugRenderer;
	private World world;
	
	private ShapeRenderer renderer;
	private MyInputProcessor input;

	public MyStage() {
		super(new StretchViewport(Gdx.graphics.getWidth()/100, Gdx.graphics.getHeight()/100,
				new OrthographicCamera()));
	}

	@Override
	public void show() {
		
		// Create Box2D world
		world = new World(new Vector2(0, -10), true); 
		debugRenderer = new Box2DDebugRenderer();
		
		BoxBodyBuilder builder = new BoxBodyBuilder(world);
		builder.createStaticSquare(10f, 0.1f, 0, 0);
		builder.createDynamicCircle(0.5f, 0.5f, 0.5f, 0.2f, 1, 1, false);
		
		renderer = new ShapeRenderer();
		input = new MyInputProcessor(world, getCamera());
		Gdx.input.setInputProcessor(input);
		
	}
	@Override
	public void act(float delta) {
		// Box2D step
		world.step(1/240f, 6, 2);
		
		super.act(delta);
	}
	
	@Override
	public void render(float delta) {
		// Clean the screen
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		this.act();
		
		// Box2D step
		debugRenderer.render(world, getCamera().combined);
		
		// Draw points
		renderer.setColor(0, 1, 0, 1);
		renderer.begin(ShapeType.Filled);
		for(Vector2 point : input.getScreenPoints())
			renderer.circle(point.x, point.y, 1);
		renderer.end();

		// Updates and paint the stage
		super.act(delta);
		super.draw();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void dispose() {
		renderer.dispose();
		super.dispose();
	}
}
