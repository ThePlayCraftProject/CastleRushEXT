package marvel.android.game.messangergame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.badlogic.gdx.Game;

import marvel.android.game.AndroidLauncher;
import marvel.android.game.AndroidLauncherP;
import marvel.android.game.R;

public class GameSelector extends AppCompatActivity {
    private Boolean on = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_selector);
    }

    @Override
    protected synchronized void onResume() {
        super.onResume();
        on = false;
    }

    public synchronized void onRush(View v) {
        synchronized (on) {
            if (!on) {
                Intent i = new Intent(GameSelector.this, AndroidLauncher.class);
                i.putExtra("type", 0);
                startActivity(i);
                on = true;
            }
        }
    }
    public synchronized void onFlappy(View v) {
        synchronized (on) {
            if (!on) {
                Intent i = new Intent(GameSelector.this, AndroidLauncherP.class);
                i.putExtra("type", 1);
                startActivity(i);
                on = true;
            }
        }
    }
    public synchronized void onRainy(View v) {
            synchronized (on) {
                if (!on) {
                    Intent i = new Intent(GameSelector.this, AndroidLauncher.class);
                    i.putExtra("type", 2);
                    startActivity(i);
                    on = true;
                }
            }
    }
}
