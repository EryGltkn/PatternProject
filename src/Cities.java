import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Paths;

class Cities{
    private Cities citiesInstance;
    private City[] CityList;
    
    public Cities GetCitiesInstance(){
        if(citiesInstance == null){
            citiesInstance = new Cities();
            createCityList();
        }
        return citiesInstance;
    }
    public City[] getCityList(){
        return CityList;
    }

    private void createCityList(){
        try {
            String Jcontent = new String(Files.readAllBytes(Paths.get("cities.json")));
            JSONArray citiesArray = new JSONArray(Jcontent);

            City[] cityList = new City[citiesArray.length()];   

            for (int i = 0; i < citiesArray.length(); i++) {
                JSONObject city = citiesArray.getJSONObject(i);
                cityList[i] = new City(city.getString("name"), city.getDouble("population"), city.getFloat("area"), city.getFloat("currentTemperature"), city.getString("currentWeatherState"));
            }

            CityList = cityList;
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}