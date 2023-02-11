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

**Range:**
- `expand(Range range, double lowerMargin, double upperMargin)`
- `shift(Range base, double delta)`
- `constrain(double value)`
- `contains(double value)`
- `getUpperBound()`
- `getLowerBound()`

**DataUtilities:**
- `calculateColumnTotal(Values2D data, int column)`
- `calculateRowTotal(Values2D data, int row)`
- `createNumberArray(double[] data)`
- `createNumberArray2D(double[][] data)`
- `getCumulativePercentages(KeyedValues data)`

## Range
### `expand`

The range of acceptable values is:
- range: is either null or it exists, so 2 partitions
- lowerMargin: is either < 0 or >= 0, so 2 partitions
- upperMargin: is either < 0 or >= 0, so 2 partitions

By using strong ECT we then have:
2 * 2 * 2 = 8 cases

To note, as expand can only add to the range, a negative percentage should not shrink the range. If the intended functionality included in expand to shrink a range, that should be made its own method or expand should instead be named changeSize.

Since boundary conditions are unknown we cannot do any boundary value tests.

### `shift`

The range of acceptable values is:
- range: is either null or it exists, so 2 partitions
- delta: is either >= 0 or < 0, so 2 partitions
We also have the case where the range’s values cross zero and where it does not.

In total by using strong ECT we then have:
2 * 2 * 2 = 8 cases

Since boundary conditions are unknown we cannot do any boundary value tests.

### `constrain`

The range of acceptable values is:
- value: There are 3 cases, the value is below the range, above the range, and in the range

In total using strong ECT we then have:
3 cases

Since boundary conditions are unknown we cannot do any boundary value tests.

### `contains`

The range of acceptable values is:
- value: Is either in the range or it is not, so 2 partitions

By using strong ECT we then have:
2 cases

Since boundary conditions are unknown we cannot do any boundary value tests.

### `getLowerBound`

There is no range of acceptable values, the method returns the lower bound of the range. So we need to test if the range returns a correct value, meaning only 1 case.

Since boundary conditions are unknown we cannot do any boundary value tests.

### `getUpperBound`

There is no range of acceptable values, the method returns the upper bound of the range. So we need to test if the range returns a correct value, meaning only 1 case.

Since boundary conditions are unknown we cannot do any boundary value tests.


## `DataUtilities`
### `calculateColumnTotal`

Through the use of  ECT, we can derive the range of acceptable values.
- Data input = null (invalid) or valid input 
- Column input = either all positive, all negative, positive and negative, positive and negative and zero, one null, multiple null, one zero, multiple zero, empty column, 
- Column out of bounds
    - Positive out of bounds
    - Negative row 

1 + 9 + 2 = 12 test cases.

Boundary conditions are unknown so we cannot do any boundary value tests.

### `calculateRowTotal`

When using ECT we can split the inputs:
- Data input = either null or valid input
- Row input = either positive or negative

Therefore due to Strong ECT there are 2*2 = 4 test cases. We also created several variations where all data inputs were null as well as all inputs were set to zero. We then also tested index values using boundary class testing. Starting from a negative value to out of bounds- larger value. Therefore by adding all of the additional variations, we ended with a total of 8 test cases for this method.

### `createNumberArray`

Through the use of ECT, we can derive the range of acceptable values.
- Data input = null (invalid) and valid (double)
- Valid data = Multiple and single datasets 
    - Data is positive and negative (2 cases)
- Valid data = Multiple with both positive and negative and 0, empty array 

Therefore we have 1 + 2* 2 + 2 = 7 test cases.

Boundary conditions are unknown so we cannot do any boundary value tests. 

### `createNumberArray2D`

When using ECT we can split the inputs:
- Valid array input = double (either positive or negative)
- Invalid array input = null

Therefore due to Strong ECT there are 2 test cases.

Based on Boundary Value Testing, we can create a 2D array with the smallest size being 1 by 1 or very large arrays. This makes the total number of test cases to be 4 that were done in our testing process. Due to the boundary conditions being unknown we are unable to find concrete boundary classes for this method.

### `getCumulativePercentage`

