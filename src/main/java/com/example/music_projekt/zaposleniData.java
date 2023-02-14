package com.example.music_projekt;

public class zaposleniData {
    private Integer idz;
    private String imez;
    private String priimekz;
    private String epostaz;
    private String polozajz;
    private String radioz;

    public zaposleniData(Integer idz, String imez, String priimekz, String epostaz, String polozajz, String radioz){
        this.idz=idz;
        this.imez=imez;
        this.priimekz=priimekz;
        this.epostaz=epostaz;
        this.polozajz=polozajz;
        this.radioz=radioz;
    }

    public Integer getIdz(){
        return idz;
    }
    public String getImez(){
        return imez;
    }
    public String getPriimekz(){
        return priimekz;
    }
    public String getEpostaz(){
        return epostaz;
    }
    public String getPolozajz(){
        return polozajz;
    }
    public String getRadioz(){
        return radioz;
    }
}
