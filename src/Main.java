public class Main {
    public static void main(String[] args) {
        UI.createMainFrame();
        CityRandomizer.StartRandomizerThread();
        UI.updateUI();
    }
}
