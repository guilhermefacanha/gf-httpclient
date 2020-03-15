#maven build command
$ clean verify install deploy

#update from pom:
	<description>GF HTTP Cliente based on OkHttp4 lib</description>

#add scope compile for dependencies
#remove build block


#update maven-metadata.xml (Example)
<?xml version="1.0" encoding="UTF-8"?>
<metadata>
  <groupId>br.com.gfsolucoesti</groupId>
  <artifactId>gf-httpclient</artifactId>
  <versioning>
    <lastest>1.0.2</lastest>
    <release>1.0.2</release>
    <versions>
      <version>1.0.2</version>
      <version>1.0.1</version>
      <version>1.0.0</version>
    </versions>
    <lastUpdated>20200315045948</lastUpdated>
  </versioning>
</metadata>




