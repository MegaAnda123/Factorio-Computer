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
            int x = i % 16;
            int y = (int) Math.floor(i / 16) * 3;
            out.append(gameObjects.constantCombinator(x, y, new GameObjects.Filter("virtual","signal-R",lines[i])));
        }
     return out.toString();
    }

    public String getBluePrint() {
        return getBluePrint("16-BIT code", new String[]{"signal-1", "signal-6", "signal-C"});
    }

    public String getBluePrint(String name, String[] icons) {
        BluePrintEncoder bluePrintEncoder = new BluePrintEncoder(name,icons);
        return bluePrintEncoder.EncodeBlueprintMeta(getJson());
    }

}
