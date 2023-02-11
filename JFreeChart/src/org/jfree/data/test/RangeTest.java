package org.jfree.data.test;

import static org.junit.Assert.*;
import org.jfree.data.Range;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.security.InvalidParameterException;

public class RangeTest{
	private Range exampleRange;
	
	// Used to detect when an exception has been thrown
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	// Sets up a Range object for use later in every test
	@Before
	public void setUp() throws Exception {
		exampleRange = new Range(-5, 5);
	}
	
	//The expand() method is test by sending null range object and two negative bounds
    @Test 
    public void expandNullNegativeLowerNegativeUpper() throws InvalidParameterException{
    	
    		exception.expect(InvalidParameterException.class);
    		Range.expand(null, 0.5, 0.5);

    }
    
    //The expand() method is test by sending null range object, negative lower bounds and positive upper bound
    @Test 
    public void expandNullNegativeLowerPositiveUpper()throws InvalidParameterException{
    	
    		exception.expect(InvalidParameterException.class);
    		Range.expand(null, -0.5, 0.5);
    	   
    }
    
    //The expand() method is test by sending null range object, negative upper bounds and positive lower bound
    @Test 
    public void expandNullPositiveLowerNegativeUpper() throws InvalidParameterException{
    	
    		exception.expect(InvalidParameterException.class);
    		Range.expand(null, 0.5, -0.5);

       
    }
    
    //The expand() method is test by sending null range object and two positive bounds
    @Test 
    public void expandNullPostiveLowerPositiveUpper() throws InvalidParameterException{
    	
    		exception.expect(InvalidParameterException.class);
    		Range.expand(null, 0.5, 0.5);
    	
       
    }
    
    //The expand() method is test by sending valid range object and two negative bounds
    @Test
    public void expandNegativeLowerNegativeUpper() {
    	
    	Range testRange = null;
    	Range excpectedRange = new Range(-5,5);
    	
    	
    	try {
    		testRange = Range.expand(exampleRange, -0.5, -0.5 );
    	}
    	catch(Exception e){
    		fail("unepected exception");
    		
    	}
    	
    	assertEquals(excpectedRange, testRange);
        
    }
    
    //The expand() method is test by sending valid range object, negative lower bounds and positive upper bound
    @Test
    public void expandNegativeLowerPositiveUpper() {
    	
    	Range excpectedRange = new Range(-5,10);
    	Range testRange = Range.expand(exampleRange, -0.5, 0.5 );
        
    	assertEquals(excpectedRange, testRange);
    }
    
    //The expand() method is test by sending valid range object, negative upper bounds and positive lower bound
    @Test
    public void expandPositiveLowerNegativeUpper() {
    	
    	Range excpectedRange = new Range(-10,5); 
    	Range testRange = Range.expand(exampleRange, 0.5, -0.5 );
    	
    	assertEquals(excpectedRange, testRange);
       
    }
    
    //The expand() method is test by sending valid range object and two positive bounds
    @Test
    public void expandPostiveLowerPositiveUpper() {
    	
    	Range excpectedRange = new Range(-10,10);
 
    	Range testRange = Range.expand(exampleRange, 0.5, 0.5 );
       
    	assertEquals(excpectedRange, testRange);
    }
    
    // ---------------------------------------------------------------
    //The Constraint() method is test when a value below the range 
    @Test
    public void testConstraintBelowRange() {
    	double testValue = -6;
    	
        assertEquals("Return value should be -5",-5, exampleRange.constrain(testValue), 0.000000001d);		
    }
    
    //The Constraint() method is test when a value in the range 
    @Test
    public void testConstraintInRange() { 

    	double testValue = 3;
    	
        assertEquals("Return value should be 3",3, exampleRange.constrain(testValue), 0.000000001d);	
        	
    }
    
    //The Constraint() method is test when a value above the range 
    @Test
    public void testConstraintAboveRange() {

    	double testValue = 7;

        assertEquals("Return value should be 7",7, exampleRange.constrain(testValue), 0.000000001d);	
    }
    
    // Contains() method is tested by sending a value which is out of range
    @Test
    public void testContainsOutOfRange() {
    	
    	double testValue = -6;
    	
        assertFalse( exampleRange.contains(testValue));
    }
    
    // Contains() method is tested by sending a value which is within the range
    @Test
    public void testContainsInRange() {
    	double testValue1 = 3;
    	
        assertTrue( exampleRange.contains(testValue1));	
        
    }
    // ---------------------------------------------------------------
	
	// This test covers the Range parameter being null with 
	// a negative delta value
	@Test
	public void shiftNullNegativeTest() {
		exception.expect(InvalidParameterException.class);
		Range.shift(null, -1);
	}
	
	// This test covers when shift has a null Range object 
	// with a positive delta value
	@Test
	public void shiftNullPositiveTest() {
		exception.expect(InvalidParameterException.class);
		Range.shift(null, 1);
	}
	
	// Tests when shift has a null Range object with a negative delta,
	// which would cross the range over 0
	@Test
	public void shiftNullNegativeCrossZeroTest() {
		exception.expect(InvalidParameterException.class);
		Range.shift(null, -10);
	}
	
	// Tests when shift has a null Range object with a positive delta,
	// which would cross the range over 0
	@Test
	public void shiftNullPositiveCrossZeroTest() {
		exception.expect(InvalidParameterException.class);
		Range.shift(null, 10);
	}
	
	// Tests when shift has a valid Range object with a negative delta.
	// The Range object should not change from this operation
	@Test
	public void shiftNegativeTest() {
		Range testRange = Range.shift(exampleRange, -1);
		Range expected = new Range(-5.0, 5.0);
		
		assertEquals("Lower and upper bound should not change", expected,
				testRange);
	}
	
	// Tests when shift has a valid Range object with a positive delta,
	// which should shift the Range object to [-4.0, 6.0]
	@Test
	public void shiftPositiveTest() {
		Range testRange = Range.shift(exampleRange, 1);
		Range expected = new Range(-4.0, 6.0);
		
		assertEquals("Lower bound should be -4, Upper bound should 6", expected,
				testRange);
	}
	
	
	// Tests when shift has a valid Range object with a negative delta,
	// which would cause the upper or lower bound to cross over 0.
	// The Range object should not change from this operation
	@Test
	public void shiftNegativeCrossZeroTest() {
		Range testRange = Range.shift(exampleRange, -10);
		Range expected = new Range(-5.0, 5.0);
		
		assertEquals("Lower and upper bound should not change", expected,
				testRange);
	}
	
	// Tests when shift has a valid Range object with a positive delta,
	// which would cause the upper or lower bound to cross over 0.
	// The Range object should be [0.0, 10.0] after the operation is performed
	@Test
	public void shiftPositiveCrossZeroTest() {
		Range testRange = Range.shift(exampleRange, 10);
		Range expected = new Range(0.0, 10.0);
		
		assertEquals("Lower bound should be 0, Upper bound should 10", expected,
				testRange);	
	}
	
	// Tests getting the lower bound from a Range object
	@Test
	public void getLowerBoundTest() {
		assertEquals("Retrieved lower bound incorrect",
				-5, exampleRange.getLowerBound(), .000000001d);
	}
	
	// Tests getting the upper bound from a Range object
	@Test
	public void getUpperBoundTest() {
		assertEquals("Retrieved upper bound incorrect",
				5, exampleRange.getUpperBound(), .000000001d);
	}
	
	@After
	public void tearDown() throws Exception {exampleRange = null;}
}
