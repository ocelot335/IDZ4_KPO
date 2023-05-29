package com.example.hwww.request;

import lombok.Data;

@Data
public class GiveRoleRequest {
    private String email;
    private String role = "chef";
}
