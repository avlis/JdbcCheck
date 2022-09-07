#!/bin/bash
rm -f JdbcCheck.class JdbcCheck.jar
javac JdbcCheck.java
jar --main-class=JdbcCheck -c -v -f JdbcCheck.jar JdbcCheck.class
