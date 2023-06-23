package com.company;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JFrame;

class SquareAnimate extends JPanel {
    private int edge;
    private final JSlider slider = new JSlider();

    SquareAnimate() {
        this.setLayout(new BorderLayout());
        this.slider.setPaintTicks(true);
        this.slider.setPaintLabels(true);
        this.slider.setMajorTickSpacing(20);
        this.slider.setMinorTickSpacing(10);
        this.add(this.slider, "Last");
        this.edge = this.slider.getValue();
        this.slider.addChangeListener((e) -> {
            this.setEdge(this.slider.getValue());
        });
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        int sideLength = (int)((double)Math.min(this.getSize().width, this.getSize().height) * ((double)this.edge / 100.0D));
        int x = this.getSize().width / 2 - sideLength / 2;
        int y = this.getSize().height / 2 - sideLength / 2;
        g.fillRect(x, y, sideLength, sideLength);
    }

    public int getEdge() {
        return this.edge;
    }

    public void setEdge(int edge) {
        this.edge = edge;
        this.repaint();
    }
}

public class Main {

    public static void main(String[] args) {
        JFrame frame=new JFrame("Resizable Square");
        frame.setSize(500,500);
        frame.setContentPane(new SquareAnimate());
        frame.setDefaultCloseOperation(2);
        frame.setVisible(true);

    }
}
