import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.FileImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by kadyr on 04.02.2018.
 */
public class ImageLoader {
    public BufferedImage loadImage(String path){
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            System.out.println("file not found");
        }
        return image;
    }

    public void writeImage(BufferedImage image, String path) throws IOException {
       // ImageIO.write(image,"jpeg", new File(path));

        final ImageWriter writer = ImageIO.getImageWritersByFormatName("jpg").next();
        JPEGImageWriteParam jpegParams = new JPEGImageWriteParam(null);
        jpegParams.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        jpegParams.setCompressionQuality(1f);
        try {
            writer.setOutput(new FileImageOutputStream(new File(path)));
            writer.write(null, new IIOImage(image, null, null), jpegParams);
        } catch (Exception e) {
            System.out.println("Save error");
        }
        writer.dispose();
    }

}
