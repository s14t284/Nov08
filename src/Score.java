import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JPanel;


public class Score{

	
	private int Width = 192;
	private int Height = 64;
	
	private int x;
	private int y;
	

	
	public Score(){	
		x = 20;
		y = 2;
	}
	
	public void Draw(Graphics g){
		  Graphics2D g2 = (Graphics2D)g;
		  Font Matrs = createFont("font/ka1.ttf");
		  Matrs = Matrs.deriveFont(12.0f);
		  
		  g2.setColor(Color.RED);
		  g2.setFont(Matrs);
		  g2.drawString("SCORE:00000", Map.TilesToPixels(x),Map.TilesToPixels(y));
	}

	
	private Font createFont(String filename) {
        Font font = null;
        InputStream is = null;

        try {
            is = getClass().getResourceAsStream(filename);
            font = Font.createFont(Font.TRUETYPE_FONT, is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
            e.printStackTrace();
        }

        return font;
    }
	
}
