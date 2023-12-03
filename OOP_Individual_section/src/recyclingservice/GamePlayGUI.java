/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package recyclingservice;

import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author domas
 */


public class GamePlayGUI extends javax.swing.JFrame
{

    /**
     * Creates new form GamePlayGUI
     */
    public GamePlayGUI()
    {
        initComponents();
        getContentPane().setBackground( new Color(30,30,30) );
        setDefaultList();
        setPrompt();
        startTimer(30);

    }
    
    private int countDown;
    private ArrayList<String[]> prompt = new ArrayList<String[]>();
    private int promptIndex = 0;
    private int score = 0;
    //code for Timer and TimerTask was implemented using the following video as reference:
    //https://www.youtube.com/watch?app=desktop&v=QEF62Fm81h4
    Timer myTimer;
    TimerTask myTask;
    
    //Starts the timer, the parameter sets the duration of timer
    public void startTimer(int duration)
    {
        countDown = duration;
        
        updateCountDown();

        myTimer = new Timer();
        myTask = new TimerTask()
        
        {
            @Override
            public void run()
            {
                /*
                counts the timer down, if the timer goes down to 0 or below
                the timer stops and the user is redirected to another GUI
                */
                countDown--;

                if (countDown < 0)
                {
                    gotoLeaderBoard();
                }
                //if timer value is still positive after time deduction, updates the label
                updateCountDown();
            }
        };
        
        //repeatedly calls the TimerTask at a 1 second interval
        myTimer.scheduleAtFixedRate(myTask, 1000, 1000);
    }
    
    //updates the Timer label (TimeVal)
    public void updateCountDown()
    {
        lblTimeVal.setText(Integer.toString(countDown));
    }
    
