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

    public Long getNews_id() {
        return newsId;
    }

    public void setNews_id(Long news_id) {
        this.newsId = news_id;
    }

    public Long getAuthor_id() {
        return authorId;
    }

    public void setAuthor_id(Long author_id) {
        this.authorId = author_id;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getNews_text() {
        return newsText;
    }

    public void setNews_text(String news_text) {
        this.newsText = news_text;
    }

    public Timestamp getPublic_date() {
        return publicDate;
    }

    public void setPublic_date(Timestamp public_date) {
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
