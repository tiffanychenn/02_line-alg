import java.util.*;
import java.io.*;

public class Image {

    private static BufferedWriter w;
    private static ArrayList<ArrayList<Integer>> coloredpixels = new ArrayList<ArrayList<Integer>>();

    public static void main(String[] args){
        makeImage();
        drawline(250, 250, 100, 250);
        drawline(250, 250, 400, 250);
        drawline(250, 250, 250, 400);
        drawline(250, 250, 250, 100);
        drawline(250, 250, 300, 450);
        drawline(250, 250, 200, 50);
        drawline(250, 250, 450, 300);
        drawline(250, 250, 50, 200);
        drawline(250, 250, 300, 50);
        drawline(250, 250, 200, 450);
        drawline(250, 250, 50, 300);
        drawline(250, 250, 450, 200);
        /*drawline(250, 250, 100, 250);
        drawline(250, 250, 400, 250);
        drawline(250, 250, 175, 380);
        drawline(250, 250, 175, 120);
        drawline(250, 250, 325, 380);
        drawline(250, 250, 325, 120);
        drawline(190, 146, 161, 154);
        drawline(310, 354, 339, 346);
        drawline(190, 146, 208, 122);
        drawline(370, 250, 388, 226);
        drawline(370, 250, 388, 274);
        drawline(130, 250, 112, 226);
        drawline(130, 250, 112, 274);
        drawline(310, 146, 292, 122);
        drawline(310, 354, 292, 378);
        drawline(190, 354, 208, 378);
        drawline(310, 146, 339, 154);
        drawline(190, 354, 161, 346);*/
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
                    temp.add(j);
                    temp.add(i);
                    if (coloredpixels.contains(temp)){
                        w.write(line);
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
        else {
            double slope = -1.0 * (y1 - y0) / (x1 - x0);
            System.out.println(slope);
            if (slope == 1){
                slope1(x0, y0, x1, y1);
            }
            else if (slope == -1){
                slopenegative1(x0, y0, x1, y1);
            }
            else if (slope < -1){
                octant7(x0, y0, x1, y1);
            }
            else if (slope < 0){
                octant8(x0, y0, x1, y1);
            }
            else if (slope < 1){
                octant1(x0, y0, x1, y1);
            }
            else {
                octant2(x0, y0, x1, y1);
            }
        }
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
        while (x0 <= x1){
            ArrayList<Integer> temp = new ArrayList<Integer>();
            temp.add(x0);
            temp.add(y0);
            coloredpixels.add(temp);
            x0 ++;
            y0 --;
        }
    }

    private static void slopenegative1(int x0, int y0, int x1, int y1){
        while (x0 <= x1){
            ArrayList<Integer> temp = new ArrayList<Integer>();
            temp.add(x0);
            temp.add(y0);
            coloredpixels.add(temp);
            x0 ++;
            y0 ++;
        }
    }

    private static void octant1(int x0, int y0, int x1, int y1){
        int a = y0 - y1;
        int b = -1 * (x1 - x0);
        int d = 2 * a + b;
        System.out.println(a + " " + b);
        while (x0 < x1){
            ArrayList<Integer> temp = new ArrayList<Integer>();
            temp.add(x0);
            temp.add(y0);
            coloredpixels.add(temp);
            x0 ++;
            if (d > 0){
                y0 --;
                d += 2 * b;
            }
            d += 2 * a;
        }
        return;
    }

    private static void octant2(int x0, int y0, int x1, int y1){
        int a = y0 - y1;
        int b = -1 * (x1 - x0);
        int d = a + 2 * b;
        System.out.println(a + " " + b);
        while (y0 > y1){
            ArrayList<Integer> temp = new ArrayList<Integer>();
            temp.add(x0);
            temp.add(y0);
            coloredpixels.add(temp);
            y0 --;
            if (d < 0){
                x0 ++;
                d += 2 * a;
            }
            d += 2 * b;
        }
        return;
    }

    private static void octant7(int x0, int y0, int x1, int y1){
        int a = y1 - y0;
        int b = -1 * (x1 - x0);
        int d = a - 2 * b;
        System.out.println(a + " " + b);
        while (y0 < y1){
            ArrayList<Integer> temp = new ArrayList<Integer>();
            temp.add(x0);
            temp.add(y0);
            coloredpixels.add(temp);
            y0 ++;
            if (d < 0){
                x0 ++;
                d += 2 * a;
            }
            d += 2 * b;
        }
        return;
    }

    private static void octant8(int x0, int y0, int x1, int y1){
        int a = y1 - y0;
        int b = -1 * (x1 - x0);
        int d = 2 * a - b;
        System.out.println(a + " " + b);
        while (x0 < x1){
            ArrayList<Integer> temp = new ArrayList<Integer>();
            temp.add(x0);
            temp.add(y0);
            coloredpixels.add(temp);
            x0 ++;
            if (d > 0){
                y0 ++;
                d += 2 * b;
            }
            d += 2 * a;
        }
        return;
    }

}