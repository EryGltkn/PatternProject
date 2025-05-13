import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ItemListener;
import java.util.concurrent.Flow;
import java.awt.event.ItemEvent;

public class UI {

    private static int frameWidth = 800;
    private static int frameHeight = 600;

    public static void createMainFrame(){
        CityIterator allCityIterator = new AllCityIterator();
        CityIterator sunnyCityIterator = new SunnyCityIterator();
        CityIterator rainyCityIterator = new RainyCityIterator();
        CityIterator snowyCityIterator = new SnowyCityIterator();
        CityIterator windyCityIterator = new WindyCityIterator();

        JFrame mainFrame = new JFrame("City Information");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainFrame.setSize(frameWidth, frameHeight);

        

        //Center Panel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));

        JPanel allCitiesSortedPanel = new JPanel();
        allCitiesSortedPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        allCitiesSortedPanel.setBorder(BorderFactory.createTitledBorder("All Cities (Sorted)"));
        
        JList<String> cityList1 = new JList<>(allCityIterator.getCityListInString());
        JScrollPane allCitiesScrollPane = new JScrollPane(cityList1);

        JPanel citiesByWeatherPanel = new JPanel();
        citiesByWeatherPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        citiesByWeatherPanel.setBorder(BorderFactory.createTitledBorder("Cities by Weather Condition"));

        JList<String> cityList2 = new JList<>(windyCityIterator.getCityListInString());
        JScrollPane citiesByWeatherScrollPane = new JScrollPane(cityList2);

        citiesByWeatherPanel.add(citiesByWeatherScrollPane);
        
        
        allCitiesSortedPanel.add(allCitiesScrollPane);




        centerPanel.add(allCitiesSortedPanel);
        centerPanel.add(citiesByWeatherPanel);
        


        createTopPanel();
        // Add the panel to the top of the frame
        mainFrame.add(createTopPanel(), BorderLayout.NORTH);
        mainFrame.add(centerPanel, BorderLayout.CENTER);

        // Make the frame visible
        mainFrame.setVisible(true);
    }   
    
    private static JPanel createTopPanel(){
        // Top Control Panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.setBorder(BorderFactory.createTitledBorder("Controls"));

        //LabelPanel
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        //ComboBoxPanel
        JPanel comboPanel = new JPanel();
        comboPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        //Labels
        JPanel comboLabePanel = new JPanel();
        comboLabePanel.setLayout(new BoxLayout(comboLabePanel, BoxLayout.Y_AXIS));

        JLabel weatherLabel = new JLabel("Weather Condition");
        JLabel sortingLabel = new JLabel("Sorting Type");

        comboLabePanel.add(weatherLabel);
        comboLabePanel.add(sortingLabel);

        labelPanel.add(comboLabePanel);

        //ComboBox
        JPanel comboBoxPanel = new JPanel();
        comboBoxPanel.setLayout(new BoxLayout(comboBoxPanel, BoxLayout.Y_AXIS));
        
        String[] weatherConditions = { "Sunny", "Rainy", "Snowy", "Windy"};
        JComboBox<String> weatherComboBox = new JComboBox<>(weatherConditions);

        String[] sortingTypes = {"Population", "Area", "Temperature"};
        JComboBox<String> sortingComboBox = new JComboBox<>(sortingTypes);

        comboBoxPanel.add(weatherComboBox);
        comboBoxPanel.add(sortingComboBox);

        comboPanel.add(comboBoxPanel);

        //adding to main Top Panel
        topPanel.add(labelPanel);
        topPanel.add(comboPanel);

        return topPanel;
    }
    public static void main(String[] args) {
        createMainFrame();
    }
}
