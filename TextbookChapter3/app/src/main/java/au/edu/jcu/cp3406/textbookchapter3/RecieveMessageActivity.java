package au.edu.jcu.cp3406.textbookchapter3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class RecieveMessageActivity extends Activity {

    public static final String EXTRA_MESSAGE = "messageString"; // this comes from the onSendMessage method in CreateMessageActivity.java

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recieve_message);
        Intent intent = getIntent();
        String messageText = intent.getStringExtra(EXTRA_MESSAGE);
        TextView recieveMessageView = findViewById(R.id.sentMessage);
        if (messageText.equals("")) {
            recieveMessageView.setText("No message was sent...");
        } else {
            recieveMessageView.setText(messageText);
        }
    }
}
