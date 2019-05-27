package drawer.validation;

import drawer.GeneralOptions;

import javax.swing.*;

public class InputDataValidator {
    public static String digit(String msg){
        boolean isValid = true;
        char []characters = msg.toCharArray();
        for (char character : characters) {
            if (!digitValidation(character)) {
                isValid = false;
                break;
            }
        }
        if(!isValid){
            digit(
                    JOptionPane.showInputDialog(
                            "Error! Input the valid data: {'0..9', '.'}"
                    )
            );
        }
        return msg;
    }

    private static boolean digitValidation(char digit){
        return
                InputDataValidator.toIntegerFromChar(digit) == ASCII.DOT || (InputDataValidator.toIntegerFromChar(digit) >= ASCII.ZERO && InputDataValidator.toIntegerFromChar(digit) <= ASCII.NINE);
    }

    public static char confirmation(String msg){
        if(msg.length() != 1){
            confirmation(
                    JOptionPane.showInputDialog(
                         "Error! The length of message is greater than 1. Valid symbols to enter: 'Y','N'. Would You like to save the image? (Y.N)"
                    )
            );
        } else {
            if(
                    InputDataValidator.toIntegerFromChar(msg.charAt(0)) != ASCII.N
                &&
                    InputDataValidator.toIntegerFromChar(msg.charAt(0)) != ASCII.Y){
                    confirmation(
                            JOptionPane.showInputDialog(
                                    "Error! The length of message is greater than 1. Valid symbols to enter: 'Y','N'. Would You like to save the image? (Y.N)"
                            )
                    );
            } else {
                return msg.charAt(0);
            }
        }
        return GeneralOptions.SAVE;
    }

    public static boolean bool(String msg){
        if(msg.length() != 1){
            confirmation(
                    JOptionPane.showInputDialog(
                            "Error! The length of message is greater than 1. Valid symbols to enter: 'Y','N'. Would You like to save the image? (Y.N)"
                    )
            );
        } else {
            if(
                    InputDataValidator.toIntegerFromChar(msg.charAt(0)) != ASCII.N
                            &&
                            InputDataValidator.toIntegerFromChar(msg.charAt(0)) != ASCII.Y){
                confirmation(
                        JOptionPane.showInputDialog(
                                "Error! The length of message is greater than 1. Valid symbols to enter: 'Y','N'. Would You like to save the image? (Y.N)"
                        )
                );
            } else {
                if(msg.charAt(0) == ASCII.Y){
                    return true;
                } else if(msg.charAt(0) == ASCII.N){
                    return false;
                }
            }
        }
        return false;
    }

    private static int toIntegerFromChar(char character){
        return (int) character;
    }
}
