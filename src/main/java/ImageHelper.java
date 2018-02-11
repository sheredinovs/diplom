import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by kadyr on 04.02.2018.
 */
public class ImageHelper {

    public List<double[][]> getBlocks(double[][] blueMAtrix){
        int rows = blueMAtrix.length / 8;
        int cols = blueMAtrix[0].length / 8;

        ArrayList<double[][]> list = new ArrayList<>(rows * cols);

        for (int indRow = 0; indRow < rows; indRow++)
            for (int indCol = 0; indCol < cols; indCol++) {
                double[][] tempBlock = new double[8][8];
                for (int i = 0; i < 8; i++)
                    System.arraycopy(blueMAtrix[indRow * 8 + i], indCol * 8, tempBlock[i], 0, 8);
                list.add(tempBlock);
            }

        return list;
    }

    public List<double[][]> convertToArray(BufferedImage image){
        int h = image.getHeight();
        int w = image.getWidth();
        double[][] resultBlue = new double[h][w];
        double[][] resultRed = new double[h][w];
        double[][] resultGreen = new double[h][w];
        System.out.println(resultBlue.length + "   " + resultBlue[0].length);
        List<double[][]> res = new ArrayList<>();
        for(int i = 0; i < h; i++)
            for(int j = 0; j < w; j++) {
                try {
                    Color pixel;
                    pixel = new Color(image.getRGB(j, i));
                    resultBlue[i][j] = pixel.getBlue();
                    resultRed[i][j] = pixel.getRed();
                    resultGreen[i][j] = pixel.getGreen();
                }catch (Exception e){
                    System.out.println(e.getMessage());;
                }
            }
        res.add(resultRed);
        res.add(resultGreen);
        res.add(resultBlue);

        return res;
    }

    public void showImageMatrix(double[][] arr){
        for(int i = 0; i < arr.length; i++){
            System.out.println(Arrays.toString(arr[i]));
        }
    }

    public void showAllBlocks(List<double[][]> blosks){
        for (double[][] matrix:blosks) {
            showImageMatrix(matrix);
            System.out.println();
        }
    }

    public BufferedImage createImage(double[][] redPart, double[][] greenPart, double[][] bluePart, BufferedImage originalImage) throws Exception {
        BufferedImage image = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(),originalImage.getType());
        int[][] newRedPart = arrayToByte(redPart);
        int[][] newGreenPart = arrayToByte(greenPart);
        int[][] newBluePart = arrayToByte(bluePart);

        for (int y = 0; y < newRedPart.length; y++) {
            for (int x = 0; x < newRedPart[0].length; x++) {
                Color pixel = null;

                pixel = new Color(newRedPart[y][x], newGreenPart[y][x], newBluePart[y][x]);
                image.setRGB(x, y, pixel.getRGB());
            }
        }

        return image;
    }
    private static int[][] arrayToByte(double[][] array) {
        int[][] res = new int[array.length][array[0].length];

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                if (array[i][j] < 0)
                    res[i][j] = 0;
                else if (array[i][j] > 255)
                    res[i][j] = 255;
                else
                    res[i][j] = (int) array[i][j];
            }
        }
        return res;
    }


}
