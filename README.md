# SolarCity

>>Track: Back-end

>>Documentation: For full documentation refer to SolarCity_App_Documentation provided as an attachment in the submission email.

>>Code: https://github.com/tejalshah84/SolarCity

>>Hosting on AWS: http://ec2-54-183-170-1.us-west-1.compute.amazonaws.com/solarprospect

>>Problem: The SolarCity sales team gets a lot of client inquiries regarding solar panels and their prices. Some of these clients are third party distributors while some are individual homeowners who are looking for a convincing reason to switch to solar energy. The sales team would like to manage the inquiries they get and schedule proper time with the client with relevant and adequate information rather than having a generic 1-800 number for client inquiries.

>>Solution: 
For Individual Homeowners - There is a web portal for them to understand the overall benefits of switching to solar energy and submit an inquiry via a form to SolarCity. The inquiry form allows the sales representative to call the client back after thoroughly researching the weather in the clients area. Since my track is back-end, the html page is static. Ideally, the form would be dynamic with validations and would have posted the inquiry data into the database using the REST API. There could also be a dynamic solar panel cost estimator available in the web portal as an additional self-help feature. But since this functionality is front-end heavy, it has not been built.
For Distributors - There is a REST API available through which their systems can schedule an inquiry or inquire about different types of solar panels and their cost per watt.

>>REST API:

***** http://<host><port>/solarprospect *****

GET - A GET call to this url will serve a static html page which lists the benefit of switching to solar energy and it will give a static form for submitting an inquiry.

POST - A POST call to this url takes in data in JSON format. The backend accepts the data and saves it in a MYSQL database. A post call could for instance use the following RAW JSON format to submit the data. Ideally, if the inquiry form in the front-end was not static it would have used this POST call to submit the data to the back-end via JavaScript or JSP. This API call can be tested using Postman.

Format of JSON Submission String in POST Call:
{"lastName":"Smith","firstName":"John","age":3,"email":"johnsmith@gmail.com","phoneNumber":"2123456789",
"addressStreet":"450 Mathilda","addressCity":"Sunnyvale","addressState":"CA","addressZip":78450,"installation":true, "reasonInterested":"save electricity costs and be environmentally friendly"}

Format of JSON Response on Successful POST call:
{"respMsg":"Thank you John Smith for your inquiry. You will be contacted soon by a SolarCity sales representative at 2123456789."}

Format of JSON Response on Failed POST call:
{"respMsg":"Apologies for the inconvenience. We were not able to register your inquiry at this time. Please try again later or call a sales representative at 1-800-SLR-CITY."}



***** http://<host><port>/solarcells?priceLimit=3 *****

GET - A GET call to this url takes an attribute priceLimit (int) as a parameter. This could be thought of as a max budget the client is willing to consider. Based on this attribute, the GET call returns a list (array) of all the different types of solar cells and their corresponding prices as long as the price per watt for the solar cells falls within the budget. The static data in the DB used for this project is fictional and all prices fall within the range of 1 to 14.

Format of JSON Response on Successful GET call:
[{"solarCellType":"Buried contact solar cell","solarCellCode":null,"pricePerWatt":3},{"solarCellType":"Luminescent solar concentrator cell","solarCellCode":"LSC","pricePerWatt":3},{"solarCellType":"Perovskite solar cell","solarCellCode":null,"pricePerWatt":2},{"solarCellType":"Plastic solar cell","solarCellCode":null,"pricePerWatt":1},{"solarCellType":"Polymer solar cell","solarCellCode":null,"pricePerWatt":3},{"solarCellType":"Quantum dot solar cell","solarCellCode":null,"pricePerWatt":2},{"solarCellType":"Thin-film solar cell","solarCellCode":"TFSC","pricePerWatt":2}]

POST - A POST call to this URL will give the same output as the GET call as long as the parameter priceLimit is specified.


Third-party Libraries: Three main third party libraries were used to make this client-server application:

1) Hikari CP - For DB connection pooling

2) Jackson - For JSON generation and parsing

3) MySQL Connector - For connecting to MySQL DB

4) Logging - Other libraries for logging



Commands to Run Application:

1) Setup MySQL DB with username “root” and password “mysql123”. Alternatively change the three lines in the HikariDBPool class in database package according to how and where your DB is setup.
config.setJdbcUrl("jdbc:mysql://"+"localhost/solarcity?useSSL=false&useUnicode=yes&characterEncoding=UTF-8");
			config.setUsername("root");
			config.setPassword("mysql123");
      
2) Create the DB and tables with data using the SQL provided in the file TableSetup.sql. MySQL Workbench is a convenient GUI to visualize the DB and run commands.

3) Clone the repo from github and setup the code to run with some web server. I used Tomcat. Other options are Undertow, Nginx, etc. The folder structure can be used as is with Tomcat.

4) Run the web server

5) Test the API (/solarprospect or /solarcells) using postman or browser. Use the hostname according to where the application is hosted.



Technical Choices:

~ I used Java for this project for two reasons: 

1) I have most experience with Java. I have been using it for the past 1.5 years in all my classes and also used it during my internship to develop an email parser and an Android app.
2) It demonstrates my ability to think in object oriented way

~ I left out:

1) Validations in the front-end and back-end code due to lack of time
2) Reading DB configurations from a properties file. Ideally, the config values should not be hard coded into the code, they should be read from properties file so they can be easily maintained. An example of using this practice can be seen in the other code sample that I have provided.
3) Reading SQL queries from a properties file. Instead of coding the queries as static variables in the class, they could be read from a properties file for easier maintenance and readability of code.
4) The html page should have ideally been dynamic. JavaScript should have been used to submit the form data to the back-end. I skipped this as focusing on the back-end track but in the past I have worked on full-stack projects where I made a responsive chat application using bootstrap.


