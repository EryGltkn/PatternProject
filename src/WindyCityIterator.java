public class WindyCityIterator implements CityIterator {
    private Cities citiesInstance;
    private City[] cityList;
    private int currentIndex = 0;

    public WindyCityIterator() {
        citiesInstance = Cities.GetCitiesInstance();
        cityList = citiesInstance.getCityList();
    }

    @Override
    public boolean hasNext() {
        while (currentIndex < cityList.length) {
            if (cityList[currentIndex].getCurrentWeatherCondition().equals("Windy")) {
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
        City[] windyCities = new City[cityList.length];
        for (int i = 0; i < cityList.length; i++) {
            if (cityList[i].getCurrentWeatherCondition().equals("WINDY")) {
                windyCities[i] = cityList[i];
            }
        }
        return windyCities;
    }

    @Override
    public String[] getCityListInString() {
        City[] windyCities = getCityList();
        String[] windyCitiesString = new String[windyCities.length];
        for (int i = 0; i < windyCities.length; i++) {
            if (windyCities[i] != null) {
                windyCitiesString[i] = "City Name: " + windyCities[i].getName() + "(Population:" + windyCities[i].getPopulation() + ", Area:" + windyCities[i].getArea() + ", Temperature: " + windyCities[i].getCurrentTemperature() + ", Weather Condition: " + windyCities[i].getCurrentWeatherCondition() + ")";
            }
        }
        return windyCitiesString;
    }
}