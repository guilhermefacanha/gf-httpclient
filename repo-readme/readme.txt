#maven build command
$ clean verify install deploy

#update from pom:
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<groupId>br.com.gfsolucoesti</groupId>
	<artifactId>gfhttpclient</artifactId>
	<version>1.0.1</version>
	<description>GF HTTP Cliente based on OkHttp4 lib</description>

#add scope compile for dependencies


#update maven-metadata.xml (Example)
<?xml version="1.0" encoding="UTF-8"?>
<metadata>
  <groupId>br.com.gfsolucoesti</groupId>
  <artifactId>gfhttpclient</artifactId>
  <versioning>
    <latest>1.0.1</latest>
    <release>1.0.1</release>
    <versions>
      <version>1.0.0</version>
      <version>1.0.1</version>
    </versions>
    <lastUpdated>20200312201645</lastUpdated>
  </versioning>
</metadata>



