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
public class Dealer extends Person{
    // Not done
    
    
    private int total;
    private boolean hitstay; //True when dealer wants to hit and false when he wants to stay
    private boolean lost = false;
    private boolean got21 = false;
    private boolean has1Ace = false;
    private boolean has2Ace = false;
    private boolean has3Ace = false;
    private boolean has4Ace = false;
    
    public void dealPlay(){
        if (total < 17){
            hitstay = true;
        }
        else{
            hitstay = false;
        }
    }
    
    public boolean dealChoice(){
        return hitstay;
    }
    
    
    public int getTotal(){
        return total;
    }
    
    public void setTotal(int set){
        total = set;
    }
    
    public void addToTotal(int newValue){
        total += newValue;
    }
    
    public void giveAce(){
        if(!has1Ace){
            has1Ace = true;
        }
        else if (!has2Ace){
            has2Ace = true;
        }
        else if (!has3Ace){
            has3Ace = true;
        }
        else{
            has4Ace = true;
        }
    }
    
    public boolean hasAce(){
        return has1Ace;
    }
    
    public void takeAce(){
        if(has4Ace){
            has4Ace = false;
        }
        else if (has3Ace){
            has3Ace = false;
        }
        else if (!has2Ace){
            has2Ace = false;
        }
        else{
            has1Ace = false;
        }
        
        total -= 10;
    }
    
    public void hasLost(){
        lost = true;
    }
    
    public boolean checkLoss(){ //returns true if player has lost
        return lost;
    }
    
    public void set21(boolean value){
        got21 = value;
    }
    
    public boolean check21(){
        return got21;
    }
}
