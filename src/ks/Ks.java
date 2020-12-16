package ks;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.martiansoftware.jsap.*;

public class Ks {

    static int bag;
    static List<Item> items;
    
    public static void main (String[] args) {
        JSAP jsaper = parsePreparation();
        JSAPResult config = jsaper.parse(args);
        if (!config.success()) {
            System.err.println();
            System.err.println("Usage: java " + Ks.class.getName());
            System.err.println("		" + jsaper.getUsage());
            System.err.println();
            System.err.println("		" + jsaper.getHelp());
            System.exit(1);
        }
        Reader reader = new Reader();
        if(config.getBoolean("greedy")){
            if (config.contains("file")) {
                reader.read(config.getString("file"));
                greedyFile(reader, config);
            }
            if (config.contains("directory")) {
                File carpeta = new File(config.getString("directory"));
                if (carpeta == null){
                    System.err.println("El directorio no existe");
                    System.exit(1);
                }
                for(File archivo : carpeta.listFiles()) {
                    System.out.println(archivo.getName());
                    reader.clear();
                    reader.read(carpeta.getName() + "/" + archivo.getName());
                    greedyFile(reader, config);
                }
            }
        }
        if(config.getBoolean("tabulation")){
            if (config.contains("file")) {
                reader.read(config.getString("file"));
                tabulationFile(reader, config);
            }
            if (config.contains("directory")) {
                File carpeta = new File(config.getString("directory"));
                if (carpeta == null){
                    System.err.println("El directorio no existe");
                    System.exit(1);
                }
                for(File archivo : carpeta.listFiles()) {
                    System.out.println(archivo.getName());
                    reader.clear();
                    reader.read(carpeta.getName() + "/" + archivo.getName());
                    tabulationFile(reader, config);
                }
            }
        }
        
        if(config.getBoolean("memoization")){
            if (config.contains("file")) {
                reader.read(config.getString("file"));
                memoizationFile(reader, config);
            }
            if (config.contains("directory")) {
                File carpeta = new File(config.getString("directory"));
                if (carpeta == null){
                    System.err.println("El directorio no existe");
                    System.exit(1);
                }
                for(File archivo : carpeta.listFiles()) {
                    System.out.println(archivo.getName());
                    reader.clear();
                    reader.read(carpeta.getName() + "/" + archivo.getName());
                    memoizationFile(reader, config);
                }
            }
        }
    }
	
    private static List<Integer> taken_Items(int[] taken, List<Item> items) {
        List<Integer> takenitems = new ArrayList<>();
            for (int i = 0; i < taken.length; i++) {
                if (taken[i] == 1) {
                    takenitems.add(i);
                }
            }
            return takenitems;
	}

    public static int calculateValue(int[] taken, List<Item> items) {
            int value = 0;
            for (int i = 0; i < taken.length; i++) {
                    if (taken[i] == 1) {
                            for (Item item:items) {
                                    if(item.getIndex() == i) value += item.getValue();
                            }
                    }
            }
            return value;
    }

