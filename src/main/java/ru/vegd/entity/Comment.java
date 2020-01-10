package ru.vegd.entity;

import java.sql.Timestamp;
import java.util.Objects;

public class Comment {

    private Long commentId;
    private Long newsId;
    private Long authorId;
    private String commentText;
    private Timestamp sendingDate;

    public Comment(Long commentId, Long news_id, Long author_id, String comment_text, Timestamp sending_date) {
        this.commentId = commentId;
        this.newsId = news_id;
        this.authorId = author_id;
        this.commentText = comment_text;
        this.sendingDate = sending_date;
    }

    public Comment() {}

    public Long getComment_id() {
        return commentId;
    }

    public void setComment_id(Long comment_id) {
        this.commentId = comment_id;
    }

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

    public String getComment_text() {
        return commentText;
    }

    public void setComment_text(String comment_text) {
        this.commentText = comment_text;
    }

    public Timestamp getSending_date() {
        return sendingDate;
    }

    public void setSending_date(Timestamp sending_date) {
        this.sendingDate = sending_date;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return commentId == comment.commentId &&
                newsId == comment.newsId &&
                Objects.equals(authorId, comment.authorId) &&
                Objects.equals(commentText, comment.commentText) &&
                Objects.equals(sendingDate, comment.sendingDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(commentId, newsId, authorId, commentText, sendingDate);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "comment_id=" + commentId +
                ", news_id=" + newsId +
                ", author_id='" + authorId + '\'' +
                ", comment_text='" + commentText + '\'' +
                ", sending_date=" + sendingDate +
                '}';
    }
}
