package org.mestresolutions.tasks.services;

import java.time.LocalDateTime;
import java.util.List;

import org.mestresolutions.tasks.domain.tasks.GetAllTasksQueryDto;
import org.mestresolutions.tasks.domain.tasks.Task;
import org.mestresolutions.tasks.domain.tasks.TaskInputDto;
import org.mestresolutions.tasks.repositories.TaskRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TaskService {

  @Autowired
  private TaskRepository taskRepository;

  @Autowired
  private ExcelService<Task> excelService;

  public Task createTask(TaskInputDto taskDto) {
    Task task = new Task();

    BeanUtils.copyProperties(taskDto, task);
    return taskRepository.save(task);

  }

  public Task iniciarTaskPorId(Integer id) {
    var task = taskRepository.findById(id).orElse(null);

    if (task != null) {
      if (task.foiFinalizada()) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Task já foi finalizada");
      }

      if (task.estaEmAndamento()) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Task já esta em andamento");
      }

      if (task.foiIniciada()) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Task já foi iniciada");
      }
      task.setDataInicio(LocalDateTime.now());
      taskRepository.save(task);
      return task;
    }

    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task não encontrada");

  }

  public Task finalizarTaskPorId(Integer id) {
    var task = taskRepository.findById(id).orElse(null);
    if (task != null) {
      if (!task.foiIniciada()) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Task não foi iniciada");
      }
      if (task.foiFinalizada()) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Task já foi finalizada");
      }
      if (task.estaEmAndamento()) {
        task.setDataFim(LocalDateTime.now());
        taskRepository.save(task);
        return task;
      }

    }
    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task não encontrada");
  }

  public List<Task> getTasks(GetAllTasksQueryDto getAllTasksQueryDto) {
    if (getAllTasksQueryDto.isEmAndamento()) {
      return taskRepository.findByDataFimIsNullAndDataInicioIsNotNull();
    }

    if (getAllTasksQueryDto.isFinalizada()) {
      return taskRepository.findByDataFimIsNotNull();
    }
    return taskRepository.findAll();
  }

  public void deletarTaskPorId(Integer id) {
    var task = taskRepository.findById(id).orElse(null);
    if (task != null) {
      taskRepository.delete(task);
      return;
    }
    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task não encontrada");
  }

  public void gerarExcel() {

    // Workbook workbook = new XSSFWorkbook();
    // Sheet sheet = workbook.createSheet("Sheet 1");
    // Row row = sheet.createRow(0);
    // Cell cell = row.createCell(0);
    // cell.setCellValue("Hello World");

    // try {
    //   FileOutputStream fileOut = new FileOutputStream("output.xlsx");
    //   workbook.write(fileOut);
    //   fileOut.close();
    //   workbook.close();
    //   System.out.println("Excel file generated successfully.");
    // } catch (IOException e) {
    //   e.printStackTrace();
    // }

    List<Task> tasks = taskRepository.findAll();

    excelService.gerarExcel(tasks);
  }
}