    //configures the the images that appear in the game
    public void setDefaultList()
    {
        //creates a default list of images, material type specified at index 1 of each array
        String[][] promptArray = {
            //Note: references can also be found in the text document in the "GameImages" directory
            
            //https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.toppr.com%2Fguides%2Fphysics%2Fatomic-and-molecular-structure%2Fwhat-is-hdpe-plastic%2F&psig=AOvVaw36BTzTkPtj5agj3Jh-pkTY&ust=1701548011972000&source=images&cd=vfe&opi=89978449&ved=0CBAQjRxqFwoTCNjNor2G74IDFQAAAAAdAAAAABAO
            {"hdpeMilk.jpg", "hdpe"},
            //https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.vlmaritime.com%2Fproduct%2Fh0125-hdpe-pipe%2F&psig=AOvVaw1WHM_aTuyRVHbHcovR6_j0&ust=1701545817653000&source=images&cd=vfe&opi=89978449&ved=0CBAQjRxqFwoTCJCT9Kf-7oIDFQAAAAAdAAAAABAD
            {"hdpePipes.jpg", "hdpe"},
            //https://www.google.com/url?sa=i&url=https%3A%2F%2Fhongyanpak.com%2Fpolyethylene-film%2Flow-density-polyethylene-ldpe-bags%2F&psig=AOvVaw08Xx0RFunOeTepthvIeDDl&ust=1701548433698000&source=images&cd=vfe&opi=89978449&ved=0CBAQjRxqFwoTCIi8j4aI74IDFQAAAAAdAAAAABAI
            {"ldpeBags.jpg", "ldpe"},
            //https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.birchplastics.com%2Fplastic-resin%2Fvirgin-plastic-resin%2Fvirgin-low-density-polyethylene-ldpe&psig=AOvVaw08Xx0RFunOeTepthvIeDDl&ust=1701548433698000&source=images&cd=vfe&opi=89978449&ved=0CBAQjRxqFwoTCIi8j4aI74IDFQAAAAAdAAAAABAO
            {"ldpeBags2.jpg", "ldpe"},
            //https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.wipeout.ie%2Fproducts%2Fcling-film-12.html&psig=AOvVaw0OAsb1h7udjbggIVQJ1lUg&ust=1701550086657000&source=images&cd=vfe&opi=89978449&ved=0CBAQjRxqFwoTCLCFlJyO74IDFQAAAAAdAAAAABAH
            {"ldpeClingFilm.jpg", "ldpe"},
            //https://www.roofingsuperstore.co.uk/product/polycarbonate-35mm-clear-cut-length.html
            {"pcSheet.jpg", "pc"},
            //https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.compactor-runi.com%2Frecycling-guides%2Frecycling-pet-bottles&psig=AOvVaw331asc0lzKP8kCqBDDWzvh&ust=1701545621865000&source=images&cd=vfe&opi=89978449&ved=0CBAQjRxqFwoTCLimscb97oIDFQAAAAAdAAAAABAD
            {"petBottles.jpg", "pet"},
            //https://www.google.com/url?sa=i&url=https%3A%2F%2Fcmsplastic.com%2Fwhat-items-can-be-made-from-recycled-polypropylene-pp-plastics%2F&psig=AOvVaw1SLg1QFsoajZDvl1M1Wwox&ust=1701548672723000&source=images&cd=vfe&opi=89978449&ved=0CBAQjRxqFwoTCJCj6fqI74IDFQAAAAAdAAAAABAQ
            {"ppBox.jpg", "pp"},
            //https://www.google.com/url?sa=i&url=https%3A%2F%2Fcmsplastic.com%2Fwhat-items-can-be-made-from-recycled-polypropylene-pp-plastics%2F&psig=AOvVaw1SLg1QFsoajZDvl1M1Wwox&ust=1701548672723000&source=images&cd=vfe&opi=89978449&ved=0CBAQjRxqFwoTCJCj6fqI74IDFQAAAAAdAAAAABAQ
            {"ppCup.jpg", "pp"},
            //https://www.google.com/url?sa=i&url=https%3A%2F%2Fletstalkscience.ca%2Feducational-resources%2Fstem-explained%2Fpolystyrene-pros-cons-chemistry&psig=AOvVaw2FYiMQRKnzj4KzRRIEIx6o&ust=1701549079511000&source=images&cd=vfe&opi=89978449&ved=0CBAQjRxqFwoTCIDCwL6K74IDFQAAAAAdAAAAABAY
            {"psContainer.jpg", "ps"},
            //https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.retlawindustries.com%2FPlasticTypes%2FPolystyrene&psig=AOvVaw3xih96zk88WrmWrRP1K0v0&ust=1701549190638000&source=images&cd=vfe&opi=89978449&ved=0CBAQjRxqFwoTCKCJg-qK74IDFQAAAAAdAAAAABAE
            {"psFork.png", "ps"},
            //https://www.google.com/url?sa=i&url=https%3A%2F%2Fen.wikipedia.org%2Fwiki%2FBlister_pack&psig=AOvVaw1AyQfREu-IKLyX_R3qEeiS&ust=1701550434666000&source=images&cd=vfe&opi=89978449&ved=0CBAQjRxqFwoTCOCfnbuP74IDFQAAAAAdAAAAABAD
            {"pvcBlister.png", "pvc"},
            //https://www.google.com/url?sa=i&url=https%3A%2F%2Fedgebp.co.uk%2Fwhy-is-pvc-so-widely-used-in-the-construction-industry%2F&psig=AOvVaw2Byt-hJn6wqlm7sZBrLvsX&ust=1701548207677000&source=images&cd=vfe&opi=89978449&ved=0CBAQjRxqFwoTCIiQuJyH74IDFQAAAAAdAAAAABAD
            {"pvcPipes.png", "pvc"},
            //https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.indiamart.com%2Fproddetail%2Fpvc-flexible-natural-transparent-sheet-22248200033.html&psig=AOvVaw2OYh-zWEAEj9Qy97uRSyzH&ust=1701548262805000&source=images&cd=vfe&opi=89978449&ved=0CBAQjRxqFwoTCNCLzK-H74IDFQAAAAAdAAAAABAK
            {"pvcSheet.jpg", "pvc"}
        };
        
        //adds each value of the 2d array into an ArrayList, this is important for the next method
        for (String[] x : promptArray)
        {
            prompt.add(x);
        }
        
        randomisePromptList();
    }
    
