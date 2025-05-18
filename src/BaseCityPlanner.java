public class BaseCityPlanner implements CityPlanner {
    protected City city;

    public BaseCityPlanner(City city) {
        this.city = city;
    }

    @Override
    public String getDescription() {
        return "Visit " + city.getName();
    }

    @Override
    public double getTotalCost() {
        return 0; 
    }

    @Override
    public double getTotalTime() {
        return 0;
    }

    public City getCity() {
        return city;
    }
}
