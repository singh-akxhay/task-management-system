package com.singhakxhay.taskmanagement.data.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "task_lists")
public class TaskListDb {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String taskListId;
  private String name;
  @CreationTimestamp
  private Date createdAt;
  @ManyToOne
  @JoinColumn(name = "fk_user_id")
  private UserDb createdBy;
  @OneToMany(mappedBy = "taskList")
  private List<TaskDb> tasks;
}
