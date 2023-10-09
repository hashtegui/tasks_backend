"use server";

import { ITask } from "@/types/Task";
import { revalidatePath } from "next/cache";

export const iniciarTask = async (task: ITask) => {
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
    revalidatePath("/");
  }
};
