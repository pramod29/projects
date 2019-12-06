package project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class MultiDivAdd {
	String pth; // To store multiplication
	String srctime; // To store division
	static String Filedata1; // To store addition
	String rFiles;
	String rankingTme;
	static String Filedata2;

	public MultiDivAdd(String m, String d, String r, String rf, String rt,String ft) {
		pth = m;
		srctime = d;
		Filedata1 = r;
		rFiles = rf;
		rankingTme = rt;
		Filedata2= ft;
	}

	public MultiDivAdd() {
		// TODO Auto-generated constructor stub
	}
}

public class Searcheng extends MultiDivAdd {

	Searcheng() {
		super();
		// TODO Auto-generated constructor stub
	}

//	public Searcheng() {
//		// TODO Auto-generated constructor stub
//	}

	static ArrayList<String> arrayL = new ArrayList<String>();
	static Hashtable<String, Integer> hashTable = new Hashtable<String, Integer>();
	static Scanner sc = new Scanner(System.in);

	// get occurance and position of word
	public int searchWord(File filePath, String s1) throws IOException {
		int counter = 0;
		int i = 0;
		String data = "";
		try {
			BufferedReader bufferReader = new BufferedReader(new FileReader(filePath));
			String line = null;

			while ((line = bufferReader.readLine()) != null) {

				data = data + line;

			}
			bufferReader.close();

		} catch (NullPointerException e) {
			e.printStackTrace();
		}

		String txt = data;
		//InvertedIndex offset1a = new InvertedIndex(s1);
		
		  BoyerMoore offset1 = new BoyerMoore(s1);
		  String name = null;
		  int offset = 0;
		  
		  for (int loc = 0; loc <= txt.length(); loc += offset + s1.length()) {
		  
		  offset = offset1.search(s1, txt.substring(loc));
		  //System.out.println("\n");
		  if ((offset + loc) < txt.length()) { 
			  counter++; 
			  //System.out.print(s1 +" is at position " + (offset + loc)); //printing position of word 
			  name= s1 + " is at position " + (offset + loc); 
			  System.out.println(name);
			  //Filedata1=name;
		  } 
		  }
		  if(counter!=0) { 
		   System.out.println("in file: \n"+filePath.getName());
		   name += " in " + filePath.getName();
//		  if (filePath.getName().contains("null")) {
//				name = filePath.getName().substring(0, 3);
//				//Filedata1 += (name.substring(0, 3) + "\n");
//			}
		  Filedata1+=name+"\n";
		 //name= filePath.getName(); 
		  }
		  return counter;
		 
	}

	// Ranking of Web Pages using merge sort
	// Collections.sort by default uses merge sort
	public static String rankFiles(Hashtable<?, Integer> fname, int occur) {

		// Transfer as List and sort it
		ArrayList<Map.Entry<?, Integer>> list = new ArrayList(fname.entrySet());

		Collections.sort(list, new Comparator<Map.Entry<?, Integer>>() {

			public int compare(Map.Entry<?, Integer> obj1, Map.Entry<?, Integer> obj2) {
				return obj1.getValue().compareTo(obj2.getValue());
			}
		});

		Collections.reverse(list);

		String rankingFiles = null;
		if (occur != 0) {
			System.out.println("\nTop 5 Search Results\n");

			int num = 5;
			int j = 1;
			while (list.size() > j && num > 0) {
				System.out.println("(" + j + ") " + list.get(j) + " times ");
				rankingFiles += list.get(j) + " times" + "\n";
				j++;
				num--;
			}
		} else {

		}
		return rankingFiles;
	}

	/* using regex to find similar string to pattern */
	public void suggestions(String pattern) {
		try {

			// String to be scanned to find the pattern.
			String line = " ";
			String reg = "[\\w]+[@$%^&*()!?{}\b\n\t]*";

			// Create a Pattern object
			Pattern pat = Pattern.compile(reg);
			// Now create matcher object.
			Matcher match = pat.matcher(line);
			int fileNum = 0;
			try {
				File directory = new File("I:\\ACC\\project\\SearchEng\\src\\Webpages\\Text\\");
				File[] fileArray = directory.listFiles();
				for (int i = 0; i < fileArray.length; i++) {
					findWord(fileArray[i], fileNum, match, pattern);
					fileNum++;
				}

				Set keys = new HashSet();
				Integer value = 1;
				Integer val = 0;
				int counter = 0;

				System.out.println("\nDid you mean?:");
				for (Map.Entry entry : hashTable.entrySet()) {
					if (val == entry.getValue()) {

						break;

					} else {

						if (value == entry.getValue()) {

							if (counter == 0) {
								System.out.print(entry.getKey());
								Filedata2 += entry.getKey();
								counter++;
							}

							else {
								System.out.print(" , " + entry.getKey());
								Filedata2 += ","+entry.getKey();
								counter++;
							}

						}

					}
				}

			} catch (Exception e) {
				System.out.println("Exception:" + e);
			} finally {

			}

		} catch (Exception e) {

		}
	}

