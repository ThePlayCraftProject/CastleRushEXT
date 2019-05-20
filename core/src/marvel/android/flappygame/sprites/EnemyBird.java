package marvel.android.flappygame.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Disposable;

public class EnemyBird implements Disposable {

    public static final int MOVEMENT = 100;

    private Vector3 position;
    private Vector3 velocity;

    private Texture enemyBird;
    private Rectangle bounds;
    private Animation enemyBirdAnim;

    public Rectangle getBounds() {
        return bounds;
    }

    public EnemyBird(int x, int y, int vx) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(-vx, 0, 0);
        enemyBird = new Texture("enemybirdanimation.png");
        enemyBirdAnim = new Animation(new TextureRegion(enemyBird), 3, 0.5f);
        bounds = new Rectangle(x, y, enemyBird.getWidth()/3, enemyBird.getHeight());
    }

    public void reposition(int x, int y) {
        position.x = x;
        position.y = y;

        bounds.setPosition(position.x, position.y);
    }

    public void update(float dt) {
        enemyBirdAnim.update(dt);
        velocity.scl(dt);
        position.add(velocity);
        velocity.scl(1/dt);

        bounds.setPosition(position.x, position.y);
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getEnemyBird() {
        return enemyBirdAnim.getFrame();
    }

    @Override
    public void dispose() {
        enemyBird.dispose();
    }
}
