import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kadyr on 04.02.2018.
 */
public class Main {
    private static final ImageHelper imageHelper = new ImageHelper();
    private static final ImageLoader imageLoader = new ImageLoader();
    private static final DcpUtils dctUtil = new DcpUtils();

    public static void main(String[] args) throws IOException {
        BufferedImage image = imageLoader.loadImage("/Users/kadyr/Desktop/im1.jpg");
        BufferedImage result = imageHelper.getBlueMatrix(image);
        imageLoader.writeImage(result, "/Users/kadyr/Desktop/im6_b.jpg");

        double[][] arr = imageHelper.convertToArray(image);

        List<double[][]> blosks = imageHelper.getBlocks(arr);
        //imageHelper.showAllBlocks(blosks);
        List<double[][]> dctBlocks = new ArrayList<>();
        for(double[][] mass : blosks){
            dctBlocks.add(dctUtil.dcp(mass));
        }
        imageHelper.showAllBlocks(dctBlocks);

        System.out.println(dctBlocks.get(0)[4][5]);
        System.out.println(dctBlocks.get(0)[5][4]);


    }
}
