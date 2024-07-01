import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

interface CardDetails {
    String getName();
    int getAge();
    String getAddress();
    String getContactNumber();
    String getGender();
    String getSignature();
    String getStudentType(); // Added for student type (day scholar or hosteler)
}

class Person implements CardDetails {
    private String name;
    private int age;
    private String address;
    private String contactNumber;
    private String gender;
    private String signature;
    private String studentType; // Added for student type

    public Person(String name, int age, String address, String contactNumber, String gender, String signature, String studentType) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.contactNumber = contactNumber;
        this.gender = gender;
        this.signature = signature;
        this.studentType = studentType;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public String getContactNumber() {
        return contactNumber;
    }

    @Override
    public String getGender() {
        return gender;
    }

    @Override
    public String getSignature() {
        return signature;
    }

    @Override
    public String getStudentType() {
        return studentType;
    }
}

class IDCard extends JPanel {
    private CardDetails details;

    public IDCard(CardDetails details) {
        this.details = details;
        setPreferredSize(new Dimension(300, 400)); // Modified dimensions
        setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Added border
        setBackground(getColor(details.getStudentType())); // Set background color based on student type
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Font font = new Font("Arial", Font.BOLD, 16); // Set font for college name
        g.setFont(font);
        g.drawString("AMITY UNIVERSITY NOIDA", 50, 30); // Modified college name
        font = new Font("Arial", Font.PLAIN, 12); // Change font style here
        g.setFont(font);
        g.drawString("Name: " + details.getName(), 50, 60);
        g.drawString("Age: " + details.getAge(), 50, 90);
        g.drawString("Address: " + details.getAddress(), 50, 120);
        g.drawString("Contact Number: " + details.getContactNumber(), 50, 150);
        g.drawString("Gender: " + details.getGender(), 50, 180);
    }

    // Method to get color based on student type
    private Color getColor(String studentType) {
        if (studentType.equalsIgnoreCase("day scholar")) {
            return Color.ORANGE; // Orange color for day scholar
        } else if (studentType.equalsIgnoreCase("hosteler")) {
            return Color.GREEN; // Green color for hosteler
        }
        return Color.WHITE; // Default color
    }
}

public class IDCardGenerator extends JFrame {
    private JTextField nameField, ageField, addressField, contactField, genderField, signatureField, studentTypeField;
    private JButton generateButton;

    public IDCardGenerator() {
        setTitle("ID Card Generator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel inputPanel = new JPanel(new GridLayout(8, 2));
        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Age:"));
        ageField = new JTextField();
        inputPanel.add(ageField);
        inputPanel.add(new JLabel("Address:"));
        addressField = new JTextField();
        inputPanel.add(addressField);
        inputPanel.add(new JLabel("Contact Number:"));
        contactField = new JTextField();
        inputPanel.add(contactField);
        inputPanel.add(new JLabel("Gender:"));
        genderField = new JTextField();
        inputPanel.add(genderField);
        inputPanel.add(new JLabel("Signature Path:"));
        signatureField = new JTextField();
        inputPanel.add(signatureField);
        inputPanel.add(new JLabel("Student Type (Day Scholar/Hosteler):")); // Added student type input
        studentTypeField = new JTextField();
        inputPanel.add(studentTypeField);

        generateButton = new JButton("Generate ID Card");
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = nameField.getText();
                    if (!name.matches("[a-zA-Z\\s]+")) {
                        throw new IllegalArgumentException("Name should only contain alphabets.");
                    }
                    name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase(); // Capitalize first letter

                    int age = Integer.parseInt(ageField.getText());
                    String address = addressField.getText();

                    String contactNumber = contactField.getText();
                    if (!contactNumber.matches("\\d{10}")) {
                        throw new IllegalArgumentException("Contact number should be a 10-digit number.");
                    }

                    String gender = genderField.getText();
                    String signaturePath = signatureField.getText();
                    String studentType = studentTypeField.getText();

                    Person person = new Person(name, age, address, contactNumber, gender, signaturePath, studentType);

                    JFrame idCardFrame = new JFrame("ID Card");
                    idCardFrame.setLayout(new BorderLayout());
                    idCardFrame.setSize(300, 400); // Modified dimensions

                    IDCard idCardPanel = new IDCard(person);

                    idCardFrame.add(idCardPanel, BorderLayout.CENTER);
                    idCardFrame.setVisible(true);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid age.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        getContentPane().add(inputPanel, BorderLayout.CENTER);
        getContentPane().add(generateButton, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new IDCardGenerator().setVisible(true);
            }
        });
    }
}