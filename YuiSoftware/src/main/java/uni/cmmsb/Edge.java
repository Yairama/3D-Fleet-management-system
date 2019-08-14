package uni.cmmsb;

class Edge {

    Point p1, p2;
    Triangle t;

    Edge(Point p1, Point p2, Triangle t) {
        this.t = t;
        //from lowest Point.id to highest
        if (p1.id < p2.id) {
            this.p1 = p1;
            this.p2 = p2;
        } else if (p1.id > p2.id) {
            this.p1 = p2;
            this.p2 = p1;
        } else {
            try {
                System.out.println("Napaka pri tockah: " + p1 + p2);
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    Edge(Point p1, Point p2) {
        this.t = t;
        //from lowest Point.id to highest
        if (p1.id < p2.id) {
            this.p1 = p1;
            this.p2 = p2;
        } else if (p1.id > p2.id) {
            this.p1 = p2;
            this.p2 = p1;
        } else {
            try {
                System.out.println("Napaka pri tockah: " + p1 + p2);
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
