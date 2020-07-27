# Tier 3

**Category:** SQL and AWS RDS

**Difficulty:** Intermediate I

**Estimated time to complete:** 45-60 minutes

**Minimum completion:** 60 points

**Description/Setup:**
  - ERD: [Quizzard Entity Relationship Diagram](https://revature-note-assets.s3.amazonaws.com/quizzard-erd.png)
  - Create and connect to a deployed DB instance, and create a table structure using a provided ERD
  - Alter the ConnectionConfig class with the appropriate values and push the code to check your work. No need to add any work done, the program will test that with the provided names.

**Problem Set:**
  - Create an AWS RDS Instance (vendor can be either Oracle or PostgreSQL)
    - Value: 10 points

  - Create a sequence that produces numbers beginning at 6 and increments by 3 each time.
    - Value: 20 Points

 - Create a table structure using the provided ERD, ensure that the ability for auto-incrementing IDs
    - Value: 30 points
	

  - Implement a stored procedure that takes three integers (i,j and k) and finds how many numbers between i and j (inclusively) are divisible by k. Example: proc(12, 18, 3) -> returns 3
 * 12/3 = 4
 * 13/3 = 4.333...
 * 14/3 = 4.667...
 * 15/3 = 5
 * 16/3 = 5.333...
 * 17/3 = 5.667...
 * 18/3 = 6

   So there are 3 numbers in the inclusive range from 12 to 18 that are divisible by 3.
    - Value: 40 points

