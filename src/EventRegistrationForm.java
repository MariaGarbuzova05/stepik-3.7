import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventRegistrationForm extends JFrame implements ActionListener {

    private JTextField nameField, emailField, phoneField;
    private JTextArea addressArea;
    private JComboBox<String> eventTypeCombo;
    private JRadioButton maleRadio, femaleRadio;
    private JCheckBox agreeCheckbox;
    private JSpinner ageSpinner;
    private JList<String> interestsList;
    private JSlider experienceSlider;
    private JButton submitButton;

    public EventRegistrationForm() {
        // Set up the frame
        setTitle("Event Registration Form");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(0, 2, 10, 10)); // GridLayout for easy organization with gaps
        setLocationRelativeTo(null); // Center the window
        // Name
        add(new JLabel("Name:"));
        nameField = new JTextField();
        add(nameField);

        // Email
        add(new JLabel("Email:"));
        emailField = new JTextField();
        add(emailField);

        // Phone Number
        add(new JLabel("Phone Number:"));
        phoneField = new JTextField();
        add(phoneField);

        // Address
        add(new JLabel("Address:"));
        addressArea = new JTextArea(3, 20);
        JScrollPane addressScrollPane = new JScrollPane(addressArea); // Add scrollbars
        add(addressScrollPane);

        // Event Type
        add(new JLabel("Event Type:"));
        String[] eventTypes = {"Workshop", "Conference", "Seminar", "Webinar"};
        eventTypeCombo = new JComboBox<>(eventTypes);
        add(eventTypeCombo);

        // Gender (Radio Buttons)
        add(new JLabel("Gender:"));
        JPanel genderPanel = new JPanel();
        maleRadio = new JRadioButton("Male");
        femaleRadio = new JRadioButton("Female");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);
        genderPanel.add(maleRadio);
        genderPanel.add(femaleRadio);
        add(genderPanel);

        // Age
        add(new JLabel("Age:"));
        SpinnerModel ageModel = new SpinnerNumberModel(18, 0, 120, 1); // Initial, min, max, step
        ageSpinner = new JSpinner(ageModel);
        add(ageSpinner);

        // Interests (JList)
        add(new JLabel("Interests:"));
        String[] interests = {"Technology", "Science", "Art", "Music", "Sports"};
        interestsList = new JList<>(interests);
        JScrollPane interestsScrollPane = new JScrollPane(interestsList);
        add(interestsScrollPane);

        // Experience (Slider)
        add(new JLabel("Experience (Years):"));
        experienceSlider = new JSlider(0, 20, 0); // Min, max, initial
        experienceSlider.setMajorTickSpacing(5);
        experienceSlider.setMinorTickSpacing(1);
        experienceSlider.setPaintTicks(true);
        experienceSlider.setPaintLabels(true);
        add(experienceSlider);

        // Terms Agreement (Checkbox)
        add(new JLabel("I agree to the terms and conditions:"));
        agreeCheckbox = new JCheckBox();
        add(agreeCheckbox);

        // Submit Button
        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        add(submitButton);
        add(new JLabel("")); // Filler for layout consistency

        // Make the form visible
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            // Gather all the data
            String name = nameField.getText();
            String email = emailField.getText();
            String phone = phoneField.getText();
            String address = addressArea.getText();
            String eventType = (String) eventTypeCombo.getSelectedItem();
            String gender = maleRadio.isSelected() ? "Male" : (femaleRadio.isSelected() ? "Female" : "Not Specified");
            int age = (int) ageSpinner.getValue();
            String interests = String.join(", ", interestsList.getSelectedValuesList());
            int experience = experienceSlider.getValue();
            boolean agreed = agreeCheckbox.isSelected();

            // Create the message to display in JOptionPane
            String message = "Registration Details:\n" +
                    "Name: " + name + "\n" +
                    "Email: " + email + "\n" +
                    "Phone: " + phone + "\n" +
                    "Address: " + address + "\n" +
                    "Event Type: " + eventType + "\n" +
                    "Gender: " + gender + "\n" +
                    "Age: " + age + "\n" +
                    "Interests: " + interests + "\n" +
                    "Experience: " + experience + " years\n" +
                    "Agreed to Terms: " + agreed;

            // Display the data in a JOptionPane
            JOptionPane.showMessageDialog(this, message, "Registration Information", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(EventRegistrationForm::new);
    }
}