	// finds strings with similar pattern and calls edit distance() on those strings
	public static void findWord(File sourceFile, int fileNumber, Matcher match, String str)
			throws FileNotFoundException, ArrayIndexOutOfBoundsException {
		try {
			// int i = 0;
			BufferedReader bufferReader = new BufferedReader(new FileReader(sourceFile));
			String line = null;

			while ((line = bufferReader.readLine()) != null) {
				match.reset(line);
				while (match.find()) {
					arrayL.add(match.group());
				}
			}
			bufferReader.close();
			for (int p = 0; p < arrayL.size(); p++) {
				hashTable.put(arrayL.get(p), editDistance(str.toLowerCase(), arrayL.get(p).toLowerCase()));
			}
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

	}

	// Uses Edit distance to compare nearest distance between keyword and similar
	// patterns obtained from regex
	public static int editDistance(String str1, String str2) {
		int len1 = str1.length();
		int len2 = str2.length();

		int[][] array = new int[len1 + 1][len2 + 1];

		for (int i = 0; i <= len1; i++) {
			array[i][0] = i;
		}

		for (int j = 0; j <= len2; j++) {
			array[0][j] = j;
		}

		// iterate though, and check last char
		for (int i = 0; i < len1; i++) {
			char c1 = str1.charAt(i);
			for (int j = 0; j < len2; j++) {
				char c2 = str2.charAt(j);

				if (c1 == c2) {

					array[i + 1][j + 1] = array[i][j];
				} else {
					int replace = array[i][j] + 1;
					int insert = array[i][j + 1] + 1;
					int delete = array[i + 1][j] + 1;

					int min = replace > insert ? insert : replace;
					min = delete > min ? min : delete;
					array[i + 1][j + 1] = min;
				}
			}
		}

		return array[len1][len2];
	}

	public MultiDivAdd search(String p) {
		Searcheng websearch = new Searcheng();
		Hashtable<String, Integer> hashtable = new Hashtable<String, Integer>();
		File dir = new File("I:\\ACC\\project\\SearchEng\\src\\Webpages\\Text");
		File[] fileArray = dir.listFiles();
		long fileNumber = 0;
		String pth = null, srctime = null, rFiles = null, rtime = null;
		// String[] Filedata1=null;
		int frequency = 0;
		int rep = 0; // No. of files that contains the Searched word
		try {
//				Scanner s = new Scanner (System.in);
//				System.out.println("Enter your search: ");
//				String p= s.nextLine();
			double startTime = System.currentTimeMillis();
			double startTimesearch = System.currentTimeMillis();
			//InvertedIndex offset1a = new InvertedIndex(p);
			for (int i = 1; i < fileArray.length; i++) {

				frequency = websearch.searchWord(fileArray[i], p);

				hashtable.put(fileArray[i].getName(), frequency);
				if (frequency != 0) {
				rep++;
					// String name =
					if (fileArray[i].getName().toString().substring(0, 3) == "null") {
						String name = fileArray[i].getName().toString();
						//Filedata1 += (name.substring(0, 3) + "\n");
					} else {
						//Filedata1 += (fileArray[i].getName().toString() + "\n");
					}
				}

				++fileNumber;
			}
			System.out.println("\nNumber of files contains " + p + " word is= " + rep);
			long endTimesearch = System.currentTimeMillis();
			double srchtime = endTimesearch - startTimesearch;
			System.out.println("\nSeacrh Result execution time:" + srchtime + " Milli Seconds");
			pth = Integer.toString(rep);
			srctime = Double.toString(srchtime);
			// searchData(pth);
			Scanner s1 = new Scanner(System.in);
			if (rep == 0) {

			System.out.println("\nSearching closely related words");
			websearch.suggestions(p);
//			InvertedIndex a= new InvertedIndex();
//				a.suggestions(p);

			}
//			System.out.println("\nFor Synonyms of the word "+ p+" enter yes or no");
//			String op =s1.nextLine();
//			if(op.equals("yes")) {
//				
//		        System.out.println("\nSearching synonyms");
//		        
//				websearch.suggestions(p);	
//				
//			}
			double startTimerank = System.nanoTime();

			rFiles = rankFiles(hashtable, rep);
			double endTimerank = System.nanoTime();
			System.out.println("\nRanking Algorithm time:" + (endTimerank - startTimerank) + " nano Seconds");
			double rankingTme = endTimerank - startTimerank;
			rtime = Double.toString(rankingTme);
			
			double endTime = System.currentTimeMillis();
			System.out.println("\nTotal Execution Time:" + (endTime - startTime) + " Milli Seconds");
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		} finally {

		}
		return new MultiDivAdd(pth, srctime, Filedata1, rFiles, rtime, Filedata2);
	}

	public static void main(String[] args) {
//		websearch.search();

	}

}