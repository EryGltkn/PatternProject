public class CityRandomizer {
    public static void StartRandomizerThread() {
        Cities citiesInstance = Cities.GetCitiesInstance();
        City[] cityList = citiesInstance.getCityList();
        
        // Register UI as observer for all cities
        for (City city : cityList) {
            city.registerObserver(new UI());
        }
        
        Thread randomizerThread = new Thread(() -> {
            while (true) {
                try {
                    for (City city : cityList) {
                        city.randomizeWeatherCondition();
                        city.randomizeTemperature();
                    }
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    System.out.println("Randomizer thread interrupted");
                    break;
                }
            }
        });
        randomizerThread.start();
    }
}
