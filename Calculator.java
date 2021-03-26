import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.rmi.server.UnicastRemoteObject;

public class Calculator implements ActionListener{

    JFrame frame;
    JTextField textField;
    JButton[] numbers = new JButton[10];
    JButton[] operators = new JButton[9];
    JButton addBut, subBut, multBut, divBut;
    JButton decBut, equBut, delBut, clrscrBut, negBut;
    JPanel panel;
    ImageIcon img;

    Font universalFont = new Font("Ink Free", Font.ITALIC, 28);
    Font universalWhiteFont = new Font("Ink Free", Font.ITALIC, 28);
    Font NumberFont = new Font("Castellar", Font.BOLD, 30);

    double number1=0, number2=0, result=0;
    char function;

    Calculator() {
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 600);
        frame.setLayout(null);
        img = new ImageIcon("Icon.jpg");
        frame.setIconImage(img.getImage());
        frame.getContentPane().setBackground(Color.decode("#070f1d"));
        frame.setResizable(false);

        textField = new JTextField();
        textField.setBounds(50, 25, 300, 50);
        textField.setFont(NumberFont);
        textField.setEditable(false);

        addBut = new JButton("+");
        subBut = new JButton("-");
        multBut = new JButton("*");
        divBut = new JButton("/");
        decBut = new JButton(".");
        equBut = new JButton("=");
        delBut = new JButton("Del");
        clrscrBut = new JButton("Clr");
        negBut = new JButton("(-)");

        operators[0]=addBut;
        operators[1]=subBut;
        operators[2]=multBut;
        operators[3]=divBut;
        operators[4]=decBut;
        operators[5]=equBut;
        operators[6]=delBut;
        operators[7]=clrscrBut;
        operators[8]=negBut;

        for (int i=0; i<9;i++) {
            operators[i].addActionListener(this);
            operators[i].setFont(NumberFont);
            operators[i].setFocusable(false);
            operators[i].setBackground(Color.decode("#070f1d"));
            operators[i].setForeground(Color.decode("#ffffff"));
        }
        operators[6].setFont(universalFont);
        operators[7].setFont(universalFont);

        for (int i=0; i<10;i++) {
            numbers[i] = new JButton(String.valueOf(i));
            numbers[i].addActionListener(this);
            numbers[i].setFont(NumberFont);
            numbers[i].setFocusable(false);
            numbers[i].setBackground(Color.decode("#070f1d"));
            numbers[i].setForeground(Color.decode("#ffffff"));
        }

        negBut.setBounds(50,430,100,50);
        delBut.setBounds(150, 430, 100, 50);
        clrscrBut.setBounds(250, 430, 100, 50);

        panel = new JPanel();
        panel.setBounds(50, 100, 300, 300);
        panel.setLayout(new GridLayout(4,4,10,10));
        panel.setBackground(Color.decode("#070f1d"));

        panel.add(numbers[1]);
        panel.add(numbers[2]);
        panel.add(numbers[3]);
        panel.add(addBut);
        panel.add(numbers[4]);
        panel.add(numbers[5]);
        panel.add(numbers[6]);
        panel.add(subBut);
        panel.add(numbers[7]);
        panel.add(numbers[8]);
        panel.add(numbers[9]);
        panel.add(multBut);
        panel.add(decBut);
        panel.add(numbers[0]);
        panel.add(equBut);
        panel.add(divBut);

        frame.add(panel);
        frame.add(delBut);
        frame.add(clrscrBut);
        frame.add(negBut);
        frame.add(textField);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i=0; i<10; i++) {
            if (e.getSource() == numbers[i]) {
                textField.setText(textField.getText().concat(String.valueOf(i)));
            }
        }
        if (e.getSource()==clrscrBut) {
            textField.setText(null);
        }
        if (e.getSource()==delBut){
            String temp = textField.getText();
            textField.setText("");
            for (int i=0; i<temp.length()-1; i++){
                textField.setText(textField.getText()+temp.charAt(i));
            }
        }
        if (e.getSource()==negBut) {
            double tempnum = Double.parseDouble(textField.getText());
            tempnum *= -1;
            textField.setText(String.valueOf(tempnum));
        }
        if (e.getSource() == decBut) {
            textField.setText(textField.getText().concat("."));
        }
        if (e.getSource()==addBut){
            number1 = Double.parseDouble(textField.getText());
            function = '+';
            textField.setText("");
        }
        if (e.getSource()==subBut){
            number1 = Double.parseDouble(textField.getText());
            function = '-';
            textField.setText("");
        }
        if (e.getSource()==multBut){
            number1 = Double.parseDouble(textField.getText());
            function = '*';
            textField.setText("");
        }
        if (e.getSource()==divBut){
            number1 = Double.parseDouble(textField.getText());
            function = '/';
            textField.setText("");
        }
        if (e.getSource()==equBut){
            number2 = Double.parseDouble(textField.getText());

            switch (function){
                case '+':
                    result = number1+number2;
                    break;
                case '-':
                    result = number1-number2;
                    break;
                case '*':
                    result = number1*number2;
                    break;
                case '/':
                    result = number1/number2;
                    break;
                default:
                    break;
            }
            textField.setText(String.valueOf(result));
            number1 = result;
        }
    }
}
