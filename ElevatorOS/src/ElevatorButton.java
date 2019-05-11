import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import static java.lang.Math.abs;


public class ElevatorButton {
    private int floor;
    private int elevator;
    private int status;




    public void setElevator() {
        if(this.floor<=20){
                Control.elevatorList[this.elevator][this.floor]= 1;
        }
        else if(this.floor == 21)
            Control.elevatorSleep[this.elevator]=true;
        else if(this.floor == 22)
            Control.elevatorSleep[this.elevator]=false;
        else if(this.floor == 23){
            if(Control.stopTime[this.elevator] <= 2)
                Control.stopTime[this.elevator] += 2;
            else
                Control.stopTime[this.elevator] = 4;
        } else if(this.floor == 24) {
            if (Control.stopTime[this.elevator] >= 2)
                Control.stopTime[this.elevator] -= 2;
            else
                Control.stopTime[this.elevator] = 0;
        }

    }

    public void setElevatorAction() {
       int floor = this.floor;
       int status = this.status;
        int eleNum = 0;
        int eleDis = 100;
        for(int i = 0;i<5;i++) {
            int tempDis;
            if(Control.elevator[i].getCurStatus() == status || Control.elevator[i].getCurStatus() == status + 10 ) {

                if(status==1){
                    if(floor>=Control.elevator[i].getCurFloor())
                        tempDis = floor - Control.elevator[i].getCurFloor();
                    else
                        tempDis = 40 -floor -Control.elevator[i].getCurFloor();
                }
                else{
                    if(floor <= Control.elevator[i].getCurFloor())
                        tempDis = Control.elevator[i].getCurFloor() - floor;
                    else
                        tempDis = floor + Control.elevator[i].getCurFloor();
                }
            }else if(Control.elevator[i].getCurStatus() == 111){
                tempDis = abs(floor - Control.elevator[i].getCurFloor());
            }else{
                if(status==1){
                    tempDis = floor + Control.elevator[i].getCurFloor();
                }
                else{
                    tempDis = 40 -floor -Control.elevator[i].getCurFloor();
                }


            }
            if (tempDis < eleDis) {
                eleDis = tempDis;
                eleNum = i;
            }
        }
            Control.elevatorList[eleNum][floor] = 1;

    }




    public ElevatorButton(int i, int j, boolean temp, AnchorPane layout){

        this.elevator= i;
        this.floor = j+1;

        if(temp == true) {
            String s;
            if (i == 0) {
                s = "U";
                this.status = 1;
            } else {
                s = "D";
                this.status = 0;
            }
            Button button = new Button(s);
            button.setLayoutY(j * 40);
            button.setLayoutX(i * 40);
            button.setPrefSize(40,40 );
            layout.getChildren().add(button);
            button.setOnMousePressed(event -> setElevatorAction());
        }
        else {
            Button button =new Button(String.valueOf(this.floor));

            if(this.floor == 21)
                button.setText("A");
            if(this.floor == 22)
                button.setText("R");
            if(this.floor == 23)
                button.setText("O");
            if(this.floor == 24)
                button.setText("C");

            int m ;
            int n ;
            m = j/2;
            n = j%2;
            button.setLayoutX(n*50+i*150+160);
            button.setLayoutY(m*50+200);
            button.setPrefSize(40, 40);
            layout.getChildren().add(button);
            button.setOnMousePressed(event ->setElevator());
        }

    }
}