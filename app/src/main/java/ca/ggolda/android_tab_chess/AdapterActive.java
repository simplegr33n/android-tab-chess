package ca.ggolda.android_tab_chess;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.Arrays;
import java.util.List;

public class AdapterActive extends ArrayAdapter<InstanceGame> {

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

    private Context mContext;

    private View mView;

    private String gameset;


    public AdapterActive(Context context, int resource, List<InstanceGame> objects) {
        super(context, resource, objects);

        mContext = context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.card_game, parent, false);
        }

        mView = convertView;

        final InstanceGame current = getItem(position);

        InstanceGame game = current;

        Log.e("ADAPTERCHILDEV", "" + game.getBlack());

        gameset = current.getBoard();

        TextView nameWhiteTextView = (TextView) convertView.findViewById(R.id.username);
        TextView nameBlackTextView = (TextView) convertView.findViewById(R.id.username2);


        //Set white and black usernames
        if (game.getWhite().equals(LobbyActivity.userId)) {
            nameWhiteTextView.setText("You");
        } else {
            nameWhiteTextView.setText(game.getUsername_white());
        }
        if (game.getBlack().equals(LobbyActivity.userId)) {
            nameBlackTextView.setText("You");
        } else {
            nameBlackTextView.setText(game.getUsername_black());
        }




        TextView turnTextView = (TextView) convertView.findViewById(R.id.turn);
        turnTextView.setText(game.getTurn_color() + " turn");

        // Declare and set board
        declareBoard();


        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (current.getMatch_id() != null) {

                    if ((current.getBlack().equals(LobbyActivity.userId)) || (current.getWhite().equals(LobbyActivity.userId))) {

                        Intent intent = new Intent(getContext(), GameActivity.class);
                        intent.putExtra("MATCH_ID", current.getMatch_id());
                        getContext().startActivity(intent);
                    }
                }
            }
        });

        return convertView;


    }

    private void declareBoard() {

        View v = mView;

        // TODO: Best way to reverse image view order depending on playerColor.
        a1 = (ImageView) v.findViewById(R.id.img_a1);
        a2 = (ImageView) v.findViewById(R.id.img_a2);
        a3 = (ImageView) v.findViewById(R.id.img_a3);
        a4 = (ImageView) v.findViewById(R.id.img_a4);
        a5 = (ImageView) v.findViewById(R.id.img_a5);
        a6 = (ImageView) v.findViewById(R.id.img_a6);
        a7 = (ImageView) v.findViewById(R.id.img_a7);
        a8 = (ImageView) v.findViewById(R.id.img_a8);

        b1 = (ImageView) v.findViewById(R.id.img_b1);
        b2 = (ImageView) v.findViewById(R.id.img_b2);
        b3 = (ImageView) v.findViewById(R.id.img_b3);
        b4 = (ImageView) v.findViewById(R.id.img_b4);
        b5 = (ImageView) v.findViewById(R.id.img_b5);
        b6 = (ImageView) v.findViewById(R.id.img_b6);
        b7 = (ImageView) v.findViewById(R.id.img_b7);
        b8 = (ImageView) v.findViewById(R.id.img_b8);

        c1 = (ImageView) v.findViewById(R.id.img_c1);
        c2 = (ImageView) v.findViewById(R.id.img_c2);
        c3 = (ImageView) v.findViewById(R.id.img_c3);
        c4 = (ImageView) v.findViewById(R.id.img_c4);
        c5 = (ImageView) v.findViewById(R.id.img_c5);
        c6 = (ImageView) v.findViewById(R.id.img_c6);
        c7 = (ImageView) v.findViewById(R.id.img_c7);
        c8 = (ImageView) v.findViewById(R.id.img_c8);

        d1 = (ImageView) v.findViewById(R.id.img_d1);
        d2 = (ImageView) v.findViewById(R.id.img_d2);
        d3 = (ImageView) v.findViewById(R.id.img_d3);
        d4 = (ImageView) v.findViewById(R.id.img_d4);
        d5 = (ImageView) v.findViewById(R.id.img_d5);
        d6 = (ImageView) v.findViewById(R.id.img_d6);
        d7 = (ImageView) v.findViewById(R.id.img_d7);
        d8 = (ImageView) v.findViewById(R.id.img_d8);

        e1 = (ImageView) v.findViewById(R.id.img_e1);
        e2 = (ImageView) v.findViewById(R.id.img_e2);
        e3 = (ImageView) v.findViewById(R.id.img_e3);
        e4 = (ImageView) v.findViewById(R.id.img_e4);
        e5 = (ImageView) v.findViewById(R.id.img_e5);
        e6 = (ImageView) v.findViewById(R.id.img_e6);
        e7 = (ImageView) v.findViewById(R.id.img_e7);
        e8 = (ImageView) v.findViewById(R.id.img_e8);

        f1 = (ImageView) v.findViewById(R.id.img_f1);
        f2 = (ImageView) v.findViewById(R.id.img_f2);
        f3 = (ImageView) v.findViewById(R.id.img_f3);
        f4 = (ImageView) v.findViewById(R.id.img_f4);
        f5 = (ImageView) v.findViewById(R.id.img_f5);
        f6 = (ImageView) v.findViewById(R.id.img_f6);
        f7 = (ImageView) v.findViewById(R.id.img_f7);
        f8 = (ImageView) v.findViewById(R.id.img_f8);

        g1 = (ImageView) v.findViewById(R.id.img_g1);
        g2 = (ImageView) v.findViewById(R.id.img_g2);
        g3 = (ImageView) v.findViewById(R.id.img_g3);
        g4 = (ImageView) v.findViewById(R.id.img_g4);
        g5 = (ImageView) v.findViewById(R.id.img_g5);
        g6 = (ImageView) v.findViewById(R.id.img_g6);
        g7 = (ImageView) v.findViewById(R.id.img_g7);
        g8 = (ImageView) v.findViewById(R.id.img_g8);

        h1 = (ImageView) v.findViewById(R.id.img_h1);
        h2 = (ImageView) v.findViewById(R.id.img_h2);
        h3 = (ImageView) v.findViewById(R.id.img_h3);
        h4 = (ImageView) v.findViewById(R.id.img_h4);
        h5 = (ImageView) v.findViewById(R.id.img_h5);
        h6 = (ImageView) v.findViewById(R.id.img_h6);
        h7 = (ImageView) v.findViewById(R.id.img_h7);
        h8 = (ImageView) v.findViewById(R.id.img_h8);

        setBoard();
    }

    private void setBoard() {


        if (gameset != null) {
            Log.e("HEREWEGO", "" + gameset);

            List<String> gamesetList = Arrays.asList(gameset.split("\\s*,\\s*"));

            a1.setImageResource(mContext.getResources().getIdentifier(gamesetList.get(0), "drawable", mContext.getPackageName()));
            b1.setImageResource(mContext.getResources().getIdentifier(gamesetList.get(1), "drawable", mContext.getPackageName()));
            c1.setImageResource(mContext.getResources().getIdentifier(gamesetList.get(2), "drawable", mContext.getPackageName()));
            d1.setImageResource(mContext.getResources().getIdentifier(gamesetList.get(3), "drawable", mContext.getPackageName()));
            e1.setImageResource(mContext.getResources().getIdentifier(gamesetList.get(4), "drawable", mContext.getPackageName()));
            f1.setImageResource(mContext.getResources().getIdentifier(gamesetList.get(5), "drawable", mContext.getPackageName()));
            g1.setImageResource(mContext.getResources().getIdentifier(gamesetList.get(6), "drawable", mContext.getPackageName()));
            h1.setImageResource(mContext.getResources().getIdentifier(gamesetList.get(7), "drawable", mContext.getPackageName()));

            a2.setImageResource(mContext.getResources().getIdentifier(gamesetList.get(8), "drawable", mContext.getPackageName()));
            b2.setImageResource(mContext.getResources().getIdentifier(gamesetList.get(9), "drawable", mContext.getPackageName()));
            c2.setImageResource(mContext.getResources().getIdentifier(gamesetList.get(10), "drawable", mContext.getPackageName()));
            d2.setImageResource(mContext.getResources().getIdentifier(gamesetList.get(11), "drawable", mContext.getPackageName()));
            e2.setImageResource(mContext.getResources().getIdentifier(gamesetList.get(12), "drawable", mContext.getPackageName()));
            f2.setImageResource(mContext.getResources().getIdentifier(gamesetList.get(13), "drawable", mContext.getPackageName()));
            g2.setImageResource(mContext.getResources().getIdentifier(gamesetList.get(14), "drawable", mContext.getPackageName()));
            h2.setImageResource(mContext.getResources().getIdentifier(gamesetList.get(15), "drawable", mContext.getPackageName()));

            a3.setImageResource(mContext.getResources().getIdentifier(gamesetList.get(16), "drawable", mContext.getPackageName()));
            b3.setImageResource(mContext.getResources().getIdentifier(gamesetList.get(17), "drawable", mContext.getPackageName()));
            c3.setImageResource(mContext.getResources().getIdentifier(gamesetList.get(18), "drawable", mContext.getPackageName()));
            d3.setImageResource(mContext.getResources().getIdentifier(gamesetList.get(19), "drawable", mContext.getPackageName()));
            e3.setImageResource(mContext.getResources().getIdentifier(gamesetList.get(20), "drawable", mContext.getPackageName()));
            f3.setImageResource(mContext.getResources().getIdentifier(gamesetList.get(21), "drawable", mContext.getPackageName()));
            g3.setImageResource(mContext.getResources().getIdentifier(gamesetList.get(22), "drawable", mContext.getPackageName()));
            h3.setImageResource(mContext.getResources().getIdentifier(gamesetList.get(23), "drawable", mContext.getPackageName()));

            a4.setImageResource(mContext.getResources().getIdentifier(gamesetList.get(24), "drawable", mContext.getPackageName()));
            b4.setImageResource(mContext.getResources().getIdentifier(gamesetList.get(25), "drawable", mContext.getPackageName()));
            c4.setImageResource(mContext.getResources().getIdentifier(gamesetList.get(26), "drawable", mContext.getPackageName()));
            d4.setImageResource(mContext.getResources().getIdentifier(gamesetList.get(27), "drawable", mContext.getPackageName()));
            e4.setImageResource(mContext.getResources().getIdentifier(gamesetList.get(28), "drawable", mContext.getPackageName()));
            f4.setImageResource(mContext.getResources().getIdentifier(gamesetList.get(29), "drawable", mContext.getPackageName()));
            g4.setImageResource(mContext.getResources().getIdentifier(gamesetList.get(30), "drawable", mContext.getPackageName()));
            h4.setImageResource(mContext.getResources().getIdentifier(gamesetList.get(31), "drawable", mContext.getPackageName()));

            a5.setImageResource(mContext.getResources().getIdentifier(gamesetList.get(32), "drawable", mContext.getPackageName()));
            b5.setImageResource(mContext.getResources().getIdentifier(gamesetList.get(33), "drawable", mContext.getPackageName()));
            c5.setImageResource(mContext.getResources().getIdentifier(gamesetList.get(34), "drawable", mContext.getPackageName()));
            d5.setImageResource(mContext.getResources().getIdentifier(gamesetList.get(35), "drawable", mContext.getPackageName()));
            e5.setImageResource(mContext.getResources().getIdentifier(gamesetList.get(36), "drawable", mContext.getPackageName()));
            f5.setImageResource(mContext.getResources().getIdentifier(gamesetList.get(37), "drawable", mContext.getPackageName()));
            g5.setImageResource(mContext.getResources().getIdentifier(gamesetList.get(38), "drawable", mContext.getPackageName()));
            h5.setImageResource(mContext.getResources().getIdentifier(gamesetList.get(39), "drawable", mContext.getPackageName()));

            a6.setImageResource(mContext.getResources().getIdentifier(gamesetList.get(40), "drawable", mContext.getPackageName()));
            b6.setImageResource(mContext.getResources().getIdentifier(gamesetList.get(41), "drawable", mContext.getPackageName()));
            c6.setImageResource(mContext.getResources().getIdentifier(gamesetList.get(42), "drawable", mContext.getPackageName()));
            d6.setImageResource(mContext.getResources().getIdentifier(gamesetList.get(43), "drawable", mContext.getPackageName()));
            e6.setImageResource(mContext.getResources().getIdentifier(gamesetList.get(44), "drawable", mContext.getPackageName()));
            f6.setImageResource(mContext.getResources().getIdentifier(gamesetList.get(45), "drawable", mContext.getPackageName()));
            g6.setImageResource(mContext.getResources().getIdentifier(gamesetList.get(46), "drawable", mContext.getPackageName()));
            h6.setImageResource(mContext.getResources().getIdentifier(gamesetList.get(47), "drawable", mContext.getPackageName()));

            a7.setImageResource(mContext.getResources().getIdentifier(gamesetList.get(48), "drawable", mContext.getPackageName()));
            b7.setImageResource(mContext.getResources().getIdentifier(gamesetList.get(49), "drawable", mContext.getPackageName()));
            c7.setImageResource(mContext.getResources().getIdentifier(gamesetList.get(50), "drawable", mContext.getPackageName()));
            d7.setImageResource(mContext.getResources().getIdentifier(gamesetList.get(51), "drawable", mContext.getPackageName()));
            e7.setImageResource(mContext.getResources().getIdentifier(gamesetList.get(52), "drawable", mContext.getPackageName()));
            f7.setImageResource(mContext.getResources().getIdentifier(gamesetList.get(53), "drawable", mContext.getPackageName()));
            g7.setImageResource(mContext.getResources().getIdentifier(gamesetList.get(54), "drawable", mContext.getPackageName()));
            h7.setImageResource(mContext.getResources().getIdentifier(gamesetList.get(55), "drawable", mContext.getPackageName()));

            a8.setImageResource(mContext.getResources().getIdentifier(gamesetList.get(56), "drawable", mContext.getPackageName()));
            b8.setImageResource(mContext.getResources().getIdentifier(gamesetList.get(57), "drawable", mContext.getPackageName()));
            c8.setImageResource(mContext.getResources().getIdentifier(gamesetList.get(58), "drawable", mContext.getPackageName()));
            d8.setImageResource(mContext.getResources().getIdentifier(gamesetList.get(59), "drawable", mContext.getPackageName()));
            e8.setImageResource(mContext.getResources().getIdentifier(gamesetList.get(60), "drawable", mContext.getPackageName()));
            f8.setImageResource(mContext.getResources().getIdentifier(gamesetList.get(61), "drawable", mContext.getPackageName()));
            g8.setImageResource(mContext.getResources().getIdentifier(gamesetList.get(62), "drawable", mContext.getPackageName()));
            h8.setImageResource(mContext.getResources().getIdentifier(gamesetList.get(63), "drawable", mContext.getPackageName()));

        } else {
//            logoCover.setVisibility(View.VISIBLE);
        }

    }
}
