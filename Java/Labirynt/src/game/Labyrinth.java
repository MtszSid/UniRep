package game;

import java.util.*;

public class Labyrinth {
    private Chamber[][] dungeons;
    private int width = 10;
    private int height = 10;

    public int getWidth() {
        return width;
    }
    public int getHeight(){
        return height;
    }

    public void setWidth(int width) {
        setSize(this.height, width);
    }

    public void setHeight(int height) {
        setSize(height, this.width);
    }

    public Labyrinth(){
        dungeons = new Chamber[height][width];

        initialize();
        generate();
    }

    private void initialize() {
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                dungeons[i][j] = new Chamber();
            }
        }
    }

    private void reInitialize() {
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                dungeons[i][j].setExit("N", false);
                dungeons[i][j].setExit("S", false);
                dungeons[i][j].setExit("E", false);
                dungeons[i][j].setExit("W", false);
            }
        }
    }

    public void NewGame(){
        reInitialize();
        generate();
    }

    private void generate(){
        ArrayList<Pair<Integer, Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>>> weights
                = new ArrayList<>(width * height * 2);

        Map<Pair<Integer, Integer>, Integer> zoneOfChamber = new HashMap<>();
        Map<Integer, ArrayList<Pair<Integer, Integer>>> chambersInZone = new HashMap<>();

        int zone = 0;

        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                if(i < height - 1){
                    weights.add(new Pair<>((int)(10000 * Math.random()),
                                            new Pair<>(new Pair<>(i, j), new Pair<>(i + 1, j))));
                }
                if(j < width - 1){
                    weights.add(new Pair<>((int)(10000 * Math.random()),
                            new Pair<>(new Pair<>(i, j), new Pair<>(i, j + 1))));
                }
                zoneOfChamber.put(new Pair<>(i, j), zone);

                ArrayList<Pair<Integer, Integer>> a = new ArrayList<>();
                a.add(new Pair<>(i, j));
                chambersInZone.put(zone, a);

                zone++;
            }
        }

        Collections.sort(weights);

        for (Pair<Integer, Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> p : weights) {
            Pair<Integer, Integer> ch1 = p.getValue().getKey();
            Pair<Integer, Integer> ch2 = p.getValue().getValue();

            if (!Objects.equals(zoneOfChamber.get(ch1), zoneOfChamber.get(ch2))) {
                int zoneOfCh1 = zoneOfChamber.get(ch1);
                int zoneOfCh2 = zoneOfChamber.get(ch2);

                if (Objects.equals(ch1.getKey(), ch2.getKey())) {
                    dungeons[ch1.getKey()][ch1.getValue()].setExit("E", true);
                    dungeons[ch2.getKey()][ch2.getValue()].setExit("W", true);
                } else {
                    dungeons[ch1.getKey()][ch1.getValue()].setExit("S", true);
                    dungeons[ch2.getKey()][ch2.getValue()].setExit("N", true);
                }

                if (chambersInZone.get(zoneOfCh1).size() < chambersInZone.get(zoneOfCh2).size()) {
                    for (Pair<Integer, Integer> chamber : chambersInZone.get(zoneOfCh1)) {
                        zoneOfChamber.replace(chamber, zoneOfCh2);
                        chambersInZone.get(zoneOfCh2).add(chamber);
                    }
                    chambersInZone.remove(zoneOfCh1);
                } else {
                    for (Pair<Integer, Integer> chamber : chambersInZone.get(zoneOfCh2)) {
                        zoneOfChamber.replace(chamber, zoneOfCh1);
                        chambersInZone.get(zoneOfCh1).add(chamber);
                    }
                    chambersInZone.remove(zoneOfCh2);
                }
            }
        }

        dungeons[height - 1][width - 1].setExit("E", true);
    }

    public boolean isThereExit(int x, int y, String direction){
        return dungeons[y][x].isThereExit(direction);
    }

    public void setSize( int height, int width){
        this.height = height;
        this.width = width;
        this.dungeons = new Chamber[height][width];

        initialize();
        generate();
    }

    class Chamber {
        ArrayList<Pair<String, Boolean>> exits;

        public Chamber(){
            exits = new ArrayList<>(4);
            exits.add(new Pair<>("N", false));
            exits.add(new Pair<>("E", false));
            exits.add(new Pair<>("S", false));
            exits.add(new Pair<>("W", false));
        }

        public ArrayList<Pair<String, Boolean>> getExits() {
            return exits;
        }

        public boolean isThereExit(String direction){
            for (Pair<String, Boolean> exit: exits) {
                if(Objects.equals(exit.getKey(), direction)){
                    return exit.getValue();
                }
            }
            return false;
        }

        public void setExit(String direction, boolean isOpen){
            for (Pair<String, Boolean> exit: exits) {
                if(Objects.equals(exit.getKey(), direction)){
                    exit.setValue(isOpen);
                }
            }
        }
    }


}
