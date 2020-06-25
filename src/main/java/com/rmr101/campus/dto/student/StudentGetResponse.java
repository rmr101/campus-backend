package com.rmr101.campus.dto.student;

import lombok.Data;
import java.util.UUID;

@Data
public class StudentGetDto {
  private UUID uuid;
  private String name;
}
