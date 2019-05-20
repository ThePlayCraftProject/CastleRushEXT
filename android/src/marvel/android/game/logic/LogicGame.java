package marvel.android.game.logic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.badlogic.gdx.math.MathUtils;

import org.xml.sax.helpers.LocatorImpl;

import marvel.android.game.R;

public class LogicGame extends AppCompatActivity {

    private Item[] items = new Item[3];
    private int[] select = new int[3];
    private int lvl = 1;
    private int errors = 0;
    ImageView[] iv;
    TextView[] tv;
    ImageButton[] a;
    ImageButton[] c;
    ImageButton[] r;
    int[] resources;
    MySurface ms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logic_game);
        ms = findViewById(R.id.game);
        iv = new ImageView[]{findViewById(R.id.imageView), findViewById(R.id.imageView2), findViewById(R.id.imageView3)};
        tv = new TextView[]{findViewById(R.id.res1), findViewById(R.id.res2), findViewById(R.id.res3)};
        a = new ImageButton[]{findViewById(R.id.imageButton4), findViewById(R.id.plus2), findViewById(R.id.plus3)};
        c = new ImageButton[]{findViewById(R.id.imageButton5), findViewById(R.id.submit2), findViewById(R.id.submit3)};
        r = new ImageButton[]{findViewById(R.id.minus), findViewById(R.id.minus2), findViewById(R.id.minus3)};
        resources = new int[]{R.drawable.o1, R.drawable.o2, R.drawable.o3};
        select = Item.Factory.create3Id(resources);
        ms.preset(this);
    }

    public void start() {
        ms.sprites.clear();
        for (int i = 0; i < items.length; i++) {
            items[i] = new Item(this, iv[i], resources[select[i]], tv[i], (int)((lvl+1)*MathUtils.random(0f,1f)));
            items[i].setAddButton(a[i]);
            items[i].setCheckButton(c[i]);
            items[i].setRemoveButton(r[i]);
            ms.sprites.add(new Sprite(ms, resources[select[i]], 100));
        }
    }

    public void nextLevel() {
        lvl++;
        Toast.makeText(this,"Level "+lvl, Toast.LENGTH_LONG).show();
        for (Item i : items) {
            if (i != null) {
                i.dispose();
            }
        }
        select = Item.Factory.create3Id(resources);
        ms.sprites.clear();
        for (int i = 0; i < items.length; i++) {
            items[i] = new Item(this, iv[i], resources[select[i]], tv[i], (int)((lvl+1)*MathUtils.random(0f,1f)));
            items[i].setAddButton(a[i]);
            items[i].setCheckButton(c[i]);
            items[i].setRemoveButton(r[i]);
            items[i].refresh();
            ms.sprites.add(new Sprite(ms, resources[select[i]], 10));
        }
    }
}

class Item {
    private static int answers = 0;
    ImageView icon;
    TextView counter;
    int count = 0;
    int right;
    boolean finished = false;

    private LogicGame lg;
    private ImageButton ib, ib2, ib3;

    public Item(LogicGame lg, ImageView iv, int r, TextView tv, int right) {
        this.lg = lg;
        icon = iv;
        counter = tv;
        this.right = right;

        iv.setImageResource(r);
    }

    public boolean isRight() {
        return (right == count);
    }
    public boolean isFinished() {
        return finished;
    }

    public void setAddButton(ImageButton ib) {
        this.ib = ib;
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (finished) return;
                count++;
                refresh();
            }
        });
    }

    public void setCheckButton(ImageButton ib) {
        ib2 = ib;
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (finished) return;
                if (count == right) {
                    answers++;
                    counter.setText("+");
                    finished = true;
                    if (answers >= 3) {
                        answers = 0;
                        lg.nextLevel();
                    }
                }
                else {
                    counter.setText("-");
                    count = 0;
                }
            }
        });
    }
    public void setRemoveButton(ImageButton ib) {
        ib3 = ib;
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (finished) return;
                if (count <= 0) return;
                count--;
                refresh();
            }
        });
    }

    public void dispose() {
        if (ib != null) ib.setOnClickListener(null);
        if (ib2 != null) ib2.setOnClickListener(null);
        if (ib3 != null) ib3.setOnClickListener(null);
    }

    public void refresh() {
        counter.setText("= "+count);
    }

    public static class Factory {
        public static int[] create3Id(int[] r) {
            if (r.length < 3) return null;
            int[] i = new int[3];
            i[0] = MathUtils.random(0, r.length-1);
            i[1] = MathUtils.random(0, r.length-2);
            if (i[1] == i[0]) i[1]+=1;
            i[2] = MathUtils.random(0, r.length-3);
            while (i[2] == i[1] || i[2] == i[0]) i[2]+=1;
            return i;
        }
    }
}
