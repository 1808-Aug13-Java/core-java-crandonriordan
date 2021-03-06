package com.revature.eval.java.core;

import java.nio.CharBuffer;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class EvaluationService {

	/**
	 * 1. Without using the StringBuilder or StringBuffer class, write a method that
	 * reverses a String. Example: reverse("example"); -> "elpmaxe"
	 * 
	 * @param string
	 * @return
	 */
	public String reverse(String string) {
		// TODO Write an implementation for this method declaration
		if (string == "") {
			return "";
		}
		
		String newStr = "";
		
		for(int i = string.length() - 1; i >= 0; i--) {
			newStr += String.valueOf(string.charAt(i));
		}
		
		return newStr;
	}

	/**
	 * 2. Convert a phrase to its acronym. Techies love their TLA (Three Letter
	 * Acronyms)! Help generate some jargon by writing a program that converts a
	 * long name like Portable Network Graphics to its acronym (PNG).
	 * 
	 * @param phrase
	 * @return
	 */
	public String acronym(String phrase) {
		String copyOfPhrase = phrase.toUpperCase();
		String[] words = copyOfPhrase.split("[ -]");
		String acronym = "";
		for(String word : words) {
			acronym += String.valueOf(word.charAt(0));
		}
		
		return acronym;
	}

	/**
	 * 3. Determine if a triangle is equilateral, isosceles, or scalene. An
	 * equilateral triangle has all three sides the same length. An isosceles
	 * triangle has at least two sides the same length. (It is sometimes specified
	 * as having exactly two sides the same length, but for the purposes of this
	 * exercise we'll say at least two.) A scalene triangle has all sides of
	 * different lengths.
	 *
	 */
	static class Triangle {
		private double sideOne;
		private double sideTwo;
		private double sideThree;

		public Triangle() {
			super();
		}

		public Triangle(double sideOne, double sideTwo, double sideThree) {
			this();
			this.sideOne = sideOne;
			this.sideTwo = sideTwo;
			this.sideThree = sideThree;
		}

		public double getSideOne() {
			return sideOne;
		}

		public void setSideOne(double sideOne) {
			this.sideOne = sideOne;
		}

		public double getSideTwo() {
			return sideTwo;
		}

		public void setSideTwo(double sideTwo) {
			this.sideTwo = sideTwo;
		}

		public double getSideThree() {
			return sideThree;
		}

		public void setSideThree(double sideThree) {
			this.sideThree = sideThree;
		}

		public boolean isEquilateral() {
			if(Double.compare(sideOne, sideTwo) == 0 && 
					Double.compare(sideOne, sideThree) == 0 &&
					Double.compare(sideTwo, sideThree) == 0
					) {
				return true;
			}
			
			return false;
		}

		public boolean isIsosceles() {
			if(Double.compare(sideOne, sideTwo) == 0) {
				return true;
			} else if (Double.compare(sideTwo, sideThree) == 0) {
				return true;
			} else if (Double.compare(sideOne, sideThree) == 0) {
				return true;
			}
			
			return false;
		}

		public boolean isScalene() {
			if(Double.compare(sideOne, sideTwo) == 0) {
				return false;
			} else if (Double.compare(sideTwo, sideThree) == 0) {
				return false;
			} else if (Double.compare(sideOne, sideThree) == 0) {
				return false;
			}
			
			return true;
		}

	}

	/**
	 * 4. Given a word, compute the scrabble score for that word.
	 * 
	 * --Letter Values-- Letter Value A, E, I, O, U, L, N, R, S, T = 1; D, G = 2; B,
	 * C, M, P = 3; F, H, V, W, Y = 4; K = 5; J, X = 8; Q, Z = 10; Examples
	 * "cabbage" should be scored as worth 14 points:
	 * 
	 * 3 points for C, 1 point for A, twice 3 points for B, twice 2 points for G, 1
	 * point for E And to total:
	 * 
	 * 3 + 2*1 + 2*3 + 2 + 1 = 3 + 2 + 6 + 3 = 5 + 9 = 14
	 * 
	 * @param string
	 * @return
	 */
	public int getScrabbleScore(String string) {
		int totalValue = 0;
		String copyOfString = string.toUpperCase();
		String[] charsInString = copyOfString.split("");
		for(String letter : charsInString) {
			switch(letter) {
				case "A":
				case "E":
				case "I":
				case "O":
				case "U":
				case "L":
				case "N":
				case "R":
				case "S":
				case "T":
					totalValue += 1;
					break;
				case "D":
				case "G":
					totalValue += 2;
					break;
				case "B":
				case "C":
				case "M":
				case "P":
					totalValue += 3;
					break;
				case "F":
				case "H":
				case "V":
				case "W":
				case "Y": 
					totalValue += 4;
					break;
				case "K":
					totalValue += 5;
					break;
				case "J":
				case "X":
					totalValue += 8;
					break;
				case "Q":
				case "Z":
					totalValue += 10;
					break;
				default:
					System.out.println("The programmer left out a letter...");
				
			}
			
			
		}
		return totalValue;
	}

	/**
	 * 5. Clean up user-entered phone numbers so that they can be sent SMS messages.
	 * 
	 * The North American Numbering Plan (NANP) is a telephone numbering system used
	 * by many countries in North America like the United States, Canada or Bermuda.
	 * All NANP-countries share the same international country code: 1.
	 * 
	 * NANP numbers are ten-digit numbers consisting of a three-digit Numbering Plan
	 * Area code, commonly known as area code, followed by a seven-digit local
	 * number. The first three digits of the local number represent the exchange
	 * code, followed by the unique four-digit number which is the subscriber
	 * number.
	 * 
	 * The format is usually represented as
	 * 
	 * 1 (NXX)-NXX-XXXX where N is any digit from 2 through 9 and X is any digit
	 * from 0 through 9.
	 * 
	 * Your task is to clean up differently formatted telephone numbers by removing
	 * punctuation and the country code (1) if present.
	 * 
	 * For example, the inputs
	 * 
	 * +1 (613)-995-0253 613-995-0253 1 613 995 0253 613.995.0253 should all produce
	 * the output
	 * 
	 * 6139950253
	 * 
	 * Note: As this exercise only deals with telephone numbers used in
	 * NANP-countries, only 1 is considered a valid country code.
	 */
	public String cleanPhoneNumber(String string) {
		String copyOfString = string;
		copyOfString = copyOfString.replaceAll("\\s+","");
		copyOfString = copyOfString.replaceAll("\\p{Punct}", "");
		
		if(copyOfString.charAt(0) == '1') {
			copyOfString = copyOfString.substring(1);
		}
		
		for(int i = 0; i < copyOfString.length(); i++) {
			String currentLetter = String.valueOf(copyOfString.charAt(i));
			if(currentLetter.matches("[a-zA-Z]+\\.?")) {
				throw new IllegalArgumentException();
			}
		}
		
		if(copyOfString.length() > 11) {
			throw new IllegalArgumentException();
		}
		
		return copyOfString;
	}

	/**
	 * 6. Given a phrase, count the occurrences of each word in that phrase.
	 * 
	 * For example for the input "olly olly in come free" olly: 2 in: 1 come: 1
	 * free: 1
	 * 
	 * @param string
	 * @return
	 */
	public Map<String, Integer> wordCount(String string) {
HashMap<String, Integer> countOfWords = new HashMap<String, Integer>();
		
		// account for an empty string
		if(string == "") {
			return null;
		}
		
		String copyOfString = string;
		String[] words;
		copyOfString = copyOfString.replaceAll("\\n", "");
		boolean containsCommas = copyOfString.contains(",");
		if(containsCommas) {
			// checking for commas and then removing from them
			// would be best to remove whitespace in each element of words
			// after splitting for an input string such as "one, two"
			words = copyOfString.split(",");
		} else {
			// if we didn't have commas we know the tests
			// are testing for splits at spaces
			words = copyOfString.split(" ");
		}
		
		for(String word : words) {
			if(countOfWords.containsKey(word)) {
				// the following increments the value of Key by one.
				countOfWords.put(word, countOfWords.get(word) + 1);
			
			} else {
				// we will create the initial value of Key(K) to one.
				countOfWords.put(word, 1);
			}
		}
		
		
		return countOfWords;
	}

	/**
	 * 7. Implement a binary search algorithm.
	 * 
	 * Searching a sorted collection is a common task. A dictionary is a sorted list
	 * of word definitions. Given a word, one can find its definition. A telephone
	 * book is a sorted list of people's names, addresses, and telephone numbers.
	 * Knowing someone's name allows one to quickly find their telephone number and
	 * address.
	 * 
	 * If the list to be searched contains more than a few items (a dozen, say) a
	 * binary search will require far fewer comparisons than a linear search, but it
	 * imposes the requirement that the list be sorted.
	 * 
	 * In computer science, a binary search or half-interval search algorithm finds
	 * the position of a specified input value (the search "key") within an array
	 * sorted by key value.
	 * 
	 * In each step, the algorithm compares the search key value with the key value
	 * of the middle element of the array.
	 * 
	 * If the keys match, then a matching element has been found and its index, or
	 * position, is returned.
	 * 
	 * Otherwise, if the search key is less than the middle element's key, then the
	 * algorithm repeats its action on the sub-array to the left of the middle
	 * element or, if the search key is greater, on the sub-array to the right.
	 * 
	 * If the remaining array to be searched is empty, then the key cannot be found
	 * in the array and a special "not found" indication is returned.
	 * 
	 * A binary search halves the number of items to check with each iteration, so
	 * locating an item (or determining its absence) takes logarithmic time. A
	 * binary search is a dichotomic divide and conquer search algorithm.
	 * 
	 */
	static class BinarySearch<T> {
		private List<T> sortedList;
		
		public int indexOf(T t) {
			
			ArrayList<Integer> values = new ArrayList<Integer>();
			
			for(int i = 0; i < sortedList.size(); i++) {
				IntStream stream = CharBuffer.wrap(sortedList.get(i)
						.toString().toCharArray()).chars();
				
				int number = stream.sum();
				
				values.add(number);
			}

			int middleIndex = values.size() % 2 == 0 ?
					(values.size() / 2) + 1   : values.size() / 2;
			int lowIndex = 0;
			int highIndex = values.size();
			
			IntStream tStream = CharBuffer.wrap(t.toString().toCharArray()).chars();
			int valueOfT = tStream.sum();
			int copyOfT = valueOfT;

			boolean found = false;
			
			while(!found) {
				for(int i = lowIndex; i < highIndex; i++) {
					
					if(values.get(middleIndex) == copyOfT) {
						return middleIndex;
					} else if (values.get(middleIndex) < copyOfT) {
						lowIndex = middleIndex;
						middleIndex = (highIndex + lowIndex) / 2;
						break;
					} else if (values.get(middleIndex) > copyOfT) {
						highIndex = middleIndex;
						middleIndex = (highIndex + lowIndex) / 2;
						break;
					}
				}
			}
			return -1;
		}

		public BinarySearch(List<T> sortedList) {
			super();
			this.sortedList = sortedList;
		}

		public List<T> getSortedList() {
			return sortedList;
		}

		public void setSortedList(List<T> sortedList) {
			this.sortedList = sortedList;
		}

	}

	/**
	 * 8. Implement a program that translates from English to Pig Latin.
	 * 
	 * Pig Latin is a made-up children's language that's intended to be confusing.
	 * It obeys a few simple rules (below), but when it's spoken quickly it's really
	 * difficult for non-children (and non-native speakers) to understand.
	 * 
	 * Rule 1: If a word begins with a vowel sound, add an "ay" sound to the end of
	 * the word. Rule 2: If a word begins with a consonant sound, move it to the end
	 * of the word, and then add an "ay" sound to the end of the word. There are a
	 * few more rules for edge cases, and there are regional variants too.
	 * 
	 * See http://en.wikipedia.org/wiki/Pig_latin for more details.
	 * 
	 * @param string
	 * @return
	 */
	
	public String toPigLatin(String string) {
		String[] wordsToConvert = string.toLowerCase().split(" ");
		ArrayList<String> converted = new ArrayList<String>();
		boolean isCluster = false;
		for(String word : wordsToConvert) {
			String translatedWord = "";
			// handle vowels
			switch(word.charAt(0)) {
				case 'a': 
				case 'e':
				case 'i':
				case 'o':
				case 'u':
					translatedWord = word + "ay";
					converted.add(translatedWord);
					continue;
			}
			
			// handle consonant clusters
			String[] clusters = {"sm", "sch", "st",
			                "th", "tr", "gl", "fl", "qu"};
			String twoL = word.substring(0, 2);
			String threeL = word.substring(0, 3);
			for (String cluster : clusters) {
				isCluster = false;
				// looking for a consonant cluster of length 2
				// if so perform the correct operation
				if (cluster.equals(twoL)) {
					translatedWord = word.substring(2) + twoL + "ay";
					converted.add(translatedWord);
					isCluster = true;
					continue;
				} else if (cluster.equals(threeL)) {
					translatedWord = word.substring(3) + threeL + "ay";
					converted.add(translatedWord);
					isCluster = true;
					continue;
				}
			}
			
			if(isCluster) {
				continue;
			}
			
			// handle consonants
			translatedWord = word.substring(1) + String.valueOf(word.charAt(0)) + "ay";
			converted.add(translatedWord);
			
		}
		
		String phrase = "";
		
		for(int i = 0; i < converted.size(); i++) {
			if(wordsToConvert.length == 1) {
				phrase = converted.get(0);
				return phrase;
			}
			// don't add space at end of phrase
			if(i == converted.size() - 1) {
				phrase += converted.get(i);
				break;
			}
			
			phrase += converted.get(i) + " ";
			
		}
		
		return phrase;
	}

	/**
	 * 9. An Armstrong number is a number that is the sum of its own digits each
	 * raised to the power of the number of digits.
	 * 
	 * For example:
	 * 
	 * 9 is an Armstrong number, because 9 = 9^1 = 9 10 is not an Armstrong number,
	 * because 10 != 1^2 + 0^2 = 2 153 is an Armstrong number, because: 153 = 1^3 +
	 * 5^3 + 3^3 = 1 + 125 + 27 = 153 154 is not an Armstrong number, because: 154
	 * != 1^3 + 5^3 + 4^3 = 1 + 125 + 64 = 190 Write some code to determine whether
	 * a number is an Armstrong number.
	 * 
	 * @param input
	 * @return
	 */
	public boolean isArmstrongNumber(int input) {
		// regex doesn't make "" the front of the list;
		String[] digits = String.valueOf(input).split("(?<=.)");
		
		int armStrongTotal = 0;
		
		for(String digit : digits) {
			armStrongTotal += Math.pow(Integer.parseInt(digit), digits.length);
		}
		
		
		if(armStrongTotal == input) {
			
			return true;
		}
		
		return false;
	}

	/**
	 * 10. Compute the prime factors of a given natural number.
	 * 
	 * A prime number is only evenly divisible by itself and 1.
	 * 
	 * Note that 1 is not a prime number.
	 * 
	 * @param l
	 * @return
	 */
	public List<Long> calculatePrimeFactorsOf(long l) {
		// TODO Write an implementation for this method declaration
		List<Long> factors = new ArrayList<Long>();
		long number = l;
		// while it is still even
		while(number % 2 == 0) {
			factors.add( (long) 2 );
			number = number / 2;
		}
		
		// if 2 is no longer the prime factor
		// then the number is now an odd number
		// i is set to three due to 1 not being a prime number
		// we increment our indexer, i, by two to ensure it 
		// stays an odd number
		for(int i = 3; i <= Math.sqrt((double) number); i += 2) {
			
			while(number % i == 0) {
				factors.add( (long) i );
				//
				number = number / i;
			}
			
		}
		
		// if any of the above cases didnt work
		// we know it is an odd number above two that is prime
		// our number must be a prime number that our previous for loop didn't catch
		if(number > 2) {
			factors.add((long) number);
		}
		

		return factors;
		//44
	}

	/**
	 * 11. Create an implementation of the rotational cipher, also sometimes called
	 * the Caesar cipher.
	 * 
	 * The Caesar cipher is a simple shift cipher that relies on transposing all the
	 * letters in the alphabet using an integer key between 0 and 26. Using a key of
	 * 0 or 26 will always yield the same output due to modular arithmetic. The
	 * letter is shifted for as many values as the value of the key.
	 * 
	 * The general notation for rotational ciphers is ROT + <key>. The most commonly
	 * used rotational cipher is ROT13.
	 * 
	 * A ROT13 on the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: nopqrstuvwxyzabcdefghijklm It is
	 * stronger than the Atbash cipher because it has 27 possible keys, and 25
	 * usable keys.
	 * 
	 * Ciphertext is written out in the same formatting as the input including
	 * spaces and punctuation.
	 * 
	 * Examples: ROT5 omg gives trl ROT0 c gives c ROT26 Cool gives Cool ROT13 The
	 * quick brown fox jumps over the lazy dog. gives Gur dhvpx oebja sbk whzcf bire
	 * gur ynml qbt. ROT13 Gur dhvpx oebja sbk whzcf bire gur ynml qbt. gives The
	 * quick brown fox jumps over the lazy dog.
	 */
	static class RotationalCipher {
		private int key;

		public RotationalCipher(int key) {
			super();
			this.key = key;
		}

		public String rotate(String string) {
			// TODO Write an implementation for this method declaration
			char[] characters = string.toCharArray();
			char[] scrambledChars = new char[characters.length];
						
			
			for(int i = 0; i < characters.length; i++) {
				int charCode = (int) characters[i];
				scrambledChars[i] = (char) changeCharCode(charCode, key);
			}
			
			String scrambledPhrase = new String(scrambledChars);
			
			return scrambledPhrase;
		}
		
		private static int changeCharCode(int charCode, int key) {
			int copyChar = charCode;
			
			if (charCode >= 65 && charCode <= 90) {
				// handle capital characters
				for(int i = 1; i <= key; i++) {
					if(copyChar != 90) {
						copyChar++;
					} else if (copyChar == 90) {
						copyChar = 65;
					}
				}
				
			} else if (charCode >= 97 && charCode <= 122) {
				// handle lower case characters
				for(int i = 1; i <= key; i++) {
					if(copyChar != 122) {
						copyChar++;
					} else if (copyChar == 122) {
						copyChar = 97;
					}
				}
			}
			
			return copyChar;
		}

	}

	/**
	 * 12. Given a number n, determine what the nth prime is.
	 * 
	 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see
	 * that the 6th prime is 13.
	 * 
	 * If your language provides methods in the standard library to deal with prime
	 * numbers, pretend they don't exist and implement them yourself.
	 * 
	 * @param i
	 * @return
	 */
	public int calculateNthPrime(int i) {
		if(i == 0) {
			throw new IllegalArgumentException();
		}
		
		
		ArrayList<Integer> primes = new ArrayList<Integer>();
		
		int number = 2;
		while(primes.size() < i) {
			
			if(isPrime(number)) {
				primes.add(number);
			}
			
			// increment index and number if it we didn't find
			number++;
			
		}
		
		return primes.get(primes.size() - 1);
	}

	private boolean isPrime(int num) {
		for(int i = 2; i < num; i++) {
			if(num % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 13 & 14. Create an implementation of the atbash cipher, an ancient encryption
	 * system created in the Middle East.
	 * 
	 * The Atbash cipher is a simple substitution cipher that relies on transposing
	 * all the letters in the alphabet such that the resulting alphabet is
	 * backwards. The first letter is replaced with the last letter, the second with
	 * the second-last, and so on.
	 * 
	 * An Atbash cipher for the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: zyxwvutsrqponmlkjihgfedcba It is a
	 * very weak cipher because it only has one possible key, and it is a simple
	 * monoalphabetic substitution cipher. However, this may not have been an issue
	 * in the cipher's time.
	 * 
	 * Ciphertext is written out in groups of fixed length, the traditional group
	 * size being 5 letters, and punctuation is excluded. This is to make it harder
	 * to guess things based on word boundaries.
	 * 
	 * Examples Encoding test gives gvhg Decoding gvhg gives test Decoding gsvjf
	 * rxpyi ldmul cqfnk hlevi gsvoz abwlt gives thequickbrownfoxjumpsoverthelazydog
	 *
	 */
	static class AtbashCipher {

		/**
		 * Question 13
		 * 
		 * @param string
		 * @return
		 */
		
		
		
		public static final String alphabet = "abcdefghijklmnopqrstuvwxyz";
		public static final String reverseAlphabet = new StringBuilder(alphabet).reverse().toString();

		public static final String[] alphabetArr = alphabet.split("");
		public static final String[] reverseArr = reverseAlphabet.split("");
		
		public static String encode(String string) {
			// TODO Write an implementation for this method declaration
			string = string.replaceAll("\\p{P}", "").replaceAll("\\s+","").toLowerCase();
			
			String[] characters = string.split("");
			String[] scrambledChars = new String[characters.length];
			for(int i = 0; i < characters.length; i++) {
				if(Character.isDigit(characters[i].charAt(0))) {
					// if it's a number we don't want to do anything to scramble it
					scrambledChars[i] = characters[i];
					continue;
					
				}
				int index = Arrays.stream(alphabetArr) 
						.collect(Collectors.toList())
						.indexOf(characters[i]);
				if(index == -1) {
					// not a letter
					continue;
				}
				scrambledChars[i] = reverseArr[index];
			}
			
			String scrambledPhrase = String.join("", scrambledChars);
			// regex that takes every five chars and adds a space at the end
			// unfortunately adds space at end if the last scrambled
			// word is a length of five
			scrambledPhrase = scrambledPhrase.replaceAll("(.{5})", "$1 ");
			// long ternary, might be more readable to do an an if
			scrambledPhrase = scrambledPhrase.charAt(scrambledPhrase.length() - 1) 
					== ' ' ? scrambledPhrase.substring(0, scrambledPhrase.length() - 1) :
						scrambledPhrase;
			return scrambledPhrase;
		}

		/**
		 * Question 14
		 * 
		 * @param string
		 * @return
		 */
		public static String decode(String string) {
			
			String[] scrambledCharacters = string.replaceAll(" ", "").split("");
			String[] characters = new String[scrambledCharacters.length];
			
			for(int i = 0; i < characters.length; i++) {
				// this aggregate function
				// allows us to get the index by arr -> list conversion
				int index = Arrays.stream(alphabetArr) 
						.collect(Collectors.toList())
						.indexOf(scrambledCharacters[i]);
			
				if(Character.isDigit(scrambledCharacters[i].charAt(0))) {
					// if it's a number we don't want to do anything to scramble it
					characters[i] = scrambledCharacters[i];
					continue;
					
				}
				
				if(index == -1) {
					// not a letter
					continue;
				}

				characters[i] = reverseArr[index];
			}

			String deScrambledPhrase = String.join("", characters);
			
			return deScrambledPhrase;
		}
		
		
	}

	/**
	 * 15. The ISBN-10 verification process is used to validate book identification
	 * numbers. These normally contain dashes and look like: 3-598-21508-8
	 * 
	 * ISBN The ISBN-10 format is 9 digits (0 to 9) plus one check character (either
	 * a digit or an X only). In the case the check character is an X, this
	 * represents the value '10'. These may be communicated with or without hyphens,
	 * and can be checked for their validity by the following formula:
	 * 
	 * (x1 * 10 + x2 * 9 + x3 * 8 + x4 * 7 + x5 * 6 + x6 * 5 + x7 * 4 + x8 * 3 + x9
	 * * 2 + x10 * 1) mod 11 == 0 If the result is 0, then it is a valid ISBN-10,
	 * otherwise it is invalid.
	 * 
	 * Example Let's take the ISBN-10 3-598-21508-8. We plug it in to the formula,
	 * and get:
	 * 
	 * (3 * 10 + 5 * 9 + 9 * 8 + 8 * 7 + 2 * 6 + 1 * 5 + 5 * 4 + 0 * 3 + 8 * 2 + 8 *
	 * 1) mod 11 == 0 Since the result is 0, this proves that our ISBN is valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isValidIsbn(String string) {
		// TODO Write an implementation for this method declaration
		char[] digits = string.replaceAll("[a-wyzA-Wyz-]", "").toCharArray();
		if(digits.length != 10) {
			return false;
		}
		
		int multiplier = 10;
		int sum = 0;
		
		for(char digit : digits) {
			
			int number = 0;
			if(multiplier == 1 && digit == 'X') {
				sum += 10;
				continue;
			}
			
			if(Character.isDigit(digit)) {
				number = Integer.parseInt(String.valueOf(digit)) * multiplier;
			} else {
				return false;
			}
			
			
			
			sum += number;
			multiplier--;
		}
		
		if(sum % 11 == 0) {
			return true;
		}
		
		return false;
	}

	/**
	 * 16. Determine if a sentence is a pangram. A pangram (Greek: παν γράμμα, pan
	 * gramma, "every letter") is a sentence using every letter of the alphabet at
	 * least once. The best known English pangram is:
	 * 
	 * The quick brown fox jumps over the lazy dog.
	 * 
	 * The alphabet used consists of ASCII letters a to z, inclusive, and is case
	 * insensitive. Input will not contain non-ASCII symbols.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isPangram(String string) {
		string = string.toLowerCase().replaceAll("[^a-zA-Z]", "");
		
		HashSet<String> uniqueLetters = new HashSet<String>(
				Arrays.asList(string.split("")));
		
		if(uniqueLetters.size() == 26) {
			return true;
		}
		// TODO Write an implementation for this method declaration
		return false;
	}

	/**
	 * 17. Calculate the moment when someone has lived for 10^9 seconds.
	 * 
	 * A gigasecond is 109 (1,000,000,000) seconds.
	 * 
	 * @param given
	 * @return
	 */
	public Temporal getGigasecondDate(Temporal given) {
		// TODO Write an implementation for this method declaration
		int year = given.get(ChronoField.YEAR);
		int month = given.get(ChronoField.MONTH_OF_YEAR);
		int day = given.get(ChronoField.DAY_OF_MONTH);
		int hour = 0;
		int minutes = 0;
		int seconds = 0;
		if(given.isSupported(ChronoField.SECOND_OF_MINUTE)) {
			seconds = given.get(ChronoField.SECOND_OF_MINUTE);
			minutes = given.get(ChronoField.MINUTE_OF_HOUR);
			hour = given.get(ChronoField.HOUR_OF_DAY);
		} else if(given.isSupported(ChronoField.MINUTE_OF_HOUR)) {
			minutes = given.get(ChronoField.MINUTE_OF_HOUR);
			hour = given.get(ChronoField.HOUR_OF_DAY);
		} else if(given.isSupported(ChronoField.HOUR_OF_DAY)) {
			hour = given.get(ChronoField.HOUR_OF_DAY);
		}
		
		LocalDateTime casted = LocalDateTime.of(year, month, day, 
				hour, minutes, seconds);
		
		casted = casted.plus((long) Math.pow(10, 9), ChronoUnit.SECONDS);
		
		return casted;
	}

	/**
	 * 18. Given a number, find the sum of all the unique multiples of particular
	 * numbers up to but not including that number.
	 * 
	 * If we list all the natural numbers below 20 that are multiples of 3 or 5, we
	 * get 3, 5, 6, 9, 10, 12, 15, and 18.
	 * 
	 * The sum of these multiples is 78.
	 * 
	 * @param i
	 * @param set
	 * @return
	 */
	public int getSumOfMultiples(int i, int[] set) {
		// TODO Write an implementation for this method declaration
		HashSet<Integer> uniqueMultiples = new HashSet<Integer>();
		
		for(int j = 1; j < i; j++) {
			for(int numberToCheck : set) {
				if(j % numberToCheck == 0) {
					uniqueMultiples.add(j);
				}
			}
		}
		
		int sum = uniqueMultiples.stream().mapToInt(num -> num).sum();
		
		return sum;
	}

	/**
	 * 19. Given a number determine whether or not it is valid per the Luhn formula.
	 * 
	 * The Luhn algorithm is a simple checksum formula used to validate a variety of
	 * identification numbers, such as credit card numbers and Canadian Social
	 * Insurance Numbers.
	 * 
	 * The task is to check if a given string is valid.
	 * 
	 * Validating a Number Strings of length 1 or less are not valid. Spaces are
	 * allowed in the input, but they should be stripped before checking. All other
	 * non-digit characters are disallowed.
	 * 
	 * Example 1: valid credit card number 1 4539 1488 0343 6467 The first step of
	 * the Luhn algorithm is to double every second digit, starting from the right.
	 * We will be doubling
	 * 
	 * 4_3_ 1_8_ 0_4_ 6_6_ If doubling the number results in a number greater than 9
	 * then subtract 9 from the product. The results of our doubling:
	 * 
	 * 8569 2478 0383 3437 Then sum all of the digits:
	 * 
	 * 8+5+6+9+2+4+7+8+0+3+8+3+3+4+3+7 = 80 If the sum is evenly divisible by 10,
	 * then the number is valid. This number is valid!
	 * 
	 * Example 2: invalid credit card number 1 8273 1232 7352 0569 Double the second
	 * digits, starting from the right
	 * 
	 * 7253 2262 5312 0539 Sum the digits
	 * 
	 * 7+2+5+3+2+2+6+2+5+3+1+2+0+5+3+9 = 57 57 is not evenly divisible by 10, so
	 * this number is not valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isLuhnValid(String string) {
		// TODO Write an implementation for this method declaration
		string = string.replace(" ", "");
		
		char[] digitsChar = string.toCharArray();
		int[] digits = new int[digitsChar.length];
		int[] doubledDigits = new int[digitsChar.length];
		
		for(int i = 0; i < digits.length; i++) {
			// sneaky way to get the numeric value from
			// ASCII codes
			digits[i] = digitsChar[i] - '0';
		}
		
		int checkSum = 0;
		
		Pattern p = Pattern.compile("[^0-9]");
		Matcher m = p.matcher(string);
		if(m.find()) {
			return false;
		}
		
		int checker = digits.length % 2 == 0 ? 3 : 2;
		int checkerSkip = digits.length % 2 == 0 ? 3 : 2;
		int index = 0;
		for(int digit : digits) {
			int number = 0;
			
			if(checker % checkerSkip - 1 == 0) {
				number = digit * 2;
				number = number > 9 ? number - 9 : number;
				doubledDigits[index] = number;
			} else {
				doubledDigits[index] = digit;
			}
			
			checker++;
			index++;
		}
		
		int sum = IntStream.of(doubledDigits).sum();

		if(sum % 10 == 0) {
			return true;
		}
		
		return false;
	}

	/**
	 * 20. Parse and evaluate simple math word problems returning the answer as an
	 * integer.
	 * 
	 * Add two numbers together.
	 * 
	 * What is 5 plus 13?
	 * 
	 * 18
	 * 
	 * Now, perform the other three operations.
	 * 
	 * What is 7 minus 5?
	 * 
	 * 2
	 * 
	 * What is 6 multiplied by 4?
	 * 
	 * 24
	 * 
	 * What is 25 divided by 5?
	 * 
	 * 5
	 * 
	 * @param string
	 * @return
	 */
	public int solveWordProblem(String string) {
		// TODO Write an implementation for this method declaration
		String[] words = string.replaceAll("[.!?\\\\]", "").split(" ");
		String[] operatorStrs = { "plus", "minus", "multiplied", "divided" };
		List<String> operators = Arrays.asList(operatorStrs);
		String operatorPresent = "";
		int firstNum = 0;
		int secondNum = 0;
		int answer = 0;
		for(String word: words) {
			try {
				
				if(operators.contains(word)) {
					operatorPresent = word;
				}
				
				if(firstNum == 0) {
					firstNum = Integer.parseInt(word);
				} else if(secondNum == 0) {
					secondNum = Integer.parseInt(word);
				}
			} catch (Exception e) {
				// do nothing with exception
			}
		}
		
		switch(operatorPresent) {
			case "plus":
				answer = firstNum + secondNum;
				break;
			case "minus":
				answer = firstNum - secondNum;
				break;
			case "multiplied":
				answer = firstNum * secondNum;
				break;
			case "divided":
				answer = firstNum / secondNum;
				break;
		}
		
		
		
		return answer;
	}

}
