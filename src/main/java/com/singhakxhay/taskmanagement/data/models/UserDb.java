package com.singhakxhay.taskmanagement.data.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
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
  @OneToMany(mappedBy = "createdBy")
  private List<TaskListDb> taskLists;
  @OneToMany(mappedBy = "createdBy")
  private List<TaskDb> tasks;
}
