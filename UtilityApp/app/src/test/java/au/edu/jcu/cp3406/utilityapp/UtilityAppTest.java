package au.edu.jcu.cp3406.utilityapp;


import android.support.v7.app.AppCompatActivity;

import org.junit.Test;


import static org.junit.Assert.assertEquals;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UtilityAppTest extends AppCompatActivity {
    @Test
    public void conversionIsCorrect() {
        assertEquals(50.0, (10 * (9.0 / 5.0)) + 32, 0.01); //celsius to fahrenheit
        assertEquals(-12.22, (10 - 32) * (5.0 / 9.0), 0.01); //fahrenheit to celsius
        assertEquals(-263.15, 10 - 273.15, 0.01); // kelvin to celsius

        assertEquals(22.0462, 10 / 0.45359237, 0.01); //kilos to pounds
        assertEquals(1.0197, 10 / 9.81, 0.01); //newtons to kilos

        assertEquals(32.8084, 10 * 3.2808, 0.01); //meters to feet
        assertEquals(3.048, 10 / 3.2808, 0.01); //feet to meters
        assertEquals(10.9361, 10 * 1.0936, 0.01); //meters to yards
        assertEquals(9.144, 10 / 1.0936, 0.01); //yards to meters
    }
}