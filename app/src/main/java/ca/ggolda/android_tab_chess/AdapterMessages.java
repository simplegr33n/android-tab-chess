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

        InstanceMessage message = getItem(position);

        authorTextView.setText(message.getName());
        messageTextView.setText(message.getText());

        return convertView;
    }
}
