package ks;

import java.io.BufferedReader;
import java.io.File;

import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

public class Reader {
    int number;
    int bag;
    List<Item> items = new ArrayList<>();
    private String fileName;
    
    public void read(String fileName) {
        int i = 0;
        this.fileName = fileName;
        try{
        	//System.out.println(fileName);
            BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
            String line = reader.readLine();
            number = Integer.parseInt(line.substring(0, line.indexOf(" ")));
            bag = Integer.parseInt(line.substring(line.indexOf(" ")+1));
            while(line!=null){
                line = reader.readLine();
                if(line==null) break;
                String value = line.substring(0, line.indexOf(" "));
                String weigth = line.substring(line.indexOf(" ")+1);
                items.add(new Item(i, Integer.parseInt(value), Integer.parseInt(weigth)));
                i++;
            }
            reader.close();
        }catch (IOException e){
            System.out.println("ERROR: MailListReader::read(IO)"+ e.getMessage());
        }
        
    }
    
    public List<Item> getItems() {
        return items;
    }
    
    public int getBag() {
        return bag;
    }
    
    public int getNumber() {
        return number;
    }
    
    public void clear() {
    	this.number = 0;
    	this.bag = 0;
    	this.items.clear();
    }
    
    @Override
    public String toString() {
        return fileName;
    }
}
