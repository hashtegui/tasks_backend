"use server";

import { revalidatePath } from "next/cache";

export async function addTask(prevstate: any, form: FormData) {
  try {
    const descricao = form.get("descricao") as string;

    const url: string = "http://localhost:8080/tasks";
    const requestOptions: RequestInit = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ descricao }),
    };

    const response = await fetch(url, requestOptions);

    return revalidatePath("/");
  } catch (error) {
    return { message: "Erro ao adicionar tarefa" };
  }
}
