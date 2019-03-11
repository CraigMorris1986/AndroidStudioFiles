package au.edu.jcu.cp3406.textbookchapter2;

import java.util.ArrayList;
import java.util.List;

public class BeerExpert {
    List<String> getBrands(String colour) {
        List<String> brands = new ArrayList<>();
        if (colour.equals("light")) {
            brands.add("Coopers Pale Ale");
            brands.add("Cascade light");
        } else {
            brands.add("Jail Ale");
            brands.add("Gout Stout");
        }
        return brands;
    }
}
