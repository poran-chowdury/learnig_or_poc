import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemperatureConverterGui extends JFrame {
    private JPanel mainPanel;
    private JTextField celsiusTextField;
    private JButton convertButton;
    private JLabel fahrenheitLabel;
    private JLabel celsiusLabel;

    public TemperatureConverterGui(String title) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        convertButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                int tempFahrenheit = (int) (Double.parseDouble(celsiusTextField.getText()) * 1.8 + 32);
                fahrenheitLabel.setText(tempFahrenheit + " Fahrenheit");

            }
        });
    }

    public static void main(String[] args) {
        JFrame jFrame = new TemperatureConverterGui("Temperature Converter");
        jFrame.setVisible(true);
    }
}
