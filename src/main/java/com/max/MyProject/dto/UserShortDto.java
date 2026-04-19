package com.max.MyProject.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserShortDto {

    @NonNull
    private long id;

    @NonNull
    private String userName;
}