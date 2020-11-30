import java.util.ArrayList;


/**
 * Object used to aid in finding a trees total number of words, number of unique words, and most occurred word(s).
 * Member variables are declared public with self-evident names for ease of implementation. 
 * @author Luke Hollinda
 *
 */
public class TreeReport {
		public int totalWords;
		public int uniqueWords;
	
		public ArrayList<String> mostOccuredWords;
		public int mostOccuredWordNumber;
		
		
		public TreeReport()
		{
			this.totalWords = 0;
			this.uniqueWords = 0;
			this.mostOccuredWords = new ArrayList<String>();
			this.mostOccuredWordNumber = 0;
		}
		
		
		
}
