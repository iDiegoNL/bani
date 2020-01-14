package com.diegor.bani.model;

public class Product {

    //values
    public int aantal;
    public String artikel;
    public double prijs;

    public Product(int aantal, String artikel, double prijs) {
        this.aantal = aantal;
        this.artikel = artikel;
        this.prijs = prijs;
    }

    //getters
    public int getAantal() {
        return aantal;
    }
    public String getArtikel(){
        return artikel;
    }
    public double getPrijs(){
        return prijs;
    }

    public String getPrijsString(){ return String.format("€ %.2f", prijs); }
    public String getAantalString(){return Integer.toString(aantal);}

    //setters
    public void setAantal(int aantal){
        this.aantal = aantal;
    }
    public void setArtikel(String artikel){
        this.artikel = artikel;
    }
    public void setPrijs(double prijs){
        this.prijs = prijs;
    }

    @Override
    public String toString(){
        return "Product gegevens: " + artikel + prijs + aantal;
    }
}
