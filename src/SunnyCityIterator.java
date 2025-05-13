public class SunnyCityIterator implements CityIterator {
    private Cities citiesInstance;
    private City[] cityList;
    private int currentIndex = 0;

    public SunnyCityIterator() {
        citiesInstance = Cities.GetCitiesInstance();
        cityList = citiesInstance.getCityList();
    }

    @Override
    public boolean hasNext() {
        while (currentIndex < cityList.length) {
            if (cityList[currentIndex].getCurrentWeatherCondition().equals("Sunny")) {
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
        City[] sunnyCities = new City[cityList.length];
        for (int i = 0; i < cityList.length; i++) {
            if (cityList[i].getCurrentWeatherCondition().equals("SUNNY")) {
                sunnyCities[i] = cityList[i];
            }
        }
        return sunnyCities;
    }

    @Override
    public String[] getCityListInString() {
        City[] sunnyCities = getCityList();
        String[] sunnyCitiesString = new String[sunnyCities.length];
        for (int i = 0; i < sunnyCities.length; i++) {
            if (sunnyCities[i] != null) {
                sunnyCitiesString[i] = "City Name: " + sunnyCities[i].getName() + "(Population:" + sunnyCities[i].getPopulation() + ", Area:" + sunnyCities[i].getArea() + ", Temperature: " + sunnyCities[i].getCurrentTemperature() + ", Weather Condition: " + sunnyCities[i].getCurrentWeatherCondition() + ")";
            }
        }
        return sunnyCitiesString;
    }
}