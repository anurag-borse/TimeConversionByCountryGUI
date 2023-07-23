import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class TimeConversionByCountryGUI extends JFrame {
    private Map<String, String> countryTimeZones;

    private JLabel countryLabel;
    private JComboBox<String> countryComboBox;
    private JButton convertButton;
    private JLabel resultLabel;

    public TimeConversionByCountryGUI() {

        countryTimeZones = new HashMap<>();
        countryTimeZones.put("US", "America/New_York");
        countryTimeZones.put("UK", "Europe/London");
        countryTimeZones.put("IN", "Asia/Kolkata");
        countryTimeZones.put("JP", "Asia/Tokyo");
        countryTimeZones.put("AU", "Australia/Sydney");
        countryTimeZones.put("CA", "America/Toronto");
        countryTimeZones.put("BR", "America/Sao_Paulo");
        countryTimeZones.put("CN", "Asia/Shanghai");
        countryTimeZones.put("FR", "Europe/Paris");
        countryTimeZones.put("RU", "Europe/Moscow");

        countryLabel = new JLabel("Select Country:");
        countryComboBox = new JComboBox<>();
        for (Map.Entry<String, String> entry : countryTimeZones.entrySet()) {
            String countryCode = entry.getKey();
            String countryName = getCountryName(countryCode);
            countryComboBox.addItem(countryCode + " - " + countryName);
        }
        convertButton = new JButton("Convert Time");
        resultLabel = new JLabel();

        setLayout(new GridLayout(4, 1));
        add(countryLabel);
        add(countryComboBox);
        add(convertButton);
        add(resultLabel);

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOption = (String) countryComboBox.getSelectedItem();
                if (selectedOption == null) {
                    resultLabel.setText("Invalid selection.");
                } else {
                    String countryCode = selectedOption.split(" - ")[0];
                    String timeZoneId = countryTimeZones.get(countryCode);
                    LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of(timeZoneId));
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    String formattedDateTime = localDateTime.format(formatter);
                    resultLabel.setText("Time in " + countryCode + ": " + formattedDateTime);
                }
            }
        });

        setTitle("Time Conversion by Country");
        setSize(600, 300); // Set a larger window size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private String getCountryName(String countryCode) {
        // Add more country names as needed
        switch (countryCode) {
            case "US":
                return "United States";
            case "UK":
                return "United Kingdom";
            case "IN":
                return "India";
            case "JP":
                return "Japan";
            case "AU":
                return "Australia";
            case "CA":
                return "Canada";
            case "BR":
                return "Brazil";
            case "CN":
                return "China";
            case "FR":
                return "France";
            case "RU":
                return "Russia";
            default:
                return "Unknown";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TimeConversionByCountryGUI().setVisible(true);
            }
        });
    }
}
