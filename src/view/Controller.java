package view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.Board;
import model.CellState;
import rules.Convay;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.*;

public class Controller implements Initializable{

    private static final int MILLISECONDS = 100;


    @FXML private HBox hbox;
    @FXML private VBox vbox;
    @FXML private Canvas canvas;

    private Board board;
    private Map<CellState, Color> colorMap;
    private ScheduledExecutorService executorService;



    public Controller(){
        this.board = new Board(70,45, new Convay());
        initColorMap();


    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initButtons();
        render();
    }

    private void initButtons(){

        hbox.setSpacing(5.0);

        Button btnNextFrame = new Button("Next Frame");
        btnNextFrame.setOnAction(event -> tick());

        Button btnPlay = new Button("Play");
        btnPlay.setOnAction(event -> play());

        Button btnPause = new Button("Pause");
        btnPause.setOnAction(event -> {
            executorService.shutdown();
        });


        hbox.getChildren().add(btnNextFrame);
        hbox.getChildren().addAll(btnPlay, btnPause);
    }

    private void initColorMap(){
        colorMap = new HashMap<>();
        colorMap.put(CellState.ACTIVE, Color.BLACK);
        colorMap.put(CellState.INACTIVE, Color.WHITE);
    }

    private void play(){
        if(executorService != null && !executorService.isShutdown()) return;

        executorService = Executors.newSingleThreadScheduledExecutor();

        Runnable runnable = () -> {
            tick();
            try {
                TimeUnit.MILLISECONDS.sleep(MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        executorService.scheduleAtFixedRate(runnable, 0L, MILLISECONDS, TimeUnit.MILLISECONDS);

    }

    private void tick(){
        this.board = board.tick();
        render();
    }

    private void render(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        int pixelWidth = (int)canvas.getWidth()/board.getX_dim();
        int pixelHeigth = (int)canvas.getHeight()/board.getY_dim();

        for(int x = 0; x < board.getX_dim(); x++){
            for(int y = 0; y < board.getY_dim(); y++){
                gc.setFill(colorMap.get(board.getState(x,y)));
                gc.fillRect(x*pixelWidth,y*pixelHeigth, pixelWidth, pixelHeigth);
            }
        }
    }
}
