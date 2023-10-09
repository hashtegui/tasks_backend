"use client";
import { ITask } from "@/types/Task";
import { ActionIcon, Box, Flex, Text } from "@mantine/core";
import { IconPlayerPause, IconPlayerPlay } from "@tabler/icons-react";

interface TaskComponentProps {
  task: ITask;
}
export const Task: React.FC<TaskComponentProps> = ({
  task,
}: TaskComponentProps) => {
  const tarefaEmAndamento = task.dataInicio !== null && task.dataFim === null;
  const handlePlayTask = async () => {
    const url: string = `http://localhost:8080/tasks/${task.id}/iniciar`;
    const requestOptions: RequestInit = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ id: task.id }),
    };

    const response = await fetch(url, requestOptions);

    if (response.ok) {
      console.log("tarefa iniciada");
    }
  };

  return (
    <Box m={5}>
      <Flex align={"center"}>
        <ActionIcon
          variant="light"
          color="green"
          children={
            !tarefaEmAndamento ? <IconPlayerPlay /> : <IconPlayerPause />
          }
          onClick={handlePlayTask}
        />
        <Text ml={10}>{task.descricao}</Text>
      </Flex>
    </Box>
  );
};
