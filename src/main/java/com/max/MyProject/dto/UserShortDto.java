package com.max.MyProject.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserShortDto {

    private long id;

    private String userName;
}