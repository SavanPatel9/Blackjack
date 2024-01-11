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
public class Deck {
    private String[] deck;
    public Deck(){
        deck = new String[]{"SA", "S2", "S3", "S4", "S5", "S6", "S7", "S8", "S9", "S10", "SJ", "SQ", "SK", 
                            "HA", "H2", "H3", "H4", "H5", "H6", "H7", "H8", "H9", "H10", "HJ", "HQ", "HK",
                            "DA", "D2", "D3", "D4", "D5", "D6", "D7", "D8", "D9", "D10", "DJ", "DQ", "DK",
                            "CA", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "C10", "CJ", "CQ", "CK"};
    }
    
    // Not Done
    public String getCard (int index){
        String card = this.deck[index];
        return card;
    }
    
    public int getCardValue(String card){  
        String digit = card.substring(1);
        
        if(digit.equals("A")){
            return 11;
        }
        else if(digit.equals("J") | digit.equals("Q") | digit.equals("K")){
            return 10;
        }
        else{
            return Integer.parseInt(digit);
        }
    }
    
    
    public void cardUsed(int index){
        this.deck[index] = "TO";
    }
    
    
}
