package com.jrumpe.surveyfirebase.model;

public class Retail {

    String located ;
    String appropriate;
    String selection;
    String appealing;

    public Retail (){

    }

    public Retail(String located, String appropriate, String selection, String appealing) {
        this.located = located;
        this.appropriate = appropriate;
        this.selection = selection;
        this.appealing = appealing;
    }

    public String getLocated() {
        return located;
    }

    public void setLocated(String located) {
        this.located = located;
    }

    public String getAppropriate() {
        return appropriate;
    }

    public void setAppropriate(String appropriate) {
        this.appropriate = appropriate;
    }

    public String getSelection() {
        return selection;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }

    public String getAppealing() {
        return appealing;
    }

    public void setAppealing(String appealing) {
        this.appealing = appealing;
    }
}
