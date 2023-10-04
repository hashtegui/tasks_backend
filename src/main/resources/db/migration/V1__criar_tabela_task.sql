create table tasks (
  id serial primary key,
  descricao varchar(100),
  data_hora_inicio timestamp,
  data_hora_fim timestamp
)