    public static JSAP parsePreparation() {
            try {
                    JSAP jsaper = new JSAP();
                    jsaper.registerParameter(new FlaggedOption("directory")
                                    .setStringParser(JSAP.STRING_PARSER)
                                    .setShortFlag('d')
                                    .setLongFlag("directory")
                                    .setHelp("Directory (Process many files)"));
                    jsaper.registerParameter(new FlaggedOption("file")
                                    .setStringParser(JSAP.STRING_PARSER)
                                    .setShortFlag('f')
                                    .setLongFlag("file")
                                    .setHelp("File (Process a single file)"));
                    jsaper.registerParameter(new Switch("benefit")
                                    .setShortFlag('b')
                                    .setLongFlag("benefit")
                                    .setHelp("Display Benefit"));
                    jsaper.registerParameter(new Switch("room")
                                    .setShortFlag('r')
                                    .setLongFlag("room")
                                    .setHelp("Display room (Unused knapsack weigth)"));
                    jsaper.registerParameter(new Switch("time")
                                    .setShortFlag('t')
                                    .setLongFlag("time")
                                    .setHelp("Display time"));
                    jsaper.registerParameter(new Switch("greedy")
                                    .setLongFlag("sg")
                                    .setHelp("Solve it with Greedy"));
                    jsaper.registerParameter(new Switch("display_taken")
                                    .setLongFlag("dt")
                                    .setHelp("Display taken items"));
                    jsaper.registerParameter(new Switch("tabulation")
                                    .setLongFlag("st")
                                    .setHelp("Solve it with tabulation"));
                    jsaper.registerParameter(new Switch("memoization")
                                    .setLongFlag("sm")
                                    .setHelp("Solve it with tabulation"));

                    return jsaper;
            }catch(JSAPException ex) {
                    Logger.getLogger(Ks.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
    }

    public static void greedyFile(Reader reader, JSAPResult config) {
        bag = reader.getBag();
        items = reader.getItems();
        int[] taken = new int[bag];
        long begin = System.currentTimeMillis();
        int[] takenH = Greedy.solveGreedyHeaviest(items,bag);
        int[] takenV = Greedy.solveGreedyValue(items,bag);
        int[] takenR = Greedy.solveGreedyRelation(items,bag);
        int[] takenL = Greedy.solveGreedyLightest(items,bag);
        int value = 0;
        int valueH = calculateValue(takenH, items);
        int valueV = calculateValue(takenV, items);
        int valueR = calculateValue(takenR, items);
        int valueL = calculateValue(takenL, items);

        if(Math.max(valueH, Math.max(valueV, Math.max(valueR, valueL))) == valueH) {
                taken =  Greedy.solveGreedyHeaviest(items, bag);
                value = valueH;
                System.out.println(reader + ": Heaviest");
        }else if(Math.max(valueH, Math.max(valueV, Math.max(valueR, valueL))) == valueV) {
                taken =  Greedy.solveGreedyValue(items, bag);
                value = valueV;
                System.out.println(reader + ": Value");
        }else if(Math.max(valueH, Math.max(valueV, Math.max(valueR, valueL))) == valueL) {
                taken =  Greedy.solveGreedyLightest(items, bag);
                value = valueL;
                System.out.println(reader + ": Lightest");
        }else {
                taken =  Greedy.solveGreedyRelation(items, bag);
                value = valueR;
                System.out.println(reader + ": Relation");
        }
        long end = System.currentTimeMillis();

        List<Integer> takenitems = taken_Items(taken, reader.items);

        if (config.getBoolean("benefit")) {
            System.out.println(value);
        }

        if (config.getBoolean("room")) {
            int weights = 0;
            for (int i = 0; i < taken.length; i++) {
                if (taken[i] == 1) {
                    for (Item item:items) {
                        if(item.getIndex() == i) weights += item.getWeight();
                    }
                }
            }
            System.out.println(reader.bag - weights);
        }

        if (config.getBoolean("time")) {
            System.out.println(end - begin);
        }

        if (config.getBoolean("display_taken")) {
            System.out.println(takenitems);
        }
    }
    public static void toString(int[] taken) {
        for (int obj : taken) {
            System.out.print(obj+" ");
        }
        System.out.print("\n");		
    }
    public static void toString(List<Item> items) {
        for (Item obj : items) {
            System.out.print(obj.getIndex()+" ");
        }
        System.out.print("\n");
    }
    public static void toString1(List<Integer> items) {
        for (int i: items) {
            System.out.print(i+" ");
        }
        System.out.print("\n");
    }

    private static void tabulationFile(Reader reader, JSAPResult config) {
        bag = reader.getBag();
        items = reader.getItems();
        long begin = System.currentTimeMillis();
        int[] taken = Tabulation.tabulation(items, bag);
        int value = calculateValue(taken, items);
        long end = System.currentTimeMillis();

        List<Integer> takenitems = taken_Items(taken, reader.items);

        if (config.getBoolean("benefit")) {
                System.out.println(value);
        }

        if (config.getBoolean("room")) {
            int weights = 0;
            for (int i = 0; i < taken.length; i++) {
                if (taken[i] == 1) {
                    for (Item item:items) {
                        if(item.getIndex() == i) weights += item.getWeight();
                    }
                }
            }
            System.out.println(reader.bag - weights);
        }

        if (config.getBoolean("time")) {
            System.out.println(end - begin);
        }

        if (config.getBoolean("display_taken")) {
            System.out.println(takenitems);
        }
    }
    
    private static void memoizationFile(Reader reader, JSAPResult config) {
        bag = reader.getBag();
        items = reader.getItems();
        long begin = System.currentTimeMillis();
        int[] taken = Memoization.memoization(items, bag);
        int value = calculateValue(taken, items);
        long end = System.currentTimeMillis();

        List<Integer> takenitems = taken_Items(taken, reader.items);

        if (config.getBoolean("benefit")) {
                System.out.println(value);
        }

        if (config.getBoolean("room")) {
            int weights = 0;
            for (int i = 0; i < taken.length; i++) {
                if (taken[i] == 1) {
                    for (Item item:items) {
                        if(item.getIndex() == i) weights += item.getWeight();
                    }
                }
            }
            System.out.println(reader.bag - weights);
        }

        if (config.getBoolean("time")) {
            System.out.println(end - begin);
        }

        if (config.getBoolean("display_taken")) {
            System.out.println(takenitems);
        }
    }
}
