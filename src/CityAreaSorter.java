public class CityAreaSorter implements CitySorter {
    @Override
    public City[] sortCities(City[] cities, Boolean increasingOrDecreasing) {
        if(increasingOrDecreasing){
            for(int i = 0; i < cities.length - 1; i++){
                for(int j = 0; j < cities.length - i -1;j++){
                    if(cities[j].getArea() > cities[j+1].getArea()){
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
                    if(cities[j].getArea() < cities[j+1].getArea()){
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
