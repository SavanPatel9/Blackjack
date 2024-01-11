/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.lang.*; 
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author savpa
 */
public class BlackJack extends javax.swing.JFrame {

    /**
     * Creates new form BlackJack
     */
    
    private Round round;
    
    public BlackJack() {
        initComponents();
        this.round = new Round();
        beginGame();
        
        
        
    }
    
    
    
    private void beginGame(){
      Deck current = this.round.getDeck();
      Player savan = this.round.getPlayer();
      Random rand = new Random(); 
      // Setting the upper bound to generate the
      // random numbers in specific range
      int upperbound = 52;
      System.out.println("Here");
      
      int i = 0;
      
      while(i < 3){
          if(i % 2 == 0)    // player's hand
          {
            // Generating random values from 0 - 51
            // using nextInt()
            int index = rand.nextInt(upperbound);

            String card = current.getCard(index);
            
            System.out.println("player's " + card);

            while(card.equals("TO"))
            {
                index = rand.nextInt(upperbound);
                card = current.getCard(index);
            }

            current.cardUsed(index);
            
            // implement a method where the image appears in player's panel
            
            updatePlayer(card);
            
            this.PlayerHand.repaint();
            
            savan.addCard(card);
            round.AddToPlayer(card);
            if (savan.getTotal() == 21){
                savan.set21(true);
            }
            
            System.out.println(savan.checkLoss());
            System.out.println(savan.check21());
            System.out.println(savan.getTotal());
            
              
          }
          else      // dealer's one card
          {
            // Generating random values from 0 - 51
            // using nextInt()
            int index = rand.nextInt(upperbound);

            String card = current.getCard(index);
            
            System.out.println("dealer's " + card);

            while(card.equals("TO"))
            {
                index = rand.nextInt(upperbound);
                card = current.getCard(index);
            }
            
            

            current.cardUsed(index);
            
            // implement a method where the image appears in dealer's panel
            
            updateDealer(card);
            
            this.DealerHand.repaint();
            
            round.AddToDealer(card);
            
          }
          
          
          i++;
          
      }
      
      
      // Not done
      
    }
    
    private void updatePlayer(String card){
            File file = new File("./cards/" + card + ".png");
            if(!file.exists()){
                System.out.println("Can't find " + file.getPath());
                return;
            }
            
            System.out.println("Found for Player!");

            ImageIcon icon = new ImageIcon(file.getPath());
            
            icon.setImage(icon.getImage().getScaledInstance(70, 100, Image.SCALE_DEFAULT));
            
            JLabel picture = new JLabel(icon);
            this.PlayerHand.add(picture);
            //Thread.sleep(500);
    }
    
    private void updateDealer(String card){
        File file = new File("./cards/" + card + ".png");
        if(!file.exists()){
            System.out.println("Can't find " + file.getPath());
            return;
        }
        
        System.out.println("Found for Dealer!");

        ImageIcon icon = new ImageIcon(file.getPath());
        
        icon.setImage(icon.getImage().getScaledInstance(70, 100, Image.SCALE_DEFAULT));
        
        JLabel picture = new JLabel(icon);
        this.DealerHand.add(picture);
        //Thread.sleep(500);
        
    }
     
    
    private void onButtonPressed (javax . swing . JButton which)
    {
        // Step 1 - Turn `which ` into a letter
        Random rand = new Random(); 
        int upperbound = 52;
        Deck current = this.round.getDeck();
        Player savan = this.round.getPlayer();
        Dealer nicholas = this.round.getDealer();
      
        String buttonText = which . getText () ;

        if(buttonText.equals("Hit"))
        {
            int index = rand.nextInt(upperbound);

            String card = current.getCard(index);
            
            System.out.println("player's " + card);
            

            while(card.equals("TO"))
            {
                index = rand.nextInt(upperbound);
                card = current.getCard(index);
            }

            current.cardUsed(index);
            
            // implement a method where the image appears in player's panel
            
            updatePlayer(card);
            round.AddToPlayer(card);// Add a card to player's hand
            
            if(savan.getTotal() > 21){
                if(savan.hasAce()){
                    savan.takeAce();
                }
                else{
                    savan.hasLost();
                }  
            }
            else if (savan.getTotal() == 21){
                savan.set21(true);
            }
            
            System.out.println(savan.checkLoss());
            System.out.println(savan.check21());
            System.out.println(savan.getTotal());
        }
        
        if(savan.checkLoss() | buttonText.equals("Stay") | savan.check21())
        {
            
            
            nicholas.dealPlay();
            
            System.out.println(nicholas.getTotal() + "  " + nicholas.dealChoice());
            
            while(nicholas.dealChoice()){
                
            
                
                int index = rand.nextInt(upperbound);

                String card = current.getCard(index);

                System.out.println("dealer's " + card);

                while(card.equals("TO"))
                {
                    index = rand.nextInt(upperbound);
                    card = current.getCard(index);
                    System.out.println("What?");
                }



                current.cardUsed(index);

                // implement a method where the image appears in dealer's panel

                
                
                updateDealer(card);
                      
                    round.AddToDealer(card);// Add a card to player's hand
            
                    if(nicholas.getTotal() > 21){
                        if(nicholas.hasAce()){
                            nicholas.takeAce();
                        }
                        else{
                            nicholas.hasLost();
                        }  
                    }
                    else if (nicholas.getTotal() == 21){
                        nicholas.set21(true);
                    }
                    nicholas.dealPlay();
                    
                    System.out.println(nicholas.getTotal() + "  " + nicholas.dealChoice());
                
                
                
            }
            
            round.changeOver(true);
            
        }
        
        if(round.checkOver() == true){
            if(savan.checkLoss()){
                if(nicholas.checkLoss()){
                    round.setWinner(3);
                }
                else{
                    round.setWinner(2);
                }
            }
            else if(nicholas.checkLoss()){
                if(!savan.checkLoss()){
                    round.setWinner(1);
                }
            }
            else{
                if(savan.getTotal() > nicholas.getTotal()){
                    round.setWinner(1);
                }
                else if(savan.getTotal() == nicholas.getTotal()){
                    round.setWinner(3);
                }
                else{
                    round.setWinner(2);
                }
            }
            
            int output = round.getWinner();
        
            if(output == 1){
                System.out.println("You Win!");
                restart("You Win!");
            }
            else if(output == 2){
                System.out.println("You Lose!");
                restart("You Lose!");
            }
            else if(output == 3){
                System.out.println("You Tie!");
                restart("You Tie!");
            }

            
        }
        

        
        
        

    }
    
