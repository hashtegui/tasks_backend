import AddTask from "@/components/AddTask";
import { Tasks } from "@/components/Tasks";
import { ITask } from "@/types/Task";
import { Container } from "@mantine/core";

interface FetchProps {
  data: ITask[];
  message: string;
  status: string;
}

export default async function Home() {
  const response = await fetch("http://localhost:8080/tasks", {
    cache: "no-cache",
  });
  const { data } = (await response.json()) as FetchProps;

  return (
    <main>
      <Container>
        <AddTask />
        <Tasks tasks={data} />
      </Container>
    </main>
  );
}
