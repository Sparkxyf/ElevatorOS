
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class init {
    public static Text label[] = new Text[5];
    public static Text label_Up[] = new Text[5];
    public static Text label_Down[] = new Text[5];
    public static  Text label_Open[] = new Text[5];
    private Label elevatorLabel[] = new Label[20];
    private ElevatorButton elevatorButton[][] = new ElevatorButton[2][20];
    private ElevatorButton button[][] = new ElevatorButton[5][24];
    private Text label_Info = new Text();



    public  init(AnchorPane layout){
        //view layer
        label_Info = new Text("A：Alarm 警报\n" +
                " 暂停该部电梯所有活动\n"+
                "R：Recover \n" +
                "恢复 恢复电梯活动\n" +
                "O：Open  开门\n " +
                "延长电梯门开启时间2s\n" +
                "C：Close 关门\n " +
                "缩短电梯门开启时间2s\n"+
                "计时器代表电梯关门时间"
        );
        label_Info.setFont(Font.font("Comforta",30));
        label_Info.setFill(Color.BLUE);
        label_Info.setLayoutX(900);
        label_Info.setLayoutY(200);
        layout.getChildren().add(label_Info);


        for(int i = 0;i < 5;i++){

            label_Up[i] = new Text("↑");
            label_Down[i] = new Text("↓");

            label_Up[i].setFont(Font.font("Comforta",40));
            label_Up[i].setFill(Color.WHITE);
            label_Up[i].setLayoutX(i*150+150);
            label_Up[i].setLayoutY(40);
            layout.getChildren().add(label_Up[i]);

            label_Open[i]= new Text("5s");
            label_Open[i].setFont(Font.font("Comforta",30));
            label_Open[i].setFill(Color.WHITE);
            label_Open[i].setLayoutX(i*150+180);
            label_Open[i].setLayoutY(150);
            layout.getChildren().add(label_Open[i]);


            label_Down[i].setFont(Font.font("Comforta",40));
            label_Down[i].setFill(Color.WHITE);
            label_Down[i].setLayoutX(i*150+220);
            label_Down[i].setLayoutY(40);
            layout.getChildren().add(label_Down[i]);


            label[i] = new Text();
            label[i].setText("1");
            label[i].setFont(Font.font("Comforta",40));

            label[i].setLayoutX(i*150+190);
            label[i].setLayoutY(100);
            layout.getChildren().add(label[i]);

        }

        for(int i = 0;i < 20;i++){
            elevatorLabel[i] = new Label(String.valueOf(i+1));
            elevatorLabel[i].setFont(Font.font("Comforta",20));
            elevatorLabel[i].setPrefSize(40,40);
            elevatorLabel[i].setBackground(new Background(new BackgroundFill(Color.PINK,null,null)));
            elevatorLabel[i].setLayoutX(80);
            elevatorLabel[i].setLayoutY(i*40);
            layout.getChildren().add(elevatorLabel[i]);
        }



        //button layer

        for(int i =0 ; i < 5 ;i++){
            for(int j = 0 ;j < 24;j++){
                button[i][j] = new ElevatorButton(i,j,false,layout);
            }
        }

        for(int i =0 ; i < 2 ;i++){
            for(int j = 0 ;j < 20;j++){
                if(i == 1&&j==0)continue;
                if(i==0&&j==19)continue;
                elevatorButton[i][j] = new ElevatorButton(i,j, true,layout);
            }
        }


    }


}
