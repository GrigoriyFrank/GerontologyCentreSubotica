
package rs.gerontoloski.centresuboticaapp.retrofit.POJO.media;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sizes {

    @SerializedName("thumbnail")
    @Expose
    private Thumbnail thumbnail;
    @SerializedName("medium")
    @Expose
    private Medium medium;
    @SerializedName("medium_large")
    @Expose
    private MediumLarge mediumLarge;
    @SerializedName("large")
    @Expose
    private Large large;
    @SerializedName("sydney-large-thumb")
    @Expose
    private SydneyLargeThumb sydneyLargeThumb;
    @SerializedName("sydney-medium-thumb")
    @Expose
    private SydneyMediumThumb sydneyMediumThumb;
    @SerializedName("sydney-small-thumb")
    @Expose
    private SydneySmallThumb sydneySmallThumb;
    @SerializedName("sydney-service-thumb")
    @Expose
    private SydneyServiceThumb sydneyServiceThumb;
    @SerializedName("sydney-mas-thumb")
    @Expose
    private SydneyMasThumb sydneyMasThumb;
    @SerializedName("full")
    @Expose
    private Full full;

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Medium getMedium() {
        return medium;
    }

    public void setMedium(Medium medium) {
        this.medium = medium;
    }

    public MediumLarge getMediumLarge() {
        return mediumLarge;
    }

    public void setMediumLarge(MediumLarge mediumLarge) {
        this.mediumLarge = mediumLarge;
    }

    public Large getLarge() {
        return large;
    }

    public void setLarge(Large large) {
        this.large = large;
    }

    public SydneyLargeThumb getSydneyLargeThumb() {
        return sydneyLargeThumb;
    }

    public void setSydneyLargeThumb(SydneyLargeThumb sydneyLargeThumb) {
        this.sydneyLargeThumb = sydneyLargeThumb;
    }

    public SydneyMediumThumb getSydneyMediumThumb() {
        return sydneyMediumThumb;
    }

    public void setSydneyMediumThumb(SydneyMediumThumb sydneyMediumThumb) {
        this.sydneyMediumThumb = sydneyMediumThumb;
    }

    public SydneySmallThumb getSydneySmallThumb() {
        return sydneySmallThumb;
    }

    public void setSydneySmallThumb(SydneySmallThumb sydneySmallThumb) {
        this.sydneySmallThumb = sydneySmallThumb;
    }

    public SydneyServiceThumb getSydneyServiceThumb() {
        return sydneyServiceThumb;
    }

    public void setSydneyServiceThumb(SydneyServiceThumb sydneyServiceThumb) {
        this.sydneyServiceThumb = sydneyServiceThumb;
    }

    public SydneyMasThumb getSydneyMasThumb() {
        return sydneyMasThumb;
    }

    public void setSydneyMasThumb(SydneyMasThumb sydneyMasThumb) {
        this.sydneyMasThumb = sydneyMasThumb;
    }

    public Full getFull() {
        return full;
    }

    public void setFull(Full full) {
        this.full = full;
    }

}
