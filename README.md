Cars API tech test

List of APIs added:

- to add the car
  - POST /car with request with car properties
- to retrieve all the cars
  - GET /car
- to retrieve a car with id
  - GET /car/{id}
- to remove a car with id
  - DELETE /car/{id}
- to update a car
  - PUT /car/{id} with request to update the car properties

- persistence behaviour of added cars between application restarts

Test cases for
 - all the above APIs
 - service class