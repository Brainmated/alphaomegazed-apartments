AOZ Apartments API Usage
Overview

This document serves as a guide for running and interacting with the AOZ Apartments API. It includes instructions on building and running Docker containers, using Postman to send requests, and information about authentication with Bearer Tokens.
Building and Running the Container

    To build the Docker image, navigate to the directory containing the Dockerfile and run:

bash

docker build -t aoz-apartments .

    Once the image is built, you can run a container with:

bash

docker run -dp 8080:8080 aoz-apartments

Replace 8080:8080 with the desired port mapping.

Using Postman
Authenticating

Before you can use the API endpoints, you need to authenticate and obtain a Bearer Token.
Registration

    Set the request type to POST and the URL to localhost:8080/register endpoint.
    In the Body tab, switch to raw and select JSON. Enter the user credentials:

json

{
  "username": "newuser",
  "password": "newpassword",
  "role": "BASE_USER" #The user roles vary from BASE_USER, ADMIN and MODERATOR.
}

    Send the request. You'll receive a response containing the authentication token.
    Example:
    {
    "message": "User id has been registered.",
    "token": "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJuZXd1c2VyMiIsImlhdCI6MTcxMDQxODg4NSwiZXhwIjoxNzEwNTA1Mjg1fQ.a_Yx6mmmEAQl1o-WqkCoaS7IQ8k4Oj7QRazVr8bsaaa8c6Ta9s2535RWyNr4uLBY"
    }

Login

    Set the request type to POST and the URL to your API's /login endpoint.
    Similar to registration, provide your username and password in the request body.
    Send the request. You'll receive a response containing the authentication token.
    Example:
    {
    "message": "Successful login for user id.",
    "token": "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJuZXd1c2VyMiIsImlhdCI6MTcxMDQxODkzNSwiZXhwIjoxNzEwNTA1MzM1fQ.3QJJyRhpQpQse43plhefyJOb7qBjybV1gL-T3KVuopqgHaryNFUjuN20wsRtKa7k"
    }
    
Using the Token

For all subsequent requests, you will need to include the Bearer Token in the header.

    Copy the token from the authentication response.
    In Postman, go to the Headers tab.
    Add a new key called Authorization.
    Set the value to Bearer <your_token>, replacing <your_token> with the token you copied.
    Example:

API Endpoints
Create Apartment

    POST /apartments/: Creates a new apartment entry.
    Body:
    {
        "id": 17,
        "picture": "http://example.com/images/apartment.jpg",
        "address": "456 Elm Street, Sometown, ST 67890",
        "area": 850.5,
        "monthlyRent": 1200.0,
        "rooms": {}
    }
    OR: #To include rooms
    {
        "picture": "http://example.com/images/apartment.jpg",
        "address": "456 Elm Street, Sometown, ST 67890",
        "area": 850.5,
        "monthlyRent": 1200.00,
        "rooms": {
          "bedroom": 2,
          "bathroom": 1,
          "livingRoom": 1,
          "kitchen": 1
          }
    }

Add Rooms

    POST /apartments/{id}/rooms: Adds rooms to an existing apartment.
    Path Variable: id of the apartment.
    Body: Array of RoomUpdateDto objects.
    Example:
    "rooms": {
          "bedroom": 2,
          "bathroom": 1,
          "livingRoom": 1,
          "kitchen": 1
          }

Get Apartment

    GET /apartments/{id}: Retrieves details of a specific apartment.
    Path Variable: id of the apartment.
    Example for /apartments/{17}
    {
        "id": 17,
        "picture": "http://example.com/images/apartment.jpg",
        "address": "456 Elm Street, Sometown, ST 67890",
        "area": 850.5,
        "monthlyRent": 1200.0,
        "rooms": {}
    }

Update Apartment

    PUT /apartments/{id}: Updates an existing apartment.
    Path Variable: id of the apartment.
    Body: Updated Apartment object.

Delete Apartment

    DELETE /apartments/{id}: Deletes a specific apartment.
    Path Variable: id of the apartment.

List Apartments

    GET /apartments: Lists all apartments.

Common Postman Steps

    Set the request type to GET, POST, PUT, or DELETE according to the operation you want to perform.
    Enter the full request URL.
    If applicable, add the request body by selecting the Body tab, choosing raw, and entering the JSON data.
    Make sure you've included the Bearer Token in the request header.

