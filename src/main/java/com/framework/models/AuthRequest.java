package com.framework.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthRequest {


    @JsonProperty("username")
    public String username;

    @JsonProperty("password")
    public String password;
}
