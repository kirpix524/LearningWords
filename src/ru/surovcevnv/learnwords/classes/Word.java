package ru.surovcevnv.learnwords.classes;

import java.rmi.server.UID;
import java.util.Date;

public class Word {
    private String word;
    private String translation;
    private Date studyDate;
    private UID studyUID; //uid of package inside one date
    private boolean isStudied;

    public Word(String word, String translation) {
        init(word,translation,false, null, null);
    }

    public Word(String word, String translation, Date studyDate, UID studyUID) {
        Boolean isStudied=true;
        if ((studyDate==null)||(studyUID==null)) {
            studyDate = null;
            studyUID = null;
            isStudied = false;
        }
        init(word,translation, isStudied, studyDate, studyUID);
    }

    private void init(String word, String translation, boolean isStudied, Date studyDate, UID studyUID) {
        this.word=word;
        this.translation = translation;
        this.isStudied = isStudied;
        this.studyDate = studyDate;
        this.studyUID = studyUID;
    }

    //getters
    public String getWord() {
        return word;
    }

    public String getTranslation() {
        return translation;
    }

    public Date getStudyDate() {
        return studyDate;
    }

    public UID getStudyUID() {
        return studyUID;
    }

    public boolean isStudied() {
        return isStudied;
    }

    //setters
    public void setWord(String word) {
        this.word = word;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public void setStudied(Date studyDate, UID studyUID) {
        isStudied = true;
        this.studyDate=studyDate;
        this.studyUID=studyUID;
    }

    public void setUnstudied() {
        isStudied = false;
        this.studyDate=null;
        this.studyUID=null;
    }

    @Override
    public String toString() {
        return "[слово:"+getWord()+", перевод:"+getTranslation()+"]";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj==this) return true;
        if (!(obj instanceof Word)) return false;
        return getWord().equals(((Word) obj).getWord()) && getTranslation().equals(((Word) obj).getTranslation());
    }
}
