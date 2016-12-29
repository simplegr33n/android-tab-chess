package ca.ggolda.android_tab_chess;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.List;

/**
 * Created by gcgol on 12/19/2016.
 */


public class GameActivity extends AppCompatActivity {

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

    private String gamesetString = "";

    private int selectedSquare = 99;
    private String selectedUnit = "";

    //TODO: remove hardcoded playerColor
    private String playerColor;

    private String turn = "white";

    private TextView currentTurn;

    private List<String> gamesetList;

    private FirebaseAuth.AuthStateListener authListener;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mGamesDatabaseReference;

    private String match_id;

    private String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        mFirebaseDatabase = FirebaseDatabase.getInstance();

        mGamesDatabaseReference = mFirebaseDatabase.getReference().child("games");

        match_id = getIntent().getStringExtra("MATCH_ID");

        //get current user and send to login screen if user is null
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        userId = user.getUid();

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(GameActivity.this, LoginActivity.class));
                    finish();
                }
            }
        };

        currentTurn = (TextView) findViewById(R.id.current_turn);



        // TODO: probably remove new game, test board, and change sides on clicks
        TextView newGame = (TextView) findViewById(R.id.new_game);
        newGame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                gamesetString = getResources().getString(R.string.new_board);


                turn = "white";
                currentTurn.setText("turn: WHITE");
                currentTurn.setBackgroundColor(Color.parseColor("#FFFFFF"));
                currentTurn.setTextColor(Color.parseColor("#000000"));

                setBoard();

            }
        });


        TextView testBoard = (TextView) findViewById(R.id.test_board);
        testBoard.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                gamesetString = getResources().getString(R.string.test_board);


                turn = "white";
                currentTurn.setText("turn: WHITE");
                currentTurn.setBackgroundColor(Color.parseColor("#FFFFFF"));
                currentTurn.setTextColor(Color.parseColor("#000000"));

                setBoard();

            }
        });

        final TextView currentSide = (TextView) findViewById(R.id.current_side);

        // Get user games for Active list
        mGamesDatabaseReference.child(match_id).child("white").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String white_player = dataSnapshot.getValue(String.class);
                if (userId.equals(white_player)) {
                    playerColor = "white";

                    currentSide.setText("you are: WHITE");
                    currentSide.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    currentSide.setTextColor(Color.parseColor("#000000"));

                } else {
                    playerColor = "black";

                    currentSide.setText("you are: BLACK");
                    currentSide.setBackgroundColor(Color.parseColor("#000000"));
                    currentSide.setTextColor(Color.parseColor("#FFFFFF"));

                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });


        TextView changeSide = (TextView) findViewById(R.id.change_side);
        changeSide.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                for (int i = 0; i < 64; i++) {
                    getSquareImageView(i).setOnClickListener(null);
                }

                clearSelected();


            }
        });

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





        // Get gamesetString from firebase
        mGamesDatabaseReference.child(match_id).child("board").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {



                Log.e("GAMESET", ""+match_id);
                Log.e("GAMESET", ""+dataSnapshot.getValue(String.class));

                gamesetString = dataSnapshot.getValue(String.class);
                setBoard();


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });


        // Get turn color from firebase
        mGamesDatabaseReference.child(match_id).child("turn_color").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                turn = dataSnapshot.getValue(String.class);

                if (turn.equals("black")) {
                    currentTurn.setText("turn: BLACK");
                    currentTurn.setBackgroundColor(Color.parseColor("#000000"));
                    currentTurn.setTextColor(Color.parseColor("#FFFFFF"));

                } else if (turn.equals("white")) {
                    currentTurn.setText("turn: WHITE");
                    currentTurn.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    currentTurn.setTextColor(Color.parseColor("#000000"));
                }

                setBoard();



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });



    }

    private void setBoard() {


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


            // if turn is white
            if (turn.equals("white")) {

                Log.e("GAMEEEE", gamesetList.get(square).split("_")[0]);

                if (playerColor == null) {
                    playerColor = "black";
                }
                Log.e("GAMEEEE", playerColor);

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
            }

            // if turn is black
            if (turn.equals("black")) {
                if ((gamesetList.get(square).split("_")[0]).equals("black") && playerColor.equals("black")) {

                    final int localSquare = square;

                    squareImageView.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            if (selectedSquare == 99 && turn.equals("black")) {
                                v.setBackgroundColor(Color.parseColor("#A60000FF"));
                                selectedSquare = localSquare;
                                isSelected(localSquare, squareImageView);

                            }
                        }
                    });
                }
            }

        }


    }


    private void isSelected(int square, ImageView squareImageView) {

        // get selectedUnit
        selectedUnit = gamesetList.get(selectedSquare);

        final ImageView localImageView = squareImageView;
        final int localSquare = square;

        //TODO: clean up... this is too many levels of onclick listeners
        squareImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //set background back to transparent when clicked again and set selectedSquare to empty value (99)
                selectedSquare = 99;

                // call clearBackgronds function to clear selected and possible moves indicators
                clearSelected();

                // Rerun set board as some squares have probably been nulled by a wall function
                // TODO: this may be inefficient, rethink
                setBoard();

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

        // Check for possible moves
        possibleMoves();


        //Logging
        Log.e("TEST: ", "" + selectedUnit);
        Log.e("TEST: ", "" + String.valueOf(selectedSquare));

    }

    // TODO: Create function for possible moves after piece selected
    // make possible views active
    // passing in image view to be able to set color back on second click
    private void possibleMoves() {


//            __      ___    _ _       ___
//            \ \    / / |_ (_) |_ ___| _ \__ ___ __ ___ _
//             \ \/\/ /| ' \| |  _/ -_)  _/ _` \ V  V / ' \
//              \_/\_/ |_||_|_|\__\___|_| \__,_|\_/\_/|_||_|

        if (selectedUnit.equals("white_pawn")) {
            Log.e("EYHOSEL", selectedUnit);

            for (int i = 0; i < 64; i++) {
                final int localI = i;
                final ImageView space = getSquareImageView(i);

                Log.e("EYHOSEL", gamesetList.get(i));


                // if statement for +1 forward
                if ((i == selectedSquare + 8) && isFree(i)) {

                    if (space != null) {
                        space.setBackgroundColor(Color.parseColor("#A600FF00"));
                        space.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                moveGamepiece(localI);
                            }
                        });
                    }
                }

                // if statement for double move on first go
                if ((i == selectedSquare + 16) && isFree(i) && gamesetList.get(i).equals("free_square") && ((i == 24) || (i == 25) || (i == 26) || (i == 27) || (i == 28) || (i == 29) || (i == 30) || (i == 31))) {

                    if (space != null) {
                        space.setBackgroundColor(Color.parseColor("#A600FF00"));
                        space.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                moveGamepiece(localI);
                            }
                        });
                    }
                }


                // if statement for pawn kill
                if (((i == selectedSquare + 7) || (i == selectedSquare + 9))) {
                    if ((gamesetList.get(i).equals("black_pawn") || gamesetList.get(i).equals("black_king") || gamesetList.get(i).equals("black_queen") || gamesetList.get(i).equals("black_rook") || gamesetList.get(i).equals("black_knight") || gamesetList.get(i).equals("black_bishop"))) {

                        Log.e("EHYOe", "um" + gamesetList.get(i));

                        if (space != null) {
                            space.setBackgroundColor(Color.parseColor("#A600FF00"));
                            space.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    moveGamepiece(localI);
                                }
                            });
                        }
                        wallPawn(i);
                    }
                }


            }


        }

//             ___ _         _   ___
//            | _ ) |__ _ __| |_| _ \__ ___ __ ___ _
//            | _ \ / _` / _| / /  _/ _` \ V  V / ' \
//            |___/_\__,_\__|_\_\_| \__,_|\_/\_/|_||_|

        if (selectedUnit.equals("black_pawn")) {
            Log.e("EYHOSEL", selectedUnit);


            for (int i = 0; i < 64; i++) {
                final int localI = i;
                final ImageView space = getSquareImageView(i);

                // if statement for -1 forward
                if ((i == selectedSquare - 8) && isFree(i)) {

                    if (space != null) {
                        space.setBackgroundColor(Color.parseColor("#A600FF00"));
                        space.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                moveGamepiece(localI);
                            }
                        });
                    }
                }

                // if statement for double move on first go
                if ((i == selectedSquare - 16) && isFree(i) && gamesetList.get(i + 8).equals("free_square") && ((i == 32) || (i == 33) || (i == 34) || (i == 35) || (i == 36) || (i == 37) || (i == 38) || (i == 39))) {

                    if (space != null) {
                        space.setBackgroundColor(Color.parseColor("#A600FF00"));
                        space.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                moveGamepiece(localI);
                            }
                        });
                    }
                }

                // if statement for pawn kill
                if (((i == selectedSquare - 7) || (i == selectedSquare - 9))) {
                    if ((gamesetList.get(i).equals("white_pawn") || gamesetList.get(i).equals("white_king") || gamesetList.get(i).equals("white_queen") || gamesetList.get(i).equals("white_rook") || gamesetList.get(i).equals("white_knight") || gamesetList.get(i).equals("white_bishop"))) {

                        Log.e("EHYOe", "um" + gamesetList.get(i));

                        if (space != null) {
                            space.setBackgroundColor(Color.parseColor("#A600FF00"));
                            space.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    moveGamepiece(localI);
                                }
                            });
                        }
                        wallPawn(i);
                    }
                }


            }
        }


