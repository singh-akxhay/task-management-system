package com.singhakxhay.taskmanagement.data.models;

import jakarta.persistence.*;
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
public class TaskDb {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String taskId;
  private String title;
  private String description;
  @CreationTimestamp
  private Date createdAt;
  @UpdateTimestamp
  private Date modifiedAt;
  @Enumerated(EnumType.STRING)
  private TaskStatusDb taskStatus;
  private Date dueDate;
  @ManyToOne
  @JoinColumn(name = "fk_user_id")
  private UserDb createdBy;
}
