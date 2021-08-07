
package io.github.ashishthehulk.assignmentapp;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Astronomy {

    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("astronomy")
    @Expose
    private Astronomy__1 astronomy;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Astronomy__1 getAstronomy() {
        return astronomy;
    }

    public void setAstronomy(Astronomy__1 astronomy) {
        this.astronomy = astronomy;
    }

}
