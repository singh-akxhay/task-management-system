package com.singhakxhay.taskmanagement.data.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDb {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String userId;
  private String name;
  private String username;
  private String password;
  @CreationTimestamp
  private Date createdAt;
  @UpdateTimestamp
  private Date modifiedAt;
}
