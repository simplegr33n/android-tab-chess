package ca.ggolda.android_tab_chess;

import android.content.Context;
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

    public static ImageView a1;
    public static ImageView a2;
    public static ImageView a3;
    public static ImageView a4;
    public static ImageView a5;
    public static ImageView a6;
    public static ImageView a7;
    public static ImageView a8;

    public static ImageView b1;
    public static ImageView b2;
    public static ImageView b3;
    public static ImageView b4;
    public static ImageView b5;
    public static ImageView b6;
    public static ImageView b7;
    public static ImageView b8;

    public static ImageView c1;
    public static ImageView c2;
    public static ImageView c3;
    public static ImageView c4;
    public static ImageView c5;
    public static ImageView c6;
    public static ImageView c7;
    public static ImageView c8;

    public static ImageView d1;
    public static ImageView d2;
    public static ImageView d3;
    public static ImageView d4;
    public static ImageView d5;
    public static ImageView d6;
    public static ImageView d7;
    public static ImageView d8;

    public static ImageView e1;
    public static ImageView e2;
    public static ImageView e3;
    public static ImageView e4;
    public static ImageView e5;
    public static ImageView e6;
    public static ImageView e7;
    public static ImageView e8;

    public static ImageView f1;
    public static ImageView f2;
    public static ImageView f3;
    public static ImageView f4;
    public static ImageView f5;
    public static ImageView f6;
    public static ImageView f7;
    public static ImageView f8;

    public static ImageView g1;
    public static ImageView g2;
    public static ImageView g3;
    public static ImageView g4;
    public static ImageView g5;
    public static ImageView g6;
    public static ImageView g7;
    public static ImageView g8;

    public static ImageView h1;
    public static ImageView h2;
    public static ImageView h3;
    public static ImageView h4;
    public static ImageView h5;
    public static ImageView h6;
    public static ImageView h7;
    public static ImageView h8;

    private static TextView logs;

    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseDatabase mFirebaseDatabase;

    private static Context mContext;


    private String userId;

    // All static for Movement.java
    public static String gamesetString = "";
    public static List<String> gamesetList;
    public static int selectedSquare = 99;
    public static String selectedUnit = "";
    public static String playerColor;
    public static String turn = "";
    public static TextView currentTurn;
    public static DatabaseReference mGamesDatabaseReference;
    public static String match_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mContext = this;


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

                Log.e("GAMESET", "" + match_id);
                Log.e("GAMESET", "" + dataSnapshot.getValue(String.class));

                gamesetString = dataSnapshot.getValue(String.class);


                // Get turn color from firebase
                mGamesDatabaseReference.child(match_id).child("turn_color").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        turn = dataSnapshot.getValue(String.class);

                        Log.e("turn_color-ref", turn);

                        if (turn.equals("black")) {
                            currentTurn.setText("turn: BLACK");
                            currentTurn.setBackgroundColor(Color.parseColor("#000000"));
                            currentTurn.setTextColor(Color.parseColor("#FFFFFF"));

                        } else if (turn.equals("white")) {
                            currentTurn.setText("turn: WHITE");
                            currentTurn.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            currentTurn.setTextColor(Color.parseColor("#000000"));
                        }

                        clearSelected();

                        setBoard();

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        System.out.println("The read failed: " + databaseError.getCode());
                    }
                });


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });


    }

    public static void setBoard() {

        logs.setText(gamesetString);

        gamesetList = Arrays.asList(gamesetString.split("\\s*,\\s*"));

        a1.setImageResource(getContext().getResources().getIdentifier(gamesetList.get(0), "drawable", getContext().getPackageName()));
        returnSelectable(0, a1);
        b1.setImageResource(getContext().getResources().getIdentifier(gamesetList.get(1), "drawable", getContext().getPackageName()));
        returnSelectable(1, b1);
        c1.setImageResource(getContext().getResources().getIdentifier(gamesetList.get(2), "drawable", getContext().getPackageName()));
        returnSelectable(2, c1);
        d1.setImageResource(getContext().getResources().getIdentifier(gamesetList.get(3), "drawable", getContext().getPackageName()));
        returnSelectable(3, d1);
        e1.setImageResource(getContext().getResources().getIdentifier(gamesetList.get(4), "drawable", getContext().getPackageName()));
        returnSelectable(4, e1);
        f1.setImageResource(getContext().getResources().getIdentifier(gamesetList.get(5), "drawable", getContext().getPackageName()));
        returnSelectable(5, f1);
        g1.setImageResource(getContext().getResources().getIdentifier(gamesetList.get(6), "drawable", getContext().getPackageName()));
        returnSelectable(6, g1);
        h1.setImageResource(getContext().getResources().getIdentifier(gamesetList.get(7), "drawable", getContext().getPackageName()));
        returnSelectable(7, h1);

        a2.setImageResource(getContext().getResources().getIdentifier(gamesetList.get(8), "drawable", getContext().getPackageName()));
        returnSelectable(8, a2);
        b2.setImageResource(getContext().getResources().getIdentifier(gamesetList.get(9), "drawable", getContext().getPackageName()));
        returnSelectable(9, b2);
        c2.setImageResource(getContext().getResources().getIdentifier(gamesetList.get(10), "drawable", getContext().getPackageName()));
        returnSelectable(10, c2);
        d2.setImageResource(getContext().getResources().getIdentifier(gamesetList.get(11), "drawable", getContext().getPackageName()));
        returnSelectable(11, d2);
        e2.setImageResource(getContext().getResources().getIdentifier(gamesetList.get(12), "drawable", getContext().getPackageName()));
        returnSelectable(12, e2);
        f2.setImageResource(getContext().getResources().getIdentifier(gamesetList.get(13), "drawable", getContext().getPackageName()));
        returnSelectable(13, f2);
        g2.setImageResource(getContext().getResources().getIdentifier(gamesetList.get(14), "drawable", getContext().getPackageName()));
        returnSelectable(14, g2);
        h2.setImageResource(getContext().getResources().getIdentifier(gamesetList.get(15), "drawable", getContext().getPackageName()));
        returnSelectable(15, h2);

        a3.setImageResource(getContext().getResources().getIdentifier(gamesetList.get(16), "drawable", getContext().getPackageName()));
        returnSelectable(16, a3);
        b3.setImageResource(getContext().getResources().getIdentifier(gamesetList.get(17), "drawable", getContext().getPackageName()));
        returnSelectable(17, b3);
        c3.setImageResource(getContext().getResources().getIdentifier(gamesetList.get(18), "drawable", getContext().getPackageName()));
        returnSelectable(18, c3);
        d3.setImageResource(getContext().getResources().getIdentifier(gamesetList.get(19), "drawable", getContext().getPackageName()));
        returnSelectable(19, d3);
        e3.setImageResource(getContext().getResources().getIdentifier(gamesetList.get(20), "drawable", getContext().getPackageName()));
        returnSelectable(20, e3);
        f3.setImageResource(getContext().getResources().getIdentifier(gamesetList.get(21), "drawable", getContext().getPackageName()));
        returnSelectable(21, f3);
        g3.setImageResource(getContext().getResources().getIdentifier(gamesetList.get(22), "drawable", getContext().getPackageName()));
        returnSelectable(22, g3);
        h3.setImageResource(getContext().getResources().getIdentifier(gamesetList.get(23), "drawable", getContext().getPackageName()));
        returnSelectable(23, h3);

        a4.setImageResource(getContext().getResources().getIdentifier(gamesetList.get(24), "drawable", getContext().getPackageName()));
        returnSelectable(24, a4);
        b4.setImageResource(getContext().getResources().getIdentifier(gamesetList.get(25), "drawable", getContext().getPackageName()));
        returnSelectable(25, b4);
        c4.setImageResource(getContext().getResources().getIdentifier(gamesetList.get(26), "drawable", getContext().getPackageName()));
        returnSelectable(26, c4);
        d4.setImageResource(getContext().getResources().getIdentifier(gamesetList.get(27), "drawable", getContext().getPackageName()));
        returnSelectable(27, d4);
        e4.setImageResource(getContext().getResources().getIdentifier(gamesetList.get(28), "drawable", getContext().getPackageName()));
        returnSelectable(28, e4);
        f4.setImageResource(getContext().getResources().getIdentifier(gamesetList.get(29), "drawable", getContext().getPackageName()));
        returnSelectable(29, f4);
        g4.setImageResource(getContext().getResources().getIdentifier(gamesetList.get(30), "drawable", getContext().getPackageName()));
        returnSelectable(30, g4);
        h4.setImageResource(getContext().getResources().getIdentifier(gamesetList.get(31), "drawable", getContext().getPackageName()));
        returnSelectable(31, h4);

        a5.setImageResource(getContext().getResources().getIdentifier(gamesetList.get(32), "drawable", getContext().getPackageName()));
        returnSelectable(32, a5);
        b5.setImageResource(getContext().getResources().getIdentifier(gamesetList.get(33), "drawable", getContext().getPackageName()));
        returnSelectable(33, b5);
        c5.setImageResource(getContext().getResources().getIdentifier(gamesetList.get(34), "drawable", getContext().getPackageName()));
        returnSelectable(34, c5);
        d5.setImageResource(getContext().getResources().getIdentifier(gamesetList.get(35), "drawable", getContext().getPackageName()));
        returnSelectable(35, d5);
        e5.setImageResource(getContext().getResources().getIdentifier(gamesetList.get(36), "drawable", getContext().getPackageName()));
        returnSelectable(36, e5);
        f5.setImageResource(getContext().getResources().getIdentifier(gamesetList.get(37), "drawable", getContext().getPackageName()));
        returnSelectable(37, f5);
        g5.setImageResource(getContext().getResources().getIdentifier(gamesetList.get(38), "drawable", getContext().getPackageName()));
        returnSelectable(38, g5);
        h5.setImageResource(getContext().getResources().getIdentifier(gamesetList.get(39), "drawable", getContext().getPackageName()));
        returnSelectable(39, h5);

        a6.setImageResource(getContext().getResources().getIdentifier(gamesetList.get(40), "drawable", getContext().getPackageName()));
        returnSelectable(40, a6);
        b6.setImageResource(getContext().getResources().getIdentifier(gamesetList.get(41), "drawable", getContext().getPackageName()));
        returnSelectable(41, b6);
        c6.setImageResource(getContext().getResources().getIdentifier(gamesetList.get(42), "drawable", getContext().getPackageName()));
        returnSelectable(42, c6);
        d6.setImageResource(getContext().getResources().getIdentifier(gamesetList.get(43), "drawable", getContext().getPackageName()));
        returnSelectable(43, d6);
        e6.setImageResource(getContext().getResources().getIdentifier(gamesetList.get(44), "drawable", getContext().getPackageName()));
        returnSelectable(44, e6);
        f6.setImageResource(getContext().getResources().getIdentifier(gamesetList.get(45), "drawable", getContext().getPackageName()));
        returnSelectable(45, f6);
        g6.setImageResource(getContext().getResources().getIdentifier(gamesetList.get(46), "drawable", getContext().getPackageName()));
        returnSelectable(46, g6);
        h6.setImageResource(getContext().getResources().getIdentifier(gamesetList.get(47), "drawable", getContext().getPackageName()));
        returnSelectable(47, h6);

        a7.setImageResource(getContext().getResources().getIdentifier(gamesetList.get(48), "drawable", getContext().getPackageName()));
        returnSelectable(48, a7);
        b7.setImageResource(getContext().getResources().getIdentifier(gamesetList.get(49), "drawable", getContext().getPackageName()));
        returnSelectable(49, b7);
        c7.setImageResource(getContext().getResources().getIdentifier(gamesetList.get(50), "drawable", getContext().getPackageName()));
        returnSelectable(50, c7);
        d7.setImageResource(getContext().getResources().getIdentifier(gamesetList.get(51), "drawable", getContext().getPackageName()));
        returnSelectable(51, d7);
        e7.setImageResource(getContext().getResources().getIdentifier(gamesetList.get(52), "drawable", getContext().getPackageName()));
        returnSelectable(52, e7);
        f7.setImageResource(getContext().getResources().getIdentifier(gamesetList.get(53), "drawable", getContext().getPackageName()));
        returnSelectable(53, f7);
        g7.setImageResource(getContext().getResources().getIdentifier(gamesetList.get(54), "drawable", getContext().getPackageName()));
        returnSelectable(54, g7);
        h7.setImageResource(getContext().getResources().getIdentifier(gamesetList.get(55), "drawable", getContext().getPackageName()));
        returnSelectable(55, h7);

        a8.setImageResource(getContext().getResources().getIdentifier(gamesetList.get(56), "drawable", getContext().getPackageName()));
        returnSelectable(56, a8);
        b8.setImageResource(getContext().getResources().getIdentifier(gamesetList.get(57), "drawable", getContext().getPackageName()));
        returnSelectable(57, b8);
        c8.setImageResource(getContext().getResources().getIdentifier(gamesetList.get(58), "drawable", getContext().getPackageName()));
        returnSelectable(58, c8);
        d8.setImageResource(getContext().getResources().getIdentifier(gamesetList.get(59), "drawable", getContext().getPackageName()));
        returnSelectable(59, d8);
        e8.setImageResource(getContext().getResources().getIdentifier(gamesetList.get(60), "drawable", getContext().getPackageName()));
        returnSelectable(60, e8);
        f8.setImageResource(getContext().getResources().getIdentifier(gamesetList.get(61), "drawable", getContext().getPackageName()));
        returnSelectable(61, f8);
        g8.setImageResource(getContext().getResources().getIdentifier(gamesetList.get(62), "drawable", getContext().getPackageName()));
        returnSelectable(62, g8);
        h8.setImageResource(getContext().getResources().getIdentifier(gamesetList.get(63), "drawable", getContext().getPackageName()));
        returnSelectable(63, h8);
    }

    // Move the gamepiece
    public static void moveGamepiece(int moveTo) {

        // Draw piece in new location
        // chance in gamesetList, then gamesetString // clear values of selectedUnit and selectedSquare
        if (selectedUnit != "") {
            ImageView space = getSquareImageView(moveTo);
            space.setImageResource(GameActivity.getContext().getResources().getIdentifier(selectedUnit, "drawable", GameActivity.getContext().getPackageName()));

            // change gamesetList
            gamesetList.set(moveTo, selectedUnit);
            if (selectedSquare != 99) {
                gamesetList.set(selectedSquare, "free_square");
            }

            selectedUnit = "";
            selectedSquare = 99;


            // delete piece from previous location
            if (getSquareImageView(selectedSquare) != null) {
                getSquareImageView(selectedSquare).setImageResource(GameActivity.getContext().getResources().getIdentifier("free_square", "drawable", GameActivity.getContext().getPackageName()));
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

    private static void returnSelectable(int square, final ImageView squareImageView) {
        // if turn is white
        if (turn.equals("white") && (gamesetList.get(square).split("_")[0]).equals("white") && playerColor.equals("white")) {

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

        // if turn is black
        if (turn.equals("black") && (playerColor.equals("black") && (gamesetList.get(square).split("_")[0]).equals("black"))) {

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


    private static void isSelected(int square, ImageView squareImageView) {

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


        //Logging
        Log.e("TEST: ", "" + selectedUnit);
        Log.e("TEST: ", "" + String.valueOf(selectedSquare));

        // Check for possible moves
        Movement.possibleMoves();

    }

    // Clear backgrounds from selected / possible moves
    private static void clearSelected() {
        for (int i = 0; i < 64; i++) {
            ImageView space = (ImageView) getSquareImageView(i);
            space.setBackgroundColor(Color.parseColor("#00FFFFFF"));

            selectedSquare = 99;
            selectedUnit = "";
        }
    }

    // to conveniently get ImageView from the int value of the square
    // TODO: there are definitely more clever/efficient ways to do this
    private static ImageView getSquareImageView(int square) {
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

    public static Context getContext() {
        return mContext;
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
    }

}
