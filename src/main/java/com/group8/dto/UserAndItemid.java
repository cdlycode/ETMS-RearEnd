package com.group8.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAndItemid {
    private int userId;
    private int itemId;
    private String trainClassTitle;
    private String catalog;
    private long durations;
    private int trainStatus;
}
