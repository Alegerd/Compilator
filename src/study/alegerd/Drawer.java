package study.alegerd;

public class Drawer {
    public Drawer() {

    }

    public void drawTree(Node curNode, int lvl) {

        drawData(curNode, lvl);

        int i = 0;
        if(curNode.subnodes != null) {
            while (i < curNode.subnodes.size()) {
                Node node = curNode.subnodes.get(i);
                drawTree(node, lvl + 1);
                i++;
            }
        }
    }

    public void drawData(Node node, int lvl){
        for(int i = 0; i < lvl;i++) {
            System.out.print(" ");
        }
        System.out.println(node.data);
        for(int i = 0; i < lvl;i++) {
            System.out.print(" ");
        }
        if(node.subnodes != null && !node.subnodes.isEmpty()) {
            System.out.println("\\");
        }
        else System.out.println("|");

    }
}
