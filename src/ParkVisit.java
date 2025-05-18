public class ParkVisit extends CityPlannerDecorator {
    public ParkVisit(CityPlanner planner) { super(planner); }

    @Override
    public String getDescription() {
        return planner.getDescription() + ", Park Visit";
    }

    @Override
    public double getTotalCost() {
        return planner.getTotalCost() + 5;
    }

    @Override
    public double getTotalTime() {
        return planner.getTotalTime() + 1;
    }
}