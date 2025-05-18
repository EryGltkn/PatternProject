public class CityCenterVisit extends CityPlannerDecorator {
    public CityCenterVisit(CityPlanner planner) { super(planner); }

    @Override
    public String getDescription() {
        return planner.getDescription() + ", City Center Visit";
    }

    @Override
    public double getTotalCost() {
        return planner.getTotalCost() + 10;
    }

    @Override
    public double getTotalTime() {
        return planner.getTotalTime() + 1.5;
    }
}