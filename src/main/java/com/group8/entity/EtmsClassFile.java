package com.group8.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EtmsClassFile {
  private long outlineId;
  private String classTitle;
  private String classFile;
}
