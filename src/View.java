import java.util.Scanner;

/**
 * Functions as the front end of the program prompting the user for their choice of available functions. 
 * @author Luke Hollinda
 *
 */
public class View {
	
	

	public static void main(String[] args) {
	
		Scanner scan = new Scanner(System.in);
		
		WordTree theTree = new WordTree();
		theTree.addFileContents(scan);
		
		int choice = -1;
		
		
		
		while(choice != 4)
		{
			System.out.println("\nPlease choose an option from the list below:");
			System.out.println("1:\tPrint report of tree");
			System.out.println("2:\tTraverse and print tree contents");
			System.out.println("3:\tSearch for word");
			System.out.println("4:\tExit program");
			
			choice = scan.nextInt();
			scan.nextLine();
			
			switch(choice)
			{
			case 1:
				theTree.runTreeReport();
				break;
			case 2:
				theTree.printTree(scan);
				break;
			case 3:
				theTree.searchTree(scan);
				break;
			case 4:
				break;
			default:
				System.out.println("Invalid selection. Please try again...\n");
			}
		}
		
		System.out.println("Thank you for using the application. Closing program...");
		scan.close();

		
	}

}
