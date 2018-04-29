import java.io.*;
import java.util.*;
public class TheHouseAlwaysWins
{
    static int[][] pins;
    static int[][] highestMemo;

    public static void main(String args[]) throws IOException
    {
        Scanner in = new Scanner(new File("house.dat"));

        int c = in.nextInt();
        for(int ci = 0; ci < c; ci++)
        {
            int n = in.nextInt();

            pins = new int[n][];
            highestMemo = new int[n][];
            for(int lvl = 0; lvl < n; lvl++)
            {
                pins[lvl] = new int[lvl+1];
                highestMemo[lvl] = new int[lvl+1];
                for(int col = 0; col <= lvl; col++)
                {
                    pins[lvl][col] = in.nextInt();
                    highestMemo[lvl][col] = -1;
                }
            }

            //System.out.println(highest(0,0));
            //System.out.println(highestM(0,0));
            //System.out.println(highestDP());
        }
    }

    static int highest(int lvl, int col)
    {
        if(lvl == pins.length-1)
            return pins[lvl][col];
        return pins[lvl][col] + Math.max(highest(lvl+1, col), highest(lvl+1, col+1));
    }

    static int highestM(int lvl, int col)
    {
        if(highestMemo[lvl][col] == -1)
        {
            if(lvl == pins.length-1)
                highestMemo[lvl][col] = pins[lvl][col];
            else
                highestMemo[lvl][col] = pins[lvl][col] + Math.max(highestM(lvl+1, col), highestM(lvl+1, col+1));
        }
        return highestMemo[lvl][col];
    }

    static int highestDP()
    {
        //bottom level is the base case
        for(int col = 0; col < highestMemo[highestMemo.length-1].length; col++)
            highestMemo[highestMemo.length-1][col] = pins[pins.length-1][col];

        for(int lvl = highestMemo.length-2; lvl >= 0; lvl--)
            for (int col = 0; col <= lvl; col++)
                highestMemo[lvl][col] = pins[lvl][col] + Math.max(highestMemo[lvl + 1][col], highestMemo[lvl + 1][col + 1]);

        return highestMemo[0][0];
    }
}
