import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


/**
 * A binary search tree that stores strings in order. Left child nodes are alphabetically smaller then parent nodes and right child nodes are alphabetically larger.
 * @author Luke Hollinda
 */
public class WordTree {
	
		/**
		 * The root node of the BST.
		 */
		private Node root;
		
		
		/**
		 * Initializes an empty tree.
		 */
		public WordTree()
		{
			this.root = null;
		}
		
		
		/**
		 * Adds a word to the Tree structure in the correct position for a binary search tree.
		 * @param word The word to be added to the tree.
		 */
		public void addWord(String word) 
		{
			//Empty tree case
			if(root == null)
				root = new Node(word);
			else
				root.addWordRec(word);					
		}
		
		/**
		 * Prompts user for a file name. Handles incorrect file name and IOExceptions properly. 
		 * Reads data from file into WordTree.
		 * @param scan Scanner passed to function to account for issues with scan.close() permanently closing the input stream.
		 */
		public void addFileContents(Scanner scan)
		{			
			String fileName = null;
			FileReader fr;
			BufferedReader reader = null;
			
			while(reader == null) //Accept file name from user input and initialize file readers 
			{
				System.out.println("Enter the input file name: ");
				fileName = scan.nextLine();
				
				try
				{
					fr = new FileReader(fileName);
					reader = new BufferedReader(fr);
					
				}catch(IOException e)
				{
					System.out.println("There was an error with your file name. Please try again.");
				}

			}
			
			try 
			{
				String line;
				while((line = reader.readLine()) != null) //Read file contents and put into tree structure
				{
					 //Regular expression to replace all non-alpha characters with space, and split on all white space
					String[] words = line.replaceAll("[^a-zA-Z ]", " ").toLowerCase().split("\\s+");
					for(String word : words)
					{
						addWord(word);
					}
				}
			}catch(IOException e)
			{
				System.out.println("Error reading file.");
			}
		}
		
		
		/**
		 * Prompts user for a tree traversal method and prints the tree to console in the specified manor.
		 * @param scan Scanner passed to function to account for issues with scan.close() permanently closing the input stream.
		 */
		public void printTree(Scanner scan)
		{
			if(root == null)
				System.out.println("The tree requested to be printed is empty.");
			else
			{
				int choice = 4;
				
				while(choice < 1 || choice > 3)
				{
					System.out.println("Enter the BST traversal method(1 = IN_ORDER, 2 = PRE_ORDER, 3 = POST_ORDER)");
					choice = Integer.parseInt(scan.nextLine());
				}
				
				
				switch(choice)
				{
				case 1:
					System.out.println("*************************************");
					System.out.print("IN_ORDER output: ");
					root.printWordInOrder();
					System.out.println("\n*************************************\n");

					break;
				case 2:
					System.out.println("*************************************");
					System.out.print("PRE_ORDER output: ");
					root.printWordPreOrder();
					System.out.println("\n*************************************\n");
					break;
				case 3:
					System.out.println("*************************************");
					System.out.print("POST_ORDER output: ");
					root.printWordPostOrder();
					System.out.println("\n*************************************\n");
					break;
				default:
					System.out.println("Something has gone wrong with switch case within WordTree/printTree");
				}
			
			}
		}

		/**
		 * Passes a TreeReport object along all nodes of the tree completing the assignment tasks with one functions. Calculates and displayes to console 
		 * the WordTree's total number of words, number of unique words, most occured word(s), and max depth.
		 */
		public void runTreeReport()
		{
			if(root == null)
			{
				System.out.println("The tree requested to be reported is empty.");
				return;
			}
			
			TreeReport theReport = new TreeReport();
			int maxDepth = root.fillReport(theReport);
			
			System.out.println("");
			System.out.println("*************************************");
			System.out.println("Total number of words: " + theReport.totalWords);
			System.out.println("");
			System.out.println("Number of unique words: " + theReport.uniqueWords);
			System.out.println("");
			System.out.println("The word(s) which occur(s) most often and the number of times that it/they occur(s):");
			for(String word : theReport.mostOccuredWords)
			{
				System.out.println(word + " : " + theReport.mostOccuredWordNumber + " times");
			}
			System.out.println("");
			System.out.println("The maximum height of the tree: " + maxDepth);
			System.out.println("*************************************");
			
		}

		/**
		 * Prompts the user for a word to search for within the WordTree. Displays if the word has been found within the tree structure and the number of occurences.
		 * @param scan Scanner passed to function to account for issues with scan.close() permanently closing the input stream.
		 */
		public void searchTree(Scanner scan)
		{
			
			if(root == null)
			{
				System.out.println("The tree requested to search is empty.");
				return;
			}
			
			System.out.println("Please enter the word you are looking for:");
			String searchedWord = scan.nextLine();
			
			int occurrenceNumber = root.searchWord(searchedWord);
			if(occurrenceNumber == -1)
			{
				System.out.println("Word not found!");
			}
			else
			{
				System.out.println("Your word has been found. It occurs " + occurrenceNumber
						+ " time in the input text file");
			}
			
		}

	
}
