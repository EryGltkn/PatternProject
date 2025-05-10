public class RandomizerThread extends Thread {
    public void run(){
        Cities citiesInstance = Cities.GetCitiesInstance();
        City[] cityList = citiesInstance.getCityList();
        int randomIndex = (int) (Math.random() * cityList.length);
        City randomCity = cityList[randomIndex];
        System.out.println("Random City: " + randomCity.getName() + ", Weather: " + randomCity.getCurrentWeatherCondition());
    }
}