//            __      ___    _ _       _  __     _      _   _
//            \ \    / / |_ (_) |_ ___| |/ /_ _ (_)__ _| |_| |_
//             \ \/\/ /| ' \| |  _/ -_) ' <| ' \| / _` | ' \  _|
//              \_/\_/ |_||_|_|\__\___|_|\_\_||_|_\__, |_||_\__|
//                                                |___/
        if (selectedUnit.equals("white_knight")) {
            Log.e("EYHOSEL", selectedUnit);

            for (int i = 0; i < 64; i++) {
                final int localI = i;
                final ImageView space = getSquareImageView(i);

                // if statement for +1 forward
                if ((i == selectedSquare + 6) || (i == selectedSquare + 10) || (i == selectedSquare + 15) || (i == selectedSquare + 17) || (i == selectedSquare - 6) || (i == selectedSquare - 10) || (i == selectedSquare - 15) || (i == selectedSquare - 17)) {

                    if (isFree(i) || (gamesetList.get(i).equals("black_pawn")) || (gamesetList.get(i).equals("black_king")) || (gamesetList.get(i).equals("black_queen")) || (gamesetList.get(i).equals("black_bishop")) || (gamesetList.get(i).equals("black_knight")) || (gamesetList.get(i).equals("black_rook"))) {
                        if (space != null) {
                            space.setBackgroundColor(Color.parseColor("#A600FF00"));
                            space.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    moveGamepiece(localI);
                                }
                            });

                            wallKnight();

                        }
                    }
                }
            }
        }


//            ___ _         _   _  __     _      _   _
//           | _ ) |__ _ __| |_| |/ /_ _ (_)__ _| |_| |_
//           | _ \ / _` / _| / / ' <| ' \| / _` | ' \  _|
//           |___/_\__,_\__|_\_\_|\_\_||_|_\__, |_||_\__|
//                                          |___/

        if (selectedUnit.equals("black_knight")) {
            Log.e("EYHOSEL", selectedUnit);

            for (int i = 0; i < 64; i++) {
                final int localI = i;
                final ImageView space = getSquareImageView(i);

                // if statement for Knight movement
                if ((i == selectedSquare + 6) || (i == selectedSquare + 10) || (i == selectedSquare + 15) || (i == selectedSquare + 17) || (i == selectedSquare - 6) || (i == selectedSquare - 10) || (i == selectedSquare - 15) || (i == selectedSquare - 17)) {

                    if (isFree(i) || (gamesetList.get(i).equals("white_pawn")) || (gamesetList.get(i).equals("white_king")) || (gamesetList.get(i).equals("white_queen")) || (gamesetList.get(i).equals("white_bishop")) || (gamesetList.get(i).equals("white_knight")) || (gamesetList.get(i).equals("white_rook"))) {
                        if (space != null) {
                            space.setBackgroundColor(Color.parseColor("#A600FF00"));
                            space.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    moveGamepiece(localI);
                                }
                            });

                            wallKnight();
                        }
                    }
                }
            }
        }


