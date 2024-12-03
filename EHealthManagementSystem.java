import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class EHealthManagementSystem {

    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    private ArrayList<Patient> patients; // Stores patient details
    private ArrayList<Appointment> appointments; // Stores appointments

    public static void main(String[] args) {
        new EHealthManagementSystem().initialize();
    }

    // Initialize the application
    public void initialize() {
        patients = new ArrayList<>();
        appointments = new ArrayList<>();

        frame = new JFrame("E-Health Management System");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Add pages to the card layout
        mainPanel.add(createLoginPage(), "LoginPage");
        mainPanel.add(createDashboardPage(), "DashboardPage");
        mainPanel.add(createPatientRegistrationPage(), "PatientRegistrationPage");
        mainPanel.add(createAppointmentPage(), "AppointmentPage");
        mainPanel.add(createViewPatientsPage(), "ViewPatientsPage");

        frame.add(mainPanel);
        frame.setVisible(true);

        cardLayout.show(mainPanel, "LoginPage");
    }

    // Create Navigation Panel
    private JPanel createNavigationPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JButton btnDashboard = new JButton("Dashboard");
        JButton btnRegisterPatient = new JButton("Register Patient");
        JButton btnViewPatients = new JButton("View Patients");
        JButton btnScheduleAppointment = new JButton("Schedule Appointment");
        JButton btnExit = new JButton("Exit");

        btnDashboard.addActionListener(e -> cardLayout.show(mainPanel, "DashboardPage"));
        btnRegisterPatient.addActionListener(e -> cardLayout.show(mainPanel, "PatientRegistrationPage"));
        btnViewPatients.addActionListener(e -> cardLayout.show(mainPanel, "ViewPatientsPage"));
        btnScheduleAppointment.addActionListener(e -> cardLayout.show(mainPanel, "AppointmentPage"));
        btnExit.addActionListener(e -> System.exit(0));

        panel.add(btnDashboard);
        panel.add(btnRegisterPatient);
        panel.add(btnViewPatients);
        panel.add(btnScheduleAppointment);
        panel.add(btnExit);

        return panel;
    }

    // Create Login Page
    private JPanel createLoginPage() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.LIGHT_GRAY);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(3, 2, 10, 10));

        JLabel lblUsername = new JLabel("Username:");
        JTextField txtUsername = new JTextField();
        JLabel lblPassword = new JLabel("Password:");
        JPasswordField txtPassword = new JPasswordField();
        JButton btnLogin = new JButton("Login");

        formPanel.add(lblUsername);
        formPanel.add(txtUsername);
        formPanel.add(lblPassword);
        formPanel.add(txtPassword);
        formPanel.add(new JLabel());
        formPanel.add(btnLogin);

        panel.add(new JLabel("E-Health Management System", JLabel.CENTER), BorderLayout.NORTH);
        panel.add(formPanel, BorderLayout.CENTER);

        btnLogin.addActionListener(e -> {
            String username = txtUsername.getText();
            String password = new String(txtPassword.getPassword());

            if (username.equals("admin") && password.equals("password")) {
                cardLayout.show(mainPanel, "DashboardPage");
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid credentials. Try again.");
            }
        });

        return panel;
    }

    // Create Dashboard Page
    private JPanel createDashboardPage() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(createNavigationPanel(), BorderLayout.NORTH);

        JPanel contentPanel = new JPanel(new GridLayout(4, 1, 10, 10));

        JButton btnRegisterPatient = new JButton("Register Patient");
        JButton btnViewPatients = new JButton("View Patients");
        JButton btnScheduleAppointment = new JButton("Schedule Appointment");
        JButton btnExit = new JButton("Exit");

        btnRegisterPatient.addActionListener(e -> cardLayout.show(mainPanel, "PatientRegistrationPage"));
        btnViewPatients.addActionListener(e -> cardLayout.show(mainPanel, "ViewPatientsPage"));
        btnScheduleAppointment.addActionListener(e -> cardLayout.show(mainPanel, "AppointmentPage"));
        btnExit.addActionListener(e -> System.exit(0));

        contentPanel.add(btnRegisterPatient);
        contentPanel.add(btnViewPatients);
        contentPanel.add(btnScheduleAppointment);
        contentPanel.add(btnExit);

        panel.add(contentPanel, BorderLayout.CENTER);
        return panel;
    }

    // Create Patient Registration Page
    private JPanel createPatientRegistrationPage() {
        JPanel panel = new JPanel(new BorderLayout());

        panel.add(createNavigationPanel(), BorderLayout.NORTH); // Add the navigation panel at the top

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        JLabel lblName = new JLabel("Name:");
        JTextField txtName = new JTextField();
        JLabel lblAge = new JLabel("Age:");
        JTextField txtAge = new JTextField();
        JLabel lblGender = new JLabel("Gender:");
        JComboBox<String> cmbGender = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        JLabel lblAddress = new JLabel("Address:");
        JTextField txtAddress = new JTextField();
        JButton btnRegister = new JButton("Register");

        formPanel.add(lblName);
        formPanel.add(txtName);
        formPanel.add(lblAge);
        formPanel.add(txtAge);
        formPanel.add(lblGender);
        formPanel.add(cmbGender);
        formPanel.add(lblAddress);
        formPanel.add(txtAddress);
        formPanel.add(new JLabel());
        formPanel.add(btnRegister);

        panel.add(new JLabel("Patient Registration", JLabel.CENTER), BorderLayout.CENTER);
        panel.add(formPanel, BorderLayout.CENTER);

        btnRegister.addActionListener(e -> {
            String name = txtName.getText();
            int age;
            try {
                age = Integer.parseInt(txtAge.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid age.");
                return;
            }
            String gender = (String) cmbGender.getSelectedItem();
            String address = txtAddress.getText();

            patients.add(new Patient(name, age, gender, address));
            JOptionPane.showMessageDialog(frame, "Patient registered successfully!");
            txtName.setText("");
            txtAge.setText("");
            txtAddress.setText("");
        });

        return panel;
    }

    // Create Appointment Page
    private JPanel createAppointmentPage() {
        JPanel panel = new JPanel(new BorderLayout());

        panel.add(createNavigationPanel(), BorderLayout.NORTH); // Add the navigation panel at the top

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        JLabel lblPatientName = new JLabel("Patient Name:");
        JTextField txtPatientName = new JTextField();
        JLabel lblDoctorName = new JLabel("Doctor Name:");
        JTextField txtDoctorName = new JTextField();
        JButton btnSchedule = new JButton("Schedule");

        formPanel.add(lblPatientName);
        formPanel.add(txtPatientName);
        formPanel.add(lblDoctorName);
        formPanel.add(txtDoctorName);
        formPanel.add(new JLabel());
        formPanel.add(btnSchedule);

        panel.add(new JLabel("Schedule Appointment", JLabel.CENTER), BorderLayout.NORTH);
        panel.add(formPanel, BorderLayout.CENTER);

        btnSchedule.addActionListener(e -> {
            String patientName = txtPatientName.getText();
            String doctorName = txtDoctorName.getText();

            appointments.add(new Appointment(patientName, doctorName));
            JOptionPane.showMessageDialog(frame, "Appointment scheduled successfully!");
            txtPatientName.setText("");
            txtDoctorName.setText("");
        });

        return panel;
    }

    // Create View Patients Page
    private JPanel createViewPatientsPage() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(createNavigationPanel(), BorderLayout.NORTH); // Add the navigation panel at the top

        String[] columns = {"Name", "Age", "Gender", "Address"};

        String[][] data = new String[patients.size()][4];
        for (int i = 0; i < patients.size(); i++) {
            Patient p = patients.get(i);
            data[i][0] = p.getName();
            data[i][1] = String.valueOf(p.getAge());
            data[i][2] = p.getGender();
            data[i][3] = p.getAddress();
        }

        JTable table = new JTable(data, columns);
        JScrollPane scrollPane = new JScrollPane(table);

        panel.add(new JLabel("Patient Details", JLabel.CENTER), BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    // Patient class
    class Patient {
        private String name;
        private int age;
        private String gender;
        private String address;

        public Patient(String name, int age, String gender, String address) {
            this.name = name;
            this.age = age;
            this.gender = gender;
            this.address = address;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public String getGender() {
            return gender;
        }

        public String getAddress() {
            return address;
        }
    }

    // Appointment class
    class Appointment {
        private String patientName;
        private String doctorName;

        public Appointment(String patientName, String doctorName) {
            this.patientName = patientName;
            this.doctorName = doctorName;
        }

        public String getPatientName() {
            return patientName;
        }

        public String getDoctorName() {
            return doctorName;
        }
    }
}
