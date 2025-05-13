public class SnowyCityIterator implements CityIterator {
    private Cities citiesInstance;
    private City[] cityList;
    private int currentIndex = 0;

    public SnowyCityIterator() {
        citiesInstance = Cities.GetCitiesInstance();
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

    @Override
    public City[] getCityList() {
        City[] snowyCities = new City[cityList.length];
        for (int i = 0; i < cityList.length; i++) {
            if (cityList[i].getCurrentWeatherCondition().equals("SNOWY")) {
                snowyCities[i] = cityList[i];
            }
        }
        return snowyCities;
    }

    @Override
    public String[] getCityListInString() {
        City[] snowyCities = getCityList();
        String[] snowyCitiesString = new String[snowyCities.length];
        for (int i = 0; i < snowyCities.length; i++) {
            if (snowyCities[i] != null) {
                snowyCitiesString[i] = "City Name: " + snowyCities[i].getName() + "(Population:" + snowyCities[i].getPopulation() + ", Area:" + snowyCities[i].getArea() + ", Temperature: " + snowyCities[i].getCurrentTemperature() + ", Weather Condition: " + snowyCities[i].getCurrentWeatherCondition() + ")";
            }
        }
        return snowyCitiesString;
    }
}