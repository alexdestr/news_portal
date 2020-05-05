package ru.vegd.entity;

import java.sql.Timestamp;
import java.util.Objects;

public class Comment {

    private Long commentId;
    private Long newsId;
    private Long authorId;
    private String authorName;
    private String commentText;
    private Timestamp sendingDate;

    public Comment(Long commentId, Long newsId, Long authorId, String authorName, String commentText, Timestamp sendingDate) {
        this.commentId = commentId;
        this.newsId = newsId;
        this.authorId = authorId;
        this.authorName = authorName;
        this.commentText = commentText;
        this.sendingDate = sendingDate;
    }

    public Comment() {}

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long comment_id) {
        this.commentId = comment_id;
    }

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

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String comment_text) {
        this.commentText = comment_text;
    }

    public Timestamp getSendingDate() {
        return sendingDate;
    }

    public void setSendingDate(Timestamp sending_date) {
        this.sendingDate = sending_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(commentId, comment.commentId) &&
                Objects.equals(newsId, comment.newsId) &&
                Objects.equals(authorId, comment.authorId) &&
                Objects.equals(authorName, comment.authorName) &&
                Objects.equals(commentText, comment.commentText) &&
                Objects.equals(sendingDate, comment.sendingDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(commentId, newsId, authorId, authorName, commentText, sendingDate);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", newsId=" + newsId +
                ", authorId=" + authorId +
                ", authorName='" + authorName + '\'' +
                ", commentText='" + commentText + '\'' +
                ", sendingDate=" + sendingDate +
                '}';
    }
}
