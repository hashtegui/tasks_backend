package org.mestresolutions.tasks.controller;

import org.mestresolutions.tasks.domain.tasks.GetAllTasksQueryDto;
import org.mestresolutions.tasks.domain.tasks.Task;
import org.mestresolutions.tasks.domain.tasks.TaskInputDto;
import org.mestresolutions.tasks.response.ResponseHandler;
import org.mestresolutions.tasks.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tasks")
public class TaskController {

  @Autowired
  private TaskService taskService;

  @GetMapping
  public ResponseEntity<Object> getTasks(
      @Valid GetAllTasksQueryDto getAllTasksQueryDto) {

    var tasks = taskService.getTasks(getAllTasksQueryDto);

    return ResponseHandler.generateResponse("sucesso", HttpStatus.OK, tasks);
  }

  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  public ResponseEntity<Object> createTask(@RequestBody @Valid TaskInputDto taskDto) {
    var task = taskService.createTask(taskDto);
    return ResponseHandler.generateResponse("Task criada com sucesso", HttpStatus.CREATED, task);
  }

  // iniciar task pelo id
  @PostMapping("/{id}/iniciar")
  public ResponseEntity<Object> startTaskById(@PathVariable("id") Integer id) {
    try {
      Task task = taskService.iniciarTaskPorId(id);
      return ResponseHandler.generateResponse("Task com o id " + id + " iniciada", HttpStatus.OK, task);

    } catch (ResponseStatusException e) {
      var httpStatus = HttpStatus.valueOf(e.getStatusCode().value()); // pegando o http status pelo status code
      return ResponseHandler.generateResponse(e.getReason(), httpStatus, null);
    } catch (Exception e) {

      return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);

    }
  }

  @PostMapping("/{id}/finalizar")
  public ResponseEntity<Object> finishTaskById(@PathVariable("id") Integer id) {
    try {
      Task task = taskService.finalizarTaskPorId(id);
      return ResponseHandler.generateResponse("Task com o id " + id + " finalizada", HttpStatus.OK, task);

    } catch (ResponseStatusException e) {
      var httpStatus = HttpStatus.valueOf(e.getStatusCode().value()); // pegando o http status pelo status code

      return ResponseHandler.generateResponse(e.getReason(), httpStatus, null);
    } catch (Exception e) {
      return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
    }
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  public void deleteTaskById(@PathVariable("id") Integer id) {
    try {
      taskService.deletarTaskPorId(id);
    } catch (ResponseStatusException e) {
      throw e;
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
  }

  @GetMapping("/excel")
  @ResponseStatus(code = HttpStatus.OK)
  public void excel() {
    taskService.gerarExcel();
  }
}
