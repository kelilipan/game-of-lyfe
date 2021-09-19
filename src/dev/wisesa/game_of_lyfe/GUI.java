package dev.wisesa.game_of_lyfe;

import javax.swing.*;
import java.awt.*;
class Grid extends Canvas{
 @Override
 public void paint(Graphics graphics){
  graphics.setColor(Color.red);
  graphics.setFont(new Font("Bold", Font.BOLD, 20));
  graphics.drawString("Hello world", 100, 100);
 }
}
public class GUI extends JFrame {
 GUI(){
  super("Game Of Life");
  Canvas grid = new Grid();
  grid.setBackground(Color.white);
  add(grid);
  setSize(400,400);
  setVisible(true);
 }
}
