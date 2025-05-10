public class Main {
    public static void main(String[] args) {
        // Get the single instance of the Cities class
        Cities citiesInstance = Cities.GetCitiesInstance();

        // Access the city list
        City[] cityList = citiesInstance.getCityList();

        // Example: Print the names of all cities
        if (cityList != null) {
            for (City city : cityList) {
                System.out.println("City Name: " + city.getName());
            }
        } else {
            System.out.println("City list is empty or not initialized.");
        }
    }
}