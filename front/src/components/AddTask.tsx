"use client";

import { addTask } from "@/actions/addTask";
import { Box, Button, Flex, TextInput } from "@mantine/core";
import { IconPlus } from "@tabler/icons-react";
import { useState } from "react";
import { experimental_useFormState as useFormState } from "react-dom";

const AddTask: React.FC = () => {
  const initialState = {
    message: "",
  };

  const [descricao, setDescricao] = useState<string>("");

  const [state, formAction] = useFormState(addTask, initialState);

  return (
    <>
      <Box component="form" action={formAction} w={{ base: "100%" }}>
        <Flex justify={"center"} direction={"row"} align={"center"}>
          <TextInput
            value={descricao}
            onChange={(e) => setDescricao(e.target.value)}
            w={{ base: "50%", xs: "30%" }}
            placeholder="Descrição da tarefa"
            name="descricao"
            m={10}
            error={state?.message}
          />
          <Button type="submit" variant="filled">
            <IconPlus />
          </Button>
        </Flex>
      </Box>
    </>
  );
};

export default AddTask;
