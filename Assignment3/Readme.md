Title	: Find me the nearest points

Instructions
You are given a set, S, of points in a plane (2-dimensional). A query is a point, p, and a number k.
You have to determine the k points of S which are nearest to p. The query point p may or may not belong to S.
Input:The points of S are specified in a file by their (x,y)-coordinate. The file contains 1 line in the form 
10 (2.1,4.3) (3.3,1.5) (4.7,11.1) (4.9,1.6) (5.0,12.3) (5.1,1.2) (6.7,3.3) (19.2,5.4) (20.5,7.9) (100.3,52)
The first number is the number of points, n, and following it are the coordinates of the n points in ascending order of the x-coordinate.
The query point p and the number k should be taken as input from the user.
Restrictions: Your program should take O(n) space and return the points in time O(k+log n).
