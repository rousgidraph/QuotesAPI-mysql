# QuotesAPI-mysql
An implementation of quotes api but using the MYSQL Database


## End points and Payloads

#### *Add User*
>Post : localhost:8080/api/v1/user/addUser

*Request*

```json
{
	"firstName": "Samuel",
	"secondName": "Kevin",
	"email": "masculinity@male.com"
}
```

*Response*

```json
{
    "userId": 2,
    "firstName": "Daniel",
    "secondName": "Simon",
    "email": "masculinity@male.com",
    "dateJoined": "2022-10-14T05:22:29.748+00:00",
    "submittedQuotesCount": 0
}
```

#### *Add Quote*

>Post: localhost:8080/api/v1/quote/newQuote?submittedBy=1

*Request Parameters*

|Name|Description|Example|
|---|---|---|
|submittedBy|Who is submitting the quote?|1|

*Request*

```json
{
	"quoteStatement": "Courage is not the absence of fear, but the ability to act despite it. "
}
```

*Response*

```json
{
    "submittedQuote": {
        "quoteId": 2,
        "quoteStatement": "Courage is not the absebce of fear, but the ability to act despite it. ",
        "quoteTagsCount": 0,
        "verifiersCount": 0,
        "submittedBy": {
            "userId": 1,
            "firstName": "Samuel",
            "secondName": "Kevin",
            "email": "masculinity@male.com",
            "dateJoined": "2022-10-13T11:54:24.665+00:00",
            "submittedQuotesCount": 0
        },
        "dateSubmitted": "2022-10-14T05:18:22.710+00:00"
    },
    "verificationLink": "http://localhost:8080/api/v1/quote/verify?quoteId=2"
}
```

#### *Verify the quote*

> Get : localhost:8080/api/v1/quote/verify?quoteId=1&userId=1

*Request Parameters*

|Name|Description|Example|
|---|---|---|
|quoteId|Which quote are you verifying?|1|
|userId|Who is verifying the quote?|1|


*Response*

```json
{
    "verifierId": 2,
    "verifiedBy": "http://localhost:8080/api/v1/user/findUser/2",
    "quoteID": 2,
    "quoteLink": "http://localhost:8080/api/v1/quote/find/2"
}
```

*Errors*

A user can not verify a quote twice.

```json
{
    "errorMessage": "It appears this user has already verified this Quote",
    "errorCode": 400
}
```

A user attempting to verify their own quote.

```json
{
    "errorMessage": "A user can not verify their own quote, request another user to verify the quote",
    "errorCode": 400
}
```

#### *Find User*

> Get : localhost:8080/api/v1/user/findUser/{userId}

Url parameters

|Name|Description|Example|
|---|---|---|
|userId|The user you are looking for. |1|

*Response*

```json
{
    "userId": 1,
    "firstName": "Samuel",
    "secondName": "Kevin",
    "email": "masculinity@male.com",
    "dateJoined": "2022-10-13T11:54:24.665+00:00",
    "submittedQuotesCount": 2
}
```


#### *Find Quote*

> Get : http://localhost:8080/api/v1/quote/find/{quoteId}

Url parameters

|Name|Description|Example|
|---|---|---|
|quoteId|The quote you are looking for. |1|

*Response*

```json
{
    "quoteId": 1,
    "quoteStatement": "Laughter is like music to the soul.",
    "quoteTagsCount": 1,
    "verifiersCount": 0,
    "submittedBy": {
        "userId": 1,
        "firstName": "Samuel",
        "secondName": "Kevin",
        "email": "masculinity@male.com",
        "dateJoined": "2022-10-13T11:54:24.665+00:00",
        "submittedQuotesCount": 0
    },
    "dateSubmitted": "2022-10-13T11:54:32.297+00:00"
}
```

#### *Tag Quote*

> Post : http://localhost:8080/api/v1/quote/addTags

Body parameters
|Name|Description|Example|
|---|---|---|
|quoteId|The quote to add the tag to |1|
|tagId|The tag id to add (*optional*)|1|
|tag|The tag (string) to add  (*optional*)|"Technical"|

> The request should have atleast the tag string or the tagId, not both.


*Request*

```json
{
    "quoteId": 2,
    "tag": "something educational"
}
```


*Response*

```json
{
    "quoteId": 2,
    "tagId": 1,
    "tag": "something educational",
    "status": "Success"
}
```

:sunglasses: :fire:

## Containerisation

I've made use of JIB from google to assist with the creating of a docker container.
There are several ways to achieve this, not sure if there is a better way. (:unamused: Still have a lot to learn.)

### Add a JIB Profile

```xml
<profiles>
   <profile>
      <id>jib</id>
      <build>
         <plugins>
            <plugin>
               <groupId>com.google.cloud.tools</groupId>
               <artifactId>jib-maven-plugin</artifactId>
               <version>0.10.1</version>
               <configuration>
                  <from>
                     <image>openjdk:11-jre</image>
                  </from>
                  <to>
                     <image>${docker.name}</image>
                  </to>
                  <container>
                     <environment>
                        <_JAVA_OPTIONS>'-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005'</_JAVA_OPTIONS>
                        <swarm.http.port>8080</swarm.http.port>
                     </environment>
                     <ports>
                        <port>8080</port>
                        <port>5005</port>
                     </ports>
                     <useCurrentTimestamp>true</useCurrentTimestamp>
                  </container>
               </configuration>
               <executions>
                  <execution>
                     <phase>package</phase>
                     <goals>
                        <goal>dockerBuild</goal>
                     </goals>
                  </execution>
               </executions>
            </plugin>
         </plugins>
      </build>
   </profile>
</profiles>

```
#### Load To Docker Demon (locally)
```
 mvn package -Pjib
```

#### Load To Docker hub

```
mvn jib:build -Pjib
```

### Add JIB Directly as a plugin

```xml
<plugin>
   <groupId>com.google.cloud.tools</groupId>
   <artifactId>jib-maven-plugin</artifactId>
   <version>3.3.0</version>
   <configuration>
      <to>
         <image>docker.io/rousgidraph/${artifactId}</image>
      </to>
   </configuration>
</plugin>
```

#### Load To Docker Demon (locally)

```
mvn compile jib:dockerBuild
```

#### Load To Docker hub

```
mvn compile com.google.cloud.tools:jib-maven-plugin:2.5.0:build -Dimage=quotes-api-mysql
```






