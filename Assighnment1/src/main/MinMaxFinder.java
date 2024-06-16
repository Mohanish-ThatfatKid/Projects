package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MinMaxFinder {

	public static void main(String[] args) {

		Map<String, Integer> wordCount = new HashMap<>(); // map to keep words and counter
		Map<Character, Integer> charCount = new HashMap<>(); // map to keep char and counter
		Map<Integer, Integer> numbCount = new HashMap<Integer, Integer>();

		int maxUsedNumber = Integer.MIN_VALUE;
		int minUsedNumber = Integer.MAX_VALUE;
		

//		If you want to take file path from user use this, this is risky stuff as file path can get wrong when entered manually.
		
//		Scanner sc = new Scanner(System.in);
//		System.out.println("Enter your full path to file: ");
//		String filePath = sc.nextLine();

		String filePath = "E:\\MyProjects\\JAVA\\Strings\\Assighnment1\\src\\main\\any.txt";
		String textContent = readFile(filePath);

		if (textContent == null) {
			System.out.println("error in file.");
			return;
		}

		String[] words = textContent.split("\\W+"); // array for words

		for (char c : textContent.toCharArray()) // converting it into char array
		{
			// bifurcation of chars and digits
			if (Character.isLetter(c)) {
				charCount.put(c, charCount.getOrDefault(c, 0) + 1); // getOrDefault to check if character already there
																	// or not if its there increase value by 1 if not
																	// set 0 and add 1
			} else if (Character.isDigit(c)) {
				int num = Character.getNumericValue(c); // to find the numeric value of digit
//				maxUsedNumber = Math.max(maxUsedNumber, num);
//                minUsedNumber = Math.min(minUsedNumber, num);

				numbCount.put(num, numbCount.getOrDefault(num, 0) + 1);
			}
		}

		for (String word : words) {
			if (!word.isEmpty()) {
				wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
			}
		}

		System.out.println(wordCount);
		System.out.println(charCount);
		// get all the data
		char maxUsedChar = maxCharacter(charCount);
		char minUsedChar = minCharacter(charCount);

		String maxUsedWord = maxWord(wordCount);
		String secondMaxUsedWord = secondMaxWord(wordCount, maxUsedWord);

		String minUsedWord = minWord(wordCount);
		String secondMinUsedWord = secondMinword(wordCount, minUsedWord);

		maxUsedNumber = highestCount(numbCount);
		minUsedNumber = lowestCount(numbCount);

//		System.out.println("Max Used Alphabet\tMax User Number\tMin Used Alphabet\tMin Used Number\tMax Used Word\t2nd Max used Word\tMin Used Word\t2nd Min used Word");
//        System.out.printf("%c\t\t\t%d\t\t\t%c\t\t\t%d\t\t\t%s\t\t\t%s\t\t\t%s%s\n",
//                maxUsedChar, maxUsedNumber, minUsedChar, minUsedNumber, maxUsedWord, secondMaxUsedWord, minUsedWord, secondMinUsedWord);

		System.out.println("Max Used Alphabet: " + maxUsedChar);

		if (maxUsedNumber == Integer.MIN_VALUE) {
			System.out.println("Max Used Number: " + "NA");
		} else {
			System.out.println("Max Used Number: " + maxUsedNumber);
		}

		System.out.println("Min Used Alphabet: " + minUsedChar);

		if (minUsedNumber == Integer.MAX_VALUE) {
			System.out.println("Min Used Number: " + "NA");
		} else {
			System.out.println("Min Used Number: " + minUsedNumber);
		}
		System.out.println("Max Used Word: " + maxUsedWord);
		System.out.println("2nd Max Used word: " + secondMaxUsedWord);
		System.out.println("Min Used word: " + minUsedWord);
		System.out.println("2nd Min Used Word: " + secondMinUsedWord);

	}

	public static String readFile(String path) {
		StringBuilder fileContent = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			String data = "";

			while ((data = br.readLine()) != null) {
				fileContent.append(data).append(" ");
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		// remove any blank space from start or end.
		return fileContent.toString().trim();
	}

	// methods to find min char and words.
	public static char minCharacter(Map<Character, Integer> map) {

		char minChar = ' ';
		int minCharCount = Integer.MAX_VALUE;
		for (Map.Entry<Character, Integer> entry : map.entrySet()) { // iterating over all values

			if (entry.getValue() < minCharCount) {
				minChar = entry.getKey();
				minCharCount = entry.getValue();
			}
		}

		return minChar;
	}

	public static String minWord(Map<String, Integer> map) {

		String minWord = " ";
		int minWordCount = Integer.MAX_VALUE;

		for (Map.Entry<String, Integer> entry : map.entrySet()) {

			if (entry.getValue() < minWordCount) {
				minWord = entry.getKey();
				minWordCount = entry.getValue();
			}

		}

		return minWord;
	}

	public static String secondMinword(Map<String, Integer> map, String minWord) {
		// here we are going to exclude the min word to find second min word

		String secondMin = " ";
		int secondMinCount = Integer.MAX_VALUE;

		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			if (!entry.getKey().equals(minWord) && entry.getValue() < secondMinCount) {
				secondMinCount = entry.getValue();
				secondMin = entry.getKey();
			}

		}

		return secondMin;
	}

//	public static char secondMinCharacter(Map<Character, Integer> map, char minChar) {
//
//		char secondminChar = ' ';
//		int secondMincharCounter = Integer.MAX_VALUE;
//
//		for (Map.Entry<Character, Integer> entry : map.entrySet()) {
//
//			if (!entry.getKey().equals(minChar) && entry.getValue() < secondMincharCounter) {
//
//				secondminChar = entry.getKey();
//				secondMincharCounter = entry.getValue();
//
//			}
//		}
//
//		return secondminChar;
//	}

	// methods to find max words and max chars

	public static char maxCharacter(Map<Character, Integer> map) {

		char maxChar = ' ';
		int maxCharCount = Integer.MIN_VALUE;

		for (Map.Entry<Character, Integer> entry : map.entrySet()) {

			if (entry.getValue() > maxCharCount) {

				maxChar = entry.getKey();
				maxCharCount = entry.getValue();
			}
		}

		return maxChar;

	}

	public static String maxWord(Map<String, Integer> map) {

		String maxWord = "";
		int maxWordCount = Integer.MIN_VALUE;

		for (Map.Entry<String, Integer> entry : map.entrySet()) {

			if (entry.getValue() > maxWordCount) {
				maxWord = entry.getKey();
				maxWordCount = entry.getValue();
			}
		}

		return maxWord;
	}

	public static String secondMaxWord(Map<String, Integer> map, String maxWord) {

		String secondMaxWord = "";
		int secondmaxCount = Integer.MIN_VALUE;

		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			if (!entry.getKey().equals(maxWord) && entry.getValue() > secondmaxCount) {
				secondMaxWord = entry.getKey();
				secondmaxCount = entry.getValue();
			}
		}

		return secondMaxWord;
	}

//	public static char secondMaxCharacter(Map<Character, Integer> map, char maxChar) {
//
//		char secondmaxChar = ' ';
//		int secondmaxCount = Integer.MIN_VALUE;
//
//		for (Map.Entry<Character, Integer> entry : map.entrySet()) {
//
//			if (!entry.getKey().equals(maxChar)) {
//				secondmaxChar = entry.getKey();
//				secondmaxCount = entry.getValue();
//			}
//		}
//
//		return secondmaxChar;
//	}

	public static int highestCount(Map<Integer, Integer> map) {

		int number = 0;
		int highestCount = Integer.MIN_VALUE;

		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {

			if (entry.getValue() > highestCount) {
				number = entry.getKey();
				highestCount = entry.getValue();
			}

		}

		return number;
	}

	public static int lowestCount(Map<Integer, Integer> map) {

		int number = 0;
		int lowestCount = Integer.MAX_VALUE;

		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {

			if (entry.getValue() < lowestCount) {
				number = entry.getKey();
				lowestCount = entry.getValue();
			}

		}

		return number;

	}

}
