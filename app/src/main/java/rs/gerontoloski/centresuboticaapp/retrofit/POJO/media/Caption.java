
package rs.gerontoloski.centresuboticaapp.retrofit.POJO.media;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Caption {

    @SerializedName("rendered")
    @Expose
    private String rendered;

    public String getRendered() {
        return rendered;
    }

    public void setRendered(String rendered) {
        this.rendered = rendered;
    }

}
