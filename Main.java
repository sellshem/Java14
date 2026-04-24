import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Регистрация");
        frame.setSize(350, 380);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(6, 1, 0, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(Color.WHITE);

        JTextField nameField = new JTextField("Ваше имя");
        JTextField emailField = new JTextField("Ваш e-mail");
        JTextField phoneField = new JTextField("Ваш телефон");

        JPasswordField passwordField = new JPasswordField("Пароль");
        passwordField.setEchoChar((char) 0);

        JPasswordField repeatPasswordField = new JPasswordField("Пароль еще раз");
        repeatPasswordField.setEchoChar((char) 0);

        JTextField[] fields = {
                nameField, emailField, phoneField,
                passwordField, repeatPasswordField
        };

        for (JTextField field : fields) {
            field.setFont(new Font("Arial", Font.PLAIN, 14));
            field.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
            field.setForeground(Color.GRAY);
        }

        clearOnClick(nameField, "Ваше имя");
        clearOnClick(emailField, "Ваш e-mail");
        clearOnClick(phoneField, "Ваш телефон");
        clearPasswordOnClick(passwordField, "Пароль");
        clearPasswordOnClick(repeatPasswordField, "Пароль еще раз");

        JButton button = new JButton("Регистрация");
        button.setBackground(new Color(0, 200, 0));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);

        button.addActionListener(e -> {
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String phone = phoneField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();
            String repeatPassword = new String(repeatPasswordField.getPassword()).trim();

            if (name.equals("Ваше имя") || email.equals("Ваш e-mail")
                    || phone.equals("Ваш телефон")
                    || password.equals("Пароль")
                    || repeatPassword.equals("Пароль еще раз")
                    || name.isEmpty() || email.isEmpty() || phone.isEmpty()
                    || password.isEmpty() || repeatPassword.isEmpty()) {

                JOptionPane.showMessageDialog(frame, "Заполните все поля");

            } else if (!isValidEmail(email)) {
                JOptionPane.showMessageDialog(frame, "Введите корректный e-mail");

            } else if (!password.equals(repeatPassword)) {
                JOptionPane.showMessageDialog(frame, "Пароли не совпадают");

            } else {
                try {
                    FileWriter writer = new FileWriter("users.txt", true);

                    writer.write("Имя: " + name + "\n");
                    writer.write("Email: " + email + "\n");
                    writer.write("Телефон: " + phone + "\n");
                    writer.write("Пароль: " + password + "\n");
                    writer.write("----------------------\n");

                    writer.close();

                    JOptionPane.showMessageDialog(frame, "Регистрация успешна!");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "Ошибка записи в файл");
                }
            }
        });

        panel.add(nameField);
        panel.add(emailField);
        panel.add(phoneField);
        panel.add(passwordField);
        panel.add(repeatPasswordField);
        panel.add(button);

        frame.add(panel);
        frame.setVisible(true);
    }

    private static boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }

    private static void clearOnClick(JTextField field, String placeholder) {
        field.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                }
            }
        });
    }

    private static void clearPasswordOnClick(JPasswordField field, String placeholder) {
        field.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (new String(field.getPassword()).equals(placeholder)) {
                    field.setText("");
                    field.setEchoChar('•');
                    field.setForeground(Color.BLACK);
                }
            }
        });
    }
}
