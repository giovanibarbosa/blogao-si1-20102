package classes;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.SystemColor;
import java.awt.Toolkit;
  
import ourExceptions.ArgumentInvalidException;

/**
 * Classe que manipula imagens, foi usado Canvas como biblioteca pela
 * facilidade e portabilidade para dispositivos portateis..
 * @author Tiago
 *
 */

public class Imagem extends Canvas {  
   
     private Image imagem;  
     private int altura,  largura;  
     /**
      * Contrutor da classe, onde passamos apenas a url da imagem como parametro.
      * @param imageName
      * @throws InterruptedException
      */
     public Imagem(String imageName) throws InterruptedException {  
         this(imageName, 0, 0);  
     }  
     /**
      * Contrutor onde passamos como parametro altura, largura e a imagem.
      * @param imageName
      * @param width
      * @param height
      * @throws InterruptedException
      */
     public Imagem(String imageName, int width, int height) throws InterruptedException {  
         imagem = Toolkit.getDefaultToolkit().getImage(imageName);  
         MediaTracker tracker = new MediaTracker(this);  
         tracker.addImage(imagem, 0);  
         tracker.waitForID(0);//esse metodo lanca uma exception do tipo Interrupted  

         largura = (width == 0 ? imagem.getWidth(this) : width);  
         altura = (height == 0 ? imagem.getHeight(this) : height);  
         setBackground(SystemColor.control);  
         setSize(largura, altura);  
     }  
     /**
      * Metodo que printa a imagem. 
      */
     public void paint(Graphics g) {  
         g.drawImage(imagem, 0, 0, largura, altura, this);  
     }  
} 