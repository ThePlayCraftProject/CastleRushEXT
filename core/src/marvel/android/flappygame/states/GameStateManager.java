package marvel.android.flappygame.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

import java.util.Stack;

import marvel.android.flappygame.FlappyGame;

public class GameStateManager implements Disposable {
    private Stack<State> states;

    public int score, max_score;
    public Sound death;

    public GameStateManager() {
        max_score = FlappyGame.preferences.getInteger("max_score", 0);
        score = 0;
        states = new Stack<State>();
        death = Gdx.audio.newSound(Gdx.files.internal("death.mp3"));
    }

    public void push(State state) {
        states.push(state);
    }

    public void pop(State state) {
        states.pop().dispose();
    }

    public void set(State state) {
        states.pop().dispose();
        states.push(state);
    }

    public void update(float dt) {
        states.peek().update(dt);
    }
    public void render(SpriteBatch sb) {
        states.peek().render(sb);
    }

    @Override
    public void dispose() {
        death.dispose();
    }
}