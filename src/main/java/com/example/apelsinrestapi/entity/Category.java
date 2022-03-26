package com.example.apelsinrestapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "category")
public class Category{
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Integer id;

   private boolean active = true;
   private String name;
}
