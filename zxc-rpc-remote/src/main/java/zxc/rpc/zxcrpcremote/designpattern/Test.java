package zxc.rpc.zxcrpcremote.designpattern;

public class Test {


    // State 接口
    interface LightState {
        void switchState(LightContext context);
    }

    // 具体状态：开
    static class OnState implements LightState {
        @Override
        public void switchState(LightContext context) {
            System.out.println("Light is ON. Switching to OFF.");
            context.setState(new OffState());
        }
    }

    // 具体状态：关
    static class OffState implements LightState {
        @Override
        public void switchState(LightContext context) {
            System.out.println("Light is OFF. Switching to ON.");
            context.setState(new OnState());
        }
    }

    // Context 类
    static class LightContext {
        private LightState currentState;

        public LightContext() {
            this.currentState = new OffState(); // 初始状态为关
        }

        public void setState(LightState state) {
            this.currentState = state;
        }

        public void pressSwitch() {
            currentState.switchState(this); // 委托给当前状态处理
        }
    }

    // 客户端调用
    public class Client {
        public static void main(String[] args) {


            LightContext light = new LightContext();
            light.pressSwitch(); // 关 -> 开
            light.pressSwitch(); // 开 -> 关
        }
    }
}
