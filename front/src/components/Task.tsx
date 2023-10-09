"use client";
import { iniciarTask } from "@/actions/iniciarTask";
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

  return (
    <Box m={5}>
      <Flex align={"center"}>
        <ActionIcon
          variant="light"
          color="green"
          children={
            !tarefaEmAndamento ? <IconPlayerPlay /> : <IconPlayerPause />
          }
          onClick={(e) => iniciarTask(task)}
        />
        <Text ml={10}>{task.descricao}</Text>
      </Flex>
    </Box>
  );
};
