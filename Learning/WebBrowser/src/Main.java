public class Main {
    public static void main(String[] args){
        History newHistory = new History();

        newHistory.add("google.com\n");

        System.out.println("\nadded: " + newHistory.getUrl());

        newHistory.add("usu.edu\n");

        System.out.println("\nadded: " + newHistory.getUrl());

        newHistory.getBack();

        System.out.println("getBack url: " + newHistory.getUrl());

        newHistory.getForward();

        System.out.println("getForward url: " + newHistory.getUrl());

        newHistory.printlist(newHistory.head);


    }

}
