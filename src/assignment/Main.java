package assignment;

public class Main {

    public static void main(String[] args) {
        new NamePrinter().printNames();
    }
}

class NamePrinter {
    /**
     * Prints the names of the group members separated by ;.
     */
    public void printNames() {
        String separator = ";";

        String[] names = {
                "Zhiming Xie Happy yoyo",
                "Harish Kaza",
                "Supriya Adhanki",
                "Cedrick Munongo"};

        System.out.println(String.join(separator, names));
    }
}