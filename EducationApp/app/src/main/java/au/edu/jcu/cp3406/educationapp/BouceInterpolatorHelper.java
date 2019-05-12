package au.edu.jcu.cp3406.educationapp;

public class BouceInterpolatorHelper implements android.view.animation.Interpolator {
    private double Amplitude;
    private double Frequency;

    BouceInterpolatorHelper(double amplitude, double frequency) {
        Amplitude = amplitude;
        Frequency = frequency;
    }

    public float getInterpolation(float time) {
        return (float) (-1 * Math.pow(Math.E, -time/ Amplitude) *
                Math.cos(Frequency * time) + 1);
    }
}
