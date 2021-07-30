package webd4201.NarvaezF;
/**
 * @author Fabian Narvaez
 * @name Deliverable 1 - Mark Class
 * @date 2021-01-12
 */
//import Decimal Format
import java.text.DecimalFormat;
public class Mark {
	//class attributes
	/**
	 * It stores the minimum GPA 
	 */
	static final float MINIMUM_GPA = 0.0f;
	/**
	 * It stores the maximum GPA
	 */
	static final float MAXIMUM_GPA = 5.0f;
	/**
	 * It stores the GPA
	 */
	static final DecimalFormat GPA = new DecimalFormat("#0.00#");
	
	//instance attributes
	/**
	 * instance attribute for course code
	 */
	private String courseCode;
	/**
	 * instance attribute for course Name
	 */
	private String courseName;
	/**
	 * instance attribute for result
	 */
	private int result;
	/**
	 * instance for gpa Weighting
	 */
	private float gpaWeighting;
	
	
	/**
	 * Parameterized Constructor
	 * @param courseCode
	 * @param courseName
	 * @param result
	 * @param gpaWeighting
	 */
	public Mark(String courseCode, String courseName, int result, float gpaWeighting) {		
		//set the mark attributes using the set Methods
		setCourseCode(courseCode);
		setCourseName(courseName);
		setResult(result);
		setGpaWeighting(gpaWeighting);
	}
	
	/**
	 * Overide the toString method for displaying the attributes well formatted
	 */
	@Override
	public String toString() {
		//return and display as one string
		return "\n" + getCourseCode() + "\t" + getCourseName() + "\t\t" + getResult() + "\t" + getGpaWeighting() ;
	}
	
	/**
	 * accessor for course code
	 * @return the courseCode
	 */
	public String getCourseCode() {
		return courseCode;
	}
	/**
	 * mutator for course code
	 * @param courseCode the courseCode to set
	 */
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	/**
	 * accessor for course Name
	 * @return the courseName
	 */
	public String getCourseName() {
		return courseName;
	}
	/**
	 * mutator for course Name
	 * @param courseName the courseName to set
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	/**
	 * accessor for result
	 * @return the result
	 */
	public int getResult() {
		return result;
	}
	/**
	 * mutator for result
	 * @param result the result to set
	 */
	public void setResult(int result) {
		this.result = result;
	}
	/**
	 * accessor for gpa weighting
	 * @return the gpaWeighting
	 */
	public float getGpaWeighting() {
		return gpaWeighting;
	}
	/**
	 * mutator for gpa weighting
	 * @param gpaWeighting the gpaWeighting to set
	 */
	public void setGpaWeighting(float gpaWeighting) {
		this.gpaWeighting = gpaWeighting;
	}
	
	
	
	

}
