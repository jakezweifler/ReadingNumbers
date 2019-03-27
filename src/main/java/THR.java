public class THR {

    public static void main(String[] args) {
        moveTower(3, 1, 3);
        System.out.println("\n");
        moveTowerIterative(3);
    }


    public static void moveTower(int size, int from, int to) {
        if (size == 1) {
            System.out.println("move a disk from rod " + from + " to rod " + to + ".");
        } else {
            moveTower(size - 1, from, 6 - from - to);
            moveTower(1, from, to);
            moveTower(size - 1, 6 - from - to, to);
        }
    }


    public static void moveTowerIterative(int towerSize) {
        int[] counts = new int[towerSize];

        for (int x = 0; x < towerSize; x++) {
            if (towerSize % 2 == 0) {
                if (x % 2 == 0) {
                    counts[x] = 1;
                } else {
                    counts[x] = 2;
                }
            } else {
                if (x % 2 == 0) {
                    counts[x] = 2;
                } else {
                    counts[x] = 1;
                }
            }
        }

        for (int turn = 1; turn < Math.pow(2, towerSize); turn++) {
            int power = 0;
            int getPower = turn;
            while (getPower % 2 == 0) {
                power++;
                getPower = getPower / 2;
            }

            int moveTo = counts[power]%3 + 1;
            if(towerSize%2==1 && power%2 == 0) {
                counts[power]--;
            }
            else if(towerSize%2==0 && power%2 == 0) {
                counts[power]++;
            }
            else if(towerSize%2==0 && power%2 == 1) {
                counts[power]--;
            }
            else {
                counts[power]++;
            }

            //to fix some errors that occurred when 3s replaced 0s and when -1s replaced 2s
            if (moveTo == 0) {
                moveTo = 3;
            }
            if (moveTo == -1) {
                moveTo = 2;
            }

            System.out.println("move disk #" + power + " to space " + moveTo);

        }


    }

}
