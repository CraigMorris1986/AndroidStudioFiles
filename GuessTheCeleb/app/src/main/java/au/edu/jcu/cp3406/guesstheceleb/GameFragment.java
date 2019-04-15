package au.edu.jcu.cp3406.guesstheceleb;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import au.edu.jcu.cp3406.guesstheceleb.game.GameBuilder;


/**
 * A simple {@link Fragment} subclass.
 */
public class GameFragment extends Fragment{
    private StateListener listener;


    public GameFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_game, container, false);
        final Spinner spinner = view.findViewById(R.id.spinner);

        view.findViewById(R.id.play_button).setOnClickListener((v) -> {
            String selection = spinner.getSelectedItem().toString();
            Log.i("GameFragment", "selection: " + selection);
            listener.onUpdate((State.START_GAME));
        });
//        return inflater.inflate(R.layout.fragment_game, container, false);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (StateListener) context;
    }

}
