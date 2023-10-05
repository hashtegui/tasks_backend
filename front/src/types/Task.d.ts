export interface ITask {
  id: number;
  descricao: string;
  dataInicio: string | Date | undefined;
  dataFim: string | Date | undefined;
  dataExclusao: string | Date | undefined;
}
