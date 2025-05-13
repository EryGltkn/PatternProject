public class CityRandomizer {
    public static void StartRandomizerThread() {
        Cities citiesInstance = Cities.GetCitiesInstance();
        City[] cityList = citiesInstance.getCityList();
        
        Thread randomizerThread = new Thread(() -> {
            while (true) {
                try {
                    for (City city : cityList) {
                        city.randomizeWeatherCondition();
                        city.randomizeTemperature();
                        UI.updateUI();
                    }
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    System.out.println("Randomizer thread interrupted");
                    break;
                }
            }
        });
        randomizerThread.start();
    }
}
