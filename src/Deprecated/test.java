package Deprecated;

import B16.Assembler16;
import B16.ROM16;
import Tools.*;

import java.util.ArrayList;
import java.util.List;

public class test {

    public static void main(String[] args) {
        new test();
    }
    public test() {
        ArrayList<GameObjects.Filter> filters = new ArrayList<>();
        filters.add(new GameObjects.Filter("virtual","signal-A",1));
        filters.add(new GameObjects.Filter("virtual","signal-A",1));
        filters.add(new GameObjects.Filter("virtual","signal-A",1));
        filters.add(new GameObjects.Filter("virtual","signal-A",1));
        filters.add(new GameObjects.Filter("virtual","signal-A",1));
        filters.add(new GameObjects.Filter("virtual","signal-A",1));
        filters.add(new GameObjects.Filter("virtual","signal-A",1));
        filters.add(new GameObjects.Filter("virtual","signal-A",1));
        filters.add(new GameObjects.Filter("virtual","signal-A",1));
        filters.add(new GameObjects.Filter("virtual","signal-A",1));
        filters.add(new GameObjects.Filter("virtual","signal-A",1));
        filters.add(new GameObjects.Filter("virtual","signal-A",1));
        filters.add(new GameObjects.Filter("virtual","signal-A",1));
        filters.add(new GameObjects.Filter("virtual","signal-A",1));
        filters.add(new GameObjects.Filter("virtual","signal-A",1));
        filters.add(new GameObjects.Filter("virtual","signal-A",1));
        filters.add(new GameObjects.Filter("virtual","signal-A",1));
        filters.add(new GameObjects.Filter("virtual","signal-A",1));
        filters.add(new GameObjects.Filter("virtual","signal-A",1));
        filters.add(new GameObjects.Filter("virtual","signal-A",1));
        filters.add(new GameObjects.Filter("virtual","signal-A",1));
        filters.add(new GameObjects.Filter("virtual","signal-A",1));
        filters.add(new GameObjects.Filter("virtual","signal-A",1));
        filters.add(new GameObjects.Filter("virtual","signal-A",1));

        System.out.println(filters.size());

        List<GameObjects.Filter> Temp = filters.subList(0,18);

        System.out.println(Temp.get(0).count);

        GameObjects gameObjects = new GameObjects();

        gameObjects.constantCombinator(1,2,filters,true);

        ROM16 rom16 = new ROM16();

        rom16.addLine(0,96);
        rom16.addLine(1,69);
        rom16.addLine(0xff,700);


        System.out.println(rom16.getBluePrint());
        Tools.setClipBoard(rom16.getBluePrint());

        System.out.println();

        Assembler16 assembler16 = new Assembler16();

        try {
            System.out.println(assembler16.parserLevel0("LOAD_A 0"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
