package ca.ggolda.android_tab_chess;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FirebaseActiveViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

    List<InstanceGame> data = Collections.emptyList();

    View mView;
    Context mContext;

    InstanceGame current;

    public FirebaseActiveViewHolder(View gameView) {
        super(gameView);
        mView = gameView;
        mContext = gameView.getContext();
        gameView.setOnClickListener(this);
    }

    public void bindItem(InstanceGame game) {

        current = game;

        TextView nameTextView = (TextView) mView.findViewById(R.id.username);
        nameTextView.setText(game.getUsername_white());


    }

    @Override
    public void onClick(final View view) {
        final ArrayList<InstanceGame> games = new ArrayList<>();


        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("games");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    games.add(snapshot.getValue(InstanceGame.class));
                }

                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final int position = getLayoutPosition();

                        //        InstanceGame game = data.get(position);

                        if (current.getMatch_id() != null) {

                            Intent intent = new Intent(mContext, GameActivity.class);
                            intent.putExtra("MATCH_ID", current.getMatch_id());
                            mContext.startActivity(intent);
                        }
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}