import java.util.ArrayList;
import java.util.List;

class City implements WeatherObservable {
    private String name;
    private double population;
    private float area;
    private float currentTemprature;
    private String currentWeatherCondition;
    private List<WeatherObserver> observers = new ArrayList<>();

    public City(String cName, double cPop, float cArea, float currTemp, String currWeather){
        name = cName;
        population = cPop;
        area = cArea;
        currentTemprature = currTemp;
        currentWeatherCondition = currWeather;
    }

    public String getName(){
        return name;
    }
    public double getPopulation(){
        return population;
    }
    public float getArea(){
        return area;
    }
    public float getCurrentTemperature(){
        return currentTemprature;
    }
    public String getCurrentWeatherCondition(){
        return currentWeatherCondition;
    }

    @Override
    public void registerObserver(WeatherObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(WeatherObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (WeatherObserver observer : observers) {
            observer.update(name, currentWeatherCondition, currentTemprature);
        }
    }

    public void randomizeWeatherCondition(){
        String[] weatherConditions = {"Sunny", "Rainy", "Snowy", "Windy"};
        int randomIndex = (int) (Math.random() * weatherConditions.length);
        currentWeatherCondition = weatherConditions[randomIndex];
        notifyObservers();
    }

    public void randomizeTemperature(){
        currentTemprature = (float) (Math.random() * 40);
        notifyObservers();
    }
}