public interface WeatherObserver {
    void update(String cityName, String weatherCondition, float temperature);
} 