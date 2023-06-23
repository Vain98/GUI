package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

class DrawPanel extends JPanel {
    Color backgroundColor = Color.BLACK;
    Color foregroundColor = Color.BLACK;

    public DrawPanel()
    {
        setPreferredSize(new Dimension(500, 200));
    }

    @Override
    protected void paintComponent(Graphics graphics)
    {
        super.paintComponent(graphics);
        graphics.setColor(backgroundColor);

        graphics.fillRect(0, 0, getWidth(), getHeight());

        graphics.setColor(foregroundColor);

        graphics.drawOval(getWidth() / 2 - 50, getHeight() / 2 - 50, 100, 100);
        graphics.fillOval(getWidth() / 2 - 30, getHeight() / 2 - 30, 60, 60);
    }
}
class ColorSelectorPanel extends JPanel {
    JSlider red, green, blue;
    JLabel redLabel, greenLabel, blueLabel, hexLabel;

    public ColorSelectorPanel(String title, ColorSelector parent)
    {
        red = new JSlider(JSlider.VERTICAL, 0, 255, 0);
        green = new JSlider(JSlider.VERTICAL, 0, 255, 0);
        blue = new JSlider(JSlider.VERTICAL, 0, 255, 0);

        red.addChangeListener(parent);
        green.addChangeListener(parent);
        blue.addChangeListener(parent);

        redLabel = new JLabel("000");
        greenLabel = new JLabel("000");
        blueLabel = new JLabel("000");
        hexLabel = new JLabel("#000000");


        JPanel redPanel = new JPanel();
        redPanel.setLayout(new BoxLayout(redPanel, BoxLayout.Y_AXIS));
        redPanel.add(red);
        redPanel.add(redLabel);
        redPanel.setBorder(BorderFactory.createTitledBorder("R"));


        JPanel greenPanel = new JPanel();
        greenPanel.setLayout(new BoxLayout(greenPanel, BoxLayout.Y_AXIS));
        greenPanel.add(green);
        greenPanel.add(greenLabel);
        greenPanel.setBorder(BorderFactory.createTitledBorder("G"));

        JPanel bluePanel = new JPanel();
        bluePanel.setLayout(new BoxLayout(bluePanel, BoxLayout.Y_AXIS));
        bluePanel.add(blue);
        bluePanel.add(blueLabel);
        bluePanel.setBorder(BorderFactory.createTitledBorder("B"));

        JPanel sliderPanel = new JPanel();
        sliderPanel.add(redPanel);
        sliderPanel.add(greenPanel);
        sliderPanel.add(bluePanel);

        JPanel hexPanel = new JPanel();
        hexPanel.setBorder(BorderFactory.createTitledBorder("Hex"));
        hexPanel.add(hexLabel);

        add(sliderPanel);
        add(hexPanel);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createTitledBorder(title));
    }
}

class ColorSelector extends JFrame implements ChangeListener
{
    ColorSelectorPanel backGround, foreGround;
    DrawPanel drawPanel;

    public ColorSelector()
    {

        super("Color Selector");

        drawPanel = new DrawPanel();
        backGround = new ColorSelectorPanel("Background", this);
        foreGround = new ColorSelectorPanel("Foreground", this);

        add(backGround, BorderLayout.WEST);
        add(foreGround, BorderLayout.EAST);
        add(drawPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }


    @Override
    public void stateChanged(ChangeEvent e)
    {
        if (e.getSource() == backGround.red || e.getSource() == backGround.green || e.getSource() == backGround.blue)
        {
            int redColor = backGround.red.getValue();
            int greenColor = backGround.green.getValue();
            int blueColor = backGround.blue.getValue();

            Color color = new Color(redColor, greenColor, blueColor);

            backGround.redLabel.setText(String.format("%03d", redColor));
            backGround.greenLabel.setText(String.format("%03d", greenColor));
            backGround.blueLabel.setText(String.format("%03d", blueColor));

            backGround.hexLabel.setText(String.format("#%02X%02X%02X", redColor, greenColor, blueColor));

            drawPanel.backgroundColor = color;

            repaint();
        }
        else if (e.getSource() == foreGround.red || e.getSource() == foreGround.green || e.getSource() == foreGround.blue) {
            int redColor = foreGround.red.getValue();
            int greenColor = foreGround.green.getValue();
            int blueColor = foreGround.blue.getValue();

            Color color = new Color(redColor, greenColor, blueColor);

            foreGround.redLabel.setText(String.format("%03d", redColor));
            foreGround.greenLabel.setText(String.format("%03d", greenColor));
            foreGround.blueLabel.setText(String.format("%03d", blueColor));
            foreGround.hexLabel.setText(String.format("#%02X%02X%02X", redColor,
                    greenColor, blueColor));

            drawPanel.foregroundColor = color;
            repaint();
        }
    }
}
public class Main {

    public static void main(String[] args) {
        new ColorSelector();
    }
}
