package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserDTO {
   @NotNull(message = "ID is mandatory")
   private Long id;

   @NotBlank(message = "Name is mandatory")
   private String name;

   @NotBlank(message = "Email is mandatory")
   @Email(message = "Email should be valid")
   private String email;

   // Getters and Setters
   public Long getId() {
       return id;
   }

   public void setId(Long id) {
       this.id = id;
   }

   public String getName() {
       return name;
   }

   public void setName(String name) {
       this.name = name;
   }

   public String getEmail() {
       return email;
   }

   public void setEmail(String email) {
       this.email = email;
   }
}
