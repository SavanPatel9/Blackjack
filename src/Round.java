/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.lang.*; 

/**
 *
 * @author savpa
 */
public class Round {
    private Deck deck;
    private Player player;
    private Dealer dealer;
    private boolean over = false;
    private int winner; //Tells who won with 1 being player 2 being dealer 3 being tied
    
    public Round(){
        this.deck = new Deck();
        this.player = new Player();
        this.dealer = new Dealer();
    }
    
    // Not done
    public void setWinner(int end){
        winner = end;
    }
    
    public int getWinner(){
        return winner;
    }
    
    public boolean checkOver(){
        return over;
    }
    
    public void changeOver(boolean check){
        over = check;
    }
    
    public Deck getDeck(){
        return this.deck;
    }
    
    public Player getPlayer(){
        return this.player;
    }
    
    public Dealer getDealer(){
        return this.dealer;
    }
    
    public void AddToPlayer(String card){
        if(this.deck.getCardValue(card) == 11){
            if(this.player.getTotal() > 10){
                this.player.addToTotal(1);
            }
            else{
                this.player.giveAce();
                this.player.addToTotal(11);
            }
        }
        else{
            this.player.addToTotal(this.deck.getCardValue(card));
        }
    }
    
    public void AddToDealer(String card){
        if(this.deck.getCardValue(card) == 11){
            if(this.dealer.getTotal() > 10){
                this.dealer.addToTotal(1);
            }
            else{
                this.dealer.giveAce();
                this.dealer.addToTotal(11);
            }
        }
        else{
            this.dealer.addToTotal(this.deck.getCardValue(card));
        }
    }
}
