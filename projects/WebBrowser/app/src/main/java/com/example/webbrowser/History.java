package com.example.webbrowser;

public class History {
    private HistoryNode head = new History.HistoryNode();;
    private int current;
    private int count;

    public String getUrl(){
        // go to node we want
        HistoryNode node = head;
        for( int i = 0;i < current; i++){
            node = node.forward;
        }
        return node.url;
    }

    public String getBack(){
        // checks if there is a back node
        if (current < 2){
            return "";
        }

        // gets to current node
        HistoryNode node = head;
        for( int i = 0; i < current; i++){
            node = node.forward;
        }

        // tells current where we are going and returns correct url
        current--;
        node = node.back;
        return node.url;
    }

    public String getForward() {
        // checks if there is a forward node
        if (current + 1 > count){
            return "";
        }

        // gets to current node
        HistoryNode node = head;
        for( int i = 0; i < current; i++){
            node = node.forward;
        }

        // tells current where were going and returns correct url
        current++;
        node = node.forward;
        return node.url;
    }

    public void add(String url){
        HistoryNode newNode = new HistoryNode(); // make a new node
        newNode.url = url; // set new node url to the url passed in
        if (head == null){ // if the head is null, set the head = to new node, initialize current
            head = newNode;
            current = 0;
        }
        else {
            HistoryNode node = head; // set a new node = to head
            for( int i = 0; i < current; i++){ // iterate through all nodes till your at current
                node = node.forward;
            }
            current++; // add one to current because we are going forward
            count = current; // set count = current because if we are adding in the middle of history all the ones that where following are deleted

            newNode.back = node; // set back reference for added node to the last node
            node.forward = newNode; // set forward reference for last node to added node
        }
    }

    public static class HistoryNode {
        private String url;
        private HistoryNode back;
        private HistoryNode forward;
    }
}
