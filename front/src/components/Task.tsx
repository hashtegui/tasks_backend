import { ITask } from "@/types/Task";

interface TaskComponentProps {
  task: ITask;
}
export const Task: React.FC<TaskComponentProps> = ({
  task,
}: TaskComponentProps) => {
  return (
    <div >
      <p>{task.descricao}</p>
    </div>
  );
};
