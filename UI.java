import javax.swing.*;
import java.awt.*;

public class UI {

    private static final int frameWidth = 800;
    private static final int frameHeight = 600;

    private static DefaultListModel<String> cityListModel1 = new DefaultListModel<>();
    private static DefaultListModel<String> cityListModelByWeather = new DefaultListModel<>();
    private static JList<String> cityList1 = new JList<>(cityListModel1);
    private static JList<String> cityList2 = new JList<>(cityListModelByWeather);
    private static CityIterator allCityIterator = new AllCityIterator();
    private static CityIterator cityIterator = new SunnyCityIterator();
    private static CitySorter citySorter = new CityNameSorter();

    public static void main(String[] args) {
        createMainFrame();
        CityRandomizer.StartRandomizerThread();
        updateUI();
    }

    public static void createMainFrame() {
        JFrame mainFrame = new JFrame("City Information");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(frameWidth, frameHeight);
        mainFrame.setLayout(new BorderLayout());

        mainFrame.add(createTopPanel(), BorderLayout.NORTH);
        mainFrame.add(createCenterPanel(), BorderLayout.CENTER);

        mainFrame.setVisible(true);
    }

    private static JPanel createCenterPanel() {
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));

        centerPanel.add(createAllCitiesPanel());
        centerPanel.add(createCitiesByWeatherPanel());

        return centerPanel;
    }

    private static JPanel createAllCitiesPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBorder(BorderFactory.createTitledBorder("All Cities (Sorted)"));

        updateAllCityListModel();
        JScrollPane scrollPane = new JScrollPane(cityList1);

        panel.add(scrollPane);
        return panel;
    }

    private static JPanel createCitiesByWeatherPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBorder(BorderFactory.createTitledBorder("Cities by Weather Condition"));


        updateWeatherCityList();
        JScrollPane scrollPane = new JScrollPane(cityList2);

        panel.add(scrollPane);
        return panel;
    }

    private static JPanel createTopPanel() {
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.setBorder(BorderFactory.createTitledBorder("Controls"));

        topPanel.add(createLabelPanel());
        topPanel.add(createComboBoxPanel());

        return topPanel;
    }

    private static JPanel createLabelPanel() {
        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel labelContainer = new JPanel();
        labelContainer.setLayout(new BoxLayout(labelContainer, BoxLayout.Y_AXIS));

        labelContainer.add(new JLabel("Weather Condition"));
        labelContainer.add(new JLabel("Sorting Type"));

        labelPanel.add(labelContainer);
        return labelPanel;
    }

    private static JPanel createComboBoxPanel() {
        JPanel comboPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel comboBoxContainer = new JPanel();
        comboBoxContainer.setLayout(new BoxLayout(comboBoxContainer, BoxLayout.Y_AXIS));

        String[] weatherConditions = {"Sunny", "Rainy", "Snowy", "Windy"};
        JComboBox<String> weatherComboBox = new JComboBox<>(weatherConditions);

        String[] sortingTypes = {"Population", "Area", "Name"};
        JComboBox<String> sortingComboBox = new JComboBox<>(sortingTypes);

        weatherComboBox.addActionListener(e -> {
            String selectedWeather = (String) weatherComboBox.getSelectedItem();
            if (selectedWeather != null) {
                updateCityIterator(selectedWeather);
                updateUI();
            }
        });

        sortingComboBox.addActionListener(e -> {
            String selectedSorting = (String) sortingComboBox.getSelectedItem();
            if (selectedSorting != null) {
                updateCitySorter(selectedSorting);
                updateUI();
            }
        });

        comboBoxContainer.add(weatherComboBox);
        comboBoxContainer.add(sortingComboBox);
        comboPanel.add(comboBoxContainer);

        return comboPanel;
    }

    public static void updateUI() {
        SwingUtilities.invokeLater(UI::updateAllCityListModel);
        SwingUtilities.invokeLater(UI::updateWeatherCityList);
        SwingUtilities.invokeLater(() -> {
            cityList1.setModel(cityListModel1);
            cityList2.setModel(cityListModelByWeather);
        });
        
    }
    
    private static void updateCityIterator(String weatherCondition) {
        switch (weatherCondition) {
            case "Sunny":
                cityIterator = new SunnyCityIterator();
                break;
            case "Rainy":
                cityIterator = new RainyCityIterator();
                break;
            case "Snowy":
                cityIterator = new SnowyCityIterator();
                break;
            case "Windy":
                cityIterator = new WindyCityIterator();
                break;
        }
    }

    private static void updateCitySorter(String selectedSorting) {
    switch (selectedSorting) {
        case "Population":
            citySorter = new CityPopulationSorter();
            break;
        case "Area":
            citySorter = new CityAreaSorter();
            break;
        case "Name":
            citySorter = new CityNameSorter();
            break;
    }
}

    private static void updateAllCityListModel() {
        cityListModel1.clear();
        City[] cityList = allCityIterator.getCityList();
        cityList = citySorter.sortCities(cityList, true);
        for (City city : cityList) {
            if (city != null) {
                cityListModel1.addElement("City Name: " + city.getName() + " (Population: " + city.getPopulation() + ", Area: " + city.getArea() + ", Temperature: " + city.getCurrentTemperature() + ", Weather Condition: " + city.getCurrentWeatherCondition() + ")");
            }
        }
    }

    private static void updateWeatherCityList() {
        cityListModelByWeather.clear();
        City[] cityList = cityIterator.getCityList();
        cityList = citySorter.sortCities(cityList, true);
        for (City city : cityList) {
            if (city != null) {
                cityListModelByWeather.addElement("City Name: " + city.getName() + " (Population: " + city.getPopulation() + ", Area: " + city.getArea() + ", Temperature: " + city.getCurrentTemperature() + ", Weather Condition: " + city.getCurrentWeatherCondition() + ")");
            }
        }
    }
}
