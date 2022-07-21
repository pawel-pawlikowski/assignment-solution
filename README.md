# assignment-solution

This is an attempted solution to the recruitment assignment. This version was prepared from scratch within one working day. 
Currently it's not intgrated with Swagger so the basic API description is provided below.

API: 

method: PUT
url: /api/v1/account
body: {
    "firstName": <first name>,
    "lastName": <last name>,
    "initialBalance": <initial balance>,
    "pesel": <PESEL number>
}

method: GET
url: /api/v1/account?pesel=<PESEL number>

method: POST
url: /api/v1/account/transfer
body: {
    "from": <USD | PLN>,
    "to": <USD | PLN>,
    "amount": <amount>,
    "pesel": <PESEL number>
}
