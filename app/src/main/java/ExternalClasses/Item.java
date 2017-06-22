package ExternalClasses;

/**
 * Created by lenovo on 20/06/2017.
 */

public class Item {

    private String title;
    private String published_date;
    private String url;


    public Item(String title, String published_date, String url) {
        this.title = title;
        this.published_date = published_date;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublished_date() {
        return published_date;
    }

    public void setPublished_date(String published_date) {
        this.published_date = published_date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
