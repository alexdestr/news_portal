package ru.vegd.entity;

import java.util.Objects;

public class Tag {

    private Long newsId;
    private String tags;

    public Tag(Long news_ID, String tags) {
        this.newsId = news_ID;
        this.tags = tags;
    }

    public Tag () {}

    public Long getNewsID() {
        return newsId;
    }

    public void setNewsID(Long news_ID) {
        this.newsId = news_ID;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag1 = (Tag) o;
        return newsId == tag1.newsId &&
                Objects.equals(tags, tag1.tags);
    }

    @Override
    public int hashCode() {

        return Objects.hash(newsId, tags);
    }

    @Override
    public String toString() {
        return "Tag{" +
                "news_ID=" + newsId +
                ", tags='" + tags + '\'' +
                '}';
    }
}
