import java.util.ArrayList;

public class ROM16 {
    private int[] lines = new int[256];


    public ROM16() {}

    public void addLine(int line, int value) {
        lines[line] = value;
    }

    public void clearROM() {
        lines = new int[256];
    }

    public String getJson() {
        GameObjects gameObjects = new GameObjects();
        StringBuilder out = new StringBuilder();

        for(int i = 0; i<lines.length; i++) {
            //if(lines[i]!=0) {
                int x = i % 16;
                int y = (int) Math.floor(i / 16) * 3;
                //TODO fix trash string list issue!!
                ArrayList<String> temp = gameObjects.constantCombinator(x, y, "R", lines[i], true);
                for(String string : temp) {
                    out.append(string);
                }


            //}
        }
     return out.toString();
    }

}
