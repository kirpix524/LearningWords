package ru.surovcevnv.learnwords.classes;

import java.rmi.server.UID;
import java.util.*;

public class Vocabulary {
    private enum Modes {
        LEARN,
        REPEAT
    }

    private HashSet<Word> words;
    private ArrayList<Word> currentStack;
    private Modes mode;
    //
    private Iterator<Word> iteratorWord;
    private UID currentStudyUID;
    private int currentWordsToLearn;
    private Word currentWord;
    //
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

    public void startStudying(int wordsToLearn) {
        if (wordsToLearn<1) wordsToLearn=1;
        this.mode = Modes.LEARN;
        currentWordsToLearn=wordsToLearn;
        currentStack = new ArrayList<Word>(wordsToLearn);
        iteratorWord = words.iterator();
        currentStudyUID = new UID();
    }

    public void nextWord() {
        switch (mode) {
            case LEARN:
                Word word;
                do {
                    if (iteratorWord.hasNext()) {
                        word=iteratorWord.next();
                    } else {
                        currentWord = null;
                        return;
                    }
                } while (word.isStudied());
                currentWord = word;
                return;
            case REPEAT:
                if (iteratorWord.hasNext()) {
                    currentWord = iteratorWord.next();
                } else {
                    currentWord = null;
                }
                break;
        }
    }

    public void studiedWord(Word word) {
        word.setStudied(new Date(), currentStudyUID);
        currentStack.add(word);
        words.add(word);
    }

    public boolean studiedEnough() {
        return currentStack.size() >= currentWordsToLearn;
    }

    public Word getCurrentWord() {
        return currentWord;
    }
    public void moveWordRandomly(Word word) {
//        int moveOn = (int)(2.0+4*Math.random());
////        if (currentStack.size()-currentStack.indexOf(word)>)
//        int index = 0;
//        currentStack.remove(word);
//        currentStack.add(index, word);
        }
}
