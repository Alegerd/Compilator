package study.alegerd;

import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        String data = "";

        // чтение из файла
        try(FileReader reader = new FileReader("src/study/alegerd/input.txt"))
        {
            // читаем посимвольно
            int c;
            while((c=reader.read())!=-1){

                data+=(char)c;
            }
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }

        Parcer parcer = new Parcer();

        try {
            Node root = parcer.acceptData(data);
            Drawer dr = new Drawer();
            dr.drawTree(root, 0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
