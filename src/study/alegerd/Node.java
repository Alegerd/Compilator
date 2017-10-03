package study.alegerd;

import java.util.ArrayList;

public class Node {

    public Tokens state;

    public String data;

    public ArrayList<Node> subnodes = new ArrayList<>();

    public Node(Tokens token){
        state = token;
    }

    public Node(Tokens token, ArrayList<Node> subnodes, String data){
        this.subnodes = subnodes;
        this.data = data;
    }
}
