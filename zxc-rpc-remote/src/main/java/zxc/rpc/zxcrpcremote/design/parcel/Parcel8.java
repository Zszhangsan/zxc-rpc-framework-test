package zxc.rpc.zxcrpcremote.design.parcel;

public class Parcel8 {
    public Wrapper get(int i) {
        return new Wrapper(i) {
            @Override
            public int getValue() {
                return super.getValue() * 10;
            }
        };
    }
    public static void main(String[] args) {
        Parcel8 parcel8 = new Parcel8();
        Wrapper wrapper = parcel8.get(20);
        System.out.println(wrapper.getValue());
    }
}
