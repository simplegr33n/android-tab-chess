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

    private int selectedSquare = 99;

    private String playerColor = "white";

    private List<String> gamesetList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);


        logs = (TextView) findViewById(R.id.log);

        // TODO: Best way to reverse image view order depending on playerColor.
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

        a1.setImageResource(getResources().getIdentifier(gamesetList.get(0), "drawable", getPackageName()));
        returnSelectable(0, a1);
        b1.setImageResource(getResources().getIdentifier(gamesetList.get(1), "drawable", getPackageName()));
        returnSelectable(1, b1);
        c1.setImageResource(getResources().getIdentifier(gamesetList.get(2), "drawable", getPackageName()));
        returnSelectable(2, c1);
        d1.setImageResource(getResources().getIdentifier(gamesetList.get(3), "drawable", getPackageName()));
        returnSelectable(3, d1);
        e1.setImageResource(getResources().getIdentifier(gamesetList.get(4), "drawable", getPackageName()));
        returnSelectable(4, e1);
        f1.setImageResource(getResources().getIdentifier(gamesetList.get(5), "drawable", getPackageName()));
        returnSelectable(5, f1);
        g1.setImageResource(getResources().getIdentifier(gamesetList.get(6), "drawable", getPackageName()));
        returnSelectable(6, g1);
        h1.setImageResource(getResources().getIdentifier(gamesetList.get(7), "drawable", getPackageName()));
        returnSelectable(7, h1);

        a2.setImageResource(getResources().getIdentifier(gamesetList.get(8), "drawable", getPackageName()));
        returnSelectable(8, a2);
        b2.setImageResource(getResources().getIdentifier(gamesetList.get(9), "drawable", getPackageName()));
        returnSelectable(9, b2);
        c2.setImageResource(getResources().getIdentifier(gamesetList.get(10), "drawable", getPackageName()));
        returnSelectable(10, c2);
        d2.setImageResource(getResources().getIdentifier(gamesetList.get(11), "drawable", getPackageName()));
        returnSelectable(11, d2);
        e2.setImageResource(getResources().getIdentifier(gamesetList.get(12), "drawable", getPackageName()));
        returnSelectable(12, e2);
        f2.setImageResource(getResources().getIdentifier(gamesetList.get(13), "drawable", getPackageName()));
        returnSelectable(13, f2);
        g2.setImageResource(getResources().getIdentifier(gamesetList.get(14), "drawable", getPackageName()));
        returnSelectable(14, g2);
        h2.setImageResource(getResources().getIdentifier(gamesetList.get(15), "drawable", getPackageName()));
        returnSelectable(15, h2);

        a3.setImageResource(getResources().getIdentifier(gamesetList.get(16), "drawable", getPackageName()));
        returnSelectable(16, a3);
        b3.setImageResource(getResources().getIdentifier(gamesetList.get(17), "drawable", getPackageName()));
        returnSelectable(17, b3);
        c3.setImageResource(getResources().getIdentifier(gamesetList.get(18), "drawable", getPackageName()));
        returnSelectable(18, c3);
        d3.setImageResource(getResources().getIdentifier(gamesetList.get(19), "drawable", getPackageName()));
        returnSelectable(19, d3);
        e3.setImageResource(getResources().getIdentifier(gamesetList.get(20), "drawable", getPackageName()));
        returnSelectable(20, e3);
        f3.setImageResource(getResources().getIdentifier(gamesetList.get(21), "drawable", getPackageName()));
        returnSelectable(21, f3);
        g3.setImageResource(getResources().getIdentifier(gamesetList.get(22), "drawable", getPackageName()));
        returnSelectable(22, g3);
        h3.setImageResource(getResources().getIdentifier(gamesetList.get(23), "drawable", getPackageName()));
        returnSelectable(23, h3);

        a4.setImageResource(getResources().getIdentifier(gamesetList.get(24), "drawable", getPackageName()));
        returnSelectable(24, a4);
        b4.setImageResource(getResources().getIdentifier(gamesetList.get(25), "drawable", getPackageName()));
        returnSelectable(25, b4);
        c4.setImageResource(getResources().getIdentifier(gamesetList.get(26), "drawable", getPackageName()));
        returnSelectable(26, c4);
        d4.setImageResource(getResources().getIdentifier(gamesetList.get(27), "drawable", getPackageName()));
        returnSelectable(27, d4);
        e4.setImageResource(getResources().getIdentifier(gamesetList.get(28), "drawable", getPackageName()));
        returnSelectable(28, e4);
        f4.setImageResource(getResources().getIdentifier(gamesetList.get(29), "drawable", getPackageName()));
        returnSelectable(29, f4);
        g4.setImageResource(getResources().getIdentifier(gamesetList.get(30), "drawable", getPackageName()));
        returnSelectable(30, g4);
        h4.setImageResource(getResources().getIdentifier(gamesetList.get(31), "drawable", getPackageName()));
        returnSelectable(31, h4);

        a5.setImageResource(getResources().getIdentifier(gamesetList.get(32), "drawable", getPackageName()));
        returnSelectable(32, a5);
        b5.setImageResource(getResources().getIdentifier(gamesetList.get(33), "drawable", getPackageName()));
        returnSelectable(33, b5);
        c5.setImageResource(getResources().getIdentifier(gamesetList.get(34), "drawable", getPackageName()));
        returnSelectable(34, c5);
        d5.setImageResource(getResources().getIdentifier(gamesetList.get(35), "drawable", getPackageName()));
        returnSelectable(35, d5);
        e5.setImageResource(getResources().getIdentifier(gamesetList.get(36), "drawable", getPackageName()));
        returnSelectable(36, e5);
        f5.setImageResource(getResources().getIdentifier(gamesetList.get(37), "drawable", getPackageName()));
        returnSelectable(37, f5);
        g5.setImageResource(getResources().getIdentifier(gamesetList.get(38), "drawable", getPackageName()));
        returnSelectable(38, g5);
        h5.setImageResource(getResources().getIdentifier(gamesetList.get(39), "drawable", getPackageName()));
        returnSelectable(39, h5);

        a6.setImageResource(getResources().getIdentifier(gamesetList.get(40), "drawable", getPackageName()));
        returnSelectable(40, a6);
        b6.setImageResource(getResources().getIdentifier(gamesetList.get(41), "drawable", getPackageName()));
        returnSelectable(41, b6);
        c6.setImageResource(getResources().getIdentifier(gamesetList.get(42), "drawable", getPackageName()));
        returnSelectable(42, c6);
        d6.setImageResource(getResources().getIdentifier(gamesetList.get(43), "drawable", getPackageName()));
        returnSelectable(43, d6);
        e6.setImageResource(getResources().getIdentifier(gamesetList.get(44), "drawable", getPackageName()));
        returnSelectable(44, e6);
        f6.setImageResource(getResources().getIdentifier(gamesetList.get(45), "drawable", getPackageName()));
        returnSelectable(45, f6);
        g6.setImageResource(getResources().getIdentifier(gamesetList.get(46), "drawable", getPackageName()));
        returnSelectable(46, g6);
        h6.setImageResource(getResources().getIdentifier(gamesetList.get(47), "drawable", getPackageName()));
        returnSelectable(47, h6);

        a7.setImageResource(getResources().getIdentifier(gamesetList.get(48), "drawable", getPackageName()));
        returnSelectable(48, a7);
        b7.setImageResource(getResources().getIdentifier(gamesetList.get(49), "drawable", getPackageName()));
        returnSelectable(49, b7);
        c7.setImageResource(getResources().getIdentifier(gamesetList.get(50), "drawable", getPackageName()));
        returnSelectable(50, c7);
        d7.setImageResource(getResources().getIdentifier(gamesetList.get(51), "drawable", getPackageName()));
        returnSelectable(51, d7);
        e7.setImageResource(getResources().getIdentifier(gamesetList.get(52), "drawable", getPackageName()));
        returnSelectable(52, e7);
        f7.setImageResource(getResources().getIdentifier(gamesetList.get(53), "drawable", getPackageName()));
        returnSelectable(53, f7);
        g7.setImageResource(getResources().getIdentifier(gamesetList.get(54), "drawable", getPackageName()));
        returnSelectable(54, g7);
        h7.setImageResource(getResources().getIdentifier(gamesetList.get(55), "drawable", getPackageName()));
        returnSelectable(55, h7);

        a8.setImageResource(getResources().getIdentifier(gamesetList.get(56), "drawable", getPackageName()));
        returnSelectable(56, a8);
        b8.setImageResource(getResources().getIdentifier(gamesetList.get(57), "drawable", getPackageName()));
        returnSelectable(57, b8);
        c8.setImageResource(getResources().getIdentifier(gamesetList.get(58), "drawable", getPackageName()));
        returnSelectable(58, c8);
        d8.setImageResource(getResources().getIdentifier(gamesetList.get(59), "drawable", getPackageName()));
        returnSelectable(59, d8);
        e8.setImageResource(getResources().getIdentifier(gamesetList.get(60), "drawable", getPackageName()));
        returnSelectable(60, e8);
        f8.setImageResource(getResources().getIdentifier(gamesetList.get(61), "drawable", getPackageName()));
        returnSelectable(61, f8);
        g8.setImageResource(getResources().getIdentifier(gamesetList.get(62), "drawable", getPackageName()));
        returnSelectable(62, g8);
        h8.setImageResource(getResources().getIdentifier(gamesetList.get(63), "drawable", getPackageName()));
        returnSelectable(63, h8);
    }

    private void returnSelectable(int square, final ImageView squareImageView) {
        //if the first word of the piece name is white or black, their space is clickable
        //TODO: implement turns
        if ((gamesetList.get(square).split("_")[0]).equals("white") || (gamesetList.get(square).split("_")[0]).equals("black")) {
            Log.e("EYHO", gamesetList.get(square).split("_")[0]);


            // if playerColor is white
            if ((gamesetList.get(square).split("_")[0]).equals("white") && playerColor.equals("white")) {

                final int localSquare = square;


                    squareImageView.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            if (selectedSquare == 99) {
                                v.setBackgroundColor(Color.parseColor("#A60000FF"));
                                selectedSquare = localSquare;
                                isSelected(localSquare, squareImageView);
                            }


                        }
                    });


            }

            // if playerColor is black
            if ((gamesetList.get(square).split("_")[0]).equals("black") && playerColor.equals("black")) {

                final int localSquare = square;



                    squareImageView.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            if (selectedSquare == 99) {
                                v.setBackgroundColor(Color.parseColor("#A60000FF"));
                                selectedSquare = localSquare;
                                isSelected(localSquare, squareImageView);

                            }
                        }
                    });


            }
        }


    }



    private void isSelected(int square, ImageView squareImageView) {

        // get selectedUnit
        final String selectedUnit = gamesetList.get(selectedSquare).split("_")[1];

        final ImageView localImageView = squareImageView;
        final int localSquare = square;

        //TODO: clean up... this is too many levels of onclick listeners
        squareImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //set background back to transparent when clicked again and set selectedSquare to empty value (99)
                selectedSquare = 99;

                // call clearBackgronds function to clear selected and possible moves indicators
                clearBackgrounds();

                v.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {

                        if (selectedSquare == 99) {
                            if (playerColor.equals("black")) {
                                v.setBackgroundColor(Color.parseColor("#A60000FF"));
                                selectedSquare = localSquare;
                            }

                            if (playerColor.equals("white")) {
                                v.setBackgroundColor(Color.parseColor("#A60000FF"));
                                selectedSquare = localSquare;
                            }

                            isSelected(localSquare, localImageView);
                        }

                    }
                });


            }
        });

        ;


        // Check for possible moves
        possibleMoves();


        //Logging
        Log.e("TEST: ", "" + selectedUnit);
        Log.e("TEST: ", "" + String.valueOf(selectedSquare));

    }

    // TODO: Create function for possible moves after piece selected
    //passing in image view to be able to set color back on second click
    private void possibleMoves() {

        for (int i = 0; i < gamesetList.size(); i++) {
            // If player white and space held by black or free, mark as available
            if (playerColor.equals("white") && gamesetList.get(i).split("_")[0].equals("black") || gamesetList.get(i).split("_")[0].equals("free")) {
                ImageView space = getSquareImageView(i);
                space.setBackgroundColor(Color.parseColor("#A600FF00"));

            }

            // If player black and space held by white or free, mark as available
            if (playerColor.equals("black") && gamesetList.get(i).split("_")[0].equals("white") || gamesetList.get(i).split("_")[0].equals("free")) {
                ImageView space = getSquareImageView(i);
                space.setBackgroundColor(Color.parseColor("#A600FF00"));

            }
        }
    }

    // Clear backgrounds from selected / possible moves
    private void clearBackgrounds() {
        for (int i = 0; i < gamesetList.size(); i++) {
            ImageView space = (ImageView) getSquareImageView(i);
            space.setBackgroundColor(Color.parseColor("#00FFFFFF"));
        }
    }


    // to conveniently get ImageView from the int value of the square
    // TODO: there are definitely more clever/efficient ways to do this
    private ImageView getSquareImageView(int square) {
        ImageView imgView = null;
        switch (square) {
            case 0:
                imgView = a1;
                break;
            case 1:
                imgView = b1;
                break;
            case 2:
                imgView = c1;
                break;
            case 3:
                imgView = d1;
                break;
            case 4:
                imgView = e1;
                break;
            case 5:
                imgView = f1;
                break;
            case 6:
                imgView = g1;
                break;
            case 7:
                imgView = h1;
                break;

            case 8:
                imgView = a2;
                break;
            case 9:
                imgView = b2;
                break;
            case 10:
                imgView = c2;
                break;
            case 11:
                imgView = d2;
                break;
            case 12:
                imgView = e2;
                break;
            case 13:
                imgView = f2;
                break;
            case 14:
                imgView = g2;
                break;
            case 15:
                imgView = h2;
                break;


            case 16:
                imgView = a3;
                break;
            case 17:
                imgView = b3;
                break;
            case 18:
                imgView = c3;
                break;
            case 19:
                imgView = d3;
                break;
            case 20:
                imgView = e3;
                break;
            case 21:
                imgView = f3;
                break;
            case 22:
                imgView = g3;
                break;
            case 23:
                imgView = h3;
                break;

            case 24:
                imgView = a4;
                break;
            case 25:
                imgView = b4;
                break;
            case 26:
                imgView = c4;
                break;
            case 27:
                imgView = d4;
                break;
            case 28:
                imgView = e4;
                break;
            case 29:
                imgView = f4;
                break;
            case 30:
                imgView = g4;
                break;
            case 31:
                imgView = h4;
                break;

            case 32:
                imgView = a5;
                break;
            case 33:
                imgView = b5;
                break;
            case 34:
                imgView = c5;
                break;
            case 35:
                imgView = d5;
                break;
            case 36:
                imgView = e5;
                break;
            case 37:
                imgView = f5;
                break;
            case 38:
                imgView = g5;
                break;
            case 39:
                imgView = h5;
                break;


            case 40:
                imgView = a6;
                break;
            case 41:
                imgView = b6;
                break;
            case 42:
                imgView = c6;
                break;
            case 43:
                imgView = d6;
                break;
            case 44:
                imgView = e6;
                break;
            case 45:
                imgView = f6;
                break;
            case 46:
                imgView = g6;
                break;
            case 47:
                imgView = h6;
                break;

            case 48:
                imgView = a7;
                break;
            case 49:
                imgView = b7;
                break;
            case 50:
                imgView = c7;
                break;
            case 51:
                imgView = d7;
                break;
            case 52:
                imgView = e7;
                break;
            case 53:
                imgView = f7;
                break;
            case 54:
                imgView = g7;
                break;
            case 55:
                imgView = h7;
                break;

            case 56:
                imgView = a8;
                break;
            case 57:
                imgView = b8;
                break;
            case 58:
                imgView = c8;
                break;
            case 59:
                imgView = d8;
                break;
            case 60:
                imgView = e8;
                break;
            case 61:
                imgView = f8;
                break;
            case 62:
                imgView = g8;
                break;
            case 63:
                imgView = h8;
                break;

        }






        return imgView;


    }

}
