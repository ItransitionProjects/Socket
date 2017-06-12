package server;

/**
 * The Class PersonJob. Stores info about persons job.
 */
public class PersonJob {
	
	/** The employee. */
	private String employee;
	
	/** The job experience. */
	private String jobExperience;
	
	/** The job position. */
	private String jobPosition;

	/**
	 * Gets the employee.
	 *
	 * @return the employee
	 */
	public String getEmployee() {
		return employee;
	}

	/**
	 * Sets the employee.
	 *
	 * @param employee the new employee
	 */
	public void setEmployee(String employee) {
		this.employee = employee;
	}

	/**
	 * Gets the job experience.
	 *
	 * @return the job experience
	 */
	public String getJobExperience() {
		return jobExperience;
	}

	/**
	 * Sets the job experience.
	 *
	 * @param jobExperience the new job experience
	 */
	public void setJobExperience(String jobExperience) {
		this.jobExperience = jobExperience;
	}

	/**
	 * Gets the job position.
	 *
	 * @return the job position
	 */
	public String getJobPosition() {
		return jobPosition;
	}

	/**
	 * Sets the job position.
	 *
	 * @param jobPosition the new job position
	 */
	public void setJobPosition(String jobPosition) {
		this.jobPosition = jobPosition;
	}

	public String toString() {
		return "PersonJob [employee=" + employee + ", jobExperience=" + jobExperience + ", jobPosition=" + jobPosition
				+ "]";
	}

}
