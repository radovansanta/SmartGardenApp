package com.example.smartgarden.Networking.ApiResponses;

import com.example.smartgarden.Models.Valve;

public class ValveResponse {
    private String name;
    private String description;
    private int iconId;

    public Valve getValve() {
        return new Valve(name, description,iconId);
    }
}
