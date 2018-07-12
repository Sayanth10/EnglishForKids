package com.sayanth10.englishthroughtamil;

public class Word {
    private String englishWord;
    private String tamilWord;
    private int imageId;
    private int audioId;

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int image) {
        this.imageId = image;
    }

    public String getEnglishWord() {
        return englishWord;
    }

    public void setEnglishWord(String englishWord) {
        this.englishWord = englishWord;
    }

    public String getTamilWord() {
        return tamilWord;
    }

    public void setTamilWord(String tamilWord) {
        this.tamilWord = tamilWord;
    }

    public Word(String cenglishword, String ctamilword) {
        englishWord = cenglishword;
        tamilWord = ctamilword;
    }

    public Word(String cenglishword, String ctamilword, int cimageId) {
        englishWord = cenglishword;
        tamilWord = ctamilword;
        imageId = cimageId;
    }

    public int getAudioId() {
        return audioId;
    }

    public Word(String cenglishword, String ctamilword, int cimageId, int caudioId) {
        englishWord = cenglishword;
        tamilWord = ctamilword;
        imageId = cimageId;
        audioId = caudioId;

    }
}
