package src;

public class ComparableCircle extends Circle implements Comparable {

    public int compareTo(Object obj) {
        if(obj instanceof ComparableCircle) {
            if(this.getRadius() > ((ComparableCircle)obj).getRadius() ) return -1;
            else if(this.getRadius() < ((ComparableCircle)obj).getRadius() ) return 1;
            else return 0;
        }
        else {
            System.out.println("compareTo() error, must be instance of ComparableCircle");
            return -100;
        }
    }
}
