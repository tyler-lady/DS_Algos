public class Node implements Comparable {
    private String keyword;
    private List<String> references;

    //Constructor inits keyword and depending upon mode inits references as an ArrayList or SortedArrayList
    // TODO: given some keyword, and mode (1 = arraylist 2 = sorted arraylist) set up the Node
    public Node(String keyword, int mode){
        this.keyword = keyword;
        if(mode == 1){
            this.references = new ArrayList<>();
        } else if(mode == 2){
            this.references = new SortedArrayList<>();
        }
    }

    public String getKeyword(){
        return this.keyword;
    }

    public List<String> getReferences(){
        return this.references;
    }

    public void insertReference(String website){
        this.references.add(website);
    }


    // TODO: Compare some other Node to this Node, String compareTo is your bestfriend here; return -1 if the other object of comparison isn't a Node
    public int compareTo(Object o){
        int value = -1;
        if (o instanceof Node ){
            Node tmpNode = (Node) o;
            value = this.getKeyword().compareTo(tmpNode.getKeyword());
            //if(value == 0){
            //    value = refComparison(value, tmpNode.getReferences());
            //}
            if(value > 0){
                value = 1;
            } else if(value < 0){
                value = -1;
            }
            return value;
        }
        return value;
    }

    //HelperMethod
    public int refComparison(int value, List<String> refs){
        int index = 0;

        if(this.references.size() < refs.size()){
            return -1;
        } else if(this.references.size() > refs.size()){
            return 1;
        }
        if(this.references.size() == refs.size()){
            while(index < this.references.size()){
                if(value == 0){
                    value = this.references.get(index).compareTo(refs.get(index));
                    if(value < 0){
                        value = -1;
                    } else if (value > 0) {
                        value = 1;
                    }
                } else {
                    return value;
                }
                index++;
            }
        }
        return value;
    }

    // TODO: similar to compareTo except in boolean format and is only concerned if the other Node has the same keyword or not
    public boolean equals (Object o) {
        if (o instanceof Node) {
            Node other = (Node) o;
            return this.keyword.equals(other.keyword);
        }
        else return false;
    }

    public String toString(){
        return this.keyword + " " + this.references;
    }


}
