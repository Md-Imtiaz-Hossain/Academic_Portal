package com.sktech.academicportal.enums;

public enum FileTypes {
    GalleryImage("Gallery Image"),
    NoticePDF("Notice in PDF"),
    NoticeWritten("Written Notice"),
    Achievement("Achievement");

    public final String type;

    FileTypes(String type){
        this.type = type;
    }

    String getType(){
        return this.type;
    }
}
