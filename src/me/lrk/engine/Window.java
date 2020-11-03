package me.lrk.engine;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class Window {
  private int width, height;
  private String title = "Our 2D Game";

  private JFrame frame;
  private BufferedImage image;
  private Canvas canvas;
  private BufferStrategy bs;
  private Graphics g;

  public Window(int width, int height) {
    this.width = width;
    this.height = height;

    // buffered image = image stored in RAM
    image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    canvas = new Canvas();
    Dimension size = new Dimension(width, height);
    canvas.setPreferredSize(size);
    canvas.setMaximumSize(size);
    canvas.setMinimumSize(size);

    frame = new JFrame(title);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new BorderLayout());
    frame.add(canvas, BorderLayout.CENTER);
    // set frame to size of the canvas
    frame.pack();
    frame.setResizable(false);
    frame.setFocusable(true);
    // frame in the center of the screen
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);

    canvas.createBufferStrategy(2);
    bs = canvas.getBufferStrategy();
    g = bs.getDrawGraphics();
  }

  public void render() {
    g.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight(), null);
    // draw our buffer to the canvas
    bs.show();
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public BufferedImage getImage() {
    return image;
  }

  public Canvas getCanvas() {
    return canvas;
  }
}
