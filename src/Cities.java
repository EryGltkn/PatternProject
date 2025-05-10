import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Paths;

class Cities {
    private static Cities citiesInstance; // Make this static
    private City[] CityList;

    private Cities() {
        // Private constructor to prevent instantiation
    }

    // Static method to get the single instance
    public static Cities GetCitiesInstance() {
        if (citiesInstance == null) {
            citiesInstance = new Cities();
            citiesInstance.createCityList();
            System.out.println("Cities instance created and city list populated.");
        }
        return citiesInstance;
    }

    public City[] getCityList() {
        return CityList;
    }

    private void createCityList() {
        try {
            String Jcontent = new String(Files.readAllBytes(Paths.get("C:\\Users\\PC\\Documents\\Github\\Java\\PatternProject\\src\\cities.json")));
            JSONArray citiesArray = new JSONArray(Jcontent);

            City[] cityList = new City[citiesArray.length()];

            for (int i = 0; i < citiesArray.length(); i++) {
                JSONObject city = citiesArray.getJSONObject(i);
                cityList[i] = new City(city.getString("name"), city.getDouble("population"), city.getFloat("area"), city.getFloat("currentTemperature"), city.getString("currentWeatherState"));
            }

            CityList = cityList;
        } catch (Exception e) {
            System.out.println("Error reading JSON file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}