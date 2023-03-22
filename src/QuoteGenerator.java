import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 *  -------------------
 *  QuoteGenerator
 * 	Joshua Pratchios
 *  February 10, 2022
 *  -------------------
 *  generates a random quote from a given file "quotes_all.csv" when called. 
 *  Assumes ; is always used as a delimiter.
 *  -------------------
 */
public class QuoteGenerator {
	
	List<String> quotes = new ArrayList<>(); //arrayList used to store quotes from the input file
	int ran; //random variable that will be used to call a random quote.
	
	
	/*
	 * QuoteGenerator
	 * Constructor for use in DisplayQuotes Class. 
	 * @Param fileName : String used to store the name of file. allows for use of fileNames outside of given.
	 */
	public QuoteGenerator(String fileName) throws IOException {
		setQuotes(fileName); //generates the list of quotes from given file.
		setRandom(); //sets random equal to a random value from 0 to the size of the quotes arrayList.
	}
	
	/*
	 * setRandom
	 * sets ran to a random value from 0 to 1.0 times the size of the arraylist quotes.
	 */
	
	public void setRandom() {
		ran = (int) (Math.random() * quotes.size());
	}
	
	/*
	 * getRamdom
	 * @return ran : the current value of ran.
	 */
	
	public int getRandom() {
		return ran;
	}
	
	/*
	 * getQuotes
	 * @return quote : the current arrayList of quotes.
	 */
	
	public String getQuote() {
		System.out.println(quotes.get(ran));
		System.out.println(ran);
		return(quotes.get(ran));
	}
	
	/*
	 * outputQuote
	 * Outputs the randomly generated quote into system for debugging purposes.
	 * @return : the quote located at ran in the ArrayList quotes.
	 */

	public List<String> getQuotes() {
		return quotes;
	}
	
	/*
	 * setQuotes
	 * fills the ArrayList with quotes from the given input file
	 * @param fileName : name of the current file. Allows for use of multiple different input datasets.
	 * @param line : the current line in the file.
	 * @param delimiter : delimiter for splitting of lines. Defaulted to ;.
	 * @param temp : Array of Strings used to store different splits. Used in formatting each String in quotes to include only the name and author.
	 */
	public void setQuotes(String fileName) throws IOException {

		/*
		 * Creates a bufferedReader to read through the file.
		 */
		try(BufferedReader buffer = new BufferedReader(new FileReader(fileName))) {
			
			String line = ""; //The current line in the file.
			String delimiter = ";"; //delimiter for splitting. Defaulted to ;. 
			
			/*
			 * This while loop reads in lines from given file until it cannot find anymore.
			 * lines are split by ; and put into an array of strings. 
			 * temp[0] : stores the quote 
			 * temp[1] : stores the author
			 */
			
			while((line = buffer.readLine()) != null) {  
				String[] temp = line.split(delimiter); //searches for ; to split the quotes.
				quotes.add("''" + temp[0] + "'' - " + temp[1]); //formatting for each quote.
			}
		}
		
	}

	/*
	 * moreFromAuthor
	 * @param author : the name of the author.
	 * @param rand : random variable for searching through matches.
	 * @param matches : arrayList of strings meant to save all quotes that match the author.
	 * @return newQuote : a new randomly generated quote from the list of matches. Can contain the same quote.
	 */
	
	public String moreFromAuthor(String author) {
		
		String newQuote =""; //the quote to be returned
		int rand = 0; //new random value for searching through matches.
		
		List<String> matches = new ArrayList<>(); //arrayList of Strings that will hold all quotes that match the author.
		matches.add(0, "Author Not Found. Please Try Again!"); //keeps matches from returning an error if it was not able to match the input.
		
		//iterates through quotes and searches for any that have a matching author.
		for(String str: quotes) {
			if(str.substring(str.indexOf("'' -") + 2, str.length()).contains(author)) {
				
				matches.add(str);
			}
		}
		
		// Checks to see if matches successfully grew. If it didnt output will just be "author not found". If it grew the error text will be removed.
		if(matches.size() > 1) {
			matches.remove(0);
		}
		
		rand = (int) (Math.random() * matches.size()); //sets rand equal to 0-1.0 * the number of matches
		newQuote = matches.get(rand); //chooses a random quote from set of matches
		System.out.println(newQuote);
		System.out.println(rand);
		return newQuote;
	}
	
	
	/*
	 * outputQuote 
	 * outputs the quote at a given position in the quotes arrayList. Used for testing.
	 * @param random : any integer for testing purposes
	 * @return out : the quote located at the given position.
	 */
	
	public String outputQuote(int random) {

		String out = quotes.get(random);
		System.out.print(random);
		System.out.println(out);
		
		return out;
	}
	
}//end QuoteGenerator
