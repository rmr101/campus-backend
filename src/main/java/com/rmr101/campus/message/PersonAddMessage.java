package com.rmr101.campus.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonAddMessage {
    private String role;
    private UUID uuid;
    private String campusID;
    private String firstName;
    private String LastName;
}
