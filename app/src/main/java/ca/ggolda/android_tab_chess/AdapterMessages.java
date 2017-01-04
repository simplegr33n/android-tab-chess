package ca.ggolda.android_tab_chess;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

public class AdapterMessages extends ArrayAdapter<InstanceMessage> {
    public AdapterMessages(Context context, int resource, List<InstanceMessage> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.message_slide, parent, false);
        }

        TextView messageTextView = (TextView) convertView.findViewById(R.id.messageTextView);
        TextView authorTextView = (TextView) convertView.findViewById(R.id.nameTextView);
        ImageView colorSide = (ImageView) convertView.findViewById(R.id.color_side);



        InstanceMessage message = getItem(position);

        // Set color of icone next to name in chat
        if (message.getName() != null) {
            if (message.getName().equals(GameActivity.username)) {
                if (GameActivity.playerColor.equals("white")) {
                    colorSide.setImageResource(R.drawable.white_king);

                } else if (GameActivity.playerColor.equals("black")) {
                    colorSide.setImageResource(R.drawable.black_king);
                }
            } else {
                if (GameActivity.playerColor.equals("white")) {
                    colorSide.setImageResource(R.drawable.black_king);

                } else if (GameActivity.playerColor.equals("black")) {
                    colorSide.setImageResource(R.drawable.white_king);
                }
            }
        }

        authorTextView.setText(message.getName());
        messageTextView.setText(message.getText());

        return convertView;
    }
}
