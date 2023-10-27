package Football;

import java.util.*;
import java.io.*;

class Line {
	String homeTeam;
	String awayTeam;
	int startTime;
	int endTime;

	public Line(String homeTeam, String awayTeam, int startTime, int endTime) {
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public String getHomeTeam() {
 		return this.homeTeam;
	}
	
	public String getAwayTeam() {
		return this.awayTeam;
	}
	
	public int getEndTime() {
		return this.endTime;
	}
	
	public int getStartTime() {
		return this.startTime;
	}

	public static Comparator<Line> matchEndSoon = new Comparator<Line>() {
		public int compare(Line l1, Line l2) {
			int endTime1 = l1.getEndTime();
			int endTime2 = l2.getEndTime();
			
			return endTime1 - endTime2;
		}
	};
}

public class Program {
	
	// First Case prints directly the 1st Match.
	// Otherwise, I used the algorithm that was explained in class.
	// I made a class with the 4 arguments of a Match, 
	// split the line    and added to an ArrayList.
	// Then order it by which Match ends sooner. To check if there's overlap,
	// I check the lastIndexed endedTime in the arrayList with the
	// current Match startTime.
	
	private static void calcMatches(int nMatch, String[] lines) {
		if (nMatch == 1) {
			System.out.println(nMatch);
			System.out.println(lines[0]);
		} else if (nMatch > 1){
			ArrayList<Line> matches = new ArrayList<Line>();
			int j = 0;
			while (nMatch > 0) {
				String[] info = new String[4];
				info = lines[j++].split(" ");
				matches.add(new Line(info[0], info[1], Integer.parseInt(info[2]), Integer.parseInt(info[3])));
				nMatch--;
			}	
			Collections.sort(matches, Line.matchEndSoon);
			
			int lastIndexed = 0;
			for (int i = 1; i < matches.size(); i++) {
				if (matches.get(i).getStartTime() < matches.get(lastIndexed).getEndTime())
					matches.remove(i);
				else 
					lastIndexed = i;	
			}
			
			matches.trimToSize();					// This is just in case, I read that if you remove a tile from an ArrayList, it size does not change. But it does. So I just trim it to resize, just in case.
			nMatch = matches.size();
			System.out.println(nMatch);
			for (int i = 0; i < matches.size(); i++)
				System.out.format("%s %s %06d %06d\n", matches.get(i).getHomeTeam(), matches.get(i).getAwayTeam(),	// As the solution prints Strings for both times and mine print ints removing zeroes.
						matches.get(i).getStartTime(), matches.get(i).getEndTime());								// I adapted with the same format, putting 6 decimals and if there is empty fill with zeroes..
		}
	}
	
	
	// The objective was to read line by line and split the arguments.
	// For the Date and numMatches, I decided to print the Date and save the numMatch.
	// Then save the Matches and process them.
	
	public static void main (String[] args) {
	      try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
	        
	         String linea;
	         while((linea = br.readLine()) != null) {

	        	 String[] info = linea.split(" ", 2);
	        	 int nMatch = -1;
	        	 
	        	 for (String a : info) {
	        		 if (nMatch == -1)
	        			 System.out.print(a + " ");
	        		 else if (nMatch == 0)
	        			 System.out.println(0);
	        		 nMatch = Integer.parseInt(a);
	        	 }
	        		
				String[] lines = new String[nMatch];
	        	for (int i = 0, j = nMatch; j > 0; i++, j--)
	        		lines[i] = br.readLine();
	        	calcMatches(nMatch, lines);
	         }
	      }
	      catch(Exception e){
	         e.printStackTrace();
	      }
	}
}
