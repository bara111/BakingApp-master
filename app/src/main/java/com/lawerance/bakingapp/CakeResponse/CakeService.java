package com.lawerance.bakingapp.CakeResponse;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CakeService {
    @GET("baking.json")
    Call<ArrayList<cake>> getCakes();


}
