package marvel.android.raindrops;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

class MainMenuScreen implements Screen {
    final Raindrop drop;
    OrthographicCamera camera;

    public MainMenuScreen(final Raindrop drop) {
        this.drop = drop;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        drop.batch.setProjectionMatrix(camera.combined);
        drop.batch.begin();
        drop.font.draw(drop.batch, "Tap to start playing!", 100, 150);
        drop.batch.end();

        if (Gdx.input.isTouched()) {
            drop.setScreen(new MyGame(drop));
            dispose();
        }

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
    }
}
