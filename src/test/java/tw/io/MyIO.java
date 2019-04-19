package tw.io;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class MyIO {

    public static final  String newline = System.getProperty("line.separator");

    public static String getData(String path) {

       String  res = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(path)));

            res = reader.lines().reduce((a,b)->a+newline+b).get();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return  res;
    }
}
