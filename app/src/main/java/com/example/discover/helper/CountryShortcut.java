package com.example.discover.helper;

import com.example.discover.pojo.Country;

import java.util.ArrayList;

public class CountryShortcut {
    ArrayList<Country> countries = new ArrayList<>();

    public CountryShortcut(){
        countries.add(new Country("United States of America", "us",""));
        countries.add(new Country("United Arab Emirates" , "ae", ""));
        countries.add(new Country("Madagascar", "ar",""));
        countries.add(new Country("Montserrat", "at",""));
        countries.add(new Country("Nauru", "au",""));
        countries.add(new Country("Uzbekistan", "be",""));
        countries.add(new Country("Bulgaria", "bg",""));
        countries.add(new Country("Barbados", "br",""));
        countries.add(new Country("Canada", "ca",""));
        countries.add(new Country("Switzerland", "ch",""));
        countries.add(new Country("China", "cn",""));
        countries.add(new Country("Colombia", "co",""));
        countries.add(new Country("Cuba", "cu",""));
        countries.add(new Country("Czechia", "cz",""));
        countries.add(new Country("Germany", "de",""));
        countries.add(new Country("Egypt", "eg",""));
        countries.add(new Country("France", "fr",""));
        countries.add(new Country("United Kingdom", "gb",""));
        countries.add(new Country("Greece", "gr",""));
        countries.add(new Country("Hong Kong", "hk",""));
        countries.add(new Country("Hungary", "hu",""));
        countries.add(new Country("Indonesia", "id",""));
        countries.add(new Country("Ireland", "ie",""));
        countries.add(new Country("Palastine", "il",""));
        countries.add(new Country("India", "in",""));
        countries.add(new Country("Italy", "it",""));
        countries.add(new Country("Japan", "jp",""));
        countries.add(new Country("Korea", "kr",""));
        countries.add(new Country("Mexico", "mx",""));
        countries.add(new Country("Malaysia", "my",""));
        countries.add(new Country("Poland", "pl",""));
        countries.add(new Country("Portugal", "pr",""));
        countries.add(new Country("Romania", "ro",""));
        countries.add(new Country("Russian", "ru",""));
        countries.add(new Country("Saudi Arabia", "sa",""));
        countries.add(new Country("Turkey", "tr",""));
    }

    public ArrayList<Country> getCountries(){
        return countries;
    }
}