    //randomizes the default list to make the game more interesting
    public void randomisePromptList()
    {

        //creates two ArrayList identical to "prompt"
        ArrayList<String[]> randomPattern,randomPattern2 = new ArrayList<String[]>();
        randomPattern = randomPattern2 = prompt;
        
        //this array holds a list of 2d arrays
        ArrayList<String[][]> listOfPatterns = new ArrayList<String[][]>();

        //makes two 2d arrays with the same parameters as the ArrayLists
        //more info on line: 140
        String[][] randomPatternNormal = new String[randomPattern.size()][2];
        
        String[][] randomPattern2Normal = new String[randomPattern2.size()][2];
        
        //this fills the 2d ArrayList with 10 2d arrays that hold the default values, each in a random order
        for (int x = 0; x < 10; x++)
        {
            //shuffles the ArrayList in a random order
            //https://www.geeksforgeeks.org/collections-shuffle-method-in-java-with-examples/
            Collections.shuffle(randomPattern2);
            
            //copies the values of the ArrayList into the 2d array
            /*I made this because I couldn't find a way to insert the values of an ArrayList of arrays
            into an ArrayList of 2d arrays (incompatible types)*/
            //https://www.geeksforgeeks.org/arraylist-array-conversion-java-toarray-methods/
            randomPattern2Normal = randomPattern2.toArray(randomPattern2Normal);
            
            //copies the values of the array and inserts them into the ArrayList as a constant
            //without the "copyOf" method, values inside the ArrayList would change as the variable array changes
            //https://www.geeksforgeeks.org/arrays-copyof-in-java-with-examples/
            listOfPatterns.add( Arrays.copyOf(randomPattern2Normal, randomPattern2Normal.length));
        }
        
        //used in the next loop
        boolean match;
        int failSafe = 0;
        
        //the prevoius "for" loop filled an ArrayList with 10 different arrangements of the default values
        /*
        this loop will randomize another arrangement and compare it with the 10 that were just created,
        if it finds more than 2 identical positions between the two arrangements, it will rearange the array again.
        I made this in an attempt to increase randomness and decrease the chance of a pattern emerging
        */
        do 
        {
            int foundMatch = 0;
            match = false;

            //shuffles order of ArrayList ref: line: 137
            Collections.shuffle(randomPattern);
            
            //converts ArrayList into array to avoid incompatable conparison issues ref: line: 148
            randomPatternNormal = randomPattern.toArray(randomPatternNormal);
            
            
            for (String[][] x : listOfPatterns)
            {
                for (int y = 0; y < prompt.size();y++)
                {
                    //for testing purposes
                    //System.out.println(randomPatternNormal[y] + " vs " + x[y]);
                    
                    //compares the value in the 2d array with value taken from the ArrayList
                    //x refers to the 2d array from the ArrayList, y refers to the index of the 2d arrays
                    if (randomPatternNormal[y] == x[y])
                    {
                        foundMatch++;
                    }
                }
                //checks if more than two values are found in the identical places in the 2d arrays
                //Note: adjusting the value can futher increase randomness
                if (foundMatch > 2)
                {
                    match = true;
                }
                //for testing purposes
                //System.out.println(failSafe+ " " + match + " " + foundMatch);
                
                //resets number of matches found so that it doesn't carry over when comparing the next 2d array
                foundMatch = 0;
            }
            
            failSafe++;
        }
        //if more than 2 matches occur in a given 2d array, rearange the ArrayList and try again
        //if the loop is run over 20 times, the loop ends to prevent an infinite loop
        while (match && failSafe < 20);
        
        //for testing purposes
        //System.out.println("failsafe: " + failSafe);
        
        //sets the main list used in the game to the random arrangement that was generated
        prompt = randomPattern;
        
        
    }
    
    //sets the image for the label
    public void setPrompt()
    {
        //https://stackoverflow.com/a/32885963
        lblPrompt.setIcon(new ImageIcon(new ImageIcon("src/recyclingservice/GameImages/" + prompt.get(promptIndex)[0]).getImage().getScaledInstance(lblPrompt.getWidth(), lblPrompt.getHeight(), Image.SCALE_SMOOTH)));
        
        
        //outputs answer in console, for testing purposes 
        //System.out.println(prompt.get(promptIndex)[1]);
    }

    //redirects user to LeaderboardGUI 
    public void gotoLeaderBoard()
    {
        //ends timer, this stops the timer from repeating the TimerTask
        myTimer.cancel();
        new GameLeaderboardGUI(score).setVisible(true);
        dispose();
    }
    
    //update the label for score
    public void updateScore()
    {
        lblScoreVal.setText(Integer.toString(score));
    }
    
