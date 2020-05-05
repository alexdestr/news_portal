package ru.vegd.entity;

import java.sql.Timestamp;
import java.util.Objects;

public class News {

    private Long newsId;
    private Long authorId;
    private String authorName;
    private String title;
    private String newsText;
    private Timestamp publicDate;

    public News(Long newsId, Long authorId, String authorName, String title, String newsText, Timestamp publicDate) {
        this.newsId = newsId;
        this.authorId = authorId;
        this.authorName = authorName;
        this.title = title;
        this.newsText = newsText;
        this.publicDate = publicDate;
    }

    public News() {}

    public Long getNewsId() {
        return newsId;
    }

    public void setNewsId(Long news_id) {
        this.newsId = news_id;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long author_id) {
        this.authorId = author_id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNewsText() {
        return newsText;
    }

    public void setNewsText(String news_text) {
        this.newsText = news_text;
    }

    public Timestamp getPublicDate() {
        return publicDate;
    }

    public void setPublicDate(Timestamp public_date) {
        this.publicDate = public_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return Objects.equals(newsId, news.newsId) &&
                Objects.equals(authorId, news.authorId) &&
                Objects.equals(authorName, news.authorName) &&
                Objects.equals(title, news.title) &&
                Objects.equals(newsText, news.newsText) &&
                Objects.equals(publicDate, news.publicDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(newsId, authorId, authorName, title, newsText, publicDate);
    }

    @Override
    public String toString() {
        return "News{" +
                "newsId=" + newsId +
                ", authorId=" + authorId +
                ", authorName='" + authorName + '\'' +
                ", title='" + title + '\'' +
                ", newsText='" + newsText + '\'' +
                ", publicDate=" + publicDate +
                '}';
    }
}
