package com.example.cypics;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;



public class MessageListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<Message> mMessageList;
    private final Context mContext;


    public MessageListAdapter(Context context, List<Message> messageList) {
        mContext = context;
        mMessageList = messageList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        if (viewType == Message.TYPE_SENT) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_chat_me, parent, false);
            return new SentMessageHolder(view);
        } else if (viewType == Message.TYPE_RECEIVED) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_chat_other, parent, false);
            return new ReceivedMessageHolder(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Message message = mMessageList.get(position);

        switch (message.getMessageType()) {
            case Message.TYPE_SENT:
                ((SentMessageHolder) holder).bind(message);
                break;
            case Message.TYPE_RECEIVED:
                ((ReceivedMessageHolder) holder).bind(message);
        }
    }

    @Override
    public int getItemCount() {
        return mMessageList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mMessageList.get(position).getMessageType();
    }


    private static class SentMessageHolder extends RecyclerView.ViewHolder {
        TextView messageText;

        SentMessageHolder(View itemView) {
            super(itemView);
            messageText = (TextView) itemView.findViewById(R.id. text_gchat_message_me);
        }

        void bind(Message message) {
            messageText.setText(message.getMessage());
        }
    }

    private static class ReceivedMessageHolder extends RecyclerView.ViewHolder {
        TextView messageText;

        ReceivedMessageHolder(View itemView) {
            super(itemView);
            messageText = (TextView) itemView.findViewById(R.id. text_gchat_message_other);
        }

        void bind(Message message) {
            messageText.setText(message.getMessage());
        }
    }
}

