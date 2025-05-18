public class MuseumVisit extends CityPlannerDecorator {
    public MuseumVisit(CityPlanner planner) {
        super(planner);
    }

    @Override
    public String getDescription() {
        return planner.getDescription() + ", Museum Visit";
    }

    @Override
    public double getTotalCost() {
        return planner.getTotalCost() + 15;
    }

    @Override
    public double getTotalTime() {
        return planner.getTotalTime() + 2;
    }
}
