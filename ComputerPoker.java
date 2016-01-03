//Emma Wald, Ashley Goldschmid, Alexa Kaufman, Peri Becker, Harrison Sommer

import java.util.Scanner;
public class groupProject {
  public static void main(String[] args) {
    
    String[][] deck = new String[5][2];
    
    for(int x = 0; x < deck.length; x++) {
      for(int i = 0; i < deck[x].length; ) {
        
        String[] card = new String[2];
        int number = (1 + (int)(Math.random() * ((13-1)+1))); //number between 1-13 for card number
        if (number == 1) { //one is ace 
          card[0] = "ace";
        }
        else if (number < 11) { //2-11 is regular numbers
          card[0] = number + "";
        }
        else if (number == 11) { //11 is jack
          card[0] = "jack";
        }
        else if (number == 12) { //12 is queen
          card[0] = "queen";
        }
        else if (number == 13) { //13 is king
          card[0]= "king";
        }
        deck[x][i] = card[0];
        i++;
        
        int suit = (1 + (int)(Math.random() * ((4-1)+1))); //number between one and four for suits
        if (suit == 1) { //random number 1 is hearts
          card[1] = "hearts";
        }
        if (suit == 2) { //random number 2 is diamonds
          card[1] = "diamonds";
        }
        if (suit == 3) { //random number 3 is spades
          card[1] = "spades";
        }
        if (suit == 4) { //random number 4 is clubs
          card[1] = "clubs";
        }
        deck[x][i] = card[1];
        i++;
      }
    }
    
    for (int j = 0; j < deck.length; j++) {
      for(int k = 0; k < deck[j].length; k++) {
        System.out.printf("%4.10s  ", deck[j][k]);
      }
      System.out.println();
    }
    
    int[][] times = ofAKind(deck);
    int[] number = new int[5];
    for (int i = 0; i < times.length; i++) {
      number[i] = times[i][1];
    }
    
    boolean four = false;
    boolean full = false;
    boolean three = false;
    boolean two = false;
    for(int i = 0; i < number.length; i++) {
      if(number[i] == 4) {
        four = true;
        break;
      }
    }
    for(int i = 0; i < number.length; i++) {
      if(number[i] == 3) {
        three = true;
        break;
      }
    }
    for(int i = 0; i < number.length; i++) {
      if(number[i] == 2) {
        two = true;
      }
    }
    if (two && three) {
      full = true;
    } 
    
    
    boolean stay = true;
    int trade = 0;
    if (royalFlush(deck)) {
      stay = true;
    }
    else if(straightFlush(deck)) {
      stay = true;
    }
    else if(four) {
      stay = true;
    }
    else if(full) {
      stay = true;
    }
    else if(three) {
      trade = 2;
    }
    else if(two) {
      trade = 3;
    }
    else{
      trade = 4;
    }
    
    System.out.println();
    System.out.println("Trade " + trade + " cards.");
    System.out.println("New hand is: ");
    
    deck = trade(deck, times, trade);
    for (int j = 0; j < deck.length; j++) {
      for(int k = 0; k < deck[j].length; k++) {
        System.out.printf("%4.10s  ", deck[j][k]);
      }
      System.out.println();
    }
    
  }
  
