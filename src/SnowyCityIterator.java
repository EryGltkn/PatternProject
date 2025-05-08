public class SnowyCityIterator implements CityIterator {
    private Cities citiesInstance;
    private City[] cityList;
    private int currentIndex = 0;

    public SnowyCityIterator() {
        citiesInstance = new Cities().GetCitiesInstance();
        cityList = citiesInstance.getCityList();
    }

    @Override
    public boolean hasNext() {
        while (currentIndex < cityList.length) {
            if (cityList[currentIndex].getCurrentWeatherCondition().equals("Snowy")) {
                return true;
            }
            currentIndex++;
        }
        return false;
    }

    @Override
    public City next() {
        if (hasNext()) {
            return cityList[currentIndex++];
        }
        return null; // or throw an exception
    }

    @Override
    public void reset() {
        currentIndex = 0;
    }

    public City[] getCityList() {
        City[] snowyCities = new City[cityList.length];
        for (int i = 0; i < cityList.length; i++) {
            if (cityList[i].getCurrentWeatherCondition().equals("Snowy")) {
                snowyCities[i] = cityList[i];
            }
        }
        return snowyCities;
    }
}