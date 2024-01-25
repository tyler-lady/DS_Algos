import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class SearchEngine {
    private int mode;
    private Tree<Node> nodeTree; // List -> Tree

    // build everything bahahah
    // TODO: mode 3 = BST mode 4 = AVL
    public SearchEngine(int mode) throws IOException {
        if(mode < 3 || mode > 4){
            throw new IOException();
        }
        this.mode = mode;
        if(mode == 3){
            nodeTree = new BST<>();
        }else if(mode == 4){
            nodeTree = new AVL<>();
        }
        this.buildList();
    }

    public Tree<Node> getNodeTree(){
        return this.nodeTree;
    }

    // assumes that the file exists already
    // TODO: tweak logic so that it builds the proper tree
    public void buildList() throws IOException {
        System.out.println("reading");
        BufferedReader reader = new BufferedReader(new FileReader("dataset.txt"));
        String url;
        while((url = reader.readLine()) != null){
            Document doc = Jsoup.connect(url).get();
            String text = doc.body().text().toLowerCase();
            String[] words = text.split("\\s+"); // splits by whitespace
            for (String word : words) {
                // HERE
                //create node for word
                System.out.println(word);
                Node node = new Node(word);
                //search for the node, if returned value is null, word is not in tree
                BinaryNode validation = this.nodeTree.search(node); //search for new node in tree. if not already added, returns null.
                if(validation == null){ //if not in list
                    if(url != null){
                        node.insertReference(url); //adds URL to reference list of new node
                        this.nodeTree.insert(node); //add the node (w/ its keyword and references list) to our tree
                    }

                } else {
                    node = (Node) validation.data(); //if word is already added, get the node -> can use validation for this, since if not -1 then a valid index
                    if(!node.getReferences().contains(url))
                        node.insertReference(url); //after getting the node, add the current url to the node's ref list
                }
            }
        }
        reader.close();
        System.out.println("Finished reading through all URLs");
    }

    // return a List ew ._.
    // TODO: Return the reference list of URLs
    public ArrayList<String> search(String term) {
        System.out.println("Searching for " + term + " using data structure mode " + mode + "...");
        // Search logic goes here

        Node tmpNode = new Node(term);

        tmpNode = this.nodeTree.search(tmpNode).data();
        ArrayList<String> results = new ArrayList<>();

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