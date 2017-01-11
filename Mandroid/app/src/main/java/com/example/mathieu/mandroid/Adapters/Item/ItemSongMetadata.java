package com.example.mathieu.mandroid.Adapters.Item;

/**
 * Created by Florian on 11/01/2017.
 */

public class ItemSongMetadata   extends ItemMetadata{
    protected String keyAlbum;
    protected String keyAlmbumArtist;
    protected String keyArtist;
    protected String keyTrackNumber;
    protected String keyDate;
    protected String echantilloage;
    public ItemSongMetadata(String bitrate, String dateCreation, String duration, String path, String keyAlbum, String keyAlmbumArtist, String keyArtist, String keyTrackNumber, String keyDate) {
        super(bitrate, dateCreation, duration, path);
        this.keyAlbum = keyAlbum;
        this.keyAlmbumArtist = keyAlmbumArtist;
        this.keyArtist = keyArtist;
        this.keyTrackNumber = keyTrackNumber;
        this.keyDate = keyDate;
    }

    public ItemSongMetadata(String bitrate, String duration, String path, String keyAlbum, String keyAlmbumArtist, String keyArtist, String keyTrackNumber, String keyDate) {
        super(bitrate, duration, path);
        this.keyAlbum = keyAlbum;
        this.keyAlmbumArtist = keyAlmbumArtist;
        this.keyArtist = keyArtist;
        this.keyTrackNumber = keyTrackNumber;
        this.keyDate = keyDate;
    }

    public ItemSongMetadata(String bitrate, String duration, String path)
    {
        super(bitrate, duration, path);
    }
    public ItemSongMetadata(String bitrate, String path)
    {
        super(bitrate,  path);
    }
    public ItemSongMetadata(String bitrate, String dateCreation, String duration, String path) {
        super(bitrate, dateCreation, duration, path);
    }


    public String getKeyAlbum() {
        return keyAlbum;
    }

    public void setKeyAlbum(String keyAlbum) {
        this.keyAlbum = keyAlbum;
    }

    public String getKeyAlmbumArtist() {
        return keyAlmbumArtist;
    }

    public void setKeyAlmbumArtist(String keyAlmbumArtist) {
        this.keyAlmbumArtist = keyAlmbumArtist;
    }

    public String getKeyArtist() {
        return keyArtist;
    }

    public void setKeyArtist(String keyArtist) {
        this.keyArtist = keyArtist;
    }

    public String getKeyTrackNumber() {
        return keyTrackNumber;
    }

    public void setKeyTrackNumber(String keyTrackNumber) {
        this.keyTrackNumber = keyTrackNumber;
    }

    public String getKeyDate() {
        return keyDate;
    }

    public void setKeyDate(String keyDate) {
        this.keyDate = keyDate;
    }

    public String getEchantilloage() {
        return echantilloage;
    }

    public void setEchantilloage(String echantilloage) {
        this.echantilloage = echantilloage;
    }
}
