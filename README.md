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


