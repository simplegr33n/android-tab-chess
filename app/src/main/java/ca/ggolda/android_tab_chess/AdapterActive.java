package ca.ggolda.android_tab_chess;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.List;

public class AdapterActive extends ArrayAdapter<InstanceGame> {


    public AdapterActive(Context context, int resource, List<InstanceGame> objects) {
        super(context, resource, objects);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.card_game, parent, false);
        }

        final InstanceGame current = getItem(position);

        InstanceGame game = current;

        Log.e("ADAPTERCHILDEV",""+game.getBlack());


        TextView nameTextView = (TextView) convertView.findViewById(R.id.username);
        nameTextView.setText(game.getUsername_white());

        TextView nameTwoTextView = (TextView) convertView.findViewById(R.id.username2);
        nameTwoTextView.setText(game.getUsername_black());

        TextView turnTextView = (TextView) convertView.findViewById(R.id.turn);
        turnTextView.setText(game.getTurn_color() + " turn");







        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int position = getPosition(current);

                //        InstanceGame game = data.get(position);

                if (current.getMatch_id() != null) {

                    if ((current.getBlack().equals(LobbyActivity.userId)) || (current.getWhite().equals(LobbyActivity.userId)))  {

                        Intent intent = new Intent(getContext(), GameActivity.class);
                        intent.putExtra("MATCH_ID", current.getMatch_id());
                        getContext().startActivity(intent);
                    }
                }
            }
        });

        return convertView;




    }


}
