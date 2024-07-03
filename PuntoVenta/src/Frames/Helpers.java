package Frames;

import javax.swing.*;
import java.awt.*;
import javax.swing.text.JTextComponent;

public class Helpers {
    private static final Color disabledTextColor = Color.RED; // Elige el color del texto deshabilitado
    private static final Color disabledBackgroundColor = Color.LIGHT_GRAY; // Elige el color de fondo deshabilitado

    public static void DeshabilitarComponente(JComponent componente){
        if (componente instanceof JTextComponent) {
            JTextComponent textComponent = (JTextComponent) componente;
            textComponent.setEditable(false);
            textComponent.setDisabledTextColor(disabledTextColor);
        }
        else if (componente instanceof JFormattedTextField) {
            JTextComponent textComponent = (JFormattedTextField) componente;
            textComponent.setEditable(false);
            textComponent.setDisabledTextColor(disabledTextColor);
        }
        else if (componente instanceof JComboBox) {
            JComboBox comboBoxComponent = (JComboBox) componente;
            comboBoxComponent.setEnabled(false);
            //comboBoxComponent.set(false);  
        }
        else if (componente instanceof JTable) {
            JTable jTable = (JTable) componente;
            jTable.setEnabled(false);
            //comboBoxComponent.set(false);  
        }
        
        componente.setBackground(disabledBackgroundColor);
    }
    
    public static void HabilitarComponente(JComponent componente){
        if (componente instanceof JTextComponent) {
            JTextComponent textComponent = (JTextComponent) componente;
            textComponent.setEditable(true);
            textComponent.setDisabledTextColor(null); // Restaura el color predeterminado del texto deshabilitado
        } else if (componente instanceof JFormattedTextField) {
            JTextComponent textComponent = (JFormattedTextField) componente;
            textComponent.setEditable(true);
            textComponent.setDisabledTextColor(null); // Restaura el color predeterminado del texto deshabilitado
        }
        else if (componente instanceof JComboBox) {
            JComboBox comboBoxComponent = (JComboBox) componente;
            comboBoxComponent.setEnabled(true);
        }
        else if (componente instanceof JTable) {
            JTable jTable = (JTable) componente;
            jTable.setEnabled(true);
            //comboBoxComponent.set(false);  
        }
        
        componente.setBackground(null); // Restaura el color de fondo predeterminado
    }
}