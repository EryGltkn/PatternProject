
class City{
    private String name;
    private double population;
    private float area;
    private float currentTemprature;
    private String currentWeatherCondition;

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

    public void randomizeWeatherCondition(){
        String[] weatherConditions = {"Sunny", "Rainy", "Snowy", "Windy"};
        int randomIndex = (int) (Math.random() * weatherConditions.length);
        currentWeatherCondition = weatherConditions[randomIndex];
    }

    public void randomizeTemperature(){
        currentTemprature = (float) (Math.random() * 40); 
    }
}