import java.util.Stack;
import java.util.ArrayList;

public class HanoiTowers {

    private ArrayList<Stack<Integer>> towers = new ArrayList<Stack<Integer>>(3);
    private int lastMoved = 0;

    public HanoiTowers() {
        towers.add(new Stack<Integer>());
        towers.add(new Stack<Integer>());
        towers.add(new Stack<Integer>());

        towers.get(0).push(4);
        towers.get(0).push(3);
        towers.get(0).push(2);
        towers.get(0).push(1);

        System.out.println(towers.get(0));
    }

    public void solve() {
        while(!isSolved()) {
            print();
            boolean moved = rightMove();
            if (!moved) {
                leftMove();
            }
            System.out.println("========");
            print();
            System.console().readLine();
        }
    }

    private boolean rightMove() {
        boolean canMoveFirstDisk = canMoveRight(0);
        boolean canMoveSecondDisk = canMoveRight(1);
        System.out.println("Can move right 1: " + canMoveFirstDisk + ", can move 2: " + canMoveSecondDisk);
        if (canMoveFirstDisk && canMoveSecondDisk) {
            throw new RuntimeException("Should never come here");
        } else if (canMoveFirstDisk) {
            moveRight(0);
            return true;
        } else if (canMoveSecondDisk) {
            moveRight(1);
            return true;
        } else {
            return false;
        }
    }

    private boolean leftMove() {
        boolean canMoveSecondDisk = canMoveLeft(1);
        boolean canMoveThirdDisk = canMoveLeft(2);
        System.out.println("Can move left 2: " + canMoveSecondDisk + ", can move 3: " + canMoveThirdDisk);
        if (canMoveSecondDisk && canMoveThirdDisk) {
            throw new RuntimeException("Should never come here");
        } else if (canMoveSecondDisk) {
            moveLeft(1);
            return true;
        } else if (canMoveThirdDisk) {
            moveLeft(2);
            return true;
        } else {
            return false;
        }
    }

    private void moveRight(int diskIdx) {
        int curIdx = diskIdx;
        while (canMoveRight(curIdx)) {
            System.out.println("Move " + towers.get(curIdx).peek() + " from " + curIdx + " to " + (curIdx + 1));
            towers.get(curIdx + 1).push(towers.get(curIdx).pop());
            curIdx++;
        }
        lastMoved = towers.get(curIdx).peek();
    }

    private void moveLeft(int diskIdx) {
        int curIdx = diskIdx;
        while (canMoveLeft(curIdx)) {
            towers.get(curIdx - 1).push(towers.get(curIdx).pop());
            curIdx--;
        }
        lastMoved = towers.get(curIdx).peek();
    }

    private boolean canMoveRight(int diskIdx) {
        return diskIdx < 2 && !towers.get(diskIdx).isEmpty() &&
        lastMoved != towers.get(diskIdx).peek() && (
            towers.get(diskIdx + 1).isEmpty() || 
            towers.get(diskIdx).peek() < towers.get(diskIdx + 1).peek()
            );
    }

    private boolean canMoveLeft(int diskIdx) {
        System.out.println(diskIdx);
        System.out.println(towers);
        System.out.println(towers.get(diskIdx).isEmpty() + ", " + lastMoved + ", " + towers.get(diskIdx).peek());        
        return diskIdx > 0 &&  !towers.get(diskIdx).isEmpty() &&
        lastMoved != towers.get(diskIdx).peek() && (
            towers.get(diskIdx - 1).isEmpty() || 
            towers.get(diskIdx).peek() < towers.get(diskIdx - 1).peek()
            );
    }

    private boolean isSolved() {
        Stack<Integer> lastTower = new Stack<Integer>();
        lastTower.addAll(towers.get(2));
        for (int i = 1; i <= 4 ;i++) {
            System.out.println(towers);
            if (lastTower.isEmpty() || lastTower.pop() != i) {
                return false;
            }
        }
        return true;
    }

    private void print() {
        ArrayList<Stack<Integer>> towers = new ArrayList<>(3);
        for (Stack<Integer> tower: this. towers) {
            towers.add(new Stack<Integer>());
            towers.get(towers.size() - 1).addAll(tower);
        }
        for (int i = 4; i >= 1; i--) {
            System.out.print("+ ");            
            for (Stack<Integer> tower: towers) {
                if (tower.size() == i) {
                    System.out.print(tower.pop() + " ");
                } else {
                    System.out.print("  ");
                }
            }            
            System.out.println();
        }
    }

    public static void main(String[] args) {
        new HanoiTowers().solve();
    }

}