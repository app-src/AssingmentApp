
package io.github.ashishthehulk.assignmentapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Astronomy__1 {

    @SerializedName("astro")
    @Expose
    private Astro astro;

    public Astro getAstro() {
        return astro;
    }

    public void setAstro(Astro astro) {
        this.astro = astro;
    }

}
