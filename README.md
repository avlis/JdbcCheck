# JdbcCheck

just a quick something to test jdbc connections.

bash shell to remember how to build this stuff.

how to call:

java -cp -cp JdbcCheck.jar:mysql-connector-java-8.0.30.jar JdbcCheck com.mysql.cj.jdbc.Driver jdbc:mysql://127.0.0.1:3306/mysql 'SELECT version()'
