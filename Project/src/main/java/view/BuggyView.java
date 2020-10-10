
package view;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import model.BuggyModel;
import model.Laser;


public class BuggyView {

    public void draw(TextGraphics graphics, BuggyModel model){
        graphics.enableModifiers(SGR.BOLD);

        if (model.getDestroyed()){
            drawDestroyedBuggy(graphics, model);
        }
        else {
            drawBuggyAnimations(graphics, model);
        }

        drawBuggyLasers(graphics, model);

        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.disableModifiers(SGR.BOLD);
    }

    private void drawDestroyedBuggy(TextGraphics graphics, BuggyModel model) {
        graphics.putString(model.getPosition().getX(), model.getPosition().getY(), "cn0MMnb");
        graphics.putString(model.getPosition().getX()+2, model.getPosition().getY() - 1, "--");
    }

    private void drawBuggyLasers(TextGraphics graphics, BuggyModel model) {
        switch(model.getType()){
            case 1:
                graphics.setForegroundColor(TextColor.Factory.fromString("#00FFFF"));
                break;
            case 2:
                graphics.setForegroundColor(TextColor.Factory.fromString("#00FF00"));
                break;
            case 3:
                graphics.setForegroundColor(TextColor.Factory.fromString("#FF00FF"));
                break;
        }

        if (model.getLasers().size() > 0){
            for (Laser laser: model.getLasers())
            graphics.putString(new TerminalPosition(laser.getPosition().getX(), laser.getPosition().getY()), "--");
        }
    }

    private void drawBuggyAnimations(TextGraphics graphics, BuggyModel model) {
        if (model.getJumping()) {
            drawJumping(graphics, model);
        }
        else{
            switch(model.getType()){
                case 1:
                    drawFirstBuggyAnimations(graphics, model);
                    break;
                case 2:
                    drawSecondBuggyAnimations(graphics, model);
                    break;
                case 3:
                    drawThirdBuggyAnimations(graphics, model);
                    break;

            }
        }
    }

    private void drawJumping(TextGraphics graphics, BuggyModel model){
        switch(model.getType()){
            case 1:
                graphics.putString(model.getPosition().getX(), model.getPosition().getY(), "(U)-(U)");
                graphics.putString(model.getPosition().getX()+2, model.getPosition().getY() - 1, "oMm");
                break;
            case 2:
                graphics.putString(model.getPosition().getX(), model.getPosition().getY(), "|U\\o/U|");
                graphics.putString(model.getPosition().getX()+2, model.getPosition().getY() - 1, "<x>");
                break;
            case 3:
                graphics.putString(model.getPosition().getX(), model.getPosition().getY(), "\\^/-\\^/");
                graphics.putString(model.getPosition().getX()+2, model.getPosition().getY() - 1, "|*|");
                break;
        }
    }

    private void drawFirstBuggyAnimations(TextGraphics graphics, BuggyModel model){
        switch(model.getAnimPos()){
            case 0:
                graphics.putString(model.getPosition().getX(), model.getPosition().getY(), "(|)-(|)");
                graphics.putString(model.getPosition().getX()+2, model.getPosition().getY() - 1, "0mm");
                break;
            case 1:
                graphics.putString(model.getPosition().getX(), model.getPosition().getY(), "(\\)-(\\)");
                graphics.putString(model.getPosition().getX()+2, model.getPosition().getY() - 1, "0mm");
                break;
            case 2:
                graphics.putString(model.getPosition().getX(), model.getPosition().getY(), "(-)-(-)");
                graphics.putString(model.getPosition().getX()+2, model.getPosition().getY() - 1, "0mm");
                break;
            case 3:
                graphics.putString(model.getPosition().getX(), model.getPosition().getY(), "(/)-(/)");
                graphics.putString(model.getPosition().getX()+2, model.getPosition().getY() - 1, "0mm");
                break;

        }
    }

    private void drawSecondBuggyAnimations(TextGraphics graphics, BuggyModel model){
        switch(model.getAnimPos()){
            case 0:
                graphics.putString(model.getPosition().getX(), model.getPosition().getY(), "|=\\x/=|");
                graphics.putString(model.getPosition().getX()+2, model.getPosition().getY() - 1, "<o>");
                break;
            case 1:
                graphics.putString(model.getPosition().getX(), model.getPosition().getY(), "|=|X|=|");
                graphics.putString(model.getPosition().getX()+2, model.getPosition().getY() - 1, "<o>");
                break;
            case 2:
                graphics.putString(model.getPosition().getX(), model.getPosition().getY(), "|-\\X/=|");
                graphics.putString(model.getPosition().getX()+2, model.getPosition().getY() - 1, "<o>");
                break;
            case 3:
                graphics.putString(model.getPosition().getX(), model.getPosition().getY(), "|=|x|-|");
                graphics.putString(model.getPosition().getX()+2, model.getPosition().getY() - 1, "<o>");
                break;

        }
    }

    private void drawThirdBuggyAnimations(TextGraphics graphics, BuggyModel model){
        switch(model.getAnimPos()){
            case 0:
                graphics.putString(model.getPosition().getX(), model.getPosition().getY(), "\\o/|\\o/");
                graphics.putString(model.getPosition().getX()+2, model.getPosition().getY() - 1, "|_|");
                break;
            case 1:
                graphics.putString(model.getPosition().getX(), model.getPosition().getY(), "\\O/|\\O/");
                graphics.putString(model.getPosition().getX()+2, model.getPosition().getY() - 1, "|-|");
                break;
            case 2:
                graphics.putString(model.getPosition().getX(), model.getPosition().getY(), "\\o/_\\o/");
                graphics.putString(model.getPosition().getX()+2, model.getPosition().getY() - 1, "|^|");
                break;
            case 3:
                graphics.putString(model.getPosition().getX(), model.getPosition().getY(), "\\O/-\\O/");
                graphics.putString(model.getPosition().getX()+2, model.getPosition().getY() - 1, "|_|");
                break;

        }
    }



}
