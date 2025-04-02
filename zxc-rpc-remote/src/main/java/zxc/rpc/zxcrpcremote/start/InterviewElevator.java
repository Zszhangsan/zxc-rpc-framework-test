package zxc.rpc.zxcrpcremote.start;

import java.util.Scanner;

/**
 * 基于Java控制台模拟电梯上下楼
 * 电梯先随机停在一个楼层 有人按下按钮 电梯判断几楼按的去接人，人进入电梯按下几楼 电梯判断送人
 */
public class InterviewElevator {
    // 电梯：楼层、功能
//    楼层：当前楼层、最大楼层、最小楼层
//    功能：外部按钮、内部按钮、运行
//    外部按钮：上、下
//    内部按钮：输入去往的楼层
//    运行：显示电梯所在楼层
    static class Elevator {
        private int maxFloor; // 最大楼层
        private int minFloor; // 最小楼层
        private int currentFloorForElevator; // 电梯的当前楼层
        private int userCurrentFloorForElevator; // 用户所在的楼层

        /**
         * 外部按钮，用户按了按钮之后，判断当前电梯所在的楼层和用户所在的楼层，运行电梯
         * 判断距离最近的电梯去
         * @param direction  用户下楼还是上楼，可以修改为枚举类。
         * @return
         */
        private void externalBtn() {
            run();
            System.out.println("接到客人了");
        }

        private void internalBtn(int goToFloor) {
            run(goToFloor);
            System.out.println("抵达了楼层");
        }

        private void run() {
             run(this.userCurrentFloorForElevator);
        }

        /**
         * 输入了楼层后，电梯显示当前的楼层
         * @param goToFloor 用户要去的楼层
         */
        private void run(int goToFloor) {
            if (goToFloor <= 0 || goToFloor < minFloor || goToFloor > maxFloor) {
                System.out.println("输入错误的楼层");
                return;
            }
            int runToFloor = 0;
            // 电梯楼层低于用户楼层
            if (this.currentFloorForElevator < goToFloor) {
                runToFloor =goToFloor - this.currentFloorForElevator;
                runFloor(runToFloor, goToFloor);
            } else {
                runToFloor =this.currentFloorForElevator - goToFloor;
                runFloor(goToFloor, runToFloor);
            }

        }

        private void runFloor (int runToFloor, int goToFloor) {
            System.out.println("将要运行多少个楼层: " + runToFloor);
            System.out.println("电梯所在楼层：" + this.currentFloorForElevator);
            while (this.currentFloorForElevator < goToFloor) {
                this.currentFloorForElevator++;
                System.out.println("电梯所在楼层：" + this.currentFloorForElevator);
            }
        }

        public Elevator(int maxFloor, int minFloor, int currentFloorForElevator, int userCurrentFloorForElevator) {
            this.maxFloor = maxFloor;
            this.minFloor = minFloor;
            this.currentFloorForElevator = currentFloorForElevator;
            this.userCurrentFloorForElevator = userCurrentFloorForElevator;
            System.out.println("电梯所在楼层" + currentFloorForElevator + "用户所在楼层" + userCurrentFloorForElevator);
        }

        public Elevator() {
        }

        public int getMaxFloor() {
            return maxFloor;
        }

        public void setMaxFloor(int maxFloor) {
            this.maxFloor = maxFloor;
        }

        public int getMinFloor() {
            return minFloor;
        }

        public void setMinFloor(int minFloor) {
            this.minFloor = minFloor;
        }

        public int getCurrentFloorForElevator() {
            return currentFloorForElevator;
        }

        public void setCurrentFloorForElevator(int currentFloorForElevator) {
            this.currentFloorForElevator = currentFloorForElevator;
        }

        public int getUserCurrentFloorForElevator() {
            return userCurrentFloorForElevator;
        }

        public void setUserCurrentFloorForElevator(int userCurrentFloorForElevator) {
            this.userCurrentFloorForElevator = userCurrentFloorForElevator;
        }
    }
    public final static Scanner sc = new Scanner(System.in);
    public static void main (String[] args) {
        int i = sc.nextInt();
        Elevator elevator = new Elevator(10, 1, 6, 2);
        elevator.externalBtn();
    }

}
