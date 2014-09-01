meekan-java-sdk
===============

Please check the documentation to all the methods over [here](http://playground.meekan.com)

## JAVA SDK

You will need except of the sdk to use the following jars.

jackson-core-2.3.3
jackson-annotations-2.3.3
jackson-databind-2.3.3

You can find them over [here](http://wiki.fasterxml.com/JacksonDownload)


## Code Example

```
// Initialize the meekan api with your api key
MeekanApi meekanApi = new MeekanApi(apiKey); 
// Credentials for icloud authenticate
ICloudAuthenticate iCloudAuthenticate = new ICloudAuthenticate(username, password, icloudId) 
ApiRequestResponse icloudAuthenticate2 = meekanApi.icloudAuthenticate(iCloudAuthenticate); 

// Get account ids for each email
Collection<String> idsOfAccounts = Arrays.asList("example@meekan.com", "meekan@example.com");
ApiRequestResponse response = meekanApi.getIdsToAccounts(idsOfAccounts);
System.out.println(response.getResponse());
// Get all meeting that changed since ..
ApiRequestResponse meetings = meekanApi.getMeetings((System.currentTimeMillis() / 1000) - 10000);
System.out.println(meetings.getResponse());
```
