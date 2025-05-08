public class CityPopulationSorter implements CitySorter {
    @Override
    public City[] sortCities(City[] cities, Boolean increasingOrDecreasing) {
        if(increasingOrDecreasing){
            for(int i = 0; i < cities.length - 1; i++){
                for(int j = 0; j < cities.length - i -1;j++){
                    if(cities[j].getPopulation() > cities[j+1].getPopulation()){
                        City temp = cities[j];
                        cities[j] = cities[j+1];
                        cities[j+1] = temp;
                    }
                }
            }
        }
        else{
            for(int i = 0; i < cities.length - 1; i++){
                for(int j = 0; j < cities.length - i -1;j++){
                    if(cities[j].getPopulation() < cities[j+1].getPopulation()){
                        City temp = cities[j];
                        cities[j] = cities[j+1];
                        cities[j+1] = temp;
                    }
                }
            }
        }
        return cities;
    }
}
