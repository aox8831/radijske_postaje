package com.example.music_projekt;

public class radioData {
    private  Integer id;
    private String ime;
    private String frekvenca;
    private String kraj;
    private Integer stZaposlenih;
    private Integer stZvrsti;

    public radioData(Integer id, String ime, String frekvenca, String kraj, Integer stZaposlenih, Integer stZvrsti)
    {
        this.id = id;
        this.ime = ime;
        this.frekvenca = frekvenca;
        this.kraj = kraj;
        this.stZaposlenih = stZaposlenih;
        this.stZvrsti = stZvrsti;
    }

    public Integer getId(){ return id; }
    public String getIme(){
        return ime;
    }
    public String getFrekvenca(){
        return frekvenca;
    }
    public String getKraj(){
        return kraj;
    }
    public Integer getStZaposlenih(){
        return stZaposlenih;
    }
    public Integer getStZvrsti(){
        return stZvrsti;
    }

}
