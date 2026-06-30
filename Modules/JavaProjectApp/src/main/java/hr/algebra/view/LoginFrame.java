/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package hr.algebra.view;

import hr.algebra.controller.AppSession;
import hr.algebra.model.AppUser;
import hr.algebra.service.AuthService;
import hr.algebra.utilities.MessageUtils;
import java.awt.EventQueue;
import java.util.Arrays;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

// Login and registration screen.
public class LoginFrame extends javax.swing.JFrame {

    private static final String TITLE = "NASA IOTD - Login";

    private final AuthService authService = new AuthService();

    public LoginFrame() {
        initComponents();
        getRootPane().setDefaultButton(btnLogin);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblUsernameCaption = new javax.swing.JLabel();
        tfUsername = new javax.swing.JTextField();
        lblPasswordCaption = new javax.swing.JLabel();
        pfPassword = new javax.swing.JPasswordField();
        lblMessage = new javax.swing.JLabel();
        btnLogin = new javax.swing.JButton();
        btnRegister = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("NASA IOTD - Login");
        setResizable(false);

        lblUsernameCaption.setText("Username:");

        lblPasswordCaption.setText("Password:");

        lblMessage.setForeground(new java.awt.Color(204, 0, 0));
        lblMessage.setText(" ");

        btnLogin.setDefaultCapable(true);
        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        btnRegister.setText("Register");
        btnRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblUsernameCaption)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblPasswordCaption)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnLogin)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRegister)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsernameCaption)
                    .addComponent(tfUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPasswordCaption)
                    .addComponent(pfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMessage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLogin)
                    .addComponent(btnRegister))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        performLogin();
    }//GEN-LAST:event_btnLoginActionPerformed

    private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterActionPerformed
        performRegistration();
    }//GEN-LAST:event_btnRegisterActionPerformed

    // Validates credentials and opens MainFrame for the authenticated user.
    private void performLogin() {
        lblMessage.setText(" ");
        char[] password = pfPassword.getPassword();
        try {
            Optional<AppUser> user = authService.authenticate(tfUsername.getText(), password);
            if (user.isEmpty()) {
                lblMessage.setText("Invalid username or password.");
                return;
            }
            openMainFrameForUser(user.get());
        } catch (Exception ex) {
            Logger.getLogger(LoginFrame.class.getName()).log(Level.SEVERE, null, ex);
            MessageUtils.showErrorMessage(TITLE, "Login failed. Check database connection.");
        } finally {
            clearPasswordField();
        }
    }

    // Registers a new USER account and shows a confirmation message.
    private void performRegistration() {
        lblMessage.setText(" ");
        char[] password = pfPassword.getPassword();
        try {
            authService.registerUser(tfUsername.getText(), password);
            MessageUtils.showInformationMessage(TITLE, "Registration successful. You can log in now.");
            pfPassword.setText("");
        } catch (IllegalArgumentException ex) {
            lblMessage.setText(ex.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(LoginFrame.class.getName()).log(Level.SEVERE, null, ex);
            MessageUtils.showErrorMessage(TITLE, "Registration failed. Check database connection.");
        } finally {
            clearPasswordField();
        }
    }

    // Stores session user and opens MainFrame.
    private void openMainFrameForUser(AppUser user) {
        AppSession.setCurrentUser(user);
        dispose();
        MainFrame.launch(user);//passing user to MainFrame!
    }

    // Clears password chars from memory after login or registration.
    private void clearPasswordField() {
        char[] password = pfPassword.getPassword();
        Arrays.fill(password, '\0');
        pfPassword.setText("");
    }

    // Opens the login frame on the EDT.
    public static void launch() {
        EventQueue.invokeLater(() -> {
            LoginFrame frame = new LoginFrame();
            frame.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnRegister;
    private javax.swing.JLabel lblMessage;
    private javax.swing.JLabel lblPasswordCaption;
    private javax.swing.JLabel lblUsernameCaption;
    private javax.swing.JPasswordField pfPassword;
    private javax.swing.JTextField tfUsername;
    // End of variables declaration//GEN-END:variables
}
