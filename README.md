
Follow these steps to start the server and test the operations.

Prerequisites
Java 8 or later
Maven (optional, if using the Maven wrapper included in the project)
Starting the server
Open a terminal or command prompt.
Navigate to the project's root directory.
Run the following command:
./mvnw spring-boot:run
(On Windows, use ./mvnw.cmd instead)

Wait for the server to start successfully. You should see console output indicating that the server is running on port 8080.

Testing the operations
You can use a tool like Postman or a web browser to send requests to the exposed endpoints:

Get the exchange rate for a specific date and currency:

GET http://localhost:8080/exchange-rate/{currency}/{date}
Example: http://localhost:8080/exchange-rate/USD/2021-09-01

<img width="630" alt="test the endpoint for getting an exchange rate for a specific date and currency" src="https://user-images.githubusercontent.com/96840701/234523283-560870b7-7a5b-4967-a9a8-e90c3bd2fdf9.png">


Get the min and max average exchange rates for the last N days:

GET http://localhost:8080/exchange-rate/{currency}/min-max?days={N}
Example: http://localhost:8080/exchange-rate/USD/min-max?days=10

<img width="631" alt="test the endpoint for getting the min and max average exchange rates for the last N days" src="https://user-images.githubusercontent.com/96840701/234523408-159d18ae-18e3-44c9-9409-bb78458aea67.png">

Get the major difference between the buy and ask rates for the last N days:

GET http://localhost:8080/exchange-rate/{currency}/difference?days={N}
Example: http://localhost:8080/exchange-rate/USD/difference?days=10

<img width="631" alt="test the endpoint for getting the major difference between the buy and ask rates for the last N days" src="https://user-images.githubusercontent.com/96840701/234523599-d4d3848b-cf38-4830-b4cb-4b6d0af435ce.png">


Replace {currency} with the desired currency code and {date} with a date in the format YYYY-MM-DD. Replace {N} with the desired number of days (N <= 255).

Check the responses to verify that the functionality is working as expected.
