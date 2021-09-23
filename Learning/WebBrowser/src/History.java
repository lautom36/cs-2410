public class History {
    public HistoryNode head;
    public int current;
    private int historyNodeCount;

    public HistoryNode getHead() {
        return head;
    }

    public String getUrl(){
        // go to node we want
        HistoryNode node = head;
        for( int i = 0; i < current; i++){
            node = node.forward;
        }
        return node.url;
    }

    public String getBack(){
        // go to node we want
        HistoryNode node = head;
        current--;
        for( int i = 0; i < current; i++){
            node = node.back;
        }
        return node.url;
    }

    public String getForward() {
        // go to node we want
        HistoryNode node = head;
        for( int i = 0; i < current; i++){
            node = node.forward;
        }
        current++;
        node = node.forward;
        return node.url;
    }

    public void add(String url){
        HistoryNode newNode = new HistoryNode();
        newNode.url = url;
        if (head == null){
            head = newNode;
            current = 0;
        }
        else {
            HistoryNode node = head;
            for( int i = 0; i < current; i++){
                node = node.forward;
            }
            current++;
            newNode.back = node;
            node.forward = newNode;
//            HistoryNode node = head;
//            while ( node.forward != null){
//                node = node.forward;
//            }
//            node.forward = newNode;
        }
    }

    public void InsertAfter(HistoryNode prev_Node, String url) {

        /*1. check if the given prev_node is NULL */
        if (prev_Node == null) {
            System.out.println("The given previous node cannot be NULL ");
            return;
        }

        /* 2. allocate node
         * 3. put in the data */
        HistoryNode new_node = new HistoryNode();
        new_node.url = url;

        /* 4. Make next of new node as next of prev_node */
        new_node.forward = prev_Node.back;

        /* 5. Make the next of prev_node as new_node */
        prev_Node.forward = new_node;

        /* 6. Make prev_node as previous of new_node */
        new_node.back = prev_Node;

        /* 7. Change previous of new_node's next node */
        if (new_node.forward != null)
            new_node.forward.back = new_node;
    }

    public int getCount(){
        return historyNodeCount;
    }

    public void printlist(HistoryNode node)
    {
        HistoryNode last = null;
        System.out.println("Traversal in forward Direction");
        while (node != null) {
            System.out.print(node.url + " ");
            last = node;
            node = node.forward;
        }
//        System.out.println();
//        System.out.println("Traversal in reverse direction");
//        while (last != null) {
//            System.out.print(last.url + " ");
//            last = last.back;
//        }
    }

    public static class HistoryNode {
        public String url;
        public HistoryNode back;
        public HistoryNode forward;
    }
}


