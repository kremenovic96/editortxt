package editor;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.input.KeyCode;
import java.util.LinkedList;

public class Editor extends Application {
    Group root;
    private static final int WINDOW_WIDTH = 500;
    private static final int WINDOW_HEIGHT = 500;
    LinkedList<String> list = new LinkedList<String>();
    private class KeyEventHandler implements EventHandler<KeyEvent> {
            int textCenterX;
            int textCenterY;
            private static final int STARTING_FONT_SIZE = 20;
            private static final int STARTING_TEXT_POSITION_X = 250;
            private static final int STARTING_TEXT_POSITION_Y = 10;
            private Text textfornode;
            private int fontSize = STARTING_FONT_SIZE;
            private String fontName = "Verdana";

      KeyEventHandler(Group root,int windowWidth,int windowHeight){
          textCenterX = windowWidth / 2;
          textCenterY = windowHeight / 2;
          textfornode = new Text(textCenterX,textCenterY,"");
          textfornode.setTextOrigin(VPos.TOP);
          textfornode.setFont(Font.font(fontName,fontSize));
          root.getChildren().add(textfornode);
      }
            @Override
           public void handle(KeyEvent keyEvent) {
            if (keyEvent.getEventType() == KeyEvent.KEY_TYPED) {
                String characterTyped = keyEvent.getCharacter();
                //
                if (characterTyped.charAt(0) == 8){

                    root.getChildren().remove(textfornode);
                    list.removeLast();
                }
                if (characterTyped.length() > 0 && characterTyped.charAt(0) != 8) {
                    Text tmp = new Text(250,250,characterTyped);
    root.getChildren().add(tmp);
                    list.add(characterTyped);
                    textfornode = new Text(400,400,"");
                    textfornode.setText(list.toString());
                }
                centerText();
            }
                else if (keyEvent.getEventType()==KeyEvent.KEY_PRESSED) {
                    KeyCode code = keyEvent.getCode();
                    if (code == KeyCode.UP){
                     /*   fontSize += 5;
                        displayText.setFont(Font.font(fontName,fontSize));
                        centerText();   */
                    }
                    else if (code == KeyCode.DOWN){
                       /* fontSize = Math.max(0,fontSize -5);
                        displayText.setFont(Font.font(fontName, fontSize));
                        centerText();   */
                    }
                }
            }
            private void centerText() {
                double textHeight = textfornode.getLayoutBounds().getHeight();
                double textWidth = textfornode.getLayoutBounds().getWidth();
                double textTop = 0; //= textCenterY - textHeight /2;
                double textLeft = 0; //textCenterX - textWidth /2;
                textfornode.setX(textLeft);
                textfornode.setY(textTop);
                textfornode.toFront();

            }
        }
    @Override
    public void start(Stage primaryStage) {
         root = new Group();
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT, Color.WHITE);
        EventHandler<KeyEvent> keyEventHandler = new KeyEventHandler(root,WINDOW_WIDTH,WINDOW_HEIGHT);
        scene.setOnKeyTyped(keyEventHandler);
        scene.setOnKeyPressed(keyEventHandler);
        primaryStage.setTitle("Editor");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
