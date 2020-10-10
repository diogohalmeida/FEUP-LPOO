package view;

import com.googlecode.lanterna.graphics.TextGraphics;
import model.EnemyModel;

public interface EnemyView {
    void draw(TextGraphics graphics, EnemyModel model);
}