    private void restart(String condition){
        int choice = JOptionPane.showConfirmDialog(
                null,
                condition + "    Do you want to restart the game?",
                "Yes or No",
                JOptionPane.YES_NO_OPTION);
        
        if(choice == JOptionPane.YES_OPTION)
        {
            System.out.println("Restarting");
            restartFrame();
        }
        else
        {
            System.out.println("Exiting");
            System.exit(0);
        }
        
    }
    
    private void restartFrame(){
           dispose();
           new BlackJack().setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PlayerHand = new javax.swing.JPanel();
        Hit = new javax.swing.JPanel();
        HitButton = new javax.swing.JButton();
        Stay = new javax.swing.JPanel();
        StayButton = new javax.swing.JButton();
        DealerHand = new javax.swing.JPanel();
        OutputText = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        exit = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        about = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(53, 101, 77));

        PlayerHand.setBackground(new java.awt.Color(53, 101, 77));
        PlayerHand.setPreferredSize(new java.awt.Dimension(479, 150));
        getContentPane().add(PlayerHand, java.awt.BorderLayout.PAGE_END);

        Hit.setBackground(new java.awt.Color(53, 101, 77));
        Hit.setPreferredSize(new java.awt.Dimension(60, 60));
        Hit.setLayout(new java.awt.GridLayout(1, 1, 1, 1));

        HitButton.setBackground(new java.awt.Color(153, 153, 153));
        HitButton.setText("Hit");
        HitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPressed(evt);
            }
        });
        Hit.add(HitButton);

        getContentPane().add(Hit, java.awt.BorderLayout.LINE_START);

        Stay.setBackground(new java.awt.Color(53, 101, 77));
        Stay.setPreferredSize(new java.awt.Dimension(60, 60));
        Stay.setLayout(new java.awt.GridLayout(1, 1, 1, 1));

        StayButton.setBackground(new java.awt.Color(153, 153, 153));
        StayButton.setText("Stay");
        StayButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPressed(evt);
            }
        });
        Stay.add(StayButton);

        getContentPane().add(Stay, java.awt.BorderLayout.LINE_END);

        DealerHand.setBackground(new java.awt.Color(53, 101, 77));
        DealerHand.setPreferredSize(new java.awt.Dimension(481, 150));
        getContentPane().add(DealerHand, java.awt.BorderLayout.PAGE_START);

        OutputText.setBackground(new java.awt.Color(53, 101, 77));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Dealer's Hand");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Player's Hand");

        javax.swing.GroupLayout OutputTextLayout = new javax.swing.GroupLayout(OutputText);
        OutputText.setLayout(OutputTextLayout);
        OutputTextLayout.setHorizontalGroup(
            OutputTextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, OutputTextLayout.createSequentialGroup()
                .addContainerGap(310, Short.MAX_VALUE)
                .addGroup(OutputTextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(281, 281, 281))
        );
        OutputTextLayout.setVerticalGroup(
            OutputTextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, OutputTextLayout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
                .addComponent(jLabel1))
        );

        getContentPane().add(OutputText, java.awt.BorderLayout.CENTER);

        jMenu1.setText("File");

        exit.setText("Exit");
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });
        jMenu1.add(exit);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Help");

        about.setText("About");
        about.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutActionPerformed(evt);
            }
        });
        jMenu2.add(about);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_exitActionPerformed

    private void aboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_aboutActionPerformed

    private void buttonPressed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPressed
        // TODO add your handling code here:
        onButtonPressed((javax . swing . JButton)evt.getSource());
    }//GEN-LAST:event_buttonPressed

    /**
     * @param args the command line arguments
     */
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BlackJack.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BlackJack.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BlackJack.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BlackJack.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BlackJack().setVisible(true);
            }
        });
        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel DealerHand;
    private javax.swing.JPanel Hit;
    private javax.swing.JButton HitButton;
    private javax.swing.JPanel OutputText;
    private javax.swing.JPanel PlayerHand;
    private javax.swing.JPanel Stay;
    private javax.swing.JButton StayButton;
    private javax.swing.JMenuItem about;
    private javax.swing.JMenuItem exit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    // End of variables declaration//GEN-END:variables
}
