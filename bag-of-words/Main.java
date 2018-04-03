import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;
import java.io.IOException;
import java.util.Scanner;

public class Main{
	public static void main(String[] args){
		//Ask user input for path
		Scanner read = new Scanner(System.in);
		System.out.print("Enter path: ");
		String path = read.next();
		
		//save to folder variable
		File folder = new File(path);	
		listAllFiles(folder);
	}


	//Lists all files to an input directory
	public static void listAllFiles(File folder) {
		HashMap<String, Integer> words = new HashMap<String,Integer>();
		int dictioSize = 0;
		int totalWords = 0;
		
		//instantiate BagOfWords with initially 0 dictiosize and totalwords
		BagOfWords bagOfWords = new BagOfWords(words, dictioSize, totalWords);

		//stores to a list yung files
		File[] filenames = folder.listFiles();
		//ITERATES THE FILENAMES
		for(File file : filenames){
			if(file.isDirectory()){
				listAllFiles(file);	
			}else{
				try{
					//READS CONTENT OF THE CURRENT FILE
					readContent(file, bagOfWords);
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		}
		
		//This stores each word to a hashmap, checks if there's a duplicate
		for(String word: bagOfWords.getWords().keySet()) {
			
			String key = word.toString();
			int value = bagOfWords.getWords().get(key);
			System.out.println("key: " + key);
			System.out.println("value: " + value);
		}	
		System.out.println("Dictionary size: " + bagOfWords.getDictioSize());
		System.out.println("Total Words: " + bagOfWords.getTotalWords());

		//write to ouput file
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("solutions.txt"));
            	for(String word: bagOfWords.getWords().keySet()) {
		
					String key = word.toString();
					int value = bagOfWords.getWords().get(key);
					writer.write("key: " + key);
					writer.write("value: " + value);
				}	
            	writer.write("Dictionary size: " + bagOfWords.getDictioSize());	
            	writer.write("\n");
            	writer.write("Total Words: " + bagOfWords.getTotalWords());
            writer.close();
        } catch(FileNotFoundException e){
            System.out.println("File not found");
        } catch(Exception e){
            System.out.println(e.getMessage());
        }

	}
	//Reads content 
	public static BagOfWords readContent(File file, BagOfWords bagOfWords) throws IOException{
		HashMap<String, Integer> words = bagOfWords.getWords();
		Set set = words.entrySet();	
		Iterator iter = set.iterator();
		String strline;


		int dictioSize = 0;
		int totalWords = 0;
		int updDictioSize = 0;
		int updTotalWords = 0;

		try(BufferedReader br = new BufferedReader(new FileReader(file))){

			String [] tokens;
			while((strline = br.readLine()) != null) {
				tokens = strline.split("\\s+");
				for(int i=0; i<tokens.length; i++){
					
					//Checks for non-alphanumeric 
					tokens[i] = tokens[i].replaceAll("[^a-zA-Z0-9]", "");
					//LowerCase tokens
					tokens[i] = tokens[i].toLowerCase();
					if(!tokens[i].equals("")){
						if(words.containsKey(tokens[i])){
						
							words.put(tokens[i], words.get(tokens[i])+1);
						}
						else{
							words.put(tokens[i],1);
							dictioSize++;
						}
						totalWords++;
					}
				}
		
			}
			
			//UPDATE bagOfWords
			updDictioSize=bagOfWords.updateDictioSize(dictioSize);
			updTotalWords=bagOfWords.updateTotalWords(totalWords);
		
			BagOfWords newBoW = new BagOfWords(words, updDictioSize, updTotalWords);
			return newBoW;
		}

	}
	//total words - 27566
	//dictionary size - 5136
	/* References: 
		
		https://netjs.blogspot.com/2017/04/reading-all-files-in-folder-java-program.html

	*/
}	