  public static boolean royalFlush(String[][] deck) {
    String suit = "";
    int x = 0;
    if (deck[0][1].contains("clubs")) {
      suit = "clubs";
    }
    else if (deck[0][1].contains("diamonds")) {
      suit = "diamonds";
    }
    else if (deck[0][1].contains("hearts")) {
      suit = "hearts";
    }
    else {
      suit = "spades";
    }
    int count = 0;
    
    for(x = 0; x < deck.length; x++) {
      for (int i = 0; i < deck[x].length; i++) {
        if (deck[x][i].contains("ace") || deck[x][i].contains("king") || deck[x][i].contains("queen") || deck[x][i].contains("jack") || deck[x][i].contains("10")) {
          if (deck[x][i].contains(suit)) {
            count++;
          }
        }
      }
    }
    boolean i = false;
    if (count == 5) {
      i = true;
    }
    return i;
  }
  
  
  public static boolean straightFlush(String[][] deck) {
    int[][] number = new int[5][2];
    for(int i = 0; i < deck.length; i++) {
      for (int j = 0; j < deck[i].length; j++){
        if (deck[i][j].contains("ace")) {
          number[i][j] = 1;
        }
        else if(deck[i][j].contains("jack")) {
          number[i][j] = 11;
        }
        else if(deck[i][j].contains("queen")) {
          number[i][j] = 12;
        }
        else if(deck[i][j].contains("king")) {
          number[i][j] = 13;
        }
        else if(deck[i][j].contains("hearts")) {
          number[i][j] = 14;
        }
        else if(deck[i][j].contains("diamonds")) {
          number[i][j] = 15;
        }
        else if(deck[i][j].contains("spades")) {
          number[i][j] = 16;
        }
        else if(deck[i][j].contains("clubs")) {
          number[i][j] = 17;
        }
        else{ 
          number[i][j] = Integer.parseInt(deck[i][j]);
        }
      }
    }
    
    int count = 0;
    int num = number[0][0];
    for(int i = 0; i < number.length; i++) {
      for(int j = 0; j < number[i].length; j++) {
        if(number[i][j] == num) {
          count++;
        }
        num++;
      }
    }
    int suitcount = 0;
    int suit = number[0][1];
    for(int i = 0; i < number.length; i++) {
      for(int j = 0; j < number[i].length; j++) {
        if(number[i][j] == suit) {
          suitcount++;
        }
      }
    }
    
    boolean x = false;
    if (count == 5 && suitcount == 5) {
      x = true;
    }
    return x;
    
  }
  
  public static int[][] ofAKind(String[][] deck) {
    int[][] number = new int[5][2];
    for(int i = 0; i < deck.length; i++) {
      for (int j = 0; j < deck[i].length; j++){
        if (deck[i][j].contains("ace")) {
          number[i][j] = 1;
        }
        else if(deck[i][j].contains("jack")) {
          number[i][j] = 11;
        }
        else if(deck[i][j].contains("queen")) {
          number[i][j] = 12;
        }
        else if(deck[i][j].contains("king")) {
          number[i][j] = 13;
        }
        else if(deck[i][j].contains("hearts")) {
          number[i][j] = 14;
        }
        else if(deck[i][j].contains("diamonds")) {
          number[i][j] = 15;
        }
        else if(deck[i][j].contains("spades")) {
          number[i][j] = 16;
        }
        else if(deck[i][j].contains("clubs")) {
          number[i][j] = 17;
        }
        else{ 
          number[i][j] = Integer.parseInt(deck[i][j]);
        }
      }
    }
    
    int num1 = number[0][0];
    int num2 = number[1][0];
    int num3 = number[2][0];
    int num4 = number[3][0];
    int num5 = number[4][0];
    int count1 = 0;
    int count2 = 0;
    int count3 = 0;
    int count4 = 0;
    int count5 = 0;
    for (int i = 0; i < number.length; i++) {
      for(int j = 0; j < number[i].length; j++) {
        if (number[i][j] == num1) {
          count1++;
        }
        if (number[i][j] == num2) {
          count2++;
        }
        if (number[i][j] == num3) {
          count3++;
        }
        if (number[i][j] == num4) {
          count4++;
        }
        if (number[i][j] == num5) {
          count5++;
        }
      }
    }
    
    int[][] times = new int[5][2];
    times[0][0] = num1;
    times[0][1] = count1;
    times[1][0] = num2;
    times[1][1] = count2;
    times[2][0] = num3;
    times[2][1] = count3;
    times[3][0] = num4;
    times[3][1] = count4;
    times[4][0] = num5;
    times[4][1] = count5;
    
    return times;
  }
  
  public static boolean flush(String[][] deck) {
    int[][] number = new int[5][2];
    for(int i = 0; i < deck.length; i++) {
      for (int j = 0; j < deck[i].length; j++){
        if (deck[i][j].contains("ace")) {
          number[i][j] = 1;
        }
        else if(deck[i][j].contains("jack")) {
          number[i][j] = 11;
        }
        else if(deck[i][j].contains("queen")) {
          number[i][j] = 12;
        }
        else if(deck[i][j].contains("king")) {
          number[i][j] = 13;
        }
        else if(deck[i][j].contains("hearts")) {
          number[i][j] = 14;
        }
        else if(deck[i][j].contains("diamonds")) {
          number[i][j] = 15;
        }
        else if(deck[i][j].contains("spades")) {
          number[i][j] = 16;
        }
        else if(deck[i][j].contains("clubs")) {
          number[i][j] = 17;
        }
        else{ 
          number[i][j] = Integer.parseInt(deck[i][j]);
        }
      }
    }
    
    int suitcount = 0;
    int suit = number[0][1];
    for(int i = 0; i < number.length; i++) {
      for(int j = 0; j < number[i].length; j++) {
        if(number[i][j] == suit) {
          suitcount++;
        }
      }
    }
    
    boolean x = false;
    if (suitcount == 5) {
      x = true;
    }
    return x;
    
  }
  
