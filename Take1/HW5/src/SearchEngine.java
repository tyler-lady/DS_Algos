import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class SearchEngine {
    private int mode;
    private Dictionary<String, Node> nodeTable;

    // build everything bahahah
    // TODO: mode 5 = Openaddressing mode 6 = Chaining; build
    public SearchEngine(int mode) throws IOException {
        if(mode < 5 || mode > 6)
            throw new IOException();
        this.mode = mode;
        if(this.mode == 5){
            nodeTable = new HashTableOpenAddressing<String, Node>();
        }else if(this.mode == 6){
            nodeTable = new HashTableWithChaining<String, Node>();
        }
        this.buildList();
    }

    public Dictionary<String, Node> getNodeTree(){
        return this.nodeTable;
    }

    // TODO: assumes that the file exists already
    public void buildList() throws IOException {
        System.out.println("reading");
        BufferedReader reader = new BufferedReader(new FileReader("dataset.txt"));
        String url;
        while((url = reader.readLine()) != null){
            Document doc = Jsoup.connect(url).get();
            String text = doc.body().text().toLowerCase();
            if(url.equals("https://en.wikipedia.org/wiki/Kamala_Harris"))            System.out.println(text);

            // System.out.println(text);
            String[] words = text.split("\\s+"); // splits by whitespace
            int count = 0;
            for (String word : words) {
                // TODO:
                Node node = new Node(word); //create new Node (for search) from the entry
                boolean validation = this.nodeTable.containsKey(node.getKeyword()); //try to get the node from table, if null then not in table
                if(!validation){ //if not in list
                    if(url != null){
                        node.insertReference(url); //adds URL to reference list of new node
                        this.nodeTable.put(word, new Node(word)); //add the word and node to our table
                    }

                } else {
                    node = this.nodeTable.get(word); //if word is already added, get the node -> can use validation for this, since if not -1 then a valid index
                    if(!node.getReferences().contains(url))
                        node.insertReference(url); //after getting the node, add the current url to the node's ref list
                }
            }
        }
        reader.close();
        System.out.println("Finished reading through all URLs");
    }

    // TODO: return the results from one term
    public ArrayList<String> search(String term) {
        System.out.println("Searching for " + term + " using data structure mode " + mode + "...");

        //Node tmpNode = new Node(term);

        Node tmpNode = this.nodeTable.get(term);
        ArrayList<String> results = new ArrayList<>();
        //System.out.println(location);

        if(!tmpNode.equals(null)){
            System.out.println("Displaying results for " + term + ":");
            results = tmpNode.getReferences();
            int i = 0;
            while(i < results.size()){
                System.out.println("    "+ (i+1) +". " + results.get(i) + " "+ (i+1) +": ");
                i++;
            }
        }

        return results; //return the nodes list of references, or empty
    }

    public static void main(String[] args) throws IOException{
        Scanner input = new Scanner(System.in);

        System.out.println("Enter mode as in what data structure to use:");
        System.out.println("    5. HashTableOpenAddressing ");
        System.out.println("    6. HashTableWithChaining");

        int mode = input.nextInt();

        System.out.println("Building Search Engine...");
        SearchEngine engine = new SearchEngine(mode);

        String answer = "y";
        while (answer.equals("y")) {
            input.nextLine(); // consume the remaining newline character
            System.out.print("Search (enter a term to query): ");
            String term = input.nextLine();
            engine.search(term);
            System.out.print("Would you like to search another term (y/n)? ");
            answer = input.nextLine();
        }
        input.close();
    }
}