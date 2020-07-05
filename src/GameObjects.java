import java.util.ArrayList;
import java.util.List;

public class GameObjects {
    int entityNumber = 1;
    ArrayList<String> list;


    /**
     * Compatibility method for old methods TODO remove
     * Generates json string for constant combinator.
     * Sets signal number or letter (no exception handling!).
     * @param x coordinate positive = right
     * @param y coordinate positive = down
     * @param signal signal number or letter (no exception handling!).
     * @param bool if signal is on.
     * @return return string list of json
     */
    public ArrayList<String> constantCombinator(int x, int y, String signal, boolean bool) {
        ArrayList<Filter> filters = new ArrayList<>();
        filters.add(new Filter("virtual",signal,1));
        ArrayList<String> temp = new ArrayList<>();
        temp.add(constantCombinator(x,y,filters,bool));
        return temp;
    }

    /**
     * Generates json string for constant combinator using a single filter input.
     * @param x coordinate positive = right
     * @param y coordinate positive = down
     * @param filter Single filter object with filter values: type, name, count.
     * @return json string for constant combinator.
     */
    public String constantCombinator(int x, int y, Filter filter) {
        ArrayList<Filter> filters = new ArrayList<>();
        filters.add(filter);
        return constantCombinator(x,y,filters,true);
    }

    /**
     * Generates json string for constant combinator.
     * @param x coordinate positive = right
     * @param y coordinate positive = down
     * @param filters filter array list containing filter object with filter values: type, name, count.
     * @param isOn is constant combinator turned on.
     * @return json string for constant combinator.
     */
    public String constantCombinator(int x, int y, ArrayList<Filter> filters, boolean isOn) {
        StringBuilder stringBuilder = new StringBuilder();

        String out = "";
        if (entityNumber != 1) {
            out += ",";
        }

        out += Tools.readFromFile("Combinators\\constantCombinator.txt");
        String filter = Tools.readFromFile("Combinators\\filter.txt");

        out = out.replaceFirst("@entity_number",String.valueOf(entityNumber));
        out = out.replaceFirst("@x",String.valueOf(x));
        out = out.replaceFirst("@y",String.valueOf(y));
        if (isOn) {
            out = out.replaceFirst("@is_on","true");
        } else {
            out = out.replaceFirst("@is_on","false");
        }

        List<Filter> list = filters;
        if(list.size()>18) {
            list = filters.subList(0, 18);  //Cutoff elements above index 17.
        }

        for(int i=0; i<list.size(); i++) {
            String temp = filter;
            temp = temp.replaceFirst("@type",list.get(i).type);
            temp = temp.replaceFirst("@name",list.get(i).name);
            temp = temp.replaceFirst("@count",String.valueOf(list.get(i).count));
            temp = temp.replaceFirst("@index",String.valueOf(i+1));
            stringBuilder.append(temp);
            if(i+1 != list.size()) {stringBuilder.append(",");}
        }
        out = out.replaceFirst("@filters",stringBuilder.toString());

        this.entityNumber++;
        return out;
    }

    //TODO refactor...
    public ArrayList<String> deciderCombinator(float x, float y, int i, String signal, String outSignal, boolean count) {
        list = new ArrayList<>();

        if (entityNumber != 1) {
            addStringln(",");
        }
        addStringln("{\"entity_number\":" + entityNumber + ",\"name\":\"decider-combinator\",");
        addStringln("\"position\":{\"x\":" + x + ",\"y\":" + y + "},\"direction\":4");
        addStringln(",\"control_behavior\":{\"decider_conditions\":{\"first_signal\":{\"type\":\"virtual\",\"name\":\"signal-" + signal + "\"}");
        addStringln(",\"constant\":" + i + ",\"comparator\":\"=\",");
        addStringln("\"output_signal\":{\"type\":\"virtual\",\"name\":\"signal-" + outSignal + "\"},\"copy_count_from_input\":" + count + "}}}");

        this.entityNumber++;
        return list;
    }

    public void addStringln(String string) {
        list.add((string + "\n"));
    }

    //TODO add valid filter check?
    /**
     * Filter class for object to hold filter values
     */
    public static class Filter {
        String type;
        String name;
        int  count;

        public Filter(String type, String name, int count) {
            this.type = type;
            this.name = name;
            this.count = count;
        }
    }

}


