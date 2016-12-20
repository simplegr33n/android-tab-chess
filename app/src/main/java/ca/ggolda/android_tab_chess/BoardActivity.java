package ca.ggolda.android_tab_chess;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

/**
 * Created by gcgol on 12/19/2016.
 */


public class BoardActivity extends AppCompatActivity {

    private ImageView c3;
    private ImageView b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        b1 = (ImageView) findViewById(R.id.img_b1);
        b1.setImageResource(R.drawable.black_pawn);

        c3 = (ImageView) findViewById(R.id.img_c3);
        c3.setImageResource(R.drawable.black_pawn);


    }
}
