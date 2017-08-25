package com.jingyushi.chatchat;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jingyushi on 8/22/17.
 */

public class MessageAdapter extends ArrayAdapter<ChatMessage> {
    public MessageAdapter(Context context, int resource, List<ChatMessage> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_message, parent, false);
        }

        TextView messageTextView = (TextView) convertView.findViewById(R.id.chat_message);
        TextView chatUserTextView = (TextView) convertView.findViewById(R.id.chat_user_name);

        ChatMessage message = getItem(position);
        chatUserTextView.setText(message.getName());
        messageTextView.setText(message.getMessage());

        return convertView;
    }
}
