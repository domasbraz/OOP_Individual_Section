/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package recyclingservice;

import java.awt.Color;
import javax.swing.table.DefaultTableCellRenderer;
import java.io.*;
import recyclingservice.data.player.PlayerHighScore;
import recyclingservice.data.leaderboard.LeaderBoardData;


/**
 *
 * @author domas
 */
public class GameLeaderboardGUI extends javax.swing.JFrame
{
    private Color bgColor = new Color(30,30,30);
    private Color fgColor = new Color(111,162,202);
    private int playerScore = 0;
    private String percentageScore; 
    private String username = "n/a";
    private String fName = "n/a";
    private String lName = "n/a";
    
    


    /**
     * Creates new form GameLeaderboard
     */
    public GameLeaderboardGUI()
    {
        defaultMethod();
    }
    
    //constructor for passing over the score
    public GameLeaderboardGUI(int score)
    {
        playerScore = score;
        defaultMethod();
    }
    
    //constructor use for when a submission is made
    public GameLeaderboardGUI(boolean submit)
    {
        //this constructor should never have "false" as the parameter
        //decided to include anyways in case I want to do any testing
        if (submit)
        {
            retrieveDataFile();
            defaultMethod();
        }
        else
        {
            new GameLeaderboardGUI(playerScore).setVisible(true);
            dispose();
        }
    }
    
    //this method is called by all the constructors
    private void defaultMethod()
    {
        initComponents();
        setExtraStyling();
        setLeaderBoardValues();
        setDetailTableValues();
    }
    
    //retrieves the users data file and uses it to set the variables
    public void retrieveDataFile()
    {
        File inFilePlayer;
        FileInputStream fStreamPlayer;
        ObjectInputStream oStreamPlayer;
        
        try
        {
            inFilePlayer = new File("src/recyclingservice/data/player/playerHighScore.dat");
            fStreamPlayer = new FileInputStream(inFilePlayer);
            oStreamPlayer = new ObjectInputStream(fStreamPlayer);
            
            PlayerHighScore pi = (PlayerHighScore) oStreamPlayer.readObject();
            
            String[] dataInfo = pi.getPlayerValues();
            
            oStreamPlayer.close();
            
            playerScore = Integer.parseInt(dataInfo[0]);
            
            username = dataInfo[1];
            
            fName = dataInfo[2];
            
            lName = dataInfo[3];

        } 
        catch (IOException e )
        {
            System.out.println(e);
        }
        catch (ClassNotFoundException e)
        {
            System.out.println(e);
        }
    }
    
    //sets the cell values of the leaderboard table
    private void setLeaderBoardValues()
    {
        
        File inFilePlayer, inFileLeaderboard;
        FileInputStream fStreamPlayer, fStreamLeaderboard;
        ObjectInputStream oStreamPlayer, oStreamLeaderboard;  
        

        try 
        {
            //retrieves leaderboard data
            inFileLeaderboard = new File("src/recyclingservice/data/leaderboard/leaderboard.dat");
            fStreamLeaderboard = new FileInputStream(inFileLeaderboard);
            oStreamLeaderboard = new ObjectInputStream(fStreamLeaderboard);

            LeaderBoardData lbi = (LeaderBoardData) oStreamLeaderboard.readObject();
            
            String[][] leaderboardValues = lbi.getLeaderboadValues();
            
            oStreamLeaderboard.close();
            
            inFilePlayer = new File("src/recyclingservice/data/player/playerHighScore.dat");
            fStreamPlayer = new FileInputStream(inFilePlayer);
            oStreamPlayer = new ObjectInputStream(fStreamPlayer);
            
            PlayerHighScore pi = (PlayerHighScore) oStreamPlayer.readObject();
            
            //sets the last value of the leaderboard as the player's value
            leaderboardValues[10] = pi.getPlayerValues();
            
            oStreamPlayer.close();
            
            leaderboardValues = sortTable(leaderboardValues);
            
            
            //inserts data into table
            for (int row = 0; row < 10; row++)
            {
                for (int col = 1; col < 5; col++)
                {
                    tblLeaderboard.setValueAt(leaderboardValues[row][col-1], row, col);
                }
            }
            
            saveLeaderboard(leaderboardValues);
        
        } 
        catch (IOException e1) 
        {
            System.out.println(e1);
        }
        catch (ClassNotFoundException e)
        {
            System.out.println(e); 
        }

        
    }
    
