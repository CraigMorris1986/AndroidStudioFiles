package au.edu.jcu.cp3406.textbookchapter2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    BeerExpert beerExpert = new BeerExpert();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onCLickFindBeer(View view) {
        TextView beer_brands = findViewById(R.id.text_output);
        // creates the text view id object reference
        Spinner colours = findViewById(R.id.beer_colours);
        // see above but for the spinner object
        String selected_beer_colour = String.valueOf(colours.getSelectedItem());
        //String value of the selected item in the spinner focus value

        List<String> brandsList = beerExpert.getBrands(selected_beer_colour);
        StringBuilder brandsFormatted = new StringBuilder();
        for (String brand : brandsList) {
            brandsFormatted.append(brand).append("\n"); //chain method call to append name item and new line
        }
        // above is for looping through arraylist values to append to output and add new lines
        beer_brands.setText(brandsFormatted);
    }
}
