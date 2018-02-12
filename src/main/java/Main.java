import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class Main {
    private static final ImageHelper imageHelper = new ImageHelper();
    private static final ImageLoader imageLoader = new ImageLoader();
    private static final DcpUtils dctUtil = new DcpUtils();

    public static void main(String[] args) throws Exception {
        BufferedImage image = imageLoader.loadImage("/Users/kadyr/Desktop/1.jpg");

        System.out.println(image.getWidth());
        System.out.println(image.getHeight());
        byte[] message = new byte[28400];

        new Random().nextBytes(message);
        for(int i=0; i < message.length; i++){
            if(message[i] % 2 == 0){
                message[i] = 1;
            }
            else
                message[i] = 0;
        }


        List<double[][]> pixels = imageHelper.convertToArray(image);
        double[][] blueMatrixArray = pixels.get(2);
        double[][] redMatrixArray = pixels.get(0);
        double[][] greenMatrixArray = pixels.get(1);



        List<double[][]> blosks = imageHelper.getBlocks(blueMatrixArray);


        Chipher chipher = new Chipher();

        List<double[][]> blokcWithCoeff = new ArrayList<>();
        for(int i = 0; i < message.length; i++){
            blokcWithCoeff.add(chipher.smartInsert(blosks.get(i), message[i]));
        }


        double[][] finalBlue = setBlocks(blueMatrixArray, blokcWithCoeff);
       // System.exit(0);

        BufferedImage newImage = imageHelper.createImage(redMatrixArray, greenMatrixArray,
                finalBlue, image);
        System.out.println(newImage.getWidth());
        System.out.println(newImage.getHeight());
        imageLoader.writeImage(newImage, "/Users/kadyr/Desktop/im6_b.jpg");

        compareResult(message, getMessage());
    }


    public static List<Byte> getMessage(){
        BufferedImage image = imageLoader.loadImage("/Users/kadyr/Desktop/im6_b.jpg");

        double[][] blueMatrix = imageHelper.convertToArray(image).get(2);
        List<double[][]> blosks = imageHelper.getBlocks(blueMatrix);

        Chipher chipher = new Chipher();

        List<Byte> out = new ArrayList<>();
        for (double[][] re : blosks) {
            out.add(chipher.compute(re));
        }
        return out;
    }

    public static double[][] setBlocks(double[][] array, List<double[][]> list) {
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
            return array;
    }

    public static void compareResult(byte[] input, List<Byte> output){
        System.out.println(Arrays.toString(input));
        System.out.println(input.length);
        int err = 0;
        for(int i=0; i < input.length; i++){
            if(input[i] != output.get(i)){
                err++;
            }
        }
        System.out.println("Погрешность = " + (err * 100 / input.length));
    }
}
