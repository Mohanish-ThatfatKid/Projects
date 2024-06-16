package main;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class UniqueString {

	
	    public static void main(String[] args) {
	        
	    	//generating dummy list of 50 candidates
	    	String [][] participants = generateParticipants();
	    	
	    	//fetching 10 random names from the list
	    	String participantNames = generateRandomNames(participants, 10);
	    	
	    	//sorted name string of 10 names
	    	String sortedParticipants = process(participantNames);
	    	System.out.println("Previous names: "+ participantNames);
	    	System.out.println("Sorted names :" + sortedParticipants);
	    	
	    
	    	//specific string for sports candidate
	    	String sportsCandidates = generateSpecificCandidateString(participants, "Sports Candidate");
	    	
	    	//specific String for academic candidate.
	    	String academicCandidates = generateSpecificCandidateString(participants, "Academic Candidate");
	    	
	    	System.out.println("Sports Candidates : " + sportsCandidates);
	    	System.out.println("Academic Candidates : " + academicCandidates);
	    }
	    
	    
	    
	    //generate list of 50 candidates
	    public static String [][] generateParticipants(){
	    	
	    	String[] randomNames = {
	                "Ajay", "Vijay", "Anil", "Sunil", "Rahul", "Rohit", "Amit", "Suresh", "Ramesh", "Mukesh",
	                "Sanjay", "Rajesh", "Deepak", "Manoj", "Ravi", "Kamal", "Prakash", "Arun", "Mahesh", "Naresh",
	                "Santosh", "Pankaj", "Vikas", "Nitin", "Raj", "Akhil", "Ranjan", "Vikram", "Abhishek", "Kiran",
	                "Sachin", "Harish", "Yogesh", "Lokesh", "Aman", "Nilesh", "Tejas", "Naveen", "Dinesh", "Raghu",
	                "Sameer", "Siddharth", "Vivek", "Karthik", "Bharat", "Chetan", "Gaurav", "Hemant", "Jatin", "Kunal"
	            };
	    	
	    	String [][] candidates = new String [50][2];
	    	
	    	for (int i = 0; i < randomNames.length; i++) {
	    		
	    		if (i<23) {
	    			candidates[i][0] = "Sports Candidate";
	    			candidates[i][1] = randomNames[i];
				}
	    		else {
					candidates[i][0] = "Academic Candidate";
					candidates[i][1] = randomNames[i];
				}
			}
	    	
	    	
	    	return candidates;
	    }
	    
	    
	    public static String generateRandomNames(String [][] participants, int count) {
	    	StringBuilder namelist = new StringBuilder(); // string builder is used because we need to append the names and ','
	    	Random random = new Random();
	    	
	    	//used set for 10 unique random numbers
	    	Set<Integer> randomnumbers = new HashSet<>();
	    	
	    	//generating 10 unique random numbers
	    	while (randomnumbers.size() <= count) {
				int num = random.nextInt(49);
				randomnumbers.add(num);
			}
	    	
	    	
	    	int counter = 0;
	    	
	    	//set can not be iterated using index used enhanced for loop to fetch 10 random names based on 10 random numbers
	    	for (int num : randomnumbers) {
				if (counter > 0) {
					namelist.append(",");
				}
				namelist.append(participants[num][1]);
	    		counter++;
			}
	    	
	    	return namelist.toString();
	    }
	    
	    //sorting names Alphabetically
	    public static String process(String names) {
	    	
	    	String [] nameList = names.split(",");
	    	
	    	sortedNames(nameList);
	    	
	    	return String.join(",", nameList);
	    }
	    
	    
	    //sorting functionality
	    public static void sortedNames(String [] names) {
	    	
	    	for (int i = 0; i < names.length-1; i++) {
				for (int j = 0; j < names.length-1-i; j++) {
					if (names[j].compareTo(names[j+1]) > 0) {
						String temp = names[j];
						names[j] = names [j+1];
						names[j+1] = temp;
					}
				}
			}
	    	
	   
	    }
	    
		// method to create specific string for all the candidates
		 public static String generateSpecificCandidateString(String [][] participants, String type) {
	    	
			 StringBuilder candidatenames = new StringBuilder();
			 
			 boolean flag = true;
			
			 for (String[] name : participants) {
				if (name[0].equals(type)) {
					if (!flag) {
						candidatenames.append(",");
					}
					candidatenames.append(name[1]);
					flag = false;
				}
				 
			}
			 
	    	return candidatenames.toString();
	    }
	}
