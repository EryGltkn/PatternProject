import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;


public class UI {

    private static final int frameWidth = 800;
    private static final int frameHeight = 600;

    private static DefaultListModel<String> cityListModel1 = new DefaultListModel<>();
    private static DefaultListModel<String> cityListModelByWeather = new DefaultListModel<>();
    private static City[] sortedCityList = new City[0];
    private static JList<String> cityList1 = new JList<>(cityListModel1);
    private static JList<String> cityList2 = new JList<>(cityListModelByWeather);
    private static CityIterator allCityIterator = new AllCityIterator();
    private static CityIterator cityIterator = new SunnyCityIterator();
    private static CitySorter citySorter = new CityNameSorter();

    private static DefaultCategoryDataset temperatureDataset = new DefaultCategoryDataset();
    private static ChartPanel temperatureChartPanel;

    private static DefaultPieDataset weatherDataset = new DefaultPieDataset();
    private static ChartPanel weatherChartPanel;

    private static JCheckBox museumCheck = new JCheckBox("Museum Visit");
    private static JCheckBox mallCheck = new JCheckBox("Shopping Mall Visit");
    private static JCheckBox parkCheck = new JCheckBox("Park Visit");
    private static JCheckBox centerCheck = new JCheckBox("City Center Visit");
    private static JLabel plannerResultLabel = new JLabel("Plan details will appear here.");
    private static JTextField costField = new JTextField(15); // to display total cost
    
    public static void createMainFrame() {
        JFrame mainFrame = new JFrame("City Information");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(frameWidth, frameHeight);
        mainFrame.setLayout(new BorderLayout());

        mainFrame.add(createTopPanel(), BorderLayout.NORTH);
        mainFrame.add(createCenterPanel(), BorderLayout.CENTER);
        mainFrame.add(createBottomPanel(), BorderLayout.SOUTH);

        mainFrame.setVisible(true);
    }

    private static JPanel createCenterPanel() {
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));

        centerPanel.add(createAllCitiesPanel());
        centerPanel.add(createCitiesByWeatherPanel());
        centerPanel.add(createPlannerPanel());


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
        panel.setSize(400, 100);


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

    private static JPanel createBottomPanel(){
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 2)); // Show both charts side-by-side

        bottomPanel.add(createBottomGraphPanel());  // Bar chart
        bottomPanel.add(createWeatherPieChart());  // Pie chart

        return bottomPanel;
    }


    private static JPanel createBottomGraphPanel(){
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        bottomPanel.add(createCitiesTempGraph());

        
        return bottomPanel;
    }

    private static JPanel createCitiesTempGraph() {
        JPanel graphPanel = new JPanel(new BorderLayout());
        graphPanel.setBorder(BorderFactory.createTitledBorder("City Temperature Graph"));

        // Initial fill
        updateTemperatureDataset();

        // Create chart
        JFreeChart barChart = ChartFactory.createBarChart(
                "Current Temperatures by City",
                "City",
                "Temperature (°C)",
                temperatureDataset
        );

        temperatureChartPanel = new ChartPanel(barChart);
        temperatureChartPanel.setPreferredSize(new Dimension(750, 300));

        graphPanel.add(temperatureChartPanel, BorderLayout.CENTER);
        return graphPanel;
    }

    private static JPanel createWeatherPieChart() {
        JPanel chartPanel = new JPanel(new BorderLayout());
        chartPanel.setBorder(BorderFactory.createTitledBorder("Weather Distribution"));

        // Fill dataset
        updateWeatherDataset();

        JFreeChart pieChart = ChartFactory.createPieChart(
                "Weather Conditions",
                weatherDataset,
                true, // legend
                true,
                false
        );

        weatherChartPanel = new ChartPanel(pieChart);
        weatherChartPanel.setPreferredSize(new Dimension(400, 300));
        chartPanel.add(weatherChartPanel, BorderLayout.CENTER);

        return chartPanel;
    }

    private static JPanel createPlannerPanel() {
        JPanel plannerPanel = new JPanel();
        plannerPanel.setBorder(BorderFactory.createTitledBorder("City Activity Planner"));
        plannerPanel.setLayout(new BoxLayout(plannerPanel, BoxLayout.Y_AXIS));

        // Checkboxes for activities
        plannerPanel.add(museumCheck);
        plannerPanel.add(mallCheck);
        plannerPanel.add(parkCheck);
        plannerPanel.add(centerCheck);

        // Plan button
        JButton planButton = new JButton("Plan Activities");
        plannerPanel.add(planButton);

        // Cost field
        costField.setEditable(false);
        plannerPanel.add(new JLabel("Total Cost ($):"));
        plannerPanel.add(costField);

        // Detailed description (optional)
        plannerPanel.add(plannerResultLabel);

        // Action when "Plan" button clicked
        planButton.addActionListener(e -> {
            int selectedIndex = cityList1.getSelectedIndex();
            if (selectedIndex == -1) {
                plannerResultLabel.setText("⚠️ Please select a city from the list above.");
                costField.setText("");
                return;
            }

            if (selectedIndex >= 0 && selectedIndex < sortedCityList.length) {
                City selectedCity = sortedCityList[selectedIndex];

                CityPlanner planner = new BaseCityPlanner(selectedCity);
                if (museumCheck.isSelected()) planner = new MuseumVisit(planner);
                if (mallCheck.isSelected()) planner = new ShoppingMallVisit(planner);
                if (parkCheck.isSelected()) planner = new ParkVisit(planner);
                if (centerCheck.isSelected()) planner = new CityCenterVisit(planner);

                double totalCost = planner.getTotalCost();
                double totalTime = planner.getTotalTime();

                costField.setText(String.format("%.2f", totalCost));
                plannerResultLabel.setText(
                    "<html>" + planner.getDescription()
                    + "<br>⏱️ Total Time: " + totalTime + " hours</html>"
                );
            } else {
                plannerResultLabel.setText("⚠️ Selected city index is invalid.");
                costField.setText("");
            }
        });

        return plannerPanel;
    }




    private static void updateTemperatureDataset() {
        temperatureDataset.clear();

        City[] cityList = allCityIterator.getCityList();
        cityList = citySorter.sortCities(cityList, true);

        for (City city : cityList) {
            if (city != null) {
                temperatureDataset.addValue(city.getCurrentTemperature(), "Temperature", city.getName());
            }
        }
    }

    private static void updateWeatherDataset() {
        weatherDataset.clear();

        City[] cityList = allCityIterator.getCityList();

        Map<String, Integer> weatherCounts = new HashMap<>();
        for (City city : cityList) {
            if (city != null) {
                String weather = city.getCurrentWeatherCondition();
                weatherCounts.put(weather, weatherCounts.getOrDefault(weather, 0) + 1);
            }
        }

        for (Map.Entry<String, Integer> entry : weatherCounts.entrySet()) {
            weatherDataset.setValue(entry.getKey(), entry.getValue());
        }
    }


    

    public static void updateUI() {
        SwingUtilities.invokeLater(UI::updateAllCityListModel);
        SwingUtilities.invokeLater(UI::updateWeatherCityList);
        SwingUtilities.invokeLater(() -> {
            cityList1.setModel(cityListModel1);
            cityList2.setModel(cityListModelByWeather);
            updateTemperatureDataset();
            updateWeatherDataset();
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
        sortedCityList = citySorter.sortCities(cityList, true);  // Save for access

        for (City city : sortedCityList) {
            if (city != null) {
                cityListModel1.addElement(
                    "City Name: " + city.getName()
                    + " (Population: " + city.getPopulation()
                    + ", Area: " + city.getArea() + ")"
                );
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
