package me.lrk.engine;

import java.awt.image.DataBufferInt;

public class Renderer {
  private int pixelW, pixelH;
  private int[] pixels;

  public Renderer(Application app) {
    pixelW = app.getWindow().getWidth();
    pixelH = app.getWindow().getHeight();
    pixels = ((DataBufferInt)app.getWindow().getImage().getRaster().getDataBuffer()).getData();
  }

  public void clear() {
    for(int i = 0; i < pixels.length; i++) {
      // 0x - prefix
      // ff - alpha (ff = 255)
      // 00 - R (00 = 0)
      // 00 - G (00 = 0)
      // 00 - B (00 = 0)
      pixels[i] += 0xff000000;
    }
  }

  public void setPixel(int x, int y, int color) {
    if (x < 0 || x > pixelW || y < 0 || y > pixelH) {
      return;
    }
    pixels[x + y * pixelW] = color;
  }

  public void drawRect(int x, int y, int w, int h, int color) {
    for (int xCur = x; xCur < x + w; xCur++) {
      for (int yCur = y; yCur < y + h; yCur ++) {
        setPixel(xCur, yCur, color);
      }
    }
  }
}
