"use client";

import { Box, Button, Flex, TextInput } from "@mantine/core";
import { IconPlus } from "@tabler/icons-react";
import { useState } from "react";

const AddTask: React.FC = () => {
  const [descricao, setDescricao] = useState<string>("");

  const handleAddTask = async () => {
    const url: string = "http://localhost:8080/tasks";
    const requestOptions: RequestInit = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ descricao }),
    };

    const response = await fetch(url, requestOptions);

    if (response.ok) {
      setDescricao("");
    }
  };
  return (
    <>
      <Box w={{ base: "100%" }}>
        <Flex justify={"center"} direction={"row"} align={"center"}>
          <TextInput
            value={descricao}
            onChange={(e) => setDescricao(e.target.value)}
            w={{ base: "50%", xs: "30%" }}
            placeholder="Descrição da tarefa"
            m={10}
          />
          <Button onClick={handleAddTask} variant="filled">
            <IconPlus />
          </Button>
        </Flex>
      </Box>
    </>
  );
};

export default AddTask;
