package marvel.android.game;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import marvel.android.castleattackers.game.try2.castleattackers.CastleInvaders;
import marvel.android.flappygame.FlappyGame;
import marvel.android.raindrops.Drop;

public class AndroidLauncher extends AndroidApplication {
    public volatile static Boolean on = false;
    public static final Object monitor = new Object();
    @Override
    protected synchronized void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        //cfg.useGL20 = true;
        cfg.useAccelerometer = false;
        cfg.useCompass = false;
        cfg.useWakelock = true;

        Intent i = getIntent();
        int type = i.getExtras().getInt("type");
        synchronized (monitor) {
            if (!AndroidLauncher.on) {
                switch (type) {
                    case 0: initialize(new CastleInvaders(), cfg); return;
                    case 2: initialize(new Drop(), cfg); return;
                    default: setResult(RESULT_CANCELED);
                }
            }
            AndroidLauncher.on = true;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        on = false;
    }
}
