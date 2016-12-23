package ca.ggolda.android_tab_chess;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by gcgol on 12/22/2016.
 */

public class LobbyActivity extends AppCompatActivity {

    private FirebaseAuth.AuthStateListener authListener;
    private String userId;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mGamesDatabaseReference;
    private DatabaseReference mUsersDatabaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);


        ArrayList<InstanceGame> games = new ArrayList<>();


        AdapterActive mAdapterActive = new AdapterActive(LobbyActivity.this, R.layout.card_game, games);
        ListView mListViewActive = (ListView) findViewById(R.id.active_listview);
        mListViewActive.setAdapter(mAdapterActive);


        //get current user and send to login screen if user is null
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        userId = user.getUid();

        Log.e("USER", "" + userId);

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(LobbyActivity.this, LoginActivity.class));
                    finish();
                }
            }
        };

        mFirebaseDatabase = FirebaseDatabase.getInstance();

        mGamesDatabaseReference = mFirebaseDatabase.getReference().child("games");
        mUsersDatabaseReference = mFirebaseDatabase.getReference().child("users");


        //TODO: accept already pending offer
        //create a random game offer
        TextView matchRandom = (TextView) findViewById(R.id.random_button);
        matchRandom.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                mGamesDatabaseReference.child("offers").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        final String offer = dataSnapshot.getValue(String.class);


                        Log.e("USER", "Offeryo" + offer);

                        //if offer/accept offer
                        // TODO: possibly allow offers to hold multiple offer games
                        if (offer != null) {

                            mGamesDatabaseReference.child(offer).child("white").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    String player_white = dataSnapshot.getValue(String.class);

                                    Log.e("USER", "player_white " + player_white);

                                    if (player_white.equals(userId)) {
                                        Toast.makeText(LobbyActivity.this, "Please Wait...", Toast.LENGTH_SHORT).show();

                                    } else {
                                        // TODO: something to ensure these all happen
                                        mGamesDatabaseReference.child(offer).child("black").setValue(userId);
                                        mUsersDatabaseReference.child(userId).child("games").setValue(offer);
                                        mGamesDatabaseReference.child("offers").removeValue();
                                    }

                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                    System.out.println("The read failed: " + databaseError.getCode());
                                }
                            });

                        } else {
                            String eventId = mGamesDatabaseReference.push().getKey();

                            //TODO: ensure these all happen
                            mGamesDatabaseReference.child(eventId).child("white").setValue(userId);
                            String newBoard = getResources().getString(R.string.new_board);
                            mGamesDatabaseReference.child(eventId).child("board").setValue(newBoard);
                            mGamesDatabaseReference.child(eventId).child("turn_color").setValue("white");
                            mUsersDatabaseReference.child(userId).child("games").setValue(eventId);
                            mGamesDatabaseReference.child("offers").setValue(eventId);
                        }


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        System.out.println("The read failed: " + databaseError.getCode());
                    }
                });


            }
        });

        // Get user games for Active list
        mUsersDatabaseReference.child(userId).child("games").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.getValue() != null) {
                    final String game = "" + dataSnapshot.getValue(String.class);

                    if (dataSnapshot.getValue(String.class) != null) {
                        final TextView playCurrent = (TextView) findViewById(R.id.play_current);
                        playCurrent.setVisibility(View.VISIBLE);
                        playCurrent.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                Intent intent = new Intent(LobbyActivity.this, BoardActivity.class);
                                intent.putExtra("MATCH_ID", game);
                                startActivity(intent);

                                finish();
                            }
                        });
                    }
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });


    }


}
