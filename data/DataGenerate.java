import edu.princeton.cs.algs4.*;

public class DataGenerate {
    public static void main(String[] args) {
        int N = (int)(StdRandom.random() * 100 + 1);
        for (int i = 0; i < N; i++)
            System.out.print((int)(StdRandom.random() * 200) + " ");
    }
}