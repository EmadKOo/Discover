package com.example.discover.pojo;

public class Country {
    String Name;
    String extension;
    String imgURL;

    public Country(String name, String extension, String imgURL) {
        Name = name;
        this.extension = extension;
        this.imgURL = imgURL;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }
}
