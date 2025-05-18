public abstract class CityPlannerDecorator implements CityPlanner {
    protected CityPlanner planner;

    public CityPlannerDecorator(CityPlanner planner) {
        this.planner = planner;
    }

    @Override
    public String getDescription() {
        return planner.getDescription();
    }

    @Override
    public double getTotalCost() {
        return planner.getTotalCost();
    }

    @Override
    public double getTotalTime() {
        return planner.getTotalTime();
    }
}
