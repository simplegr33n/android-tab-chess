package ca.ggolda.android_tab_chess;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
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

    private String username;

    private ArrayList<InstanceGame> games;
    private AdapterActive mAdapterActive;
    private ListView mListViewActive;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);


        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mGamesDatabaseReference = mFirebaseDatabase.getReference().child("games");
        mUsersDatabaseReference = mFirebaseDatabase.getReference().child("users");

        final EditText editUsername = (EditText) findViewById(R.id.username_edittext);


        // Set Preview Board
        BoardFragment emptyFragment = new BoardFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_container, emptyFragment);
        transaction.commit();


        //get current user and send to login screen if user is null
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        userId = user.getUid();

        Log.e("USER", "" + userId);



    }

    @Override
    protected void onResume() {
        super.onResume();

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mGamesDatabaseReference = mFirebaseDatabase.getReference().child("games");
        mUsersDatabaseReference = mFirebaseDatabase.getReference().child("users");

        final EditText editUsername = (EditText) findViewById(R.id.username_edittext);


        // Set Preview Board
        BoardFragment emptyFragment = new BoardFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_container, emptyFragment);
        transaction.commit();


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


        TextView setUsername = (TextView) findViewById(R.id.set_username);
        setUsername.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                username = editUsername.getText().toString();
                Log.e("GAMEEE", "" + username);
                if (username != null) {
                    mUsersDatabaseReference.child(userId).child("username").setValue(username);

                }

            }


        });

        // Ensure user has username
        mUsersDatabaseReference.child(userId).child("username").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Log.e("USER", "GAME2uname " + dataSnapshot.getValue());

                if (dataSnapshot.getValue() == null) {
                    LinearLayout usernameLayout = (LinearLayout) findViewById(R.id.username_layout);
                    usernameLayout.setVisibility(View.VISIBLE);

                }

                if (dataSnapshot.getValue() != null) {
                    LinearLayout usernameLayout = (LinearLayout) findViewById(R.id.username_layout);
                    usernameLayout.setVisibility(View.GONE);

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


        //create a random game offer
        TextView matchRandom = (TextView) findViewById(R.id.random_button);
        matchRandom.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                mGamesDatabaseReference.child("offers").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {


                        // TODO: just get game key rather than splitting {akdffkadfjsdkfdf=true} strings
                        String temp = ("" + dataSnapshot.getValue()).split("=")[0];

                        Log.e("USER", "temp" + temp);

                        //if offer/accept offer
                        // TODO: possibly allow offers to hold multiple offer games
                        if (dataSnapshot.getValue() != null) {

                            //TODO: remove stringbuilder
                            StringBuilder sb = new StringBuilder(temp);
                            sb.deleteCharAt(0);
                            final String offer = sb.toString();
                            Log.e("USEROH", "offer" + offer);

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
                                        mUsersDatabaseReference.child(userId).child("games").child(offer).setValue(true);
                                        mGamesDatabaseReference.child(offer).child("username_black").setValue(username);
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
                            mUsersDatabaseReference.child(userId).child("games").child(eventId).setValue(true);
                            mGamesDatabaseReference.child("offers").child(eventId).setValue(true);
                            mGamesDatabaseReference.child(eventId).child("match_id").setValue(eventId);
                            mGamesDatabaseReference.child(eventId).child("username_white").setValue(username);
                        }


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        System.out.println("The read failed: " + databaseError.getCode());
                    }
                });


            }
        });


        // Set active games list

        games = new ArrayList<>();
        mAdapterActive = new AdapterActive(LobbyActivity.this, R.layout.card_game, games);
        mListViewActive = (ListView) findViewById(R.id.active_listview);
        mListViewActive.setAdapter(mAdapterActive);


        //       refreshActive();

        userGamesChildListener();


    }

    @Override
    protected void onPause() {
        super.onPause();

        // clearAdapter();

    }


    private void userGamesChildListener() {
        mUsersDatabaseReference.child(userId).child("games").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {


                Log.e("GAME", "GETKEY" + dataSnapshot.getKey());

                mGamesDatabaseReference.child(dataSnapshot.getKey()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        if (dataSnapshot.getValue(InstanceGame.class) != null) {
                            games.add(dataSnapshot.getValue(InstanceGame.class));

                        }

                        mAdapterActive.notifyDataSetChanged();


                        //mAdapterActive.clear();

//                        mListViewActive.setAdapter(mAdapterActive);
                        //refreshActive();
                        // clearAdapter();


//                        mAdapterActive.notifyDataSetChanged();


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {


            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


    }


    private void clearAdapter() {
        if (mAdapterActive != null) {
            mAdapterActive.clear();
            //    mAdapterActive = new AdapterActive(LobbyActivity.this, R.layout.card_game, games);
//            mListViewActive.setAdapter(mAdapterActive);

        }
    }

    private void refreshActive() {
        if (games != null) {
            games.clear();
        }
        if (mAdapterActive != null) {
            mAdapterActive.clear();
            //    mAdapterActive = new AdapterActive(LobbyActivity.this, R.layout.card_game, games);
            mListViewActive.setAdapter(mAdapterActive);
        }


      //  userGamesChildListener();

    }


}