    //check if user answer is correct
    public void guessPrompt(String guess)
    {
        //index 1 of array holds answer
        //see for yourself: line: 90
        String answer = prompt.get(promptIndex)[1];
        if (guess.equals(answer))
        {
            //increases score and time remaining if the user guesses correctly
            countDown += 3;
            score += 10;
            updateScore();
        }
        else
        {
            //reduces time remaining if user guesses incorectly
            //this prevents the user abusing the game by continuously pressing the same button
            /*
            If you want to try for yourself: comment out this "else" statement, when you run the game, you can repeatedly
            press the same button (Low-Density Polyethylene and Polyvinyl Chloride have the highest success rate)
            and if you can click fast enough you will simply beat the timer, allowing you to
            easily beat the leaderboard
            */
            countDown -= 3;
            if (countDown < 0)
            {
                countDown = 0;
            }
        }
        updateCountDown();
        updatePromptIndex();
    }
    
    public void updatePromptIndex()
    {
        //checks if index has reached the end of the list
        if (promptIndex == prompt.size() - 1)
        {
            randomisePromptList();
            promptIndex = 0;
        }
        else
        {
            promptIndex++;
        }
        setPrompt();
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

        lblTitle = new javax.swing.JLabel();
        lblTimeVal = new javax.swing.JLabel();
        lblTime = new javax.swing.JLabel();
        lblScore = new javax.swing.JLabel();
        lblScoreVal = new javax.swing.JLabel();
        btnQuit = new javax.swing.JButton();
        lblPrompt = new javax.swing.JLabel();
        btnHDPE = new javax.swing.JButton();
        btnLDPE = new javax.swing.JButton();
        btnPP = new javax.swing.JButton();
        btnPC = new javax.swing.JButton();
        btnPS = new javax.swing.JButton();
        btnPET = new javax.swing.JButton();
        btnPVC = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        setMaximumSize(new java.awt.Dimension(850, 600));
        setMinimumSize(new java.awt.Dimension(850, 600));
        setResizable(false);

        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblTitle.setForeground(new Color(111, 162, 202));
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("Recycling Game");

        lblTimeVal.setFont(new java.awt.Font("Segoe UI", 0, 60)); // NOI18N
        lblTimeVal.setForeground(new Color(111, 162, 202));
        lblTimeVal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTimeVal.setText("0");

        lblTime.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTime.setForeground(new Color(111, 162, 202));
        lblTime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTime.setText("Time");

        lblScore.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblScore.setForeground(new Color(111, 162, 202));
        lblScore.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblScore.setText("Score");

        lblScoreVal.setFont(new java.awt.Font("Segoe UI", 0, 60)); // NOI18N
        lblScoreVal.setForeground(new Color(111, 162, 202));
        lblScoreVal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblScoreVal.setText("0");

        btnQuit.setBackground(new java.awt.Color(30, 30, 30));
        btnQuit.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnQuit.setForeground(new java.awt.Color(255, 0, 51));
        btnQuit.setText("Quit");
        btnQuit.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnQuitActionPerformed(evt);
            }
        });

        lblPrompt.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        lblPrompt.setForeground(new Color(111, 162, 202));
        lblPrompt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        btnHDPE.setBackground(new java.awt.Color(30, 30, 30));
        btnHDPE.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnHDPE.setForeground(new java.awt.Color(111, 162, 202));
        btnHDPE.setText("High-Density Polyethylene");
        btnHDPE.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnHDPEActionPerformed(evt);
            }
        });

        btnLDPE.setBackground(new java.awt.Color(30, 30, 30));
        btnLDPE.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnLDPE.setForeground(new java.awt.Color(111, 162, 202));
        btnLDPE.setText("Low-Density Polyethylene");
        btnLDPE.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnLDPEActionPerformed(evt);
            }
        });

        btnPP.setBackground(new java.awt.Color(30, 30, 30));
        btnPP.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnPP.setForeground(new java.awt.Color(111, 162, 202));
        btnPP.setText("Polypropylene");
        btnPP.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnPPActionPerformed(evt);
            }
        });

        btnPC.setBackground(new java.awt.Color(30, 30, 30));
        btnPC.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnPC.setForeground(new java.awt.Color(111, 162, 202));
        btnPC.setText("Polycarbonate");
        btnPC.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnPCActionPerformed(evt);
            }
        });

        btnPS.setBackground(new java.awt.Color(30, 30, 30));
        btnPS.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnPS.setForeground(new java.awt.Color(111, 162, 202));
        btnPS.setText("Polystyrene");
        btnPS.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnPSActionPerformed(evt);
            }
        });

        btnPET.setBackground(new java.awt.Color(30, 30, 30));
        btnPET.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnPET.setForeground(new java.awt.Color(111, 162, 202));
        btnPET.setText("Polyethylene Terephthalate");
        btnPET.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnPETActionPerformed(evt);
            }
        });

        btnPVC.setBackground(new java.awt.Color(30, 30, 30));
        btnPVC.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnPVC.setForeground(new java.awt.Color(111, 162, 202));
        btnPVC.setText("Polyvinyl Chloride");
        btnPVC.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnPVCActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTitle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblTime, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTimeVal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblScoreVal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblScore, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE))
                .addGap(42, 42, 42)
                .addComponent(lblPrompt, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnPET)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPVC)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLDPE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(btnHDPE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPP)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPS)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPC))
                    .addComponent(btnQuit))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(lblTime, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTimeVal, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblScore)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblScoreVal, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPrompt, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHDPE)
                    .addComponent(btnPC)
                    .addComponent(btnPP)
                    .addComponent(btnPS))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPET)
                    .addComponent(btnLDPE)
                    .addComponent(btnPVC))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(btnQuit)
                .addGap(27, 27, 27))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    
    private void btnQuitActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnQuitActionPerformed
    {//GEN-HEADEREND:event_btnQuitActionPerformed
        gotoLeaderBoard();
    }//GEN-LAST:event_btnQuitActionPerformed

    private void btnLDPEActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnLDPEActionPerformed
    {//GEN-HEADEREND:event_btnLDPEActionPerformed
        guessPrompt("ldpe");
    }//GEN-LAST:event_btnLDPEActionPerformed

    private void btnHDPEActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnHDPEActionPerformed
    {//GEN-HEADEREND:event_btnHDPEActionPerformed
        guessPrompt("hdpe");
    }//GEN-LAST:event_btnHDPEActionPerformed

    private void btnPPActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnPPActionPerformed
    {//GEN-HEADEREND:event_btnPPActionPerformed
        guessPrompt("pp");
    }//GEN-LAST:event_btnPPActionPerformed

    private void btnPSActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnPSActionPerformed
    {//GEN-HEADEREND:event_btnPSActionPerformed
        guessPrompt("ps");
    }//GEN-LAST:event_btnPSActionPerformed

    private void btnPCActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnPCActionPerformed
    {//GEN-HEADEREND:event_btnPCActionPerformed
        guessPrompt("pc");
    }//GEN-LAST:event_btnPCActionPerformed

    private void btnPETActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnPETActionPerformed
    {//GEN-HEADEREND:event_btnPETActionPerformed
        guessPrompt("pet");
    }//GEN-LAST:event_btnPETActionPerformed

    private void btnPVCActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnPVCActionPerformed
    {//GEN-HEADEREND:event_btnPVCActionPerformed
        guessPrompt("pvc");
    }//GEN-LAST:event_btnPVCActionPerformed

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
            java.util.logging.Logger.getLogger(GamePlayGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(GamePlayGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(GamePlayGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(GamePlayGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        
        //d1.getContentPane().setBackground( new Color(1,1,1) );

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                
                new GamePlayGUI().setVisible(true);
                
            }
        });
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHDPE;
    private javax.swing.JButton btnLDPE;
    private javax.swing.JButton btnPC;
    private javax.swing.JButton btnPET;
    private javax.swing.JButton btnPP;
    private javax.swing.JButton btnPS;
    private javax.swing.JButton btnPVC;
    private javax.swing.JButton btnQuit;
    private javax.swing.JLabel lblPrompt;
    private javax.swing.JLabel lblScore;
    private javax.swing.JLabel lblScoreVal;
    private javax.swing.JLabel lblTime;
    private javax.swing.JLabel lblTimeVal;
    private javax.swing.JLabel lblTitle;
    // End of variables declaration//GEN-END:variables
}
