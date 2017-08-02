
package eveniment.UI;

import eveniment.DataLayer.Logic.Crypto;
import eveniment.DataLayer.UsersJpaController;
import eveniment.Entities.Enums.AccessLevel;
import eveniment.Entities.Enums.Convertor;
import eveniment.Entities.Enums.RowState;
import eveniment.Entities.Users;
import javax.persistence.EntityManagerFactory;
import javax.swing.JOptionPane;

public class RegisterForm extends javax.swing.JFrame {

    private final EntityManagerFactory _entityManagerFactory;

    public RegisterForm(EntityManagerFactory entityManagerFactory) {
        initComponents();
        setLocationRelativeTo(null);
        _entityManagerFactory = entityManagerFactory;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblFullName = new javax.swing.JLabel();
        txtFullName = new javax.swing.JTextField();
        lblPhone = new javax.swing.JLabel();
        txtPhone = new javax.swing.JTextField();
        lblEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lblPassword = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        lblFill = new javax.swing.JLabel();
        btnOk = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Eveniment");
        getContentPane().setLayout(new java.awt.GridLayout(5, 2, 20, 20));

        lblFullName.setLabelFor(txtFullName);
        lblFullName.setText("Nume complet");
        getContentPane().add(lblFullName);
        getContentPane().add(txtFullName);

        lblPhone.setLabelFor(txtPhone);
        lblPhone.setText("Telefon");
        getContentPane().add(lblPhone);
        getContentPane().add(txtPhone);

        lblEmail.setLabelFor(txtEmail);
        lblEmail.setText("Email");
        getContentPane().add(lblEmail);
        getContentPane().add(txtEmail);

        lblPassword.setLabelFor(txtPassword);
        lblPassword.setText("Password");
        getContentPane().add(lblPassword);
        getContentPane().add(txtPassword);
        getContentPane().add(lblFill);

        btnOk.setText("Ok");
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });
        getContentPane().add(btnOk);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        if(!Validate())
            return;
        
        UsersJpaController userController = new UsersJpaController(_entityManagerFactory);
        
        Users user = new Users();
        
        user.setAccessLevel(Convertor.ToShort(AccessLevel.Regular));
        user.setEmail(txtEmail.getText());
        user.setPhone(txtPhone.getText());
        user.setFullName(txtFullName.getText());
        user.setPassword(Crypto.HashMd5(new String(txtPassword.getPassword())));
        user.setRowState(RowState.Created);
        
        userController.create(user);
        
        setVisible(false);
        
        new LoginForm(_entityManagerFactory).setVisible(true);
        
        dispose();
    }//GEN-LAST:event_btnOkActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOk;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblFill;
    private javax.swing.JLabel lblFullName;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblPhone;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFullName;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtPhone;
    // End of variables declaration//GEN-END:variables

    private boolean Validate() {
        if(txtFullName.getText().trim().equalsIgnoreCase(""))
        {
            JOptionPane.showMessageDialog(this, "Numele nu este completat");
            return false;
        }
        if(txtPhone.getText().trim().equalsIgnoreCase(""))
        {
            JOptionPane.showMessageDialog(this, "Telefonu; nu este completat");
            return false;
        }
        if(txtEmail.getText().trim().equalsIgnoreCase(""))
        {
            JOptionPane.showMessageDialog(this, "Email-ul nu este completat");
            return false;
        }
        if(txtPassword.getPassword().length <= 0)
        {
            JOptionPane.showMessageDialog(this, "Parola nu este completat");
            return false;
        }
        /*if(txtPassword.getPassword().length <= 6)
        {
            JOptionPane.showMessageDialog(this, "Parola trebuie sa aiba cel putin 6 caractere");
            return false;
        }*/
        return true;
    }
}
