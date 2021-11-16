import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Main {
    private static int k;
    private static int l;
    private static int size;
    private static ArrayList<Integer> arrayToAdjacency;
    private static HashMap<Integer, Integer> px;
    private static HashMap<Integer, Integer> py;
    private static ArrayList<Pair<Integer, Integer>> e;
    private static ArrayList<Pair<Integer, Boolean>> vis;

    public static void main(String[] args) throws IOException {
        readFile();
        init();
        falkerson();
//        for(var i: px.values()){
//            System.out.println(i);
//        }
//        for(var i: py.values()){
//            System.out.println(i);
//        }
        writeFile();
    }

    private static void writeFile(){
        try(FileWriter writer = new FileWriter("out.txt", false))
        {
            for(var i: px.values()){
                System.out.println(i);
                if (i != -1) {
                    var a = i - k + 1;
                    writer.write(a + " ");
                }
                else writer.write(0 + " " );
            }
            writer.flush();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }

    private static boolean dfs(int x){

        if (vis.get(x).getY()) {
            return false;
        }
        vis.set(x, new Pair<>(x, true));
        for (var i: e) {
            System.out.println(x + " " + i.getX() + " " + i.getY() + py.get(i.getY()));
            if (py.get(i.getY()) == -1 & x == i.getX()) {
                py.put(i.getY(), x);
                px.put(x, i.getY());
                return true;
            }
        else
            if (py.get(i.getY()) != -1 & x == i.getX() ) {
                if (dfs(py.get(i.getY()))) {
                    py.put(i.getY(), x);
                    px.put(x, i.getY());
                    return true;
                }
            }
        }
        return false;
    }

    private static void falkerson(){
        var isPath = true;
        while (isPath) {
            isPath = false;
            for(var i = 0; i < k + l; i++) {
                vis.set(i, new Pair(i, false));
            }
            System.out.println(123);
            for(var x = 0; x < k; x++) {
                if (px.get(x) == -1) {
                    if (dfs(x)) {
                        isPath = true;
                    }
                }
            }
        }
    }

    private static void init(){
        px = new HashMap<>();
        py = new HashMap<>();
        e = new ArrayList<>();
        vis = new ArrayList<>();
        for(var i = 0; i < k; i++){
            px.put(i, -1);
            vis.add(new Pair(i, false));
        }

        for(var i = 0; i < l; i++){
            py.put(i + k, -1);
            vis.add(new Pair(i + k, false));
        }
        var li = arrayToAdjacency.get(0) - 1;
        var ai = 0;
        for(var i = 0; i < k; i++){
            ai = arrayToAdjacency.get(i + 1) - 1;
            var t = i;
            while (ai == -1) {
                ai = arrayToAdjacency.get(t + 2) - 1;
                t++;
            }
                for (var j = li; j < ai; j++) {
                    System.out.println(arrayToAdjacency.get(j) + "asdas");
                    if (arrayToAdjacency.get(j) < 1000) {
                        e.add(new Pair(i, arrayToAdjacency.get(j) - 1 + k));

                    }
                }
            li = arrayToAdjacency.get(i + 1) - 1;
            while (li == -1) {
                li = arrayToAdjacency.get(i + 2) - 1;
                i++;
            }
        }
        for(var i : e){
            System.out.println(i.getX() + " " + i.getY());
        }

    }

    private static void readFile() throws IOException {
        var br = new BufferedReader(new FileReader("in.txt"));
        arrayToAdjacency = new ArrayList<>();
        try {
            var line = br.readLine();
            var a = line.split("\n")[0];
            k = Integer.parseInt(a.split(" ")[0]);
            l = Integer.parseInt(a.split(" ")[1]);
            size=Integer.parseInt(br.readLine().split("\n")[0]);
            line = br.readLine();
            while (line != null) {
                line = line.split("\n")[0];
                for(var i: line.split(" ")){
                    arrayToAdjacency.add(Integer.parseInt(i));
                }
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            br.close();
        }
//        System.out.println(k);
//        System.out.println(l);
//        System.out.println(size);
        for (var i:
                arrayToAdjacency) {
            System.out.println(i);
        }
    }
}
