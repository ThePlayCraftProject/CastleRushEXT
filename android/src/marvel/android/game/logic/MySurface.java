package marvel.android.game.logic;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TimeUtils;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class MySurface extends SurfaceView implements SurfaceHolder.Callback {
    public final Array<Sprite> sprites = new Array<>();
    DrawThread dt;
    private LogicGame lg;
    private int w, h;

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        lg.start();
        dt = new DrawThread(this, holder);
        dt.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    public MySurface(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);
    }


    public void preset(LogicGame lg) {
        this.lg = lg;
    }

}
class DrawThread extends Thread {
    MySurface ms;
    SurfaceHolder sh;
    long lastTime = System.currentTimeMillis();
    volatile boolean running = true;

    public DrawThread(MySurface ms, SurfaceHolder sh) {
        this.ms = ms;
        this.sh = sh;
    }

    public void requestStop() {
        running = false;
    }

    private void update(float dt) {
        for (Sprite sprite : ms.sprites) {
            sprite.update(dt);
        }
    }
    private void draw(Canvas c) {
        for (Sprite sprite : ms.sprites) {
            sprite.draw(c);
        }
    }

    @Override
    public void run() {
        while (running) {
            long thistime = System.currentTimeMillis();
            float dt = (thistime - lastTime)/100f;
            lastTime = thistime;
            update(dt);
            Canvas c = sh.lockCanvas();
            draw(c);
            sh.unlockCanvasAndPost(c);
        }
    }
}

class Sprite {
    private Bitmap texture;
    private int width = 64;
    private int height = 64;
    private MySurface ms;
    private Vector2 position = new Vector2();
    private Vector2 velocity = new Vector2();
    private Vector2 velocityT = new Vector2();

    public Sprite(MySurface ms, int r, float vx) {
        this.ms = ms;
        texture = BitmapFactory.decodeResource(ms.getResources(), r);
        float q = MathUtils.random(0.5f,2f);
        width*=q;
        height*=q;
        texture = Bitmap.createScaledBitmap(texture, width, height, false);
        position.set(ms.getHolder().getSurfaceFrame().width()/2f+(float)Math.random()*5f, ms.getHolder().getSurfaceFrame().height()/2f);
        velocity.set((float)(Math.random()*2-1), (float)(Math.random()*2-1));
        velocity.scl(1);
        velocity.scl(vx);
    }
    public void update(float dt) {
        velocityT = new Vector2(velocity);
        velocityT.scl(dt);
        position.add(velocityT);
    }
    public void draw(Canvas c) {
        c.drawColor(Color.BLACK);
        c.drawBitmap(texture, position.x, position.y, null);
    }
}