The range of acceptable values is:
- data: can be null, valid but filled with null values, valid but has a null value at the start,  valid with a null value somewhere between the start and end, valid with a null value at the end, and valid with only numerical values inside; so 6 partitions
- As well, we have the case that the numbers are > 0 or < 0, so 2 cases

Using strong ECT we then have: 
2 * 6 - 2= 10 test cases
Note: This is because when the data object is null and when all values are null we cannot have negative or positive numbers, so we must subtract 2 cases from the total amount. As well, boundary values are unknown for the tests so we cannot perform any Boundary Value Tests.

# 3 Test cases developed

## `Range`

### `expand`

1. `expandNullNegativeLowerNegativeUpper()` - Tests to check if InvalidParameterException error works by sending in null range object and two negative bounds
2. `expandNullNegativeLowerPositiveUpper()` - Tests to check if InvalidParameterException error works by sending in null range object, negative lower bound and positive upper bound
3. `expandNullPositiveLowerNegativeUpper()` - Tests to check if InvalidParameterException error works by sending in null range object, negative upper bound and positive lower bound
4. `expandNullPostiveLowerPositiveUpper()` - Tests to check if InvalidParameterException error works by sending in null range object and two positive bounds
5. `expandNegativeLowerNegativeUpper()` - Tests to check if range bounds that was sent in was not shrunk because of negative bounds
6. `expandNegativeLowerPositiveUpper()` - Tests to check if lower range bounds were not shrunk and that only the upper bounds was affected
7. `expandPositiveLowerNegativeUpper()` - Tests to check if upper range bounds were not shrunk and that only the lower bounds was affected
8. `expandPostiveLowerPositiveUpper()` - Tests to check if both upper and lower bound of range bounds expanded 

### `shift`

1. `shiftNullNegativeTest()` - Tests to check if an InvalidParameterException is thrown when provided a null Range object and negative delta value
2. `shiftNullPositiveTest()` - Tests to check if an InvalidParameterException is thrown when provided a null Range object and positive delta value
3. `shiftNullNegativeCrossZeroTest()` - Tests to check if an InvalidParameterException is thrown when provided a null Range object and negative delta value, where the delta is large enough to cause the Range to cross zero
4. `shiftNullPositiveCrossZeroTest()` - Tests to check if an InvalidParameterException is thrown when provided a null Range object and positive delta value, where the delta is large enough to cause the Range to cross zero
5. `shiftNegativeTest()` - Tests to check if Range object was not shifted when provided a valid Range object and negative delta value. This is because the documentation describes the shift being positive (to the right), meaning a negative value should not be allowed
6. `shiftPositiveTest()` - Tests to check if Range object was shifted correctly when provided a valid Range object and positive delta value
7. `shiftNegativeCrossZeroTest()` - Tests to check if Range object was not shifted when provided a valid Range object and negative delta value which would cause the Range to cross zero. This is because the documentation describes the shift being positive (to the right), meaning a negative value should not be allowed
8. `shiftPositiveCrossZeroTest()` - Tests to check if Range object was shifted correctly when provided a valid Range object and positive delta value, which would cause the Range to cross over zero. The correct output should be pinned at 0 for either the upper or lower bound, depending on which would cross zero, as the documentation describes the method as not being able to cross over zero

### `constrain`

1. `testConstraintBelowRange()` - Test to check if value which is below the range returns the minimum value of the range
2. `testConstraintInRange()` -Test to check if value which is in the range returns the value of that's within range
3. `testConstraintAboveRange()` - Test to check if value which is above the range returns the maximum value of the range

### `Contains`

1. `testContainsInRange()` - Test to check if the value sent in was in the range
2. `testContainsOutOfRange()` - Test to check if the value sent in was out of range

### `getLowerBound`
1. `getLowerBoundTest()` - Tests to check if the return value is the minimum bound of range

### `getUpperBound`

1. `getUpperBoundTest()` - Tests to check if the return value is the maximum bound of range

-----

## `Data Utilities`

### `createNumberArray`