    //takes the values of the table an re-organises them in the event that a player beats someone on the leaderboard
    private String[][] sortTable(String[][] table)
    {
        boolean failState;
        do{
            failState = false;
            
            for (int x = 0; x < table.length - 1 ; x++)
            {
                //compares the the score value of the table current index with the value of the next index (the value below it)
                if (Integer.parseInt(table[x][0]) < Integer.parseInt(table[x + 1][0]))
                {
                    //if the value below is higher, the table values are switched
                    String[] tempval = table[x];

                    table[x] = table[x + 1];

                    table[x + 1] = tempval;

                    failState = true;
                }
            }
        }
        //if a switch occurs, repeat the loop until the table is properlly organised
        while (failState);
        
        //returns the new organised table
        return table;
    }
    
    //save the new leaderboard values so that players that end up on the leaderboard have their scores saved
    //Note: there is a backup to the data files provided in the "data" directory if you wish to reset the leaderboard
    private void saveLeaderboard(String[][] leaderboard)
    {
        File outFile;
        FileOutputStream fStream;
        ObjectOutputStream oStream;
        
        try
        {
            LeaderBoardData lbd = new LeaderBoardData();
            
            outFile = new File("src/recyclingservice/data/leaderboard/leaderboard.dat");
            fStream= new FileOutputStream(outFile);
            oStream = new ObjectOutputStream(fStream);
            
            lbd.setLeaderboadValues(leaderboard);
            
            oStream.writeObject(lbd);
            
            oStream.close();
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
    }
    
    //sets extra styling for tables
    private void setExtraStyling()
    {
        //changes to table headers so that they match the style
        //https://stackoverflow.com/questions/4408644/how-can-i-change-the-font-of-a-jtables-header
        tblLeaderboard.getTableHeader().setFont(new java.awt.Font("Segoe UI", 1, 36));
        tblLeaderboard.getTableHeader().setForeground(fgColor);
        tblResult.getTableHeader().setFont(new java.awt.Font("Segoe UI", 1, 36));
        tblResult.getTableHeader().setForeground(fgColor);
        tblLeaderboard.getTableHeader().setOpaque(true);
        tblLeaderboard.getTableHeader().setBackground(bgColor);

        //couldn't find any other way to change the table header background colour
        //I believe this issue is caused by the default renderer used for the tables
        //https://stackoverflow.com/a/15280574
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();

        headerRenderer.setBackground(new Color(35,35,35));

        for (int i = 0; i < tblLeaderboard.getModel().getColumnCount(); i++) 
        {
            tblLeaderboard.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
        
        for (int i = 0; i < tblResult.getModel().getColumnCount(); i++) 
        {
            tblResult.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
        
        //adjust scroll sensetivity of the scrollPane
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(10);
    }
    
    //sets a simple percentage depending on your score in the results table
    private void setTopPercentage()
    {
        //if players end up on the leaderboard
        if (playerScore > 300)
        {
            String leaderboardScore;
            //loops through the leaderboard
            for (int x = 0; x < 10; x++)
            {
                /*
                "getValueAt" method returns as an Object, typically this would mean that it could be simply parsed as an Integer
                using "(int)" however, in this case, the value stored inside the Object is a String and therfore must be parsed into a
                String before it can be parsed into an Integer
                */
                leaderboardScore = (tblLeaderboard.getValueAt(x, 1)).toString();
                
                //if your score is equal to or higher than the person on the leaderboard, your top score will be from 1-10%
                /*
                Note: this is only accurate if we assume that 100 people submited their scores, setting the value as top 1% would
                likely be more accurate 
                */
                if (playerScore >= Integer.parseInt(leaderboardScore))
                {
                    percentageScore = (x + 1) + "%";
                    break;
                }
            }
        }
        else if (playerScore > 200)
        {
            percentageScore = "20%";
        }
        else if (playerScore > 100)
        {
            percentageScore = "50%";
        }
        else if (playerScore > 50)
        {
            percentageScore = "70%";
        }
        else if (playerScore > 0)
        {
            percentageScore = "90%";
        }
        else
        {
            percentageScore = "100%";
        }
        
        //sets the percentage score in the results table
        tblResult.setValueAt(percentageScore, 0, 0);
    }
    
    //sets values in results table
    private void setDetailTableValues()
    {
        //for percentage score (aka first column)
        setTopPercentage();
        
        tblResult.setValueAt(Integer.toString(playerScore), 0, 1);
        tblResult.setValueAt(username, 0, 2);
        tblResult.setValueAt(fName, 0, 3);
        tblResult.setValueAt(lName, 0, 4);
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
        jScrollPane4 = new javax.swing.JScrollPane();
        tblLeaderboard = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblResult = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnReplay = new javax.swing.JButton();
        btnSubmit = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jScrollPane1.setPreferredSize(new java.awt.Dimension(850, 600));

        jPanel1.setBackground(bgColor);
        jPanel1.setPreferredSize(new java.awt.Dimension(950, 919));

        jScrollPane4.setPreferredSize(new java.awt.Dimension(400, 412));

        tblLeaderboard.setBackground(bgColor);
        tblLeaderboard.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        tblLeaderboard.setForeground(fgColor);
        tblLeaderboard.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {
                {"1.", "0", "x", "y", "z"},
                {"2.", "0", "x", "y", "z"},
                {"3.", "0", "x", "y", "z"},
                {"4.", "0", "x", "y", "z"},
                {"5.", "0", "x", "y", "z"},
                {"6.", "0", "x", "y", "z"},
                {"7.", "0", "x", "y", "z"},
                {"8.", "0", "x", "y", "z"},
                {"9.", "0", "x", "y", "z"},
                {"10.", "0", "x", "y", "z"}
            },
            new String []
            {
                "Top", "Score", "Username", "Name", "Surname"
            }
        ));
        tblLeaderboard.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblLeaderboard.setRowHeight(40);
        tblLeaderboard.setShowGrid(true);
        jScrollPane4.setViewportView(tblLeaderboard);

        jScrollPane2.setMinimumSize(new java.awt.Dimension(16, 200));

        tblResult.setBackground(bgColor);
        tblResult.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        tblResult.setForeground(fgColor);
        tblResult.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {
                {"0%", "0", "x", "y", "z"}
            },
            new String []
            {
                "Top", "Score", "Username", "Name", "Surname"
            }
        ));
        tblResult.setRowHeight(50);
        tblResult.setShowGrid(true);
        jScrollPane2.setViewportView(tblResult);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(fgColor);
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Your Result");

