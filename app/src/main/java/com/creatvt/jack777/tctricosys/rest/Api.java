package com.creatvt.jack777.tctricosys.rest;

import com.creatvt.jack777.tctricosys.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    @GET("posts")
    public Call<List<Post>> getPost();

}
