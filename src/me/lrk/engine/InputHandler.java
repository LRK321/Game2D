package me.lrk.engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener{
  private final int NUM_OF_KEYS = 300;
  private boolean[] keys = new boolean[NUM_OF_KEYS];
  private boolean[] keysLast = new boolean[NUM_OF_KEYS];

  public InputHandler(Application app) {
     app.getWindow().getCanvas().addKeyListener(this);
  }

  public void update() {
    for (int i = 0; i < NUM_OF_KEYS; i++) {
      keysLast[i] = keys[i];
    }
  }

  // is the key pressed?
  public boolean isKey(int keyCode) {
    return keys[keyCode];
  }

  // did we just release the key?
  public boolean isKeyUp(int keyCode) {
    return !keys[keyCode] && keysLast[keyCode];
  }

  // did we just press the key?
  public boolean isKeyDown(int keyCode) {
    return keys[keyCode] && !keysLast[keyCode];
  }

  @Override
  public void keyTyped(KeyEvent e) { }

  @Override
  public void keyPressed(KeyEvent e) {
    keys[e.getKeyCode()] = true;
  }

  @Override
  public void keyReleased(KeyEvent e) {
    keys[e.getKeyCode()] = false;
  }
}
