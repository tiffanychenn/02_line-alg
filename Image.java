import java.util.*;
import java.io.*;

public class Image {

    private static BufferedWriter w;
    private static ArrayList<ArrayList<Integer>> coloredpixels = new ArrayList<ArrayList<Integer>>();

    public static void main(String[] args){
        makeImage();
        drawline(250, 100, 250, 400);
        drawline(400, 250, 100, 250);
        fillImage();
    }

    private static void makeImage(){
        try {
            File f = new File("image.ppm");
            FileWriter photo = new FileWriter(f);
            w = new BufferedWriter(photo);
            w.write("P3\n500 500\n255\n");
        }
        catch (Exception e) {
            System.out.println("File doesn't exist.");
        }
    }

    private static void fillImage(){
        try {
            String background = "51 101 138 ";
            String line = "134 187 216 ";
            for (int i = 0; i < 500; i ++){
                for (int j = 0; j < 500; j ++){
                    ArrayList<Integer> temp = new ArrayList<Integer>();
                    temp.add(i);
                    temp.add(j);
                    if (coloredpixels.contains(temp)){
                        w.write(line);
                        //System.out.println("line!");
                    }
                    else {
                        w.write(background);
                    }
                }
                w.newLine();
            }
            w.close();
        }
        catch (Exception e){
            System.out.println("why am i doing this in java");
        }
    }

    private static void drawline(int x0, int y0, int x1, int y1){
        if (x0 > x1){
            int temp = x1;
            x1 = x0;
            x0 = temp;
            temp = y1;
            y1 = y0;
            y0 = temp;
        }
        if (x0 == x1){
            vertical(x0, y0, y1);
        }
        else if (y0 == y1){
            horizontal(x0, x1, y0);
        }
        //slope = 
    }

    private static void horizontal(int x0, int x1, int y){
        if (x0 > x1){
            int temp = x0;
            x0 = x1;
            x1 = temp;
        }
        for (int i = x0; i <= x1; i ++){
            ArrayList<Integer> temp = new ArrayList<Integer>();
            temp.add(i);
            temp.add(y);
            coloredpixels.add(temp);
        }
    }

    private static void vertical(int x, int y0, int y1){
        if (y0 > y1){
            int temp = y0;
            y0 = y1;
            y1 = temp;
        }
        for (int i = y0; i <= y1; i ++){
            ArrayList<Integer> temp = new ArrayList<Integer>();
            temp.add(x);
            temp.add(i);
            coloredpixels.add(temp);
        }
    }

    private static void slope1(int x0, int y0, int x1, int y1){

    }

    private static void slopenegative1(int x0, int y0, int x1, int y1){

    }

    private static void octant1(int x0, int y0, int x1, int y1){
        
    }

    private static void octant2(int x0, int y0, int x1, int y1){
        
    }

    private static void octant7(int x0, int y0, int x1, int y1){
        
    }

    private static void octant8(int x0, int y0, int x1, int y1){
        
    }

}