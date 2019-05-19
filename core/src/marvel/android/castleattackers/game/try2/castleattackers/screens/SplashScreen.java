package marvel.android.castleattackers.game.try2.castleattackers.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import marvel.android.castleattackers.game.try2.castleattackers.Assets;
import marvel.android.castleattackers.game.try2.castleattackers.CastleAttackers;


public class SplashScreen implements Screen {
	final CastleAttackers game;

	OrthographicCamera camera;
	Rectangle playBounds;
	Rectangle exitBounds;
	Vector3 touchPoint;

	public SplashScreen(final CastleAttackers gam) {
		game = gam;

		Assets.load(game);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1280, 768);
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);

	}
	
	@Override
	public void render(float delta) {
		game.batch.begin();
		game.batch.draw(Assets.splashscreen,0,0);
		game.batch.end();
		if(Gdx.input.justTouched())
		game.setScreen(new MainMenuScreen(game, null));

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
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
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
