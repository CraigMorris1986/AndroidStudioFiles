package au.edu.jcu.cp3406.textbookchapter3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CreateMessageActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_message);
    }

    public void onSendMessage(View view) {
        Intent intent = new Intent(this, RecieveMessageActivity.class);

        EditText messageView = findViewById(R.id.message);
        String messageString = messageView.getText().toString();

        intent.putExtra(RecieveMessageActivity.EXTRA_MESSAGE, messageString);
        startActivity(intent);
    }
}
