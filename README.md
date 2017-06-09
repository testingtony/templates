[![Build Status](https://travis-ci.org/testingtony/templates.svg?branch=master)](https://travis-ci.org/testingtony/templates)
[![Dependency Status](https://www.versioneye.com/user/projects/593aa5e0822da00068d167ee/badge.svg?style=flat-square)](https://www.versioneye.com/user/projects/593aa5e0822da00068d167ee)

Templates
=========

All the stuff it's easier to get a computer to remember for me.

Utils
-----

Handy objects for any tests. 

Hamcrest Matchers 
-----------------

Some of the different hamcrest matchers.

`test/java/templates/hamcrest`

Cucumber/BDD 
------------

Glue and the runner are java in `test/java/templates/cucumber`

Features are in `test/resources/cucumber`
 - this in defined `AllBDD.java` 

Selenium
--------

Utilities and Rules which help in Selenium webdriver tests. Pages and test
showing examples of page objects and loadableobject.

Selenium tests require docker components and run as integration tests.