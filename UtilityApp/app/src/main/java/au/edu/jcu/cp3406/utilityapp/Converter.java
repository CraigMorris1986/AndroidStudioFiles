package au.edu.jcu.cp3406.utilityapp;

public class Converter {

    // 0.45359237 kilos per pound
    // 1 kilo = 9.81 Newton

    // C to Far = (°C x (9/5))+32=°F
    // Far to C = ((°F-32)x(5/9))=°C
    // C - 273.15 = K

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

}
