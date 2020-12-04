# Install and configure

* The Azure Functions Core Tools version 3.x..
* The Azure CLI version 2.4 or later.

1. Login to Azure  
$ az login -u <username> -p <password>

2. Set Azure Subscription for which to create Service Principal  
$ az account set -s <subscription-id>

3. Create SP with unique name  
$ az ad sp create-for-rbac --name <my-unique-name>

* This will yield something like:  
{  
  "appId": "<servicePrincipalId>",  
  "displayName": "<name>",  
  "name": "<name>",  
  "password": "<password>",  
  "tenant": "<tenantId>"  
}  

4. Set environment variables with values from above service principal  
$ export AZURE_SUBSCRIPTION_ID='<subscriptionId (see above, step 2)>'  
$ export AZURE_TENANT_ID='<tenantId>'  
$ export AZURE_CLIENT_ID='<servicePrincipalId>'  
$ export AZURE_CLIENT_SECRET='<password>'  
  
5. Run and deploy  
$ mvn clean package  
$ mvn azure-functions:run  
$ mvn azure-functions:deploy  
  
Referência

[https://docs.microsoft.com/en-us/azure/azure-functions/create-first-function-cli-java?WT.mc_id=-youtube-jogiles&tabs=bash%2Cazure-cli%2Cbrowser](
https://docs.microsoft.com/en-us/azure/azure-functions/create-first-function-cli-java?WT.mc_id=-youtube-jogiles&tabs=bash%2Cazure-cli%2Cbrowser)