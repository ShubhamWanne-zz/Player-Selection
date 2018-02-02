/* 
   You can use System.out.println for debugging purposes, e.g.
   System.out.println("this is a debug message");
   
  
*/

package com.psl;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Client {
	static boolean isBoundary(int run){
		if(run == 4 || run == 6)
			return true;
		return false;
	}
	static boolean isRunEven(int run){
		if(run%2==0)
			return true;
		return false;
	}
	static boolean isWicket(int run){
		if(run==7)
			return true;
		return false;
	}
	static boolean isWideFour(int run){
		if(run==8)
			return true;
		return false;
	}
	public static int findPlayer(int n, int delv, int[] runsScored){
		/* Write your code here
		   Do not write System.out.println here or any other printing statements
	    Your code may get 0 marks if you do not follow above instruction. 
		*/

		int player_index=0;
		int over_index=0;
		int ball_index=0;
		int strike_index=0;
		String current_strike;
		
		ArrayList<String>player_list= new ArrayList<>();
		player_list.add("Kuldeep");
		player_list.add("Chandra");
		current_strike = player_list.get(strike_index);
		
		Map<String,Integer> balls_faced = new TreeMap<>();
		balls_faced.put("Kuldeep", 0);
		balls_faced.put("Chandra", 0);
	
		Map<String,Integer> run_scored_from_boundaries = new TreeMap<>();
		run_scored_from_boundaries.put("Kuldeep", 0);
		run_scored_from_boundaries.put("Chandra", 0);
		
		Map<String,Integer> total_run_scored = new TreeMap<>();
		total_run_scored.put("Kuldeep", 0);
		total_run_scored.put("Chandra", 0);
		
		for(int run : runsScored){
			System.out.println("Current Strike = "+current_strike);
			System.out.println("Runs Scored = "+run);
			ball_index++;
			System.out.println("Current Ball : "+ball_index);
			if(ball_index%6==0){
				System.out.println("Over Completed");
				if(strike_index==0)
					strike_index = 1;
				else
					strike_index = 0;
				current_strike = player_list.get(strike_index);
				over_index += 1;
				ball_index = 0;
			}
			if(isWicket(run)){
				System.out.println("Oops "+current_strike+" gone for "+total_run_scored.get(current_strike));
				player_index++;
				player_list.remove(current_strike);
				current_strike = "X_"+player_index;
				player_list.add(current_strike);
				balls_faced.put(current_strike,0);
				total_run_scored.put(current_strike,0);
				run_scored_from_boundaries.put(current_strike, 0);
			}else if(isWideFour(run)){
				ball_index-- ;
			}else if(isBoundary(run)){
				System.out.println(current_strike+" scored a boundary ("+run+")");
				balls_faced.put(current_strike,balls_faced.get(current_strike)+1);
				run_scored_from_boundaries.put(current_strike, run_scored_from_boundaries.get(current_strike)+run);
				total_run_scored.put(current_strike,total_run_scored.get(current_strike)+run);
				System.out.println("Summary\n"+current_strike+"\t"+balls_faced.get(current_strike)+"\t"+run_scored_from_boundaries.get(current_strike)+"\t"+total_run_scored.get(current_strike));
			}else if(isRunEven(run)){
				balls_faced.put(current_strike,balls_faced.get(current_strike)+1);
				total_run_scored.put(current_strike,total_run_scored.get(current_strike)+run);
				System.out.println("Summary\n"+current_strike+"\t"+balls_faced.get(current_strike)+"\t"+run_scored_from_boundaries.get(current_strike)+"\t"+total_run_scored.get(current_strike));
			}else{
				balls_faced.put(current_strike,balls_faced.get(current_strike)+1);
				total_run_scored.put(current_strike,total_run_scored.get(current_strike)+run);
				System.out.println("Summary\n"+current_strike+"\t"+balls_faced.get(current_strike)+"\t"+run_scored_from_boundaries.get(current_strike)+"\t"+total_run_scored.get(current_strike));
				if(strike_index==0)
					strike_index = 1;
				else
					strike_index = 0;
				current_strike = player_list.get(strike_index);
			}
			System.out.println("---------------------------------------------------------------------------------------------");
		}
		
		System.out.println("Ball Faced : "+balls_faced);
		System.out.println("Total Run : "+total_run_scored);
		System.out.println("Runs scored from boundaries : "+run_scored_from_boundaries);
		
		int ratings_kuldeep = run_scored_from_boundaries.get("Kuldeep")/balls_faced.get("Kuldeep");
		int ratings_chandra = run_scored_from_boundaries.get("Chandra")/balls_faced.get("Chandra");
		
		if(ratings_chandra == ratings_kuldeep){
			if(total_run_scored.get("Kuldeep") == total_run_scored.get("Chandra"))
				return 2;
			else if(total_run_scored.get("Kuldeep") > total_run_scored.get("Chandra"))
				return 1;
			else 
				return 0;
		}else{
			if(ratings_kuldeep > ratings_chandra)
				return 1;
			else 
				return 0;
		}
	}
	public static void main(String args[])
	{
	    /*
	        You can use Console input as well with the help of Scanner or BufferedReader classes.
		Please provide input data in Input box on right side of window.
	    */
       
		/*int n=1, delv=6;       
       int runsScored[]={1, 4, 4, 1, 6, 4};
       */
       /*int n=1, delv=6;       
       int runsScored[]={4, 4, 3, 2, 4, 4};
       */
		
		int n=2, delv=13;       
	    int runsScored[]={1, 8, 1, 6, 1, 6, 1, 7, 0, 1, 7, 0, 0};
	      
		/*
		
		int n=2, delv=12;       
	    int runsScored[]={1,4,6,2,3,2,2,6,3,6,4,2};
	      *//*
		int n=4, delv=24;       
	       int runsScored[]={4,4,3,6,6,6,6,6,4,6,4,6,4,2,4,6,6,4,3,3,6,6,4,4};
	       */
	   System.out.println(findPlayer(n,delv,runsScored));
	}

}

