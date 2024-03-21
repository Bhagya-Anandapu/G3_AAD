package com.self.newsfeed;

import java.util.List;

public class Response {
    private String success;
    private List<DetailedNewsModel> articles;
    public Response(String success, List<DetailedNewsModel> articles){
        this.success=success;
        this.articles=articles;
    }

    public String isSuccess(){return success;}
    public void setSuccess(String success){ this.success=success;}
    public List<DetailedNewsModel> getNews(){ return articles;}

    public void setNews(List<DetailedNewsModel> news){this.articles=news;}
}
