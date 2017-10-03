package study.alegerd;

import java.util.ArrayList;
import java.util.Arrays;

enum Tokens {ID, ADDITION, MULT, GROUP}

public class Parcer {

    public Parcer() {

    }

    public Node acceptData(String data) throws Exception{
        data = data.replace(" ", "");
        data = data.replace("\n", "");

        char[] array = data.toCharArray();

        Node root = null;
        System.out.println(data);

        root = result(array);

        return root;
    }

    private Node result(char[] data) throws Exception {
        Node res = null;

        return add(data);
    }

    private Node add(char[] data) throws Exception {
        Node res = null;

        ArrayList<Node> subnodes = new ArrayList<>();

        int i = 0;
        boolean finish = false;
        String nodeData = null;

        int skip = 0;

        while (i < data.length && !finish) {

            if(data[i] == '(')
            {
                skip++;
            }
            else if(data[i] == ')')
            {
                skip--;
                if (skip<0) throw new Exception("Неверный порядок скобок");
            }
            else if ((data[i] == '+' || data[i] == '-')&&(skip == 0)) {

                char[] left = Arrays.copyOfRange(data, 0, i);
                char[] right = Arrays.copyOfRange(data, i+1, data.length);


                subnodes.add(mult(left));
                subnodes.add(add(right));
                nodeData = String.valueOf(data[i]);
                finish = true;
            }
            i++;
        }
        if(skip > 0){
            throw new Exception("Незакрытая скобка");
        }
        else if(!subnodes.isEmpty())
        {
            res = new Node(Tokens.ADDITION, subnodes, nodeData);
            return res;
        }
        return mult(data);
    }

    private Node mult(char[] data) throws Exception {
        Node res = null;

        ArrayList<Node> subnodes = new ArrayList<>();

        int i = 0;
        boolean finish = false;
        String nodeData = null;
        int skip = 0;

        while (i < data.length && !finish) {

            if(data[i] == '(')
            {
                skip++;
            }
            else if(data[i] == ')')
            {
                skip--;
                if (skip<0) throw new Exception("Неверный порядок скобок");
            }
            else if ((data[i] == '*' || data[i] == '/')&&(skip == 0)) {

                char[] left = Arrays.copyOfRange(data, 0, i);
                char[] right = Arrays.copyOfRange(data, i+1, data.length);


                subnodes.add(group(left));
                subnodes.add(mult(right));
                nodeData = String.valueOf(data[i]);
                finish = true;
            }
            i++;
        }
        if(skip > 0){
            throw new Exception("Незакрытая скобка");
        }
        if(!subnodes.isEmpty())
        {
            res = new Node(Tokens.MULT, subnodes, nodeData);
            return res;
        }
        return group(data);
    }

    private Node group(char[] data) throws Exception {
        Node res = null;

        int i = 0;
        boolean finish = false;
        int skip = 0;
        boolean parExist = false;

        while (i < data.length) {

            if(data[i] == '(')
            {
                skip++;
                parExist = true;
            }
            else if(data[i] == ')')
            {
                skip--;
                if (skip<0) throw new Exception("Неверный порядок скобок");
            }

            if(skip==0 && parExist){
                if(data[data.length-1] != ')')
                {
                    throw new Exception("Проверьте введенные данные.");
                }
                char[] array = Arrays.copyOfRange(data, 1, data.length - 1);
                if(array.length > 0){
                    return add(array);
                }
                else throw new Exception("Незначащие скобки.");
            }
            i++;
        }

        if (skip > 0) throw new Exception("Незакрытая скобка");   ///все уже отловлено выше -_-

        return var(data);
    }

    private Node number(char[] data) throws Exception {
        Node res = null;

        for (char item: data) {
            if(!Character.isDigit(item)){
                throw new Exception();          //кинуть нормальный Эксепшен
            }
        }
        return new Node(Tokens.ID, null, String.valueOf(data));
    }

    private Node var(char[] data) throws Exception {
        Node res = null;

        if(data.length==0){
            throw new Exception("Проверьте ввод");
        }

        return new Node(Tokens.ID, null, String.valueOf(data));
    }




}
