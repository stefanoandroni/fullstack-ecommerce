package org.stand.springbootecommerce.dto.request;

import lombok.Data;

@Data
public class UserUpdateRequest {
    private String name;
    private String surname;
}