package me.lrk.engine;

import java.awt.event.KeyEvent;

public class Application {
  private Window window;
  private Renderer renderer;
  private InputHandler input;

  private boolean running;

  private final double UPDATE_CAP = 1.0/60.0;

  public Application() {

  }

  public void start() {
    window = new Window(800, 500);
    renderer = new Renderer(this);
    input = new InputHandler(this);

    // enter game loop
    this.run();
  }

  public void stop() {

  }

  private void run() {
    running = true;

    // render shows whether a new frame should be rendered or not
    boolean render = false;

    // last time the loop has been executed
    double lastTime = System.nanoTime() / 1000000000.0;
    // passed time since the last time the loop has been executed
    double passedTime = 0;
    // time that hasn't been processed yet (adds up if game freezes)
    double unprocessedTime = 0;

    // just a variable to store the time since last display of FPS
    double frameTime = 0;
    // amount of frames rendered since last FPS output
    int curFrames = 0;
    int fps = 0;

    while(running) {
      render = false;

      double currentTime = System.nanoTime() / 1000000000.0;
      passedTime = currentTime - lastTime;
      lastTime = currentTime;

      unprocessedTime += passedTime;
      frameTime += passedTime;

      while(unprocessedTime >= UPDATE_CAP) {
        // remove the time for one update from unprocessed time
        unprocessedTime -= UPDATE_CAP;
        // only render if we update
        render = true;

        // TODO update game
        if(input.isKeyDown(KeyEvent.VK_W)) {
          System.out.println("vk down!");
        }
        renderer.drawRect(10, 10, 50, 50, 0xffffffff);

        input.update();

        // print FPS each second
        if (frameTime >= 1) {
          frameTime = 0;
          fps = curFrames;
          curFrames = 0;
          System.out.println("FPS: " + fps);
        }
      }

      if (render) {
        renderer.clear();
        window.render();
        curFrames += 1;
      } else {
        try {
          // sleep for 1 milis reduces cpu usage
          Thread.sleep(1);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
    dispose();
  }

  private void dispose() {

  }

  public Window getWindow() {
    return window;
  }
}
