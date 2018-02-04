import javax.imageio.ImageIO;
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
        ImageIO.write(image,"jpeg", new File(path));
    }

}
