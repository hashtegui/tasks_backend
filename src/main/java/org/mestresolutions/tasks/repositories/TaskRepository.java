package org.mestresolutions.tasks.repositories;

import java.util.List;

import org.mestresolutions.tasks.domain.tasks.Task;
import org.springframework.data.jpa.repository.JpaRepository;



public interface TaskRepository extends JpaRepository<Task, Integer>{
  List<Task> findByDataFimIsNull();
  List<Task> findByDataFimIsNotNull();
  List<Task> findByDataExclusaoIsNull();
  List<Task> findByDataFimIsNullAndDataInicioIsNotNull();



}
