package au.edu.jcu.cp3406.educationapp;

/**
 * Class to handle the mathematics of the bounce interpolation for the bounce animation in the app.
 */
public class BouceInterpolatorHelper implements android.view.animation.Interpolator {
    private double Amplitude;
    private double Frequency;

    /**
     * Class constructor for the bounce interpolation.
     * @param amplitude takes a double value to calculate the severity of the bounce. 1.0 being the most severe
     * @param frequency takes a double value to calculate the amount of bounces during the animation.
     *                  1.0 being the highest amount of bounces
     */
    BouceInterpolatorHelper(double amplitude, double frequency) {
        Amplitude = amplitude;
        Frequency = frequency;
    }

    /**
     * Method to runt the mathematical formula to calculate the interpolation value of the bounce animation.
     * Uses the values defined in the constructor to calcualte the bounce frequency and severity.
     * @param time takes a float value to determine the time of the animation for the calculation.
     * @return returns a float value of the interpolation calculation result.
     */
    public float getInterpolation(float time) {
        return (float) (-1 * Math.pow(Math.E, -time/ Amplitude) *
                Math.cos(Frequency * time) + 1);
    }
}
