import java.util.LinkedList;
import java.util.Queue;

class Point {
    int x;
    int y;

    Point() {
        x = 0;
        y = 0;
    }

    Point(int a, int b) {
        x = a;
        y = b;
    }
}

public class KnightShortestPath {
    /**
     * @param grid:        a chessboard included 0 (false) and 1 (true)
     * @param source:      a point
     * @param destination: a point
     * @return: the shortest path
     */
    public int shortestPath(boolean[][] grid, Point source, Point destination) {
        // write your code here
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;

        int[][] moveArray = new int[][] { { 1, 2 }, { 1, -2 }, { -1, 2 }, { -1, -2 }, { 2, 1 }, { 2, -1 }, { -2, 1 },
                { -2, -1 } };

        Queue<Point> q = new LinkedList<>();
        q.offer(source);
        int step = 0;

        if (source.x == destination.x && source.y == destination.y) {
            return 0;
        }

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Point p = q.poll();
                for (int j = 0; j < 8; j++) {
                    Point newPoint = new Point(p.x + moveArray[j][0], p.y + moveArray[j][1]);
                    if (inBound(newPoint, m, n) && grid[newPoint.x][newPoint.y] == false) {
                        if (newPoint.x == destination.x && newPoint.y == destination.y) {
                            return step + 1;
                        }
                        q.offer(newPoint);
                        grid[newPoint.x][newPoint.y] = true;
                    } else {
                        continue;
                    }
                }
            }
            step++;
        }

        return -1;

    }

    private boolean inBound(Point p, int m, int n) {
        if (p.x < 0 || p.x >= n) {
            return false;
        }

        if (p.y < 0 || p.y >= m) {
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        KnightShortestPath k = new KnightShortestPath();
        boolean[][] grid = new boolean[][] { { false, false, false }, { false, false, false },
                { false, false, false } };
        Point s = new Point(2, 0);
        Point d = new Point(2, 2);

        System.out.println(k.shortestPath(grid, s, d));
    }
}