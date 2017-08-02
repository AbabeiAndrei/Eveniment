
package eveniment.UI;

import eveniment.DataLayer.UsersJpaController;
import eveniment.Entities.Enums.AccessLevel;
import eveniment.Entities.Enums.Convertor;
import eveniment.Entities.Users;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.persistence.EntityManagerFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class LoginForm extends JFrame {        
    
    private JButton btnLogin;
    private JButton btnRegister;
    private JLabel lblMail;
    private JLabel lblPassword;
    private JLabel lblTitle;
    private JTextField txtMail;
    private JPasswordField txtPass;
    private EntityManagerFactory _entityManagerFactory;

    public LoginForm(EntityManagerFactory entityManagerFactory) {
        initUi();
        
        _entityManagerFactory = entityManagerFactory;
    }
       
    private void initUi(){
        setTitle("Eveniment - Login");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(300, 400);
        setLocationRelativeTo(null);
        
        GridBagConstraints gridBagConstraints;

        btnRegister = new JButton();
        btnLogin = new JButton();
        lblPassword = new JLabel();
        txtPass = new JPasswordField();
        lblMail = new JLabel();
        txtMail = new JTextField();
        lblTitle = new JLabel();
        
        Container panel = getContentPane();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        panel.setLayout(new GridBagLayout());

        btnRegister.setText("Inregistrare");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.SOUTH;
        gridBagConstraints.insets = new Insets(0, 0, 0, 17);
        panel.add(btnRegister, gridBagConstraints);

        btnLogin.setText("Ok");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        gridBagConstraints.insets = new Insets(40, 80, 0, 0);
        panel.add(btnLogin, gridBagConstraints);

        lblPassword.setText("Parola");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = GridBagConstraints.LAST_LINE_START;
        gridBagConstraints.insets = new Insets(26, 0, 0, 0);
        panel.add(lblPassword, gridBagConstraints);

        txtPass.setToolTipText("");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.PAGE_END;
        panel.add(txtPass, gridBagConstraints);

        lblMail.setText("Email");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        panel.add(lblMail, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        panel.add(txtMail, gridBagConstraints);

        lblTitle.setFont(new Font("Tahoma", 0, 24));
        lblTitle.setText("Eveniment");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.insets = new Insets(0, 0, 40, 0);
        panel.add(lblTitle, gridBagConstraints);

        btnLogin.addActionListener(this::btnLoginActionPerformed);

        btnRegister.addActionListener(this::btnRegisterActionPerformed);
    }

    private void btnLoginActionPerformed(ActionEvent evt) {
        UsersJpaController userController = new UsersJpaController(_entityManagerFactory);
        
        Users user = userController.login(txtMail.getText(), new String(txtPass.getPassword()));
        
        if(user == null) {
            JOptionPane.showMessageDialog(null, "Incorect user or password", getTitle(), JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        setVisible(false);
        
        if(user.getAccessLevel() == Convertor.ToShort(AccessLevel.Regular))
            new EvenimenteForm(_entityManagerFactory).setVisible(true);
        else
            new ListaEvenimenteForm().setVisible(true);
        
        dispose();
    }

    private void btnRegisterActionPerformed(ActionEvent evt) {
        setVisible(false); //you can't see me!
        RegisterForm regWind = new RegisterForm(_entityManagerFactory);
        
        regWind.setVisible(true);
        
        
    }
}
