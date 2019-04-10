
package com.ultimatix.cashrich.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ultimatix.cashrich.datamodels.Source;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Article {

    @SerializedName("id")
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    @NonNull
    private Integer id;

    @Ignore
    @SerializedName("source")
    @Expose
    private Source source;
    @Ignore
    @SerializedName("author")
    @Expose
    private Object author;
    @SerializedName("title")
    @Expose
    private String title;
    @Ignore
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("urlToImage")
    @Expose
    private String urlToImage;
    @Ignore
    @SerializedName("publishedAt")
    @Expose
    private String publishedAt;
    @Ignore
    @SerializedName("content")
    @Expose
    private String content;

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public Article withSource(Source source) {
        this.source = source;
        return this;
    }

    public Object getAuthor() {
        return author;
    }

    public void setAuthor(Object author) {
        this.author = author;
    }

    public Article withAuthor(Object author) {
        this.author = author;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Article withTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Article withDescription(String description) {
        this.description = description;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Article withUrl(String url) {
        this.url = url;
        return this;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public Article withUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
        return this;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public Article withPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
        return this;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Article withContent(String content) {
        this.content = content;
        return this;
    }

    @NonNull
    public Integer getId() {
        return id;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }
}
