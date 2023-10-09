"use client";
import { ITask } from "@/types/Task";
import { Paper } from "@mantine/core";
import { Task } from "./Task";

interface TasksProps {
  tasks: ITask[];
}
export const Tasks: React.FC<TasksProps> = ({ tasks }) => {
  //ordenar task pelo id
  tasks.sort((a, b) => a.id - b.id);
  return (
    <Paper withBorder p="lg">
      {tasks.map((task) => (
        <Task key={task.id} task={task} />
      ))}
    </Paper>
  );
};
