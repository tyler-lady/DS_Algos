import java.util.*;
import java.io.*;

// This class acts a main function
public class Runner {
    // TODO: ?
    public static Graph setUpGraph(String file) throws IOException{
        try{

            BufferedReader reader = new BufferedReader(new FileReader(file));
            System.out.print(file + " loaded. ");
            String line;
            int count = 0;
            ArrayList<Node> nodes = new ArrayList<>();
            Graph graph = new Graph(nodes); //fixed runner class according to InScribe post
            while((line = reader.readLine()) != null){
                String text = line.toLowerCase();
                String[] words = text.split("\\s+"); // splits by whitespace
                if(count == 0){
                    int num = Integer.parseInt(words[0]);
                    for(int i = 0; i < num; i++){
                        nodes.add(new Node(i));
                    }
                    graph.init(num);
                    count++;
                    continue;
                }
                int source = Integer.parseInt(words[0]);
                int dest = Integer.parseInt(words[1]);
                int weight = Integer.parseInt(words[2]);
                graph.addEdge(nodes.get(source), nodes.get(dest), weight);
            }
            System.out.println("Graph was created.");
            return graph;
        } catch (IOException e){
            throw e;
        }
    }

    public static void main(String[] args) throws IOException {
        try {
            Scanner input = new Scanner(System.in);
            System.out.print("Enter file name: ");
            String file = input.nextLine();
            Graph g = setUpGraph(file);
            String answer = "y";
            while (answer.equals("y")) {
                System.out.println("Enter the starting node you'd like to find the shortest path from (or -1 to exit): ");
                int source = Integer.parseInt(input.nextLine());
                if (source == -1) {
                    System.out.println("buh bye");
                    break;
                }
                System.out.println("Enter the destination node you'd like to find the shortest path to (or -1 to exit): ");
                int dest = Integer.parseInt(input.nextLine());
                if (dest == -1) {
                    System.out.println("buh bye");
                    break;
                }
                System.out.println(g.shortestPath(source, dest));
                g.reset();
            }
            input.close();
        } catch (IOException e) {
            throw e;
        }
    }
}