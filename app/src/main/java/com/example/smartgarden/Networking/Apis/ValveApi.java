package com.example.smartgarden.Networking.Apis;

import com.example.smartgarden.Models.ValveAdapter;
import com.example.smartgarden.Networking.ApiResponses.ValveResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ValveApi {
    @GET("v3/79adc02c-d00f-4063-a61e-e389d20ee7b3")
    Call<ValveResponse> getValve();
}
