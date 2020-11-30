/**
 * Node class used for implementation of WordTree, which is a binary search tree.
 * @author Luke Hollinda
 */
public class Node {

	/**
	 * Reference to child nodes alphabetically smaller then the current node.
	 */
	private Node leftNode;
	
	/**
	 * Reference to child nodes alphabetically larger then the current node.
	 */
	private Node rightNode;
	
	/**
	 * The word stored within the node.
	 */
	private String word;
	
	/**
	 * The number of occurences of a word found within the text file.
	 */
	private int occurrence;
	
	/**
	 * Constructs a node with no children, a word, and a single occurrence.
	 * @param word The word to be stored in the node.
	 */
	public Node(String word) {
		this.word = word;
		this.occurrence = 1;
		this.leftNode = null;
		this.rightNode = null;
	}
	
	public Node getLeftNode() {return leftNode;}
	public void setLeftNode(Node leftNode) {this.leftNode = leftNode;}
	
	public Node getRightNode() {return rightNode;}
	public void setRightNode(Node rightNode) {this.rightNode = rightNode;}
	
	public String getWord() {return word;}
	public void setWord(String word) {this.word = word;}
	
	public int getOccurrence() {return occurrence;}
	public void setOccurrence(int occurrence) {this.occurrence = occurrence;}
	
	/**
	 * Increments the occurrence data member of a node.
	 */
	public void incrementOccurrence() {this.occurrence++; }
	
	/**
	 * Recursively adds a word into the correct position within the binary search tree. Will either increment a nodes occurrence if an existing node matches the input word,
	 * else it will initialize a new node and place it in the correct position within the tree.
	 * @param theWord The word to be added to the binary search tree.
	 */
	public void addWordRec(String theWord)
	{
		if(this.word.compareTo(theWord) == 0) //Equal word case
		{
			occurrence++;
			return;
		}
		else if(this.word.compareTo(theWord) > 0) //Alphabetically smaller case
		{
			if(leftNode == null)
			{
				leftNode = new Node(theWord);
				return;
			}
			leftNode.addWordRec(theWord);
		}
		else if(this.word.compareTo(theWord) < 0) //Alphabetically larger case
		{
			if(rightNode == null)
			{
				rightNode = new Node(theWord);
				return;
			}
			rightNode.addWordRec(theWord);
		}
	
	}
	
	/**
	 * Used to print the WordTree using an IN_ORDER format.
	 */
	public void printWordInOrder()
	{
		if(leftNode != null)
		{
			leftNode.printWordInOrder();
		}
		
		System.out.print(word + " ");
		
		if(rightNode != null)
		{
			rightNode.printWordInOrder();
		}
	}
	
	/**
	 * Used to print the WordTree using a POST_ORDER format.
	 */
	public void printWordPostOrder()
	{
		if(leftNode != null)
		{
			leftNode.printWordPostOrder();
		}
		
		if(rightNode != null)
		{
			rightNode.printWordPostOrder();
		}
		
		System.out.print(word + " ");
	}

	/**
	 * Used to print the WordTree using a PRE_ORDER format.
	 */
	public void printWordPreOrder()
	{
		System.out.print(word + " ");
		
		if(leftNode != null)
		{
			leftNode.printWordPreOrder();
		}
		
		if(rightNode != null)
		{
			rightNode.printWordPreOrder();
		}
	}
	
	/**
	 * Recursively searches through the tree filling the TreeReport object as it goes.
	 * Also functions as the tree depth function and will return the depth of the tree as 
	 * the final integer return value.
	 * @param theReport The TreeReport object to be filled.
	 * @return Value of the depth of the tree at the current depth.
	 */
	public int fillReport(TreeReport theReport)
	{
		theReport.totalWords++;
		
		if(occurrence == 1)
		{
			theReport.uniqueWords++;
		}
		
		if(occurrence == theReport.mostOccuredWordNumber)
		{
			theReport.mostOccuredWords.add(word);
		}
		else if(occurrence > theReport.mostOccuredWordNumber)
		{
			theReport.mostOccuredWords.clear();
			theReport.mostOccuredWords.add(word);
			theReport.mostOccuredWordNumber = occurrence;
		}
		
		int leftNodeCount = 0, rightNodeCount = 0;
		
		if(leftNode == null && rightNode == null)
			return 1;
		
		if(leftNode != null)
		{
			leftNodeCount = leftNode.fillReport(theReport);
		}
		
		if(rightNode != null)
		{
			rightNodeCount = rightNode.fillReport(theReport);
		}
		
		return (leftNodeCount > rightNodeCount) ? leftNodeCount + 1  : rightNodeCount + 1 ;
	}


	/**
	 * Recursively searches the tree. Returns the words occurrence, if the word does not exist -1 will be returned.
	 * @param theWord The word being searched for
	 * @return The number of occurrences of the found word. If the word is not found will return -1.
	 */
	public int searchWord(String theWord)
	{
		if(word.equals(theWord))
		{
			return occurrence;
		}
		else if(rightNode != null && theWord.compareTo(word) > 0)
		{
			return rightNode.searchWord(theWord);
		}
		else if(leftNode != null && theWord.compareTo(word) < 0)
		{
			return leftNode.searchWord(theWord);
		}
		
		return -1;
	}
}
