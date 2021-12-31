package com.example.myuowd;

public class NewsArticle {
    public String title;
    public int imageSrcId;
    public String articlePreviewText;
    public String articleText;
    public String age;

    public NewsArticle(String title, int imageSrcId, String articlePreviewText, String articleText, String age) {
        this.title = title;
        this.imageSrcId = imageSrcId;
        this.articlePreviewText = articlePreviewText;
        this.articleText = articleText;
        this.age = age;
    }
}
