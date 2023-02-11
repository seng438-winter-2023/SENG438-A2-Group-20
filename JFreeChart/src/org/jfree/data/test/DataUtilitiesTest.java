package org.jfree.data.test;

import org.jmock.Mockery;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.AfterClass;
import org.junit.Before;
import org.jmock.Expectations;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;

import org.jfree.data.DataUtilities;
import org.jfree.data.KeyedValues;
import org.jfree.data.Values2D;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class DataUtilitiesTest extends DataUtilities {
	private Mockery mockingContext;
	private Mockery context = new Mockery();
	
	// Used to detect when an exception has been thrown
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	// Makes sure the mocking object is clean for every test
	@Before
	public void setupClass() {
		mockingContext = new Mockery();
	}
	
	//Create 2D Array with valid inputs
	@Test
	public void createNumberArray2Ddouble() {
		double [][] data = {{1.0, 2.0, 3.0}, {4.0, 5.0, 6.0}};
		Number[][] expected = {{1.0, 2.0, 3.0}, {4.0, 5.0, 6.0}};
		Number[][] result = DataUtilities.createNumberArray2D(data);
		assertArrayEquals(expected, result);
	}
	
	//Create 2D Array with negative values
	@Test
	public void createNumberArray2DNegative() {
		double [][] data = {{-1.0, -2.0, -3.0}, {-6.0, -4.0, -2.0}};
		Number[][] expected = {{-1.0, -2.0, -4.0}, {-6.0, -4.0, -2.0}};
		Number[][] result = DataUtilities.createNumberArray2D(data);
		assertArrayEquals(expected, result);
	}
	
	//Create 2D Array with empty arrays (null)
	@Test
	public void createNumberArray2DInvalidInput() {
		int i = 0;
		try {
			double [][] data = null;
			Number[][] result = DataUtilities.createNumberArray2D(data);
		}catch(Exception e)
		{
			i = 1;
		}
	    assertEquals(1, i);
	}
	
	//Create 2D Array with single size input 
	@Test 
	public void createNumberArray2DSingleInput()
	{
		double[][] data = {{1.0}};
		Number[][] result = DataUtilities.createNumberArray2D(data);
		Number[][] expected = {{1.0}};
		assertArrayEquals(expected, result);
		
	}
	
	// -----------------------------------------------------------------
	//Calculates row total with 2 correct input values
	@Test
	public void calculateRowTotalForTwoValues() {
	    final Values2D values = mockingContext.mock(Values2D.class);
	     mockingContext.checking(new Expectations() {
	         {
	             one(values).getColumnCount();
	             will(returnValue(2));
	             one(values).getValue(0, 0);
	             will(returnValue(7.0));
	             one(values).getValue(0, 1);
	             will(returnValue(3.0));
	         }
	     });
	     double result = DataUtilities.calculateRowTotal(values, 0);
	     assertEquals(10.0, result, 0.000000001d);
	 }
	 
	 //Calculates row total with one null value and two correct value 
	 @Test
	 public void calculateRowTotalForNullVal() {
	       final Values2D values = mockingContext.mock(Values2D.class);
	        mockingContext.checking(new Expectations() {
	            {
	                one(values).getColumnCount();
	                will(returnValue(3));
	                one(values).getValue(0, 0);
	                will(returnValue(3.0));
	                one(values).getValue(0, 1);
	                will(returnValue(null));
	                one(values).getValue(0, 2);
	                will(returnValue(6.0));
	            }
	        });
	        double result = DataUtilities.calculateRowTotal(values, 0);
	        assertEquals(9.0, result, 0.000000001d);
	    }
	 
	 //Calculates row total with one null value at the end and two correct value 
	 @Test
	 public void calculateRowTotalForNullValAtEnd() {
	       final Values2D values = mockingContext.mock(Values2D.class);
	        mockingContext.checking(new Expectations() {
	            {
	                one(values).getColumnCount();
	                will(returnValue(4));
	                one(values).getValue(0, 0);
	                will(returnValue(3.0));
	                one(values).getValue(0, 1);
	                will(returnValue(5.0));
	                one(values).getValue(0, 2);
	                will(returnValue(null));
	            }
	        });
	        double result = DataUtilities.calculateRowTotal(values, 0);
	        assertEquals(8.0, result, 0.000000001d);
	    }
	 
	 
	 //Calculates row total with all Null values 
	 @Test
	 public void calculateRowTotalForAllNullVal() {
	       final Values2D values = mockingContext.mock(Values2D.class);
	        mockingContext.checking(new Expectations() {
	            {
	                one(values).getColumnCount();
	                will(returnValue(3));
	                one(values).getValue(0, 0);
	                will(returnValue(null));
	                one(values).getValue(0, 1);
	                will(returnValue(null));
	                one(values).getValue(0, 2);
	                will(returnValue(null));
	            }
	        });
	        double result = DataUtilities.calculateRowTotal(values, 0);
	        assertEquals(0.0, result, 0.000000001d);
	    }
	
	 
	 //Calculates row total with one 0 value and two correct value 
	 @Test
	 public void calculateRowTotalForOneZeroVal() {
	       final Values2D values = mockingContext.mock(Values2D.class);
	        mockingContext.checking(new Expectations() {
	            {
	                one(values).getColumnCount();
	                will(returnValue(3));
	                one(values).getValue(0, 0);
	                will(returnValue(3.0));
	                one(values).getValue(0, 1);
	                will(returnValue(0.0));
	                one(values).getValue(0, 2);
	                will(returnValue(6.0));
	            }
	        });
	        
	        double result = DataUtilities.calculateRowTotal(values, 0);
	        assertEquals(9.0, result, 0.000000001d);
	    }
	 
	 //Calculates row total with all zero values 
	 @Test
	 public void calculateRowTotalForAllZeroVal() {
	       final Values2D values = mockingContext.mock(Values2D.class);
	        mockingContext.checking(new Expectations() {
	            {
	                one(values).getColumnCount();
	                will(returnValue(3));
	                one(values).getValue(0, 0);
	                will(returnValue(0.0));
	                one(values).getValue(0, 1);
	                will(returnValue(0.0));
	                one(values).getValue(0, 2);
	                will(returnValue(0.0));
	            }
	        });
	        double result = DataUtilities.calculateRowTotal(values, 0);
	        assertEquals(0.0, result, 0.000000001d);
	    }
	 
	 
	 //Calculates row total with negative Index
	 @Test(expected = IndexOutOfBoundsException.class)
	 public void calculateRowTotalWithNegativeIndex() {
		 exception.expect(InvalidParameterException.class);
	       final Values2D values = mockingContext.mock(Values2D.class);
	        mockingContext.checking(new Expectations() {
	            {
	            	oneOf(values).getColumnCount();
	                will(returnValue(2));
	                oneOf(values).getValue(-1, 0);
	                will(returnValue(4.0));
	                oneOf(values).getValue(-1, 1);
	                will(returnValue(3.0));
	                
	            }
	        });
	        double result = DataUtilities.calculateRowTotal(values, -1);
	        assertEquals(7.0, result, 0.01);
	    } 
	 
	 
	 //Calculates row total with out of bounds index
	 @Test (expected = IndexOutOfBoundsException.class)
	 public void calculateRowTotalWithOOBIndex() {
		 exception.expect(InvalidParameterException.class);
		 int i =0;
	       final Values2D values = mockingContext.mock(Values2D.class);
	        mockingContext.checking(new Expectations() {
	            {
	                oneOf(values).getColumnCount();
	                will(returnValue(3));
	                oneOf(values).getValue(0, 0);
	                will(returnValue(4.0));
	                oneOf(values).getValue(0, 1);
	                will(returnValue(3.0));
	            }
	        }); 
	        try {
	        	double result = DataUtilities.calculateRowTotal(values, 50);
	        }catch(Exception e)
	        {
	        	i = 1;
	        }
	    	
	        assertEquals(1, i, 0.000000001d);
	       
	 }
		 
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}


	//Create Array with Multiple Valid Input that has Zero, Negative Values, and Positive Values
    @Test
    public void testCreateNumberArrayValidMultipleInput() {
        double[] data = {0.0, 1.0, -5.0, -6};
        Number[] expected = {0.0, 1.0, -5.0, -6};
        Number[] result = createNumberArray(data);
        assertArrayEquals(expected, result);
    }
    
    //Create Array with Multiple Valid Input that is only Positive
    @Test
    public void testCreateNumberArrayPositiveMultipleInput() {
        double[] data = {1.0, 2.0, 3.0, 4.0};
        Number[] expected = {1.0, 2.0, 3.0, 4.0};
        Number[] result = createNumberArray(data);
        assertArrayEquals(expected, result);
    }
    
    //Create Array with One Valid Input that is Positive
    @Test
    public void testCreateNumberArrayOnePositiveValidInput() {
    	double[] data = {1.0};
    	Number[] expected = {1.0};
    	Number[] result = createNumberArray(data);
    	assertArrayEquals(expected, result);
    }
    
    //Create Array with One Valid Input that is Negative
    @Test
    public void testCreateNumberArrayOneNegativeValidInput() {
    	double[] data = {-1.0};
    	Number[] expected = {-1.0};
    	Number[] result = createNumberArray(data);
    	assertArrayEquals(expected, result);
    }

    
    //Create Empty Array
    @Test
    public void testCreateNumberArrayEmptyInput() {
        double[] data = {};
        Number[] expected = {};
        Number[] result = createNumberArray(data);
        assertArrayEquals(expected, result);
    }

    //Create Array where Data is Null to test Error
    @Test
    public void testCreateNumberArrayNullInput() { 
        double[] data = null;
        try {
           createNumberArray(data);
           fail("Should throw InvalidParameterException");
        } catch (Exception e) {
           assertEquals("Invalid data object is passed in", e.getMessage());
        }
    } 

  //Create Array with Multiple Valid Input that is only Negative
    @Test
    public void testCreateNumberArrayNegatives() {
        double[] data = {-1.0, -2.5, -3.0, -4.7};
        Number[] expected = {-1.0, -2.5, -3.0, -4.7};
        Number[] result = createNumberArray(data);
        assertArrayEquals(expected, result);
    }
    
    //End of CreateNumberArrayTests. Beginning of calculateColumnTotalTests
    
    
    //Calculates One Column Total with 3 Values at Index 0 with Only Positive Inputs 
    @Test
    public void testCalculateColumnTotalPositiveValidInput() {
        final Values2D data = context.mock(Values2D.class);
        context.checking(new Expectations() {{
            oneOf(data).getRowCount(); will(returnValue(3));
            oneOf(data).getValue(0, 0); will(returnValue(10));
            oneOf(data).getValue(1, 0); will(returnValue(20.5));
            oneOf(data).getValue(2, 0); will(returnValue(30.5));
        }});
        int column = 0;
        double expectedResult = 61.0;
        double result = calculateColumnTotal(data, column);
        assertEquals(expectedResult, result, 0.01);
        context.assertIsSatisfied();
    }
    
    //Calculates One Column Total with 3 Values at Index 0 with Only Negative Inputs 
    @Test
    public void testCalculateColumnTotalNegativeValidInput() {
        final Values2D data = context.mock(Values2D.class);
        context.checking(new Expectations() {{
            oneOf(data).getRowCount(); will(returnValue(3));
            oneOf(data).getValue(0, 0); will(returnValue(-1));
            oneOf(data).getValue(1, 0); will(returnValue(-2.3));
            oneOf(data).getValue(2, 0); will(returnValue(-7));
        }});
        int column = 0;
        double expectedResult = -10.3;
        double result = calculateColumnTotal(data, column);
        assertEquals(expectedResult, result, 0.01);
        context.assertIsSatisfied();
    }
    
    //Calculates One Column Total with 3 Values at Index 0 with Positive, Negative, and Zero Inputs
    @Test
    public void testCalculateColumnTotalCombinedInput() {
        final Values2D data = context.mock(Values2D.class);
        context.checking(new Expectations() {{
            oneOf(data).getRowCount(); will(returnValue(3));
            oneOf(data).getValue(0, 0); will(returnValue(0));
            oneOf(data).getValue(1, 0); will(returnValue(-2.3));
            oneOf(data).getValue(2, 0); will(returnValue(7));
        }});
        int column = 0;
        double expectedResult = 4.7;
        double result = calculateColumnTotal(data, column);
        assertEquals(expectedResult, result, 0.01);
        context.assertIsSatisfied();
    }
     
    
    //Calculates One Column Total with 3 Values at Index 0 with One Null Value 
	 @Test
	 public void calculateColumnTotalForNullValue() {
	       final Values2D data = context.mock(Values2D.class);
	        context.checking(new Expectations() {
	            {
	                oneOf(data).getRowCount(); will(returnValue(3));
	                oneOf(data).getValue(0, 0); will(returnValue(10));
	                oneOf(data).getValue(1, 0); will(returnValue(null));
	                oneOf(data).getValue(2, 0); will(returnValue(20));
	        }});
	        int column = 0;
	        double expectedResult = 30;
	        double result = DataUtilities.calculateColumnTotal(data, column);
	        assertEquals(expectedResult, result, 0.01);
	        context.assertIsSatisfied();
	    }
	 
	 //Calculates One Column Total with 3 Values at Index 0 with Only Null Values
	 @Test
	 public void calculateColumnTotalForAllNullValue() {
	       final Values2D data = context.mock(Values2D.class);
	        context.checking(new Expectations() {
	            {
	                oneOf(data).getRowCount(); will(returnValue(3));
	                oneOf(data).getValue(0, 0); will(returnValue(null));
	                oneOf(data).getValue(1, 0); will(returnValue(null));
	                oneOf(data).getValue(2, 0); will(returnValue(null));
	        }});
	        int column = 0;
	        double expectedResult = 0;
	        double result = DataUtilities.calculateColumnTotal(data, column);
	        assertEquals(expectedResult, result, 0.01);
	        context.assertIsSatisfied();
	    }
	 
	 //Calculates One Column Total with 3 Values where one is Zero and the rest are positive
	 @Test
	 public void calculateColumnTotalForZeroValue() {
	       final Values2D data = context.mock(Values2D.class);
	        context.checking(new Expectations() {
	            {
	                oneOf(data).getRowCount(); will(returnValue(3));
	                oneOf(data).getValue(0, 0); will(returnValue(10));
	                oneOf(data).getValue(1, 0); will(returnValue(0));
	                oneOf(data).getValue(2, 0); will(returnValue(20));
	        }});
	        int column = 0;
	        double expectedResult = 30;
	        double result = DataUtilities.calculateColumnTotal(data, column);
	        assertEquals(expectedResult, result, 0.01);
	        context.assertIsSatisfied();
	    }
	 
	//Calculates One Column Total with 3 Values that are all Zero
	 @Test
	 public void calculateColumnTotalForAllZeroValue() {
	       final Values2D data = context.mock(Values2D.class);
	        context.checking(new Expectations() {
	            {
	                oneOf(data).getRowCount(); will(returnValue(3));
	                oneOf(data).getValue(0, 0); will(returnValue(0));
	                oneOf(data).getValue(1, 0); will(returnValue(0));
	                oneOf(data).getValue(2, 0); will(returnValue(0));
	        }});
	        int column = 0;
	        double expectedResult = 0;
	        double result = DataUtilities.calculateColumnTotal(data, column);
	        assertEquals(expectedResult, result, 0.01);
	        context.assertIsSatisfied();
	    }
	 
	
	//Calculates Column Total that is Empty
	@Test
	public void calculateColumnTotalEmpty() {
		final Values2D data = context.mock(Values2D.class);
		context.checking(new Expectations() {
			{
				one(data).getRowCount();
				will(returnValue(0));
			}
		});
		int column = 0;
		double expectedResult = 0;
		double result = DataUtilities.calculateColumnTotal(data, column);
		assertEquals(expectedResult, result, .01);
		context.assertIsSatisfied();
	}
	
	//Calculates Column Total where Data is Null/Invalid to test Error
	@Test(expected = NullPointerException.class)
	public void calculateColumnTotalNull() {
		DataUtilities.calculateColumnTotal(null, 0);
		context.assertIsSatisfied();
	}
	
	//Calculates Column Total where Columns is One Extra than Index (Out of Bounds Positive) to look for error and if OutofBoundsException is Thrown
	@Test(expected = IndexOutOfBoundsException.class)
	public void testCalculateColumnTotalOutOfBoundsPositive() {
		final Values2D data = context.mock(Values2D.class);
		context.checking(new Expectations() {
			{
				oneOf(data).getRowCount(); will(returnValue(2));
				oneOf(data).getValue(0, 1); will(returnValue(10.0));
				oneOf(data).getValue(1, 1); will(returnValue(20.0));
			}
		});
		int column = 1;
		double expectedResult = 30.0;
		double result = DataUtilities.calculateColumnTotal(data, column);
		assertEquals(expectedResult, result, 0.01);
	}
	
	// Calculates Column Total where Columns is One Extra than Index
	// (Out of Bounds Positive) to look for error and if OutofBoundsException is Thrown
	@Test(expected = IndexOutOfBoundsException.class)
	public void testCalculateColumnTotalOutOfBoundsNegative() {
		final Values2D data = context.mock(Values2D.class);
		context.checking(new Expectations() {
			{
				oneOf(data).getRowCount(); will(returnValue(2));
				oneOf(data).getValue(0, -1); will(returnValue(10.0));
				oneOf(data).getValue(1, -1); will(returnValue(20.0));
			}
		});
		int column = -1;
		double expectedResult = 30.0;
		double result = DataUtilities.calculateColumnTotal(data, column);
		assertEquals(expectedResult, result, 0.01);
	}
	// -----------------------------------------------------------------
	
	// Tests when getCumulativePercentage is given a null KeyedValues object
	// Should throw an InvalidParameterException
	@Test
	public void getCumulativePercentagesNullTest() {
		exception.expect(InvalidParameterException.class);
		DataUtilities.getCumulativePercentages(null);
	}
	
	// Tests when getCumulativePercentage is given a valid KeyedValues object
	// which only contains null values
	// Should throw an InvaludParameterException
	@Test
	public void getCumulativePercentagesWithNullValuesTest() {
		final KeyedValues values = mockingContext.mock(KeyedValues.class);
		mockingContext.checking(new Expectations() {
	        {
	        	allowing(values).getItemCount();
	        	will(returnValue(2));
	            allowing(values).getKey(0);
	            will(returnValue(0));
	            allowing(values).getKey(1);
	            will(returnValue(1));
	            allowing(values).getKey(2);
	            will(returnValue(2));
	            allowing(values).getValue(0);
	            will(returnValue(null));
	            allowing(values).getValue(1);
	            will(returnValue(null));
	            allowing(values).getValue(2);
	            will(returnValue(null));
	        }
	    });
		
		exception.expect(InvalidParameterException.class);
		DataUtilities.getCumulativePercentages(values);
	}
	
	//Only the first value send in is null, the rest is negative for getCumulativePercent()
	@Test 
    public void getCumulativePercentageWithAllFirstNullValues() throws InvalidParameterException{
    	
    	final KeyedValues keyValues = mockingContext.mock(KeyedValues.class);
    	
    	mockingContext.checking(new Expectations() {
    		{
    			allowing(keyValues).getItemCount();
    			will(returnValue(3));
    			allowing(keyValues).getKey(0);
    			will(returnValue(0));
    			allowing(keyValues).getKey(1);
    			will(returnValue(1));
    			allowing(keyValues).getKey(2);
    			will(returnValue(2));
    			allowing(keyValues).getValue(0);
    			will(returnValue(null));
    			allowing(keyValues).getValue(1);
    			will(returnValue(-1));
    			allowing(keyValues).getValue(2);
    			will(returnValue(-2));
    		}
    	});
    	
    	exception.expect(InvalidParameterException.class);
    	KeyedValues resultkeys = DataUtilities.getCumulativePercentages(keyValues);

       
    }
		
	// The method getCumulativePercent() is tested with second value being null and the rest negative
	@Test 
    public void getCumulativePercentageWithAllSecondNullValues() throws InvalidParameterException{
    	
    	final KeyedValues keyValues = mockingContext.mock(KeyedValues.class);
    	
    	mockingContext.checking(new Expectations() {
    		{
    			allowing(keyValues).getItemCount();
    			will(returnValue(3));
    			allowing(keyValues).getKey(0);
    			will(returnValue(0));
    			allowing(keyValues).getKey(1);
    			will(returnValue(1));
    			allowing(keyValues).getKey(2);
    			will(returnValue(2));
    			allowing(keyValues).getValue(0);
    			will(returnValue(-1));
    			allowing(keyValues).getValue(1);
    			will(returnValue(null));
    			allowing(keyValues).getValue(2);
    			will(returnValue(-2));
    		}
    	});
    	
    	exception.expect(InvalidParameterException.class);
    	KeyedValues resultkeys = DataUtilities.getCumulativePercentages(keyValues);

       
    }
		
	//Third value for getCumulativePercent() method is null, rest is negative
	@Test
    public void getCumulativePercentageWithAllThirdNullValues() throws InvalidParameterException{
    	
    	final KeyedValues keyValues = mockingContext.mock(KeyedValues.class);
    	
    	mockingContext.checking(new Expectations() {
    		{
    			allowing(keyValues).getItemCount();
    			will(returnValue(3));
    			allowing(keyValues).getKey(0);
    			will(returnValue(0));
    			allowing(keyValues).getKey(1);
    			will(returnValue(1));
    			allowing(keyValues).getKey(2);
    			will(returnValue(2));
    			allowing(keyValues).getValue(0);
    			will(returnValue(-1));
    			allowing(keyValues).getValue(1);
    			will(returnValue(-2));
    			allowing(keyValues).getValue(2);
    			will(returnValue(null));
    		}
    	});
    	
    	exception.expect(InvalidParameterException.class);
    	KeyedValues resultkeys = DataUtilities.getCumulativePercentages(keyValues);
    }
	
	//In this test, all value are negative in the getCumulativePercent() method to check InvalidParameterException
	@Test 
    public void getCumulativePercentageWithAllNegativeValues() throws InvalidParameterException{
    	
    	final KeyedValues keyValues = mockingContext.mock(KeyedValues.class);
    	
    	mockingContext.checking(new Expectations() {
    		{
    			allowing(keyValues).getItemCount();
    			will(returnValue(3));
    			allowing(keyValues).getKey(0);
    			will(returnValue(0));
    			allowing(keyValues).getKey(1);
    			will(returnValue(1));
    			allowing(keyValues).getKey(2);
    			will(returnValue(2));
    			allowing(keyValues).getValue(0);
    			will(returnValue(-1));
    			allowing(keyValues).getValue(1);
    			will(returnValue(-2));
    			allowing(keyValues).getValue(2);
    			will(returnValue(-3));
    		}
    	});
    	
    	exception.expect(InvalidParameterException.class);
    	KeyedValues resultkeys = DataUtilities.getCumulativePercentages(keyValues);

       
    }
	
	// Tests when getCumulativePercentage is given a valid KeyedValues object
	// which contains a null value for the first key.
	// All other values are positive
	// Should throw an InvaludParameterException
	@Test
	public void getCumulativePercentagesWithNullStartPosTest() {
		final KeyedValues values = mockingContext.mock(KeyedValues.class);
		mockingContext.checking(new Expectations() {
	        {
	        	allowing(values).getItemCount();
	        	will(returnValue(3));
	            allowing(values).getKey(0);
	            will(returnValue(0));
	            allowing(values).getKey(1);
	            will(returnValue(1));
	            allowing(values).getKey(2);
	            will(returnValue(2));
	            allowing(values).getValue(0);
	            will(returnValue(null));
	            allowing(values).getValue(1);
	            will(returnValue(9));
	            allowing(values).getValue(2);
	            will(returnValue(2));
	        }
	    });
		
		exception.expect(InvalidParameterException.class);
		DataUtilities.getCumulativePercentages(values);
	}
	
	// Tests when getCumulativePercentage is given a valid KeyedValues object
	// which contains a null value for the middle key
	// All other values are positive
	// Should throw an InvaludParameterException
	@Test
	public void getCumulativePercentagesWithNullMidPosTest() {
		final KeyedValues values = mockingContext.mock(KeyedValues.class);
		mockingContext.checking(new Expectations() {
	        {
	        	allowing(values).getItemCount();
	        	will(returnValue(3));
	            allowing(values).getKey(0);
	            will(returnValue(0));
	            allowing(values).getKey(1);
	            will(returnValue(1));
	            allowing(values).getKey(2);
	            will(returnValue(2));
	            allowing(values).getValue(0);
	            will(returnValue(5));
	            allowing(values).getValue(1);
	            will(returnValue(null));
	            allowing(values).getValue(2);
	            will(returnValue(2));
	        }
	    });
		
		exception.expect(InvalidParameterException.class);
		DataUtilities.getCumulativePercentages(values);
	}
	
	// Tests when getCumulativePercentage is given a valid KeyedValues object
	// which contains a null value for the last key
	// All other values are positive
	// Should throw an InvaludParameterException
	@Test
	public void getCumulativePercentagesWithNullEndPosTest() {
		final KeyedValues values = mockingContext.mock(KeyedValues.class);
		mockingContext.checking(new Expectations() {
	        {
	        	allowing(values).getItemCount();
	        	will(returnValue(3));
	            allowing(values).getKey(0);
	            will(returnValue(0));
	            allowing(values).getKey(1);
	            will(returnValue(1));
	            allowing(values).getKey(2);
	            will(returnValue(2));
	            allowing(values).getValue(0);
	            will(returnValue(5));
	            allowing(values).getValue(1);
	            will(returnValue(9));
	            allowing(values).getValue(2);
	            will(returnValue(null));
	        }
	    });
		
		exception.expect(InvalidParameterException.class);
		DataUtilities.getCumulativePercentages(values);
	}
	
	// Tests when getCumulativePercentage is given a valid KeyedValues object
	// which contains only positive numbers
	// Should give {{0, 0.3125}, {1, 0.875}, {2, 1.0}}
	@Test
	public void getCumulativePercentagesPosTest() {
		final KeyedValues values = mockingContext.mock(KeyedValues.class);
		mockingContext.checking(new Expectations() {
	        {
	        	allowing(values).getItemCount();
	        	will(returnValue(3));
	            allowing(values).getKey(0);
	            will(returnValue(0));
	            allowing(values).getKey(1);
	            will(returnValue(1));
	            allowing(values).getKey(2);
	            will(returnValue(2));
	            allowing(values).getValue(0);
	            will(returnValue(5));
	            allowing(values).getValue(1);
	            will(returnValue(9));
	            allowing(values).getValue(2);
	            will(returnValue(2));
	        }
	    });
		
		KeyedValues ret = DataUtilities.getCumulativePercentages(values);
		
		assertEquals("Should return 0.3125", 0.3125, ret.getValue(0));
		assertEquals("Should return 0.875", 0.875, ret.getValue(1));
		assertEquals("Should return 1.0", 1.0, ret.getValue(2));
	}
	
}
