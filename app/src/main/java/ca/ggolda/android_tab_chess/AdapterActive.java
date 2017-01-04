package ca.ggolda.android_tab_chess;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class AdapterActive extends ArrayAdapter<InstanceGame> {

    private ImageView a1, a2, a3, a4, a5, a6, a7, a8;
    private ImageView b1, b2, b3, b4, b5, b6, b7, b8;
    private ImageView c1, c2, c3, c4, c5, c6, c7, c8;
    private ImageView d1, d2, d3, d4, d5, d6, d7, d8;
    private ImageView e1, e2, e3, e4, e5, e6, e7, e8;
    private ImageView f1, f2, f3, f4, f5, f6, f7, f8;
    private ImageView g1, g2, g3, g4, g5, g6, g7, g8;
    private ImageView h1, h2, h3, h4, h5, h6, h7, h8;

    private Context mContext;

    private View mView;

    private String gameset;

    private InstanceGame game;


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

        game = current;

        Log.e("ADAPTERCHILDEV", "" + game.getBlack());

        gameset = current.getBoard();

        TextView nameLeftTextView = (TextView) convertView.findViewById(R.id.username_left);
        TextView nameRightTextView = (TextView) convertView.findViewById(R.id.username_right);
        TextView vTextView = (TextView) convertView.findViewById(R.id.v_textview);
        ImageView leftPawn = (ImageView) convertView.findViewById(R.id.left_pawn);
        ImageView rightPawn = (ImageView) convertView.findViewById(R.id.right_pawn);

        // Set datetime for last play
        TextView lastMove = (TextView) convertView.findViewById(R.id.lastmove);
        if (game.getLast_play() != null) {
            String date = DateFormat.format("MMMM dd, yyyy", game.getLast_play()).toString();
            String time = DateFormat.format("hh:mm a", game.getLast_play()).toString();

            lastMove.setText("LAST PLAY: " + date + " " + time);
        } else {
            lastMove.setText("");
        }




        // Set white and black usernames and icons to left and right
        // depending on turn
        if (game.getWhite().equals(LobbyActivity.userId)) {

            switch (game.getTurn_color()) {
                case ("white"):

                    nameLeftTextView.setText("YOUR TURN");
                    nameLeftTextView.setTextColor(Color.parseColor("#FFFFFF"));
                    vTextView.setVisibility(View.VISIBLE);
                    nameLeftTextView.setBackgroundColor(Color.parseColor("#2D7A53"));
                    nameRightTextView.setText(game.getUsername_black());

                    leftPawn.setImageResource(R.drawable.white_king);
                    rightPawn.setImageResource(R.drawable.black_king);

                    break;
                case ("black"):

                    nameRightTextView.setText("waiting...");
                    nameLeftTextView.setBackgroundColor(Color.parseColor("#c19e6d"));
                    nameLeftTextView.setTextColor(Color.parseColor("#000000"));
                    vTextView.setVisibility(View.INVISIBLE);
                    nameLeftTextView.setText(game.getUsername_black() + "'s TURN");

                    leftPawn.setImageResource(R.drawable.black_king);
                    rightPawn.setImageResource(R.drawable.white_king);

                    break;

            }


        } else {

            switch (game.getTurn_color()) {
                case ("white"):

                    nameRightTextView.setText("waiting...");
                    nameLeftTextView.setBackgroundColor(Color.parseColor("#c19e6d"));
                    nameLeftTextView.setTextColor(Color.parseColor("#000000"));
                    vTextView.setVisibility(View.INVISIBLE);
                    nameLeftTextView.setText(game.getUsername_white() + "'s TURN");

                    leftPawn.setImageResource(R.drawable.black_king);
                    rightPawn.setImageResource(R.drawable.white_king);

                    break;

                case ("black"):

                    nameLeftTextView.setText("YOUR TURN");
                    nameLeftTextView.setTextColor(Color.parseColor("#FFFFFF"));
                    vTextView.setVisibility(View.VISIBLE);
                    nameLeftTextView.setBackgroundColor(Color.parseColor("#2D7A53"));
                    nameRightTextView.setText(game.getUsername_white());

                    leftPawn.setImageResource(R.drawable.white_king);
                    rightPawn.setImageResource(R.drawable.black_king);

                    break;

            }
        }


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
        //

        if (LobbyActivity.userId.equals(game.getWhite())) {
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

        } else if (LobbyActivity.userId.equals(game.getBlack())) {
            a1 = (ImageView) v.findViewById(R.id.img_h8);
            a2 = (ImageView) v.findViewById(R.id.img_h7);
            a3 = (ImageView) v.findViewById(R.id.img_h6);
            a4 = (ImageView) v.findViewById(R.id.img_h5);
            a5 = (ImageView) v.findViewById(R.id.img_h4);
            a6 = (ImageView) v.findViewById(R.id.img_h3);
            a7 = (ImageView) v.findViewById(R.id.img_h2);
            a8 = (ImageView) v.findViewById(R.id.img_h1);

            b1 = (ImageView) v.findViewById(R.id.img_g8);
            b2 = (ImageView) v.findViewById(R.id.img_g7);
            b3 = (ImageView) v.findViewById(R.id.img_g6);
            b4 = (ImageView) v.findViewById(R.id.img_g5);
            b5 = (ImageView) v.findViewById(R.id.img_g4);
            b6 = (ImageView) v.findViewById(R.id.img_g3);
            b7 = (ImageView) v.findViewById(R.id.img_g2);
            b8 = (ImageView) v.findViewById(R.id.img_g1);

            c1 = (ImageView) v.findViewById(R.id.img_f8);
            c2 = (ImageView) v.findViewById(R.id.img_f7);
            c3 = (ImageView) v.findViewById(R.id.img_f6);
            c4 = (ImageView) v.findViewById(R.id.img_f5);
            c5 = (ImageView) v.findViewById(R.id.img_f4);
            c6 = (ImageView) v.findViewById(R.id.img_f3);
            c7 = (ImageView) v.findViewById(R.id.img_f2);
            c8 = (ImageView) v.findViewById(R.id.img_f1);

            d1 = (ImageView) v.findViewById(R.id.img_e8);
            d2 = (ImageView) v.findViewById(R.id.img_e7);
            d3 = (ImageView) v.findViewById(R.id.img_e6);
            d4 = (ImageView) v.findViewById(R.id.img_e5);
            d5 = (ImageView) v.findViewById(R.id.img_e4);
            d6 = (ImageView) v.findViewById(R.id.img_e3);
            d7 = (ImageView) v.findViewById(R.id.img_e2);
            d8 = (ImageView) v.findViewById(R.id.img_e1);

            e1 = (ImageView) v.findViewById(R.id.img_d8);
            e2 = (ImageView) v.findViewById(R.id.img_d7);
            e3 = (ImageView) v.findViewById(R.id.img_d6);
            e4 = (ImageView) v.findViewById(R.id.img_d5);
            e5 = (ImageView) v.findViewById(R.id.img_d4);
            e6 = (ImageView) v.findViewById(R.id.img_d3);
            e7 = (ImageView) v.findViewById(R.id.img_d2);
            e8 = (ImageView) v.findViewById(R.id.img_d1);

            f1 = (ImageView) v.findViewById(R.id.img_c8);
            f2 = (ImageView) v.findViewById(R.id.img_c7);
            f3 = (ImageView) v.findViewById(R.id.img_c6);
            f4 = (ImageView) v.findViewById(R.id.img_c5);
            f5 = (ImageView) v.findViewById(R.id.img_c4);
            f6 = (ImageView) v.findViewById(R.id.img_c3);
            f7 = (ImageView) v.findViewById(R.id.img_c2);
            f8 = (ImageView) v.findViewById(R.id.img_c1);

            g1 = (ImageView) v.findViewById(R.id.img_b8);
            g2 = (ImageView) v.findViewById(R.id.img_b7);
            g3 = (ImageView) v.findViewById(R.id.img_b6);
            g4 = (ImageView) v.findViewById(R.id.img_b5);
            g5 = (ImageView) v.findViewById(R.id.img_b4);
            g6 = (ImageView) v.findViewById(R.id.img_b3);
            g7 = (ImageView) v.findViewById(R.id.img_b2);
            g8 = (ImageView) v.findViewById(R.id.img_b1);

            h1 = (ImageView) v.findViewById(R.id.img_a8);
            h2 = (ImageView) v.findViewById(R.id.img_a7);
            h3 = (ImageView) v.findViewById(R.id.img_a6);
            h4 = (ImageView) v.findViewById(R.id.img_a5);
            h5 = (ImageView) v.findViewById(R.id.img_a4);
            h6 = (ImageView) v.findViewById(R.id.img_a3);
            h7 = (ImageView) v.findViewById(R.id.img_a2);
            h8 = (ImageView) v.findViewById(R.id.img_a1);
        }

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
