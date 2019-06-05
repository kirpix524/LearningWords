import java.rmi.server.UID;
import java.util.*;

public class Vocabulary {
    private HashSet<Word> words;

    public Vocabulary() {
        words = new HashSet<Word>();
    }

    public Vocabulary(HashSet<Word> words) {
        this.words.addAll(words);
    }

    public void addWord(String word, String translation) {
        this.words.add(new Word(word, translation));
    }

    public void addWord(String word, String translation, Date studyDate, UID studyUID) {
        this.words.add(new Word(word, translation, studyDate, studyUID));
    }

    public void addWord(Word word) {
        this.words.add(word);
    }
}
