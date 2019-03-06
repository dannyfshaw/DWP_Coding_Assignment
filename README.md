## Content Based Route Micro-service
## DWP Coding assessment


### Skeleton Project without full implementation to MongoDB.
This project show the baseic flow and recommended structure for building a full RabbitMQ, MongoDB, Spring Boot application

### Project STATUS 06032019

This project is only partially complete.  The MongoDB service call has not been implemented.
There are very few test classes.

COMPLETED FUNCTIONALITY
--Writing to and reading from the AMQueue.
--RestEndpoint for posting notifications to. With this format.

POST
http://localhost:8080/handleInput
 
---
{
    "documentId" : "32b568bc-4104-4f85-b675-165c5ed18733",
    "documentType" : "UC9999",
    "documentAuthority" : "LA0246",
    "documentContent" : "Lorem ipsum dolor sit amet, consectetur adipiscing..."
}

---
POST
http://localhost:8080/publishToLocalAuthorityQueue.

{
    "documentId" : "32b568bc-4104-4f85-b675-165c5ed18733",
    "documentType" : "UC9999",
    "documentAuthority" : "LA0246",
    "documentContent" : "Lorem ipsum dolor sit amet, consectetur adipiscing..."
}
