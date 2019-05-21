package drawer;

import drawer.chart.ChartFrame;

import javax.swing.*;
import java.awt.*;

public class DrawFrame extends JFrame {
    private StringBuilder title = new StringBuilder("Draw Frame");
    private StringBuilder function;

    public DrawFrame(){
        this.setTitle(title.toString());
        this.setPreferredSize(
                new Dimension(Preferences.FRAME_WIDTH, Preferences.FRAME_HEIGHT)
        );
        this.setResizable(false);
    }

    public DrawFrame draw(String function){
        this.function = new StringBuilder(function);
        return this;
    }

    public void display(){
        if(function == null){
            throw new NullPointerException(
                    "Error. You haven't defined the function to draw!"
            );
        } else {
            JPanel desk = new JPanel();
            ChartFrame chart = new ChartFrame(title.toString(), getFunction());
            chart.draw(getFunction());
            chart.display();
        }
    }

    public String getFunction(){
        return function.toString();
    }

    @Override
    public String toString() {
        return this.getTitle();
    }
}