//            __      ___    _ _       ___          _
//            \ \    / / |_ (_) |_ ___| _ \___  ___| |__
//             \ \/\/ /| ' \| |  _/ -_)   / _ \/ _ \ / /
//              \_/\_/ |_||_|_|\__\___|_|_\___/\___/_\_\

        if (selectedUnit.equals("white_rook")) {
            Log.e("EYHOSEL", selectedUnit);


            for (int i = 0; i < 64; i++) {
                final int localI = i;
                final ImageView space = getSquareImageView(i);

                Log.e("EYHOSEL", gamesetList.get(i));

                // subtract int selectedSquare to get the difference of i
                int iDiff = i - selectedSquare;

                switch (iDiff) {
                    // UP
                    case 8:
                        if (heldBlackOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;
                    case 16:

                        if (heldBlackOrFree(i) && isFree(i - 8)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        break;
                    case 24:

                        if (heldBlackOrFree(i) && isFree(i - 8) && isFree(i - 16)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;
                    case 32:

                        if (heldBlackOrFree(i) && isFree(i - 8) && isFree(i - 16) && isFree(i - 24)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;
                    case 40:

                        if (heldBlackOrFree(i) && isFree(i - 8) && isFree(i - 16) && isFree(i - 24) && isFree(i - 32)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;
                    case 48:

                        if (heldBlackOrFree(i) && isFree(i - 8) && isFree(i - 16) && isFree(i - 24) && isFree(i - 32) && isFree(i - 40)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        break;
                    case 56:

                        if (heldBlackOrFree(i) && isFree(i - 8) && isFree(i - 16) && isFree(i - 24) && isFree(i - 32) && isFree(i - 40) && isFree(i - 48)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        break;

                    // DOWN
                    case -8:
                        if (heldBlackOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;
                    case -16:

                        if (heldBlackOrFree(i) && isFree(i + 8)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        break;
                    case -24:

                        if (heldBlackOrFree(i) && isFree(i + 8) && isFree(i + 16)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;
                    case -32:

                        if (heldBlackOrFree(i) && isFree(i + 8) && isFree(i + 16) && isFree(i + 24)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;
                    case -40:

                        if (heldBlackOrFree(i) && isFree(i + 8) && isFree(i + 16) && isFree(i + 24) && isFree(i + 32)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;
                    case -48:

                        if (heldBlackOrFree(i) && isFree(i + 8) && isFree(i + 16) && isFree(i + 24) && isFree(i + 32) && isFree(i + 40)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        break;
                    case -56:

                        if (heldBlackOrFree(i) && isFree(i + 8) && isFree(i + 16) && isFree(i + 24) && isFree(i + 32) && isFree(i + 40) && isFree(i + 48)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        break;


                    // RIGHT
                    case 1:
                        if (heldBlackOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }

                        }
                        wallRook(i);


                        break;
                    case 2:

                        if (heldBlackOrFree(i) && isFree(i - 1)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallRook(i);

                        break;
                    case 3:

                        if (heldBlackOrFree(i) && isFree(i - 1) && isFree(i - 2)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallRook(i);


                        break;
                    case 4:

                        if (heldBlackOrFree(i) && isFree(i - 1) && isFree(i - 2) && isFree(i - 3)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallRook(i);


                        break;
                    case 5:

                        if (heldBlackOrFree(i) && isFree(i - 1) && isFree(i - 2) && isFree(i - 3) && isFree(i - 4)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallRook(i);


                        break;
                    case 6:

                        if (heldBlackOrFree(i) && isFree(i - 1) && isFree(i - 2) && isFree(i - 3) && isFree(i - 4) && isFree(i - 5)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallRook(i);

                        break;
                    case 7:

                        if (heldBlackOrFree(i) && isFree(i - 1) && isFree(i - 2) && isFree(i - 3) && isFree(i - 4) && isFree(i - 5) && isFree(i - 6)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallRook(i);

                        break;

                    // LEFT
                    case -1:
                        if (heldBlackOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallRook(i);


                        break;
                    case -2:

                        if (heldBlackOrFree(i) && isFree(i + 1)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        wallRook(i);

                        break;
                    case -3:

                        if (heldBlackOrFree(i) && isFree(i + 1) && isFree(i + 2)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        wallRook(i);


                        break;
                    case -4:

                        if (heldBlackOrFree(i) && isFree(i + 1) && isFree(i + 2) && isFree(i + 3)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        wallRook(i);


                        break;
                    case -5:

                        if (heldBlackOrFree(i) && isFree(i + 1) && isFree(i + 2) && isFree(i + 3) && isFree(i + 4)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        wallRook(i);


                        break;
                    case -6:

                        if (heldBlackOrFree(i) && isFree(i + 1) && isFree(i + 2) && isFree(i + 3) && isFree(i + 4) && isFree(i + 5)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        wallRook(i);

                        break;
                    case -7:

                        if (heldBlackOrFree(i) && isFree(i + 1) && isFree(i + 2) && isFree(i + 3) && isFree(i + 4) && isFree(i + 5) && isFree(i + 6)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        wallRook(i);

                        break;


                }
            }
        }
//             ___ _         _   ___          _
//            | _ ) |__ _ __| |_| _ \___  ___| |__
//            | _ \ / _` / _| / /   / _ \/ _ \ / /
//            |___/_\__,_\__|_\_\_|_\___/\___/_\_\

        if (selectedUnit.equals("black_rook")) {
            Log.e("EYHOSEL", selectedUnit);


            for (int i = 0; i < 64; i++) {
                final int localI = i;
                final ImageView space = getSquareImageView(i);

                Log.e("EYHOSEL", gamesetList.get(i));

                // subtract int selectedSquare to get the difference of i
                int iDiff = i - selectedSquare;

                switch (iDiff) {
                    // UP
                    case 8:
                        if (heldWhiteOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;
                    case 16:

                        if (heldWhiteOrFree(i) && isFree(i - 8)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        break;
                    case 24:

                        if (heldWhiteOrFree(i) && isFree(i - 8) && isFree(i - 16)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;
                    case 32:

                        if (heldWhiteOrFree(i) && isFree(i - 8) && isFree(i - 16) && isFree(i - 24)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;
                    case 40:

                        if (heldWhiteOrFree(i) && isFree(i - 8) && isFree(i - 16) && isFree(i - 24) && isFree(i - 32)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;
                    case 48:

                        if (heldWhiteOrFree(i) && isFree(i - 8) && isFree(i - 16) && isFree(i - 24) && isFree(i - 32) && isFree(i - 40)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        break;
                    case 56:

                        if (heldWhiteOrFree(i) && isFree(i - 8) && isFree(i - 16) && isFree(i - 24) && isFree(i - 32) && isFree(i - 40) && isFree(i - 48)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        break;

                    // DOWN
                    case -8:
                        if (heldWhiteOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;
                    case -16:

                        if (heldWhiteOrFree(i) && isFree(i + 8)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        break;
                    case -24:

                        if (heldWhiteOrFree(i) && isFree(i + 8) && isFree(i + 16)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;
                    case -32:

                        if (heldWhiteOrFree(i) && isFree(i + 8) && isFree(i + 16) && isFree(i + 24)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;
                    case -40:

                        if (heldWhiteOrFree(i) && isFree(i + 8) && isFree(i + 16) && isFree(i + 24) && isFree(i + 32)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;
                    case -48:

                        if (heldWhiteOrFree(i) && isFree(i + 8) && isFree(i + 16) && isFree(i + 24) && isFree(i + 32) && isFree(i + 40)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        break;
                    case -56:

                        if (heldWhiteOrFree(i) && isFree(i + 8) && isFree(i + 16) && isFree(i + 24) && isFree(i + 32) && isFree(i + 40) && isFree(i + 48)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        break;


                    // RIGHT
                    case 1:
                        if (heldWhiteOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }

                        }
                        wallRook(i);


                        break;
                    case 2:

                        if (heldWhiteOrFree(i) && isFree(i - 1)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallRook(i);

                        break;
                    case 3:

                        if (heldWhiteOrFree(i) && isFree(i - 1) && isFree(i - 2)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallRook(i);


                        break;
                    case 4:

                        if (heldWhiteOrFree(i) && isFree(i - 1) && isFree(i - 2) && isFree(i - 3)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallRook(i);


                        break;
                    case 5:

                        if (heldWhiteOrFree(i) && isFree(i - 1) && isFree(i - 2) && isFree(i - 3) && isFree(i - 4)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallRook(i);


                        break;
                    case 6:

                        if (heldWhiteOrFree(i) && isFree(i - 1) && isFree(i - 2) && isFree(i - 3) && isFree(i - 4) && isFree(i - 5)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallRook(i);

                        break;
                    case 7:

                        if (heldWhiteOrFree(i) && isFree(i - 1) && isFree(i - 2) && isFree(i - 3) && isFree(i - 4) && isFree(i - 5) && isFree(i - 6)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallRook(i);

                        break;

                    // LEFT
                    case -1:
                        if (heldWhiteOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallRook(i);


                        break;
                    case -2:

                        if (heldWhiteOrFree(i) && isFree(i + 1)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        wallRook(i);

                        break;
                    case -3:

                        if (heldWhiteOrFree(i) && isFree(i + 1) && isFree(i + 2)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        wallRook(i);


                        break;
                    case -4:

                        if (heldWhiteOrFree(i) && isFree(i + 1) && isFree(i + 2) && isFree(i + 3)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        wallRook(i);


                        break;
                    case -5:

                        if (heldWhiteOrFree(i) && isFree(i + 1) && isFree(i + 2) && isFree(i + 3) && isFree(i + 4)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        wallRook(i);


                        break;
                    case -6:

                        if (heldWhiteOrFree(i) && isFree(i + 1) && isFree(i + 2) && isFree(i + 3) && isFree(i + 4) && isFree(i + 5)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        wallRook(i);

                        break;
                    case -7:

                        if (heldWhiteOrFree(i) && isFree(i + 1) && isFree(i + 2) && isFree(i + 3) && isFree(i + 4) && isFree(i + 5) && isFree(i + 6)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        wallRook(i);

                        break;


                }

            }
        }

//            __      ___    _ _       ___ _    _
//            \ \    / / |_ (_) |_ ___| _ |_)__| |_  ___ _ __
//             \ \/\/ /| ' \| |  _/ -_) _ \ (_-< ' \/ _ \ '_ \
//              \_/\_/ |_||_|_|\__\___|___/_/__/_||_\___/ .__/
//                                                      |_|
        if (selectedUnit.equals("white_bishop")) {
            Log.e("EYHOSEL", selectedUnit);


            for (int i = 0; i < 64; i++) {
                final int localI = i;
                final ImageView space = getSquareImageView(i);

                Log.e("EYHOSEL", gamesetList.get(i));

                // subtract int selectedSquare to get the difference of i
                int iDiff = i - selectedSquare;

                switch (iDiff) {
                    // UP-RIGHT
                    case 54:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare + 9) && isFree(selectedSquare + 18) && isFree(selectedSquare + 27) && isFree(selectedSquare + 36) && isFree(selectedSquare + 45))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallBishop(i);
                        break;

                    case 45:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare + 9) && isFree(selectedSquare + 18) && isFree(selectedSquare + 27) && isFree(selectedSquare + 36))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallBishop(i);
                        break;

                    case 36:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare + 9) && isFree(selectedSquare + 18) && isFree(selectedSquare + 27))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallBishop(i);
                        break;

                    case 27:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare + 9) && isFree(selectedSquare + 18))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallBishop(i);
                        break;

                    case 18:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare + 9))) {

                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }

                        }
                        wallBishop(i);
                        break;

                    case 9:
                        if (heldBlackOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }

                        }
                        wallBishop(i);
                        break;

                    // DOWN-RIGHT
                    case -42:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare - 7) && isFree(selectedSquare - 14) && isFree(selectedSquare - 21) && isFree(selectedSquare - 28) && isFree(selectedSquare - 35))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallBishop(i);
                        break;

                    case -35:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare - 7) && isFree(selectedSquare - 14) && isFree(selectedSquare - 21) && isFree(selectedSquare - 28))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallBishop(i);
                        break;

                    case -28:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare - 7) && isFree(selectedSquare - 14) && isFree(selectedSquare - 21))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallBishop(i);
                        break;

                    case -21:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare - 7) && isFree(selectedSquare - 14))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallBishop(i);
                        break;

                    case -14:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare - 7))) {

                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }

                        }
                        wallBishop(i);
                        break;

                    case -7:
                        if (heldBlackOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }

                        }
                        wallBishop(i);
                        break;

                    // UP-LEFT
                    case 42:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare + 7) && isFree(selectedSquare + 14) && isFree(selectedSquare + 21) && isFree(selectedSquare + 28) && isFree(selectedSquare + 35))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallBishop(i);
                        break;

                    case 35:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare + 7) && isFree(selectedSquare + 14) && isFree(selectedSquare + 21) && isFree(selectedSquare + 28))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallBishop(i);
                        break;

                    case 28:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare + 7) && isFree(selectedSquare + 14) && isFree(selectedSquare + 21))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallBishop(i);
                        break;

                    case 21:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare + 7) && isFree(selectedSquare + 14))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallBishop(i);
                        break;

                    case 14:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare + 7))) {

                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }

                        }
                        wallBishop(i);
                        break;

                    case 7:
                        if (heldBlackOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }

                        }
                        wallBishop(i);
                        break;


                    // DOWN LEFT
                    case -54:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare - 9) && isFree(selectedSquare - 18) && isFree(selectedSquare - 27) && isFree(selectedSquare - 36) && isFree(selectedSquare - 45))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallBishop(i);
                        break;

                    case -45:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare - 9) && isFree(selectedSquare - 18) && isFree(selectedSquare - 27) && isFree(selectedSquare - 36))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallBishop(i);
                        break;

                    case -36:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare - 9) && isFree(selectedSquare - 18) && isFree(selectedSquare - 27))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallBishop(i);
                        break;

                    case -27:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare - 9) && isFree(selectedSquare - 18))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallBishop(i);
                        break;

                    case -18:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare - 9))) {

                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }

                        }
                        wallBishop(i);
                        break;

                    case -9:
                        if (heldBlackOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }

                        }
                        wallBishop(i);
                        break;


                }
            }
        }


