package ca.ggolda.android_tab_chess;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
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

        TextView match_id = (TextView) convertView.findViewById(R.id.match_id);
        match_id.setText(current.getmGameId());


        convertView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

//                // Get parent activity, set container view
//                MainTransactionsActivity TActivity = (MainTransactionsActivity) getContext();
//
//                FragContract contractFragment = new FragContract();
//                Bundle bundle = new Bundle();
//                bundle.putParcelable("current_transaction", current);
//                contractFragment.setArguments(bundle);
//
//                TActivity.getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.fragment_container, contractFragment)
//                        .addToBackStack(null)
//                        .commit();

            }
        });

        return convertView;




    }


}
