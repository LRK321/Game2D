package me.lrk.game;

import me.lrk.engine.Application;

public class Main {
  public static void main(String args[]) {
    Application app = new Application(new Game());
    app.start();
  }
}