//             ___ _         _   ___ _    _
//            | _ ) |__ _ __| |_| _ |_)__| |_  ___ _ __
//            | _ \ / _` / _| / / _ \ (_-< ' \/ _ \ '_ \
//            |___/_\__,_\__|_\_\___/_/__/_||_\___/ .__/
//                                                |_|
        if (selectedUnit.equals("black_bishop")) {
            Log.e("EYHOSEL", selectedUnit);


            for (int i = 0; i < 64; i++) {
                final int localI = i;
                final ImageView space = getSquareImageView(i);

                Log.e("EYHOSEL", gamesetList.get(i));

                // subtract int selectedSquare to get the difference of i
                int iDiff = i - selectedSquare;

                switch (iDiff) {
                    // UP-RIGHT
                    case 54:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare + 9) && isFree(selectedSquare + 18) && isFree(selectedSquare + 27) && isFree(selectedSquare + 36) && isFree(selectedSquare + 45))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallBishop(i);
                        break;

                    case 45:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare + 9) && isFree(selectedSquare + 18) && isFree(selectedSquare + 27) && isFree(selectedSquare + 36))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallBishop(i);
                        break;

                    case 36:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare + 9) && isFree(selectedSquare + 18) && isFree(selectedSquare + 27))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallBishop(i);
                        break;

                    case 27:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare + 9) && isFree(selectedSquare + 18))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallBishop(i);
                        break;

                    case 18:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare + 9))) {

                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }

                        }
                        wallBishop(i);
                        break;

                    case 9:
                        if (heldWhiteOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }

                        }
                        wallBishop(i);
                        break;

                    // DOWN-RIGHT
                    case -42:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare - 7) && isFree(selectedSquare - 14) && isFree(selectedSquare - 21) && isFree(selectedSquare - 28) && isFree(selectedSquare - 35))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallBishop(i);
                        break;

                    case -35:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare - 7) && isFree(selectedSquare - 14) && isFree(selectedSquare - 21) && isFree(selectedSquare - 28))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallBishop(i);
                        break;

                    case -28:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare - 7) && isFree(selectedSquare - 14) && isFree(selectedSquare - 21))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallBishop(i);
                        break;

                    case -21:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare - 7) && isFree(selectedSquare - 14))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallBishop(i);
                        break;

                    case -14:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare - 7))) {

                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }

                        }
                        wallBishop(i);
                        break;

                    case -7:
                        if (heldWhiteOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }

                        }
                        wallBishop(i);
                        break;

                    // UP-LEFT
                    case 42:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare + 7) && isFree(selectedSquare + 14) && isFree(selectedSquare + 21) && isFree(selectedSquare + 28) && isFree(selectedSquare + 35))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallBishop(i);
                        break;

                    case 35:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare + 7) && isFree(selectedSquare + 14) && isFree(selectedSquare + 21) && isFree(selectedSquare + 28))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallBishop(i);
                        break;

                    case 28:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare + 7) && isFree(selectedSquare + 14) && isFree(selectedSquare + 21))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallBishop(i);
                        break;

                    case 21:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare + 7) && isFree(selectedSquare + 14))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallBishop(i);
                        break;

                    case 14:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare + 7))) {

                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }

                        }
                        wallBishop(i);
                        break;

                    case 7:
                        if (heldWhiteOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }

                        }
                        wallBishop(i);
                        break;


                    // DOWN LEFT
                    case -54:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare - 9) && isFree(selectedSquare - 18) && isFree(selectedSquare - 27) && isFree(selectedSquare - 36) && isFree(selectedSquare - 45))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallBishop(i);
                        break;

                    case -45:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare - 9) && isFree(selectedSquare - 18) && isFree(selectedSquare - 27) && isFree(selectedSquare - 36))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallBishop(i);
                        break;

                    case -36:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare - 9) && isFree(selectedSquare - 18) && isFree(selectedSquare - 27))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallBishop(i);
                        break;

                    case -27:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare - 9) && isFree(selectedSquare - 18))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallBishop(i);
                        break;

                    case -18:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare - 9))) {

                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }

                        }
                        wallBishop(i);
                        break;

                    case -9:
                        if (heldWhiteOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }

                        }
                        wallBishop(i);
                        break;


                }
            }
        }


