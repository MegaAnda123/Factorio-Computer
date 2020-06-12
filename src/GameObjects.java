import java.util.ArrayList;

public class GameObjects {
    int entityNumber = 1;
    ArrayList<String> list;


    /**
     * Generates json string for constant combinator.
     * Sets signal number or letter (no exception handling!).
     * @param x coordinate positive = right
     * @param y coordinate positive = down
     * @param signal signal number or letter (no exception handling!).
     * @param bool if signal is on.
     * @return return string list of json
     */
    public ArrayList<String> constantCombinator(int x, int y, String signal, boolean bool) {
        return constantCombinator(x,y,signal,1,bool);
    }

    public ArrayList<String> constantCombinator(int x, int y, String signal, int i, boolean bool) {
        list = new ArrayList<>();

        if (entityNumber != 1) {
            addStringln(",");
        }
        addStringln("{");
        addStringln("\"entity_number\": " + entityNumber + ",");
        addStringln("\"name\": \"constant-combinator\",");
        addStringln("\"position\": {");
        addStringln("\"x\": " + x + ",");
        addStringln("\"y\": " + y);
        addStringln("},");
        addStringln("\"control_behavior\": {");
        addStringln("\"filters\": [");
        addStringln("{");
        addStringln("\"signal\": {");
        addStringln("\"type\": \"virtual\",");
        addStringln("\"name\": \"signal-" + signal + "\"");
        addStringln("},");
        addStringln("\"count\": " + i + ",");
        addStringln("\"index\": 1");
        addStringln("}");
        addStringln("]");

        if (!bool) {
            addStringln(",");
            addStringln("\"is_on\": false");
        }

        addStringln("}");
        addStringln("}");

        this.entityNumber++;
        return list;
    }

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

}
