package ru.vegd.entity;

import java.sql.Timestamp;
import java.util.Objects;

public class News {

    private Long newsId;
    private Long authorId;
    private String tittle;
    private String newsText;
    private Timestamp publicDate;

    public News(Long news_id, Long author_id, String tittle, String news_text, Timestamp public_date) {
        this.newsId = news_id;
        this.authorId = author_id;
        this.tittle = tittle;
        this.newsText = news_text;
        this.publicDate = public_date;
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

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
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
        News aNews = (News) o;
        return newsId == aNews.newsId &&
                authorId == aNews.authorId &&
                Objects.equals(tittle, aNews.tittle) &&
                Objects.equals(newsText, aNews.newsText) &&
                Objects.equals(publicDate, aNews.publicDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(newsId, authorId, tittle, newsText, publicDate);
    }

    @Override
    public String toString() {
        return "News{" +
                "news_id=" + newsId +
                ", author_id=" + authorId +
                ", tittle='" + tittle + '\'' +
                ", news_text='" + newsText + '\'' +
                ", public_date=" + publicDate +
                '}';
    }
}
