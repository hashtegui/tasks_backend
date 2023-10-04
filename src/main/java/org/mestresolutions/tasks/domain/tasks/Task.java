package org.mestresolutions.tasks.domain.tasks;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "tasks")
public class Task {

  public Task() {
  }

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_sequence")
  @SequenceGenerator(name = "task_sequence", sequenceName = "tasks_id_seq",allocationSize = 1)
  private Integer id;

  @Column(name = "descricao")
  private String descricao;

  @Column(name = "data_hora_inicio")
  private LocalDateTime dataInicio;

  @Column(name = "data_hora_fim")
  private LocalDateTime dataFim;

  @Column(name = "data_hora_exclusao")
  private LocalDateTime dataExclusao;

  public LocalDateTime getDataExclusao() {
    return dataExclusao;
  }

  public void setDataExclusao(LocalDateTime dataExclusao) {
    this.dataExclusao = dataExclusao;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public LocalDateTime getDataInicio() {
    return dataInicio;
  }

  public void setDataInicio(LocalDateTime dataInicio) {
    this.dataInicio = dataInicio;
  }

  public LocalDateTime getDataFim() {
    return dataFim;
  }

  public void setDataFim(LocalDateTime dataFim) {
    this.dataFim = dataFim;
  }

  public Integer getId() {
    return id;
  }

  public boolean foiIniciada(){
    return dataInicio != null;
  }

  public boolean estaEmAndamento(){
    return dataFim == null && dataInicio != null;
  }

  public boolean foiFinalizada(){
    return dataFim != null;
  }

  @Override
  public String toString() {
    return "Task [id=" + id + ", descricao=" + descricao + ", dataInicio=" + dataInicio + ", dataFim=" + dataFim + "]";
  }

}
