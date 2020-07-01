import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

public class test {

    public static void main(String[] args) throws FileNotFoundException, InstructionInvalidException {
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


    }

}
