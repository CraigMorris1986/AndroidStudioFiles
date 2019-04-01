package au.edu.jcu.cp3406.todo;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

public class AddItemActivity extends AppCompatActivity {
    SharedPreferences dataSource;
    EditText user_entry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);


        dataSource = getSharedPreferences("todo items", Context.MODE_PRIVATE);
        //below code makes the top "back arrow" button appear
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
    @Override
    // this overrides the functionality of the hardware back button press for the activity
    public void onBackPressed() {
        Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show();
        super.onBackPressed();
    }

    @Override
    // this overrides the functionality of the home button press
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
//                Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show();
//                finish();

                user_entry = findViewById(R.id.userEntry);
                if (user_entry == null) {
                    Toast.makeText(this, "Nothing was entered", Toast.LENGTH_SHORT).show();
                } else {
                    Set<String> items = dataSource.getStringSet("items", new HashSet<String>());
                    assert items != null;
                    items.add(String.valueOf(user_entry));
                    dataSource.edit().clear().putStringSet("items", items).apply();
                    Toast.makeText(this, "Item added", Toast.LENGTH_SHORT).show();
                    finish();
                    return true;
                }
        }
        return super.onOptionsItemSelected(item);
    }
}
