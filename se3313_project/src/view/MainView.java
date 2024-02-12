package view;

import controller.Machine;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class MainView {

    private Machine observer;
    private JFrame frame;
    private JButton fillButton;
    private JTextField numberOfCupsTextField;
    private JButton startButton;
    private JButton totalCupsButton;
    private JButton resetButton;
    private JPanel totalCupsPanel;
    private JPanel idlePanel;
    private JPanel brewingPanel;
    private JPanel donePanel;
    private JPanel mainPanel;
    private JLabel totalCupsLabel;

    public MainView(Machine observer)
    {
        this.observer = observer;
        fillButton.setBackground(Color.YELLOW);
        fillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int n = Integer.parseInt(numberOfCupsTextField.getText());
                notifyMachine(n);
                numberOfCupsTextField.setText("0");
                notifyMachine("fill");
            }
        });
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notifyMachine("start");
            }
        });
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notifyMachine("reset");
            }
        });
        totalCupsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notifyMachine("totalCups");
            }
        });
    }

    public void startWindow()
    {
        frame = new JFrame("Coffee Maker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);
        frame.setResizable(false);

        frame.setContentPane(mainPanel);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }

    public void showError(String err)
    {
        JOptionPane.showMessageDialog(frame, err);
    }

    public void notifyMachine(Object o)
    {
        observer.update(o);
    }

    public void displayTotalCups(int totalCups)
    {
        totalCupsLabel.setText(String.valueOf(totalCups));
    }

    public void changeState(Object s)
    {
        if (Objects.equals(s, IdleState.class)) {
            fillButton.setBackground(Color.WHITE);
            idlePanel.setBackground(Color.YELLOW);
            brewingPanel.setBackground(Color.WHITE);
            donePanel.setBackground(Color.WHITE);
        } else if (Objects.equals(s, BrewingState.class)){
            fillButton.setBackground(Color.WHITE);
            idlePanel.setBackground(Color.WHITE);
            brewingPanel.setBackground(Color.YELLOW);
            donePanel.setBackground(Color.WHITE);
        } else if(Objects.equals(s, DoneState.class)){
            fillButton.setBackground(Color.WHITE);
            idlePanel.setBackground(Color.WHITE);
            brewingPanel.setBackground(Color.WHITE);
            donePanel.setBackground(Color.YELLOW);

        } else if(Objects.equals(s, EmptyState.class)){
            fillButton.setBackground(Color.YELLOW);
            idlePanel.setBackground(Color.WHITE);
            brewingPanel.setBackground(Color.WHITE);
            donePanel.setBackground(Color.WHITE);
        }
    }

}
