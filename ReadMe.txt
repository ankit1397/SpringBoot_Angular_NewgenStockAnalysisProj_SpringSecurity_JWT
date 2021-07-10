Assignment:

              ANGULAR FRONT-END

1. Create an Angular login page with 2 input fields Username and Password fields.
2. On login, store the token in the local storage of the browser client.
3. Create a page which accepts start and end field, display the results in a line chart.

              BACKEND REST API
			  
1. Question: Given an excel file that has trade details, Parse the given excel and store them into the MySQL Database named "newgen_stock_analysis".
    i. This file needs to be loaded at the beginning of the application(Before loading clear the table data and populate)
2. Create a Login rest-API which accepts Username and Password, return the token (Note: Token valid for 30 minutes).
   Validate the username "NewgenEmployee" and password "SolutionConsultant007" (case sensitive)
3. Create a getStockAPI that accepts start and end date, get the data from the database, modify the date in the format of "YYYY-MM-dd HH:mm:ss"
   and return JSON output
   i. Create Interceptor and validate the token
   
               Key Points
			   
      FRONT-END
		   
 after 30 min, loggedIn user must be automatically logged out as token expires.
	
 only 2 pages for user as: 1) login page 2) page which accepts start and end field, display the results in a grid.
 
 Displaying stock data to user in grid. Kindly give more specific details on grid.
   
      BACKEND
 
 CSV file needs to be loaded whenever we run back-end server and to be populated in DB as fresh data clearing old table data.
	
 In back-end, only 2 RestAPIs as: 1) login rest-API 2) getStockAPI
	
 In MySQL_DB, only single table needs to created named "newgen_stock_analysis" and no other table for login credentials to be made.
 username "NewgenEmployee" and password "SolutionConsultant007" is hard coded for authentication.
	
 
   
   
   
   

   
