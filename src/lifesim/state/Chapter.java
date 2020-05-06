package lifesim.state;

public enum Chapter {

    CHAPTER_1("Invasion of Town Central", 1),

    CHAPTER_2("City", 2);


    public final String title;
    public final int chapterNum;

    Chapter(String title, int chapterNum) {
        this.title = title;
        this.chapterNum = chapterNum;
    }
}
