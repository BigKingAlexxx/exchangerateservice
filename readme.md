Usage:
1. I want to retrieve the ECB reference rate for a currency pair, e.g. USD/EUR or HUF/EUR 
--> http://localhost:8080/latest?symbols=USD,EUR

2. I want to retrieve an exchange rate for other pairs, e.g. HUF/USD. 
--> http://localhost:8080/latest?symbols=HUF,USD

3. I want to retrieve a list of supported currencies and see how many times they were requested 
--> http://localhost:8080/latest

4. I want to convert an amount in a given currency to another, e.g. 15 EUR = ??? GBP 
--> http://localhost:8080/convert?symbols=15,EUR,GBP

5. I want to retrieve a link to a public website showing an interactive chart for a given currency pair 
--> http://localhost:8080/chart?symbols=USD,EUR