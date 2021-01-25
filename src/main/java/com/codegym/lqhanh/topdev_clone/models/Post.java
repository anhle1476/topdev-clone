package com.codegym.lqhanh.topdev_clone.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Post {
    private int id;
    private String title;
    private String summary;
    private String imgLink;
    private String content;
    private User author;
    private Date creationDate;
    private Date lastUpdated;
    private List<Category> categories;
    private List<Tag> tagList;
    private boolean isApproved;

    private Post(PostBuilder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.summary = builder.summary;
        this.imgLink = builder.imgLink;
        this.content = builder.content;
        this.author = builder.author;
        this.creationDate = builder.creationDate;
        this.lastUpdated = builder.lastUpdated;
        this.categories = builder.categories;
        this.tagList = builder.tagList;
        this.isApproved = builder.isApproved;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }


    public List<Category> getCategories() {
        return categories;
    }

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    public void addCategory(Category categories) {
        this.categories.add(categories);
    }

    public void addTag(Tag tagList) {
        this.tagList.add(tagList);
    }

    public String getFormattedTagList() {
        return tagList.stream()
                .map(Tag::getName)
                .collect(Collectors.joining(", "));
    }

    public boolean containsCategory(int categoryId) {
        return categories.stream().anyMatch(category -> category.getId() == categoryId);
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", imgLink='" + imgLink + '\'' +
                ", content='" + content + '\'' +
                ", author=" + author +
                ", lastUpdated=" + lastUpdated +
                ", categories=" + categories +
                ", tagList=" + tagList +
                ", isApproved=" + isApproved +
                '}';
    }

    public static class PostBuilder {
        private static final String DEFAULT_TEXT = "No data found!";
        private static final String DEFAULT_IMG_LINK = "https://via.placeholder.com/800x500";

        private int id;
        private final String title;
        private User author;
        private String summary;
        private String imgLink;
        private String content;
        private Date creationDate;
        private Date lastUpdated;
        private final List<Category> categories;
        private final List<Tag> tagList;
        private boolean isApproved;

        public PostBuilder(String title) {
            this.title = title;
            this.summary = DEFAULT_TEXT;
            this.imgLink = DEFAULT_IMG_LINK;
            this.content = DEFAULT_TEXT;
            tagList = new ArrayList<>();
            categories = new ArrayList<>();
        }

        public PostBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public PostBuilder setSummary(String summary) {
            this.summary = summary;
            return this;
        }

        public PostBuilder setAuthor(User author) {
            this.author = author;
            return this;
        }

        public PostBuilder setImgLink(String imgLink) {
            this.imgLink = imgLink;
            return this;
        }

        public PostBuilder setContent(String content) {
            this.content = content;
            return this;
        }

        public PostBuilder setCreationDate(Date creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        public PostBuilder setLastUpdated(Date lastUpdated) {
            this.lastUpdated = lastUpdated;
            return this;
        }

        public PostBuilder setApproved(boolean approved) {
            this.isApproved = approved;
            return this;
        }

        public PostBuilder addTag(Tag tag) {
            this.tagList.add(tag);
            return this;
        }


        public PostBuilder addCategory(Category tag) {
            this.categories.add(tag);
            return this;
        }

        public Post build() {
            if (creationDate == null)
                creationDate = new Date();
            return new Post(this);
        }
    }
}