  public static int highCard(int[][] number) {
    int high = 0;
    for(int i = 0; i < number.length; i++) {
      if (high < number[i][0]) {
        high = number[i][0];
      }
    }
    return high;
  }
  
  public static String[][] trade(String[][] deck, int[][] times, int trade) {
    int[][] number = new int[5][2];
    for(int i = 0; i < deck.length; i++) {
      for (int j = 0; j < deck[i].length; j++){
        if (deck[i][j].contains("ace")) {
          number[i][j] = 1;
        }
        else if(deck[i][j].contains("jack")) {
          number[i][j] = 11;
        }
        else if(deck[i][j].contains("queen")) {
          number[i][j] = 12;
        }
        else if(deck[i][j].contains("king")) {
          number[i][j] = 13;
        }
        else if(deck[i][j].contains("hearts")) {
          number[i][j] = 14;
        }
        else if(deck[i][j].contains("diamonds")) {
          number[i][j] = 15;
        }
        else if(deck[i][j].contains("spades")) {
          number[i][j] = 16;
        }
        else if(deck[i][j].contains("clubs")) {
          number[i][j] = 17;
        }
        else{ 
          number[i][j] = Integer.parseInt(deck[i][j]);
        }
      }
    }
      
      int num=0;
      for(int i = 0; i < 5; i++) {
        if(times[i][1] == 3) {
          num = times[i][0];
        }
      }
      if(num != 0) {
      for(int i = 0; i < 5; i++) {
        if(number[i][0] != num) {
          number[i][0] = 1 + (int)(Math.random()*((13-1)+1));
          number[i][1] = 14 + (int)(Math.random()*((17-14)+1));
        }
      }
      }
      int num1 = 0;
      for(int i = 0; i < 5; i++) {
        if(times[i][1] == 2) {
          num1 = times[i][0];
        }
      }
      if (num1 != 0 ) {
      for(int i = 0; i < 5; i++) {
        if(number[i][0] != num1) {
          number[i][0] = 1 + (int)(Math.random()*((13-1)+1));
          number[i][1] = 14 + (int)(Math.random()*((17-14)+1));
        }
      }
      }
      
      int high = highCard(number);
      if (num == 0 && num1 == 0 ) {
      for(int i = 0; i < 5; i++) {
        if(number[i][0] != high) {
          number[i][0] = 1 + (int)(Math.random()*((13-1)+1));
          number[i][1] = 14 + (int)(Math.random()*((17-14)+1));
        }
      }
      }
      
      String[][] newDeck = new String[5][2];
      for(int i = 0; i < number.length; i++) {
      for(int j = 0; j < number[i].length; j++) {
        
        if (number[i][j] == 1) { //one is ace 
          newDeck[i][j] = "ace";
        }
        else if (number[i][j] < 11) { //2-10 is regular numbers
          newDeck[i][j] = number[i][j] + "";
        }
        else if (number[i][j] == 11) { //11 is jack
          newDeck[i][j] = "jack";
        }
        else if (number[i][j] == 12) { //12 is queen
          newDeck[i][j] = "queen";
        }
        else if (number[i][j] == 13) { //13 is king
          newDeck[i][j]= "king";
        }
        
        if (number[i][j] == 14) { //random number 1 is hearts
          newDeck[i][j] = "hearts";
        }
        if (number[i][j] == 15) { //random number 2 is diamonds
          newDeck[i][j] = "diamonds";
        }
        if (number[i][j] == 16) { //random number 3 is spades
          newDeck[i][j] = "spades";
        }
        if (number[i][j] == 17) { //random number 4 is clubs
          newDeck[i][j] = "clubs";
        }
      }
      }
      
      return newDeck;
  }
}