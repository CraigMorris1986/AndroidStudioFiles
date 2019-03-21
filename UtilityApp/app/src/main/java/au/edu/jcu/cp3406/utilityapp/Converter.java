package au.edu.jcu.cp3406.utilityapp;

public class Converter {


    public String convertCelsius(String celsius) {
        double celsiusDouble = Double.parseDouble(celsius);
        double fahrenheit = (celsiusDouble * (9.0/5.0))+32;
        double kelvin = celsiusDouble + 273.15;

        String conversion = String.format("%.2f Celsius =\n%.2f Fahrenheit\n%.2f Kelvin", celsiusDouble, fahrenheit, kelvin);
        return conversion;
    }

    public String convertFarenheit(String fahrenheit) {
        double fahrenheitDouble = Double.parseDouble(fahrenheit);
        double celsius = ((fahrenheitDouble-32)*(5.0/9.0));
        double kelvin = celsius + 273.15;

        String conversion = String.format("%.2f Fahrenheit =\n%.2f Celsius\n%.2f Kelvin", fahrenheitDouble, celsius, kelvin);
        return conversion;
    }

    public String convertKelvin(String kelvin) {
        double kelvinDouble = Double.parseDouble(kelvin);
        double celsius = kelvinDouble - 273.15;
        double fahrenheit = (celsius * (9.0/5.0))+32;

        String conversion = String.format("%.2f Kelvin =\n%.2f Celsius\n%.2f Fahrenheit", kelvinDouble, celsius, fahrenheit);
        return conversion;
    }

    public String convertKilogram(String kilogram) {
        double kilos = Double.parseDouble(kilogram);
        double pounds = kilos / 0.45359237;
        double newtons = kilos * 9.81;

        String conversion = String.format("%.2f Kilograms =\n%.2f Pounds\n%.2f Newtons", kilos, pounds, newtons);
        return conversion;
    }
    public String convertPound(String pound) {
        double pounds = Double.parseDouble(pound);
        double kilos = pounds * 0.45359237;
        double newtons = kilos * 9.81;

        String conversion = String.format("%.2f Pounds =\n%.2f Kilograms\n%.2f Newtons", pounds, kilos, newtons);
        return conversion;
    }
    public String convertNewton(String newton) {
        double newtons = Double.parseDouble(newton);
        double kilos = newtons / 9.81;
        double pounds = kilos / 0.45359237;

        String conversion = String.format("%.2f Newtons =\n%.2f Kilograms\n%.2f Pounds", newtons, kilos, pounds);
        return conversion;
    }

    public String convertMeter(String meter) {
        double meters = Double.parseDouble(meter);
        double feet = meters * 3.2808;
        double yards = meters * 1.0936;

        String conversion = String.format("%.2f Meters =\n%.2f Feet\n%.2f Yards", meters, feet, yards);
        return conversion;
    }

    public String convertFeet(String feet) {
        double feetDouble = Double.parseDouble(feet);
        double meters = feetDouble / 3.2808;
        double yards = feetDouble / 3;

        String conversion = String.format("%.2f Feet =\n%.2f Meters\n%.2f Yards", feetDouble, meters, yards);
        return conversion;
    }

    public String convertYards(String yard) {
        double yards = Double.parseDouble(yard);
        double meters = yards / 1.0936;
        double feet = yards * 3;

        String conversion = String.format("%.2f Yards =\n%.2f Meters\n%.2f Feet", yards, meters, feet);
        return conversion;
    }

}