1. `testCreateNumberArrayValidMultipleInput()` - Creates Array with multiple valid inputs 
2. `testCreateNumberArrayPositiveMultipleInput()` - Creates Array with multiple positive valid inputs 
3. `testCreateNumberArrayOneValidInput()` - Creates Array with one valid inputs 
4. `testCreateNumberArrayEmptyInput()` - Creates Array with no inputs
5. `testCreateNumberArrayNullInput()` -  Creates Array with null inputs 
6. `testCreateNumberArrayNegatives()` - Creates Array with multiple negative valid inputs 

### `createNumberArray2D`

1. `createNumberArray2Ddouble()` - Creates 2D Array with valid inputs
2. `createNumberArray2DNegative()` -  Creates 2D Array with valid negative inputs
3. `createNumberArray2DInvalidInput()` -  Creates 2D Array with null input
4. `createNumberArray2DSingleInput()` -  Creates 2D Array with one valid input

### `calculateColumnTotal`

1. `testCalculateColumnTotalPositiveValidInput()` - Calculates One Column Total with 3 Values at Index 0 with Only Positive Inputs 
2. `testCalculateColumnTotalNegativeValidInput()` - Calculates One Column Total with 3 Values at Index 0 with Only Negative Inputs 
3. `testCalculateColumnTotalCombinedInput()` - Calculates One Column Total with 3 Values at Index 0 with Positive, Negative, and Zero Inputs
4. `calculateColumnTotalForNullValue()` - Calculates One Column Total with 3 Values at Index 0 with One Null Value 
5. `calculateColumnTotalForAllNullValue()` - Calculates One Column Total with 3 Values at Index 0 with Only Null Values
6. `calculateColumnTotalForZeroValue()` - Calculates One Column Total with 3 Values where one is Zero and the rest are positive
7. `calculateColumnTotalForAllZeroValue()` - Calculates One Column Total with 3 Values that are all Zero
8. `calculateColumnTotalEmpty()` - Calculates Column Total that is Empty
9. `calculateColumnTotalNull()` - Calculates Column Total where Data is Null/Invalid to test Error
10. `testCalculateColumnTotalOutOfBoundsPositive()` - Calculates Column Total where Columns is One Extra than Index (Out of Bounds Positive) to look for error and if OutofBoundsException is Thrown
11. `testCalculateColumnTotalOutOfBoundsNegative()` - Calculates Column Total where Columns is One Extra than Index (Out of Bounds Positive) to look for error and if OutofBoundsException is Thrown

### `calculateRowTotal`

1. `calculateRowTotalForTwoValues()` - Calculates row total with 2 correct input values
2. `calculateRowTotalForNullVal()` - Calculates row total with one null value and two correct values
3. `calculateRowTotalForNullValAtEnd()` - Calculates row total with one null value at end and two correct values
4. `calculateRowTotalForAllNullVal()` - Calculates row total with all null values
5. `calculateRowTotalForOneZeroVal()` - Calculates row total with one 0 value and two correct values
6. `calculateRowTotalForAllZeroVal()` - Calculates row total with all zero values
7. `calculateRowTotalWithNegativeIndex()` - Calculates row total with negative index
8. `calcualteRowTotalWithOOBIndex()` - Calculates row total with out of bounds index

### `getCumulativePerentage`

1. `getCumulativePercentageWithAllNullValues()` - This test send in all null value for getCumulativePercent() method
2. `getCumulativePercentageWithAllFirstNullValues()` - Only the first value send in is null, the rest is negative for getCumulativePercent()
3. `getCumulativePercentageWithAllSecondNullValues()` - The method getCumulativePercent() is tested with second value being null and the rest negative
4. `getCumulativePercentageWithallThirdNullValues()` - Third value for getCumulativePercent() method is null, rest is negative
5. `getCumulativePercentageWithNullData()` - Tests null data for error
6. `getCumulativePercentageWithAllNegativeValues()` - all value are negative in the getCumulativePercent() method to check InvalidParameterException





# 4 How the team work/effort was divided and managed

Since there were two classes that needed to be tested, the team split up into 2 subteams for Range and DataUtilities. Each subteam tackled 5 methods in total, to meet the requirements of 10 methods, with all 5 from DataUtilities being covered and a chosen 5 out of 15 from Range.

