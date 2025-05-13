public class RainyCityIterator implements CityIterator {
    private Cities citiesInstance;
    private City[] cityList;
    private int currentIndex = 0;

    public RainyCityIterator() {
        citiesInstance = Cities.GetCitiesInstance();
        cityList = citiesInstance.getCityList();
    }

    @Override
    public boolean hasNext() {
        while (currentIndex < cityList.length) {
            if (cityList[currentIndex].getCurrentWeatherCondition().equals("Rainy")) {
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
        City[] rainyCities = new City[cityList.length];
        for (int i = 0; i < cityList.length; i++) {
            if (cityList[i].getCurrentWeatherCondition().equals("Rainy")) {
                rainyCities[i] = cityList[i];
            }
        }
        return rainyCities;
    }
    @Override
    public String[] getCityListInString() {
        City[] rainyCities = getCityList();
        String[] rainyCitiesString = new String[rainyCities.length];
        for (int i = 0; i < rainyCities.length; i++) {
            if (rainyCities[i] != null) {
                rainyCitiesString[i] = "City Name: " + rainyCities[i].getName() +"(Population:" + rainyCities[i].getPopulation()+", Area:" + rainyCities[i].getArea() +", Temperature"+rainyCities[i].getCurrentTemperature() + ", Weather Condition: " + rainyCities[i].getCurrentWeatherCondition()+")";
            }
        }
        return rainyCitiesString;
    }
}
