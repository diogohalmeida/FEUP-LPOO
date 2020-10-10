package view;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class KeyReader {
    public enum KEY {ARROWUP, ARROWDOWN, ARROWRIGHT, ARROWLEFT, ENTER, P, ESC, SPACE, NONE}
    private Screen screen;

    public KeyReader(Screen screen){
        this.screen = screen;
    }

    public KEY readInput() throws IOException {
        KeyStroke key = screen.readInput();
        if (key.getKeyType() == KeyType.Character){
            switch (key.getCharacter()){
                case ' ':
                    return KEY.SPACE;
                case 'P':
                case 'p':
                    return KEY.P;
                default:
                    return KEY.NONE;
            }
        }
        else{
            switch (key.getKeyType()){
                case Enter:
                    return KEY.ENTER;
                case ArrowUp:
                    return KEY.ARROWUP;
                case ArrowDown:
                    return KEY.ARROWDOWN;
                case ArrowRight:
                    return KEY.ARROWRIGHT;
                case ArrowLeft:
                    return KEY.ARROWLEFT;
                case Escape:
                    return KEY.ESC;
                default:
                    return KEY.NONE;
            }
        }
    }

    public KEY pollInput() throws IOException {
        KeyStroke key = screen.pollInput();
        if (key == null){
            return KEY.NONE;
        }
        if (key.getKeyType() == KeyType.Character){
            switch (key.getCharacter()){
                case ' ':
                    return KEY.SPACE;
                case 'P':
                case 'p':
                    return KEY.P;
                default:
                    return KEY.NONE;
            }
        }
        else{
            switch (key.getKeyType()){
                case Enter:
                    return KEY.ENTER;
                case ArrowUp:
                    return KEY.ARROWUP;
                case ArrowDown:
                    return KEY.ARROWDOWN;
                case ArrowRight:
                    return KEY.ARROWRIGHT;
                case ArrowLeft:
                    return KEY.ARROWLEFT;
                case Escape:
                    return KEY.ESC;
                default:
                    return KEY.NONE;
            }
        }
    }
}
