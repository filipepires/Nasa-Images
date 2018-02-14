
package filipe.pires.me.images.io.domainobjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Collection {

    @SerializedName("href")
    @Expose
    private String href;
    @SerializedName("metadata")
    @Expose
    private Metadata metadata;
    @SerializedName("links")
    @Expose
    private List<Link> links = null;
    @SerializedName("items")
    @Expose
    private List<Item> items = null;
    @SerializedName("version")
    @Expose
    private String version;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

}
