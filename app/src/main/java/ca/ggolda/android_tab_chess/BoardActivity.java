package ca.ggolda.android_tab_chess;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

/**
 * Created by gcgol on 12/19/2016.
 */


public class BoardActivity extends AppCompatActivity {

    private ImageView a1;
    private ImageView a2;
    private ImageView a3;
    private ImageView a4;
    private ImageView a5;
    private ImageView a6;
    private ImageView a7;
    private ImageView a8;

    private ImageView b1;
    private ImageView b2;
    private ImageView b3;
    private ImageView b4;
    private ImageView b5;
    private ImageView b6;
    private ImageView b7;
    private ImageView b8;

    private ImageView c1;
    private ImageView c2;
    private ImageView c3;
    private ImageView c4;
    private ImageView c5;
    private ImageView c6;
    private ImageView c7;
    private ImageView c8;

    private ImageView d1;
    private ImageView d2;
    private ImageView d3;
    private ImageView d4;
    private ImageView d5;
    private ImageView d6;
    private ImageView d7;
    private ImageView d8;

    private ImageView e1;
    private ImageView e2;
    private ImageView e3;
    private ImageView e4;
    private ImageView e5;
    private ImageView e6;
    private ImageView e7;
    private ImageView e8;

    private ImageView f1;
    private ImageView f2;
    private ImageView f3;
    private ImageView f4;
    private ImageView f5;
    private ImageView f6;
    private ImageView f7;
    private ImageView f8;

    private ImageView g1;
    private ImageView g2;
    private ImageView g3;
    private ImageView g4;
    private ImageView g5;
    private ImageView g6;
    private ImageView g7;
    private ImageView g8;

    private ImageView h1;
    private ImageView h2;
    private ImageView h3;
    private ImageView h4;
    private ImageView h5;
    private ImageView h6;
    private ImageView h7;
    private ImageView h8;

    private TextView logs;

    private String gamesetString;

