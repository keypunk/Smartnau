package com.example.smartnau.viewmodels;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartnau.R;
import com.example.smartnau.model.BaseMessage;
import com.example.smartnau.model.UserMessage;

import java.util.List;

public class MessageListAdapter extends RecyclerView.Adapter {

    private static final int VIEW_TYPE_MESSAGE_SENT = 1;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;

    private Context mContext;
    private List<BaseMessage> mMessageList;

    private RecyclerView mMessageRecycler;
    private MessageListAdapter mMessageAdapter;


    public MessageListAdapter(Context context, List<BaseMessage> messageList) {
        mContext = context;
        mMessageList = messageList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        if (viewType == VIEW_TYPE_MESSAGE_SENT) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_container_sent_message, parent, false);
            return new SentMessageHolder(view);
        } else if (viewType == VIEW_TYPE_MESSAGE_RECEIVED) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_container_recieved, parent, false);
            return new ReceivedMessageHolder(view);
        }

        return null;
    }


    // Determines the appropriate ViewType according to the sender of the message.
    @Override
    public int getItemViewType(int position) {
        BaseMessage message = (BaseMessage) mMessageList.get(position);

        if (message instanceof UserMessage) {
            // If the current user is the sender of the message
            return VIEW_TYPE_MESSAGE_SENT;
        } else {
            // If some other user sent the message
            return VIEW_TYPE_MESSAGE_RECEIVED;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        BaseMessage message = mMessageList.get(position);

        switch (holder.getItemViewType()) {
            case VIEW_TYPE_MESSAGE_SENT:
                ((SentMessageHolder) holder).bind(message);
                break;
            case VIEW_TYPE_MESSAGE_RECEIVED:
                ((ReceivedMessageHolder) holder).bind(message);
        }
    }

    @Override
    public int getItemCount() {
        return mMessageList.size();
    }

    private class ReceivedMessageHolder extends RecyclerView.ViewHolder {
        TextView messageText, timeText, nameText;
        ImageView profileImage;

        ReceivedMessageHolder(View itemView) {
            super(itemView);
            messageText = (TextView) itemView.findViewById(R.id.textMessage_recieved);
            timeText = (TextView) itemView.findViewById(R.id.textDateTime_recieved);
        }

        void bind(BaseMessage message) {
            messageText.setText(message.getMessage());
            timeText.setText(Utils.formatDateTime(message.getCreatedAt()));
        }

    }
    private class SentMessageHolder extends RecyclerView.ViewHolder {
        TextView messageText, timeText;

        SentMessageHolder(View itemView) {
            super(itemView);

            messageText = (TextView) itemView.findViewById(R.id.textMessage_sent);
            timeText = (TextView) itemView.findViewById(R.id.texDateTime_sent);
        }

        void bind(BaseMessage message) {
            messageText.setText(message.getMessage());
            timeText.setText(Utils.formatDateTime(message.getCreatedAt()));
        }
    }
}
