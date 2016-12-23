package ca.ggolda.android_tab_chess;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import java.util.List;

public class AdapterActive extends ArrayAdapter<InstanceGame> {

    String mUserID;
    FirebaseAuth mFirebaseAuth;

    public AdapterActive(Context context, int resource, List<InstanceGame> objects) {
        super(context, resource, objects);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.card_game, parent, false);
        }

        // Get UserID
        mFirebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mFirebaseAuth.getCurrentUser();
        mUserID = user.getUid();


        final InstanceGame current = getItem(position);

        final TextView match_id = (TextView) convertView.findViewById(R.id.match_id);
        match_id.setText(current.getMatch_id());


        convertView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Log.e("GAMESET",  "withintent"+current.getMatch_id());

                Intent intent = new Intent(getContext(), BoardActivity.class);
                intent.putExtra("MATCH_ID", current.getMatch_id());
                getContext().startActivity(intent);

            }
        });

        return convertView;


    }


}