//            __      ___    _ _        ___
//            \ \    / / |_ (_) |_ ___ / _ \ _  _ ___ ___ _ _
//             \ \/\/ /| ' \| |  _/ -_) (_) | || / -_) -_) ' \
//              \_/\_/ |_||_|_|\__\___|\__\_\\_,_\___\___|_||_|

        if (selectedUnit.equals("white_queen")) {
            Log.e("EYHOSEL", selectedUnit);


            for (int i = 0; i < 64; i++) {
                final int localI = i;
                final ImageView space = getSquareImageView(i);

                Log.e("EYHOSEL", gamesetList.get(i));

                // subtract int selectedSquare to get the difference of i
                int iDiff = i - selectedSquare;

                // FOR DIAGONAL
                switch (iDiff) {
                    // UP-RIGHT
                    case 54:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare + 9) && isFree(selectedSquare + 18) && isFree(selectedSquare + 27) && isFree(selectedSquare + 36) && isFree(selectedSquare + 45))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);
                        break;

                    case 45:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare + 9) && isFree(selectedSquare + 18) && isFree(selectedSquare + 27) && isFree(selectedSquare + 36))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);
                        break;

                    case 36:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare + 9) && isFree(selectedSquare + 18) && isFree(selectedSquare + 27))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);
                        break;

                    case 27:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare + 9) && isFree(selectedSquare + 18))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);
                        break;

                    case 18:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare + 9))) {

                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }

                        }
                        wallQueen(i);
                        break;

                    case 9:
                        if (heldBlackOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }

                        }
                        wallQueen(i);
                        break;

                    // DOWN-RIGHT
                    case -42:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare - 7) && isFree(selectedSquare - 14) && isFree(selectedSquare - 21) && isFree(selectedSquare - 28) && isFree(selectedSquare - 35))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);
                        break;

                    case -35:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare - 7) && isFree(selectedSquare - 14) && isFree(selectedSquare - 21) && isFree(selectedSquare - 28))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);
                        break;

                    case -28:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare - 7) && isFree(selectedSquare - 14) && isFree(selectedSquare - 21))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);
                        break;

                    case -21:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare - 7) && isFree(selectedSquare - 14))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);
                        break;

                    case -14:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare - 7))) {

                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }

                        }
                        wallQueen(i);
                        break;

                    case -7:
                        if (heldBlackOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }

                        }
                        wallQueen(i);
                        break;

                    // UP-LEFT
                    case 42:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare + 7) && isFree(selectedSquare + 14) && isFree(selectedSquare + 21) && isFree(selectedSquare + 28) && isFree(selectedSquare + 35))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);
                        break;

                    case 35:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare + 7) && isFree(selectedSquare + 14) && isFree(selectedSquare + 21) && isFree(selectedSquare + 28))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);
                        break;

                    case 28:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare + 7) && isFree(selectedSquare + 14) && isFree(selectedSquare + 21))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);
                        break;

                    case 21:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare + 7) && isFree(selectedSquare + 14))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);
                        break;

                    case 14:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare + 7))) {

                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }

                        }
                        wallQueen(i);
                        break;

                    case 7:
                        if (heldBlackOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }

                        }
                        wallQueen(i);
                        break;


                    // DOWN LEFT
                    case -54:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare - 9) && isFree(selectedSquare - 18) && isFree(selectedSquare - 27) && isFree(selectedSquare - 36) && isFree(selectedSquare - 45))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);
                        break;

                    case -45:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare - 9) && isFree(selectedSquare - 18) && isFree(selectedSquare - 27) && isFree(selectedSquare - 36))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);
                        break;

                    case -36:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare - 9) && isFree(selectedSquare - 18) && isFree(selectedSquare - 27))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);
                        break;

                    case -27:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare - 9) && isFree(selectedSquare - 18))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);
                        break;

                    case -18:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare - 9))) {

                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }

                        }
                        wallQueen(i);
                        break;

                    case -9:
                        if (heldBlackOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }

                        }
                        wallQueen(i);
                        break;
                }


                //FOR VERTICAL
                switch (iDiff) {
                    // UP
                    case 8:
                        if (heldBlackOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;
                    case 16:

                        if (heldBlackOrFree(i) && isFree(i - 8)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        break;
                    case 24:

                        if (heldBlackOrFree(i) && isFree(i - 8) && isFree(i - 16)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;
                    case 32:

                        if (heldBlackOrFree(i) && isFree(i - 8) && isFree(i - 16) && isFree(i - 24)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;
                    case 40:

                        if (heldBlackOrFree(i) && isFree(i - 8) && isFree(i - 16) && isFree(i - 24) && isFree(i - 32)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;
                    case 48:

                        if (heldBlackOrFree(i) && isFree(i - 8) && isFree(i - 16) && isFree(i - 24) && isFree(i - 32) && isFree(i - 40)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        break;
                    case 56:

                        if (heldBlackOrFree(i) && isFree(i - 8) && isFree(i - 16) && isFree(i - 24) && isFree(i - 32) && isFree(i - 40) && isFree(i - 48)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        break;

                    // DOWN
                    case -8:
                        if (heldBlackOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;
                    case -16:

                        if (heldBlackOrFree(i) && isFree(i + 8)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        break;
                    case -24:

                        if (heldBlackOrFree(i) && isFree(i + 8) && isFree(i + 16)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;
                    case -32:

                        if (heldBlackOrFree(i) && isFree(i + 8) && isFree(i + 16) && isFree(i + 24)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;
                    case -40:

                        if (heldBlackOrFree(i) && isFree(i + 8) && isFree(i + 16) && isFree(i + 24) && isFree(i + 32)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;
                    case -48:

                        if (heldBlackOrFree(i) && isFree(i + 8) && isFree(i + 16) && isFree(i + 24) && isFree(i + 32) && isFree(i + 40)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        break;
                    case -56:

                        if (heldBlackOrFree(i) && isFree(i + 8) && isFree(i + 16) && isFree(i + 24) && isFree(i + 32) && isFree(i + 40) && isFree(i + 48)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        break;


                    // RIGHT
                    case 1:
                        if (heldBlackOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }

                        }
                        wallQueen(i);


                        break;
                    case 2:

                        if (heldBlackOrFree(i) && isFree(i - 1)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);

                        break;
                    case 3:

                        if (heldBlackOrFree(i) && isFree(i - 1) && isFree(i - 2)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);


                        break;
                    case 4:

                        if (heldBlackOrFree(i) && isFree(i - 1) && isFree(i - 2) && isFree(i - 3)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);


                        break;
                    case 5:

                        if (heldBlackOrFree(i) && isFree(i - 1) && isFree(i - 2) && isFree(i - 3) && isFree(i - 4)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);


                        break;
                    case 6:

                        if (heldBlackOrFree(i) && isFree(i - 1) && isFree(i - 2) && isFree(i - 3) && isFree(i - 4) && isFree(i - 5)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);

                        break;
                    case 7:

                        if (heldBlackOrFree(i) && isFree(i - 1) && isFree(i - 2) && isFree(i - 3) && isFree(i - 4) && isFree(i - 5) && isFree(i - 6)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);

                        break;

                    // LEFT
                    case -1:
                        if (heldBlackOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);


                        break;
                    case -2:

                        if (heldBlackOrFree(i) && isFree(i + 1)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        wallQueen(i);

                        break;
                    case -3:

                        if (heldBlackOrFree(i) && isFree(i + 1) && isFree(i + 2)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        wallQueen(i);


                        break;
                    case -4:

                        if (heldBlackOrFree(i) && isFree(i + 1) && isFree(i + 2) && isFree(i + 3)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        wallQueen(i);


                        break;
                    case -5:

                        if (heldBlackOrFree(i) && isFree(i + 1) && isFree(i + 2) && isFree(i + 3) && isFree(i + 4)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        wallQueen(i);


                        break;
                    case -6:

                        if (heldBlackOrFree(i) && isFree(i + 1) && isFree(i + 2) && isFree(i + 3) && isFree(i + 4) && isFree(i + 5)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        wallQueen(i);

                        break;
                    case -7:

                        if (heldBlackOrFree(i) && isFree(i + 1) && isFree(i + 2) && isFree(i + 3) && isFree(i + 4) && isFree(i + 5) && isFree(i + 6)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        wallQueen(i);

                        break;


                }

            }
        }

//             ___ _         _    ___
//            | _ ) |__ _ __| |__/ _ \ _  _ ___ ___ _ _
//            | _ \ / _` / _| / / (_) | || / -_) -_) ' \
//            |___/_\__,_\__|_\_\\__\_\\_,_\___\___|_||_|

        if (selectedUnit.equals("black_queen")) {
            Log.e("EYHOSEL", selectedUnit);


            for (int i = 0; i < 64; i++) {
                final int localI = i;
                final ImageView space = getSquareImageView(i);

                Log.e("EYHOSEL", gamesetList.get(i));

                // subtract int selectedSquare to get the difference of i
                int iDiff = i - selectedSquare;

                // FOR DIAGONAL
                switch (iDiff) {
                    // UP-RIGHT
                    case 54:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare + 9) && isFree(selectedSquare + 18) && isFree(selectedSquare + 27) && isFree(selectedSquare + 36) && isFree(selectedSquare + 45))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);
                        break;

                    case 45:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare + 9) && isFree(selectedSquare + 18) && isFree(selectedSquare + 27) && isFree(selectedSquare + 36))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);
                        break;

                    case 36:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare + 9) && isFree(selectedSquare + 18) && isFree(selectedSquare + 27))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);
                        break;

                    case 27:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare + 9) && isFree(selectedSquare + 18))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);
                        break;

                    case 18:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare + 9))) {

                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }

                        }
                        wallQueen(i);
                        break;

                    case 9:
                        if (heldWhiteOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }

                        }
                        wallQueen(i);
                        break;

                    // DOWN-RIGHT
                    case -42:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare - 7) && isFree(selectedSquare - 14) && isFree(selectedSquare - 21) && isFree(selectedSquare - 28) && isFree(selectedSquare - 35))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);
                        break;

                    case -35:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare - 7) && isFree(selectedSquare - 14) && isFree(selectedSquare - 21) && isFree(selectedSquare - 28))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);
                        break;

                    case -28:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare - 7) && isFree(selectedSquare - 14) && isFree(selectedSquare - 21))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);
                        break;

                    case -21:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare - 7) && isFree(selectedSquare - 14))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);
                        break;

                    case -14:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare - 7))) {

                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }

                        }
                        wallQueen(i);
                        break;

                    case -7:
                        if (heldWhiteOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }

                        }
                        wallQueen(i);
                        break;

                    // UP-LEFT
                    case 42:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare + 7) && isFree(selectedSquare + 14) && isFree(selectedSquare + 21) && isFree(selectedSquare + 28) && isFree(selectedSquare + 35))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);
                        break;

                    case 35:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare + 7) && isFree(selectedSquare + 14) && isFree(selectedSquare + 21) && isFree(selectedSquare + 28))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);
                        break;

                    case 28:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare + 7) && isFree(selectedSquare + 14) && isFree(selectedSquare + 21))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);
                        break;

                    case 21:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare + 7) && isFree(selectedSquare + 14))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);
                        break;

                    case 14:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare + 7))) {

                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }

                        }
                        wallQueen(i);
                        break;

                    case 7:
                        if (heldWhiteOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }

                        }
                        wallQueen(i);
                        break;


                    // DOWN LEFT
                    case -54:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare - 9) && isFree(selectedSquare - 18) && isFree(selectedSquare - 27) && isFree(selectedSquare - 36) && isFree(selectedSquare - 45))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);
                        break;

                    case -45:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare - 9) && isFree(selectedSquare - 18) && isFree(selectedSquare - 27) && isFree(selectedSquare - 36))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);
                        break;

                    case -36:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare - 9) && isFree(selectedSquare - 18) && isFree(selectedSquare - 27))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);
                        break;

                    case -27:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare - 9) && isFree(selectedSquare - 18))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);
                        break;

                    case -18:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare - 9))) {

                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }

                        }
                        wallQueen(i);
                        break;

                    case -9:
                        if (heldWhiteOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }

                        }
                        wallQueen(i);
                        break;
                }


                //FOR VERTICAL
                switch (iDiff) {
                    // UP
                    case 8:
                        if (heldWhiteOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;
                    case 16:

                        if (heldWhiteOrFree(i) && isFree(i - 8)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        break;
                    case 24:

                        if (heldWhiteOrFree(i) && isFree(i - 8) && isFree(i - 16)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;
                    case 32:

                        if (heldWhiteOrFree(i) && isFree(i - 8) && isFree(i - 16) && isFree(i - 24)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;
                    case 40:

                        if (heldWhiteOrFree(i) && isFree(i - 8) && isFree(i - 16) && isFree(i - 24) && isFree(i - 32)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;
                    case 48:

                        if (heldWhiteOrFree(i) && isFree(i - 8) && isFree(i - 16) && isFree(i - 24) && isFree(i - 32) && isFree(i - 40)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        break;
                    case 56:

                        if (heldWhiteOrFree(i) && isFree(i - 8) && isFree(i - 16) && isFree(i - 24) && isFree(i - 32) && isFree(i - 40) && isFree(i - 48)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        break;

                    // DOWN
                    case -8:
                        if (heldWhiteOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;
                    case -16:

                        if (heldWhiteOrFree(i) && isFree(i + 8)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        break;
                    case -24:

                        if (heldWhiteOrFree(i) && isFree(i + 8) && isFree(i + 16)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;
                    case -32:

                        if (heldWhiteOrFree(i) && isFree(i + 8) && isFree(i + 16) && isFree(i + 24)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;
                    case -40:

                        if (heldWhiteOrFree(i) && isFree(i + 8) && isFree(i + 16) && isFree(i + 24) && isFree(i + 32)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;
                    case -48:

                        if (heldWhiteOrFree(i) && isFree(i + 8) && isFree(i + 16) && isFree(i + 24) && isFree(i + 32) && isFree(i + 40)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        break;
                    case -56:

                        if (heldWhiteOrFree(i) && isFree(i + 8) && isFree(i + 16) && isFree(i + 24) && isFree(i + 32) && isFree(i + 40) && isFree(i + 48)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        break;


                    // RIGHT
                    case 1:
                        if (heldWhiteOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }

                        }
                        wallQueen(i);


                        break;
                    case 2:

                        if (heldWhiteOrFree(i) && isFree(i - 1)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);

                        break;
                    case 3:

                        if (heldWhiteOrFree(i) && isFree(i - 1) && isFree(i - 2)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);


                        break;
                    case 4:

                        if (heldWhiteOrFree(i) && isFree(i - 1) && isFree(i - 2) && isFree(i - 3)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);


                        break;
                    case 5:

                        if (heldWhiteOrFree(i) && isFree(i - 1) && isFree(i - 2) && isFree(i - 3) && isFree(i - 4)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);


                        break;
                    case 6:

                        if (heldWhiteOrFree(i) && isFree(i - 1) && isFree(i - 2) && isFree(i - 3) && isFree(i - 4) && isFree(i - 5)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);

                        break;
                    case 7:

                        if (heldWhiteOrFree(i) && isFree(i - 1) && isFree(i - 2) && isFree(i - 3) && isFree(i - 4) && isFree(i - 5) && isFree(i - 6)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);

                        break;

                    // LEFT
                    case -1:
                        if (heldWhiteOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);


                        break;
                    case -2:

                        if (heldWhiteOrFree(i) && isFree(i + 1)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        wallQueen(i);

                        break;
                    case -3:

                        if (heldWhiteOrFree(i) && isFree(i + 1) && isFree(i + 2)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        wallQueen(i);


                        break;
                    case -4:

                        if (heldWhiteOrFree(i) && isFree(i + 1) && isFree(i + 2) && isFree(i + 3)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        wallQueen(i);


                        break;
                    case -5:

                        if (heldWhiteOrFree(i) && isFree(i + 1) && isFree(i + 2) && isFree(i + 3) && isFree(i + 4)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        wallQueen(i);


                        break;
                    case -6:

                        if (heldWhiteOrFree(i) && isFree(i + 1) && isFree(i + 2) && isFree(i + 3) && isFree(i + 4) && isFree(i + 5)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        wallQueen(i);

                        break;
                    case -7:

                        if (heldWhiteOrFree(i) && isFree(i + 1) && isFree(i + 2) && isFree(i + 3) && isFree(i + 4) && isFree(i + 5) && isFree(i + 6)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        wallQueen(i);

                        break;


                }

            }
        }


//            __      ___    _ _       _  ___
//            \ \    / / |_ (_) |_ ___| |/ (_)_ _  __ _
//             \ \/\/ /| ' \| |  _/ -_) ' <| | ' \/ _` |
//              \_/\_/ |_||_|_|\__\___|_|\_\_|_||_\__, |
//                                                |___/
        if (selectedUnit.equals("white_king")) {
            Log.e("EYHOSEL", selectedUnit);


            for (int i = 0; i < 64; i++) {
                final int localI = i;
                final ImageView space = getSquareImageView(i);

                Log.e("EYHOSEL", gamesetList.get(i));

                // subtract int selectedSquare to get the difference of i
                int iDiff = i - selectedSquare;

                switch (iDiff) {
                    // UP
                    case 8:
                        if (heldBlackOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;

                    // DOWN
                    case -8:
                        if (heldBlackOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;

                    // RIGHT
                    case 1:
                        if (heldBlackOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;

                    // TOP-RIGHT
                    case 9:
                        if (heldBlackOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;

                    // BOTTOM-RIGHT
                    case -9:
                        if (heldBlackOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;

                    // LEFT
                    case -1:
                        if (heldBlackOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;

                    // TOP-LEFT
                    case 7:
                        if (heldBlackOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;

                    // BOTTOM-LEFT
                    case -7:
                        if (heldBlackOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;
                }
            }
        }

//             ___ _         _   _  ___
//            | _ ) |__ _ __| |_| |/ (_)_ _  __ _
//            | _ \ / _` / _| / / ' <| | ' \/ _` |
//            |___/_\__,_\__|_\_\_|\_\_|_||_\__, |
//                                          |___/
        if (selectedUnit.equals("black_king")) {
            Log.e("EYHOSEL", selectedUnit);


            for (int i = 0; i < 64; i++) {
                final int localI = i;
                final ImageView space = getSquareImageView(i);

                Log.e("EYHOSEL", gamesetList.get(i));

                // subtract int selectedSquare to get the difference of i
                int iDiff = i - selectedSquare;

                switch (iDiff) {
                    // UP
                    case 8:
                        if (heldWhiteOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;

                    // DOWN
                    case -8:
                        if (heldWhiteOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;

                    // RIGHT
                    case 1:
                        if (heldWhiteOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;

                    // TOP-RIGHT
                    case 9:
                        if (heldWhiteOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;

                    // BOTTOM-RIGHT
                    case -9:
                        if (heldWhiteOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;

                    // LEFT
                    case -1:
                        if (heldWhiteOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;

                    // TOP-LEFT
                    case 7:
                        if (heldWhiteOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;

                    // BOTTOM-LEFT
                    case -7:
                        if (heldWhiteOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;
                }
            }


        }
    }


    //Queen Fix
    //TODO: create separate queen function instead of combining rook + bishop
    private void wallQueen(int i) {
        //if ((i == 9) || (i == 18) || (i == 27) || (i == 36) || (i == 45) || (i == 54) || (i == 63) || (i == 7) ||(i == 14) || (i == 21) || (i == 28) || (i == 35) || (i == 42) || (i == 49) || (i == 56) || (i == -9) || (i == -18) || (i == -27) || (i == -36) || (i == -45) || (i == -54) || (i == -63) || (i == -7) ||(i == -14) || (i == -21) || (i == -28) || (i == -35) || (i == -42) || (i == -49) || (i == -56)) {
            wallBishop(i);
       // } else {
            wallRook(i);

      //  }

    }


    //TODO: figure out how to stop board side crossing a cleaner way
    // do not make squares which cross over the board available

    private void wallKnight() {

        if ((selectedSquare == 0) || (selectedSquare == 1) || (selectedSquare == 8) || (selectedSquare == 9) || (selectedSquare == 16) || (selectedSquare == 17) || (selectedSquare == 24) || (selectedSquare == 25) || (selectedSquare == 32) || (selectedSquare == 33) || (selectedSquare == 40) || (selectedSquare == 41) || (selectedSquare == 48) || (selectedSquare == 49) || (selectedSquare == 56) || (selectedSquare == 57))

        {
            h1.setBackgroundColor(Color.parseColor("#00FFFFFF"));
            h1.setOnClickListener(null);
            g1.setBackgroundColor(Color.parseColor("#00FFFFFF"));
            g1.setOnClickListener(null);
            h2.setBackgroundColor(Color.parseColor("#00FFFFFF"));
            h2.setOnClickListener(null);
            g2.setBackgroundColor(Color.parseColor("#00FFFFFF"));
            g2.setOnClickListener(null);
            h3.setBackgroundColor(Color.parseColor("#00FFFFFF"));
            h3.setOnClickListener(null);
            g3.setBackgroundColor(Color.parseColor("#00FFFFFF"));
            g3.setOnClickListener(null);
            h4.setBackgroundColor(Color.parseColor("#00FFFFFF"));
            h4.setOnClickListener(null);
            g4.setBackgroundColor(Color.parseColor("#00FFFFFF"));
            g4.setOnClickListener(null);
            h5.setBackgroundColor(Color.parseColor("#00FFFFFF"));
            h5.setOnClickListener(null);
            g5.setBackgroundColor(Color.parseColor("#00FFFFFF"));
            g5.setOnClickListener(null);
            h6.setBackgroundColor(Color.parseColor("#00FFFFFF"));
            h6.setOnClickListener(null);
            g6.setBackgroundColor(Color.parseColor("#00FFFFFF"));
            g6.setOnClickListener(null);
            h7.setBackgroundColor(Color.parseColor("#00FFFFFF"));
            h7.setOnClickListener(null);
            g7.setBackgroundColor(Color.parseColor("#00FFFFFF"));
            g7.setOnClickListener(null);
            h8.setBackgroundColor(Color.parseColor("#00FFFFFF"));
            h8.setOnClickListener(null);
            g8.setBackgroundColor(Color.parseColor("#00FFFFFF"));
            g8.setOnClickListener(null);
        } else if ((selectedSquare == 6) || (selectedSquare == 7) || (selectedSquare == 14) || (selectedSquare == 15) || (selectedSquare == 22) || (selectedSquare == 23) || (selectedSquare == 30) || (selectedSquare == 31) || (selectedSquare == 38) || (selectedSquare == 39) || (selectedSquare == 46) || (selectedSquare == 47) || (selectedSquare == 54) || (selectedSquare == 55) || (selectedSquare == 62) || (selectedSquare == 63))

        {
            a1.setBackgroundColor(Color.parseColor("#00FFFFFF"));
            a1.setOnClickListener(null);
            b1.setBackgroundColor(Color.parseColor("#00FFFFFF"));
            b1.setOnClickListener(null);
            a2.setBackgroundColor(Color.parseColor("#00FFFFFF"));
            a2.setOnClickListener(null);
            b2.setBackgroundColor(Color.parseColor("#00FFFFFF"));
            b2.setOnClickListener(null);
            a3.setBackgroundColor(Color.parseColor("#00FFFFFF"));
            a3.setOnClickListener(null);
            b3.setBackgroundColor(Color.parseColor("#00FFFFFF"));
            b3.setOnClickListener(null);
            a4.setBackgroundColor(Color.parseColor("#00FFFFFF"));
            a4.setOnClickListener(null);
            b4.setBackgroundColor(Color.parseColor("#00FFFFFF"));
            b4.setOnClickListener(null);
            a5.setBackgroundColor(Color.parseColor("#00FFFFFF"));
            a5.setOnClickListener(null);
            b5.setBackgroundColor(Color.parseColor("#00FFFFFF"));
            b5.setOnClickListener(null);
            a6.setBackgroundColor(Color.parseColor("#00FFFFFF"));
            a6.setOnClickListener(null);
            b6.setBackgroundColor(Color.parseColor("#00FFFFFF"));
            b6.setOnClickListener(null);
            a7.setBackgroundColor(Color.parseColor("#00FFFFFF"));
            a7.setOnClickListener(null);
            b7.setBackgroundColor(Color.parseColor("#00FFFFFF"));
            b7.setOnClickListener(null);
            a8.setBackgroundColor(Color.parseColor("#00FFFFFF"));
            a8.setOnClickListener(null);
            b8.setBackgroundColor(Color.parseColor("#00FFFFFF"));
            b8.setOnClickListener(null);


        }

    }

    // if space in row directly ahead or behind not in same column as rook, make unavailable
    //TODO: consider more elegant solution to walling off rooks at sides

    private void wallPawn(int i) {

        if ((selectedSquare == 15) || (selectedSquare == 23) || (selectedSquare == 31) || (selectedSquare == 39) || (selectedSquare == 47) || (selectedSquare == 55)) {
            if ((i == selectedSquare + 9)) {
                ImageView nulled = getSquareImageView(i);
                nulled.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                nulled.setOnClickListener(null);
            }

        }

        if ((selectedSquare == 48) || (selectedSquare == 40) || (selectedSquare == 32) || (selectedSquare == 24) || (selectedSquare == 16) || (selectedSquare == 8)) {
            if ((i == selectedSquare - 9)) {
                ImageView nulled = getSquareImageView(i);
                nulled.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                nulled.setOnClickListener(null);
            }

        }

        if ((selectedSquare == 15) || (selectedSquare == 23) || (selectedSquare == 31) || (selectedSquare == 39) || (selectedSquare == 47) || (selectedSquare == 55)) {
            if ((i == selectedSquare - 7)) {
                ImageView nulled = getSquareImageView(i);
                nulled.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                nulled.setOnClickListener(null);
            }

        }

        if ((selectedSquare == 48) || (selectedSquare == 40) || (selectedSquare == 32) || (selectedSquare == 24) || (selectedSquare == 16) || (selectedSquare == 8)) {
            if ((i == selectedSquare + 7)) {
                ImageView nulled = getSquareImageView(i);
                nulled.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                nulled.setOnClickListener(null);
            }

        }
    }


    // if space in row directly ahead or behind not in same column as rook, make unavailable
    //TODO: consider more elegant solution to walling off rooks at sides

    private void wallRook(int i) {

        if (selectedSquare <= 7 && ((i == 8) || (i == 9) || (i == 10) || (i == 11) || (i == 12) || (i == 13) || (i == 14) || (i == 15))) {
            if (i != selectedSquare + 8) {
                ImageView nulled = getSquareImageView(i);
                nulled.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                nulled.setOnClickListener(null);
            }

        }

        if ((selectedSquare >= 8 && selectedSquare <= 15) && ((i == 0) || (i == 1) || (i == 2) || (i == 3) || (i == 4) || (i == 5) || (i == 6) || (i == 7) || (i == 16) || (i == 17) || (i == 18) || (i == 19) || (i == 20) || (i == 21) || (i == 22) || (i == 23))) {

            if (i != selectedSquare + 8) {
                ImageView nulled = getSquareImageView(i);
                nulled.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                nulled.setOnClickListener(null);
            }
            if (i != selectedSquare - 8) {
                ImageView nulled = getSquareImageView(i);
                nulled.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                nulled.setOnClickListener(null);
            }
        }


        if ((selectedSquare >= 16 && selectedSquare <= 23) && ((i == 8) || (i == 9) || (i == 10) || (i == 11) || (i == 12) || (i == 13) || (i == 14) || (i == 15) || (i == 24) || (i == 25) || (i == 26) || (i == 27) || (i == 28) || (i == 29) || (i == 30) || (i == 31))) {

            if (i != selectedSquare + 8) {
                ImageView nulled = getSquareImageView(i);
                nulled.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                nulled.setOnClickListener(null);
            }
            if (i != selectedSquare - 8) {
                ImageView nulled = getSquareImageView(i);
                nulled.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                nulled.setOnClickListener(null);
            }
        }


        if ((selectedSquare >= 24 && selectedSquare <= 31) && ((i == 16) || (i == 17) || (i == 18) || (i == 19) || (i == 20) || (i == 21) || (i == 22) || (i == 23) || (i == 32) || (i == 33) || (i == 34) || (i == 35) || (i == 36) || (i == 37) || (i == 38) || (i == 39))) {

            if (i != selectedSquare + 8) {
                ImageView nulled = getSquareImageView(i);
                nulled.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                nulled.setOnClickListener(null);
            }
            if (i != selectedSquare - 8) {
                ImageView nulled = getSquareImageView(i);
                nulled.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                nulled.setOnClickListener(null);
            }
        }


        if ((selectedSquare >= 32 && selectedSquare <= 39) && ((i == 24) || (i == 25) || (i == 26) || (i == 27) || (i == 28) || (i == 29) || (i == 30) || (i == 31) || (i == 40) || (i == 41) || (i == 42) || (i == 43) || (i == 44) || (i == 45) || (i == 46) || (i == 47))) {

            if (i != selectedSquare + 8) {
                ImageView nulled = getSquareImageView(i);
                nulled.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                nulled.setOnClickListener(null);
            }
            if (i != selectedSquare - 8) {
                ImageView nulled = getSquareImageView(i);
                nulled.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                nulled.setOnClickListener(null);
            }
        }


        if ((selectedSquare >= 40 && selectedSquare <= 47) && ((i == 32) || (i == 33 || (i == 34) || (i == 35) || (i == 36) || (i == 37) || (i == 38) || (i == 39) || (i == 48) || (i == 49) || (i == 50) || (i == 51) || (i == 52) || (i == 53)) || (i == 54) || (i == 55))) {

            if (i != selectedSquare + 8) {
                ImageView nulled = getSquareImageView(i);
                nulled.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                nulled.setOnClickListener(null);
            }
            if (i != selectedSquare - 8) {
                ImageView nulled = getSquareImageView(i);
                nulled.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                nulled.setOnClickListener(null);
            }
        }

        if ((selectedSquare >= 48 && selectedSquare <= 56) && ((i == 40) || (i == 41) || (i == 42) || (i == 43) || (i == 44) || (i == 45) || (i == 46) || (i == 47) || (i == 56) || (i == 57) || (i == 58) || (i == 59) || (i == 60) || (i == 61) || (i == 62) || (i == 63))) {

            if (i != selectedSquare + 8) {
                ImageView nulled = getSquareImageView(i);
                nulled.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                nulled.setOnClickListener(null);
            }
            if (i != selectedSquare - 8) {
                ImageView nulled = getSquareImageView(i);
                nulled.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                nulled.setOnClickListener(null);
            }
        }

        if ((selectedSquare >= 56 && selectedSquare <= 63) && ((i == 48) || (i == 49) || (i == 50) || (i == 51) || (i == 52) || (i == 53) || (i == 54) || (i == 55))) {
            if (i != selectedSquare - 8) {
                ImageView nulled = getSquareImageView(i);
                nulled.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                nulled.setOnClickListener(null);
            }
        }

    }

    // if space is not the same color as that stood on by the bishop
    // make unavailable

    private void wallBishop(int i) {
        if (isWhite(selectedSquare)) {
            if (isBlack(i)) {
                ImageView nulled = getSquareImageView(i);
                nulled.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                nulled.setOnClickListener(null);
            }
        }
        if (isBlack(selectedSquare)) {
            if (isWhite(i)) {
                ImageView nulled = getSquareImageView(i);
                nulled.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                nulled.setOnClickListener(null);
            }
        }

    }


    // Check if a square is free
    private Boolean isFree(int i) {
        return (gamesetList.get(i).equals("free_square"));
    }

    private Boolean isWhite(int i) {
        return ((i == 1) || (i == 3) || (i == 5) || (i == 7) || (i == 8) || (i == 10) || (i == 12) || (i == 14) || (i == 17) || (i == 19) || (i == 21) || (i == 23) || (i == 24) || (i == 26) || (i == 28) || (i == 30) || (i == 33) || (i == 35) || (i == 37) || (i == 39) || (i == 40) || (i == 42) || (i == 44) || (i == 46) || (i == 49) || (i == 51) || (i == 53) || (i == 55) || (i == 56) || (i == 58) || (i == 60) || (i == 62));
    }

    private Boolean isBlack(int i) {
        return ((i == 0) || (i == 2) || (i == 4) || (i == 6) || (i == 9) || (i == 11) || (i == 13) || (i == 15) || (i == 16) || (i == 18) || (i == 20) || (i == 22) || (i == 25) || (i == 27) || (i == 29) || (i == 31) || (i == 32) || (i == 34) || (i == 36) || (i == 38) || (i == 41) || (i == 43) || (i == 45) || (i == 47) || (i == 48) || (i == 50) || (i == 52) || (i == 54) || (i == 57) || (i == 59) || (i == 61) || (i == 63));
    }

    // Check if a square is either black or free
    private Boolean heldBlackOrFree(int i) {
        return ((gamesetList.get(i).equals("free_square")) || (gamesetList.get(i).equals("black_pawn")) || (gamesetList.get(i).equals("black_king")) || (gamesetList.get(i).equals("black_queen")) || (gamesetList.get(i).equals("black_bishop")) || (gamesetList.get(i).equals("black_knight")) || (gamesetList.get(i).equals("black_rook")));
    }

    // Check if a square is either white or free
    private Boolean heldWhiteOrFree(int i) {
        return ((gamesetList.get(i).equals("free_square")) || (gamesetList.get(i).equals("white_pawn")) || (gamesetList.get(i).equals("white_king")) || (gamesetList.get(i).equals("white_queen")) || (gamesetList.get(i).equals("white_bishop")) || (gamesetList.get(i).equals("white_knight")) || (gamesetList.get(i).equals("white_rook")));
    }


    private void moveGamepiece(int moveTo) {

        // Draw piece in new location
        // chance in gamesetList, then gamesetString // clear values of selectedUnit and selectedSquare
        if (selectedUnit != "") {
            ImageView space = getSquareImageView(moveTo);
            space.setImageResource(getResources().getIdentifier(selectedUnit, "drawable", getPackageName()));

            // change gamesetList
            gamesetList.set(moveTo, selectedUnit);
            if (selectedSquare != 99) {
                gamesetList.set(selectedSquare, "free_square");
            }

            selectedUnit = "";
            selectedSquare = 99;


            // delete piece from previous location
            if (getSquareImageView(selectedSquare) != null) {
                getSquareImageView(selectedSquare).setImageResource(getResources().getIdentifier("free_square", "drawable", getPackageName()));
            }


        }

        gamesetString = "";
        for (int i = 0; i < gamesetList.size(); i++) {
            if (i != 0) {
                gamesetString = gamesetString + "," + gamesetList.get(i);
            }
            if (i == 0) {
                gamesetString = gamesetList.get(i);
            }
        }

        Log.e("EYHO9", gamesetString);

        mGamesDatabaseReference.child(match_id).child("board").setValue(gamesetString);

        if (turn.equals("white")) {
            mGamesDatabaseReference.child(match_id).child("turn_color").setValue("black");
        } else if (turn.equals("black")) {
            mGamesDatabaseReference.child(match_id).child("turn_color").setValue("white");
        }


        // TODO: verify player cannot play twice in one turn if they play quickly
        // Null onclicks and change turn
        switch (turn) {
            case "white":
                turn = "black";
                currentTurn.setText("turn: BLACK");
                currentTurn.setBackgroundColor(Color.parseColor("#000000"));
                currentTurn.setTextColor(Color.parseColor("#FFFFFF"));

                if (playerColor.equals("white")) {
                    for (int i = 0; i < 64; i++) {
                        getSquareImageView(i).setOnClickListener(null);
                    }
                }
                break;
            case "black":
                turn = "white";
                currentTurn.setText("turn: WHITE");
                currentTurn.setBackgroundColor(Color.parseColor("#FFFFFF"));
                currentTurn.setTextColor(Color.parseColor("#000000"));

                if (playerColor.equals("black")) {
                    for (int i = 0; i < 64; i++) {
                        getSquareImageView(i).setOnClickListener(null);
                    }
                }
                break;
        }


        Log.e("Turn", turn);

        clearSelected();

        // Reset board based on new gamesetString
        // TODO: use firebase realtime database instead of refreshing setBoard
        setBoard();

    }

    // Clear backgrounds from selected / possible moves

    private void clearSelected() {
        for (int i = 0; i < 64; i++) {
            ImageView space = (ImageView) getSquareImageView(i);
            space.setBackgroundColor(Color.parseColor("#00FFFFFF"));

            selectedSquare = 99;
            selectedUnit = "";
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
    }
}
