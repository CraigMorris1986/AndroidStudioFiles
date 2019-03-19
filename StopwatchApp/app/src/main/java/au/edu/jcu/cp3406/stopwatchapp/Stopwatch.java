package au.edu.jcu.cp3406.stopwatchapp;

public class Stopwatch {

    int seconds = 0;
    int minutes = 0;
    int hours = 0;

    public void tick() {
        seconds++;
        if (seconds == 60) {
            seconds = 0;
            minutes++;
        }
        if (minutes == 60) {
            minutes = 0;
            hours++;
        }
    }

    public String toString() {
        if (seconds == 0 && minutes == 0 && hours == 0) {
            return "00:00:00";
        } else {
            String timeStringHours = String.format("%02d", hours);
            String timeStringMinutes = String.format(("%02d"), minutes);
            String timeStringSeconds = String.format(("%02d"), seconds);

            return timeStringHours + ":" + timeStringMinutes + ":" + timeStringSeconds;
        }
    }

}
