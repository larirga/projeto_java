package com.betrybe.agrix.dto;

import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.security.Role;

/**
 * Person DTO.
 */
public record PersonDto(String username, String password, Role role) {

  /**
   * Person Dto fromPersonEntity.
   */
  public static PersonDto fromPersonEntity(Person person) {
    return new PersonDto(
        person.getUsername(),
        person.getPassword(),
        person.getRole()
    );
  }

  /**
   * Person Dto toEntity.
   */
  public Person toEntity() {
    Person person = new Person();
    person.setUsername(username);
    person.setPassword(password);
    person.setRole(role);
    return person;
  }
}
