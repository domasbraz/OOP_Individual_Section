/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package recyclingservice;

import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.io.*;
import recyclingservice.data.player.PlayerHighScore;


/**
 *
 * @author domas
 */
public class GameSubmitGUI extends javax.swing.JFrame
{

    private String errorMsg;
    private int score = 0;
    private String playerUsername, playerFirstName, playerLastName;
    /**
     * Creates new form GameSubmitGUI
     */
    
    public GameSubmitGUI()
    {
        defaultMethod();
    }
    
    //passes on player's score value when constructer is called
    public GameSubmitGUI(int score)
    {
        defaultMethod();
        this.score = score;
        lblScoreVal.setText(Integer.toString(this.score));
    }
    
    //method called by all constructers
    public void defaultMethod()
    {
        initComponents();
        getContentPane().setBackground( new Color(30,30,30) );
        
        //adjust scroll sensetivity of the scrollPane
        //https://stackoverflow.com/questions/5583495/how-do-i-speed-up-the-scroll-speed-in-a-jscrollpane-when-using-the-mouse-wheel
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(10);
    }
    
    //sets variables and makes sure all fields are valid before submitting
    public boolean submitScore()
    {
        //resets error message to prevent same messages from appearing each time the error message is called
        errorMsg = "";
        playerUsername = tfUsername.getText();
        playerFirstName = tfFName.getText();
        playerLastName = tfLName.getText();
        
        //checks if checkbox is checked
        if (cbHideName.isSelected())
        {
            
            //sets names as anonymous
            playerFirstName = playerLastName = "Anonymous";
            
            //only need to check if username is valid
            //Note: the "else" statement is not required as the names would count as valid either way if tested
            if (checkValidInput(playerUsername, "Username"))
            {
                return true;
            }
            else
            {
                //Note: error message is set in "checkValidInput" method
                printError();
                return false;
            }
        }
        else
        {
            //checks if fields are valid
            boolean check1 = checkValidInput(playerFirstName, "First Name");
            boolean check2 = checkValidInput(playerLastName, "Last Name"); 
            boolean check3 = checkValidInput(playerUsername, "Username");       
            if (check1 && check2 && check3)
            {
                return true;
            }
            else
            {
                printError();
                return false;
            }
        }
    }
    
    //takes the field value as "input" and field as "type", checks if the values are valid
    public boolean checkValidInput(String input, String type)
    {
        if (type.equals("First Name") || type.equals("Last Name"))
        {
            //ensure name length can be between 0 and 20 characters
            if (input.length() > 0 && input.length() < 21)
            {
                //ensures only alphabetic characters are present in the field
                //removes all alphabetical characters from the input, if the input is empty, then it is valid
                if (input.replaceAll("[A-Za-z]", "").length() < 1)
                {
                    return true;
                }
                else
                {
                    //sets error message
                    errorMsg += type + " must only contain Alphabetical characters!\r\n";
                    return false;
                }
            }
            else
            {
                //sets error message
                errorMsg += type + " must be between 1 and 20 characters long!\r\n";
                return false;
            }
        }
        else
        {
            //if the field is not a name, in this case, username
            //length set as 3-16 characters
            if (input.length() > 2 && input.length() < 17)
            {
                return true;
            }
            else
            {
                errorMsg += type +" must be between 3 and 16 characters long!";
                return false;
            }
        }
    }
    
    //print error message via JOptionPane
    public void printError()
    {
        new JOptionPane().showMessageDialog(null,errorMsg);
    }
    
