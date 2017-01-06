package com.example.mathieu.mandroid;

/**
 * Created by Florian on 31/12/2016.
 */

public class ItemMetadata {

    private String bitrate;
    private String dateCreation;
    private String duration;


    public ItemMetadata(String bitrate, String dateCreation, String duration) {
        this.bitrate = bitrate;
        this.dateCreation = dateCreation;
        this.duration = duration;
    }

    public String getBitrate() {
        return bitrate;
    }

    public void setBitrate(String bitrate) {
        this.bitrate = bitrate;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}