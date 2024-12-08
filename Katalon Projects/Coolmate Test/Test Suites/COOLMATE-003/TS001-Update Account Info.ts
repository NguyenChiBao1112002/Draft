<?xml version="1.0" encoding="UTF-8"?>
<TestSuiteEntity>
   <description></description>
   <name>TS001-Update Account Info</name>
   <tag></tag>
   <isRerun>false</isRerun>
   <mailRecipient></mailRecipient>
   <numberOfRerun>0</numberOfRerun>
   <pageLoadTimeout>30</pageLoadTimeout>
   <pageLoadTimeoutDefault>true</pageLoadTimeoutDefault>
   <rerunFailedTestCasesOnly>false</rerunFailedTestCasesOnly>
   <rerunImmediately>false</rerunImmediately>
   <testSuiteGuid>c4e72d4d-a1f4-4d25-8a30-47f47062c18b</testSuiteGuid>
   <testCaseLink>
      <guid>5664bd47-80ad-4549-a1e7-c307c5b15e8a</guid>
      <isReuseDriver>false</isReuseDriver>
      <isRun>true</isRun>
      <testCaseId>Test Cases/COOLMATE-003/TC002-Update with invalid phone number</testCaseId>
      <testDataLink>
         <combinationType>ONE</combinationType>
         <id>1a008588-c06f-41d9-a56f-9b13251a9528</id>
         <iterationEntity>
            <iterationType>ALL</iterationType>
            <value></value>
         </iterationEntity>
         <testDataId>Data Files/COOLMATE-003/InvalidPhoneNumbers</testDataId>
      </testDataLink>
      <usingDataBindingAtTestSuiteLevel>true</usingDataBindingAtTestSuiteLevel>
      <variableLink>
         <testDataLinkId>1a008588-c06f-41d9-a56f-9b13251a9528</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>Invalid Phone Number</value>
         <variableId>20ea304b-e19c-4526-96da-10b0bc0b91a0</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId>1a008588-c06f-41d9-a56f-9b13251a9528</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>Expected Error Message</value>
         <variableId>52d333c9-5d3a-4600-8a98-f087f3983316</variableId>
      </variableLink>
   </testCaseLink>
   <testCaseLink>
      <guid>6a15f304-9216-4a8b-ab5f-a1f5f694f0d7</guid>
      <isReuseDriver>false</isReuseDriver>
      <isRun>true</isRun>
      <testCaseId>Test Cases/COOLMATE-003/TC003-Update with exsting phone number</testCaseId>
      <testDataLink>
         <combinationType>ONE</combinationType>
         <id>a49ca4b7-11ef-48eb-aa6c-4d46ff8d2199</id>
         <iterationEntity>
            <iterationType>ALL</iterationType>
            <value></value>
         </iterationEntity>
         <testDataId>Data Files/COOLMATE-003/ExstingPhoneNumbers</testDataId>
      </testDataLink>
      <usingDataBindingAtTestSuiteLevel>true</usingDataBindingAtTestSuiteLevel>
      <variableLink>
         <testDataLinkId>a49ca4b7-11ef-48eb-aa6c-4d46ff8d2199</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>Exsting Phone Number</value>
         <variableId>944b7164-1b88-4bb0-bfdb-d6d8b5c000b9</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId>a49ca4b7-11ef-48eb-aa6c-4d46ff8d2199</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>Expected Error Message</value>
         <variableId>84b474bd-e388-406a-ba54-fe084cf469b2</variableId>
      </variableLink>
   </testCaseLink>
</TestSuiteEntity>
