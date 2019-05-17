
package Controllers;

import javax.swing.JOptionPane;

/**
 *
 * @author mateus
 */

public class FuncionarioController {
    private float descontoIR;
    private float descontoINSS;
    
    public void descontoIR(float salario) {
        if (salario <= 1903.98) {
            JOptionPane.showMessageDialog(null, "Não precisará descontar nada do seu salário!");
        } else if (salario >= 1903.99 && salario <= 2826.65) {
            descontoIR = (float) (salario * 0.075);
            JOptionPane.showMessageDialog(null, "Você precisará descontar: R$ " + descontoIR);
        } else if (salario >= 2826.66 && salario <= 3751.05) {
            descontoIR = (float) (salario * 0.15);
            JOptionPane.showMessageDialog(null, "Você precisará descontar: R$ " + descontoIR);
        } else if (salario >= 3751.06 && salario <= 4664.68) {
            descontoIR = (float) (salario * 0.225);
            JOptionPane.showMessageDialog(null, "Você precisará descontar: R$ " + descontoIR);
        } else if (salario > 4664.68) {
            descontoIR = (float) (salario * 0.275);
            JOptionPane.showMessageDialog(null, "Você precisará descontar: R$ " + descontoIR);
        }
    }
    
    public void descontoINSS(float salario) {
        if (salario <= 1751.81) {
            descontoINSS = (float) (salario * 0.08);
            JOptionPane.showMessageDialog(null, "Você precisará descontar: R$ " + descontoINSS);
        } else if (salario >= 1751.82 && salario <= 2919.72) {
            descontoINSS = (float) (salario * 0.09);
            JOptionPane.showMessageDialog(null, "Você precisará descontar: R$ " + descontoINSS);
        } else if (salario >= 2919.73 && salario <= 5839.45) {
            descontoINSS = (float) (salario * 0.11);
            JOptionPane.showMessageDialog(null, "Você precisará descontar: R$ " + descontoINSS);
        } 
    }
}
