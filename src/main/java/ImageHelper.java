import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by kadyr on 04.02.2018.
 */
public class ImageHelper {

    public BufferedImage getBlueMatrix(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage newImage = new BufferedImage(width, height, image.getType());
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int p = image.getRGB(x, y);
                int b = p & 0xff;
                newImage.setRGB(x, y, b);
            }
        }
        return newImage;
    }
    public BufferedImage getRedMatrix(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage newImage = new BufferedImage(width, height, image.getType());
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int p = image.getRGB(x, y);
                int r = (p >> 16) & 0xff;
                newImage.setRGB(x, y, r);
            }
        }
        return newImage;
    }
    public BufferedImage getGreenMatrix(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage newImage = new BufferedImage(width, height, image.getType());
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int p = image.getRGB(x, y);
                int g = (p >> 8) & 0xff;
                newImage.setRGB(x, y, g);
            }
        }
        return newImage;
    }

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

    public double[][] convertToArray(BufferedImage image){
        double[][] result = new double[image.getWidth()][image.getHeight()];
        for(int i = 0; i < image.getWidth(); i++)
            for(int j = 0; j < image.getHeight(); j++) {
                Color pixel = new Color(image.getRGB(i, j));

                result[i][j] = pixel.getBlue();
            }
        return result;
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
        BufferedImage image = new BufferedImage(originalImage.getHeight(), originalImage.getWidth(), originalImage.getType());
        int[][] newRedPart = arrayToByte(redPart);
        int[][] newGreenPart = arrayToByte(greenPart);
        int[][] newBluePart = arrayToByte(bluePart);

        for (int y = 0; y < newRedPart.length; y++) {
            for (int x = 0; x < newRedPart[0].length; x++) {
                Color pixel = null;

                pixel = new Color(newRedPart[y][x], newGreenPart[y][x], newBluePart[y][x]);
                try {
                    image.setRGB(x, y, pixel.getRGB());
                }catch (Exception j){
                    throw new Exception("vdfv");
                }
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
