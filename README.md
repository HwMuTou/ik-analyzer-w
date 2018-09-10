# ik-analyzer-w
根据ik-analyzer源码重制版

# Add Jar 

```bash
cp ik-analyzer-solr-6.3.0.jar solr-6.3.0/server/solr-webapp/webapp/WEB-INF/lib/
```

# add config

```xml
  <fieldType name="text_ik" class="solr.TextField">
    <analyzer>
      <tokenizer class="org.wltea.analyzer.lucene.IKTokenizerFactory" useSmart="false"/>
      <filter class="solr.WordDelimiterFilterFactory" catenateNumbers="1" generateNumberParts="1" splitOnCaseChange="1" generateWordParts="1" catenateAll="0" catenateWords="1"/>
      <filter class="solr.LengthFilterFactory" min="1" max="512" />
      <filter class="solr.LowerCaseFilterFactory"/>
      <filter class="solr.SnowballPorterFilterFactory" language="English" protected="protwords.txt"/>
      <filter class="solr.RemoveDuplicatesTokenFilterFactory"/>
    </analyzer>
  </fieldType>
```
