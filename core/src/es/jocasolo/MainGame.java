package es.jocasolo;

import com.badlogic.gdx.Game;

import es.jocasolo.path2figure.MyStage;

public class MainGame extends Game {
	
	@Override
	public void create () {
		this.setScreen(new MyStage());
	}
	
}
