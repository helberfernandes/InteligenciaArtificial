
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;

import br.com.estudo.util.ImageUtil;

public class PerformanceTest {

   public static void main(String[] args) throws IOException {

//      BufferedImage hugeImage = ImageIO.read(PerformanceTest.class.getResource("/imagens/zero.png"));
//     
//	 
//      
//     int [][] result = ImageUtil.getPixel(hugeImage);
//      
//      
//     System.out.println("Tamanho "+hugeImage.getHeight()+" x "+hugeImage.getWidth());
//      for(int y=0;y<hugeImage.getHeight();y++){
//     	 for(int x=0;x<hugeImage.getWidth();x++){
//         	 System.out.print(result[y][x]);
//          }
//     	 System.out.println();
//      }
   
      System.out.println("n1 "+Math.tanh(18));
      System.out.println("n2 "+Math.tanh(24));
      System.out.println("n3 "+Math.tanh(6));
      
    
   }
  
}