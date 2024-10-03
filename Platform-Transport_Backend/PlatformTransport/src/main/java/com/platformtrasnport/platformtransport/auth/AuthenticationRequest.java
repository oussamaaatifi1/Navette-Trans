package com.platformtrasnport.platformtransport.auth;

import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data

public class AuthenticationRequest {

    private String email;
    private String password;
}
