import org.json.JSONArray;
import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;

class Cities{
    private Cities citiesInstance;
    private City[] CityList;
    private Cities(){}
    
    public Cities GetCitiesInstance(){
        if(citiesInstance == null){
            citiesInstance = new Cities();
        }
        return citiesInstance;
    }

    private JSONArray createCityList(){
        try{
            String Jcontent = new String(Files.readAllBytes(Paths.get("cities.json")));
            JSONObject jsonObject = new JSONObject(Jcontent);
            JSONArray cityNames = JSONObject.getJSONArray("name");
            return cityNames;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    }
    public static void main(String[]args){
        try{
            String[] names = new String[11];
            int i =  0;
            
            for(String name : cityNames){
                names[i] = name;
                System.out.println(names[i]);
                i++;
            }

            
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}