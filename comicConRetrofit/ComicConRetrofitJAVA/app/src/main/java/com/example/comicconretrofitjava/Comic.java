package com.example.comicconretrofitjava;

/**
 * https://www.jsonschema2pojo.org/
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Comic {

    @SerializedName("month")
    @Expose
    private String month;
    @SerializedName("num")
    @Expose
    private Integer num;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("year")
    @Expose
    private String year;
    @SerializedName("news")
    @Expose
    private String news;
    @SerializedName("safe_title")
    @Expose
    private String safeTitle;
    @SerializedName("transcript")
    @Expose
    private String transcript;
    @SerializedName("alt")
    @Expose
    private String alt;
    @SerializedName("img")
    @Expose
    private String img;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("day")
    @Expose
    private String day;

    /**
     * No args constructor for use in serialization
     *
     */
    public Comic() {
    }

    /**
     *
     * @param news
     * @param img
     * @param transcript
     * @param month
     * @param year
     * @param num
     * @param link
     * @param alt
     * @param title
     * @param safeTitle
     * @param day
     */
    public Comic(String month, Integer num, String link, String year, String news, String safeTitle, String transcript, String alt, String img, String title, String day) {
        super();
        this.month = month;
        this.num = num;
        this.link = link;
        this.year = year;
        this.news = news;
        this.safeTitle = safeTitle;
        this.transcript = transcript;
        this.alt = alt;
        this.img = img;
        this.title = title;
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }

    public String getSafeTitle() {
        return safeTitle;
    }

    public void setSafeTitle(String safeTitle) {
        this.safeTitle = safeTitle;
    }

    public String getTranscript() {
        return transcript;
    }

    public void setTranscript(String transcript) {
        this.transcript = transcript;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Comic.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("month");
        sb.append('=');
        sb.append(((this.month == null)?"<null>":this.month));
        sb.append(',');
        sb.append("num");
        sb.append('=');
        sb.append(((this.num == null)?"<null>":this.num));
        sb.append(',');
        sb.append("link");
        sb.append('=');
        sb.append(((this.link == null)?"<null>":this.link));
        sb.append(',');
        sb.append("year");
        sb.append('=');
        sb.append(((this.year == null)?"<null>":this.year));
        sb.append(',');
        sb.append("news");
        sb.append('=');
        sb.append(((this.news == null)?"<null>":this.news));
        sb.append(',');
        sb.append("safeTitle");
        sb.append('=');
        sb.append(((this.safeTitle == null)?"<null>":this.safeTitle));
        sb.append(',');
        sb.append("transcript");
        sb.append('=');
        sb.append(((this.transcript == null)?"<null>":this.transcript));
        sb.append(',');
        sb.append("alt");
        sb.append('=');
        sb.append(((this.alt == null)?"<null>":this.alt));
        sb.append(',');
        sb.append("img");
        sb.append('=');
        sb.append(((this.img == null)?"<null>":this.img));
        sb.append(',');
        sb.append("title");
        sb.append('=');
        sb.append(((this.title == null)?"<null>":this.title));
        sb.append(',');
        sb.append("day");
        sb.append('=');
        sb.append(((this.day == null)?"<null>":this.day));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}