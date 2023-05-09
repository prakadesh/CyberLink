package com.example.cypics;

import static com.example.cypics.R.raw.credential;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.api.gax.rpc.ApiException;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.dialogflow.v2beta1.DetectIntentResponse;
import com.google.cloud.dialogflow.v2beta1.QueryInput;
import com.google.cloud.dialogflow.v2beta1.QueryResult;
import com.google.cloud.dialogflow.v2beta1.SessionName;
import com.google.cloud.dialogflow.v2beta1.SessionsClient;
import com.google.cloud.dialogflow.v2beta1.TextInput;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class MessageListActivity extends AppCompatActivity {

    private RecyclerView mMessageRecycler;
    private MessageListAdapter mMessageAdapter;
    private List<Message> messageList = new ArrayList<>();

    private SessionsClient sessionsClient;
    private SessionName session;
    private String uuid = UUID.randomUUID().toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_screen_layout);

        // Initialize Dialogflow client
        try (InputStream stream = getResources().openRawResource(credential)) {
            GoogleCredentials credentials = GoogleCredentials.fromStream(stream);
            String projectId = ((ServiceAccountCredentials) credentials).getProjectId();
            sessionsClient = SessionsClient.create();
            session = SessionName.of(projectId, uuid);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Add sample data for testing
        List<Message> messageList = new ArrayList<>();
        messageList.add(new Message("Hello!", Message.TYPE_RECEIVED, new Date().getTime()));
        messageList.add(new Message("Hi there!", Message.TYPE_SENT, new Date().getTime()));

        // Initialize RecyclerView
        mMessageRecycler = findViewById(R.id.recycler_gchat);
        mMessageAdapter = new MessageListAdapter(this, messageList);
        mMessageRecycler.setLayoutManager(new LinearLayoutManager(this));
        mMessageRecycler.setAdapter(mMessageAdapter);




        // Initialize input text and send button
        final EditText input = findViewById(R.id.edit_gchat_message);
        Button send = findViewById(R.id.button_gchat_send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = input.getText().toString();
                if (!text.isEmpty()) {
                    sendMessage(text);
                    input.getText().clear();
                }
            }
        });
    }

    // Send a message to Dialogflow agent
    private void sendMessage(String message) {
        QueryInput input = QueryInput.newBuilder().setText(TextInput.newBuilder().setText(message).setLanguageCode("en-US")).build();
        DetectIntentResponse response;
        try {
            response = sessionsClient.detectIntent(session, input);
            List<QueryResult> queryResultList = Collections.singletonList(response.getQueryResult());
            if (queryResultList != null && !queryResultList.isEmpty()) {
                String botReply = queryResultList.get(0).getFulfillmentText();
                addMessage(new Message(botReply, false));
            }
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    // Add a message to the RecyclerView
    private void addMessage(Message message) {
        messageList.add(message);
        mMessageAdapter.notifyDataSetChanged();
        if (messageList.size() > 1) {
            mMessageRecycler.smoothScrollToPosition(mMessageAdapter.getItemCount() - 1);
        }
    }
}

