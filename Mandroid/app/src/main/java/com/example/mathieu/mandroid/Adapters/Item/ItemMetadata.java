package com.example.mathieu.mandroid.Adapters.Item;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Florian on 31/12/2016.
 */

public class ItemMetadata {

    private String bitrate;
    private String dateCreation;
    private String duration;
    private String path;

    public ItemMetadata(String bitrate, String duration, String path) {
        this.bitrate = bitrate;
        this.duration = duration;
        this.path = path;
    }
    public ItemMetadata(String bitrate,String path){
        this.bitrate=bitrate;
        this.path=path;
    }
    public ItemMetadata(String bitrate, String dateCreation, String duration, String path) {
        this.bitrate = bitrate;
        Date inputDate = null;
        try {
            inputDate = new SimpleDateFormat("yyyyMMdd'T'HHmmss", Locale.getDefault()).parse(dateCreation);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.dateCreation = new SimpleDateFormat("dd'/'MM'/'yy", Locale.getDefault()).format(inputDate);
        this.duration = duration;
        this.path=path;

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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
