

public class Elevator {

    private int curStatus;
    // Up=1 Down=0 Up&Stop=11 Down&Stop=10 Stop=111
    private int curFloor;


    public int getCurStatus() {
        return curStatus;
    }


    public int getCurFloor() {
        return curFloor;
    }

    public void setCurFloor(int curFloor) {
        this.curFloor = curFloor;
    }



    public Elevator() {
        this.curStatus = 111;
        this.curFloor = 1;
    }

    // Up=1 Down=0 Up&Stop=11 Down&Stop=10 Stop=111
    public void JudgeStatus(int[] curList){
        if (curList[curFloor]==1){
            if (curStatus==1) {
                curStatus=11;
                System.out.println(curStatus);
            }
            else curStatus=10;
            curList[curFloor]=0;
        }
        else if (curStatus==11){
            for (int i = curFloor; i <= 20; i++) {
                if (curList[i] != 0) {
                    curStatus = 1;
                    break;
                }
            }
            if (curStatus==11){
                for (int i=curFloor;i>=1;i--){
                    if (curList[i] != 0){
                        curStatus = 0;
                        break;
                    }
                }
            }
            if (curStatus==11) curStatus=111;
        }
        else if (curStatus==10){
            for (int i = curFloor; i >=1 ; i--) {
                if (curList[i] != 0) {
                    curStatus = 0;
                    break;
                }
            }
            if (curStatus ==  10) {
                for (int i = curFloor; i <=20 ; i++) {
                    if (curList[i] != 0) {
                        curStatus = 1;
                        break;
                    }
                }
            }
            if (curStatus == 10) curStatus = 111;
        }
        else if (curStatus==111){
            for (int i=1;i<=20;i++){
                if (curList[i]!=0){
                    if (i>curFloor) curStatus=1;
                    if (i<curFloor) curStatus=0;
                    break;
                }
            }
        }
    }
}