        btnReplay.setBackground(bgColor);
        btnReplay.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnReplay.setForeground(fgColor);
        btnReplay.setText("Retry");
        btnReplay.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnReplayActionPerformed(evt);
            }
        });

        btnSubmit.setBackground(bgColor);
        btnSubmit.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnSubmit.setForeground(fgColor);
        btnSubmit.setText("Submit Score");
        btnSubmit.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnSubmitActionPerformed(evt);
            }
        });

        btnHome.setBackground(bgColor);
        btnHome.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnHome.setForeground(fgColor);
        btnHome.setText("Home");
        btnHome.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnHomeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 852, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 959, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(348, 348, 348)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnReplay, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnReplay, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(113, Short.MAX_VALUE))
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

    private void btnReplayActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnReplayActionPerformed
    {//GEN-HEADEREND:event_btnReplayActionPerformed
        new GameRulesGUI().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnReplayActionPerformed

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnSubmitActionPerformed
    {//GEN-HEADEREND:event_btnSubmitActionPerformed
        new GameSubmitGUI(playerScore).setVisible(true);
        dispose();
    }//GEN-LAST:event_btnSubmitActionPerformed

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnHomeActionPerformed
    {//GEN-HEADEREND:event_btnHomeActionPerformed
        /*new HomePage().setVisible(true);
        dispose();
        */
    }//GEN-LAST:event_btnHomeActionPerformed

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
            java.util.logging.Logger.getLogger(GameLeaderboardGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(GameLeaderboardGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(GameLeaderboardGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(GameLeaderboardGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new GameLeaderboardGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnReplay;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable tblLeaderboard;
    private javax.swing.JTable tblResult;
    // End of variables declaration//GEN-END:variables
}
