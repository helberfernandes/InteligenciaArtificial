package br.com.estudo.util;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;

public class ImageUtil {
	
	
	public static int[][] getPixel(BufferedImage image){
		return convertTo2DUsingGetRGB(toBinary(image, 175));
	}
	
	/**
	 * Gera um nome de arquivo baseado no nome fornecido. O arquivo terá o mesmo
	 * caminho do nome original.
	 * <p>
	 * Exemplo: Se o arquivo for c:\fish.png e a string de detalhe for "gray" a
	 * saída será c:\fish-gray.png
	 * 
	 * A extensão final sempre será png.
	 * 
	 * @param file Arquivo original
	 * @param detail String de detalhe
	 * @return O nome do arquivo.
	 */
	private static File newFile(File file, String detail) {
		return new File(file.getParentFile(), file.getName().substring(0,
				file.getName().indexOf("."))
				+ "-" + detail + ".png");
	}
   
   /**
	 * Converte a imagem para binário usando um algorítmo de threshold simples.
	 * Tudo que estiver abaixo do threshold será convertido para preto. Acima ou
	 * igual ao threshold será convertido para branco.
	 * 
	 * @param image
	 *            Imagem a ser convertida (apenas o canal r será considerado).
	 *            Use uma escala já transformada em escala de cinza para
	 *            melhores resultados.
	 * @param t
	 *            Valor do threshold.
	 */
	private static BufferedImage toBinary(BufferedImage image, int t) {
		int BLACK = Color.BLACK.getRGB();
		int WHITE = Color.WHITE.getRGB();
		BufferedImage output = new BufferedImage(image.getWidth(),
				image.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
		// Percorre a imagem definindo na saída o pixel como branco se o valor
		// na entrada for menor que o threshold, ou como preto se for maior.
		for (int y = 0; y < image.getHeight(); y++)
			for (int x = 0; x < image.getWidth(); x++) {
				Color pixel = new Color(image.getRGB(x, y));
				output.setRGB(x, y, pixel.getRed() < t ? BLACK : WHITE);
			}
		return output;
	}
   

   private static int[][] convertTo2DUsingGetRGB(BufferedImage image) {
      int width = image.getWidth();
      int height = image.getHeight();
      int[][] result = new int[height][width];

      for (int row = 0; row < height; row++) {
         for (int col = 0; col < width; col++) {
            result[row][col] = image.getRGB(col, row);
         }
      }

      return result;
   }

   
   
   
   
   private static int[][] convertTo2DWithoutUsingGetRGB(BufferedImage image) {

      final byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
      final int width = image.getWidth();
      final int height = image.getHeight();
      final boolean hasAlphaChannel = image.getAlphaRaster() != null;

      int[][] result = new int[height][width];
      if (hasAlphaChannel) {
         final int pixelLength = 4;
         for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
            int argb = 0;
            argb += (((int) pixels[pixel] & 0xff) << 24); // alpha
            argb += ((int) pixels[pixel + 1] & 0xff); // blue
            argb += (((int) pixels[pixel + 2] & 0xff) << 8); // green
            argb += (((int) pixels[pixel + 3] & 0xff) << 16); // red
            result[row][col] = argb;
            col++;
            if (col == width) {
               col = 0;
               row++;
            }
         }
      } else {
         final int pixelLength = 3;
         for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
            int argb = 0;
            argb += -16777216; // 255 alpha
            argb += ((int) pixels[pixel] & 0xff); // blue
            argb += (((int) pixels[pixel + 1] & 0xff) << 8); // green
            argb += (((int) pixels[pixel + 2] & 0xff) << 16); // red
            result[row][col] = argb;
            col++;
            if (col == width) {
               col = 0;
               row++;
            }
         }
      }

      return result;
   }

   private static String toString(long nanoSecs) {
      int minutes    = (int) (nanoSecs / 60000000000.0);
      int seconds    = (int) (nanoSecs / 1000000000.0)  - (minutes * 60);
      int millisecs  = (int) ( ((nanoSecs / 1000000000.0) - (seconds + minutes * 60)) * 1000);


      if (minutes == 0 && seconds == 0)
         return millisecs + "ms";
      else if (minutes == 0 && millisecs == 0)
         return seconds + "s";
      else if (seconds == 0 && millisecs == 0)
         return minutes + "min";
      else if (minutes == 0)
         return seconds + "s " + millisecs + "ms";
      else if (seconds == 0)
         return minutes + "min " + millisecs + "ms";
      else if (millisecs == 0)
         return minutes + "min " + seconds + "s";

      return minutes + "min " + seconds + "s " + millisecs + "ms";
   }
}
