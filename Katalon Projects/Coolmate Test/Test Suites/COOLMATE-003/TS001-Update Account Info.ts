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
   <testSuiteGuid>39592f94-63cd-4787-af4f-0624dbc84581</testSuiteGuid>
   <testCaseLink>
      <guid>0fe45638-1aa3-43a0-8532-466935cb797d</guid>
      <isReuseDriver>false</isReuseDriver>
      <isRun>true</isRun>
      <testCaseId>Test Cases/COOLMATE-003/TC002-Update with invalid phone number</testCaseId>
      <testDataLink>
         <combinationType>ONE</combinationType>
         <id>62f33c5a-5918-48f1-a4a1-94338b29409d</id>
         <iterationEntity>
            <iterationType>ALL</iterationType>
            <value></value>
         </iterationEntity>
         <testDataId>Data Files/COOLMATE-003/Invalid Phone Numbers</testDataId>
      </testDataLink>
      <usingDataBindingAtTestSuiteLevel>true</usingDataBindingAtTestSuiteLevel>
      <variableLink>
         <testDataLinkId>62f33c5a-5918-48f1-a4a1-94338b29409d</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>Invalid Phone Number</value>
         <variableId>20ea304b-e19c-4526-96da-10b0bc0b91a0</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId>62f33c5a-5918-48f1-a4a1-94338b29409d</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>Expected Error Message</value>
         <variableId>52d333c9-5d3a-4600-8a98-f087f3983316</variableId>
      </variableLink>
   </testCaseLink>
   <testCaseLink>
      <guid>46b09b7f-e90a-44bd-9a44-daf6352fc690</guid>
      <isReuseDriver>false</isReuseDriver>
      <isRun>true</isRun>
      <testCaseId>Test Cases/COOLMATE-003/TC003-Update with existing phone number</testCaseId>
      <testDataLink>
         <combinationType>ONE</combinationType>
         <id>b3bca2a7-6f60-4fee-9fda-bfa6e1a6dc19</id>
         <iterationEntity>
            <iterationType>ALL</iterationType>
            <value></value>
         </iterationEntity>
         <testDataId>Data Files/COOLMATE-003/Existing Phone Numbers</testDataId>
      </testDataLink>
      <usingDataBindingAtTestSuiteLevel>true</usingDataBindingAtTestSuiteLevel>
      <variableLink>
         <testDataLinkId>b3bca2a7-6f60-4fee-9fda-bfa6e1a6dc19</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>Existing Phone Number</value>
         <variableId>944b7164-1b88-4bb0-bfdb-d6d8b5c000b9</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId>b3bca2a7-6f60-4fee-9fda-bfa6e1a6dc19</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>Expected Error Message</value>
         <variableId>84b474bd-e388-406a-ba54-fe084cf469b2</variableId>
      </variableLink>
   </testCaseLink>
</TestSuiteEntity>
