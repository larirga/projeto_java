package com.betrybe.agrix.ebytr.staff.dto;

/**
 * Token DTO.
 */
public record TokenDto(String token) {
  public static TokenDto formated(String token) {
    return new TokenDto(token);
  }
}