    //save the information provided to the player data file
    public void saveScore()
    {
        File outFile;
        FileOutputStream fStream;
        ObjectOutputStream oStream;
        PlayerHighScore pi = new PlayerHighScore();
        
        try
        {
            outFile = new File("src/recyclingservice/data/player/playerHighScore.dat");
            fStream = new FileOutputStream(outFile);
            oStream = new ObjectOutputStream(fStream);
            
            String[] Score = {Integer.toString(score), playerUsername, playerFirstName, playerLastName};
            pi.setPlayerValues(Score);
            
            oStream.writeObject(pi);
            
            oStream.close();
        } 
        catch (IOException e)
        {
            System.out.println(e);
        }
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        lblFinalScore = new javax.swing.JLabel();
        lblScoreVal = new javax.swing.JLabel();
        lblLName = new javax.swing.JLabel();
        tfFName = new javax.swing.JTextField();
        lblFName = new javax.swing.JLabel();
        tfLName = new javax.swing.JTextField();
        tfUsername = new javax.swing.JTextField();
        lblUsername = new javax.swing.JLabel();
        cbHideName = new javax.swing.JCheckBox();
        btnSubmit = new javax.swing.JButton();
        btnRetry = new javax.swing.JButton();
        btnLeaderboard = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 153, 255));
        setForeground(new java.awt.Color(0, 0, 0));
        setMinimumSize(new java.awt.Dimension(850, 600));
        setResizable(false);

        jScrollPane1.setBorder(null);
        jScrollPane1.setForeground(new java.awt.Color(153, 153, 255));
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(850, 600));

        jPanel1.setBackground(new Color(30,30,30));
        jPanel1.setForeground(new Color(111,162,202)
        );

        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblTitle.setForeground(new Color(111, 162, 202));
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("Submit Your Score");

        lblFinalScore.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblFinalScore.setForeground(new Color(111, 162, 202));
        lblFinalScore.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblFinalScore.setText("Final Score:");

        lblScoreVal.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblScoreVal.setForeground(new Color(111, 162, 202));
        lblScoreVal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblScoreVal.setText("0");

        lblLName.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblLName.setForeground(new Color(111, 162, 202));
        lblLName.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblLName.setText("Last Name:");

        tfFName.setBackground(new java.awt.Color(51, 51, 51));
        tfFName.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        tfFName.setForeground(new Color(111, 162, 202));
        tfFName.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tfFName.setCaretColor(new java.awt.Color(204, 204, 204));
        tfFName.setSelectedTextColor(new java.awt.Color(204, 204, 204));

        lblFName.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblFName.setForeground(new Color(111, 162, 202));
        lblFName.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblFName.setText("First Name:");

        tfLName.setBackground(new java.awt.Color(51, 51, 51));
        tfLName.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        tfLName.setForeground(new Color(111, 162, 202));
        tfLName.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tfLName.setCaretColor(new java.awt.Color(204, 204, 204));
        tfLName.setSelectedTextColor(new java.awt.Color(204, 204, 204));

        tfUsername.setBackground(new java.awt.Color(51, 51, 51));
        tfUsername.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        tfUsername.setForeground(new Color(111, 162, 202));
        tfUsername.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tfUsername.setCaretColor(new java.awt.Color(204, 204, 204));
        tfUsername.setMinimumSize(new java.awt.Dimension(200, 36));
        tfUsername.setSelectedTextColor(new java.awt.Color(204, 204, 204));

        lblUsername.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblUsername.setForeground(new Color(111, 162, 202));
        lblUsername.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblUsername.setText("Username:");

        cbHideName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cbHideName.setForeground(new Color(111,162,202));
        cbHideName.setText("Hide Name");
        cbHideName.setToolTipText("Hides your name in the scoreboard");
        cbHideName.setContentAreaFilled(false);
        cbHideName.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                cbHideNameActionPerformed(evt);
            }
        });

        btnSubmit.setBackground(new Color(30,30,30));
        btnSubmit.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        btnSubmit.setForeground(new Color(111,162,202)
        );
        btnSubmit.setText("Submit");
        btnSubmit.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnSubmitActionPerformed(evt);
            }
        });

        btnRetry.setBackground(new Color(30,30,30));
        btnRetry.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        btnRetry.setForeground(new Color(111,162,202)
        );
        btnRetry.setText("Retry");
        btnRetry.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnRetryActionPerformed(evt);
            }
        });

        btnLeaderboard.setBackground(new Color(30,30,30));
        btnLeaderboard.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        btnLeaderboard.setForeground(new Color(111,162,202)
        );
        btnLeaderboard.setText("View Leaderboard");
        btnLeaderboard.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnLeaderboardActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblFinalScore)
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblScoreVal, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(tfFName, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbHideName, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tfLName, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(109, 109, 109))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLName, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFName, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(517, 517, 517))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 850, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 34, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(265, 265, 265)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnRetry, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLeaderboard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSubmit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFinalScore, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblScoreVal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbHideName)
                    .addComponent(lblFName, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfFName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLName, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfLName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSubmit)
                .addGap(18, 18, 18)
                .addComponent(btnRetry)
                .addGap(18, 18, 18)
                .addComponent(btnLeaderboard)
                .addGap(147, 147, 147))
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void btnRetryActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnRetryActionPerformed
    {//GEN-HEADEREND:event_btnRetryActionPerformed
        new GameRulesGUI().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnRetryActionPerformed

    private void btnLeaderboardActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnLeaderboardActionPerformed
    {//GEN-HEADEREND:event_btnLeaderboardActionPerformed
        new GameLeaderboardGUI(score).setVisible(true);
        dispose();
    }//GEN-LAST:event_btnLeaderboardActionPerformed

    private void cbHideNameActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_cbHideNameActionPerformed
    {//GEN-HEADEREND:event_cbHideNameActionPerformed
        
    }//GEN-LAST:event_cbHideNameActionPerformed

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnSubmitActionPerformed
    {//GEN-HEADEREND:event_btnSubmitActionPerformed
        //checks if values are valid before submitting
        if (submitScore())
        {
            saveScore();
            new GameLeaderboardGUI(true).setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_btnSubmitActionPerformed

    /**
     * @param args the command line arguments
     */
    
    
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(GameSubmitGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(GameSubmitGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(GameSubmitGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(GameSubmitGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        
        

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new GameSubmitGUI().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLeaderboard;
    private javax.swing.JButton btnRetry;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JCheckBox cbHideName;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFName;
    private javax.swing.JLabel lblFinalScore;
    private javax.swing.JLabel lblLName;
    private javax.swing.JLabel lblScoreVal;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JTextField tfFName;
    private javax.swing.JTextField tfLName;
    private javax.swing.JTextField tfUsername;
    // End of variables declaration//GEN-END:variables


}