Arushi and Seniru were the first team. The two of them first worked through attempting to set up JUnit properly through Eclipse, and helped each other with the various errors that were popping up due to improper setting up methodologies by having one person share their screen on Discord and the other going through the instructions. Once this was completed, they chose to do the DataUtilities class. In order to ensure exposure to both Mock testing and non-Mock testing, Seniru created tests for calculateColumnTotal and createNumberArray methods respectively. Arushi created tests for calculateRowTotal and createNumberArray2D. The final method in the DataUtilities class (getCumulativePercentages) was left to the other subteam in order to give them exposure on Mock testing as well, as Range did not have any mock testing opportunities due to a lack of dependence on an external class or interface. With mock testing, both Arushi and Seniru decided to stick with JMock as it was the mock testing that was present on Github. 

Rian and Tanish were the second team. They first went through planning what cases there would be for each method. By doing this they were able to quickly right test cases for each of the methods. Rian and Tanish both worked on expand() method so that they both knew how to setup and correct format test cases. Rian then finished writing test cases for both shift() and getLowerBound() methods. Tanish did the other two methods of constraint() and contains().

Once both subteams had completed their methods, everyone met again to discuss all testing, to put together the test suite, and fix up errors that were present in the test suite.


# 5 Difficulties encountered, challenges overcome, and lessons learned

In the Range class test suite we encountered difficulties in determining if some methods worked correctly, as getUpperBound did not return the correct result; we surmise it gives us the lower bound instead of the upper bound. Due to this many tests like expand and so forth have test failures since we must use those getter methods, which are broken, instead of accessing the actual value inside.

To remedy this we used assertEquals, which does a deep comparison of both objects and can provide accurate results, and it decouples the dependency on getLowerBound and getUpperBound. However, it does not decouple the dependency the class has on itself, as assertEquals uses the included equals method of the given parameter, which is a Range object in this case. Similarly, using Object.deepEquals uses the equals method of the first argument, so we cannot fully decouple the dependency of the class on itself. This means that if a class incorrectly implements the equals or any other comparison method, then unit testing produces failed tests that potentially should not fail.

A major difficulty was figuring out mock testing. While some of the non-mock methods could be completed in around 10 minutes, mock testing was something that we had to wrap our head around, in terms of both how they work, and the complexity of the different test cases, as you have to involve another interface in the test case creation. Furthermore, there were a lot of issues with the exceptions that were thrown, and different procedures had to be tried to ensure that the tests weren’t receiving an error, but were instead either passes or failures. One way that was used to bypass the problem was changing a specific exception to just be “Exception”.
We learned from this lab that there is an inherent self-dependency a class has with itself, making some unit tests quite difficult to completely isolate and that to test exceptions we need to use the included @Rule to correctly test exceptions.

# 6 Comments/feedback on the lab itself

The assignment itself was informative on how black box testing is done, however several things made the assignment tedious and uncomfortable to work with. A major qualm that we faced was trying to export the eclipse project for marking. This caused various headaches and would have been nice to have clearly included instructions in the README. It was unclear as to whether the external JAR files would have been needed in our upload to Github, so more clarity would have been very essential. As well, going over how to test exceptions would have been nice but wasn’t a huge issue as various sources describe different methods of doing this. Also considering that we were new to blackbox testing, it would have been an added help to have the test cases that were included in the README, establishing the ECT calculation so that we would know based on a similar model if we were doing the right thing, plus know if we were off with the types or number of test cases selected, what the impact on the grade would be. Finally, it would have also been helpful if we were given a bit more direction regarding boundary testing, as a lot of the classes we worked with did not have this, which made us a bit apprehensive regarding whether we were doing the right thing. 

The lab was also fairly detailed in its instruction, which helped us to start testing earlier and made sure we knew what we needed to do for the submission. There were some communication errors though like “vesrsion8” in section 2.1.1 which did not communicate the java version as effectively as we would have hoped. Something we were largely very grateful for though was the JavaDoc, which was incredibly extensive and allowed us to understand exactly what we were testing for. 

Overall the lab helped deepen our understanding of black box testing and helped us to further strengthen our testing skills. For this, it was a highly important and practical experience that will better our knowledge of the concepts learned in class. 

