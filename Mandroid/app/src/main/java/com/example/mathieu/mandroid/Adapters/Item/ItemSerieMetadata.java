package com.example.mathieu.mandroid.Adapters.Item;

/**
 * Created by Florian on 10/01/2017.
 */

public class ItemSerieMetadata extends ItemMetadata {
    private  String episode;
    private String saison;
    public ItemSerieMetadata(String bitrate, String dateCreation, String duration, String path) {
        super(bitrate, dateCreation, duration, path);
    }
    public ItemSerieMetadata(String bitrate, String dateCreation, String duration, String path, String episode,String saison) {
        super(bitrate, dateCreation, duration, path);
        this.episode=episode;
        this.saison=saison;
    }
}
