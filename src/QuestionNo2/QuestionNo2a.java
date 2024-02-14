/*
Question 2 (a)
You are the manager of a clothing manufacturing factory with a production line of super sewing machines. The
production line consists of n super sewing machines placed in a line. Initially, each sewing machine has a certain
number of dresses or is empty.
For each move, you can select any m (1 <= m <= n) consecutive sewing machines on the production line and pass
one dress from each selected sewing machine to its adjacent sewing machine simultaneously.
Your goal is to equalize the number of dresses in all the sewing machines on the production line. You need to
determine the minimum number of moves required to achieve this goal. If it is not possible to equalize the number
of dresses, return -1.
Input: [1,0,5]
Output: 2
Example 1:
Imagine you have a production line with the following number of dresses in each sewing machine: [1,0,5]. The
production line has 5 sewing machines.
Here's how the process works:
1. Initial state: [1,0,5]
2. Move 1: Pass one dress from the third sewing machine to the first sewing machine, resulting in [1,1,4]
3. Move 2: Pass one dress from the second sewing machine to the first sewing machine, and from third to
first sewing Machine [2,1,3]
4. Move 3: Pass one dress from the third sewing machine to the second sewing machine, resulting in [2,2,2]
After these 3 moves, the number of dresses in each sewing machine is equalized to 2. Therefore, the minimum
number of moves required to equalize the number of dresses is 3.
*/

package QuestionNo2;

public class QuestionNo2a {
    public static int minMovesToEqualize(int[] dresses) {
        int totalDresses = 0;
        // Calculating the total number of dresses
        for (int dress : dresses) {
            totalDresses += dress;
        }
        // Checking if it's possible to equalize dresses
        int n = dresses.length;
        if (totalDresses % n != 0) {
            return -1;
        }
        // Calculating the target number of dresses after equalization
        int target = totalDresses / n;
        // Iterate through each machine
        int moves = 0;
        // Iterate through each machine
        for (int i = 0; i < n; i++) {
            if (dresses[i] > target) {
                moves += dresses[i] - target;
                dresses[(i + 1) % n] += dresses[i] - target;
                dresses[i] = target;
            }
        }
        return moves;
    }

    public static void main(String[] args) {
        int[] inputDresses = {1, 0, 5};
        System.out.println("Minimum number of moves required to equalize the number of dresses : " + minMovesToEqualize(inputDresses));//Output:3
    }
}
