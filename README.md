# SimpleStudentAccount
 

Simple Student Account provides the following services:

	Create new student information
	Retrieve student information
	Delete student information
	Create an account for student	
	Retrieve all student accounts
	Retrieve specific student account
	Delete student account


### Create new student information

Creates new student information
	
	POST /studentrestapp/student	

###	Example Request
<p><pre>
{
	"id" : "some id" 
	"firstName" : "first name",
	"lastName" : "last name",
	"middleName" : "middle name",
	"phoneNumber" : "xxxxxxxxxx",
	"street" : "street",
	"city" : "city",
	"state" : "state",
	"zip" : "90000",
	"email" : "email"
}
</pre></p>

### Example Response code
200

### Retrieve Student information

Retrieves the student information
	
	GET /studentrestapp/student/{id}
	
### Example Request
N/A

### Example Response
<p><pre>
{
	"id" : "some id" 
	"firstName" : "first name",
	"lastName" : "last name",
	"middleName" : "middle name",
	"phoneNumber" : "xxxxxxxxxx",
	"street" : "street",
	"city" : "city",
	"state" : "state",
	"zip" : "90000",
	"email" : "email"
}
</pre></p>

### Delete Student information

Deletes the student information

	DELETE /studentrestapp/student/{id}
	
### Example Request
N/A

### Example Response code
200

### Create student account

creates the student account
	
	POST /studentrestapp/student/{id}/account

###	Example Request
<p><pre>
{
	"accountType" : "some account type",
	"description" : "blah blah",
	"balance" : "100.00"
}
</pre></p>

### Example Response code
200

### Retrieves all student accounts

Retrieves all student accounts

	GET /studentrestapp/student/{id}/accounts
	
### Example Request
N/A

### Example Response
<p><pre>
{
	"accountType" : "some account type",
	"description" : "blah blah",
	"balance" : "100.00"
}
{
	"accountType" : "some account type",
	"description" : "blah blah",
	"balance" : "20.00"
}
</pre></p>
	
### Retrieve student account information

Retrieves specific account information of a student

	GET /studentrestapp/student/{id}/account/{accountId}
	
### Example Request
N/A

### Example Response
<p><pre>
{
	"accountType" : "some account type",
	"description" : "blah blah",
	"balance" : "100.00"
}
</pre></p>

### Delete student account information

Deletes specific student information
	
	DELETE /studentrestapp/student/{id}/account/{accountId}
	
### Example Request
N/A

### Example Response code
200	

	
 	


	
	