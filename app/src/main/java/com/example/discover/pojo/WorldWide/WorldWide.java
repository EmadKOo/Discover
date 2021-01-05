package com.example.discover.pojo.WorldWide;

import java.util.ArrayList;
import java.util.List;

public class WorldWide {

    public String status ;
    public int totalResults;
    public ArrayList<Article> articles ;

    public WorldWide() {
    }

    public WorldWide(String status, int totalResults, ArrayList<Article> articles) {
        this.status = status;
        this.totalResults = totalResults;
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public ArrayList<Article> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<Article> articles) {
        this.articles = articles;
    }
}
