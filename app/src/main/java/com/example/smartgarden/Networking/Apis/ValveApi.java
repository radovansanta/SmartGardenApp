package com.example.smartgarden.Networking.Apis;

import com.example.smartgarden.Models.Valve;
import com.example.smartgarden.Models.ValveAdapter;
import com.example.smartgarden.Networking.ApiResponses.ValveResponse;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;

public interface ValveApi {
    @GET("api/valves")
    Call<List<Valve>> getValve();

    @PATCH("api/valves/{id}/{state}")
    Call<ResponseBody> updateValve(@Path("id") int id, @Path("state") String state);
}
