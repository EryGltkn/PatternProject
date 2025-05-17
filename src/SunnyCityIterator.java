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
        int index = 0;

        for (int i = 0; i < cityList.length; i++) {
             if (cityList[i] != null && "Sunny".equalsIgnoreCase(cityList[i].getCurrentWeatherCondition())) {
                sunnyCities[index++] = cityList[i];
            }
        }
        City[] result = new City[index];
        System.arraycopy(sunnyCities, 0, result, 0, index);
        return result;
    }
}