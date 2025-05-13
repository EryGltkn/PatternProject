import javax.swing.*;
import java.awt.*;

public class UI {

    private static final int frameWidth = 800;
    private static final int frameHeight = 600;

    private static DefaultListModel<String> cityListModel1 = new DefaultListModel<>();
    private static JList<String> cityList1 = new JList<>(cityListModel1);
    private static CityIterator allCityIterator = new AllCityIterator();

    public static void main(String[] args) {
        createMainFrame();
        CityRandomizer.StartRandomizerThread();
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

        updateCityListModel();
        JScrollPane scrollPane = new JScrollPane(cityList1);

        panel.add(scrollPane);
        return panel;
    }

    private static JPanel createCitiesByWeatherPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBorder(BorderFactory.createTitledBorder("Cities by Weather Condition"));

        CityIterator sunnyCityIterator = new SunnyCityIterator();
        JList<String> cityList2 = new JList<>(sunnyCityIterator.getCityListInString());
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

        String[] sortingTypes = {"Population", "Area", "Temperature"};
        JComboBox<String> sortingComboBox = new JComboBox<>(sortingTypes);

        comboBoxContainer.add(weatherComboBox);
        comboBoxContainer.add(sortingComboBox);
        comboPanel.add(comboBoxContainer);

        return comboPanel;
    }

    public static void updateUI() {
        SwingUtilities.invokeLater(UI::updateCityListModel);
    }
    
    private static void updateCityListModel() {
        cityListModel1.clear();
        for (String cityStr : allCityIterator.getCityListInString()) {
            cityListModel1.addElement(cityStr);
        }
    }
}
