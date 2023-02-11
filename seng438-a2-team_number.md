**SENG 438 - Software Testing, Reliability, and Quality**

**Lab. Report \#2 – Requirements-Based Test Generation**

| Group \#: 20     |     |
| -------------- | --- |
| Student Names: |     |
| Tanish Datta               |30107335     |
| Arushi Gupta               |30121257     |
| Rian Opperman               |30118288     |
| Seniru Ruwanpura               |30122157     |

# 1 Introduction

In lab 2 we dug deeper into the testing process as we utilized several testing methods such as Unit Testing and Black Box testing. We were also strongly encouraged to learn the new application, Eclipse, to aid us in the testing process by using JAR files to set up this project in Eclipse. Not only did this lab allow us to further explore new IDEs, but it also helped apply the concepts taught in class such as Equivalence Class Testing and Boundary Class Testing to create partitions. This lab required us to use both concepts to fully test a new application, allowing us to create a structured test suite that thoroughly checked the application from multiple aspects and ensure that we accounted for the domain of the input variable. By using both ECT and Boundary testing, it ensures that coverage is at a maximum and almost every aspect of the application has been checked. In this report we will be exploring how Black Box testing and Unit Testing have helped cover some key aspects of the Range.class and all aspects of the DataUtilities.class. 

# 2 Detailed description of unit test strategy
Our tests will cover the org.jfree.data.Range and org.jfree.data.DataUtilities classes. Our methodology is to cover these classes via unit testing and to use black-box testing methods. Along with these methods we will use Mock objects to simulate interfacing between classes as we need to isolate each class for effective testing.
Equivalence classes and boundary values analysis will be used for testing each method of the classes, and the partitioning is explained further. 
The methods of each class that will be tested are:

***Range:***
expand(Range range, double lowerMargin, double upperMargin)
shift(Range base, double delta)
constrain(double value)
contains(double value)
getUpperBound()
getLowerBound()

*expand*
The range of acceptable values is:
range: is either null or it exists, so 2 partitions
lowerMargin: is either < 0 or >= 0, so 2 partitions
upperMargin: is either < 0 or >= 0, so 2 partitions

By using strong ECT we then have:
2 * 2 * 2 = 8 cases

To note, as expand can only add to the range, a negative percentage should not shrink the range. If the intended functionality included in expand to shrink a range, that should be made its own method or expand should instead be named changeSize.

Since boundary conditions are unknown we cannot do any boundary value tests.

*shift*
The range of acceptable values is:
range: is either null or it exists, so 2 partitions
delta: is either >= 0 or < 0, so 2 partitions
We also have the case where the range’s values cross zero and where it does not.

In total by using strong ECT we then have:
2 * 2 * 2 = 8 cases

Since boundary conditions are unknown we cannot do any boundary value tests.

*constrain*
The range of acceptable values is:
value: There are 3 cases, the value is below the range, above the range, and in the range

In total using strong ECT we then have:
3 cases

Since boundary conditions are unknown we cannot do any boundary value tests.

*contains*
The range of acceptable values is:
value: Is either in the range or it is not, so 2 partitions

By using strong ECT we then have:
2 cases

Since boundary conditions are unknown we cannot do any boundary value tests.

*getLowerBound*
There is no range of acceptable values, the method returns the lower bound of the range. So we need to test if the range returns a correct value, meaning only 1 case.

Since boundary conditions are unknown we cannot do any boundary value tests.

*getUpperBound*
There is no range of acceptable values, the method returns the upper bound of the range. So we need to test if the range returns a correct value, meaning only 1 case.

Since boundary conditions are unknown we cannot do any boundary value tests.


***DataUtilities:***
calculateColumnTotal(Values2D data, int column)
calculateRowTotal(Values2D data, int row)
createNumberArray(double[] data)
createNumberArray2D(double[][] data)
getCumulativePercentages(KeyedValues data)

*calculateColumnTotal*
Through the use of  ECT, we can derive the range of acceptable values.
Data input = null (invalid) or valid input 
Column input = either all positive, all negative, positive and negative, positive and negative and zero, one null, multiple null, one zero, multiple zero, empty column, 
Column out of bounds
Positive out of bounds
Negative row 

1 + 9 + 2 = 12 test cases.

Boundary conditions are unknown so we cannot do any boundary value tests.

*calculateRowTotal*
When using ECT we can split the inputs:
Data input = either null or valid input
Row input = either positive or negative

Therefore due to Strong ECT there are 2*2 = 4 test cases. We also created several variations where all data inputs were null as well as all inputs were set to zero. We then also tested index values using boundary class testing. Starting from a negative value to out of bounds- larger value. Therefore by adding all of the additional variations, we ended with a total of 8 test cases for this method.

*createNumberArray*
Through the use of ECT, we can derive the range of acceptable values.
Data input = null (invalid) and valid (double)
Valid data = Multiple and single datasets 
Data is positive and negative (2 cases)
Valid data = Multiple with both positive and negative and 0, empty array 

Therefore we have 1 + 2*2 + 2 = 7 test cases.

Boundary conditions are unknown so we cannot do any boundary value tests. 

*createNumberArray2D*
When using ECT we can split the inputs:
Valid array input = double (either positive or negative)
Invalid array input = null

Therefore due to Strong ECT there are 2 test cases.

Based on Boundary Value Testing, we can create a 2D array with the smallest size being 1 by 1 or very large arrays. This makes the total number of test cases to be 4 that were done in our testing process. Due to the boundary conditions being unknown we are unable to find concrete boundary classes for this method.

*getCumulativePercentage*
The range of acceptable values is:
data: can be null, valid but filled with null values, valid but has a null value at the start,  valid with a null value somewhere between the start and end, valid with a null value at the end, and valid with only numerical values inside; so 6 partitions
As well, we have the case that the numbers are > 0 or < 0, so 2 cases

Using strong ECT we then have: 
2 * 6 - 2= 10 test cases
Note: This is because when the data object is null and when all values are null we cannot have negative or positive numbers, so we must subtract 2 cases from the total amount. As well, boundary values are unknown for the tests so we cannot perform any Boundary Value Tests.



# 3 Test cases developed

Text…

// write down the name of the test methods and classes. Organize the based on
the source code method // they test. identify which tests cover which partitions
you have explained in the test strategy section //above

# 4 How the team work/effort was divided and managed

Text…

# 5 Difficulties encountered, challenges overcome, and lessons learned

Text…

# 6 Comments/feedback on the lab itself

Text…
