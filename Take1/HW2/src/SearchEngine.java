import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.util.Locale;
import java.util.Scanner;

public class SearchEngine {

    private int mode;
    private List<Node> nodeList;

    // TODO: build the SearchEngine's nodelist according to mode (1 = ArrayList; 2 = SortedArrayList); build the searchEngine
    public SearchEngine(int mode) throws IOException {
        if(mode < 1 || mode >2){
            throw new IOException();
        }
        this.mode = mode;
        if(mode == 1){
            nodeList = new ArrayList<Node>();
        } else if (mode == 2) {
            nodeList = new SortedArrayList<Node>();
        }
        this.buildList();
    }

    public List<Node> getNodeList(){
        return this.nodeList;
    }

    // TODO: X Go through the dataset and then create a new Node if the word hasn't been seen before X. X Add the current URL to its references
    // if it hasn't been seen X. X If the node has been created already, add the current URL to its references X . Add the Node to the the
    // SearchEngine's nodeList
    public void buildList() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("dataset.txt"));
        String url;
        while((url = reader.readLine()) != null){
            Document doc = Jsoup.connect(url).get();
            String text = doc.body().text().toLowerCase();
            String[] words = text.split("\\s+"); // splits by whitespace
            // logic here
            for(String word: words){ //foreach loop thru words
                Node node = new Node(word, this.mode); //create new Node (for search) from the entry
                int validation = this.nodeList.search(node); //search for new node in list. if not already added, returns -1.
                if(validation == -1){ //if not in list
                    if(url != null){
                        node.insertReference(url); //adds URL to reference list of new node
                        this.nodeList.add(new Node(word, mode)); //add the word to our list
                    }

                } else {
                    node = this.nodeList.get(validation); //if word is already added, get the node -> can use validation for this, since if not -1 then a valid index
                    node.insertReference(url); //after getting the node, add the current url to the node's ref list
                }
            }
        }
        reader.close();
        System.out.println("Finished reading through all URLs");
        System.out.println(this.nodeList.size());

        //int i =0;
        //while(i<nodeList.size()){
        //    System.out.println(nodeList.get(i));
        //    i++;
        //}

    }

    //binary search O(log(n))
    // TODO: Return the node's reference list - if the term isn't found, return an empty list
    public List<String> search(String term) {
        System.out.println("Searching for " + term + " using data structure mode " + mode + "...");
        // Search logic goes here

        Node tmpNode = new Node(term, this.mode);

        int location = this.nodeList.search(tmpNode);
        System.out.println(location);

        if(location != -1){
            Node node = this.nodeList.get(location);
            System.out.println("Displaying results for " + term + ":");
            List<String> results = node.getReferences();
            int i = 0;
            while(i < results.size()){
                System.out.println("    "+ (i+1) +". " + results.get(i) + " "+ (i+1) +": ");
                i++;
            }
            return node.getReferences(); //return the nodes list of references
        } else {
            //return empty list, currently implemented to use mode
            if(mode == 1){
                return new ArrayList<>();
            } else if (mode == 2) {
                return new SortedArrayList<>();
            }
        }

        // Example code for displaying results
        System.out.println("Displaying results for " + term + ":");
        System.out.println("    1. URL 1: ");
        System.out.println("    2. URL 2: ");
        System.out.println("    3. URL 3: ");
        return null;
    }

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter mode as in what data structure to use:");
        System.out.println("    1. Array List ");
        System.out.println("    2. Sorted Array List");

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