    private List<String> gamesetList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);



        logs = (TextView) findViewById(R.id.log);

        a1 = (ImageView) findViewById(R.id.img_a1);
        a2 = (ImageView) findViewById(R.id.img_a2);
        a3 = (ImageView) findViewById(R.id.img_a3);
        a4 = (ImageView) findViewById(R.id.img_a4);
        a5 = (ImageView) findViewById(R.id.img_a5);
        a6 = (ImageView) findViewById(R.id.img_a6);
        a7 = (ImageView) findViewById(R.id.img_a7);
        a8 = (ImageView) findViewById(R.id.img_a8);

        b1 = (ImageView) findViewById(R.id.img_b1);
        b2 = (ImageView) findViewById(R.id.img_b2);
        b3 = (ImageView) findViewById(R.id.img_b3);
        b4 = (ImageView) findViewById(R.id.img_b4);
        b5 = (ImageView) findViewById(R.id.img_b5);
        b6 = (ImageView) findViewById(R.id.img_b6);
        b7 = (ImageView) findViewById(R.id.img_b7);
        b8 = (ImageView) findViewById(R.id.img_b8);

        c1 = (ImageView) findViewById(R.id.img_c1);
        c2 = (ImageView) findViewById(R.id.img_c2);
        c3 = (ImageView) findViewById(R.id.img_c3);
        c4 = (ImageView) findViewById(R.id.img_c4);
        c5 = (ImageView) findViewById(R.id.img_c5);
        c6 = (ImageView) findViewById(R.id.img_c6);
        c7 = (ImageView) findViewById(R.id.img_c7);
        c8 = (ImageView) findViewById(R.id.img_c8);

        d1 = (ImageView) findViewById(R.id.img_d1);
        d2 = (ImageView) findViewById(R.id.img_d2);
        d3 = (ImageView) findViewById(R.id.img_d3);
        d4 = (ImageView) findViewById(R.id.img_d4);
        d5 = (ImageView) findViewById(R.id.img_d5);
        d6 = (ImageView) findViewById(R.id.img_d6);
        d7 = (ImageView) findViewById(R.id.img_d7);
        d8 = (ImageView) findViewById(R.id.img_d8);

        e1 = (ImageView) findViewById(R.id.img_e1);
        e2 = (ImageView) findViewById(R.id.img_e2);
        e3 = (ImageView) findViewById(R.id.img_e3);
        e4 = (ImageView) findViewById(R.id.img_e4);
        e5 = (ImageView) findViewById(R.id.img_e5);
        e6 = (ImageView) findViewById(R.id.img_e6);
        e7 = (ImageView) findViewById(R.id.img_e7);
        e8 = (ImageView) findViewById(R.id.img_e8);

        f1 = (ImageView) findViewById(R.id.img_f1);
        f2 = (ImageView) findViewById(R.id.img_f2);
        f3 = (ImageView) findViewById(R.id.img_f3);
        f4 = (ImageView) findViewById(R.id.img_f4);
        f5 = (ImageView) findViewById(R.id.img_f5);
        f6 = (ImageView) findViewById(R.id.img_f6);
        f7 = (ImageView) findViewById(R.id.img_f7);
        f8 = (ImageView) findViewById(R.id.img_f8);

        g1 = (ImageView) findViewById(R.id.img_g1);
        g2 = (ImageView) findViewById(R.id.img_g2);
        g3 = (ImageView) findViewById(R.id.img_g3);
        g4 = (ImageView) findViewById(R.id.img_g4);
        g5 = (ImageView) findViewById(R.id.img_g5);
        g6 = (ImageView) findViewById(R.id.img_g6);
        g7 = (ImageView) findViewById(R.id.img_g7);
        g8 = (ImageView) findViewById(R.id.img_g8);

        h1 = (ImageView) findViewById(R.id.img_h1);
        h2 = (ImageView) findViewById(R.id.img_h2);
        h3 = (ImageView) findViewById(R.id.img_h3);
        h4 = (ImageView) findViewById(R.id.img_h4);
        h5 = (ImageView) findViewById(R.id.img_h5);
        h6 = (ImageView) findViewById(R.id.img_h6);
        h7 = (ImageView) findViewById(R.id.img_h7);
        h8 = (ImageView) findViewById(R.id.img_h8);

        setBoard();
    }

    private void setBoard() {

        gamesetString = getResources().getString(R.string.new_board);

        logs.setText(gamesetString);

        gamesetList = Arrays.asList(gamesetString.split("\\s*,\\s*"));

        a1.setImageResource(getResources().getIdentifier(gamesetList.get(0) , "drawable", getPackageName()));
        returnClickable(0, a1);
        b1.setImageResource(getResources().getIdentifier(gamesetList.get(1) , "drawable", getPackageName()));
        returnClickable(1, b1);
        c1.setImageResource(getResources().getIdentifier(gamesetList.get(2) , "drawable", getPackageName()));
        returnClickable(2, c1);
        d1.setImageResource(getResources().getIdentifier(gamesetList.get(3) , "drawable", getPackageName()));
        returnClickable(3, d1);
        e1.setImageResource(getResources().getIdentifier(gamesetList.get(4) , "drawable", getPackageName()));
        returnClickable(4, e1);
        f1.setImageResource(getResources().getIdentifier(gamesetList.get(5) , "drawable", getPackageName()));
        returnClickable(5, f1);
        g1.setImageResource(getResources().getIdentifier(gamesetList.get(6) , "drawable", getPackageName()));
        returnClickable(6, g1);
        h1.setImageResource(getResources().getIdentifier(gamesetList.get(7) , "drawable", getPackageName()));
        returnClickable(7, h1);

        a2.setImageResource(getResources().getIdentifier(gamesetList.get(8) , "drawable", getPackageName()));
        returnClickable(8, a2);
        b2.setImageResource(getResources().getIdentifier(gamesetList.get(9) , "drawable", getPackageName()));
        returnClickable(9, b2);
        c2.setImageResource(getResources().getIdentifier(gamesetList.get(10) , "drawable", getPackageName()));
        returnClickable(10, c2);
        d2.setImageResource(getResources().getIdentifier(gamesetList.get(11) , "drawable", getPackageName()));
        returnClickable(11, d2);
        e2.setImageResource(getResources().getIdentifier(gamesetList.get(12) , "drawable", getPackageName()));
        returnClickable(12, e2);
        f2.setImageResource(getResources().getIdentifier(gamesetList.get(13) , "drawable", getPackageName()));
        returnClickable(13, f2);
        g2.setImageResource(getResources().getIdentifier(gamesetList.get(14) , "drawable", getPackageName()));
        returnClickable(14, g2);
        h2.setImageResource(getResources().getIdentifier(gamesetList.get(15) , "drawable", getPackageName()));
        returnClickable(15, h2);

        a3.setImageResource(getResources().getIdentifier(gamesetList.get(16) , "drawable", getPackageName()));
        returnClickable(16, a3);
        b3.setImageResource(getResources().getIdentifier(gamesetList.get(17) , "drawable", getPackageName()));
        returnClickable(17, b3);
        c3.setImageResource(getResources().getIdentifier(gamesetList.get(18) , "drawable", getPackageName()));
        returnClickable(18, c3);
        d3.setImageResource(getResources().getIdentifier(gamesetList.get(19) , "drawable", getPackageName()));
        returnClickable(19, d3);
        e3.setImageResource(getResources().getIdentifier(gamesetList.get(20) , "drawable", getPackageName()));
        returnClickable(20, e3);
        f3.setImageResource(getResources().getIdentifier(gamesetList.get(21) , "drawable", getPackageName()));
        returnClickable(21, f3);
        g3.setImageResource(getResources().getIdentifier(gamesetList.get(22) , "drawable", getPackageName()));
        returnClickable(22, g3);
        h3.setImageResource(getResources().getIdentifier(gamesetList.get(23) , "drawable", getPackageName()));
        returnClickable(23, h3);

        a4.setImageResource(getResources().getIdentifier(gamesetList.get(24) , "drawable", getPackageName()));
        returnClickable(24, a4);
        b4.setImageResource(getResources().getIdentifier(gamesetList.get(25) , "drawable", getPackageName()));
        returnClickable(25, b4);
        c4.setImageResource(getResources().getIdentifier(gamesetList.get(26) , "drawable", getPackageName()));
        returnClickable(26, c4);
        d4.setImageResource(getResources().getIdentifier(gamesetList.get(27) , "drawable", getPackageName()));
        returnClickable(27, d4);
        e4.setImageResource(getResources().getIdentifier(gamesetList.get(28) , "drawable", getPackageName()));
        returnClickable(28, e4);
        f4.setImageResource(getResources().getIdentifier(gamesetList.get(29) , "drawable", getPackageName()));
        returnClickable(29, f4);
        g4.setImageResource(getResources().getIdentifier(gamesetList.get(30) , "drawable", getPackageName()));
        returnClickable(30, g4);
        h4.setImageResource(getResources().getIdentifier(gamesetList.get(31) , "drawable", getPackageName()));
        returnClickable(31, h4);

        a5.setImageResource(getResources().getIdentifier(gamesetList.get(32) , "drawable", getPackageName()));
        returnClickable(32, a5);
        b5.setImageResource(getResources().getIdentifier(gamesetList.get(33) , "drawable", getPackageName()));
        returnClickable(33, b5);
        c5.setImageResource(getResources().getIdentifier(gamesetList.get(34) , "drawable", getPackageName()));
        returnClickable(34, c5);
        d5.setImageResource(getResources().getIdentifier(gamesetList.get(35) , "drawable", getPackageName()));
        returnClickable(35, d5);
        e5.setImageResource(getResources().getIdentifier(gamesetList.get(36) , "drawable", getPackageName()));
        returnClickable(36, e5);
        f5.setImageResource(getResources().getIdentifier(gamesetList.get(37) , "drawable", getPackageName()));
        returnClickable(37, f5);
        g5.setImageResource(getResources().getIdentifier(gamesetList.get(38) , "drawable", getPackageName()));
        returnClickable(38, g5);
        h5.setImageResource(getResources().getIdentifier(gamesetList.get(39) , "drawable", getPackageName()));
        returnClickable(39, h5);

        a6.setImageResource(getResources().getIdentifier(gamesetList.get(40) , "drawable", getPackageName()));
        returnClickable(40, a6);
        b6.setImageResource(getResources().getIdentifier(gamesetList.get(41) , "drawable", getPackageName()));
        returnClickable(41, b6);
        c6.setImageResource(getResources().getIdentifier(gamesetList.get(42) , "drawable", getPackageName()));
        returnClickable(42, c6);
        d6.setImageResource(getResources().getIdentifier(gamesetList.get(43) , "drawable", getPackageName()));
        returnClickable(43, d6);
        e6.setImageResource(getResources().getIdentifier(gamesetList.get(44) , "drawable", getPackageName()));
        returnClickable(44, e6);
        f6.setImageResource(getResources().getIdentifier(gamesetList.get(45) , "drawable", getPackageName()));
        returnClickable(45, f6);
        g6.setImageResource(getResources().getIdentifier(gamesetList.get(46) , "drawable", getPackageName()));
        returnClickable(46, g6);
        h6.setImageResource(getResources().getIdentifier(gamesetList.get(47) , "drawable", getPackageName()));
        returnClickable(47, h6);

        a7.setImageResource(getResources().getIdentifier(gamesetList.get(48) , "drawable", getPackageName()));
        returnClickable(48, a7);
        b7.setImageResource(getResources().getIdentifier(gamesetList.get(49) , "drawable", getPackageName()));
        returnClickable(49, b7);
        c7.setImageResource(getResources().getIdentifier(gamesetList.get(50) , "drawable", getPackageName()));
        returnClickable(50, c7);
        d7.setImageResource(getResources().getIdentifier(gamesetList.get(51) , "drawable", getPackageName()));
        returnClickable(51, d7);
        e7.setImageResource(getResources().getIdentifier(gamesetList.get(52) , "drawable", getPackageName()));
        returnClickable(52, e7);
        f7.setImageResource(getResources().getIdentifier(gamesetList.get(53) , "drawable", getPackageName()));
        returnClickable(53, f7);
        g7.setImageResource(getResources().getIdentifier(gamesetList.get(54) , "drawable", getPackageName()));
        returnClickable(54, g7);
        h7.setImageResource(getResources().getIdentifier(gamesetList.get(55) , "drawable", getPackageName()));
        returnClickable(55, h7);

        a8.setImageResource(getResources().getIdentifier(gamesetList.get(56) , "drawable", getPackageName()));
        returnClickable(56, a8);
        b8.setImageResource(getResources().getIdentifier(gamesetList.get(57) , "drawable", getPackageName()));
        returnClickable(57, b8);
        c8.setImageResource(getResources().getIdentifier(gamesetList.get(58) , "drawable", getPackageName()));
        returnClickable(58, c8);
        d8.setImageResource(getResources().getIdentifier(gamesetList.get(59) , "drawable", getPackageName()));
        returnClickable(59, d8);
        e8.setImageResource(getResources().getIdentifier(gamesetList.get(60) , "drawable", getPackageName()));
        returnClickable(60, e8);
        f8.setImageResource(getResources().getIdentifier(gamesetList.get(61) , "drawable", getPackageName()));
        returnClickable(61, f8);
        g8.setImageResource(getResources().getIdentifier(gamesetList.get(62) , "drawable", getPackageName()));
        returnClickable(62, g8);
        h8.setImageResource(getResources().getIdentifier(gamesetList.get(63) , "drawable", getPackageName()));
        returnClickable(63, h8);
    }

    private void returnClickable(int square, ImageView squareImageView) {
        //if the first word of the piece name is white or black, their space is clickable
        //TODO: implement turns
        if ((gamesetList.get(square).split("_")[0]).equals("white") || (gamesetList.get(square).split("_")[0]).equals("black") ) {
            Log.e("EYHO", gamesetList.get(square).split("_")[0]);


            squareImageView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    v.setBackgroundColor(Color.parseColor("#FF0000"));


                }
            });

            squareImageView.setBackgroundColor(Color.parseColor("#0000FF"));
        }


    }

    private void toAlphaNumeric() {

    }

}
