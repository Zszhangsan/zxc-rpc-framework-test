package zxc.rpc.zxcrpcremote.polymorphism;

public class RoundGlyphy extends Glyph{
    private Integer i = 1;

    private int radius = 1;

    public RoundGlyphy(Integer i, int radius) {
        this.i = i;
        this.radius = radius;
        System.out.println("RoundGlyphy.RoundGlyphy(), radius " + radius + " i " + i);
    }

    @Override
    void draw() {
        System.out.println("RoundGlyphy draw " + radius + i);
    }

    public static void main(String[] args) {
        Glyph roundGlyphy = new RoundGlyphy(2, 3);
        roundGlyphy.draw();
    }
}
