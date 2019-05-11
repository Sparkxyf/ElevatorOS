import javafx.application.Platform;
import javafx.scene.paint.Color;



public class Control {

    public static Elevator elevator[] = new Elevator[5];
    public static int elevatorList[][] = new int[5][21];
    public static boolean elevatorSleep[] =new boolean[5];
    public static int stopTime[] = new int[5];


    public Control(){
       for(int i = 0;i < 5;i++){
           elevator[i] = new Elevator();
           elevatorSleep[i] = false;
           stopTime[i] = -1;
       }


        ThreadStart(1);
        ThreadStart(2);
        ThreadStart(3);
        ThreadStart(4);
        ThreadStart(0);

    }


    public void ThreadStart(int i) {
        new Thread() {
            public void run() {

                elevator[i].JudgeStatus(elevatorList[i]);
                boolean flag = false;

                while(true){


                    while (elevatorSleep[i]) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }


                    elevator[i].JudgeStatus(elevatorList[i]);
                    boolean f = true;
                    if (elevator[i].getCurStatus() == 1) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        int tempFloor = elevator[i].getCurFloor();
                        elevator[i].setCurFloor(tempFloor + 1);
                    } else if (elevator[i].getCurStatus() == 0) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        int tempFloor = elevator[i].getCurFloor();
                        elevator[i].setCurFloor(tempFloor - 1);
                    } else if (elevator[i].getCurStatus() == 11 || elevator[i].getCurStatus() == 10) {
                          try {
                              System.out.println( elevator[i].getCurStatus()+ " " + elevator[i].getCurFloor());
                              stopTime[i] = 4;
                              while (stopTime[i] >= -1) {
                                  Thread.sleep(1000);
                                  if(stopTime[i] == 0){
                                      init.label_Open[i].setText("1s");
                                      init.label_Open[i].setFill(Color.BLACK);
                                  }else if(stopTime[i] == 1){
                                      init.label_Open[i].setText("2s");
                                      init.label_Open[i].setFill(Color.BLACK);
                                  }else if(stopTime[i] == 2){
                                      init.label_Open[i].setText("3s");
                                      init.label_Open[i].setFill(Color.BLACK);
                                  }else if(stopTime[i] == 3){
                                      init.label_Open[i].setText("4s");
                                      init.label_Open[i].setFill(Color.BLACK);
                                  }else if(stopTime[i] == 4){
                                      init.label_Open[i].setText("5s");
                                      init.label_Open[i].setFill(Color.BLACK);
                                  }else if(stopTime[i] == -1){
                                      init.label_Open[i].setText("5s");
                                      init.label_Open[i].setFill(Color.WHITE);
                                  }
                                  System.out.println(stopTime[i] );
                                  stopTime[i]--;
                              }
                          }catch (InterruptedException e) {
                                  e.printStackTrace();
                          }

                    } else if (elevator[i].getCurStatus() == 111) {
                        f = false;
                        while (elevator[i].getCurStatus() == 111) {
                            try {
//                                System.out.println( i + " "+elevator[i].getCurStatus()+ " " + elevator[i].getCurFloor());
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            elevator[i].JudgeStatus(elevatorList[i]);
                        }
                    }

//                    System.out.println( elevator[i].getCurStatus()+ " " + elevator[i].getCurFloor());



                    if (elevator[i].getCurFloor() == 1) flag = true;
                    if (elevator[i].getCurFloor() > 1 && !flag) elevator[i].setCurFloor(elevator[i].getCurFloor() - 1);





                    new Thread() {
                        public void run() {
                            Platform.runLater(() -> {
                                {
                                    if (elevator[i].getCurStatus() == 11 || elevator[i].getCurStatus() == 10 || elevator[i].getCurStatus() == 111){
                                        init.label[i].setText(String.valueOf(elevator[i].getCurFloor()));
                                    } else if (elevator[i].getCurStatus() == 1)
                                        init.label[i].setText(String.valueOf(elevator[i].getCurFloor() + 1));
                                    else if (elevator[i].getCurStatus() == 0)
                                        init.label[i].setText(String.valueOf(elevator[i].getCurFloor() - 1));


                                    if (elevator[i].getCurStatus() == 1) {
                                        init.label_Up[i].setFill(Color.BLACK);
                                        init.label_Down[i].setFill(Color.WHITE);
                                    } else if (elevator[i].getCurStatus() == 0) {
                                        init.label_Up[i].setFill(Color.WHITE);
                                        init.label_Down[i].setFill(Color.BLACK);
                                    } else {
                                        init.label_Up[i].setFill(Color.WHITE);
                                        init.label_Down[i].setFill(Color.WHITE);
                                    }

                                }
                            });
                        }
                    }.start();
                }
            }
        }.start();
    }
}
