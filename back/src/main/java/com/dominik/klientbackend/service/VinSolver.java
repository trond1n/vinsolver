package com.dominik.klientbackend.service;

import java.util.HashMap;

public class VinSolver {

    public String vin;
    public String marka;
    public String model;
    public String nadwozie;
    public String bezpieczenstwo;
    public String generacja;
    public String silnik;
    public int rok_produkcji;
    HashMap<String, String> manufacturer = new HashMap<String, String>();
    HashMap<String, String> nadwozie_map = new HashMap<String, String>();
    HashMap<String, String> generacja_map = new HashMap<String, String>();
    HashMap<String, String> model_map = new HashMap<String, String>();
    HashMap<String, Integer> year_map = new HashMap<String, Integer>();
    HashMap<String, String> engine_map = new HashMap<String, String>();





    public VinSolver(String vin){
        this.vin = vin;
        manufacturer.put("TMB","Skoda");

        decodeRelay(vin.substring(0, 3));


    }
    private void decodeRelay(String WMI)
    {
        year_map.put("T", 1996);
        year_map.put("V", 1997);
        year_map.put("W", 1998);
        year_map.put("X", 1999);
        year_map.put("Y", 2000);
        year_map.put("1", 2001);
        year_map.put("2", 2002);
        year_map.put("3", 2003);
        year_map.put("4", 2004);
        year_map.put("5", 2005);
        year_map.put("6", 2006);
        year_map.put("7", 2007);
        year_map.put("8", 2008);
        year_map.put("9", 2009);
        year_map.put("A", 2010);
        year_map.put("B", 2011);
        year_map.put("C", 2012);
        year_map.put("D", 2013);
        year_map.put("E", 2014);
        year_map.put("F", 2015);
        year_map.put("G", 2016);
        year_map.put("H", 2017);
        year_map.put("J", 2018);
        year_map.put("K", 2019);
        year_map.put("L", 2020);
        year_map.put("M", 2021);
        year_map.put("N", 2022);
        year_map.put("P", 2023);
        year_map.put("R", 2024);
        year_map.put("S", 2025);
        String yearSymbol = vin.substring(9, 10);
        rok_produkcji = year_map.get(yearSymbol);

        System.out.println("WMI: "+WMI);
        switch(WMI)
        {
            case "TMB":
                marka = "Skoda";
                decodeSkoda();
                break;
        }


    }
    private void decodeSkoda()
    {
        //pozycja 7-8
        //mapa modeli skody

        model_map.put("6Y","Fabia");
        model_map.put("5J","Fabia");
        model_map.put("NJ","Fabia");
        model_map.put("PJ","Fabia");
        model_map.put("1U","Octavia");
        model_map.put("1Z","Octavia");
        model_map.put("NE","Octavia");
        model_map.put("NX","Octavia");
        model_map.put("NS","Kodiaq");
        model_map.put("PS","Kodiaq");
        model_map.put("3U","Superb");
        model_map.put("3T","Superb");
        model_map.put("NP","Superb");
        model_map.put("NZ","Superb");
        model_map.put("AA","Citigo");
        model_map.put("NY","Elroq");
        model_map.put("NW","Kamiq");
        model_map.put("NU","Karoq");
        model_map.put("NH","Rapid");





        //Okreslenie modelu
        model=model_map.get(vin.substring(6, 8));
        System.out.println("model: "+model);
        //dalszy decoding modelu
        switch (model)
        {
            case "Fabia":
                if(vin.substring(3, 4).equals("M"))
                generacja_map.put("6Y","I Generacja");
                generacja_map.put("5J","II Generacja");
                generacja_map.put("NJ","III Generacja");
                generacja_map.put("PJ","IV Generacja");
                generacja=generacja_map.get(vin.substring(6, 8));
                System.out.println("generacja: "+generacja);
                nadwozie_map.put("E","Hatchback");
                nadwozie_map.put("1","Hatchback");
                nadwozie_map.put("A","Hatchback");
                nadwozie_map.put("B","Hatchback");
                nadwozie_map.put("C","Sedan");
                nadwozie_map.put("D","Sedan");
                nadwozie_map.put("F","Kombi");
                nadwozie_map.put("G","Kombi");
                nadwozie_map.put("H","Kombi");
                nadwozie_map.put("J","Kombi");
                nadwozie_map.put("N","Hatchback");
                nadwozie_map.put("P","Hatchback");
                nadwozie=nadwozie_map.get(vin.substring(3, 4));
                
                break;

            case "Octavia":
                generacja_map.put("1U","I Generacja");
                generacja_map.put("1Z","II Generacja");
                generacja_map.put("NE","III Generacja");
                generacja_map.put("NX","IV Generacja");
                generacja=generacja_map.get(vin.substring(6, 8));
                System.out.println("generacja: "+generacja);
                nadwozie_map.put("1","Kombi");
                nadwozie_map.put("A","Sedan");
                nadwozie_map.put("B","Sedan");
                nadwozie_map.put("C","Sedan");
                nadwozie_map.put("D","Sedan");
                nadwozie_map.put("E","Sedan");
                nadwozie_map.put("F","Kombi");
                nadwozie_map.put("G","Kombi");
                nadwozie_map.put("H","Kombi");
                nadwozie_map.put("J","Kombi");
                nadwozie_map.put("K","Kombi");
                nadwozie_map.put("L","Kombi");
                nadwozie_map.put("U","Kombi");
                nadwozie=nadwozie_map.get(vin.substring(3, 4));
                break;
            case "Kodiaq":
                generacja_map.put("NS","I Generacja");
                generacja_map.put("PS","II Generacja");
                generacja=generacja_map.get(vin.substring(6, 8));
                nadwozie_map.put("J","SUV");
                nadwozie_map.put("L","SUV");
                nadwozie=nadwozie_map.get(vin.substring(3, 4));


                break;
            case "Superb" :
                generacja_map.put("3U","I Generacja");
                generacja_map.put("3T","II Generacja");
                generacja_map.put("NP","III Generacja");
                generacja_map.put("NZ","IV Generacja");
                generacja=generacja_map.get(vin.substring(6, 8));
                nadwozie_map.put("1","Sedan");
                nadwozie_map.put("3","Kombi");
                nadwozie_map.put("A","Sedan");
                nadwozie_map.put("B","Sedan");
                nadwozie_map.put("C","Sedan");
                nadwozie_map.put("J","Kombi");
                nadwozie_map.put("L","Kombi");
                nadwozie_map.put("N","Kombi");
                nadwozie_map.put("R","Kombi");
                nadwozie=nadwozie_map.get(vin.substring(3, 4));
                break;
            case "Citigo":
                generacja_map.put("AA","I Generacja");
                generacja=generacja_map.get(vin.substring(6, 8));
                nadwozie_map.put("Z","Hatchback");
                nadwozie=nadwozie_map.get(vin.substring(3, 4));
                //engine_map.put("H","1.0 G-TEC 68KM 50kW");
                engine_map.put("L","e iV 83KM 61kW");
                engine_map.put("M","e iV 83KM 61kW");
                silnik=engine_map.get(vin.substring(9, 10));
                break;
            case "Elroq":
                if(vin.charAt(3) == 'N') {
                    generacja_map.put("NY", "I Generacja");
                    generacja = generacja_map.get(vin.substring(6, 8));
                    nadwozie_map.put("N", "SUV");
                    nadwozie = nadwozie_map.get(vin.substring(3, 4));
                    engine_map.put("G", "55 kWh (170 KM)");
                    engine_map.put("C", "63 kWh (204 KM)");
                    engine_map.put("H", "82 kWh (286 KM)");
                    silnik = engine_map.get(vin.substring(4, 5));
                }
                else
                {
                    model="Enyaq";
                    generacja_map.put("NY", "I Generacja");
                    generacja = generacja_map.get(vin.substring(6, 8));
                    nadwozie_map.put("E", "Coupe");
                    nadwozie_map.put("G", "Coupe");
                    nadwozie_map.put("J", "SUV");
                    nadwozie_map.put("L", "SUV");
                    nadwozie = nadwozie_map.get(vin.substring(3, 4));
                    //dodac silniki


                }

                //Elroq i Enyaq oba maja NY daltego sa razem
                break;
            case "Kamiq":
                model="Kamiq";
                generacja_map.put("NW", "I Generacja");
                generacja = generacja_map.get(vin.substring(6, 8));
                nadwozie_map.put("G", "Coupe");
                nadwozie = nadwozie_map.get(vin.substring(3, 4));
                break;
            case "Karoq":
                model="Karoq";
                generacja_map.put("NU", "I Generacja");
                generacja = generacja_map.get(vin.substring(6, 8));
                nadwozie_map.put("J", "SUV");
                nadwozie_map.put("L", "SUV 4x4");
                nadwozie = nadwozie_map.get(vin.substring(3, 4));
                break;
            case "Rapid":
                model="Rapid";
                generacja_map.put("NH","II Generacja");
                generacja = generacja_map.get(vin.substring(6, 8));
                nadwozie_map.put("A", "Sedan");
                nadwozie_map.put("E", "Kombi");
                nadwozie = nadwozie_map.get(vin.substring(3, 4));
                break;
            case "Roomster":
                model="Roomster";
                generacja_map.put("5J", "I Generacja");
                generacja = generacja_map.get(vin.substring(6, 8));
                nadwozie_map.put("1", "Kombi Praktik");
                nadwozie_map.put("L", "Kombi");
                nadwozie_map.put("M", "Kombi");
                nadwozie_map.put("N", "Kombi Praktik");
        }


    }



}
