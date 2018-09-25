
package filipe.pires.me.images.main.data.datatransferobject;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Item {

    @SerializedName("href")
    @Expose
    private String href;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    @SerializedName("links")
    @Expose
    private List<ItemLink> links = null;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public List<ItemLink> getLinks() {
        return links;
    }

    public void setLinks(List<ItemLink> links) {
        this.links = links;
    }

}
