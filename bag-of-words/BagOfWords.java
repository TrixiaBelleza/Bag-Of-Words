import java.util.HashMap;
import java.util.Map;

public class BagOfWords{
	HashMap<String, Integer> words = new HashMap<String,Integer>();
	int dictioSize = 0;
	int totalWords = 0;

	public BagOfWords(HashMap<String, Integer> words, int dictioSize, int totalWords) {
		this.words = words;
		this.dictioSize = dictioSize;
		this.totalWords = totalWords;
	}	

	//Getters
	public HashMap<String, Integer> getWords() {
		return this.words;
	}
	public int getDictioSize() {
		return this.dictioSize;
	}
	public int getTotalWords() {
		return this.totalWords;
	}

	//methods
	public int updateDictioSize(int value) {
		return this.dictioSize += value;
	}
	public int updateTotalWords(int value) {
		return this.totalWords += value;
	}
}