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

                int a = (p >> 24) & 0xff;
                int r = (p >> 16) & 0xff;
                int g = (p >> 8) & 0xff;
                int b = p & 0xff;

                newImage.setRGB(x, y, b);
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

}
