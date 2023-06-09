import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.*;


/*
 * -------------------
 * DisplayQuotes
 * Joshua Pratchios
 * February 11, 2022
 * -------------------
 * Creates the GUI for displaying and searching
 * through quotes generated by QuoteGenerator.
 * -------------------
 */

public class DisplayQuotes {

   private static final int FRAME_WIDTH = 375; //width for the generated Frame.
	 private static final int FRAME_HEIGHT = 185; //height for the generated Frame.
	 private static String inputText = ""; //default string for user's input text

	 public static void main(String[] args) throws IOException {
		 QuoteGenerator quotesList = new QuoteGenerator("quotes_all.csv"); //new quote generator object using the given file.

		 JFrame frame = new JFrame(); //new frame for the GUI
		 JPanel panel = new JPanel(); //panel for UI elements such as buttons and text.
		 JTextArea textArea = new JTextArea(6, 5); //text area where given text like quotes go.
		 JTextField input = new JTextField(6); //user input field.

		 JButton button1 = new JButton("Random Quote"); //button to find a random quote.
		 JButton button2 = new JButton("Search for Author"); //button to open the text box for finding a specific author
		 JButton button3 = new JButton("Return"); //button to return to the main page
		 JButton button4 = new JButton("Search"); //button to submit a search

		 /*
		  * Fun text area formatting. Sets up text wrapping and other small elements for the area where quotes will appear
		  */

		 textArea.setText(quotesList.getQuote());
		 textArea.setWrapStyleWord(true);
		 textArea.setLineWrap(true);
		 textArea.setOpaque(false);
		 textArea.setEditable(false);
		 textArea.setFocusable(false);
		 textArea.setBackground(UIManager.getColor("Label.background"));
		 textArea.setFont(UIManager.getFont("Label.font"));
		 textArea.setBorder(UIManager.getBorder("Label.border"));

		 //adding UI elements to the panel.
		 panel.add(button1);
		 panel.add(button2);
		 panel.add(button3);
		 panel.add(input);
		 panel.add(button4);

		 // setting certain elements invisible until they are needed.
		 button3.setVisible(false);
		 button4.setVisible(false);
		 input.setVisible(false);

		 /*
		  * Action listener for the "Random Quote" button.
		  * if the button is pressed it will return a random quote in the text area.
		  */

		 button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quotesList.setRandom();
				textArea.setText(quotesList.getQuote());
			}
		 });

		 /*
		  * Action listener for "Search" button. If this is pressed it will hide the first and second button
		  * and make the text box and the return and search buttons available for use.
		  * Additionally it change the text area to inform the user of what to do.
		  */

		 button2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					button1.setVisible(false);
					button2.setVisible(false);
					button3.setVisible(true);
					button4.setVisible(true);
					input.setVisible(true);
					textArea.setText("Type the Name of the Author Below");
				}
			 });

		 /*
		  * Action Listener for the "Return" button.
		  * if pressed it will reset everything to the default state, including the original quote.
		  */

		 button3.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e ) {
				 button1.setVisible(true);
				 button2.setVisible(true);
				 button3.setVisible(false);
				 button4.setVisible(false);
				 textArea.setText(quotesList.getQuote());
				 input.setVisible(false);
			 }
		 });

		 /*
		  * Action Listener for the "Search" button.
		  * if pressed this will call moreFromAuthor
		  * and return a random quote from the given author if it found one.
		  */

		 button4.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e ) {
				 inputText = input.getText();
				 textArea.setText(quotesList.moreFromAuthor(inputText));
			 }
		 });

		 //adding everything to the frame and setting it visible.
		 //also sets conditions for closing the program.
		 frame.getContentPane().add(textArea, BorderLayout.NORTH);
		 frame.add(panel);
		 frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 frame.setVisible(true);


	 }
}
