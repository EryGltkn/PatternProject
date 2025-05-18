public class ShoppingMallVisit extends CityPlannerDecorator {
    public ShoppingMallVisit(CityPlanner planner) { super(planner); }

    @Override
    public String getDescription() {
        return planner.getDescription() + ", Shopping Mall Visit";
    }

    @Override
    public double getTotalCost() {
        return planner.getTotalCost() + 25;
    }

    @Override
    public double getTotalTime() {
        return planner.getTotalTime() + 3;
    }
}