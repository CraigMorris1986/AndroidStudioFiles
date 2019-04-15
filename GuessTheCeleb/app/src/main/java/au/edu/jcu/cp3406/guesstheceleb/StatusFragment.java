package au.edu.jcu.cp3406.guesstheceleb;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class StatusFragment extends Fragment {
    private StateListener listener;
    private Textview message; // this wont work as it doesn't not extend from a class that recognises Textview class
    private Textview score; // can't implement Textview Class either can can only extend from one parent class

    public StatusFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        message = findViewById(R.id.status_text_top);
//        score = findViewById(R.id.status_text_bottom);
        return inflater.inflate(R.layout.fragment_status, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (StateListener) context;
    }

    public void setMessage(String text) {message.setText(text);}
    public void setScore(String text) {score.setText(text);}
}
