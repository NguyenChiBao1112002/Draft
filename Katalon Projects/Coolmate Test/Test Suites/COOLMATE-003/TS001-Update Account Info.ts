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
      <guid>cb1e1483-47d4-4283-bc7e-461981cd3904</guid>
      <isReuseDriver>false</isReuseDriver>
      <isRun>true</isRun>
      <testCaseId>Test Cases/COOLMATE-003/TC002-Update with invalid phone number</testCaseId>
      <testDataLink>
         <combinationType>ONE</combinationType>
         <id>505ab881-9541-4cb6-a46e-4ffdf1b4aa38</id>
         <iterationEntity>
            <iterationType>ALL</iterationType>
            <value></value>
         </iterationEntity>
         <testDataId>Data Files/COOLMATE-003/Invalid Phone Numbers</testDataId>
      </testDataLink>
      <usingDataBindingAtTestSuiteLevel>true</usingDataBindingAtTestSuiteLevel>
      <variableLink>
         <testDataLinkId>505ab881-9541-4cb6-a46e-4ffdf1b4aa38</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>Invalid Phone Number</value>
         <variableId>20ea304b-e19c-4526-96da-10b0bc0b91a0</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId>505ab881-9541-4cb6-a46e-4ffdf1b4aa38</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>Expected Error Message</value>
         <variableId>52d333c9-5d3a-4600-8a98-f087f3983316</variableId>
      </variableLink>
   </testCaseLink>
   <testCaseLink>
      <guid>0c68639b-0a66-4961-b9c7-9721c562d671</guid>
      <isReuseDriver>false</isReuseDriver>
      <isRun>true</isRun>
      <testCaseId>Test Cases/COOLMATE-003/TC003-Update with existing phone number</testCaseId>
      <testDataLink>
         <combinationType>ONE</combinationType>
         <id>54c89760-7954-41a0-b20b-28dd5f914591</id>
         <iterationEntity>
            <iterationType>ALL</iterationType>
            <value></value>
         </iterationEntity>
         <testDataId>Data Files/COOLMATE-003/Existing Phone Numbers</testDataId>
      </testDataLink>
      <usingDataBindingAtTestSuiteLevel>true</usingDataBindingAtTestSuiteLevel>
      <variableLink>
         <testDataLinkId>54c89760-7954-41a0-b20b-28dd5f914591</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>Existing Phone Number</value>
         <variableId>944b7164-1b88-4bb0-bfdb-d6d8b5c000b9</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId>54c89760-7954-41a0-b20b-28dd5f914591</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>Expected Error Message</value>
         <variableId>84b474bd-e388-406a-ba54-fe084cf469b2</variableId>
      </variableLink>
   </testCaseLink>
</TestSuiteEntity>
