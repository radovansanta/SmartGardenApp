package com.example.smartgarden.Networking.Apis;

import com.example.smartgarden.Models.Command;
import com.example.smartgarden.Models.Valve;
import com.example.smartgarden.Models.ValveAdapter;
import com.example.smartgarden.Networking.ApiResponses.ValveResponse;

import org.json.JSONObject;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ValveApi {
    @GET("api/valves")
    Call<List<Valve>> getValve();

    @GET("api/valves/names")
    Call<List<String>> getValveNames();

    @POST("api/valves/{id}/{state}")
    Call<ResponseBody> updateValve(@Path("id") int id, @Path("state") String state);

    @POST("api/commands/{time}")
    Call<ResponseBody> addCommand(@Body Command body, @Path("time") String time);

}
