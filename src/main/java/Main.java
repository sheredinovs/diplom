import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by kadyr on 04.02.2018.
 */
public class Main {
    private static final ImageHelper imageHelper = new ImageHelper();
    private static final ImageLoader imageLoader = new ImageLoader();
    private static final DcpUtils dctUtil = new DcpUtils();

    public static void main(String[] args) throws Exception {
        BufferedImage image = imageLoader.loadImage("/Users/kadyr/Desktop/im1.jpg");
        byte[] message = new byte[]{0, 1, 1, 1, 0, 0, 1,0};

        BufferedImage blueMatrix = imageHelper.getBlueMatrix(image);
        BufferedImage redMatrix = imageHelper.getRedMatrix(image);
        BufferedImage greenMatrix = imageHelper.getGreenMatrix(image);


      //  imageLoader.writeImage(blueMatrix, "/Users/kadyr/Desktop/im6_b.jpg");

        double[][] arr = imageHelper.convertToArray(image);

        List<double[][]> blosks = imageHelper.getBlocks(arr);
        //imageHelper.showAllBlocks(blosks);
        List<double[][]> dctBlocks = new ArrayList<>();
        for(double[][] mass : blosks){
            dctBlocks.add(dctUtil.dcp(mass));
        }
        imageHelper.showAllBlocks(dctBlocks);

        Chipher chipher = new Chipher();
        for(double[][] mass : dctBlocks){
            chipher.compute(mass);
        }

        for(int i = 0; i < message.length; i++){
            chipher.chipher(dctBlocks.get(i), message[i]);
        }

        List<double[][]> res = new ArrayList<>();
        for(double[][] mass : dctBlocks){
            res.add(dctUtil.idcp(mass));
        }


        List<Byte> out = new ArrayList<>();
        for (double[][] re : res) {
            out.add(chipher.compute(re));
        }

        setBlocks(arr, res);

        BufferedImage newImage = imageHelper.createImage(imageHelper.convertToArray(redMatrix), imageHelper.convertToArray(greenMatrix),
                imageHelper.convertToArray(blueMatrix), image);
        imageLoader.writeImage(newImage, "/Users/kadyr/Desktop/im6_b.jpg");
        System.out.println(dctBlocks.get(0)[4][5]);
        System.out.println(dctBlocks.get(0)[5][4]);


        compareResult(message, out);
    }


    public static void setBlocks(double[][] array, java.util.List<double[][]> list) {
        int rows = array.length / 8;
        int cols = array[0].length / 8;
        int indBlock = 0;

        for (int indRow = 0; indRow < rows; indRow++)
            for (int indCol = 0; indCol < cols; indCol++) {
                double[][] tempBlock = list.get(indBlock);
                for (int i = 0; i < 8; i++)
                    for (int j = 0; j < 8; j++) {
                        array[indRow * 8 + i][indCol * 8 + j] = tempBlock[i][j];
                    }
                indBlock++;
            }
    }

    public static void compareResult(byte[] input, List<Byte> output){
        System.out.println(Arrays.toString(input));
        System.out.println(input.length);
        int err = 0;
        for(byte i=0; i < input.length; i++){
            if(input[i] != output.get(i)){
                err++;
            }
        }
        System.out.println("Погрешность = " + (err * 100 / input.length));
    }
}
