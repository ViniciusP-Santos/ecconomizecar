package com.example.ecconomizecar;

public class Average {
    String data, media;
    long kms, litros;

    public Average(){}

    public Average(String data, String media, long kms, long litros) {
        this.data = data;
        this.media = media;
        this.kms = kms;
        this.litros = litros;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public long getKms() {
        return kms;
    }

    public void setKms(long kms) {
        this.kms = kms;
    }

    public long getLitros() {
        return litros;
    }

    public void setLitros(long litros) {
        this.litros = litros;
    }
}
