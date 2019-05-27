package drawer;

import drawer.chart.ChartFrame;
import drawer.chart.FunctionConstants;
import drawer.validation.InputDataValidator;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;

public class DrawFrame extends JFrame {
    private StringBuilder function;

    public DrawFrame(){
        this.setTitle(
                GeneralOptions.TITLE
        );
        this.setPreferredSize(
                new Dimension(
                        GeneralOptions.FRAME_WIDTH,
                        GeneralOptions.FRAME_HEIGHT
                )
        );
        this.setResizable(
                GeneralOptions.RESIZABILITY
        );
    }

    public DrawFrame draw(String function){
        this.function =
                new StringBuilder(function);
        return this;
    }

    public void display(){
        if(function == null){
            throw new NullPointerException(
                    "Error. You haven't defined the function to draw!"
            );
        } else {
            while(GeneralOptions.CONTINUE) {
                JOptionPane
                        .showMessageDialog(this,
                                "Welcome to the function draw program! Input the data before setting up the graphic."
                        );

                FunctionConstants.STEP
                        = Double.parseDouble(
                        InputDataValidator.digit(
                                JOptionPane.showInputDialog(
                                        "Input the function step"
                                )
                        )
                );

                FunctionConstants.LEFT_BORDER
                        = Double.parseDouble(
                        InputDataValidator.digit(
                                JOptionPane.showInputDialog(
                                        "Input the function left border"
                                )
                        )
                );

                FunctionConstants.PARAMETER
                        = Double.parseDouble(
                        InputDataValidator.digit(
                                JOptionPane.showInputDialog(
                                        "Input the function parameter"
                                )
                        )
                );

                ChartFrame chart
                        = new ChartFrame(
                            GeneralOptions.TITLE,
                            getFunction()
                );
                chart.draw(getFunction());
                chart.display();

                GeneralOptions.SAVE
                        = InputDataValidator.confirmation(
                            JOptionPane.showInputDialog(
                                    "Do you want to save graphic? (Y/N)"
                            )
                );

                if (GeneralOptions.SAVE == 'Y') {
                    JFileChooser jfc =
                            new JFileChooser(
                                    FileSystemView
                                            .getFileSystemView()
                                            .getHomeDirectory()
                            );

                    this.setVisible(true);

                    int option = jfc.showSaveDialog(null);

                    if (option == JFileChooser.APPROVE_OPTION) {
                        GeneralOptions.SRC = jfc
                                .getSelectedFile()
                                .getAbsolutePath();
                        chart.saveGraphicAsPNG();
                    }
                }

                GeneralOptions.CONTINUE =
                        InputDataValidator.bool(
                                JOptionPane.showInputDialog("Want to draw one more time? (Y/N)")
                        );
            }
        }
    }

    private String getFunction(){
        return function.toString();
    }

    @Override
    public String toString() {
        return GeneralOptions.TITLE;
    }
}